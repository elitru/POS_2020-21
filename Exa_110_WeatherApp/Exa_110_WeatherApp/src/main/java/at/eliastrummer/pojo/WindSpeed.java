package at.eliastrummer.pojo;

import javax.xml.bind.annotation.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class WindSpeed {

    @XmlAttribute
    private float value;
    @XmlAttribute
    private String name;
}