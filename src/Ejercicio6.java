import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio6 {
    private static final String ruta = "src/";
    private static final String nombre_fichero = "datospersonas.dat";

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        System.out.println("Número de personas: ");
        int numero = Integer.parseInt(teclado.nextLine());

        try (DataOutputStream escribeFichero = new DataOutputStream(new FileOutputStream(ruta + nombre_fichero))) {

            int i = 1;

            while (i <= numero) {
                System.out.println("Nombre persona " + i + ": ");
                String nombre = teclado.nextLine();

                System.out.println("Apellidos persona " + i + ": ");
                String apellidos = teclado.nextLine();

                System.out.println("Edad persona " + i + ": ");
                int edad = Integer.parseInt(teclado.nextLine());

                System.out.println("Teléfono persona " + i + ": ");
                String telefono = teclado.nextLine();

                System.out.println("Email persona " + i + ": ");
                String email = teclado.nextLine();

                System.out.println("Ciudad de residencia persona " + i + ": ");
                String ciudad = teclado.nextLine();

                System.out.println("Nacionalidad persona " + i + ": ");
                String nacionalidad = teclado.nextLine();

                System.out.println("Profesión persona " + i + ": ");
                String profesion = teclado.nextLine();

                escribeFichero.writeUTF(nombre);
                escribeFichero.writeUTF(apellidos);
                escribeFichero.writeInt(edad);
                escribeFichero.writeUTF(telefono);
                escribeFichero.writeUTF(email);
                escribeFichero.writeUTF(ciudad);
                escribeFichero.writeUTF(nacionalidad);
                escribeFichero.writeUTF(profesion);

                i++;
            }

        } catch (IOException e) {
            System.out.println("Error al escribir el fichero.");
        }

        System.out.println("Inicio del fichero");

        try (DataInputStream leeFichero = new DataInputStream(new FileInputStream(ruta + nombre_fichero))) {

            while (true) {
                String nombre = leeFichero.readUTF();
                String apellidos = leeFichero.readUTF();
                int edad = leeFichero.readInt();
                String telefono = leeFichero.readUTF();
                String email = leeFichero.readUTF();
                String ciudad = leeFichero.readUTF();
                String nacionalidad = leeFichero.readUTF();
                String profesion = leeFichero.readUTF();

                System.out.println(nombre + ", " + apellidos + ", " + edad + ", " + telefono + ", " + email + ", " + ciudad + ", " + nacionalidad + ", " + profesion);
            }

        } catch (EOFException e) {
            System.out.println("Fin de fichero.");
        } catch (IOException e) {
            System.out.println("Error al leer el fichero.");
        }
    }
}