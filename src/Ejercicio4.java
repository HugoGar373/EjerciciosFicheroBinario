import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio4 {
    private static final String ruta = "src/";
    private static final String nombre_fichero = "datosbeca.bin";

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        System.out.println("Número de becarios: ");
        int numero = Integer.parseInt(teclado.nextLine());

        try (DataOutputStream escribeFichero = new DataOutputStream(new FileOutputStream(ruta + nombre_fichero, true))) {

            int i = 1;

            while (i <= numero) {
                System.out.println("Nombre y apellido del becario " + i + ": ");
                String nombre = teclado.nextLine();

                System.out.println("Sexo (H-M) del becario " + i + ": ");
                String sexo = teclado.nextLine().toUpperCase();
                while (!sexo.equals("H") && !sexo.equals("M")) {
                    System.out.println("Dato incorrecto. Sexo (H-M): ");
                    sexo = teclado.nextLine().toUpperCase();
                }

                System.out.println("Edad (20-60) del becario " + i + ": ");
                int edad = Integer.parseInt(teclado.nextLine());
                while (edad < 20 || edad > 60) {
                    System.out.println("Dato incorrecto. Edad (20-60): ");
                    edad = Integer.parseInt(teclado.nextLine());
                }

                System.out.println("Suspensos del curso anterior (0-4) del becario " + i + ": ");
                int suspensos = Integer.parseInt(teclado.nextLine());
                while (suspensos < 0 || suspensos > 4) {
                    System.out.println("Dato incorrecto. Suspensos (0-4): ");
                    suspensos = Integer.parseInt(teclado.nextLine());
                }

                System.out.println("Residencia familiar (SI o NO) del becario " + i + ": ");
                String residencia = teclado.nextLine().toUpperCase();
                while (!residencia.equals("SI") && !residencia.equals("NO")) {
                    System.out.println("Dato incorrecto. Residencia familiar (SI o NO): ");
                    residencia = teclado.nextLine().toUpperCase();
                }

                System.out.println("Ingresos anuales de la familia del becario " + i + ": ");
                double ingresos = Double.parseDouble(teclado.nextLine());

                System.out.println("Tiene beca (SI o NO) el becario " + i + ": ");
                String tieneBeca = teclado.nextLine().toUpperCase();
                while (!tieneBeca.equals("SI") && !tieneBeca.equals("NO")) {
                    System.out.println("Dato incorrecto. Tiene beca (SI o NO): ");
                    tieneBeca = teclado.nextLine().toUpperCase();
                }

                escribeFichero.writeUTF(nombre);
                escribeFichero.writeUTF(sexo);
                escribeFichero.writeInt(edad);
                escribeFichero.writeInt(suspensos);
                escribeFichero.writeUTF(residencia);
                escribeFichero.writeDouble(ingresos);
                escribeFichero.writeUTF(tieneBeca);

                i++;
            }

        } catch (IOException e) {
            System.out.println("Error al escribir el fichero.");
        }

        System.out.println("Inicio del fichero");

        try (DataInputStream leeFichero = new DataInputStream(new FileInputStream(ruta + nombre_fichero))) {

            while (true) {
                String nombre = leeFichero.readUTF();
                String sexo = leeFichero.readUTF();
                int edad = leeFichero.readInt();
                int suspensos = leeFichero.readInt();
                String residencia = leeFichero.readUTF();
                double ingresos = leeFichero.readDouble();
                String tieneBeca = leeFichero.readUTF();

                System.out.println(nombre + ", " + sexo + ", " + edad + ", " + suspensos + ", " + residencia + ", " + ingresos + ", " + tieneBeca);
            }

        } catch (EOFException e) {
            System.out.println("Fin de fichero.");
        } catch (IOException e) {
            System.out.println("Error al leer el fichero.");
        }
    }
}