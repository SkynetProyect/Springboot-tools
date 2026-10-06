package amaris.imperative.controller.usuario.conversor;

import amaris.imperative.controller.usuario.dto.UsuarioCreateDto;
import amaris.imperative.controller.usuario.dto.UsuarioUpdateDto;
import amaris.imperative.controller.usuario.dto.UsuarioWatchDto;
import amaris.imperative.model.Usuario;

public class UsuarioConversor{

    public UsuarioConversor(){}

    public UsuarioWatchDto entityToWatch(Usuario entity) {
        return new UsuarioWatchDto(entity.getId(),entity.getUsername(), entity.getRol());
    }

    public Usuario updateToEntity(UsuarioUpdateDto dto) {
        return new Usuario()
                .setId(dto.id())
                .setUsername(dto.username())
                .setPassworde(dto.password())
                .setRol(dto.rol());
    }

    public Usuario createToEntity(UsuarioCreateDto dto) {
        return new Usuario()
                .setUsername(dto.username())
                .setPassworde(dto.password())
                .setRol(dto.rol());
    }


}