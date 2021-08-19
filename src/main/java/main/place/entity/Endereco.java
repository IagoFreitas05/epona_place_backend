package main.place.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Endereco extends  EntidadeDominio{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String logradouro;

    @Column(name = "nome_logradouro")
    private String nomeLogradouro;

    private Integer numero;

    private String complemento;

    private String cep;

    @Column(name = "id_user")
    private String idUser;
}
