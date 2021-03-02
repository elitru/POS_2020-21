package at.eliastrummer.pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@XmlAccessorType(XmlAccessType.FIELD)
public class Product {
    @EqualsAndHashCode.Include
    @XmlElement(name = "product_name")
    private String productName;
    
    @EqualsAndHashCode.Exclude
    @XmlElement
    @XmlJavaTypeAdapter(PriceAdapter.class)
    private Double price;
    
    public String getFormattedPrice() {
        return String.format("%.2f €", this.price);
    }
}
