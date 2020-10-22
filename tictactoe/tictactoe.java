package tictactoe;
import java.util.Arrays;
import  java.util.Scanner;

//branch: Master
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
        int[] boardCount = {0, 0, 0};
        int countX = 0;
        int countO = 0;
        int count_ = 0;

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

    public static boolean Check6(int[] boardCount, int[] winCount) {
        // Impossible 2 - t6/ex7
        boolean checkResult = false;
        int diff = boardCount[0] - boardCount[1];
        //System.out.println("Check6 - Impossible2");
        if (diff >= 2 || diff <= -2) {
            checkResult = true;
           //System.out.println(Arrays.toString(boardCount) + " " + Arrays.toString(winCount) + " " + diff);
            System.out.print("Impossible");
        }
        return checkResult;
    }

    public static boolean Check5(int[] boardCount, int[] winCount) {
        // Impossible 1 - t5/ex6
        boolean checkResult = false;
        int diff = boardCount[0] - boardCount[1];
        //System.out.println("Check5 - Impossible 1");
        boolean emptyCells = CheckEmptyCells(boardCount);
        if (winCount[0] >= 1 && winCount[1] >= 1) {
            checkResult = true;
            //System.out.println(Arrays.toString(boardCount) + " " + Arrays.toString(winCount) + " " + diff);
            System.out.println("Impossible");
        }
        return checkResult;
    }

    public static boolean Check4(int[] boardCount, int[] winCount) {
        // O Wins - t4/ex3
        boolean checkResult = false;
        int diff = boardCount[0] - boardCount[1];
        //System.out.println("Check4 - O Wins");
        boolean emptyCells = CheckEmptyCells(boardCount);
        if (winCount[0] == 0 && winCount[1] == 1) {
            checkResult = true;
            //System.out.println(Arrays.toString(boardCount) + " " + Arrays.toString(winCount) + " " + diff);
            System.out.println("O Wins");
        }
        return checkResult;
    }

    public static boolean Check3(int[] boardCount, int[] winCount) {
        // X Wins - t3/ex1, ex2
        boolean checkResult = false;
        int diff = boardCount[0] - boardCount[1];
        //System.out.println("Check3 - X Wins");
        boolean emptyCells = CheckEmptyCells(boardCount);
        if (winCount[0] == 1 && winCount[1] == 0) {
            checkResult = true;
            //System.out.println(Arrays.toString(boardCount) + " " + Arrays.toString(winCount) + " " + diff);
            System.out.println("X Wins");
        }
        return checkResult;
    }

    public static boolean Check2(int[] boardCount, int[] winCount) {
        // Draw - t2/ex4
        boolean checkResult = false;
        int diff = boardCount[0] - boardCount[1];
        //System.out.println("Check2 - Draw");
        boolean emptyCells = CheckEmptyCells(boardCount);
        if (winCount[0] == 0 && winCount[1] == 0 && !emptyCells) {
            checkResult = true;
            //System.out.println(Arrays.toString(boardCount) + " " + Arrays.toString(winCount) + " " + diff);
            System.out.println("Draw");
        }
        return checkResult;
    }

    public static boolean Check1(int[] boardCount, int[] winCount) {
        // Game not finished - t1/ex5
        boolean checkResult = false;
        int diff = boardCount[0] - boardCount[1];
      //System.out.println("Check1 - Game not finished");
        boolean emptyCells = CheckEmptyCells(boardCount);
        if (winCount[0] == 0 && winCount[1] == 0 && emptyCells && !(diff >= 2 || diff <= -2)) {
            checkResult = true;
          //System.out.println(Arrays.toString(boardCount) + " " + Arrays.toString(winCount) + " " + diff);
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
        r1 = r1.substring(0, 3);
        r2 = r2.substring(0, 3);
        r3 = r3.substring(0, 3);
        String strTrans =  matrixTranspose(board);
        c1 = rotate(c2 = rotate(c3 = rotate(strTrans, 3), 3),3);
        c1 = c1.substring(0, 3);
        c2 = c2.substring(0, 3);
        c3 = c3.substring(0, 3);
        df = String.valueOf(str.charAt(2)) + str.charAt(4) + str.charAt(6);
        db = String.valueOf(str.charAt(0)) + str.charAt(4) + str.charAt(8);
        String[] winArray = new String[] {r1, r2, r3, c1, c2, c3, df, db};
        int[] winCountArray = new int[] {0, 0};

        for( int i = 0; i <= 7; i++) {
            if (X3.equals(winArray[i])) {
                winCountArray[0]++;
            } else if (O3.equals(winArray[i]) ) {
                winCountArray[1]++;
            }
        }
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

        // Convert transposed inArray to a String
        String boardAgain = Arrays.deepToString(inArray);
        String strAgain = "";
        for(int i = 2; i < 32; i++) {
            if(boardAgain.charAt(i) == 'X' | boardAgain.charAt(i) == 'O' | boardAgain.charAt(i) == '_')  {
                strAgain += String.valueOf(boardAgain.charAt(i));
            }
        }
        return strAgain;
    }

    public static String rotate(String str, int numRotation) {
        String strNew = "";
        String[] ary = str.split("");
        String[] newAry = new String[ary.length];
        for(int x = 0; x <= ary.length-1; x++){
            newAry[(x + numRotation) % ary.length ] = ary[x];
        }
        for (String f : newAry) {
            strNew += f;
        }
        return strNew;
    }

    public static boolean RunChecks(int testNum, String str, int[] boardCount, boolean emptyCells, char[][] board, int[] winCountArray) {
        int diff= boardCount[0] - boardCount[1];
        switch (testNum) {
            case 1:
                return Check1(boardCount, winCountArray);
            case 2:
                return Check2(boardCount, winCountArray);
            case 3:
                return Check3(boardCount, winCountArray);
            case 4:
                return Check4(boardCount, winCountArray);
            case 5:
                return Check5(boardCount, winCountArray);
            case 6:
                return Check6(boardCount, winCountArray);
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
      //System.out.println("*************************************************************");
      //System.out.println("boardCount: "   + Arrays.toString(boardCount)
      //        + "  winChecks: " + Arrays.toString(winCountArray)
      //        + "  emptyCells: " + emptyCells);
      //System.out.println("**************************************************************");
        //System.out.println();

        // Go to StateChecks for each desired test on the Board
        for( int i = 1; i <= 6; i++) {
         // System.out.println("StateChecks: " + i );
            testResult = RunChecks( i, strAgain, boardCount, emptyCells, board, winCountArray);
         // System.out.println();
        }
        return testResult;
    }

    public static void BoardState(char [][] board) {
        //System.out.println("Entering 'BoardState'");
        //System.out.println("**************************************************************");
        boolean allTestCompleted = StateChecks(board);
        //System.out.println("**************************************************************");
        //System.out.println();
        //System.out.println("Exiting 'BoardState'");
        //System.out.println("All Tests Completed: "+ allTestCompleted);
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

        char[][] ex = ex1;  //DEBUG CODE

        board = deepCopyMatrix(ex); //DEBUG CODE
        userSymbolInputCount = 9;  //DEBUG CODE
        //userSymbolInputCount =  UserInput(board);  //REAL CODE
        //System.out.println(Arrays.deepToString(board));
        if (userSymbolInputCount >= 9 ) {
            StatusDisplay(board);
            BoardState(board);
        }
    }
}