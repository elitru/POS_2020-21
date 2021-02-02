package at.eliastrummer.pojo;

import javax.xml.bind.annotation.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Location {

    private City city;
    private Temperature temperature;
    private Humidity humidity;
    private Pressure pressure;
    private Clouds clouds;
    private Visibility visibility;
    private LastUpdate lastupdate;

    private Wind wind;
}