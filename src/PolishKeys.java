/**
 * Created by BMW on 2016-05-24.
 */
public class PolishKeys implements  Language{
    // Row 1 to 6
    // "" values  - no values to be displayed
    private String[][] Key = new String[][] {
            //row 1, keys 1-14
            {"","","","","","","","","","","","","",""},
            // row 2, keys 1-14
            {"<>","1","2","3","4","5","6","7","8","9","0","Ż","][",""},
            //row 3, keys 1-14
            {"","Q","W","E","R","T","Z","U","I","O","P","Óź","()",";$"},
            //row 4, keys 1-13
            {"","A","S","D","F","G","H","J","K","L","Ł","Ą",""},
            //row 5, keys 1-12
            {"","Y","X","C","V","B","N","M",".ś",",ń","-ć",""},
            //row 6, keys 1-11
            {"","","","","","","","","","",""},
    };
    //return key array
    public String[][]  getKeys() {return Key;}
}