package amaris.imperative.service.interfaces;
import amaris.imperative.model.Usuario;
import java.util.List;
import java.util.Optional;

public interface UsuarioInterface{

    public Optional<Usuario> findById(Long id);
    public Usuario save(Usuario Usuario);
    public Usuario update(Usuario Usuario);
    public List<Usuario> findAll();
    public boolean deleteById(Long id);

}