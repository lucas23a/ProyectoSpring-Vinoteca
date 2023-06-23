package lucas.vinoteca.entidades;

import java.util.Date;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Vino {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String nombre;
    private String tipo;
    @ManyToOne
    private Cepa cepa;
    @ManyToOne
    private Bodega bodega;
    private Integer aniada;
    private double graduacionAlcohol;
    private double precio;
    private String descripcion;
    @Temporal(TemporalType.DATE)
    private Date alta;

}
