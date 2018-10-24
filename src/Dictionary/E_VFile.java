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

//Truy xuất database từ điển Anh - Việt
public class E_VFile extends KhoTudien{
    
    public E_VFile(){
        readFile();
    }
    
    
    String path = "./FileDatabase/E_V.zip";
    public void readFile() {
        
        

        try {
            FileInputStream file = new FileInputStream(path);
            ZipInputStream zipStream = new ZipInputStream(file);
            ZipEntry entry = zipStream.getNextEntry();

            BufferedReader reader = new BufferedReader(new InputStreamReader(zipStream,"utf-8"));

            String line, word, def;
            int wordsNum = 0;
            while ((line = reader.readLine()) != null) {
                int index = line.indexOf("<html>");

                if (index != -1) {
                    word = line.substring(0, index);

                    word = word.trim();
                    keys1.add(word);


                    def = line.substring(index);


                    Word1.put(word, def);

                    wordsNum++;
                }
            }
            reader.close();

            System.out.println("Số lượng từ Anh-Việt " +wordsNum + " words");


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
            zipStream.putNextEntry(new ZipEntry("E_V.txt"));

            for (String key : keys1) {
                writer.write(key);
                String def = Word1.get(key);
                    writer.write(Word1.get(key));
                writer.newLine();
            }

            writer.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Số lượng từ Anh - Việt sau khi update: " + keys1.size());
    }
}
