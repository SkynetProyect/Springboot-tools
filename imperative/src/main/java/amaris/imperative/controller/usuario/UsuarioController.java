package amaris.imperative.controller.usuario;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import amaris.imperative.controller.usuario.conversor.UsuarioConversor;
import amaris.imperative.controller.usuario.dto.UsuarioCreateDto;
import amaris.imperative.controller.usuario.dto.UsuarioUpdateDto;
import amaris.imperative.controller.usuario.dto.UsuarioWatchDto;
import amaris.imperative.service.interfaces.UsuarioInterface;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/usuario")
@Validated
public class UsuarioController {

    private final UsuarioInterface service;
    private final UsuarioConversor conversor;

    public UsuarioController(UsuarioInterface service, UsuarioConversor conversor) {
        this.service = service;
        this.conversor = conversor;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioWatchDto> findById(
            @PathVariable @Positive(message = "El id debe ser un número positivo") Long id) {

        return service.findById(id)
                .map(objeto -> conversor.entityToWatch(objeto))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<UsuarioWatchDto>> findAll() {
    return ResponseEntity.ok(
            service.findAll().stream()
                    .map(conversor::entityToWatch)
                    .toList());
    }


    @PostMapping
    public ResponseEntity<UsuarioWatchDto> save(@Valid @RequestBody UsuarioCreateDto dto) {
        return ResponseEntity.ok( 
            conversor.entityToWatch(service.save(conversor.createToEntity(dto)))
            );
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioWatchDto> update(
            @PathVariable @Positive(message = "El id debe ser un número positivo") Long id,
            @Valid @RequestBody UsuarioUpdateDto dto) {
        return ResponseEntity.ok( 
            conversor.entityToWatch(service.save(conversor.updateToEntity(dto)))
            );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteById(
            @PathVariable @Positive(message = "El id debe ser un número positivo") Long id) {
        return ResponseEntity.ok(service.deleteById(id));
    }

}