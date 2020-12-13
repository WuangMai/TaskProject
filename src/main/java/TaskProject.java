import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

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
        String[][] clearTab = new String[0][3];
        int tabLength = -1;


        try(Scanner scan = new Scanner(path)){
            tabLength = addNewItemToMultiArray(clearTab,scan.nextLine().split(",")).length;
            System.out.println(tabLength);

        }catch (IOException ex){
            ex.printStackTrace();
        }



    }

    public static String[][] addNewItemToMultiArray(String[][] array, String[] element) {
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
