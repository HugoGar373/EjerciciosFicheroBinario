import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;

public class Ejercicio5 {
    private static final String ruta = "src/";
    private static final String nombre_fichero = "datosbeca.bin";

    private static final double base = 1500;
    private static final double media = 12000;
    private static final double complemento = 500;
    private static final double gratificacion_edad = 200;
    private static final double sin_suspensos = 500;
    private static final double un_suspenso = 200;
    private static final double alquiler = 1000;

    public static void main(String[] args) {

        System.out.println("Becas concedidas:");

        try (DataInputStream leeFichero = new DataInputStream(new FileInputStream(ruta + nombre_fichero))) {

            while (true) {
                String nombre = leeFichero.readUTF();
                String sexo = leeFichero.readUTF();
                int edad = leeFichero.readInt();
                int suspensos = leeFichero.readInt();
                String residencia = leeFichero.readUTF();
                double ingresos = leeFichero.readDouble();
                String tieneBeca = leeFichero.readUTF();

                if (tieneBeca.equals("SI") && suspensos < 2) {

                    double cuantia = base;

                    if (ingresos <= media) {
                        cuantia = cuantia + complemento;
                    }

                    if (edad < 23) {
                        cuantia = cuantia + gratificacion_edad;
                    }

                    if (suspensos == 0) {
                        cuantia = cuantia + sin_suspensos;
                    } else {
                        cuantia = cuantia + un_suspenso;
                    }

                    if (residencia.equals("NO")) {
                        cuantia = cuantia + alquiler;
                    }

                    System.out.println(nombre + ", " + cuantia + " €");
                }
            }

        } catch (EOFException e) {
            System.out.println("Fin de fichero.");
        } catch (IOException e) {
            System.out.println("Error al leer el fichero.");
        }
    }
}
