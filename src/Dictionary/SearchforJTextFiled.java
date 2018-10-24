/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dictionary;

import java.util.ArrayList;

/**
 *
 * @author ID
 */
public class SearchforJTextFiled {
    public ArrayList searchKey(String release, ArrayList<String> keys){
        ArrayList<String> list = new ArrayList();
        int len = release.length();
        if(len != 0){
        for(int i = 0; i < keys.size(); i++)
            if(keys.get(i).length() >= len)
                if(keys.get(i).substring(0,len).equalsIgnoreCase(release))
                    list.add(keys.get(i));
        }
        return list;
    }   
}
