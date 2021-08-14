package hellojpa;

import javax.persistence.Entity;
import java.util.List;

@Entity
public class Item {
    private Long id;
    private String name;
    private int price;
    private int stockQuantity;
    private List<Object> categories;
}
