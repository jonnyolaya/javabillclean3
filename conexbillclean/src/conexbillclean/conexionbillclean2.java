package conexbillclean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class conexionbillclean2 {
    public static void main(String[] args) {
        // URL de conexión a la base de datos
        String url = "jdbc:mysql://localhost:3306/almacen";
        String user = "root";
        String password = "";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("Conexión establecida.");

            // Ejecutar una consulta
            String query = "SELECT * FROM inventario";
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(query)) {

                // Procesar los resultados
                while (rs.next()) {
                    System.out.println(rs.getString("idproducto") + " " +
                                       rs.getString("nombreproducto") + " " +
                                       rs.getInt("cantidad") + " " +
                                       rs.getInt("precio") + " " +
                                       rs.getString("fechaingreso"));
                }
            } catch (SQLException e) {
                System.out.println("Error al ejecutar la consulta: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Error al establecer la conexión: " + e.getMessage());
        }
    }
}
