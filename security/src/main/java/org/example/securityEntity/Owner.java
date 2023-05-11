package org.example.securityEntity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@EqualsAndHashCode
@Getter @Setter
@Entity
@Table (name = "owners")
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private LocalDate birthday;
    @JsonManagedReference
    @OneToMany(mappedBy = "owner",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cat> cats;

    public  Owner() {
    }
    public Owner(String name, LocalDate birthday) {
        this.name = name;
        this.birthday = birthday;
        cats = new ArrayList<Cat>();
    }

    public void addCat(Cat cat) {
        cats.add(cat);
    }

}
