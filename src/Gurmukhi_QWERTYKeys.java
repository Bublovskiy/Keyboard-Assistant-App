/**
 * Created by BMW on 2016-05-24.
 */
public class Gurmukhi_QWERTYKeys implements Language {
    // Row 1 to 6
    // "" values  - no values to be displayed
    private String[][] Key = new String[][] {
            //row 1, keys 1-14
            {"","","","","","","","","","","","","",""},
            // row 2, keys 1-14
            {"ੑ","੧","੨","੩","੪","੫","੬","੭","੮","੯","੦","-_","=+",""},
            //row 3, keys 1-14
            {"","ੳ","ਅ","ੇ","ਰ","ਤ","ਯ","ੁ","ਿ","ੋ","ਪ","ੱ[","ੰ]","\\|"},
            //row 4, keys 1-13
            {"","ਾ","ਸ","ਦ","੍","ਗ","ਹ","ਜ","ਕ","ਲ",";","'",""},
            //row 5, keys 1-12
            {"","ਙ","ੜ","ਚ","ਵ","ਬ","ਨ","ਮ",",.","। ॥","/?",""},
            //row 6, keys 1-11
            {"","","","","","","","","","",""},
    };
    //return key array
    public String[][]  getKeys() {return Key;}
}