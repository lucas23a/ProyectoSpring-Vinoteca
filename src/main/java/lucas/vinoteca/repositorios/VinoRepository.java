package lucas.vinoteca.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import lucas.vinoteca.entidades.Vino;



@Repository
public interface VinoRepository extends JpaRepository<Vino,String>{
    @Query("SELECT v FROM Vino v WHERE v.nombre = :nombre")
    public Vino buscarPorNombre(@Param("nombre") String nombre);

    @Query("SELECT v FROM Vino v WHERE v.bodega.nombre = :nombre")
    public List<Vino> buscarPorBodega(@Param("nombre") String nombre);

    @Query("SELECT v FROM Vino v WHERE v.cepa.nombre = :nombre")
    public List<Vino> buscarPorCepa(@Param("nombre") String nombre);
    
}
