import java.util.Scanner;

public class RleProgram {

    public static final boolean DO_A_SCAN = true;
    public static final boolean JUST_RETURN_STRING = false;




    public static void main(String[] args) {
        Scanner rleScanner = new Scanner(System.in);

        byte[] imageData = null;
        boolean exit = false;

        String userInput = "0";
        String userInput2;

        //begin sequence
        printInitSequence();

        while (!exit) {

            printMenu();
            System.out.print("Select a Menu Option: ");
            userInput2 = userInputScan(rleScanner);


            if (userInput2.equals("0")) {
                exit = true;
            } else if (userInput2.equals("1")) {
                System.out.print("Enter name of file to load: ");
                imageData = ConsoleGfx.loadFile(userInputScan(rleScanner));
                System.out.println(""); //print blank line
            } else if (userInput2.equals("2")) {
                imageData = ConsoleGfx.testImage;
                System.out.println("Test image data loaded.\n");
            } else if (userInput2.equals("3")) { //Read RLE String
                System.out.print("Enter an RLE string to be decoded: ");
                imageData = decodeRle(stringToRle(userInputScan(rleScanner)));
                System.out.println(""); //print blank line
            } else if (userInput2.equals("4")) { //Read RLE Hex String
                System.out.print("Enter the hex string holding RLE data: ");
                imageData = decodeRle(stringToData(userInputScan(rleScanner)));
                System.out.println(""); //print blank line
            } else if (userInput2.equals("5")) { //Read Data Hex String
                System.out.print("Enter the hex string holding flat data: ");
                imageData = stringToData(userInputScan(rleScanner));
                System.out.println(""); //print blank line
            } else if (userInput2.equals("6")) { //Display Image
                System.out.println("Displaying image...");
                ConsoleGfx.displayImage(imageData); //display image stored in imageData array
                System.out.println(""); //print blank line
            } else if (userInput2.equals("7")) { //Display RLE String
                System.out.println("RLE representation: " + toRleString(encodeRle(imageData)) + "\n");
            } else if (userInput2.equals("8")) { //Display Hex RLE Data
                System.out.println("RLE hex values: " + toHexString(encodeRle(imageData)) + "\n");
            } else if (userInput2.equals("9")) { //Display Hex Flat Data
                System.out.println("Flat hex values: " + toHexString(imageData) + "\n");
            }
        }
    }

    //Methods simplifying the Main

    public static void printMenu() { //print rleProgram menu
        System.out.println(
                "RLE Menu\n" +
                        "--------\n" +
                        "0. Exit\n" +
                        "1. Load File\n" +
                        "2. Load Test Image\n" +
                        "3. Read RLE String\n" +
                        "4. Read RLE Hex String\n" +
                        "5. Read Data Hex String\n" +
                        "6. Display Image\n" +
                        "7. Display RLE String\n" +
                        "8. Display Hex RLE Data\n" +
                        "9. Display Hex Flat Data\n");
    }

    public static void printInitSequence() { //prints welcome message & spectrum image
        // 1. Display welcome message
        System.out.println("Welcome to the RLE image encoder!\n");

        // 2. Display color test with the message
        System.out.println("Displaying Spectrum Image:");
        ConsoleGfx.displayImage(ConsoleGfx.testRainbow);
        System.out.println("");
    }

    public static String userInputScan(Scanner rleScanner) { //request user input
        String userInput;
        userInput = rleScanner.next();
        return userInput;
    }


    //Methods for translating Hex and Decimal Data

    public static String decimalToHexNumDecode(byte deciChar) {  //convert decimal numbers to hex values
        String hexValue = "ERROR"; //if input is awry print error for debugging
        if (deciChar >= 0 && deciChar <= 9) {
            hexValue = String.valueOf(deciChar);
        } else if (deciChar == 10) {
            hexValue = "a";
        } else if (deciChar == 11) {
            hexValue = "b";
        } else if (deciChar == 12) {
            hexValue = "c";
        } else if (deciChar == 13) {
            hexValue = "d";
        } else if (deciChar == 14) {
            hexValue = "e";
        } else if (deciChar == 15) {
            hexValue = "f";
        }
        return hexValue;
    }

