import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio3 {
    private static final String ruta = "src/";
    private static final String nombre_fichero = "datosbeca.bin";

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        System.out.println("Nombre y apellido del becario: ");
        String nombre = teclado.nextLine();

        System.out.println("Sexo (H-M): ");
        String sexo = teclado.nextLine().toUpperCase();
        while (!sexo.equals("H") && !sexo.equals("M")) {
            System.out.println("Dato incorrecto. Sexo (H-M): ");
            sexo = teclado.nextLine().toUpperCase();
        }

        System.out.println("Edad (20-60): ");
        int edad = Integer.parseInt(teclado.nextLine());
        while (edad < 20 || edad > 60) {
            System.out.println("Dato incorrecto. Edad (20-60): ");
            edad = Integer.parseInt(teclado.nextLine());
        }

        System.out.println("Número de suspensos del curso anterior (0-4): ");
        int suspensos = Integer.parseInt(teclado.nextLine());
        while (suspensos < 0 || suspensos > 4) {
            System.out.println("Dato incorrecto. Suspensos (0-4): ");
            suspensos = Integer.parseInt(teclado.nextLine());
        }

        System.out.println("Residencia familiar (SI o NO): ");
        String residencia = teclado.nextLine().toUpperCase();
        while (!residencia.equals("SI") && !residencia.equals("NO")) {
            System.out.println("Dato incorrecto. Residencia familiar (SI o NO): ");
            residencia = teclado.nextLine().toUpperCase();
        }

        System.out.println("Ingresos anuales de la familia: ");
        double ingresos = Double.parseDouble(teclado.nextLine());

        System.out.println("Tiene beca (SI o NO): ");
        String tieneBeca = teclado.nextLine().toUpperCase();
        while (!tieneBeca.equals("SI") && !tieneBeca.equals("NO")) {
            System.out.println("Dato incorrecto. Tiene beca (SI o NO): ");
            tieneBeca = teclado.nextLine().toUpperCase();
        }

        try (DataOutputStream escribeFichero = new DataOutputStream(new FileOutputStream(ruta + nombre_fichero))) {

            escribeFichero.writeUTF(nombre);
            escribeFichero.writeUTF(sexo);
            escribeFichero.writeInt(edad);
            escribeFichero.writeInt(suspensos);
            escribeFichero.writeUTF(residencia);
            escribeFichero.writeDouble(ingresos);
            escribeFichero.writeUTF(tieneBeca);

            System.out.println("Datos guardados correctamente.");

        } catch (IOException e) {
            System.out.println("Error al escribir el fichero.");
        }
    }
}