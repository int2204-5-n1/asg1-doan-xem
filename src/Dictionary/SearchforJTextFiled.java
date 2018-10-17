/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dictionary;

import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author ID
 */
public class SearchforJTextFiled {
    public ArrayList searchKey(String s, ArrayList keys){
        ArrayList list = new ArrayList();
        int len = s.length();
        if(len != 0){
        for(int i = 0; i < keys.size(); i++)
            if(keys.get(i).toString().length() >= len)
                if(keys.get(i).toString().substring(0,len).equalsIgnoreCase(s))
                    list.add(keys.get(i));
        }
        return list;
    }   
}
