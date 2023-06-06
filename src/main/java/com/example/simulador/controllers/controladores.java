package com.example.simulador.controllers;

import com.example.simulador.dto.dto;
import com.example.simulador.models.modelos;
import com.example.simulador.repository.repositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/acceder")
public class controladores {

    private final repositorio repo;

    @Autowired
    public controladores(repositorio repo) {
        this.repo = repo;
    }

    @GetMapping("/buscarbyid/{id}")
    public ResponseEntity<dto> findById(@PathVariable("id") int id) {
        Optional<modelos> model = repo.findById(id);

        if (model.isPresent()) {
            modelos guardar = model.get();
            dto empleado = new dto();
            empleado.setClave(guardar.getClave());
            empleado.setNombre(guardar.getNombre());
            empleado.setDireccion(guardar.getDireccion());
            empleado.setTelefono(guardar.getTelefono());
            return ResponseEntity.ok(empleado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/todos")
    public List<dto> findAll() {
        List<dto> array = new ArrayList<>();

        Iterable<modelos> empleados = repo.findAll();
        for (modelos buscar : empleados) {
            dto todos = new dto();
            todos.setClave(buscar.getClave());
            todos.setNombre(buscar.getNombre());
            todos.setDireccion(buscar.getDireccion());
            todos.setTelefono(buscar.getTelefono());
            array.add(todos);
        }
        return array;
    }

    @PostMapping("/nuevo")
    public ResponseEntity<dto> create(@RequestBody modelos ejemplo) {
        modelos nuevo = repo.save(ejemplo);

        dto llenado = new dto();
        llenado.setClave(nuevo.getClave());
        llenado.setNombre(nuevo.getNombre());
        llenado.setDireccion(nuevo.getDireccion());
        llenado.setTelefono(nuevo.getTelefono());

        return ResponseEntity.status(HttpStatus.CREATED).body(llenado);
    }

    @PutMapping("/modificar/{id}")
    public ResponseEntity<dto> update(@PathVariable("id") int id, @RequestBody modelos cuerpo) {
        Optional<modelos> buscar = repo.findById(id);

        if (buscar.isPresent()) {
            modelos empleados = buscar.get();
            empleados.setClave(cuerpo.getClave());
            empleados.setNombre(cuerpo.getNombre());
            empleados.setDireccion(cuerpo.getDireccion());
            empleados.setTelefono(cuerpo.getTelefono());

            modelos modificar = repo.save(empleados);

            dto modificado = new dto();
            modificado.setClave(modificar.getClave());
            modificado.setNombre(modificar.getNombre());
            modificado.setDireccion(modificar.getDireccion());
            modificado.setTelefono(modificar.getTelefono());

            return ResponseEntity.ok(modificado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        Optional<modelos> res = repo.findById(id);

        if (res.isPresent()) {
            repo.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
