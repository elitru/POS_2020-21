package at.eliastrummer.pojo;

import javax.xml.bind.annotation.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class Coordinate {

    @XmlAttribute
    private float lon;
    @XmlAttribute
    private float lat;
}