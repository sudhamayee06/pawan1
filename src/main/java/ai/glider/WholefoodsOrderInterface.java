	package ai.glider;
	
	import java.util.HashMap;
	import java.util.Map;
	
	public interface WholefoodsOrderInterface {
	    
	
	    /**
	     * @param type The type of Wholefoods being added to the order.
	     * @param count The number of units of Wholefoods type 'type' to add to the order. 
	     */
	    public void addToCart(final Wholefoods type, final int count);
	
	
	    /**
	     * @return All the ordered Wholefoods as a mapping of Wholefoods types to Integer quantities. 
	     */
	    public Map< Wholefoods, Integer > getOrderedWholefoods();
	    
	
	    /**
	     * @param type The type of Wholefoods
	     * @return The total number of units of Wholefoods 'type' in the order. 
	     */
	    public int getCountByType(Wholefoods type);
	    
	
	    /** *
	     * @param type The type of Wholefoods being ordered
	     * @return The total cost of just the Wholefoods units of 'type' in the order. 
	     */
	    public float getCostByType(Wholefoods type);
	
	
	    /**
	     * @return The total cost of the order. 
	     */
	    public float getTotalOrderCost();
	    
	
	    /**
	     * @return The total number of all types of Wholefoods units in the order. 
	     */
	    public int getTotalOrderQuantity();
	}

