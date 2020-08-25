----------------------------------------------------------------------------------------------------
-- Tabla: pacientes
----------------------------------------------------------------------------------------------------

CREATE TABLE pacientes (
    idPaciente NUMBER NOT NULL,
    rutPaciente NVARCHAR2(11) DEFAULT NULL,
    nombre NVARCHAR2(50) DEFAULT NULL,
    apellido NVARCHAR2(50) DEFAULT NULL,

    -- Llave primaria
    CONSTRAINT paciente_PK PRIMARY KEY (idPaciente)
);

----------------------------------------------------------------------------------------------------
-- Tabla: especialidades
----------------------------------------------------------------------------------------------------

CREATE TABLE especialidades (
    idEspecialidad NUMBER NOT NULL,
    descripcion NVARCHAR2(50) DEFAULT NULL,

    -- Llave primaria
    CONSTRAINT espacialidades_PK PRIMARY KEY (idEspecialidad)
);

----------------------------------------------------------------------------------------------------
-- Tabla: doctores
----------------------------------------------------------------------------------------------------

CREATE TABLE doctores (
    idDoctor NUMBER NOT NULL,
    rutDoctor NVARCHAR2(11) DEFAULT NULL,
    nombre NVARCHAR2(50) DEFAULT NULL,
    apellido NVARCHAR2(50) DEFAULT NULL,
    idEspecialidad NUMBER NOT NULL,

    -- Lave primaria
    CONSTRAINT doctores_pk PRIMARY KEY (idDoctor),

    -- Llave foránea
    CONSTRAINT doctores_especialidades_FK FOREIGN KEY (idEspecialidad) REFERENCES especialidades (idEspecialidad)
);

----------------------------------------------------------------------------------------------------
-- Tabla: agendas
----------------------------------------------------------------------------------------------------

CREATE TABLE agendas (
    idAgenda NUMBER NOT NULL,
    fecha DATE DEFAULT NULL,
    horaDesde DATE DEFAULT NULL,
    duracion NUMBER DEFAULT NULL,
    idPaciente NUMBER NOT NULL,
    idDoctor NUMBER NOT NULL,

    -- Llave primaria
    CONSTRAINT agendas_pk PRIMARY KEY (idAgenda),

    -- Llaves foráneas
    CONSTRAINT agendas_pacientes_FK FOREIGN KEY (idPaciente) REFERENCES pacientes (idPaciente),
    CONSTRAINT agendas_doctores_FK FOREIGN KEY (idDoctor) REFERENCES doctores (idDoctor)
);
