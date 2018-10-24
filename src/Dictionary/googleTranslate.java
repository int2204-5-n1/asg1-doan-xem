/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dictionary;
import GoogleAPI.GoogleTranslate;
import GoogleAPI.Audio;
import GoogleAPI.Language;
import java.io.IOException;
import java.io.InputStream;
import javazoom.jl.decoder.JavaLayerException;
import sun.audio.AudioDataStream;
/**
 *
 * @author ID
 */
public class googleTranslate {
    public String textTranslate (String langFrom, String langTo, String str) throws IOException{
        String s = GoogleTranslate.translate(langFrom.toLowerCase().substring(0,2), langTo.toLowerCase().substring(0,2), str);
        return s;
    }
    public void speech(String str, String lang) throws IOException, JavaLayerException{
        Audio audio = Audio.getInstance();
        InputStream input = null;
        if(lang.equals("English"))
            input  = audio.getAudio(str, Language.ENGLISH);
        else if(lang.equals("Vietnamese"))
            input  = audio.getAudio(str, Language.VIETNAMESE);   
        audio.play(input);
        
    }
}
