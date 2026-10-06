package amaris.imperative.repository;

import java.util.Optional;

import org.springframework.data.repository.ListCrudRepository;

import amaris.imperative.model.Usuario;


public interface UsuarioRepository extends ListCrudRepository<Usuario, Long> {
    Optional<Usuario> findByUsername(String username);
}