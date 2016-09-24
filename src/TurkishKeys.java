/**
 * Created by BMW on 2016-05-24.
 */
public class TurkishKeys  implements Language {

    // Row 1 to 6
    // "" values  - no values to be displayed
    private String[][] Keys = new String[][] {
            //row 1, keys 1-14
            {"","","","","","","","","","","","","",""},
            // row 2, keys 1-14
            {"W","1","2","3","4","5","6","7","8","9","0","X","Q",""},
            //row 3, keys 1-14
            {"","F","G","Ğ","I","O","D","R","N","H","P",";$","><","=+"},
            //row 4, keys 1-13
            {"","U","İ","E","A","Ü","T","K","M","L","Y","Ş",""},
            //row 5, keys 1-12
            {"","J","Ö","V","C","Ç","Z","S","B",".?",",-",""},
            //row 6, keys 1-11
            {"","","","","","","","","","",""},
    };
    //return key array
    public String[][]  getKeys() {return Keys;}
}
