use bancoutn;

DELIMITER $$

CREATE TRIGGER trg_DesactivarCuentasCliente
AFTER UPDATE ON Clientes
FOR EACH ROW
BEGIN
    IF OLD.Activo = TRUE AND NEW.Activo = FALSE THEN
        UPDATE Cuentas
        SET Activa = FALSE
        WHERE DNI = NEW.DNI;
    END IF;
END$$

DELIMITER ;