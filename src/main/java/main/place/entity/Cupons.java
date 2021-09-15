package main.place.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Cupons {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column(name = "id_manager")
    private String idManager;

    @Column
    private String quantity;

    @Column(name= "count_using")
    private String countUsing;

    @Column
    private String type;
    //criar enum para listar os tipos

    @Column
    private String value;

    @Column(name = "is_valid")
    private String isValid;
}
