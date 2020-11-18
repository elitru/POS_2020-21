package at.eliastrummer.contactapp.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Company implements Comparable<Company>{
    private String name;
    private String stockmarket;

    @Override
    public int compareTo(Company o) {
        return this.name.compareTo(o.getName());
    }
}
