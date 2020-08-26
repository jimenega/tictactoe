package tictactoe;
import java.util.Arrays;
import  java.util.Scanner;

public class tictactoe {

    public static boolean RunChecks(int testNum, String str) {
        boolean testResult = true;
        System.out.println("RunChecks Test Number: " + testNum + " string: " + str);
        switch (testNum) {
            case 1:
                System.out.println("Checking: Game not finished ? ");
                break;
            case 2:
                System.out.println("Checking: Game Draw (X or O");
                break;
            case 3:
                System.out.println("Checking: Win X ");
                break;
            case 4:
                System.out.println("Checking: Win O");
                break;
            case 5:
                System.out.println("Checking: Impossible - 3 in a row ( X and O");
                break;
            case 6:
                System.out.println("Checking: Impossible - diff O count >= 2");
                break;
            case 7:
                System.out.println("Checking: Impossible - diff X count >= 2");
                break;
            default:
                System.out.println("Something went wrong!");
                break;
        }
        testResult = true;
    return testResult;
    }

    public static boolean StateChecks(char [][] board) {
        String boardAgain = Arrays.deepToString(board);
        //System.out.println(Arrays.deepToString(board));
        //System.out.println(boardAgain);
        //char symbol= boardAgain.charAt(5);
        boolean testResult = false;
        String strAgain = "";
        for(int i = 2; i < 32; i++) {
            if(boardAgain.charAt(i) == 'X' | boardAgain.charAt(i) == 'O' | boardAgain.charAt(i) == '_')  {
                strAgain += String.valueOf(boardAgain.charAt(i));
            }
        }
        //System.out.println(strAgain);
        for( int i = 1; i <= 7; i++) {
            System.out.println("StateChecks: " + i );
            testResult = RunChecks( i, strAgain);
        }
    return testResult;
    }

    public static void BoardState(char [][] board) {
        System.out.println("Entering 'BoarState'");
        System.out.println("**************************************************************");
        boolean allTestCompleted = StateChecks(board);
        //System.out.println();
        System.out.println("**************************************************************");
        System.out.println("All Tests Completed: "+ allTestCompleted);
        System.out.println("Exiting 'BoarState'");
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