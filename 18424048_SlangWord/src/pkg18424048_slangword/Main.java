/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pkg18424048_slangword;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author 18424048 Pham Huu Nhon
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Map<String, List<String>> DB = new HashMap<>();
    }

    public static void Menu() {
        System.out.println("------------------MENU------------------");
        System.out.println("0. Show list of all Slang Word");
        System.out.println("1. Find by slang word ");
        System.out.println("2. Find by the definition");
        System.out.println("3. Show history, see the list of searched words");
        System.out.println("4. Add a new Slang Word");
        System.out.println("5. Eddit a Slang Word");
        System.out.println("6. Delete a Slang Word");
        System.out.println("7. Reset the Slang Word list");
        System.out.println("8. On this day slang word");
        System.out.println("9. Quiz With Random Slang Word");
        System.out.println("10. Quiz With Random Definition");
        System.out.println("11. Exit");

        System.out.print("Enter the number to select: ");
    }
}
