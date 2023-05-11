package org.example.securityEntity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode
@Getter
@Entity
@Table (name = "cats")
public class Cat { @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private LocalDate birthday;
    private String breed;
    @JoinColumn(name = "tail_length")
    private int tailLength;
    @Enumerated(EnumType.STRING)
    private ColorType color;
    @JsonBackReference
    @Setter
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @JsonManagedReference
    @OneToMany(mappedBy = "cat",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Flea> fleas;

    public Cat() { }

    public Cat(String name, LocalDate birthday, String breed, ColorType color, Owner owner, int tailLength) {
        this.name = name;
        this.birthday = birthday;
        this.breed = breed;
        this.color = color;
        this.owner = owner;
        this.tailLength = tailLength;
    }

    public Cat(String name, LocalDate birthday, String breed, ColorType color, Owner owner) {
        this.name = name;
        this.birthday = birthday;
        this.breed = breed;
        this.color = color;
        this.owner = owner;
        this.tailLength = 0;
    }

    public void addFlea(Flea flea) {
        fleas.add(flea);
    }
    public enum ColorType {white, black, gray, multicolor}
}
