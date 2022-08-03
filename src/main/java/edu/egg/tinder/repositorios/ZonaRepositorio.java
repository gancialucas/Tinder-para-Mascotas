package edu.egg.tinder.repositorios;

import edu.egg.tinder.entidades.Zona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Lucas Gancia
 */
@Repository
public interface ZonaRepositorio extends JpaRepository<Zona, String> {

}
