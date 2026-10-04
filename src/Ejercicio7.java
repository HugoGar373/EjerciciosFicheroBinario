
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Ejercicio7 {
    private static final String ruta = "src/";
    private static final String origen = "datospersonas.dat";
    private static final String menores = "menores.dat";
    private static final String adultos = "adultos.dat";
    private static final String mayores = "mayores.dat";

    public static void main(String[] args) {

        try (DataInputStream leeFichero = new DataInputStream(new FileInputStream(ruta + origen));
             DataOutputStream escribeMenores = new DataOutputStream(new FileOutputStream(ruta + menores));
             DataOutputStream escribeAdultos = new DataOutputStream(new FileOutputStream(ruta + adultos));
             DataOutputStream escribeMayores = new DataOutputStream(new FileOutputStream(ruta + mayores))) {

            while (true) {
                String nombre = leeFichero.readUTF();
                String apellidos = leeFichero.readUTF();
                int edad = leeFichero.readInt();
                String telefono = leeFichero.readUTF();
                String email = leeFichero.readUTF();
                String ciudad = leeFichero.readUTF();
                String nacionalidad = leeFichero.readUTF();
                String profesion = leeFichero.readUTF();

                DataOutputStream destino;
                if (edad < 18) {
                    destino = escribeMenores;
                } else if (edad <= 65) {
                    destino = escribeAdultos;
                } else {
                    destino = escribeMayores;
                }

                destino.writeUTF(nombre);
                destino.writeUTF(apellidos);
                destino.writeInt(edad);
                destino.writeUTF(telefono);
                destino.writeUTF(email);
                destino.writeUTF(ciudad);
                destino.writeUTF(nacionalidad);
                destino.writeUTF(profesion);
            }

        } catch (EOFException e) {
            System.out.println("Ficheros creados correctamente.");
        } catch (IOException e) {
            System.out.println("Error al copiar los ficheros.");
        }

        // Mostrar los tres ficheros creados
        System.out.println("Inicio del fichero de menores");
        mostrarFichero(menores);

        System.out.println("Inicio del fichero de adultos");
        mostrarFichero(adultos);

        System.out.println("Inicio del fichero de mayores");
        mostrarFichero(mayores);
    }

    private static void mostrarFichero(String nombreFichero) {
        try (DataInputStream leeFichero = new DataInputStream(new FileInputStream(ruta + nombreFichero))) {

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