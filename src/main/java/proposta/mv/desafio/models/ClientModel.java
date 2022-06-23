package proposta.mv.desafio.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_CLIENTE")
public class ClientModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID_CLIENTE")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_tb_cliente")
    @SequenceGenerator(name = "seq_tb_cliente", sequenceName = "seq_tb_cliente", allocationSize = 1)
    private Long idClient;

    @Column(name = "NM_CLIENTE", length = 250)
    private String nameComplete;

    @Column(name = "NM_GENERO")
    private String gender;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Column(name = "DT_ANIVERSARIO")
    private LocalDate birthDate;

    @Column(name = "NR_IDADE", nullable = false)
    private String age;

    @ManyToOne()
    @JoinColumn(name = "ID_CIDADE")
    private CityModel city;
}
