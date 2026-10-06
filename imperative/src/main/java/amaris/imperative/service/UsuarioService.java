package amaris.imperative.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import amaris.imperative.model.Usuario;
import amaris.imperative.repository.UsuarioRepository;
import amaris.imperative.service.interfaces.UsuarioInterface;

@Service
public class UsuarioService implements UsuarioInterface{

    private final UsuarioRepository repository;
    private final PasswordEncoder encoder;

    public UsuarioService(UsuarioRepository repository, PasswordEncoder encoder){
        this.repository = repository;
        this.encoder = encoder;
    }

    @Override
    public Optional<Usuario> findById(Long id){
        return repository.findById(id);

    }
    
    @Override
    public Usuario save(Usuario usuario){
        return repository.save(usuario.setPassworde(encoder.encode(usuario.getPassword())));

    }

    @Override
    public Usuario update(Usuario usuario){
        return repository.save(usuario.setPassworde(encoder.encode(usuario.getPassword())));
    }

    @Override
    public List<Usuario> findAll(){
        return repository.findAll();

    }
    @Override
    public boolean deleteById(Long id){
        repository.deleteById(id);
        return !repository.findById(id).isPresent();
    }

}