package ai.glider;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class WholefoodsOrder implements WholefoodsOrderInterface {

    private Map < Wholefoods, Integer > totalOrders;
    
    WholefoodsOrder() {
         totalOrders = new HashMap< Wholefoods, Integer >();
    }

    public void addToCart(final Wholefoods type, final int wholefoodsCount) {
        Integer freq = totalOrders.get(type);
        totalOrders.put(type, freq == null? wholefoodsCount:(freq + wholefoodsCount));
    }

    public Map < Wholefoods, Integer > getOrderedWholefoods() {
        return totalOrders;
    }

    public float getTotalOrderCost() {
        Set<Map.Entry< Wholefoods, Integer >>  entries = totalOrders.entrySet();
        float totalCost = 0.f;
        for(Map.Entry< Wholefoods, Integer > entry : entries){
            totalCost += getCostByType(entry.getKey());
        }
        return totalCost;
    }

    public int getCountByType(Wholefoods type) {
        Integer freq = totalOrders.get(type);
        return freq == null ? 0: freq;
    }

    public float getCostByType(Wholefoods type) {
        Integer freq = totalOrders.get(type);
        float totalCostOfType = freq == null? 0.0f:freq *type.cost();
        return totalCostOfType;
    }

    public int getTotalOrderQuantity() {
        Set<Map.Entry< Wholefoods, Integer >>  entries = totalOrders.entrySet();
        int quantity = 0;
        for(Map.Entry< Wholefoods, Integer > entry : entries){
            quantity += entry.getValue();
        }

        return quantity;
    }
}
