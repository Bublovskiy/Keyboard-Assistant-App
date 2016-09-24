/**
 * Created by BMW on 2016-05-20.
 */


class RussianKeys implements Language {

    // Row 1 to 6
    // "" values  - no values to be displayed
    private String[][] Keys = new String[][] {
            //row 1, keys 1-14
            {"","","","","","","","","","","","","",""},
            // row 2, keys 1-14
            {"][","1","2","3","4","5:","6,","7.","8","9","0","-_","=+",""},
            //row 3, keys 1-14
            {"","Й" ,"Ц","У","К","Е","Н","Г","Ш","Щ","З","Х","Ъ","Ё"},
            //row 4, keys 1-13
            {"","Ф","Ы","В","А","П","Р","О","Л","Д","Ж","Э",""},
            //row 5, keys 1-12
            {"","Я","Ч","С","М","И","Т","Ь","Б","Ю","/?",""},
            //row 6, keys 1-11
            {"","","","","","","","","","",""},
    };

    //return key array
    public String[][]  getKeys() {
        return Keys;
    }
}