use bancoutn;

DELIMITER //

CREATE PROCEDURE SP_AprobarPrestamo(
    IN p_id_prestamo INT
)
BEGIN
    UPDATE Prestamos
    SET ID_Tipo_Estado = 2
    WHERE ID_Prestamo = p_id_prestamo;

    UPDATE Cuentas c
    JOIN Prestamos p ON c.CBU = p.CBU_Acreditacion
    SET c.Saldo = c.Saldo + p.Importe_Pedido
    WHERE p.ID_Prestamo = p_id_prestamo;
END //

DELIMITER ;