/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg18424048_slangword;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

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

    public void ImportSlangWordListFromTXT(Map<String, List<String>> DB) {

        String txtFileName = "slang.txt";

        try ( BufferedReader br = new BufferedReader(new FileReader(txtFileName))) {
            String contentLine = "";
            String SplitBy = "`";
            while ((contentLine = br.readLine()) != null) {

                if (contentLine.contains(SplitBy) == true) {
                    String[] arr = contentLine.split(SplitBy);
                    Word w = new Word();
                    w.setSlangWord(arr[0]);
                    w.setMean(arr[1]);
                    Collections.addAll(SlangWordList, w);

                    for (int i = 0; i < SlangWordList.size(); i++) {
                        List<String> ListMeanValid;
                        ListMeanValid = new ArrayList<>();
                        String[] validMean = SlangWordList.get(i).getMean().split("\\|");
                        ListMeanValid.addAll(Arrays.asList(validMean));
                        DB.put(w.getSlangWord(), ListMeanValid);
                    }
                }
            }
            br.close();
        } catch (IOException e) {

            System.out.println("File not found or File error!");
        }

    }

}
