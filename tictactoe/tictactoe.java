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

    public static boolean RunChecks(int testNum, String str, int[] boardCount, boolean emptyCells) {
        boolean testResult = true;
        System.out.println("RunChecks: " + testNum + " string: " + str);
        System.out.println("boardCount: " + Arrays.toString(boardCount) + "  emptyCells: " + emptyCells);
        switch (testNum) {
            case 1:
                System.out.println("Checking: Game not finished ? ");
                System.out.println();
                break;
            case 2:
                System.out.println("Checking: Game Draw (X or O)");
                System.out.println();
                break;
            case 3:
                System.out.println("Checking: Win X");
                System.out.println();
                break;
            case 4:
                System.out.println("Checking: Win O");
                System.out.println();
                break;
            case 5:
                System.out.println("Checking: Impossible - 3 in a row ( X and O)");
                System.out.println();
                break;
            case 6:
                System.out.println("Checking: Impossible - diff O count >= 2");
                int diff = boardCount[0] - boardCount[1];
                if (diff >= 2) {
                    System.out.print("X count is greater or equal to O count: Impossible: ");
                } else if (diff <= -2) {
                    System.out.print("O count is greater or equal to X count: Impossible: ");
                }
                System.out.println("diff: " + diff);
                System.out.println();
                break;
            case 7:
                System.out.println("Checking: Impossible - diff X count >= 2");
                System.out.println();
                break;
            default:
                System.out.println("location: RunCheck/case/default - Something went wrong!");
                System.out.println();
                break;
        }
        testResult = true;
    return testResult;
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
        for( int i = 1; i <= 7; i++) {
            System.out.println("StateChecks: " + i );
            testResult = RunChecks( i, strAgain, boardCount, emptyCells);
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
        if (userSymbolInputCount >= 9 ) {
            StatusDisplay(board);
            BoardState(board);
        }
    }
}