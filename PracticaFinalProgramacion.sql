CREATE DATABASE IF NOT EXISTS PracticaFinalGvhu;
USE PracticaFinalGvhu;

CREATE TABLE Usuarios (
id INT AUTO_INCREMENT PRIMARY KEY,
nombre VARCHAR(255) UNIQUE NOT NULL,
contraseña varchar(255) NOT NULL,
uuid CHAR(36) UNIQUE NOT NULL
);

CREATE TABLE Coches (
id INT AUTO_INCREMENT PRIMARY KEY,
marca VARCHAR(255) NOT NULL,
modelo VARCHAR(255) NOT NULL,
matricula VARCHAR(255) NOT NULL,
anio INT NOT NULL
);

CREATE TABLE Gastos (
id INT AUTO_INCREMENT PRIMARY KEY,
coche_id INT,
tipo VARCHAR(255) NOT NULL,
kilometraje INT NOT NULL,
fecha DATE NOT NULL,
importe DECIMAL(10, 2) NOT NULL,
descripcion TEXT,
FOREIGN KEY (coche_id) REFERENCES Coches(id)
);

CREATE TABLE Propietarios (
coche_id INT,
usuario_id INT,
PRIMARY KEY (coche_id, usuario_id),
FOREIGN KEY (coche_id) REFERENCES Coches(id),
FOREIGN KEY (usuario_id) REFERENCES Usuarios(id)
);

CREATE TABLE CochesPropietarios (
    coche_id INT,
    uuid CHAR(36),
    PRIMARY KEY (coche_id, uuid),
    FOREIGN KEY (coche_id) REFERENCES Coches(id),
    FOREIGN KEY (uuid) REFERENCES Usuarios(uuid)
);

select * from Usuarios;
INSERT INTO usuarios (nombre, contraseña, uuid ) values ("Pepe", "1234","jfgjhkjdskfjaskdf" );
select * from Propietarios;
select * from Coches;
select * from CochesPropietarios;
select * from Gastos;

insert into Propietarios (coche_id, usuario_id) values (1,2);
insert into Coches(marca, modelo, matricula, anio) values ("audi", "A3", "234234F", 2025);
INSERT INTO Gastos (coche_id, tipo, kilometraje, fecha, importe, descripcion) VALUES ( 1,"gasolina",123, now(), 34, "prueba");

SELECT c.id, c.marca, c.modelo, c.matricula, c.anio FROM Coches c JOIN CochesPropietarios p ON c.id = p.coche_id JOIN Usuarios u ON p.uuid = u.uuid WHERE  u.uuid = '54be4e80-2026-44ea-a450-1990bd6103b0';
SELECT * FROM Gastos WHERE coche_id = 5;
