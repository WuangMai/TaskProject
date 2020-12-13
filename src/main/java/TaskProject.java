import java.io.Console;
import java.util.Scanner;

public class TaskProject {
    public static void main(String[] args) {
        menu();
    }

    public static void menu(){
        Scanner scan = new Scanner(System.in);

        System.out.println("TASK PROJECT v0.1");
        System.out.println("add\nremove\nlist\nexit");
        System.out.print("Please select an option: ");
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

}
