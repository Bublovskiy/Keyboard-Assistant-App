/**
 * Created by BMW on 2016-05-24.
 */
public class GreekKeys implements Language  {

    // Row 1 to 6
    // "" values  - no values to be displayed
    private String[][] greekKeys = new String[][] {
            //row 1, keys 1-14
            {"","","","","","","","","","","","","",""},
            // row 2, keys 1-14
            {"`~","1","2","3","4","5","6","7","8","9","0","-_","=+",""},
            //row 3, keys 1-14
            {"",";","Σ","Ε","Ρ","Τ","Υ","Θ","Ι","Ο","Π","[{","]}","\\|"},
            //row 4, keys 1-13
            {"","Α","Σ","Δ","Φ","Γ","Η","Ξ","Κ","Λ","΄¨","'",""},
            //row 5, keys 1-12
            {"","Ζ","Χ","Ψ","Ω","Β","Ν","Μ",",<",".>","/?",""},
            //row 6, keys 1-11
            {"","","","","","","","","","",""},
    };
    //return key array
    public String[][]  getKeys() {return greekKeys;}
}