package at.eliastrummer.pojo;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;
import javafx.util.converter.LocalDateTimeStringConverter;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@ToString
public class RSSFeed {
    private String title;
    private String link;
    private String description;
    @XmlJavaTypeAdapter(DataAdapter.class)
    private LocalDateTime pubDate;
    private RSSEnclosure enclosure;
    @XmlTransient
    private boolean hidden = false;
    @XmlTransient
    private boolean read = false;
    @XmlTransient
    private UUID id = UUID.randomUUID();
    
    public String getFormattedDate() {
        return this.pubDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy - HH:mm:ss"));
    }
}