package at.eliastrummer.contactapp.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Company implements Comparable<Company>, Serializable {
    @EqualsAndHashCode.Include
    private String name;
    @EqualsAndHashCode.Include
    private String stockmarket;
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private List<Contact> contacts;

    @Override
    public int compareTo(Company o) {
        return this.name.compareTo(o.getName());
    }
}
