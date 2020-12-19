import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.DateTimeException;
import java.time.LocalDate;
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
        System.out.print(ConsoleColors.RESET);
        String option = scan.nextLine();

            switch (option){
                case "add":
                    System.out.println("Selected 'add'");
                    add();
                    menu();
                    break;

                case "remove":
                    System.out.println("Selected 'remove'");
                    remove(taskArray());
                    menu();
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
                        System.out.println("Wrong command\n");
                        menu();
            }
    }


    public static String[][] taskArray() {
        //sprawdza ile jest linii w pliku i tworzy tablice z taką ilością

        Path path = Paths.get("tasks.csv");
        long numLines = 0;
        try {
            for (String ignored : Files.readAllLines(path)) {
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
//        wyświetla tablicę
            StringBuilder sb = new StringBuilder();
            sb.append("Nr\t Description \t \t \t Date \t is Important?\n");
            for (int i = 0;i < array.length; i++){
                sb.append(i).append(" : ");
                for (int j = 0; j< array[i].length; j++){
                    sb.append(array[i][j]).append(" ");
                }
                sb.append("\n");
            }
            System.out.println(sb);

        }


    public static void add(){
        Scanner scan = new Scanner(System.in);
        File file = new File("tasks.csv");
        StringBuilder sb = new StringBuilder();

//        Pobranie danych od użytkownika i zbudowanie stringa + walidacja przecinka

        String taskName = ",";
        while(taskName.indexOf(',') != -1){
            System.out.println("Please add task decription. Please do NOT use ','(comma)");
            taskName = scan.nextLine();
        }
        sb.append("\n").append(taskName).append(",");


//        Pobiera i dodaje datę
        sb.append(verifiedDate()).append(",");

//        Pobiera i dodaje ważność zadania
        System.out.println("Is your task important? True / False");
        while(!scan.hasNextBoolean()){
            System.out.println("Must choose true or false");
            scan.next();
        }
            sb.append(scan.nextBoolean());


//        Zapis stringa do pliku
        try {
            FileUtils.writeStringToFile(file, sb.toString(),"ISO-8859-1",true);
        }catch (IOException ex){
            ex.printStackTrace();
        }

    }


    public static String verifiedDate(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Please add task due date in format YYYY-MM-DD");


        String input;
        while(true) {
                input = scan.nextLine();

            try {
                LocalDate.parse(input);

                if (LocalDate.now().isBefore(LocalDate.parse(input))){
                    break;
                }else throw new DateTimeException("Wrong date");
            } catch (DateTimeException ex) {
                System.out.println("Wrong date or date format. Please use YYYY-MM-DD format");
            }
        }
        return input;
    }


    public static void remove(String [][] array){
        Scanner scan = new Scanner(System.in);
        File file = new File("tasks.csv");
        StringBuilder sb = new StringBuilder();

//        Pobranie poprawnej wartości od użytkownika i usunięcie z tablicy

        int numToDel;
        System.out.println("Please select number to remove");

        while(!scan.hasNextInt()) {
            System.out.println("Select proper task NUMBER");
            scan.next();
        }

            numToDel = scan.nextInt();

            while (numToDel < 0 || numToDel >= array.length){
                System.out.println("Select proper task number");
                numToDel = scan.nextInt();
            }

        array = ArrayUtils.remove(array, numToDel);

//        zapis nowej tablicy do pliku
        for (int i = 0; i < array.length; i++){
            for(int j = 0; j < array[i].length; j++){
                sb.append(array[i][j]).append(",");
            }
            sb.deleteCharAt(sb.lastIndexOf(","));

            if (i != array.length-1) {sb.append("\n");}

            try {
                FileUtils.writeStringToFile(file, sb.toString(),"ISO-8859-1",false);
            }catch (IOException ex){
                ex.printStackTrace();
            }
        }
    }
}
