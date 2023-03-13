package com.cidenet.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Random;
import java.util.regex.Pattern;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.cidenet.model.Empleados;
import com.cidenet.repository.EmpleadosRepository;

@Service
public class EmpleadosService {
	
	@Autowired
	private EmpleadosRepository empleadosRepo;
	
	public Empleados crearEmpleado(String primerApellido, String segundoApellido, String primerNombre, String otrosNombres, String paisEmpleo, String tipoIdentificacion,String numeroIdentificacion, LocalDate fechaIngreso, String area) {
	    // Validar que los campos tengan los valores permitidos
	    if (!Pattern.matches("[a-zA-Z]+", primerApellido) || !Pattern.matches("[a-zA-Z]*", segundoApellido)
	            || !Pattern.matches("[a-zA-Z]+", primerNombre) || !Pattern.matches("[a-zA-Z]*", otrosNombres)
	            || (!paisEmpleo.equalsIgnoreCase("Colombia") && !paisEmpleo.equalsIgnoreCase("Estados unidos"))
	            || (!tipoIdentificacion.equalsIgnoreCase("cedula de ciudadania") && !tipoIdentificacion.equalsIgnoreCase("cedula de extranjeria") && !tipoIdentificacion.equalsIgnoreCase("pasaporte") && !tipoIdentificacion.equalsIgnoreCase("permiso especial"))
	            || !Pattern.matches("[a-zA-Z0-9-]+", numeroIdentificacion)
	            || fechaIngreso.isAfter(LocalDate.now()) || fechaIngreso.isBefore(LocalDate.now().minusMonths(1))
	            || !Arrays.asList("administracion", "financiera", "compras", "infraestructura", "operación", "talento humano", "servicios varios").contains(area)) {
	        throw new IllegalArgumentException("Los valores proporcionados no son válidos");
	    }
	    // Generar el correo electrónico
	    String correoElectronico = generarCorreoUnico(primerNombre, primerApellido, paisEmpleo);
	    // Verificar que no exista un empleado con el mismo correo electrónico
	    if (empleadosRepo.findByCorreoElectronico(correoElectronico).isPresent()) {
	        throw new IllegalArgumentException("Ya existe un empleado con el mismo correo electrónico");
	    }
	    // Crear el objeto Empleado
	    Empleados empleado = new Empleados(primerApellido, segundoApellido, primerNombre, otrosNombres, paisEmpleo, tipoIdentificacion, numeroIdentificacion, correoElectronico, fechaIngreso, area, "activo", LocalDateTime.now());
	    // Guardar el empleado en la base de datos
	    return empleadosRepo.save(empleado);
	}

	public String generarCorreoUnico(String primerNombre, String primerApellido, String paisEmpleo) {
        String correoElectronico = "";
        String dominio = "";

        // Obtener el dominio correspondiente al país de trabajo
        if (paisEmpleo.equalsIgnoreCase("Colombia")) {
            dominio = "@cidenet.com.co";
        } else if (paisEmpleo.equalsIgnoreCase("Estados Unidos")) {
            dominio = "@cidenet.com.us";
        } else {
            // Si el país de trabajo no es Colombia ni Estados Unidos, se devuelve un correo vacío
            return correoElectronico;
        }

        // Generar correo electrónico a partir del primer nombre y primer apellido
        correoElectronico = primerNombre.toLowerCase() + "." + primerApellido.toLowerCase() + dominio;

        // Verificar que el correo electrónico no exista ya en la base de datos
        while (empleadosRepo.findByCorreoElectronico(correoElectronico).isPresent()) {
            // Si ya existe un empleado con ese correo electrónico, se cambia el apellido por un número aleatorio
            correoElectronico = primerNombre.toLowerCase() + "." + primerApellido.toLowerCase() + new Random().nextInt(100) + dominio;
        }

        return correoElectronico;
    }

    public Empleados buscarEmpleadoPorId(Long numeroIdentificacion) throws Exception {
        return empleadosRepo.findById(numeroIdentificacion)
                .orElseThrow(() -> new Exception("Empleado no encontrado con Numero de identificacion: " + numeroIdentificacion));
    }
	
	public Empleados actualizarEmpleado(Long numeroIdentificacion, Empleados empleadoActualizado) throws Exception {
        Empleados empleadoExistente = empleadosRepo.findByNumeroIdentificacion(numeroIdentificacion);
        if (empleadoExistente != null) {
            empleadoExistente.setPrimerApellido(empleadoActualizado.getPrimerApellido());
            empleadoExistente.setSegundoApellido(empleadoActualizado.getSegundoApellido());
            empleadoExistente.setPrimerNombre(empleadoActualizado.getPrimerNombre());
            empleadoExistente.setOtrosNombres(empleadoActualizado.getOtrosNombres());
            empleadoExistente.setPaisEmpleo(empleadoActualizado.getPaisEmpleo());
            empleadoExistente.setTipoIdentificacion(empleadoActualizado.getTipoIdentificacion());
            empleadoExistente.setNumeroIdentificacion(empleadoActualizado.getNumeroIdentificacion());
            empleadoExistente.setCorreoElectronico(generarCorreo(empleadoActualizado.getPrimerNombre(), empleadoActualizado.getPrimerApellido(), empleadoActualizado.getPaisEmpleo()));
            empleadoExistente.setFechaIngreso(empleadoActualizado.getFechaIngreso());
            empleadoExistente.setArea(empleadoActualizado.getArea());
            empleadoExistente.setEstado(empleadoActualizado.getEstado());
            empleadoExistente.setFechaRegistro(LocalDateTime.now());
            return empleadosRepo.save(empleadoExistente);
        } else {
            throw new Exception("Empleado no encontrado con número de identificación: " + numeroIdentificacion);
        }
    }
       
    public void eliminarEmpleado(Long numeroIdentificacion) {
        // Buscamos el empleado por número de identificación
        Empleados empleado = empleadosRepo.findByNumeroIdentificacion(numeroIdentificacion);
        
        // Si el empleado no existe, lanzamos una excepción
        if (empleado == null) {
            throw new RuntimeException("No se encontró un empleado con el número de identificación " + numeroIdentificacion);
        }
        
        // Si el empleado existe, lo eliminamos de la base de datos
        empleadosRepo.delete(empleado);
    }
    
    // método para generar el correo electrónico
    private String generarCorreo(String primerNombre, String primerApellido, String paisEmpleo) {
        String dominio = "";
        if (paisEmpleo.equalsIgnoreCase("colombia")) {
            dominio = "@cidenet.com.co";
        } else if (paisEmpleo.equalsIgnoreCase("estados unidos")) {
            dominio = "@cidenet.com.us";
        } else {
            throw new IllegalArgumentException("El país de empleo debe ser 'Colombia' o 'Estados Unidos'");
        }
        String correo = (primerNombre + "." + primerApellido + dominio).toLowerCase();
        Empleados empleadoExistente = empleadosRepo.findByCorreoElectronico(correo);
        if (empleadoExistente != null) {
            throw new IllegalArgumentException("Ya existe un empleado con este correo electrónico: " + correo);
        } else {
            return correo;
        }
    }
    
	
}
