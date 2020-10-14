package tictactoe;
import java.util.Arrays;
import  java.util.Scanner;

//tttdev-02
public class tictactoe {

    public static boolean CheckEmptyCells(int[] boardCount) {
        //System.out.println("Empty Cells ?");
        boolean emptyCells = true;
        if (boardCount[2] == 0) {
            emptyCells = false;
        }
        return emptyCells;
    }

    public static int[] BoardCount(String board) {
        System.out.println("call BoardCount - String is: " + board);
        int[] boardCount = {0, 0, 0};
        int countX = 0;
        int countO = 0;
        int count_ = 0;
        //char symbol = board.charAt(0);
        for (int i = 0; i < board.length();  ++i) {
            switch (board.charAt(i)) {
                case 'X':
                    ++countX;
                    break;
                case 'O':
                    ++countO;
                    break;
                case '_':
                    ++count_;
                    break;
                default:
                    System.out.println("?? location: BoardCount/switch/default");
                    //symbol = ' ';
                    break;
            }
        }
        boardCount[0] = countX; // Array Index 0 is the X counter
        boardCount[1] = countO;  // Array Index 1 is the O counter
        boardCount[2] = count_; // Array Index _ is the _ counter
        return boardCount;
    }
    public static boolean Check7(int diff) {
        boolean checkResult = false;
        System.out.println("Impossible 3: O count <= -2");
        if (diff <= -2) {
            System.out.print("Imposible: O count is greater than X count (2 or more)  ");
            System.out.println("diff: " + diff);
            checkResult = true;
        }
        //System.out.println();
        return checkResult;
    }

    public static boolean Check6(int diff) {
        boolean checkResult = false;
        System.out.println("Impossible 2: X count >= 2");
        if (diff >= 2) {
            System.out.print("Impossible: X count is greater than O count (2 or more)  ");
            System.out.println("diff: " + diff);
            checkResult = true;
        }
        //System.out.println();
        return checkResult;
    }

    public static boolean Check5(int diff) {
        boolean checkResult = false;
        System.out.println("Impossible 1: 3 X's in a row & 3 O's in a row");
        return checkResult;
    }

    public static boolean Check4(int diff) {
        boolean checkResult = false;
        System.out.println("O Wins");
        return checkResult;
    }

    public static boolean Check3(int diff) {
        boolean checkResult = false;
        System.out.println("X wins");
        return checkResult;
    }

    public static boolean Check2(char[][] board) {
        boolean checkResult = false;
        System.out.println("Draw");
        matrixTranspose(board);
        return checkResult;
    }

    public static boolean Check1(int diff, String str, char[][] board) {
        boolean checkResult = false;
        String s1, s2, s3;
        String X3 = "XXX";
        String O3 = "OOO";
        System.out.println("Game not finished");
        s1 = rotate(s2 = rotate(s3 = rotate(str, 3), 3),3);
        System.out.println(s1 + " " + s2 + " " + s3);
        s1 = s1.substring(0, 3);
        s2 = s2.substring(0, 3);
        s3 = s3.substring(0, 3);
        System.out.println("s1 cutdown: " + s1);
        System.out.println("s2 cutdown: " + s2);
        System.out.println("s3 cutdown: " + s3);
        System.out.println("s1 X equal: " + X3.equals(s1));
        System.out.println("s2 X equal: " + X3.equals(s2));
        System.out.println("s3 X equal: " + X3.equals(s3));
        System.out.println("s1 O equal: " + O3.equals(s1));
        System.out.println("s2 O equal: " + O3.equals(s2));
        System.out.println("s3 O equal: " + O3.equals(s3));
        // Transpose str and recheck colums
        System.out.println("run matrixTranspose");
        String strTrans =  matrixTranspose(board);
        System.out.println("strTrans: " + strTrans);
        return checkResult;
    }

    public static String matrixTranspose(char[][] inArray) {
        // Transpose inArray
        for (int i = 0; i < 3; i++) {
            for (int j = i + 1; j < 3; j++) {
                char temp = inArray[i][j];
                inArray[i][j] = inArray[j][i];
                inArray[j][i] = temp;
            }
        }
        System.out.println(Arrays.deepToString(inArray));

        // Convert transposed inArray to a String
        String boardAgain = Arrays.deepToString(inArray);
        String strAgain = "";
        for(int i = 2; i < 32; i++) {
            if(boardAgain.charAt(i) == 'X' | boardAgain.charAt(i) == 'O' | boardAgain.charAt(i) == '_')  {
                strAgain += String.valueOf(boardAgain.charAt(i));
            }
        }
        //System.out.println(strAgain);
        return strAgain;
    }

