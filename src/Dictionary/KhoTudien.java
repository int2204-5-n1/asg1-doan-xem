package Dictionary;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collection;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import java.io.*;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ID
 */
public class KhoTudien {
    protected HashMap<String,String> Word = new HashMap<>();
    protected ArrayList<String> keys = new ArrayList();
    String path = "FileDatabase/E_V.zip";
    public KhoTudien(){
        readFile();
    }
    public HashMap getWord(){
        return Word;
    }
    public ArrayList getKeys(){
        return keys;
    }
    public void readFile() {
        FileInputStream file = null;
        ZipInputStream zipStream = null;
        ZipEntry entry = null;
        BufferedReader reader = null;

        try {
            file = new FileInputStream(path);
            zipStream = new ZipInputStream(file);
            entry = zipStream.getNextEntry();

            reader = new BufferedReader(new InputStreamReader(zipStream));

            String line, word, def;
            int wordsNum = 0;
            while ((line = reader.readLine()) != null) {
                //System.out.printf("%s\n----------------------\n", line);
                int index = line.indexOf("<html>");
                int index2 = line.indexOf("<ul>");

                if (index2 != -1 && index > index2) {
                    index = index2;
                }

                if (index != -1) {
                    word = line.substring(0, index);

                    word = word.trim();
                    keys.add(word);

                    //word = word.toLowerCase();
                    def = line.substring(index);
                    //def = "<html>" + def + "</html>";

                    Word.put(word, def);

                    wordsNum++;
                }
            }
            reader.close();

            System.out.println(": " + wordsNum + " words");


        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();

        }

        //Collections.sort(keys);

    }
    
    
    //Viết lại file txt sau khi thêm, xóa hoặc sửa.
    public void reloadFile(){
        FileOutputStream file = null;
        ZipOutputStream zipStream = null;
        BufferedWriter writer = null;

        try {
            file = new FileOutputStream("path");
            zipStream = new ZipOutputStream(file);
            writer = new BufferedWriter(new OutputStreamWriter(zipStream));
            zipStream.putNextEntry(new ZipEntry(path.replace("./data/", "").replace("zip", "txt")));

            for (String key : keys) {
                writer.write(key);
                String def = Word.get(key);
                if (def != null) {
                    writer.write(Word.get(key));
                }

                writer.newLine();
            }

            writer.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    //XỬ lÍ CÁC CHỨC NĂNG TÌM, THÊM, SỬA, XÓA
    
    

    //TÌM TỪ
    public int search(String w, ArrayList<String> keys){
        if(keys.get(0).compareTo(w)>=0) 
            return 0;
        int head = 0, rear = keys.size()-1;
        while(head < rear){
            int between = (head+rear)/2;
            if(keys.get(between).compareTo(w) < 0)
                head=between;
            else
                rear=between;
        }
        return rear;
    }
    
    //ĐỊNH NGHĨA 
    public void modify(String w, String newWord, String newDefine){
        if(newWord == null)
            Word.replace(w, newDefine);
        else if(newDefine == null){
            String def = Word.get(w);
            remove(w);
            add(w, def); 
        }
        else
            remove(w);
            add(newWord, newDefine);
        
    }
    //THÊM TỪ
    public void add(String w, String define){
        w=w.toLowerCase();
        int position = search(w, keys);
        keys.add(position,w);
        Word.put(w, define); 
    }
    //XÓA TỪ
    public void remove(String w){
        int index = keys.lastIndexOf(w);
        if(index != -1){
            keys.remove(w);
            Word.remove(w);
        }
    }
}
