package com.cidenet.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cidenet.model.Empleados;
import com.cidenet.service.EmpleadosService;

public class EmpleadosController {
	@RestController
	@RequestMapping("/empleados")
	public class EmpleadoController {
	    
	    @Autowired
	    private EmpleadosService empleadoService;

	    @GetMapping("/consulta")
	    public Empleados buscarEmpleadoPorId(@PathVariable Long numeroIdentificacion) throws Exception {
	        return empleadoService.buscarEmpleadoPorId(numeroIdentificacion);
	    }
	    
	    @PostMapping("/crear")
	    public Empleados crearEmpleado(@RequestBody String primerApellido, String segundoApellido, String primerNombre, String otrosNombres, String paisEmpleo, String tipoIdentificacion,String numeroIdentificacion, LocalDate fechaIngreso, String area) {
	        return empleadoService.crearEmpleado(primerApellido,segundoApellido,primerNombre,otrosNombres,paisEmpleo,tipoIdentificacion,numeroIdentificacion,fechaIngreso,area);
	    }
	    
	    @PutMapping("/actualizar")
	    public Empleados actualizarEmpleado(@PathVariable Long numeroIdentificacion, @RequestBody Empleados empleadoActualizado) throws Exception {
	        return empleadoService.actualizarEmpleado(numeroIdentificacion, empleadoActualizado);
	    }
	    
	    @DeleteMapping("/{numeroIdentificacion}")
	    public ResponseEntity<?> eliminarEmpleado(@PathVariable Long numeroIdentificacion) {
	        try {
	            // Llamamos al método eliminarEmpleado del servicio
	            empleadoService.eliminarEmpleado(numeroIdentificacion);
	            
	            // Si se eliminó el empleado correctamente, devolvemos un mensaje de éxito
	            return ResponseEntity.ok().body("Empleado eliminado correctamente");
	        } catch (Exception e) {
	            // Si hubo un error, devolvemos un mensaje de error con el detalle
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el empleado: " + e.getMessage());
	        }
	    }

	}

}
