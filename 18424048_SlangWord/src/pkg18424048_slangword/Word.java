/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg18424048_slangword;

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
    
}
