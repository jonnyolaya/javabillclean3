package conexbillclean;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.DriverManager;

public class ingresodatos extends conexionbillclean2 {

    public static void main(String[] args) {
        int idproducto;
        String nombreproducto;
        int cantidad;
        int precio;

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("\nIngresa el nombre del producto: ");
            nombreproducto = scanner.nextLine();

            System.out.print("Ingresa el id del producto: ");
            idproducto = scanner.nextInt();

            System.out.print("\nIngresa la cantidad del producto: ");
            cantidad = scanner.nextInt();

            System.out.print("\nIngresa el precio del producto: ");
            precio = scanner.nextInt();

            // Llamada a insertarProducto dentro del bloque try
            insertarProducto(idproducto, nombreproducto, cantidad, precio);
        } catch (Exception e) {
            System.out.println("Error al capturar los datos: " + e.getMessage());
        }
    }

    private static void insertarProducto(int idproducto, String nombreproducto, int cantidad, int precio) {
        String sql = "INSERT INTO inventario(idproducto, nombreproducto, cantidad, precio) VALUES (?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setInt(1, idproducto);
            statement.setString(2, nombreproducto);
            statement.setInt(3, cantidad);
            statement.setInt(4, precio);
            
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Producto agregado correctamente.");
            }
        } catch (SQLException ex) {
            System.out.println("Error al agregar el producto: " + ex.getMessage());
        }
    }

    private static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/almacen";
        String user = "root";
        String password = "";
        return DriverManager.getConnection(url, user, password);
    }
}
