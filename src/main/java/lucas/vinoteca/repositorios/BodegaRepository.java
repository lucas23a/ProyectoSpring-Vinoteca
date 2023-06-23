package lucas.vinoteca.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lucas.vinoteca.entidades.Bodega;


@Repository
public interface BodegaRepository extends JpaRepository<Bodega, String> {
    
}
