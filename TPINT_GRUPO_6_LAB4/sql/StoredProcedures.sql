use bancoutn;

DELIMITER //

-- Elimina el procedimiento si ya existe para evitar errores en la recreación
DROP PROCEDURE IF EXISTS SP_AprobarPrestamo//

CREATE PROCEDURE SP_AprobarPrestamo(
    IN p_id_prestamo INT
)
BEGIN
    DECLARE v_cbu VARCHAR(22);
    DECLARE v_importe_pedido DECIMAL(10, 2);
    DECLARE v_detalle VARCHAR(255);

    -- Iniciar transacción
    START TRANSACTION;

    -- Obtener datos del préstamo
    SELECT CBU_Acreditacion, Importe_Pedido
    INTO v_cbu, v_importe_pedido
    FROM Prestamos
    WHERE ID_Prestamo = p_id_prestamo;

    -- 1. Actualizar estado del préstamo a 'Aprobado' (ID 2)
    UPDATE Prestamos
    SET ID_Tipo_Estado = 2
    WHERE ID_Prestamo = p_id_prestamo;

    -- 2. Acreditar el saldo en la cuenta
    UPDATE Cuentas
    SET Saldo = Saldo + v_importe_pedido
    WHERE CBU = v_cbu;

    -- 3. Registrar el movimiento de acreditación (Tipo Movimiento ID 2)
    SET v_detalle = CONCAT('Acreditación de Préstamo ID ', p_id_prestamo);
    INSERT INTO Movimientos (CBU, Fecha_Movimiento, Detalle, Importe, ID_Tipo_Movimiento)
    VALUES (v_cbu, NOW(), v_detalle, v_importe_pedido, 2);

    -- Confirmar transacción
    COMMIT;

END //

DELIMITER ;