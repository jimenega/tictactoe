package tictactoe;
import java.util.Arrays;
import  java.util.Scanner;

public class tictactoe {

    public static boolean RunChecks(int testNum, String str) {
        boolean testResult = true;
        System.out.println("Test Number: " + testNum + " : " + str);
        testResult = true;
    return testResult;
    }

    public static void StateChecks(char [][] board) {
        String boardAgain = Arrays.deepToString(board);
        //System.out.println(Arrays.deepToString(board));
        //System.out.println(boardAgain);
        //char symbol= boardAgain.charAt(5);
        String strAgain = "";
        for(int i = 2; i < 32; i++) {
            if(boardAgain.charAt(i) == 'X' | boardAgain.charAt(i) == 'O' | boardAgain.charAt(i) == '_')  {
                strAgain += String.valueOf(boardAgain.charAt(i));
            }
        }
        System.out.println(strAgain);
        for( int i = 1; i <= 7; i++) {
          boolean testResult = RunChecks( i, strAgain);
        }
    }

    public static void BoardState(char [][] board) {
        StateChecks(board);
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