DROP DATABASE IF EXISTS bd_gestion;
CREATE DATABASE bd_gestion CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE bd_gestion;

-- 1. ASIGNATURA
CREATE TABLE Asignatura (
    Nombre VARCHAR(100) NOT NULL,
    Creditos INT NOT NULL,
    PRIMARY KEY (Nombre)
);

-- 2. USUARIO Y SUBCLASES (Herencia)
CREATE TABLE Usuario (
    Cédula VARCHAR(20) NOT NULL,
    pwHash VARCHAR(255) NOT NULL,
    Nombre VARCHAR(50) NOT NULL,
    Apellido VARCHAR(50) NOT NULL,
    Activo BOOLEAN NOT NULL DEFAULT TRUE, -- baja lógica (FALSE = dado de baja)
    PRIMARY KEY (Cédula)
);

CREATE TABLE Estudiante (
    CedulaEstudiante VARCHAR(20) NOT NULL,
    PRIMARY KEY (CedulaEstudiante),
    FOREIGN KEY (CedulaEstudiante) REFERENCES Usuario(Cédula) ON DELETE RESTRICT ON UPDATE CASCADE -- CAMBIO
);

CREATE TABLE Docente (
    CedulaDocente VARCHAR(20) NOT NULL,
    PRIMARY KEY (CedulaDocente),
    FOREIGN KEY (CedulaDocente) REFERENCES Usuario(Cédula) ON DELETE RESTRICT ON UPDATE CASCADE -- CAMBIO
);

CREATE TABLE Administrativo (
    CedulaAdmin VARCHAR(20) NOT NULL,
    PRIMARY KEY (CedulaAdmin),
    FOREIGN KEY (CedulaAdmin) REFERENCES Usuario(Cédula) ON DELETE RESTRICT ON UPDATE CASCADE
);

-- 3. CURSO (Relación INSTANCIA con Asignatura 1:N)
CREATE TABLE Curso (
    idCurso INT NOT NULL,
    Estado VARCHAR(20) NOT NULL,
    Periodo VARCHAR(20) NOT NULL,
    CuposMax INT NOT NULL,
    NombreAsignatura VARCHAR(100) NOT NULL,
    PRIMARY KEY (idCurso),
    FOREIGN KEY (NombreAsignatura) REFERENCES Asignatura(Nombre) ON DELETE RESTRICT ON UPDATE CASCADE 
);

-- 4. CURSA (Inscripción de Estudiante en Curso - Agregación)
CREATE TABLE Cursa (
    idInscripción INT NOT NULL UNIQUE, -- Identificador único para referenciar la agregación
    CedulaEstudiante VARCHAR(20) NOT NULL,
    idCurso INT NOT NULL,
    FechaInscripción DATE NOT NULL,
    Estado VARCHAR(20) NOT NULL,
    PRIMARY KEY (CedulaEstudiante, idCurso),
    FOREIGN KEY (CedulaEstudiante) REFERENCES Estudiante(CedulaEstudiante) ON DELETE RESTRICT ON UPDATE CASCADE, 
    FOREIGN KEY (idCurso) REFERENCES Curso(idCurso) ON DELETE RESTRICT ON UPDATE CASCADE 
);

-- 5. DICTA (Relación Docente-Curso 1:N)
CREATE TABLE Dicta (
    CedulaDocente VARCHAR(20) NOT NULL,
    idCurso INT NOT NULL,
    PRIMARY KEY (CedulaDocente, idCurso),
    FOREIGN KEY (CedulaDocente) REFERENCES Docente(CedulaDocente) ON DELETE RESTRICT ON UPDATE CASCADE, 
    FOREIGN KEY (idCurso) REFERENCES Curso(idCurso) ON DELETE RESTRICT ON UPDATE CASCADE 
);

-- 6. CALIFICACIÓN (Relación GENERA con la Agregación)
CREATE TABLE Calificacion (
    idCalificación INT NOT NULL,
    Nota DECIMAL(4, 2) NOT NULL,
    FechaNota DATE NOT NULL,
    TareaCalificada VARCHAR(255) NOT NULL,
    idInscripción INT NOT NULL, -- Clave foránea que apunta a la Cursada
    PRIMARY KEY (idCalificación),
    FOREIGN KEY (idInscripción) REFERENCES Cursa(idInscripción) ON DELETE RESTRICT ON UPDATE CASCADE
);

-- =====================================================================
--  DATOS DE PRUEBA
-- =====================================================================
-- USUARIOS (pwHash = contraseña cifrada con BCrypt)
INSERT INTO Usuario (Cédula, pwHash, Nombre, Apellido) VALUES
('11111111', '$2a$10$4nY1hhstC/vgfSmNIkBEduyPv85yTZVWHeCoPZJqRTiNdb/4zeSDS', 'Carlos', 'Rodríguez'),
('22222222', '$2a$10$sgyrdogs5N1bi1OcEGz4muIDpKHE3gV6gIdGku7TIGqLOE/eljhta', 'Juan', 'Pérez'),
('33333333', '$2a$10$sgyrdogs5N1bi1OcEGz4muIDpKHE3gV6gIdGku7TIGqLOE/eljhta', 'Ana', 'Gómez'),
('44444444', '$2a$10$sgyrdogs5N1bi1OcEGz4muIDpKHE3gV6gIdGku7TIGqLOE/eljhta', 'Lucas', 'Silva');

INSERT INTO Docente (CedulaDocente) VALUES ('11111111');

INSERT INTO Estudiante (CedulaEstudiante) VALUES
('22222222'), ('33333333'), ('44444444');

-- ASIGNATURAS
INSERT INTO Asignatura (Nombre, Creditos) VALUES
('Programación I', 10),
('Base de Datos I', 12);

-- CURSOS
INSERT INTO Curso (idCurso, Estado, Periodo, CuposMax, NombreAsignatura) VALUES
(101, 'Activa', '2026-S2', 30, 'Programación I'),
(102, 'Activa', '2026-S2', 25, 'Base de Datos I');

-- DOCENTE A CARGO DE LOS DOS CURSOS
INSERT INTO Dicta (CedulaDocente, idCurso) VALUES
('11111111', 101),
('11111111', 102);

-- INSCRIPCIONES (Estado = 'Activo' para que aparezcan en la ventana del docente)
INSERT INTO Cursa (idInscripción, CedulaEstudiante, idCurso, FechaInscripción, Estado) VALUES
(1, '22222222', 101, '2026-08-01', 'Activo'),
(2, '33333333', 101, '2026-08-01', 'Activo'),
(3, '44444444', 101, '2026-08-02', 'Activo'),
(4, '22222222', 102, '2026-08-01', 'Activo');

-- UNA CALIFICACIÓN CON COMENTARIO (para mostrar el historial)
INSERT INTO Calificacion (idCalificación, Nota, FechaNota, TareaCalificada, idInscripción) VALUES
(1, 8.00, '2026-09-15', 'Primer parcial: buen manejo de clases y objetos', 1);