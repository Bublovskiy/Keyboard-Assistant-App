/**
 * Created by BMW on 2016-05-21.
 */
public class EmptyKeys implements Language {

    // Row 1 to 6
    // "" values  - no values to be displayed
    private String[][] Key = new String[][] {
            //row 1, keys 1-14
            {"","","","","","","","","","","","","",""},
            // row 2, keys 1-14
            {"","","","","","","","","","","","","",""},
            //row 3, keys 1-14
            {"","","","","","","","","","","","","",""},
            //row 4, keys 1-13
            {"","","","","","","","","","","","",""},
            //row 5, keys 1-12
            {"","","","","","","","","","","",""},
            //row 6, keys 1-11
            {"","","","","","","","","","",""},
    };
    //return key array
    public String[][]  getKeys() {return Key;}
}
