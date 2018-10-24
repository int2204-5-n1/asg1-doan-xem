/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dictionary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.*;
/**
 *
 * @author ID
 */
public class Process{

    
    //XỬ lÍ CÁC CHỨC NĂNG TÌM, THÊM, SỬA, XÓA
    
    

    //TÌM TỪ

    /**
     *
     * @param w
     * @param keys1
     * @return
     */
    public int search(String w, ArrayList<String> keys1){
        if(w.compareTo(keys1.get(0))<= 0)
            return 0;
        int head = 0, rear = keys1.size()-1;
        while(head < rear){
            int between = (head+rear)/2;
            if(keys1.get(between).compareTo(w) < 0)
                head=between;
            else
                rear=between;
        }
        return rear;    //trả lại vị trí vào list Word 
    }
    
    //ĐỊNH NGHĨA 

    /**
     *
     * @param w
     * @param newWord1
     * @param newDefine
     * @param Word1
     * @param keys1
     */
    public void modify(String w,String newWord1, String newDefine, HashMap<String,String> Word1, ArrayList<String> keys1){
        newDefine = newDefine.replace("\n", "");
        newDefine = newDefine.replace("&lt;", "");
        newDefine = newDefine.replace("&gt;", "");
        if(newWord1 == null)
            Word1.replace(w, newDefine);
        else if(newDefine == null){
            String def = Word1.get(w);
            remove(w,Word1,keys1);
            add(w, def,Word1,keys1); 
        }
        else
            remove(w,Word1,keys1);
            add(newWord1, newDefine,Word1,keys1);
        
    }
    //THÊM TỪ

    /**
     *
     * @param w
     * @param define
     * @param Word1
     * @param keys1
     */
    public void add(String w, String define, HashMap<String,String>Word1, ArrayList<String>keys1){
        w=w.toLowerCase();
        keys1.add(w);
        Collections.sort(keys1);
        String str = "<html>" + define;
        Word1.put(w, str); 
    }
    //XÓA TỪ

    /**
     *
     * @param w
     * @param Word1
     * @param keys1
     */
    public void remove(String w, HashMap<String,String>Word1, ArrayList<String>keys1){
        int index = keys1.lastIndexOf(w);
        if(index != -1){
            keys1.remove(w);
            Word1.remove(w);
        }
    }
}

