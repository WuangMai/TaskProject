import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;


public class TaskProject {
    public static void main(String[] args) {
        menu();
    }

    public static void menu(){
        Scanner scan = new Scanner(System.in);

        System.out.println(ConsoleColors.PURPLE  + "TASK PROJECT v0.1" + ConsoleColors.RESET);
        System.out.println("add\nremove\nlist\nexit");
        System.out.print(ConsoleColors.BLUE + "Please select an option: ");
        System.out.println(ConsoleColors.RESET);
        String option = scan.nextLine();

            switch (option){
                case "add":
                    System.out.println("Selected 'add'");
                    add(taskArray());
                    menu();
                    break;

                case "remove":
                    System.out.println("Selected 'remove'");
                    break;

                case "list":
                    System.out.println("Selected 'list'");
                    list(taskArray());
                    menu();
                    break;

                case "exit":
                    System.out.println("'Exit' selected. Bye bye...");
                    break;

                    default:
                        System.out.println("Wrong command");
                        System.out.print("\n\n");
                        menu();
            }
    }

    public static String[][] taskArray() {
        //sprawdza ile jest linii w pliku i tworzy tablice z taką ilością

        Path path = Paths.get("tasks.csv");
        long numLines = 0;
        try {
            for (String line : Files.readAllLines(path)) {
                numLines += 1;
            }
        } catch (IOException ex) {
            System.out.println("Critical error!");
        }

        String[][] tab = new String[(int) numLines][3];

        // dodaje dane z pliku do multitablicy
        File file = new File("tasks.csv");
        try {
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()){
                for (int i = 0; i < tab.length; i++) {
                    String[] split = scan.nextLine().split(",");
                    for (int j = 0; j < split.length; j++) {
                        tab[i][j] = split[j];
                    }
                }
            }
        }catch (FileNotFoundException ex){
            ex.printStackTrace();
        }
        return tab;

    }

    public static void list(String[][] array){
            StringBuilder sb = new StringBuilder();
            sb.append("Nr\t Description \t \t \t Date \t is Important?\n");
            for (int i = 0;i < array.length; i++){
                sb.append(i).append(" : ");
                for (int j = 0; j< array[i].length; j++){
                    sb.append(array[i][j]);
                }
                sb.append("\n");
            }
            System.out.println(sb);

        }

    public static void add(String [][] array){
        Scanner scan = new Scanner(System.in);
        File file = new File("tasks.csv");
//        String[] newTask = new String[0];
        StringBuilder sb = new StringBuilder();

//        Pobranie danych od użytkownika i zbudowanie stringa
        System.out.println("Please add task decription");
        sb.append("\n").append(scan.nextLine()).append(", ");

        System.out.println("Please add task due date");
        sb.append(scan.nextLine()).append(", ");

        System.out.println("Is your task important?");
        sb.append(scan.nextLine());

//        Zapis stringa do pliku
        try {
            FileUtils.writeStringToFile(file, sb.toString(),"ISO-8859-1",true);
        }catch (IOException ex){
            ex.printStackTrace();
        }
        
    }

    public static String[][] addNewItemToMultiArray(String[][] array, String[] element) {
        // coś w tym nie działa tak jak trzeba
        String[][] newArray = new String[array.length + 1][4];
        for (int i = 0; i < newArray.length - 1; i++){
            for (int j = 0; j < newArray[i].length; j++){
                newArray[i][j] = array[i][j];
            }
        }
        newArray[newArray.length - 1] = element;
        return newArray;
    }





}
