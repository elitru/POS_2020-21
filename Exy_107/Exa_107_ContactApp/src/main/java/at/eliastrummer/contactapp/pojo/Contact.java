package at.eliastrummer.contactapp.pojo;

import at.eliastrummer.contactapp.utils.DateDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import java.io.Serializable;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Contact implements Serializable {
    private int id;
    private String firstname;
    private String lastname;
    private String gender;
    @JsonDeserialize(using = DateDeserializer.class)
    private LocalDate dateOfBirth;
    private Company company;
    private List<String> email;
    
    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    
    public String getBirthdate() {
        return DTF.format(dateOfBirth);
    }
    
    public int getAgeInYears() {
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }
    
    public long getAge() {
        return ChronoUnit.DAYS.between(dateOfBirth, LocalDate.now());
    }
}