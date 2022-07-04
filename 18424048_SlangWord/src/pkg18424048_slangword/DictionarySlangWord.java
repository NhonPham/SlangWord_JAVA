/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg18424048_slangword;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 18424048 Pham Huu Nhon
 */
public class DictionarySlangWord {

    private List<Word> SlangWordList = new ArrayList<>();

    public DictionarySlangWord() {
        SlangWordList = new ArrayList<>();
    }

    public DictionarySlangWord(List<Word> SlangWordList) {
        this.SlangWordList = SlangWordList;
    }

    public List<Word> getSlangWordList() {
        return SlangWordList;
    }

    public void setSlangWordList(List<Word> SlangWordList) {
        this.SlangWordList = SlangWordList;
    }

}
