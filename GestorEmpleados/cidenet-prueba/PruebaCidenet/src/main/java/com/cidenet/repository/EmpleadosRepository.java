package com.cidenet.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cidenet.model.Empleados;

public interface EmpleadosRepository extends JpaRepository<Empleados, Long>{

	    List<Empleados> findByPrimerApellidoContainingOrSegundoApellidoContainingOrPrimerNombreContainingOr = null;
		Empleados findByCorreoElectronico(String correoElectronico);
	    Empleados crearEmpleado(Empleados empleado);
	    Empleados findByNumeroIdentificacion(Long numeroIdentificacion);

	


}
