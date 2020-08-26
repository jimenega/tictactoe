package tictactoe;
import java.util.Arrays;
import  java.util.Scanner;

public class tictactoe {

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
                    //symbol = ' ';
                    break;
            }
        }
        boardCount[0] = countX;
        boardCount[1]= countO;
        boardCount[2] = count_;
        return boardCount;
    }

    public static boolean RunChecks(int testNum, String str) {
        boolean testResult = true;
        //System.out.println();
        System.out.println("RunChecks: " + testNum + " string: " + str);
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
                System.out.println();
                break;
            case 7:
                System.out.println("Checking: Impossible - diff X count >= 2");
                System.out.println();
                break;
            default:
                System.out.println("Something went wrong!");
                System.out.println();
                break;
        }
        testResult = true;
    return testResult;
    }

    public static boolean StateChecks(char [][] board) {
        String boardAgain = Arrays.deepToString(board);
        boolean testResult = false;
        String strAgain = "";
        for(int i = 2; i < 32; i++) {
            if(boardAgain.charAt(i) == 'X' | boardAgain.charAt(i) == 'O' | boardAgain.charAt(i) == '_')  {
                strAgain += String.valueOf(boardAgain.charAt(i));
            }
        }
        //boardCount - Get the count of each slot and print info on this***************************
        int[] boardCount = BoardCount(strAgain);
        System.out.println("boardCount: " + Arrays.toString(boardCount));
        System.out.println("**************************************************************");
        System.out.println();
        // Go to StateChecks for each desired test on the Board
        for( int i = 1; i <= 7; i++) {
            System.out.println("StateChecks: " + i );
            testResult = RunChecks( i, strAgain);
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