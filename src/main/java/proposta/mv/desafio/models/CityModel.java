package proposta.mv.desafio.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_CIDADE")
public class CityModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID_CIDADE")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_tb_cidade")
    @SequenceGenerator(name = "seq_tb_cidade", sequenceName = "seq_tb_cidade", allocationSize = 1)
    private Long idCity;

    @Column(name = "NM_CIDADE", length = 50)
    private String nameCity;

    @Column(name = "CD_ESTADO", length = 2)
    private String abbreviation;

    @ManyToOne()
    @JoinColumn(name = "ID_ESTADO")
    private StateModel state;

    @JsonIgnore
    @OneToMany(mappedBy = "city")
    private List<ClientModel> clients;
}
