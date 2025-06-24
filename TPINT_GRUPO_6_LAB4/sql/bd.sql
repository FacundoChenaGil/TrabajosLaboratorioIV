CREATE DATABASE IF NOT EXISTS bancoutn;
USE bancoutn;

-- Tabla para los tipos de usuario (Administrador, Cliente)
CREATE TABLE Tipos_Usuario (
    ID_Tipo_Usuario INT NOT NULL AUTO_INCREMENT,
    Descripcion VARCHAR(50) NOT NULL,
    PRIMARY KEY (ID_Tipo_Usuario)
);

-- Tabla para los usuarios del sistema (login)
-- Se elimina la columna ID_Usuario para usar 'Usuario' como clave primaria natural.
CREATE TABLE Usuarios (
    Usuario VARCHAR(50) NOT NULL,
    Clave VARCHAR(255) NOT NULL,
    ID_Tipo_Usuario INT NOT NULL,
    Activo BOOLEAN NOT NULL DEFAULT TRUE,
    PRIMARY KEY (Usuario),
    FOREIGN KEY (ID_Tipo_Usuario) REFERENCES Tipos_Usuario(ID_Tipo_Usuario)
);

-- Tabla para los clientes del banco
-- Se elimina la columna ID_Cliente para usar 'DNI' como clave primaria natural.
CREATE TABLE Clientes (
    DNI VARCHAR(10) NOT NULL,
    CUIL VARCHAR(13) NOT NULL UNIQUE,
    Nombre VARCHAR(100) NOT NULL,
    Apellido VARCHAR(100) NOT NULL,
    Sexo CHAR(1),
    Nacionalidad VARCHAR(50),
    Fecha_Nacimiento DATE,
    Direccion VARCHAR(255),
    Localidad VARCHAR(100),
    Provincia VARCHAR(100),
    Correo_Electronico VARCHAR(100) NOT NULL UNIQUE,
    Telefono VARCHAR(20),
    Usuario VARCHAR(50) NOT NULL,
    Activo BOOLEAN NOT NULL DEFAULT TRUE,
    PRIMARY KEY (DNI),
    FOREIGN KEY (Usuario) REFERENCES Usuarios(Usuario)
);

-- Tabla para los tipos de cuenta (Caja de Ahorro, Cuenta Corriente)
CREATE TABLE Tipos_Cuenta (
    ID_Tipo_Cuenta INT NOT NULL AUTO_INCREMENT,
    Descripcion VARCHAR(100) NOT NULL,
    PRIMARY KEY (ID_Tipo_Cuenta)
);

-- Tabla de Cuentas bancarias
-- Se elimina la columna ID_Cuenta para usar 'CBU' como clave primaria natural.
CREATE TABLE Cuentas (
    DNI VARCHAR(10) NOT NULL,
    Fecha_Creacion DATETIME NOT NULL,
    ID_Tipo_Cuenta INT NOT NULL,
    Numero_Cuenta VARCHAR(20) NOT NULL UNIQUE,
    CBU VARCHAR(22) NOT NULL,
    Saldo DECIMAL(10, 2) NOT NULL,
    Activa BOOLEAN NOT NULL DEFAULT TRUE,
    PRIMARY KEY (CBU),
    FOREIGN KEY (DNI) REFERENCES Clientes(DNI),
    FOREIGN KEY (ID_Tipo_Cuenta) REFERENCES Tipos_Cuenta(ID_Tipo_Cuenta)
);

-- Tabla para los tipos de movimiento
CREATE TABLE Tipos_Movimiento (
    ID_Tipo_Movimiento INT NOT NULL AUTO_INCREMENT,
    Descripcion VARCHAR(100) NOT NULL,
    PRIMARY KEY (ID_Tipo_Movimiento)
);

-- Tabla de Movimientos de las cuentas
CREATE TABLE Movimientos (
    ID_Movimiento INT NOT NULL AUTO_INCREMENT,
    CBU VARCHAR(22) NOT NULL,
    Fecha_Movimiento DATETIME NOT NULL,
    Detalle VARCHAR(255),
    Importe DECIMAL(10, 2) NOT NULL,
    ID_Tipo_Movimiento INT NOT NULL,
    PRIMARY KEY (ID_Movimiento),
    FOREIGN KEY (CBU) REFERENCES Cuentas(CBU),
    FOREIGN KEY (ID_Tipo_Movimiento) REFERENCES Tipos_Movimiento(ID_Tipo_Movimiento)
);

-- Tabla para los estados de un préstamo
CREATE TABLE Tipos_Estado_Prestamo (
    ID_Tipo_Estado INT NOT NULL AUTO_INCREMENT,
    Descripcion VARCHAR(50) NOT NULL,
    PRIMARY KEY (ID_Tipo_Estado)
);

-- Tabla de Préstamos
CREATE TABLE Prestamos (
    ID_Prestamo INT NOT NULL AUTO_INCREMENT,
    DNI VARCHAR(10) NOT NULL,
    Fecha_Solicitud DATETIME NOT NULL,
    Importe_Pedido DECIMAL(10, 2) NOT NULL,
    Importe_a_Pagar DECIMAL(10, 2) NOT NULL,
    Plazo_Pago_Meses INT NOT NULL,
    Cantidad_Cuotas INT NOT NULL,
    Importe_Cuota DECIMAL(10, 2) NOT NULL,
    ID_Tipo_Estado INT NOT NULL,
    ID_Cuenta_Acreditacion VARCHAR(22) NOT NULL,
    PRIMARY KEY (ID_Prestamo),
    FOREIGN KEY (DNI) REFERENCES Clientes(DNI),
    FOREIGN KEY (ID_Tipo_Estado) REFERENCES Tipos_Estado_Prestamo(ID_Tipo_Estado),
    FOREIGN KEY (ID_Cuenta_Acreditacion) REFERENCES Cuentas(CBU)
);

-- Tabla para las cuotas de cada préstamo
CREATE TABLE Cuotas (
    ID_Cuota INT NOT NULL AUTO_INCREMENT,
    ID_Prestamo INT NOT NULL,
    Numero_Cuota INT NOT NULL,
    Importe DECIMAL(10, 2) NOT NULL,
    Fecha_Vencimiento DATE NOT NULL,
    Fecha_Pago DATETIME,
    PRIMARY KEY (ID_Cuota),
    FOREIGN KEY (ID_Prestamo) REFERENCES Prestamos(ID_Prestamo)
);