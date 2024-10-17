
package ejecrcicoandroid;
import java.util.Scanner;
public class EjecrcicoAndroid {
 public static void main(String[] args) {
        String nombreusuario;
        String nombreapellido;

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("\nIngresa el nombre de usuario: ");
            nombreusuario = scanner.nextLine();

            System.out.print("\nIngresa el apellido del usuario: ");
            nombreapellido= scanner.nextLine();
            
            System.out.print("\nsu nombre de usuario "  +nombreusuario);
            System.out.print("\nsu apellido es "   +nombreapellido);
        }catch (Exception e) {
            System.out.println("Error al capturar los datos: " + e.getMessage());
        }
    }
}
