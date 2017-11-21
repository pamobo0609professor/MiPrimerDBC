/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.Cliente;

/**
 *
 * @author laboratorio
 */
public class DatabaseManager {

    public static DatabaseManager instance;

    private static final String CONNECTION_STRING = "";

    private DatabaseManager() {

    }

    public static DatabaseManager getInstance() {
        if (null == instance) {
            instance = new DatabaseManager();
        }
        return instance;
    }

    public int insert(Cliente aInsertar) {
        int rowsAffected = 0;

        Connection connection = null;
        try {
            connection = DriverManager
                    .getConnection(CONNECTION_STRING);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        final String sql = new StringBuilder()
                .append("INSERT Cliente (id, nombre, apellido, cedula, estado_civil) ")
                .append("VALUES (?, ?, ?, ?, ?);")
                .toString();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, aInsertar.getId());
            statement.setString(2, aInsertar.getNombre());
            statement.setString(3, aInsertar.getApellido());
            statement.setString(4, aInsertar.getCedula());
            statement.setBoolean(5, aInsertar.isEstadoCivil());
            
            rowsAffected = statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (null != connection) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }

        return rowsAffected;
    }

}
