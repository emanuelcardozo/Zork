package motorDeInstrucciones;
import java.util.ArrayList;
import java.util.List;

public class Broker {
   private List<Order> orderList = new ArrayList<Order>(); 
   
   public void takeOrder(Order order) {
	   orderList.add(order);
   }
   
   public String placeOrders() {
	   String orderExecuted = "";
	   
	   for(Order order : orderList) {
		   orderExecuted = order.execute();
	   }
	   orderList.clear();
	   
	   return orderExecuted;
   }	   
}
