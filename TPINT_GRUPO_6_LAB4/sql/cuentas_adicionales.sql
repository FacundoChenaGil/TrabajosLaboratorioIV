USE bancoutn;

-- Cuenta para Maria Gomez (Cuenta Corriente)
INSERT INTO Cuentas (DNI, Fecha_Creacion, ID_Tipo_Cuenta, Numero_Cuenta, CBU, Saldo) VALUES
('32987654', NOW(), 2, '0002-2002', '0010000000000000002002', 5000);
INSERT INTO Movimientos (CBU, Fecha_Movimiento, Detalle, Importe, ID_Tipo_Movimiento) VALUES
('0010000000000000002002', NOW(), 'Apertura de cuenta', 5000, 1);

-- Cuenta para Carlos Rodriguez (Cuenta Corriente)
INSERT INTO Cuentas (DNI, Fecha_Creacion, ID_Tipo_Cuenta, Numero_Cuenta, CBU, Saldo) VALUES
('35111222', NOW(), 2, '0002-2003', '0010000000000000002003', 15000);
INSERT INTO Movimientos (CBU, Fecha_Movimiento, Detalle, Importe, ID_Tipo_Movimiento) VALUES
('0010000000000000002003', NOW(), 'Apertura de cuenta', 15000, 1);

-- Cuenta 1 para Laura Fernandez (Caja de Ahorro)
INSERT INTO Cuentas (DNI, Fecha_Creacion, ID_Tipo_Cuenta, Numero_Cuenta, CBU, Saldo) VALUES
('28333444', NOW(), 1, '0001-1004', '0010000000000000001004', 8000);
INSERT INTO Movimientos (CBU, Fecha_Movimiento, Detalle, Importe, ID_Tipo_Movimiento) VALUES
('0010000000000000001004', NOW(), 'Apertura de cuenta', 8000, 1);

-- Cuenta 2 para Laura Fernandez (Cuenta Corriente con saldo de 20000)
INSERT INTO Cuentas (DNI, Fecha_Creacion, ID_Tipo_Cuenta, Numero_Cuenta, CBU, Saldo) VALUES
('28333444', NOW(), 2, '0002-2004', '0010000000000000002004', 20000);
INSERT INTO Movimientos (CBU, Fecha_Movimiento, Detalle, Importe, ID_Tipo_Movimiento) VALUES
('0010000000000000002004', NOW(), 'Apertura de cuenta', 20000, 1);

-- Lote adicional de cuentas --

-- Cuenta 3 para Juan Pérez (Caja de Ahorro)
INSERT INTO Cuentas (DNI, Fecha_Creacion, ID_Tipo_Cuenta, Numero_Cuenta, CBU, Saldo) VALUES
('30123456', NOW(), 1, '0001-1005', '0010000000000000001005', 7500);
INSERT INTO Movimientos (CBU, Fecha_Movimiento, Detalle, Importe, ID_Tipo_Movimiento) VALUES
('0010000000000000001005', NOW(), 'Apertura de cuenta', 7500, 1);

-- Cuenta 3 para Maria Gomez (Caja de Ahorro)
INSERT INTO Cuentas (DNI, Fecha_Creacion, ID_Tipo_Cuenta, Numero_Cuenta, CBU, Saldo) VALUES
('32987654', NOW(), 1, '0001-1006', '0010000000000000001006', 12500);
INSERT INTO Movimientos (CBU, Fecha_Movimiento, Detalle, Importe, ID_Tipo_Movimiento) VALUES
('0010000000000000001006', NOW(), 'Apertura de cuenta', 12500, 1);

-- Cuenta 3 para Carlos Rodriguez (Caja de Ahorro)
INSERT INTO Cuentas (DNI, Fecha_Creacion, ID_Tipo_Cuenta, Numero_Cuenta, CBU, Saldo) VALUES
('35111222', NOW(), 1, '0001-1007', '0010000000000000001007', 30000);
INSERT INTO Movimientos (CBU, Fecha_Movimiento, Detalle, Importe, ID_Tipo_Movimiento) VALUES
('0010000000000000001007', NOW(), 'Apertura de cuenta', 30000, 1);

-- Cuenta 3 para Laura Fernandez (Caja de Ahorro)
INSERT INTO Cuentas (DNI, Fecha_Creacion, ID_Tipo_Cuenta, Numero_Cuenta, CBU, Saldo) VALUES
('28333444', NOW(), 1, '0001-1008', '0010000000000000001008', 9900);
INSERT INTO Movimientos (CBU, Fecha_Movimiento, Detalle, Importe, ID_Tipo_Movimiento) VALUES
('0010000000000000001008', NOW(), 'Apertura de cuenta', 9900, 1);

-- Segundo lote adicional de cuentas --

-- Cuenta 4 para Juan Pérez (Cuenta Corriente)
INSERT INTO Cuentas (DNI, Fecha_Creacion, ID_Tipo_Cuenta, Numero_Cuenta, CBU, Saldo) VALUES
('30123456', NOW(), 2, '0002-2005', '0010000000000000002005', 42000);
INSERT INTO Movimientos (CBU, Fecha_Movimiento, Detalle, Importe, ID_Tipo_Movimiento) VALUES
('0010000000000000002005', NOW(), 'Apertura de cuenta', 42000, 1);

-- Cuenta 4 para Maria Gomez (Cuenta Corriente)
INSERT INTO Cuentas (DNI, Fecha_Creacion, ID_Tipo_Cuenta, Numero_Cuenta, CBU, Saldo) VALUES
('32987654', NOW(), 2, '0002-2006', '0010000000000000002006', 18000);
INSERT INTO Movimientos (CBU, Fecha_Movimiento, Detalle, Importe, ID_Tipo_Movimiento) VALUES
('0010000000000000002006', NOW(), 'Apertura de cuenta', 18000, 1);

-- Cuenta 4 para Carlos Rodriguez (Cuenta Corriente)
INSERT INTO Cuentas (DNI, Fecha_Creacion, ID_Tipo_Cuenta, Numero_Cuenta, CBU, Saldo) VALUES
('35111222', NOW(), 2, '0002-2007', '0010000000000000002007', 25000);
INSERT INTO Movimientos (CBU, Fecha_Movimiento, Detalle, Importe, ID_Tipo_Movimiento) VALUES
('0010000000000000002007', NOW(), 'Apertura de cuenta', 25000, 1);

-- Cuenta 4 para Laura Fernandez (Cuenta Corriente)
INSERT INTO Cuentas (DNI, Fecha_Creacion, ID_Tipo_Cuenta, Numero_Cuenta, CBU, Saldo) VALUES
('28333444', NOW(), 2, '0002-2008', '0010000000000000002008', 50000);
INSERT INTO Movimientos (CBU, Fecha_Movimiento, Detalle, Importe, ID_Tipo_Movimiento) VALUES
('0010000000000000002008', NOW(), 'Apertura de cuenta', 50000, 1);
