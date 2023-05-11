package org.example.securityEntity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table (name = "fleas")
public class Flea {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Getter
    private String name;

    @JsonBackReference
    @Getter @Setter
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "cat_id")
    private Cat cat;

    public Flea(String name, Cat cat) {
        this.name = name;
        this.cat = cat;
    }
}
