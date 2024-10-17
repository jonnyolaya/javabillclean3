
package conexbillclean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.DriverManager;

public class eliminardatos extends conexionbillclean2 {

    public static void main(String[] args) {
        int idproducto;
        try ( // Crear un objeto Scanner
                Scanner scanner = new Scanner(System.in)) {
            System.out.print("Ingresa el ID del producto a eliminar: ");
            idproducto = scanner.nextInt();
        }

        // Llamar al método para eliminar el producto de la base de datos
        eliminarProducto(idproducto);
    }

    private static void eliminarProducto(int idproducto) {
        String sql = "DELETE FROM inventario WHERE idproducto = ?";

        try (Connection conn = getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
           

            statement.setInt(1, idproducto);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Producto eliminado correctamente.");
            } else {
                System.out.println("No se encontró un producto con ese ID.");
            }
        } catch (SQLException ex) {
            System.out.println("Error al eliminar el producto.");
        }
    }

    private static Connection getConnection()throws SQLException {
       String url = "jdbc:mysql://localhost:3306/almacen";
    String user = "root";
    String password = "";
    return DriverManager.getConnection(url, user, password);
    }
    }


