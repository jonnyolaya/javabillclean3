
package conexbillclean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.DriverManager;

public class sesionBillclean extends conexionbillclean2  {
    public static void main(String[] args) {
        int idcc;
        String nomempleado;
        String apellempleado;
        String passempleado;
        
        
            try (Scanner scanner = new Scanner(System.in)) {
                    
          
           
            System.out.print("\n\nIngresa el apellido del empleado : ");
            apellempleado= scanner.nextLine();
            
            System.out.print("\n\nIngresa el nombre del empleado: ");
            nomempleado = scanner.nextLine();

            System.out.print("\nIngresa la contraseña del empleado sin caracteres especiales ni numericos : ");
            passempleado= scanner.nextLine();
            
            System.out.print("\n\nIngresa el numero de identificacion del empleado : ");
            idcc = scanner.nextInt();
            
            insertarEmpleado(idcc, nomempleado, apellempleado, passempleado);
        } catch (Exception e) {
            System.out.println("Error al capturar los datos: " + e.getMessage());
    }
}

    private static void insertarEmpleado(int idcc, String nomempleado, String apellempleado, String passempleado) {
       String sql = "INSERT INTO empleados(idcc,nomempleado,apellempleado,passempleado) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setInt(1, idcc);
            statement.setString(2, nomempleado);
            statement.setString(3, apellempleado);
            statement.setString(4, passempleado);
            
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Registro agregado correctamente.");
            }
        } catch (SQLException ex) {
            System.out.println("Error al agregar el registro: " + ex.getMessage());
        }
    }

    private static Connection getConnection() throws SQLException {
      String url = "jdbc:mysql://localhost:3306/almacen";
        String user = "root";
        String password = "";
        return DriverManager.getConnection(url, user, password);
    }
    }
