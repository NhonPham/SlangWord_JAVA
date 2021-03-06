/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg18424048_slangword;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

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

    public void ShowSlangWordList() {
        System.out.format("Slang Word Dictionary: \n");
        SlangWordList.forEach(sl -> {
            System.out.println("Slang Word: " + sl.getSlangWord() + " Mean: " + sl.getMean());
        });
    }

    public void FindbySlangWord(Map<String, List<String>> DB) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a slang word to find: ");
        String SlangWord = sc.nextLine();

        //save history
        try ( BufferedWriter bw = new BufferedWriter(new FileWriter("history.txt", true))) {
            if (DB.containsKey(SlangWord)) {
                DB.get(SlangWord).forEach(l -> {
                    try {
                        System.out.println("Definition: " + l);
                        bw.write(SlangWord + " : " + l + "\n");
                    } catch (IOException ex) {
                    }
                });
            } else {
                System.out.println("Definition word does not exist!");
            }

            bw.flush();
            bw.close();
        } catch (IOException e) {
        }
    }

    public void FindbyDefinitionWord(Map<String, List<String>> DB) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a Definition word to find: ");
        String DFWord = sc.nextLine();
        var wrapper = new Object() {
            int checkExist = 0;
            int exist = 0;
        };

        DB.entrySet().forEach((var i) -> {
            List<String> definition = i.getValue();

            definition.forEach(df -> {
                boolean isContain = df.toLowerCase().contains(DFWord.toLowerCase());
                if (isContain) {
                    System.out.print(i.getKey() + " : ");

                    List<String> value = i.getValue();

                    String delimiter = ", ";

                    String valueList = value.stream()
                            .map(Object::toString)
                            .collect(Collectors.joining(delimiter));

                    System.out.println(valueList);
                    wrapper.checkExist = 1;
                    wrapper.exist += 1;
                } else {
                    wrapper.checkExist = 0;
                }
            });

        });
        if (wrapper.checkExist == 0 && wrapper.exist == 0) {
            System.out.println("Slang word does not exist!");
        }
    }

    public void ShowHistory() {

        try ( BufferedReader br = new BufferedReader(new FileReader("history.txt"))) {
            String contentLine = "";
            System.out.println("List of words that have been searched: ");
            while ((contentLine = br.readLine()) != null) {
                System.out.println(contentLine);
            }
            br.close();
        } catch (IOException e) {

            System.out.println("File not found or File error!");
        }
    }

    private static void SaveFileFromMap(Map<String, List<String>> DB) throws IOException {
        String fileName = "slang.txt";

        try ( BufferedWriter br = new BufferedWriter(new FileWriter(fileName))) {
            StringBuilder sb = new StringBuilder();
            sb.append("Slag`Meaning\n");
            DB.entrySet().forEach((var i) -> {

                String key = i.getKey();
                List<String> ListMeanValid = i.getValue();
                sb.append(key).append("`").append(ListMeanValid.get(0));
                for (int j = 1; j < ListMeanValid.size(); j++) {
                    sb.append("|").append(ListMeanValid.get(j));

                }
                sb.append("\n");

            });

            br.write(sb.toString());

            br.flush();
            br.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void EditASlangWord(Map<String, List<String>> DB) throws IOException {
        System.out.println("Enter Slang Word to edit: ");
        Scanner sc = new Scanner(System.in);
        String word = "";
        String meanWord = "";
        word = sc.nextLine();
        String choice = "yes";
        String anothermean = "";

        int index = 0;
        boolean isValid = true;
        if (DB.containsKey(word)) {
            System.out.println("Enter a new Definition: ");
            meanWord = sc.nextLine();

            System.out.println("Old Definition: ");
            DB.get(word).forEach(l -> {
                System.out.print(l + ", ");
            });
            System.out.print("\nEnter the index to edit: ");
            index = sc.nextInt();

            try {
                DB.get(word).set(index, meanWord);

                throw new IndexOutOfBoundsException();
            } catch (IndexOutOfBoundsException e) {

                if (e.getMessage() != null) {
                    isValid = false;
                    System.out.println(e.getMessage());
                    System.out.println("The entered index is outside of the list of available indexes of the value, Please do option No.5 again.");
                }

            }
        } else {
            System.out.println("This Slang word does not exist!");
        }
        if (isValid) {
            do {

                System.out.println("Would you like to add another meaning to this word?yes/no: ");
                Scanner sc1 = new Scanner(System.in);
                choice = sc1.nextLine();
                if (choice.equals("yes")) {
                    System.out.println("Enter new meaning to add: ");
                    anothermean = sc1.nextLine();
                    DB.get(word).add(anothermean);
                }

            } while (choice.equals("yes"));
            System.out.println("Edited success!");
            SaveFileFromMap(DB);
        }
    }

    public void AddANewSlangWord(Map<String, List<String>> DB) throws IOException {
        System.out.println("--- Add a new slang word and its meaning ---");
        Word w = new Word();
        w.EnterNewSlangWord();
        List<String> ListMeanValid;
        ListMeanValid = new ArrayList<>();

        do {

            if (DB.containsKey(w.getSlangWord())) {
                System.out.println("Slang entered word already exists in the list, please enter a new word!");
                w.EnterNewSlangWord();
            }

        } while (DB.containsKey(w.getSlangWord()));
        w.EnterDefinitionWord(ListMeanValid);

        Collections.addAll(SlangWordList, w);
        DB.put(w.getSlangWord(), ListMeanValid);
        System.out.println("Added Success!");
        SaveFileFromMap(DB);
    }

    public void DeleteASlangWord(Map<String, List<String>> DB) throws IOException {
        System.out.println("Enter a Slang Word to Delete: ");
        Scanner sc = new Scanner(System.in);
        String word = "";
        String checkDelete = "no";
        word = sc.nextLine();
        if (DB.containsKey(word)) {

            System.out.println("Do you really want to remove this word from the list?yes/no");
            Scanner sc1 = new Scanner(System.in);
            checkDelete = sc1.nextLine();

            if (!checkDelete.equals("yes")) {
            } else {
                DB.remove(word);
                System.out.println("Deleted success!");
                SaveFileFromMap(DB);
            }
        } else {
            System.out.println("Slang Word does not exist!");
        }
    }

    public void ResetList() throws IOException {
        File dir = new File(".");

        String source = dir.getCanonicalPath() + File.separator + "SlangWordPrimitive.txt";
        String dest = dir.getCanonicalPath() + File.separator + "slang.txt";

        File fin = new File(source);
        FileInputStream fis = new FileInputStream(fin);
        BufferedWriter out;
        try ( BufferedReader in = new BufferedReader(new InputStreamReader(fis))) {
            FileWriter fstream = new FileWriter(dest);
            out = new BufferedWriter(fstream);
            String aLine = null;
            while ((aLine = in.readLine()) != null) {

                out.write(aLine);
                out.newLine();
            }
        }
        System.out.println("Reset Success!!!");
        out.close();
    }

    public void OnThisDaySlangWord(Map<String, List<String>> DB) {
        Set<String> keySet = DB.keySet();
        List<String> keyList = new ArrayList<>(keySet);

        int size = keyList.size();
        int randIdx = new Random().nextInt(size);

        String randomKey = keyList.get(randIdx);
        List<String> ListMeanValue = DB.get(randomKey);

        String delimiter = ", ";

        String valueList = ListMeanValue.stream()
                .map(Object::toString)
                .collect(Collectors.joining(delimiter));
        System.out.println("On this day Slang Word: ");
        System.out.println("Slang Word: " + randomKey + " - Definition: " + valueList);

    }
    
    public void QuizWithRandomSlangWord(Map<String, List<String>> DB) {

        Set<String> keySet = DB.keySet();
        List<String> keyList = new ArrayList<>(keySet);

        int size = keyList.size(); //7640
        int randIdx;
        String randomSlangWord = "";
        List<String> List = new ArrayList<>();
        ArrayList<String> Key = new ArrayList<>();

        for (int i = 1; i <= 4; i++) {
            randIdx = new Random().nextInt(size);//randome num
            randomSlangWord = keyList.get(randIdx);//get randomSlangWord key
            Key.add(randomSlangWord);

            List<String> ListMeanValue = DB.get(randomSlangWord);//get value with randomSlangWord key
            List.addAll(ListMeanValue);
        }

        String cd = "";
        cd = Key.get(new Random().nextInt(4));
        System.out.println("What is the definition of this " + cd + " slang?");
        List<String> temp = DB.get(cd);

        Collections.shuffle(List);

        System.out.println("A." + List.get(0));
        System.out.println("B." + List.get(1));
        System.out.println("C." + List.get(2));
        System.out.println("D." + List.get(3));

        System.out.println("Please choose the correct answer: ");
        String choice = "";
        Scanner sc = new Scanner(System.in);
        choice = sc.nextLine();
        var wrapper = new Object() {
            boolean correct = false;
        };

        switch (choice.toLowerCase()) {
            case "a" -> {
                temp.forEach(t -> {
                    if (t.compareTo(List.get(0)) == 0) {
                        wrapper.correct = true;
                    }
                });

                if (wrapper.correct) {
                    System.out.println("Correct!!!");
                } else {
                    System.out.println("Wrong answer.");

                }

            }

            case "b" -> {
                temp.forEach(t -> {
                    if (t.compareTo(List.get(1)) == 0) {
                        wrapper.correct = true;
                    }
                });
                if (wrapper.correct = true) {
                    System.out.println("Correct!!!");
                } else {
                    System.out.println("Wrong answer.");

                }

            }

            case "c" -> {
                temp.forEach(t -> {
                    if (t.compareTo(List.get(2)) == 0) {
                        wrapper.correct = true;
                    }
                });

                if (wrapper.correct) {
                    System.out.println("Correct!!!");
                } else {
                    System.out.println("Wrong answer.");

                }

            }

            case "d" -> {
                temp.forEach(t -> {
                    if (t.compareTo(List.get(3)) == 0) {
                        wrapper.correct = true;
                    }
                });

                if (wrapper.correct) {
                    System.out.println("Correct!!!");
                } else {

                    System.out.println("Wrong answer.");
                }
            }

            default -> {
                break;
            }
        }
    }
    
    public void QuizWithRandomDefinition(Map<String, List<String>> DB) {

        List<String> keyList = new ArrayList<>(DB.keySet());

        int size = keyList.size(); //7640
        int randIdx;
        String randomSlangWord = "";
        List<String> List = new ArrayList<>();
        ArrayList<String> Key = new ArrayList<>();

        for (int i = 1; i <= 4; i++) {
            randIdx = new Random().nextInt(size);//randome num
            randomSlangWord = keyList.get(randIdx);//get randomSlangWord key
            Key.add(randomSlangWord);

            List<String> ListMeanValue = DB.get(randomSlangWord);//get value with randomSlangWord key
            List.addAll(ListMeanValue);
        }
        ;
        String valueMean = List.get(new Random().nextInt(4));

        System.out.println("What is the Slang word of this " + valueMean.toUpperCase() + "?");
        Collections.shuffle(Key);

        System.out.println("A." + Key.get(0));
        System.out.println("B." + Key.get(1));
        System.out.println("C." + Key.get(2));
        System.out.println("D." + Key.get(3));

        String getkeyfromvalue = "";
        for (Map.Entry<String, List<String>> entry : DB.entrySet()) {
            if (entry.getValue().contains(valueMean)) {
//                System.out.println(valueMean + " is in " + entry.getKey());
                getkeyfromvalue = entry.getKey();
            }
        }

        System.out.println("Please choose the correct answer: ");
        String choice = "";
        Scanner sc = new Scanner(System.in);
        choice = sc.nextLine();
        var wrapper = new Object() {
            boolean correct = false;
        };

        switch (choice.toLowerCase()) {
            case "a" -> {
                if (Key.get(0).compareTo(getkeyfromvalue) == 0) {
                    wrapper.correct = true;
                }

                if (wrapper.correct) {
                    System.out.println("Correct!!!");
                } else {
                    System.out.println("Wrong answer.");
                }
            }

            case "b" -> {
                if (Key.get(1).compareTo(getkeyfromvalue) == 0) {
                    wrapper.correct = true;
                }

                if (wrapper.correct) {
                    System.out.println("Correct!!!");
                } else {
                    System.out.println("Wrong answer.");
                }
            }

            case "c" -> {
                if (Key.get(2).compareTo(getkeyfromvalue) == 0) {
                    wrapper.correct = true;
                }

                if (wrapper.correct) {
                    System.out.println("Correct!!!");
                } else {
                    System.out.println("Wrong answer.");
                }
            }

            case "d" -> {
                if (Key.get(3).compareTo(getkeyfromvalue) == 0) {
                    wrapper.correct = true;
                }

                if (wrapper.correct) {
                    System.out.println("Correct!!!");
                } else {
                    System.out.println("Wrong answer.");
                }
            }

            default -> {
                break;
            }

        }
    }
}
