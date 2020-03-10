package ai.glider;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Map;

import static junit.framework.TestCase.assertEquals;

@Test(suiteName = "testNG suite")
@Listeners(ai.glider.Reporter.class)
public class WholefoodsOrderTest {

    private static WholefoodsOrder wholefoodsFactory;

    @BeforeSuite
    public void instantiate() {
        wholefoodsFactory = new WholefoodsOrder();
    }


    @Test(testName = "Test cost by type", description = "Test cost by type", priority = 0)
    public void validateWholefoods() {
        for (Wholefoods f: Wholefoods.values()) {
            switch (f.label()) {
                case ("Cereal"):
                    assertEquals(14.0f, f.cost());
                    break;
                case ("Banana"):
                    assertEquals(7.5f, f.cost());
                    break;
                case ("Apple"):
                    assertEquals(10.0f, f.cost());
                    break;

            }
        }
    }

    @Test(testName = "Test find quantity by type to return zero with no orders", description = "Test find count by type to return zero with no orders", priority = 1)
    public void getCerealCount() {
        assertEquals(0, wholefoodsFactory.getCountByType(Wholefoods.CEREAL));
    }


    @Test(testName = "Test order by type and count", description = "Test order by type and count", priority = 2)
    public void orderFourCereal() {
        wholefoodsFactory.addToCart(Wholefoods.CEREAL, 4);
        assertEquals(4, wholefoodsFactory.getCountByType(Wholefoods.CEREAL));
    }

    @Test(testName = "Test find cost by type", description = "Test find cost by type", priority = 3)
    public void orderedCost() {
        assertEquals(56.00f, wholefoodsFactory.getCostByType(Wholefoods.CEREAL));
    }


    @Test(testName = "Test total order cost with single order", description = "Test total order cost with single order", priority = 4)
    public void totalOrderCost() {
        wholefoodsFactory.addToCart(Wholefoods.BANANA, 8);
        assertEquals(116.00f, wholefoodsFactory.getTotalOrderCost());
    }

    @Test(testName = "Test total order cost to be zero with no quantity of orders", description = "Test total order cost to be zero with no quantity of orders", priority = 5)
    public void orderNothing() {
        wholefoodsFactory.addToCart(Wholefoods.CEREAL, 0);
        wholefoodsFactory.addToCart(Wholefoods.BANANA, 0);
        wholefoodsFactory.addToCart(Wholefoods.APPLE, 0);
        assertEquals(116.00f, wholefoodsFactory.getTotalOrderCost());
    }


    @Test(testName = "Test total order cost with multiple orders", description = "Test total order cost with multiple orders", priority = 6)
    public void placeOrders() {
        wholefoodsFactory.addToCart(Wholefoods.CEREAL, 2);
        wholefoodsFactory.addToCart(Wholefoods.BANANA, 3);
        wholefoodsFactory.addToCart(Wholefoods.APPLE, 4);
        assertEquals(206.50f, wholefoodsFactory.getTotalOrderCost());
    }


    @Test(testName = "Test total order cost and quanitity with multiple orders", description = "Test total order cost and quanitity with multiple orders", priority = 7)
    public void validateWholefoodsCostAndQuantity() {
        //ArrayList< Wholefoods > orderedWholefoods = wholefoodsFactory.getOrderedWholefoods();
        Map< Wholefoods, Integer > orderedWholefoods = wholefoodsFactory.getOrderedWholefoods();

        assertEquals(21, orderedWholefoods.values().stream().mapToInt(Integer::intValue).sum());


        orderedWholefoods.keySet().forEach(wholefoods -> {
            if ("Cereals".equals(wholefoods.label())) {
                assertEquals(14.00f, wholefoods.cost());
            }

            if ("Banana".equals(wholefoods.label())) {
                assertEquals(7.50f, wholefoods.cost());
            }

            if ("Apple".equals(wholefoods.label())) {
                assertEquals(10.00f, wholefoods.cost());
            }
        });

        assertEquals(6, wholefoodsFactory.getCountByType(Wholefoods.CEREAL));
        assertEquals(84.00f, wholefoodsFactory.getCostByType(Wholefoods.CEREAL));
        assertEquals(11, wholefoodsFactory.getCountByType(Wholefoods.BANANA));
        assertEquals(82.50f, wholefoodsFactory.getCostByType(Wholefoods.BANANA));
        assertEquals(4, wholefoodsFactory.getCountByType(Wholefoods.APPLE));
        assertEquals(40.00f, wholefoodsFactory.getCostByType(Wholefoods.APPLE));
        // Validates order size
        assertEquals(21, wholefoodsFactory.getTotalOrderQuantity());
    }
}
