package proposta.mv.desafio.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "TB_ESTADO")
public class StateModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID_ESTADO")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_tb_estado")
    @SequenceGenerator(name = "seq_tb_estado", sequenceName = "seq_tb_estado", allocationSize = 1)
    private Long idState;

    @Column(name = "NM_ESTADO", length = 50)
    private String nameState;

    @Column(name = "CD_ESTADO", length = 2)
    private String abbreviation;

    @JsonIgnore
    @OneToMany(mappedBy = "state")
    private List<CityModel> cities;

}
