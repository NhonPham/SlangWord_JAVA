/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pkg18424048_slangword;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author 18424048 Pham Huu Nhon
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, Exception {
        // TODO code application logic here
        Map<String, List<String>> DB = new HashMap<>();
        Main_Menu(DB);
    }

    public static void Menu() {
        System.out.println("------------------MENU------------------");
        System.out.println("0. Show list of all Slang Word");
        System.out.println("1. Find by Slang Word");
        System.out.println("2. Find by the definition");
        System.out.println("3. Show history, see the list of searched words");
        System.out.println("4. Add a new Slang Word");
        System.out.println("5. Edit a Slang Word");
        System.out.println("6. Delete a Slang Word");
        System.out.println("7. Reset the Slang Word list");
        System.out.println("8. On this day Slang Word");
        System.out.println("9. Quiz With Random Slang Word");
        System.out.println("10. Quiz With Random Definition");
        System.out.println("11. Exit");

        System.out.print("Enter the number to select: ");
    }

    public static void Main_Menu(Map<String, List<String>> DB) throws InterruptedException, IOException, Exception {
        int choice;
        DictionarySlangWord DS = new DictionarySlangWord();
        DS.ImportSlangWordListFromTXT(DB);

        do {
            Scanner scanner0 = new Scanner(System.in);
            Menu();

            choice = scanner0.nextInt();

            System.out.println("----------------------------------------");

            switch (choice) {

                case 0 -> {
                    if (!DS.getSlangWordList().isEmpty()) {
                        DS.ShowSlangWordList();
                    } else {
                        System.out.println("The list is empty! Please Import data!");
                        Thread.sleep(500);
                    }
                    break;
                }
                case 1 -> {
                    DS.FindbySlangWord(DB);
                    break;
                }
                case 2 -> {
                    DS.FindbyDefinitionWord(DB);
                    break;
                }
                case 3 -> {
                    DS.ShowHistory();
                    break;
                }
                case 5 -> {
                    DS.EditASlangWord(DB);
                    break;
                }
                case 11 -> {
                    break;
                }
                default -> {
                    System.out.println("Invalid, please choose again.");
                    break;
                }
            }

        } while (choice != 11);
    }
}
