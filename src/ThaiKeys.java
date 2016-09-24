/**
 * Created by BMW on 2016-05-24.
 */
public class ThaiKeys implements Language {
    // Row 1 to 6
    // "" values  - no values to be displayed
    private String[][] Key = new String[][] {
            //row 1, keys 1-14
            {"","","","","","","","","","","","","",""},
            // row 2, keys 1-14
            {"-%","ๅ","/","_","ภ","ถ","ุ","ึ","ค","ต","จ","ข","ช",""},
            //row 3, keys 1-14
            {"","ๆ","ไ","ำ","พ","ะ","ั","ี","ร","น","ย","บ","ล","ฃ"},
            //row 4, keys 1-13
            {"","ฟ","ห","ก","ด","เ","้","่","า","ส","ว","ง",""},
            //row 5, keys 1-12
            {"","ผ","ป","แ","อ","ิ","ื","ท","ม","ใ","ฝ",""},
            //row 6, keys 1-11
            {"","","","","","","","","","",""},
    };
    //return key array
    public String[][]  getKeys() {return Key;}
}