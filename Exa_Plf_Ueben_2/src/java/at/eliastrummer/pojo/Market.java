package at.eliastrummer.pojo;

import java.util.Comparator;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Market {
    @XmlElement(name = "product")
    private List<Product> products;
    
    public Product getByName(String name) {
        return this.products.stream().filter(p -> p.getProductName().equals(name)).findFirst().get();
    }
    
    public void init() {
        this.products.removeIf(p -> p.getPrice() < 10 || p.getPrice() > 20);
        this.products.sort(Comparator.comparing(Product::getProductName));
    }
}