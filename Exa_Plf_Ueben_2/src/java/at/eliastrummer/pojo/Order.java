package at.eliastrummer.pojo;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order {
    private Map<Product, Integer> orderItems = new HashMap<>();
    
    public void addOrder(Product p, int quantity) {
        if(orderItems.containsKey(p)) {
            orderItems.put(p, orderItems.get(p) + quantity);
            return;
        }
        
        orderItems.put(p, quantity);
    }
    
    public void removeProduct(Product p) {
        if(!this.orderItems.containsKey(p)) {
            return;
        }
        
        this.orderItems.remove(p);
    }
    
    public String getPrice() {
        double price = 0;
        
        for(Entry<Product, Integer> entry : this.orderItems.entrySet()) {
            price += entry.getKey().getPrice() * entry.getValue();
        }
        
        return String.format("€ %.2f", price).replace(".", ",");
    }
}
