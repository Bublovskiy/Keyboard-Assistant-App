/**
 * Created by BMW on 2016-05-27.
 */
public class KeyCodesJNativeHook {

    //*****************************************
    // JNative hook returns different key codes than native java KeyListener
    //*****************************************
    //storing the key codes
    // Row 1 to 6
    // - 1 values  - the key code cannot be identified
    private static int[][] keyCodes = new int[][] {
            //row 1, keys 1-14
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            // row 2, keys 1-14
            {41,2,3,4,5,6,7,8,9,10,11,12,13,14},
            //row 3, keys 1-14
            {-1,16,17,18,19,20,21,22,23,24,25,26,27,43},
            //row 4, keys 1-13
            {-1,30,31,32,33,34,35,36,37,38,39,40,28},
            //row 5, keys 1-12
            {-1,44,45,46,47,48,49,50,51,52,53,-1},
            //row 6, keys 1-11
            {-1,-1,-1,-1,57,-1,-1,-1,-1,-1,-1},
    };
    //return keyCodes array
    public static int[][]  getKeyCodes() {
        return keyCodes;
    }


}
