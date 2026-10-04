import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Ejercicio10 {
    private static final String ruta = "src/";
    private static final String nombre = "nominas.dat";
    private static final String temp = "nominas_temp.dat";

    public static void main(String[] args) {

        int empleadosBaja = 0;

        try (DataInputStream leeFichero = new DataInputStream(new FileInputStream(ruta + nombre));
             DataOutputStream escribeFichero = new DataOutputStream(new FileOutputStream(ruta + temp))) {

            while (true) {
                String empNombre = leeFichero.readUTF();
                int diasBaja = leeFichero.readInt();
                double nomina = leeFichero.readDouble();

                if (diasBaja > 10) {
                    empleadosBaja++;
                } else {
                    if (diasBaja == 0) {
                        nomina = nomina * 1.05;
                    } else if (diasBaja >= 4) {
                        nomina = nomina * 0.90;
                    }

                    escribeFichero.writeUTF(empNombre);
                    escribeFichero.writeInt(diasBaja);
                    escribeFichero.writeDouble(nomina);
                }
            }

        } catch (EOFException e) {
            System.out.println("Nóminas actualizadas.");
        } catch (IOException e) {
            System.out.println("Error al actualizar el fichero: " + e.getMessage());
            return;
        }

        File original = new File(ruta + nombre);
        File tempFile = new File(ruta + temp);

        if (original.delete() && tempFile.renameTo(original)) {
            System.out.println("Fichero sustituido correctamente.");
        } else {
            System.out.println("Error al sustituir el fichero.");
            return;
        }

        System.out.println("Inicio del fichero:");

        try (DataInputStream leeFichero = new DataInputStream(new FileInputStream(ruta + nombre))) {

            while (true) {
                String empNombre = leeFichero.readUTF();
                int diasBaja = leeFichero.readInt();
                double nomina = leeFichero.readDouble();

                System.out.println(empNombre + ", " + diasBaja + ", " + nomina);
            }

        } catch (EOFException e) {
            System.out.println("Fin de fichero.");
        } catch (IOException e) {
            System.out.println("Error al leer el fichero: " + e.getMessage());
        }

        System.out.println("Empleados dados de baja: " + empleadosBaja);
    }
}
