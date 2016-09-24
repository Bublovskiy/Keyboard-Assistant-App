/**
 * Created by BMW on 2016-05-20.
 */
class KeyCodes {

    //storing the key codes
    // Row 1 to 6
    // - 1 values  - the key code cannot be identified
    private static int[][] keyCodes = new int[][] {
            //row 1, keys 1-14
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            // row 2, keys 1-14
            {192,49,50,51,52,53,54,55,56,57,48,45,61,8},
            //row 3, keys 1-14
            {-1,81 ,87, 69,82, 84,89, 85,73, 79,80, 91,93, 92},
              //row 4, keys 1-13
            {-1,65,83,68,70,71,72,74,75,76,59,222,10},
            //row 5, keys 1-12
            {-1, 90,88,67 ,86,66 ,78,77 ,44,46 ,47,-1},
            //row 6, keys 1-11
            {-1,-1,-1,-1,32,-1,-1,-1,-1,-1,-1},
    };
    //return keyCodes array
    public static int[][]  getKeyCodes() {
        return keyCodes;
    }

}
