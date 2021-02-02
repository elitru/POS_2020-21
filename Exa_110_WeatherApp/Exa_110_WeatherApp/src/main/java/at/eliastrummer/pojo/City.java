package at.eliastrummer.pojo;

import javax.xml.bind.annotation.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class City {

    @XmlAttribute
    private String id;
    @XmlAttribute
    private String name;

    private String country;

    private String timezone;

    private Coordinate coord;
    
    private Sun sun;
}
