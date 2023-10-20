package patikastore.models.brand;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Brand {
    private int id;
    private String name;

    public static int lastID = -1;

    public Brand(String name) {
        this.id = ++lastID;
        this.name = name;
    }
}
