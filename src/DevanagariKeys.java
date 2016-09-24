/**
 * Created by BMW on 2016-05-24.
 */
public class DevanagariKeys implements Language {

    // Row 1 to 6
    // "" values  - no values to be displayed
    private String[][] Key = new String[][] {
            //row 1, keys 1-14
            {"","","","","","","","","","","","","",""},
            // row 2, keys 1-14
            {"ॊ","१","२","३","४","५","६","७","८","९","०","-","ृ",""},
            //row 3, keys 1-14
            {"","ौ","ै","ा","ी","ू","ब","ह","ग","द","ज","ड","़","ॉ"},
            //row 4, keys 1-13
            {"","ो","े","्","ि","ु","प","र","क","त","च","ट",""},
            //row 5, keys 1-12
            {"","ॆ","ं","म","न","व","ल","स",",ष",".।","य",""},
            //row 6, keys 1-11
            {"","","","","","","","","","",""},
    };
    //return key array
    public String[][]  getKeys() {return Key;}
}