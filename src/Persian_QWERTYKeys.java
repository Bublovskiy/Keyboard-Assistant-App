/**
 * Created by BMW on 2016-05-24.
 */
public class Persian_QWERTYKeys implements  Language {
    // Row 1 to 6
    // "" values  - no values to be displayed
    private String[][] Key = new String[][] {
            //row 1, keys 1-14
            {"","","","","","","","","","","","","",""},
            // row 2, keys 1-14
            {"‍‍‍`","۱","۲","۳","۴","۵","۶","۷","۸","۹","۰","-ـ","=+",""},
            //row 3, keys 1-14
            {"","ق","و","ع","ر","ت","ی","ہ","ئ","ظ","پ","]}","[{","\\|"},
            //row 4, keys 1-13
            {"","ا","س","د","ف","گ","ه","ج","ک","ل","؛:","",""},
            //row 5, keys 1-12
            {"","ز","خ","ث","ء","ب","ن","م","،<",".>","/؟",""},
            //row 6, keys 1-11
            {"","","","","","","","","","",""},
    };
    //return key array
    public String[][]  getKeys() {return Key;}
}