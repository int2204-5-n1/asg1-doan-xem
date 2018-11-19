/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//Source: youtube - freetts: kenvin16;
package Dictionary;
import com.sun.speech.freetts.*;
/**
 *
 * @author ID
 */
public class Sound {
    
    private static final String VOICE = "kevin16";
    public void speak(String s){
        Voice voice;
        VoiceManager vm = VoiceManager.getInstance();
        voice = vm.getVoice(VOICE);
        voice.allocate();
        try{
            voice.speak(s);
        }catch(Exception e){  
        }
    }  
}
