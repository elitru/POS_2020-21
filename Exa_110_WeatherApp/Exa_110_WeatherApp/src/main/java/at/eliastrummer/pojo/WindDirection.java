package at.eliastrummer.pojo;

import javax.xml.bind.annotation.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class WindDirection {

    @XmlAttribute
    private int value;
    @XmlAttribute
    private String name;
}
