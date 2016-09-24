/**
 * Created by BMW on 2016-05-24.
 */
public class SerbianKeys implements Language {

    // Row 1 to 6
    // "" values  - no values to be displayed
    private String[][] Keys = new String[][] {
            //row 1, keys 1-14
            {"","","","","","","","","","","","","",""},
            // row 2, keys 1-14
            {"<>","1","2","3","4","5","6","7","8","9","0","‘?","+*",""},
            //row 3, keys 1-14
            {"","Љ","Њ","Е","Р","Т","З","У","И","О","П","Ш","Ђ","Ж"},
            //row 4, keys 1-13
            {"","А","С","Д","Ф","Г","Х","Ј","К","Л","Ч","Ћ",""},
            //row 5, keys 1-12
            {"","Ѕ","Џ","Ц","В","Б","Н","М",",;",".:","-_",""},
            //row 6, keys 1-11
            {"","","","","","","","","","",""},
    };
    //return key array
    public String[][]  getKeys() {return Keys;}
}