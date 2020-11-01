import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class RleProgramTest {

    @Test
    public void countRunsTest() {
        byte[] flatData = {2,4,15,1,15,1,5,1,1,8,1,7};
        byte[] rleData = {15,15,6,4};
        int groups = 9; //expected result
////        assertEquals(flatData, RleProgram.encodeRle(flatData));
////        System.out.println(RleProgram.encodeRle(flatData));
        String string = "15f:64";
        System.out.println(RleProgram.toRleString(rleData));
//        for (int i : RleProgram.stringToRle(string)) {
//            System.out.print(i + " ");
//        }

//        String happyFeet = "Happy:Feet:Tiong:WOW:FUdge";
//        String[] happy = happyFeet.split(":");
//        for (String i : happy) {
//            System.out.print(i + " ");
//        }
//        System.out.println(Arrays.toString(happy));




    }
}
