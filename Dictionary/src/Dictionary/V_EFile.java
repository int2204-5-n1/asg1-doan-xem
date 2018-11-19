/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dictionary;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 *
 * @author ID
 */


//Truy xuất database từ điển Việt - Anh
public class V_EFile extends KhoTudien{
    
    public V_EFile(){
        readFile();
    }
    
    String path = "./FileDatabase/V_E.zip";
    public void readFile() {
        
        

        try {
            FileInputStream file = new FileInputStream(path);
            ZipInputStream zipStream = new ZipInputStream(file);
            ZipEntry entry = zipStream.getNextEntry();  //truy xuất trong zip

            BufferedReader reader = new BufferedReader(new InputStreamReader(zipStream,"utf-8"));   //đọc zip với utf-8

            String line, word, meaning;
            int wordsNum = 0;
            while ((line = reader.readLine()) != null) {
                int index = line.indexOf("<html>");

                if (index != -1) {
                    word = line.substring(0, index);

                    word = word.trim();
                    keys2.add(word);

                    meaning = line.substring(index);

                    Word2.put(word, meaning);

                    wordsNum++;
                }
            }
            reader.close();

            System.out.println("Số lượng từ Việt - Anh " +wordsNum + " words");


        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();

        }

    }
    
    
    //Viết lại file txt sau khi thêm, xóa hoặc sửa.
    public void reloadFile(){

        try {
            FileOutputStream file = new FileOutputStream(path);
            ZipOutputStream zipStream = new ZipOutputStream(file);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(zipStream,"utf-8"));
            zipStream.putNextEntry(new ZipEntry("V_E.txt"));    //đặt lại vào tệp V_E.txt

            for (String key : keys2) {
                writer.write(key);
                writer.write(Word2.get(key));
                writer.newLine();
            }

            writer.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Số lượng từ Anh - Viet sau khi update: " + keys2.size());
    }
}
