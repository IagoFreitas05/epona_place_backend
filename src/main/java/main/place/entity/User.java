package main.place.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User extends  EntidadeDominio{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column
    private String cpf;
    @Column
    private String birthday;
    @Column
    private String celphone;
    @Column
    private String gender;
    @Column
    private String mail;
    @Column
    private String password;
    @Column
    private String role;
}
