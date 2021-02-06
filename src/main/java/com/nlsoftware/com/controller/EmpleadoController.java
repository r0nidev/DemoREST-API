package com.nlsoftware.com.controller;

import com.nlsoftware.com.entity.Empleado;
import com.nlsoftware.com.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api")
public class EmpleadoController {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    // método para obtener todos los empleados
    @GetMapping
    public ResponseEntity<List<Empleado>> getEmpleado(){
        List<Empleado> emp =  empleadoRepository.findAll();
        return ResponseEntity.ok(emp);
    }

    // método para un empleado en específico, por medio de su id
    @RequestMapping(value = "{id}")
    public ResponseEntity<Empleado> getEmpleadoById(@PathVariable("id") Long id){
        Optional<Empleado> optionalEmp = empleadoRepository.findById(id);
        if(optionalEmp.isPresent()){
            return ResponseEntity.ok(optionalEmp.get());
        }else {
            return ResponseEntity.noContent().build();
        }
    }

    // Método para guardar Empleado en la BD
    @PostMapping         // POST
    public ResponseEntity<Empleado> createEmpleado(@RequestBody Empleado emp){
        Empleado newEmp = empleadoRepository.save(emp);
        return ResponseEntity.ok(newEmp);
    }

    // Método para Eliminar Employee
    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> deleteEmpleado(@PathVariable("id") Long id){
        empleadoRepository.deleteById(id);
        return ResponseEntity.ok(null);
    }

    // Método para Actualizar (PUT)
    @PutMapping
    public ResponseEntity<Empleado> updateEmpleado(@RequestBody Empleado empleado){
        Optional<Empleado> optionalEmp = empleadoRepository.findById(empleado.getId());
        if(optionalEmp.isPresent()){
            Empleado updateEmp = optionalEmp.get();
            updateEmp.setNombres(empleado.getNombres());
            empleadoRepository.save(updateEmp);
            return ResponseEntity.ok(updateEmp);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    /*Empleado empleado = new Empleado();
        empleado.setId(1L);
        empleado.setNombres("Nick");*/

/*    @GetMapping
    public String hello(){ // hemos creado our first rest service.
        return "Hello HACKER";
    }*/


}
