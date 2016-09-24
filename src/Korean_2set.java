/**
 * Created by BMW on 2016-05-27.
 */
public class Korean_2set implements Language {
    // Row 1 to 6
    // "" values  - no values to be displayed
    private String[][] Key = new String[][] {
            //row 1, keys 1-14
            {"","","","","","","","","","","","","",""},
            // row 2, keys 1-14
            {"`","1","2","3","4","5","6","7","8","9","0","-_","=+",""},
            //row 3, keys 1-14
            {"","ㅂ","ㅈ","ㄷ","ㄱ","ㅅ","ㅛ","ㅕ","ㅑ","ㅐ","ㅔ","[{","]}","\\|"},
            //row 4, keys 1-13
            {"","ㅁ","ㄴ","ㅇ","ㄹ","ㅎ","ㅗ","ㅓ","ㅏ","ㅣ",";:","'",""},
            //row 5, keys 1-12
            {"","ㅋ","ㅌ","ㅊ","ㅍ","ㅠ","ㅜ","ㅡ",",<",".>","/?",""},
            //row 6, keys 1-11
            {"","","","","","","","","","",""},
    };

    //return key array
    public String[][]  getKeys() {return Key;}
}
