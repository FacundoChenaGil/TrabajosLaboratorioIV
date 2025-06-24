USE bancoutn;

-- Inserts iniciales para las tablas de tipos (catálogos)
INSERT INTO Tipos_Usuario (Descripcion) VALUES ('Administrador'), ('Cliente');
INSERT INTO Tipos_Cuenta (Descripcion) VALUES ('Caja de ahorro'), ('Cuenta corriente');
INSERT INTO Tipos_Movimiento (Descripcion) VALUES ('Alta de cuenta'), ('Alta de un préstamo'), ('Pago de préstamo'), ('Transferencia - Débito'), ('Transferencia - Crédito');
INSERT INTO Tipos_Estado_Prestamo (Descripcion) VALUES ('Pendiente'), ('Aprobado'), ('Rechazado'), ('Finalizado');

-- Creación de un usuario administrador por defecto
INSERT INTO Usuarios (Usuario, Clave, ID_Tipo_Usuario) VALUES ('admin', 'admin', 1);
INSERT INTO Usuarios (Usuario, Clave, ID_Tipo_Usuario) VALUES ('cliente', 'cliente', 2);
INSERT INTO Usuarios (Usuario, Clave, ID_Tipo_Usuario) VALUES ('cliente2', 'cliente', 2);
INSERT INTO Usuarios (Usuario, Clave, ID_Tipo_Usuario) VALUES ('cliente3', 'cliente', 2);
INSERT INTO Usuarios (Usuario, Clave, ID_Tipo_Usuario) VALUES ('cliente4', 'cliente', 2);

INSERT INTO Clientes (DNI, CUIL, Nombre, Apellido, Sexo, Nacionalidad, Fecha_Nacimiento, Direccion, Localidad, Provincia, Correo_Electronico, Telefono, Usuario) VALUES
('30123456', '20301234561', 'Juan', 'Pérez', 'M', 'Argentina', '1983-05-20', 'Av. Siempre Viva 742', 'Springfield', 'Buenos Aires', 'juan.perez@email.com', '1111111111', 'cliente'),
('32987654', '27329876545', 'Maria', 'Gomez', 'F', 'Argentina', '1987-10-15', 'Calle Falsa 123', 'San Justo', 'Buenos Aires', 'maria.gomez@email.com', '1122222222', 'cliente2'),
('35111222', '20351112228', 'Carlos', 'Rodriguez', 'M', 'Argentina', '1990-01-30', 'Rivadavia 5000', 'Morón', 'Buenos Aires', 'carlos.rodriguez@email.com', '1133333333', 'cliente3'),
('28333444', '27283334442', 'Laura', 'Fernandez', 'F', 'Argentina', '1980-12-01', 'Corrientes 348', 'CABA', 'CABA', 'laura.fernandez@email.com', '1144444444', 'cliente4');

-- Cuenta 1 para Juan Pérez (Caja de Ahorro)
INSERT INTO Cuentas (DNI, Fecha_Creacion, ID_Tipo_Cuenta, Numero_Cuenta, CBU, Saldo) VALUES
('30123456', NOW(), 1, '0001-1001', '0010000000000000001001', 10000);
INSERT INTO Movimientos (CBU, Fecha_Movimiento, Detalle, Importe, ID_Tipo_Movimiento) VALUES
('0010000000000000001001', NOW(), 'Apertura de cuenta', 10000, 1);

-- Cuenta 2 para Juan Pérez (Cuenta Corriente)
INSERT INTO Cuentas (DNI, Fecha_Creacion, ID_Tipo_Cuenta, Numero_Cuenta, CBU, Saldo) VALUES
('30123456', NOW(), 2, '0002-2001', '0010000000000000002001', 10000);
INSERT INTO Movimientos (CBU, Fecha_Movimiento, Detalle, Importe, ID_Tipo_Movimiento) VALUES
('0010000000000000002001', NOW(), 'Apertura de cuenta', 10000, 1);

-- Cuenta para Maria Gomez
INSERT INTO Cuentas (DNI, Fecha_Creacion, ID_Tipo_Cuenta, Numero_Cuenta, CBU, Saldo) VALUES
('32987654', NOW(), 1, '0001-1002', '0010000000000000001002', 10000);
INSERT INTO Movimientos (CBU, Fecha_Movimiento, Detalle, Importe, ID_Tipo_Movimiento) VALUES
('0010000000000000001002', NOW(), 'Apertura de cuenta', 10000, 1);

-- Cuenta para Carlos Rodriguez
INSERT INTO Cuentas (DNI, Fecha_Creacion, ID_Tipo_Cuenta, Numero_Cuenta, CBU, Saldo) VALUES
('35111222', NOW(), 1, '0001-1003', '0010000000000000001003', 10000);
INSERT INTO Movimientos (CBU, Fecha_Movimiento, Detalle, Importe, ID_Tipo_Movimiento) VALUES
('0010000000000000001003', NOW(), 'Apertura de cuenta', 10000, 1);


