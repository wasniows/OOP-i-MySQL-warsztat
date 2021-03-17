package pl.coderslab.entity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class MainTest {

    public static void main(String[] args) {

        create(100.99);


    }

    public static int getInt(String message) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        while (!scanner.hasNextInt()) {
            System.out.println("Waiting for a number");
            scanner.next();
        }
        return scanner.nextInt();
    }

    public static String getString(String message) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        return scanner.nextLine();
    }
    public static void create(double limit) {

        int n = getInt("Podaj ilość produktów w koszyku: ");
        try (FileWriter fileWriter = new FileWriter("koszyk.txt", false)) {
            for (int i = 1; i <= n; i++) {
                fileWriter.append(String.valueOf(i)).append(". ").append(getString("Podaj nazwę produktu: ")).append(" ");
                fileWriter.append(String.valueOf(getDouble("Podaj cenę produktu: "))).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        File file = new File("koszyk.txt");
        int j = 0;
        try (Scanner scanner = new Scanner(file);
             FileWriter fileWriter = new FileWriter("Produkty_drogie.txt", false)) {
            while (scanner.hasNextLine()) {
                String[] elements = scanner.nextLine().split(" ");
                if (Double.parseDouble(elements[2]) >= limit) {
                    j += 1;
                    fileWriter.append(String.valueOf(j)).append(" ").append(elements[1]).append(" ").append(elements[2]).append("\n");
                }
            }
            System.out.println("Saved");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static double getDouble(String message) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        while (!scanner.hasNextDouble()) {
            System.out.println("Waiting for a number");
            scanner.next();
        }
        return scanner.nextDouble();
    }
}
