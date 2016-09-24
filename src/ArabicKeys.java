/**
 * Created by BMW on 2016-05-24.
 */
public class ArabicKeys implements Language {

    // Row 1 to 6
    // "" values  - no values to be displayed
    private String[][] Keys = new String[][] {
            //row 1, keys 1-14
            {"","","","","","","","","","","","","",""},
            // row 2, keys 1-14
            {"ـ","١","٢","٣","٤","٥","٦","٧","٨","٩)","٠(","-ـ","=+",""},
            //row 3, keys 1-14
            {"","ض","ص","ث","ق","ف","غ","ع","ه","]خ","[ح","}ج","{ة","\\|"},
            //row 4, keys 1-13
            {"","ش","س","ي","ب","ل","ا","ت","٫ن","٬م",":ك","؛",""},
            //row 5, keys 1-12
            {"","ظ","ط","ذ","د","ز","ر","و","،>",".<","/؟",""},
            //row 6, keys 1-11
            {"","","","","","","","","","",""},
    };
    //return key array
    public String[][]  getKeys() {
        return Keys;
    }
}