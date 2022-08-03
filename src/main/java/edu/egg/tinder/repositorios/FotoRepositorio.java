package edu.egg.tinder.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.egg.tinder.entidades.Foto;

public interface FotoRepositorio extends JpaRepository<Foto, String> {
    
}
