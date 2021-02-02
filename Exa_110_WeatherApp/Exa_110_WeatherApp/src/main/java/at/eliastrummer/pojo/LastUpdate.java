package at.eliastrummer.pojo;

import javax.xml.bind.annotation.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class LastUpdate {

    @XmlAttribute
    private String value;
}
