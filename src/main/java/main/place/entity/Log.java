package main.place.entity;

import lombok.*;
import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Log extends  EntidadeDominio{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "data_cadastro")
    private String DataCadastro;

    @Column( name = "id_estudante")
    private Integer idEstudante;
}
