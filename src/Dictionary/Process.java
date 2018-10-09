/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dictionary;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author ID
 */
public class Process extends KhoTudien{

    
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

