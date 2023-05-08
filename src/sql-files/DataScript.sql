CREATE DATABASE IF NOT EXISTS Hotel_Alura;
USE Hotel_Alura;

create table usuarios
(
Id int(10) unsigned NOT NULL AUTO_INCREMENT,
NOMBRE_COMPLETO varchar(255) DEFAULT NULL,
UserName varchar(255) DEFAULT NULL,
UserPwd varchar(255) DEFAULT NULL,
primary key(Id),
UNIQUE KEY UserName (UserName)
);

INSERT INTO USUARIOS(NOMBRE_COMPLETO, UserName, UserPwd) VALUES ('ADMINISTRADOR', 'admin', MD5('admin'));

create table reservas
(
Reservas_ID integer NOT NULL AUTO_INCREMENT,
FechaEntrada date DEFAULT NULL,
FechaSalida date DEFAULT NULL,
Valor float  DEFAULT 0.0,
FormaPago varchar(255) DEFAULT NULL,
LocalizaReserva varchar(255) DEFAULT NULL,
tipo_ocupacionId integer,
eliminado TINYINT DEFAULT 0,
primary key(Reservas_ID)
);

create table huesped
(
Id integer NOT NULL AUTO_INCREMENT,
Reservas_ID integer,
Nombre varchar(255) DEFAULT NULL,
Apellido varchar(255) DEFAULT NULL,
FechaNacimiento date DEFAULT NULL,
Nacionalidad varchar(255) DEFAULT NULL,
Telefono varchar(12) DEFAULT NULL,
eliminado TINYINT DEFAULT 0,
primary key(Id),
FOREIGN KEY (Reservas_ID)
      REFERENCES RESERVAS(Reservas_ID)
);


CREATE TABLE tipo_ocupacion
(
	tipo_ocupacionId integer NOT NULL AUTO_INCREMENT,
	Ocupacion varchar(255) DEFAULT NULL,
    Precio float  DEFAULT 0.0,
	primary key(tipo_ocupacionId)
);

INSERT INTO tipo_ocupacion
(Ocupacion, Precio) VALUES ('SENCILLA CAMA QUEEN', 80.00);
INSERT INTO tipo_ocupacion
(Ocupacion, Precio) VALUES ('DOBLE CAMA KING', 125.00);
INSERT INTO tipo_ocupacion
(Ocupacion, Precio) VALUES ('DOBLE DOS CAMAS QUEEN', 145.00);
INSERT INTO tipo_ocupacion
(Ocupacion, Precio) VALUES ('TRIPLE DOS CAMA QUEEN Y SOFA', 180.00);

--Este Trigger deshabilita el huesped despues de deshabilitar la reserva

DELIMITER $$

CREATE TRIGGER after_Reservas_update
AFTER UPDATE
ON reservas FOR EACH ROW
BEGIN
    IF OLD.eliminado <> new.eliminado THEN
        UPDATE huesped SET eliminado = 1 WHERE (Reservas_ID = OLD.Reservas_ID);
    END IF;
END$$

DELIMITER ;