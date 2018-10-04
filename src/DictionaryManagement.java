
import java.util.Scanner;
import java.io.*;

public class DictionaryManagement {
    private int wordCount;
    private String search = new String("--search");

    public String getSearch() {
        return search;
    }

    private String fix = new String("--fix");

    public String getFix() {
        return fix;
    }

    private String delete = new String("--delete");

    public String getDelete() {
        return delete;
    }

    Dictionary a = new Dictionary();

    public int getWordCount() {
        return wordCount;
    }

    public void insertFromCommandline() {
        Scanner keyboard = new Scanner(System.in);
        Scanner word = new Scanner(System.in);
        System.out.println("Nhap so luong tu: ");
        wordCount = keyboard.nextInt();
        for (int i = 0; i < wordCount; i++) {
            Word words = new Word();
            words.word_target = word.nextLine();
            words.word_explain = word.nextLine();
            a.dictionary.add(words);
        }
    }
}

