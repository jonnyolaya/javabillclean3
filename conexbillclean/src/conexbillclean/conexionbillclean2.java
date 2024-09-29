package conexbillclean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class conexionbillclean2 {
    private static final String URL = "jdbc:mysql://localhost:3306/almacen";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println("Conexión establecida.");

            // Agregar un registro
            agregarRegistro(conn, 2, "Producto B", 200, 100.0, "2024-09-29");

            // Eliminar un registro
            eliminarRegistro(conn, 1);

            // Mostrar registros
            mostrarRegistros(conn);
        } catch (SQLException e) {
        }
    }

    public static void agregarRegistro(Connection conn, int id, String nombre, int cantidad, double precio, String fechaIngreso) {
        String sqlInsert = "INSERT INTO inventario (idproducto, nombreproducto, cantidad, precio, fecha_ingreso) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sqlInsert)) {
            pstmt.setInt(1, id);
            pstmt.setString(2, nombre);
            pstmt.setInt(3, cantidad);
            pstmt.setDouble(4, precio);
            pstmt.setDate(5, java.sql.Date.valueOf(fechaIngreso));

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Un nuevo registro fue insertado exitosamente!");
            }
        } catch (SQLException e) {
        }
    }

    public static void eliminarRegistro(Connection conn, int id) {
        String sqlDelete = "DELETE FROM inventario WHERE idproducto = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sqlDelete)) {
            pstmt.setInt(1, id);

            int rowsDeleted = pstmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("El registro fue eliminado exitosamente!");
            }
        } catch (SQLException e) {
        }
    }

    public static void mostrarRegistros(Connection conn) {
        String sqlSelect = "SELECT * FROM inventario";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sqlSelect)) {

            while (rs.next()) {
                System.out.println(rs.getString("idproducto") + " " + rs.getString("nombreproducto") + " " + rs.getString("cantidad") + " " + rs.getString("precio") + " " + rs.getString("fecha_ingreso"));
            }
        } catch (SQLException e) {
        }
    }
}
