/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg18424048_slangword;

import java.util.List;
import java.util.Scanner;

/**
 *
 * @author 18424048 Pham Huu Nhon
 */
public class Word {

    private String SlangWord;
    private String Mean;

    public Word() {
        super();
    }

    public Word(String SlangWord, String Mean) {
        this.SlangWord = SlangWord;
        this.Mean = Mean;
    }

    public String getSlangWord() {
        return SlangWord;
    }

    public void setSlangWord(String SlangWord) {
        this.SlangWord = SlangWord;
    }

    public String getMean() {
        return Mean;
    }

    public void setMean(String Mean) {
        this.Mean = Mean;
    }
    
    public void EnterNewSlangWord() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a new Slang word: ");
        SlangWord = sc.nextLine();
    }

    public void EnterDefinitionWord(List<String> ListMeanValid) {

        Scanner sc = new Scanner(System.in);
        String choose ="yes";
        do {
            System.out.print("Enter mean of this Slang word: ");
            Mean = sc.nextLine();

            ListMeanValid.add(Mean);
            System.out.println("Would you like to enter more meaning of the slang word? yes/no: ");
            choose = sc.nextLine();
        } while (choose.equals("yes"));
    }
}
