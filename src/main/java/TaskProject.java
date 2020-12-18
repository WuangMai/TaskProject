import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

public class TaskProject {
    public static void main(String[] args) {
        readTaskFile();
        menu();
    }

    public static void menu(){
        Scanner scan = new Scanner(System.in);

        System.out.println(ConsoleColors.PURPLE  + "TASK PROJECT v0.1" + ConsoleColors.RESET);
        System.out.println("add\nremove\nlist\nexit");
        System.out.print(ConsoleColors.BLUE + "Please select an option: ");
        String option = scan.nextLine();

            switch (option){
                case "add":
                    System.out.println("Selected 'add'");
                    break;

                case "remove":
                    System.out.println("Selected 'remove'");
                    break;

                case "list":
                    System.out.println("Selected 'list'");
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

    public static void readTaskFile(){
        Path path = Paths.get("tasks.csv");
        //sprawdza ile jest linii w pliku i tworzy tablice z taką ilością
        long numLines = 0;
        try (Stream<String> stream = Files.lines(path, StandardCharsets.UTF_8)){
            numLines = stream.count();
        }catch (IOException ex){
            System.out.println("Critical error!");
        }

        String[][] clearTab = new String[(int)numLines][3];

        for (int i = 0; i < clearTab.length; i++){
            for (int j = 0; j < clearTab[i].length; j++){
                clearTab[i][j]=
            }
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
