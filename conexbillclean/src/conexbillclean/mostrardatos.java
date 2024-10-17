package conexbillclean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.DriverManager;

public class mostrardatos extends conexionbillclean2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("¿Deseas ver todos los datos o buscar por ID? (todos/id): ");
        String opcion = scanner.nextLine();

        if (opcion.equalsIgnoreCase("todos")) {
            verTodosLosDatos();
        } else if (opcion.equalsIgnoreCase("id")) {
            System.out.print("Ingresa el ID del producto: ");
            int idproducto = scanner.nextInt();
            verDatosPorId(idproducto);
        } else {
            System.out.println("Opción no válida.");
        }
    }

    private static void verTodosLosDatos() {
        String sql = "SELECT * FROM inventario";

        try (Connection conn = getConnection();
             PreparedStatement statement = conn.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int idproducto = resultSet.getInt("idproducto");
                String nombreproducto = resultSet.getString("nombreproducto");
                int cantidad = resultSet.getInt("cantidad");
                double precio = resultSet.getDouble("precio");
                String fechaingreso = resultSet.getString("fechaingreso");

                System.out.println("ID: " + idproducto + ", Nombre: " + nombreproducto + ", Cantidad: " + cantidad + ", Precio: " + precio + ", Fecha de Ingreso: " + fechaingreso);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void verDatosPorId(int idproducto) {
        String sql = "SELECT * FROM inventario WHERE idproducto = ?";

        try (Connection conn = getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setInt(1, idproducto);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String nombreproducto = resultSet.getString("nombreproducto");
                    int cantidad = resultSet.getInt("cantidad");
                    double precio = resultSet.getDouble("precio");
                    String fechaingreso = resultSet.getString("fechaingreso");

                    System.out.println("ID: " + idproducto + ", Nombre: " + nombreproducto + ", Cantidad: " + cantidad + ", Precio: " + precio + ", Fecha de Ingreso: " + fechaingreso);
                } else {
                    System.out.println("No se encontró un producto con el ID: " + idproducto);
                }
            }
        } catch (SQLException e) {
        }
    }

    private static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/almacen";
        String user = "root";
        String password = "";
        return DriverManager.getConnection(url, user, password);
    }
}