    public static byte hexCharDecode(char digit) { //decode hexchar inputs and return decimal byte data
        byte deciValue;
        String stringValue = String.valueOf(digit);
        if (stringValue.equals("1")) {
            deciValue = 1;
        } else if (stringValue.equals("2")) {
            deciValue = 2;
        } else if (stringValue.equals("3")) {
            deciValue = 3;
        } else if (stringValue.equals("4")) {
            deciValue = 4;
        } else if (stringValue.equals("5")) {
            deciValue = 5;
        } else if (stringValue.equals("6")) {
            deciValue = 6;
        } else if (stringValue.equals("7")) {
            deciValue = 7;
        } else if (stringValue.equals("8")) {
            deciValue = 8;
        } else if (stringValue.equals("9")) {
            deciValue = 9;
        } else if (stringValue.equals("A") || stringValue.equals("a")) {
            deciValue = 10;
        } else if (stringValue.equals("B") || stringValue.equals("b")) {
            deciValue = 11;
        } else if (stringValue.equals("C") || stringValue.equals("c")) {
            deciValue = 12;
        } else if (stringValue.equals("D") || stringValue.equals("d")) {
            deciValue = 13;
        } else if (stringValue.equals("E") || stringValue.equals("e")) {
            deciValue = 14;
        } else if (stringValue.equals("F") || stringValue.equals("f")) {
            deciValue = 15;
        } else {
            deciValue = 0;
        }
        return deciValue;
    }

    public static byte hexCharStringDecode(String stringValue) { //decode hexstring inputs and return decimal byte data
        byte deciValue;
        if (stringValue.equals("1")) {
            deciValue = 1;
        } else if (stringValue.equals("2")) {
            deciValue = 2;
        } else if (stringValue.equals("3")) {
            deciValue = 3;
        } else if (stringValue.equals("4")) {
            deciValue = 4;
        } else if (stringValue.equals("5")) {
            deciValue = 5;
        } else if (stringValue.equals("6")) {
            deciValue = 6;
        } else if (stringValue.equals("7")) {
            deciValue = 7;
        } else if (stringValue.equals("8")) {
            deciValue = 8;
        } else if (stringValue.equals("9")) {
            deciValue = 9;
        } else if (stringValue.equals("10")) {
            deciValue = 10;
        } else if (stringValue.equals("11")) {
            deciValue = 11;
        } else if (stringValue.equals("12")) {
            deciValue = 12;
        } else if (stringValue.equals("13")) {
            deciValue = 13;
        } else if (stringValue.equals("14")) {
            deciValue = 14;
        } else if (stringValue.equals("15")) {
            deciValue = 15;
        } else if (stringValue.equals("A") || stringValue.equals("a")) {
            deciValue = 10;
        } else if (stringValue.equals("B") || stringValue.equals("b")) {
            deciValue = 11;
        } else if (stringValue.equals("C") || stringValue.equals("c")) {
            deciValue = 12;
        } else if (stringValue.equals("D") || stringValue.equals("d")) {
            deciValue = 13;
        } else if (stringValue.equals("E") || stringValue.equals("e")) {
            deciValue = 14;
        } else if (stringValue.equals("F") || stringValue.equals("f")) {
            deciValue = 15;
        } else {
            deciValue = 0;
        }
        return deciValue;
    }


    //Project methods for RLE Imaging data

    public static String toHexString(byte[] data) {
        String hexWithoutDelimiters = "";
        for (int i = 0; i < data.length; i++) {
            hexWithoutDelimiters += decimalToHexNumDecode(data[i]);
        }
        return hexWithoutDelimiters;
    }

    public static int countRuns(byte[] flatData) {
        int currentValue = flatData[0]; //current value = to first index
        int runCount = 1;
        int inARow = 1;
        for (int i = 1; i < flatData.length; i++) {
            if (inARow > 15) {
                runCount++;
                inARow = 0;
            } else if (currentValue != flatData[i]) {
                runCount++;
                currentValue = flatData[i];
                inARow = 0;
            }
            inARow++;
        }
        return runCount;
    }

