CREATE TABLE empleados (
    primer_apellido VARCHAR(20) NOT NULL,
    segundo_apellido VARCHAR(20) NOT NULL,
    primer_nombre VARCHAR(20) NOT NULL,
    otros_nombres VARCHAR(50),
    pais_empleo VARCHAR(50),
    tipo_identificacion VARCHAR(20),
    numero_identificacion VARCHAR(20) UNIQUE NOT NULL,
    correo_electronico VARCHAR(300) UNIQUE NOT NULL,
    fecha_ingreso DATE NOT NULL CHECK (fecha_ingreso <= current_date AND fecha_ingreso >= current_date - interval '1 month'),
    area VARCHAR(50),
    estado VARCHAR(20),
    fecha_registro TIMESTAMP DEFAULT NOW(),
    PRIMARY KEY (numero_identificacion)
);

--Drop table empleados