    public static String rotate(String str, int numRotation) {
        //Scanner scanner = new Scanner(System.in);
        //String str = scanner.nextLine();
        //int numRotation;
        //StringBuilder strNew = new StringBuilder();
        String strNew = "";
        String[] ary = str.split("");
        String[] newAry = new String[ary.length];
        for(int x = 0; x <= ary.length-1; x++){
            newAry[(x + numRotation) % ary.length ] = ary[x];
        }
        for (String f : newAry) {
            //System.out.print(f + "");
            strNew += f;
        }
        //System.out.println();
        //System.out.println("strNew: " + strNew);
        return strNew;
    }

    public static boolean RunChecks(int testNum, String str, int[] boardCount, boolean emptyCells, char[][] board) {
        int diff= boardCount[0] - boardCount[1];
        //System.out.println("RunChecks: " + testNum + " string: " + str);
        //System.out.println("boardCount: " + Arrays.toString(boardCount) + "  emptyCells: " + emptyCells);
        switch (testNum) {
            case 1:
                return Check1(diff, str, board);
            case 2:
                return Check2(board);
            case 3:
                return Check3(diff);
            case 4:
                return Check4(diff);
            case 5:
                return Check5(diff);
            case 6:
                return Check6(diff);
            case 7:
                return Check7(diff);
            default:
                System.out.println("RunCheck: switch/case default ?");
                return false;
        }
    }

    public static boolean StateChecks(char [][] board) {
        boolean testResult = false;

        //* Convert Array board back to a String ****************************************
        String boardAgain = Arrays.deepToString(board);
        String strAgain = "";
        for(int i = 2; i < 32; i++) {
            if(boardAgain.charAt(i) == 'X' | boardAgain.charAt(i) == 'O' | boardAgain.charAt(i) == '_')  {
                strAgain += String.valueOf(boardAgain.charAt(i));
            }
        }

        //* boardCount - Get the count of each slot for X, O, _ ***************************
        int[] boardCount = BoardCount(strAgain);

        //* Check for empty cells *********************************************************
        boolean emptyCells = CheckEmptyCells(boardCount);
        System.out.println("boardCount: " + Arrays.toString(boardCount) + " emptyCells: " + emptyCells);
        System.out.println("**************************************************************");
        System.out.println();

        //* Go to StateChecks for each desired test on the Board
        for( int i = 1; i <= 1; i++) {
            System.out.println("StateChecks: " + i );
            testResult = RunChecks( i, strAgain, boardCount, emptyCells, board);
            System.out.println("testResult: " + testResult);
            System.out.println();
        }
        return testResult;
    }

    public static void BoardState(char [][] board) {
        System.out.println("Entering 'BoardState'");
        System.out.println("**************************************************************");
        boolean allTestCompleted = StateChecks(board);
        System.out.println("**************************************************************");
        System.out.println("All Tests Completed: "+ allTestCompleted);
        System.out.println("Exiting 'BoardState'");
    }

    public static char CheckSymbol(String str, int symbolCount) {
        char symbol = str.charAt(symbolCount);
        
        switch (symbol) {
            case 'X':
            case 'O':
            case '_':
                break;
            default:
                symbol = ' ';
                break;
        }
    return symbol;
    }

    public static int UserInput(char [][] board) {
        int userInputCount= 0;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter cells: ");
        String str = scanner.next();
        if(str.length() < 9) {
            System.out.println("Symbol count must be 9");
        } else {
            userInputCount = str.length();
            int symbolCount = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    board[i][j] = CheckSymbol(str, symbolCount);
                    ++symbolCount;
                }
            }
        }
    return userInputCount;
    }

    public static void StatusDisplay(char [][] board) {
        char CurrentField;
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                CurrentField = board[i][j];
                System.out.print(CurrentField + " ");
            }
        System.out.println('|');
        }
    	System.out.println("---------");
    }

    public static void main(String[] args) {
        int userSymbolInputCount;
        char[][] board = new char[3][3];
        userSymbolInputCount =  UserInput(board);
        System.out.println(Arrays.deepToString(board));
        if (userSymbolInputCount >= 9 ) {
            StatusDisplay(board);
            BoardState(board);
        }
    }
}