    public static byte[] encodeRle(byte[] flatData) {
        byte[] rleData = new byte[countRuns(flatData) * 2]; //how long it the array
        int newIndex = 1;
        boolean lastTerm = false;
        for (int i = 0; i < rleData.length; i+=2) { //for assigning to rleData[]
            byte runLength = 1;
            for (int j = newIndex; j < flatData.length; j++) { //for reading the flatData[]
                if (flatData[j] == flatData[j - 1] && runLength < 15) {
                    runLength++;
                } else {
                    break;
                }
            }
            rleData[i] = runLength; //how many of that number in a row appeared?
            if (!lastTerm) {
                rleData[i + 1] = flatData[newIndex - 1]; //set rleData index = to the first checked index
            } else {
                rleData[i + 1] = flatData[newIndex]; //set relData index = to the second checked index (for last term of array only)
            }
            newIndex += runLength; //increment newIndex by the amount of terms checked previously(runLength)
            if (newIndex >= flatData.length) {
                newIndex = flatData.length - 1;
                lastTerm = true;
            }
        }
        return rleData;
    }

    public static int getDecodedLength(byte[] rleData) { //returns length of output flat data for decodeRle()
        int num = 0;
        for (int i = 0; i < rleData.length; i+=2) {
            num += rleData[i];
        }
        return num;
    }

    public static byte[] decodeRle(byte[] rleData) { //decodes rle data and returns flat data
        byte[] flatData = new byte[getDecodedLength(rleData)];
        int currentIndex = 0;
        for (int i=0; i < rleData.length; i+=2) {
            for (int j=currentIndex; j < rleData[i]+currentIndex; j++) {
                flatData[j] = rleData[i+1]; //input data into the flat data array
            }
            currentIndex += rleData[i]; //set to proper index for next for loop input
        }
        return flatData;
    }

    public static byte[] stringToData(String dataString) { //translates hex rleStrings into decimal arrays
        byte[] rleData = new byte[dataString.length()];
        for (int i=0; i < rleData.length; i++) {
            rleData[i] = hexCharDecode(dataString.charAt(i)); //inputs each individual character in string into rleData array
        }
        return rleData;
    }

    public static String toRleString(byte[] rleData) { //translates rleData array into hexString with Delimiters in between terms
        String[] rleHexData = new String[rleData.length/2];
        String rleHex = "";
        int j = 0;
        for (int i=0; i < rleHexData.length; i++) { //make hexadecimal formatted array that can be directly translated into a string
            rleHexData[i] = String.valueOf(rleData[j]) + decimalToHexNumDecode(rleData[j+1]);
            j+=2;
        }
        for (int i=0; i < rleHexData.length; i++) { //translate hexadecimal array into string adding delimiters as ":"
            if (i == rleHexData.length-1)
                rleHex += rleHexData[i];
            else
                rleHex += rleHexData[i] + ":";
        }
        return rleHex;
    }

    public static byte[] stringToRle(String rleString) { //translate string data with delimiters into decimal arrays
        byte[] rleData = new byte [rleString.split(":").length*2];
        String[] rleStringData = rleString.split(":"); //remove delimiters and make hexadecimal string array
        String firstDigit;
        String secondDigit;
        int j = 0;
        for (int i = 0; i < rleData.length; i+=2) { //split each array element into substrings so that each substring can be properly translated into byte-array values
            if (rleStringData[j].length() == 3) {
                firstDigit = rleStringData[j].substring(0,2);
                secondDigit = rleStringData[j].substring(2);
            } else { //(rleStringData[i].length() == 2)
                firstDigit = rleStringData[j].substring(0,1);
                secondDigit = rleStringData[j].substring(1);
            }
            rleData[i] = hexCharStringDecode(firstDigit); //decode string data types returning bytes
            rleData[i+1] = hexCharStringDecode(secondDigit); //decode string data types returning bytes
            j++;
        }
        return rleData;
    }

    //    public static int numericalCount(String string) {
//
//
//    }

}
