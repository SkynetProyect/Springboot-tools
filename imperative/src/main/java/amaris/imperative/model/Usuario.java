package amaris.imperative.model;

import java.util.Collection;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Table("usuario")
public class Usuario implements UserDetails{

    @Id
    private Long id;

    private String username;
    private String passworde;
    private int rol;

    public Usuario() {
    }

    public Usuario(Long id, String username, int rol, String passworde) {
        this.id = id;
        this.username = username;
        this.rol = rol;
        this.passworde = passworde;
    }

    public Long getId() {
        return id;
    }

    public Usuario setId(Long id) {
        this.id = id;
        return this;
    }

    public Usuario setUsername(String username) {
        this.username = username;
        return this;
    }

    public Usuario setPassworde(String passworde) {
        this.passworde = passworde;
        return this;
    }

    public int getRol(){
        return rol;
    }

    public Usuario setRol(int rol){
        this.rol = rol;
        return this;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String authority = switch (rol) {
            case 1 -> "ROLE_ADMIN";
            case 2 -> "ROLE_USER";
            default -> "ROLE_USER";
        };
        return List.of(new SimpleGrantedAuthority(authority));
    }

    @Override
    public String getPassword() {
        return this.passworde;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

}