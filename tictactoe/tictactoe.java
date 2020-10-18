package tictactoe;
import java.util.Arrays;
import  java.util.Scanner;

//stateAnalysis
public class tictactoe {

    // deepCopy is in code base only for testing and debugging
    public static char[][] deepCopyMatrix(char[][] input) {
        if (input == null) {
            return null;
        }
        char[][] result = new char[input.length][];
        for (int r = 0; r < input.length; r++) {
            result[r] = input[r].clone();
        }
        return result;
    }

    public static boolean CheckEmptyCells(int[] boardCount) {
        //System.out.println("Empty Cells ?");
        boolean emptyCells = true;
        if (boardCount[2] == 0) {
            emptyCells = false;
        }
        return emptyCells;
    }

    public static int[] BoardCount(String board) {
        //System.out.println("call BoardCount - String is: " + board);
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

    public static boolean Check7(int[] boardCount, int[] winCount) {
        boolean checkResult = false;
        int diff = boardCount[0] - boardCount[1];
        System.out.println("Check7");
        System.out.println("boardCount: "   + Arrays.toString(boardCount)
                + "  winChecks: " + Arrays.toString(winCount)
                + "  diff: " + diff);
        if (diff <= -2) {
            System.out.print("Imposible: O count is greater than X count (2 or more)  ");
            System.out.println("diff: " + diff);
            checkResult = true;
        }
        //System.out.println();
        return checkResult;
    }

    public static boolean Check6(int[] boardCount, int[] winCount) {
        boolean checkResult = false;
        int diff = boardCount[0] - boardCount[1];
        System.out.println("Check6");
        System.out.println("boardCount: "   + Arrays.toString(boardCount)
                + "  winChecks: " + Arrays.toString(winCount)
                + "  diff: " + diff);
        if (diff >= 2) {
            System.out.print("Impossible: X count is greater than O count (2 or more)  ");
            System.out.println("diff: " + diff);
            checkResult = true;
        }
        //System.out.println();
        return checkResult;
    }

    public static boolean Check5(int[] boardCount, int[] winCount) {
        boolean checkResult = false;
        int diff = boardCount[0] - boardCount[1];
        System.out.println("Check5");
        System.out.println("boardCount: "   + Arrays.toString(boardCount)
                + "  winChecks: " + Arrays.toString(winCount)
                + "  diff: " + diff);
        boolean emptyCells = CheckEmptyCells(boardCount);
        if (winCount[0] >= 1 && winCount[1] >= 1 && emptyCells) {
            checkResult = true;
            System.out.println("Impossible 1: 3 X's in a row & 3 O's in a row");
        }

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

    public static boolean Check1(int[] boardCount, int[] winCount) {
        boolean checkResult = false;
        int diff = boardCount[0] - boardCount[1];
        System.out.println("Check1");
        System.out.println("boardCount: "   + Arrays.toString(boardCount)
                + "  winChecks: " + Arrays.toString(winCount)
                + "  diff: " + diff);
        boolean emptyCells = CheckEmptyCells(boardCount);
        if (winCount[0] == 0 && winCount[1] == 0 && emptyCells) {
            checkResult = true;
            System.out.println("Game not finished");
        }
        return checkResult;
    }

    public static int[] winChecks(int[] boardCount, String str, char[][] board) {
        int diff= boardCount[0] - boardCount[1];
        boolean checkResult = false;
        String r1, r2, r3;
        String c1, c2, c3;
        String df, db;
        String X3 = "XXX";
        String O3 = "OOO";
        r1 = rotate(r2 = rotate(r3 = rotate(str, 3), 3),3);
        //System.out.println(r1 + "  " + r2 + "  " + r3);
        r1 = r1.substring(0, 3);
        r2 = r2.substring(0, 3);
        r3 = r3.substring(0, 3);
        //System.out.println("r1,r2,r3 cut: " + r1 + " " + r2 + " " + r3);
        //System.out.println("r2 cutdown: " + r2);
        //System.out.println("r3 cutdown: " + r3);
        //System.out.println("r1 X equal: " + X3.equals(r1));
        //System.out.println("r2 X equal: " + X3.equals(r2));
        //System.out.println("r3 X equal: " + X3.equals(r3));
        //System.out.println("r1 O equal: " + O3.equals(r1));
        //System.out.println("r2 O equal: " + O3.equals(r2));
        //System.out.println("r3 O equal: " + O3.equals(r3));
        // Transpose str and recheck colums
        //System.out.println("run matrixTranspose");
        String strTrans =  matrixTranspose(board);
        //System.out.println("strTrans: " + strTrans);
        c1 = rotate(c2 = rotate(c3 = rotate(strTrans, 3), 3),3);
        //System.out.println(c1 + "  " + c2 + "  " + c3);
        c1 = c1.substring(0, 3);
        c2 = c2.substring(0, 3);
        c3 = c3.substring(0, 3);
        //System.out.println("c1 cutdown: " + c1);
        //System.out.println("c2 cutdown: " + c2);
        //System.out.println("c3 cutdown: " + c3);
        //System.out.println("c1,c2,c3 cut: " + c1 + " " + c2 + " " + c3);
        //System.out.println("c1 X equal: " + X3.equals(c1));
        //System.out.println("c2 X equal: " + X3.equals(c2));
        //System.out.println("c3 X equal: " + X3.equals(c3));
        //System.out.println("c1 O equal: " + O3.equals(c1));
        //System.out.println("c2 O equal: " + O3.equals(c2));
        //System.out.println("c3 O equal: " + O3.equals(c3));
        // Start diagonal checks
        df = String.valueOf(str.charAt(2)) + str.charAt(4) + str.charAt(6);
        db = String.valueOf(str.charAt(0)) + str.charAt(4) + str.charAt(8);
        //System.out.println(df + "  " + db);
        //System.out.println("df X equal: " + X3.equals(df));
        //System.out.println("db X equal: " + X3.equals(db));
        //System.out.println("df O equal: " + O3.equals(df));
        //System.out.println("db O equal: " + O3.equals(db));
        String[] winArray = new String[] {r1, r2, r3, c1, c2, c3, df, db};
        int[] winCountArray = new int[] {0, 0};
        //System.out.println(Arrays.toString(winArray));
        //System.out.println("r1 X equal: " + X3.equals(winArray[0]));
        for( int i = 0; i <= 7; i++) {
            if (X3.equals(winArray[i])) {
                winCountArray[0]++;
            } else if (O3.equals(winArray[i]) ) {
                winCountArray[1]++;
            }
        }
        //System.out.println(Arrays.toString(winCountArray));
        return winCountArray;
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
        //System.out.println(Arrays.deepToString(inArray));

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

    public static boolean RunChecks(int testNum, String str, int[] boardCount, boolean emptyCells, char[][] board, int[] winCountArray) {
        int diff= boardCount[0] - boardCount[1];
        //System.out.println("RunChecks: " + testNum + " string: " + str);
        //System.out.println("boardCount: " + Arrays.toString(boardCount) + "  emptyCells: " + emptyCells);
        switch (testNum) {
            case 1:
                return Check1(boardCount, winCountArray);
                //System.out.println("running RunChecks1 - empty");
                //return false;
            case 2:
                return Check2(board);
            case 3:
                return Check3(diff);
            case 4:
                return Check4(diff);
            case 5:
                return Check5(boardCount, winCountArray);
            case 6:
                return Check6(boardCount, winCountArray);
            case 7:
                return Check7(boardCount, winCountArray);
            default:
                System.out.println("RunCheck: switch/case default ?");
                return false;
        }
    }

    public static boolean StateChecks(char [][] board) {
        boolean testResult = false;

        // Convert Array board back to a String
        String boardAgain = Arrays.deepToString(board);
        String strAgain = "";
        for(int i = 2; i < 32; i++) {
            if(boardAgain.charAt(i) == 'X' | boardAgain.charAt(i) == 'O' | boardAgain.charAt(i) == '_')  {
                strAgain += String.valueOf(boardAgain.charAt(i));
            }
        }

        // boardCount - Get the count of each slot for X, O, _
        int[] boardCount = BoardCount(strAgain);

        // winChecks - check for X or O winner
        int[] winCountArray = winChecks(boardCount, strAgain, board);

        //* Check for empty cells
        boolean emptyCells = CheckEmptyCells(boardCount);

        // Report vital game analysis data
        System.out.println("*************************************************************");
        System.out.println("boardCount: "   + Arrays.toString(boardCount)
                + "  winChecks: " + Arrays.toString(winCountArray)
                + "  emptyCells: " + emptyCells);
        System.out.println("**************************************************************");
        //System.out.println();

        // Go to StateChecks for each desired test on the Board
        for( int i = 1; i <= 1; i++) {
            System.out.println("StateChecks: " + i );
            testResult = RunChecks( i, strAgain, boardCount, emptyCells, board, winCountArray);
            //System.out.println("testResult: " + testResult);
            System.out.println();
        }
        return testResult;
    }

    public static void BoardState(char [][] board) {
        System.out.println("Entering 'BoardState'");
        //System.out.println("**************************************************************");
        boolean allTestCompleted = StateChecks(board);
        //System.out.println("**************************************************************");
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

    public static int UserInput(char[][] board) {
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

    public static void StatusDisplay(char[][] board) {
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
        char[][] board = new char[3][3];  //REAL CODE
        char[][] ex1 = new char[][] {{'X','X','X'},{'O','O','_'},{'_','O','_'}};  //DEBUG CODE  t3
        char[][] ex2 = new char[][] {{'X','O','X'},{'O','X','O'},{'X','X','O'}};  //DEBUG CODE  t3
        char[][] ex3 = new char[][] {{'X','O','O'},{'O','X','O'},{'X','X','O'}};  //DEBUG CODE  t4
        char[][] ex4 = new char[][] {{'X','O','X'},{'O','O','X'},{'X','X','O'}};  //DEBUG CODE  t2
        char[][] ex5 = new char[][] {{'X','O','_'},{'O','O','X'},{'_','X','_'}};  //DEBUG CODE  t1
        char[][] ex6 = new char[][] {{'X','O','_'},{'X','O','_'},{'X','O','X'}};  //DEBUG CODE  t5
        char[][] ex7 = new char[][] {{'_','O','_'},{'X','_','_'},{'X','_','X'}};  //DEBUG CODE  t6
        char[][] ex8 = new char[][] {{'_','O','O'},{'O','O','_'},{'X','_','X'}};  //DEBUG CODE  t7
        char[][] ex9 = new char[][] {{'X','X','X'},{'O','O','O'},{'_','_','_'}};  //DEBUG CODE

        char[][] ex = ex5;  //DEBUG CODE

        board = deepCopyMatrix(ex); //DEBUG CODE
        userSymbolInputCount = 9;  //DEBUG CODE
        //userSymbolInputCount =  UserInput(board);  //REAL CODE
        System.out.println(Arrays.deepToString(board));
        if (userSymbolInputCount >= 9 ) {
            StatusDisplay(board);
            BoardState(board);
        }
    }
}