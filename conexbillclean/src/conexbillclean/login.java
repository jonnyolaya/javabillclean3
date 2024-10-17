package conexbillclean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class login {
    public static void main(String[] args) throws SQLException {
        String Url = "jdbc:mysql://localhost:3306/almacen";
        String User = "root";
        String Password = "";

        try (Connection connection = DriverManager.getConnection(Url, User, Password);
             Statement statement = connection.createStatement();
             Scanner scanner = new Scanner(System.in)) {

            System.out.print("Ingrese su nombre : ");
            String inputnomempleado = scanner.nextLine();

            String query = "SELECT nomempleado, passempleado FROM empleados";
            ResultSet resultSet = statement.executeQuery(query);

            boolean userFound = false;
            while (resultSet.next()) {
                String nomempleado = resultSet.getString("nomempleado");
                String passempleado = resultSet.getString("passempleado");

                if (inputnomempleado.equals(nomempleado)) {
                    userFound = true;
                    System.out.print("Ingrese su contraseña: ");
                    String inputpassempleado = scanner.nextLine();

                    if (inputpassempleado.equals(passempleado)) {
                        System.out.println("¡Ingreso exitoso!");
                    } else {
                        System.out.println("Contraseña incorrecta.");
                    }
                    break;
                }
            }

            if (!userFound) {
                System.out.println("Usuario no encontrado.");
            }

        } catch (SQLException e) {
        }
    }
}
