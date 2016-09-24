/**
 * Created by BMW on 2016-05-24.
 */
public class Hebrew_QWERTYKeys implements  Language {
    // Row 1 to 6
    // "" values  - no values to be displayed
    private String[][] Key = new String[][] {
            //row 1, keys 1-14
            {"","","","","","","","","","","","","",""},
            // row 2, keys 1-14
            {"ּ","1","2","3","4","5","6","7","8","9","0","–","=+",""},
            //row 3, keys 1-14
            {"","ק","ש","ע","ר","ת","ט","ו","י","ו","פ","’","ײַ","ֿֿ-" },
            //row 4, keys 1-13
            {"","א","ס","ד","פ","ג","ה","ח","כ","ל",";:","’׳",""},
            //row 5, keys 1-12
            {"","ז","כ","צ","ו","ב","נ","מ",",>",".<","/?",""},
            //row 6, keys 1-11
            {"","","","","","","","","","",""},
    };
    //return key array
    public String[][]  getKeys() {return Key;}
}