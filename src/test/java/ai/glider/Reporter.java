package ai.glider;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public class Reporter implements IReporter {

    @SuppressWarnings("unchecked")
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        JSONArray results = new JSONArray();
        suites.forEach(element->{
            results.add(createSuiteJsonObject(element));
        });
        String projectRootDirectory = System.getProperty("user.dir");
        try (FileWriter file = new FileWriter(projectRootDirectory + "/result.json")) {
            file.write(results.toJSONString());
        } catch (IOException e) {
            System.out.println("exception is writing JSON");
        }

    }

    @SuppressWarnings("unchecked")
   public JSONObject createSuiteJsonObject(ISuite suite) {
        JSONObject result = new JSONObject();
        JSONArray passedMethods = new JSONArray();
        JSONArray failedMethods = new JSONArray();
        suite.getResults().entrySet().forEach(element -> {
            ITestContext context = element.getValue().getTestContext();
            passedMethods.addAll(createResultJsonArray(context.getPassedTests().getAllResults()));
            failedMethods.addAll(createResultJsonArray(context.getFailedTests().getAllResults()));
            failedMethods.addAll(createResultJsonArray(context.getSkippedTests().getAllResults()));
        });
        result.put("name", suite.getName());
        result.put("passed", passedMethods);
        result.put("failed", failedMethods);
        return result;
    }

    @SuppressWarnings("unchecked")
    public JSONArray createResultJsonArray(Set<ITestResult> results) {
        JSONArray result = new JSONArray();
        results.forEach(element ->{
            JSONObject currentJsonResult = new JSONObject();
            currentJsonResult.put("methodName", element.getName());
            currentJsonResult.put("title",element.getMethod().getDescription());
            currentJsonResult.put("id",element.getMethod().getPriority());
            result.add(currentJsonResult);
        });
        return result;
    }


}
