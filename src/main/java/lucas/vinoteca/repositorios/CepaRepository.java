package lucas.vinoteca.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lucas.vinoteca.entidades.Cepa;


@Repository
public interface CepaRepository extends JpaRepository<Cepa, String> {
    
}