-- ##################################################################
-- 3. SIMULACIÓN DE SOLICITUD Y APROBACIÓN DE PRÉSTAMO
-- El cliente Carlos Rodriguez solicita un préstamo. El administrador lo aprueba.
-- ##################################################################

-- Paso 3.1: Carlos solicita un préstamo de $50,000 en 12 cuotas.
-- El estado inicial es 1 ('Pendiente').
INSERT INTO Prestamos (DNI, Fecha_Solicitud, Importe_Pedido, Importe_a_Pagar, Plazo_Pago_Meses, Cantidad_Cuotas, Importe_Cuota, ID_Tipo_Estado, ID_Cuenta_Acreditacion) VALUES
('35111222', '2025-06-20 10:00:00', 50000.00, 65000.00, 12, 12, 5416.67, 1, '0010000000000000001003');

-- Paso 3.2: El administrador aprueba el préstamo.
-- Se actualiza el estado a 2 ('Aprobado').
UPDATE Prestamos SET ID_Tipo_Estado = 2 WHERE ID_Prestamo = 1;

-- Paso 3.3: Se acredita el dinero en la cuenta de Carlos.
UPDATE Cuentas SET Saldo = Saldo + 50000.00 WHERE CBU = '0010000000000000001003';

-- Paso 3.4: Se registra el movimiento de alta de préstamo.
INSERT INTO Movimientos (CBU, Fecha_Movimiento, Detalle, Importe, ID_Tipo_Movimiento) VALUES
('0010000000000000001003', NOW(), 'Acreditación de Préstamo ID 1', 50000.00, 2);

-- Paso 3.5: Se generan las 12 cuotas del préstamo.
INSERT INTO Cuotas (ID_Prestamo, Numero_Cuota, Importe, Fecha_Vencimiento) VALUES
(1, 1, 5416.67, '2025-07-20'),
(1, 2, 5416.67, '2025-08-20'),
(1, 3, 5416.67, '2025-09-20'),
(1, 4, 5416.67, '2025-10-20'),
(1, 5, 5416.67, '2025-11-20'),
(1, 6, 5416.67, '2025-12-20'),
(1, 7, 5416.67, '2026-01-20'),
(1, 8, 5416.67, '2026-02-20'),
(1, 9, 5416.67, '2026-03-20'),
(1, 10, 5416.67, '2026-04-20'),
(1, 11, 5416.67, '2026-05-20'),
(1, 12, 5416.67, '2026-06-20');


-- ##################################################################
-- 4. SIMULACIÓN DE UNA TRANSFERENCIA
-- Juan Pérez le transfiere $2,500 desde su Caja de Ahorro a Maria Gomez.
-- ##################################################################

-- Paso 4.1: Se debita el dinero de la cuenta de Juan.
UPDATE Cuentas SET Saldo = Saldo - 2500.00 WHERE CBU = '0010000000000000001001';

-- Paso 4.2: Se acredita el dinero en la cuenta de Maria.
UPDATE Cuentas SET Saldo = Saldo + 2500.00 WHERE CBU = '0010000000000000001002';

-- Paso 4.3: Se generan los dos movimientos correspondientes.
-- Movimiento de DÉBITO en la cuenta de Juan (Tipo Movimiento 4)
INSERT INTO Movimientos (CBU, Fecha_Movimiento, Detalle, Importe, ID_Tipo_Movimiento) VALUES
('0010000000000000001001', NOW(), 'Transferencia enviada a CBU 0010000000000000001002', -2500.00, 4);
-- Movimiento de CRÉDITO en la cuenta de Maria (Tipo Movimiento 5)
INSERT INTO Movimientos (CBU, Fecha_Movimiento, Detalle, Importe, ID_Tipo_Movimiento) VALUES
('0010000000000000001002', NOW(), 'Transferencia recibida de CBU 0010000000000000001001', 2500.00, 5);

-- ##################################################################
-- 5. SIMULACIÓN DE PAGO DE CUOTA
-- Carlos Rodriguez paga la primera cuota de su préstamo.
-- ##################################################################

-- Paso 5.1: Se debita el dinero de la cuenta de Carlos.
UPDATE Cuentas SET Saldo = Saldo - 5416.67 WHERE CBU = '0010000000000000001003';

-- Paso 5.2: Se marca la cuota como pagada.
UPDATE Cuotas SET Fecha_Pago = NOW() WHERE ID_Prestamo = 1 AND Numero_Cuota = 1;

-- Paso 5.3: Se genera el movimiento de pago de préstamo (Tipo Movimiento 3).
INSERT INTO Movimientos (CBU, Fecha_Movimiento, Detalle, Importe, ID_Tipo_Movimiento) VALUES
('0010000000000000001003', NOW(), 'Pago de cuota 1/12 del préstamo ID 1', -5416.67, 3);