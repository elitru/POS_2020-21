package at.eliastrummer.pojo;

import javax.xml.bind.annotation.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class Sun {

    @XmlAttribute
    private String rise;
    @XmlAttribute
    private String set;
}