package at.eliastrummer.pojo;

import javax.xml.bind.annotation.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class Temperature {

    @XmlAttribute
    private float value;
    @XmlAttribute
    private float min;
    @XmlAttribute
    private float max;
}
