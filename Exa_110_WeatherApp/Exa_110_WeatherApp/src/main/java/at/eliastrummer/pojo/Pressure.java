package at.eliastrummer.pojo;

import javax.xml.bind.annotation.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class Pressure {

    @XmlAttribute
    private float value;
}
