package edu.egg.tinder.repositorios;

import edu.egg.tinder.entidades.Mascota;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Lucas Gancia
 */
@Repository
public interface MascotaRepositorio extends JpaRepository<Mascota, String> {

    @Query("SELECT c FROM Mascota c WHERE c.usuario.id = :id AND c.baja IS NULL")
    public List<Mascota> buscarMascotasPorUsuario(@Param("id") String id);
}
