/**
 * Created by BMW on 2016-05-25.
 */
public class SystemKeysOnly implements Language {

    // Row 1 to 6
    // "" values  - no values to be displayed
    private String[][] Keys = new String[][] {
            //row 1, keys 1-14
            {"","","","","","","","","","","","","",""},
            // row 2, keys 1-14
            {"","1","2","3","4","5","6","7","8","9","0","-","=","delete"},
            //row 3, keys 1-14
            {"tab","" ,"", "","","","","","","","","[","]", "\\"},
            //row 4, keys 1-13
            {"caps lock","","","","","","","","","",";","'","return"},
            //row 5, keys 1-12
            {"shift","","","","","","","",",",".","/","shift"},
            //row 6, keys 1-11
            {"fn","ctrl","opt","cmd","","cmd","opt","","","",""},
    };
    //return key array
    public String[][] getKeys() {
        return Keys;
    }
}
