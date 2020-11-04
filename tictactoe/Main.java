package tictactoe;
import java.util.Arrays;
import  java.util.Scanner;

public class Main {

    static boolean gameNotOver = true;
    static boolean swap = true;
    static String finalResult = null;

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
                    break;
            }
        }
        boardCount[0] = countX; // Array Index 0 is the X counter
        boardCount[1] = countO;  // Array Index 1 is the O counter
        boardCount[2] = count_; // Array Index _ is the _ counter
        return boardCount;
    }

    public static StringBuilder ArrayToStr(char [][] board) {
        // Convert Array board back to a String
        String boardAgain = Arrays.deepToString(board);
        StringBuilder strAgain = new StringBuilder();
        for(int i = 2; i < 32; i++) {
            if(boardAgain.charAt(i) == 'X' | boardAgain.charAt(i) == 'O' | boardAgain.charAt(i) == '_')  {
                strAgain.append(boardAgain.charAt(i));
            }
        }
        return strAgain;
    }

    public static boolean Check6(int[] boardCount) {
        boolean checkResult = false;
        int diff = boardCount[0] - boardCount[1];
        if (diff >= 2 || diff <= -2)  {
            checkResult = true;
            gameNotOver = false;
            //System.out.print("Impossible");
            finalResult = "Impossible";
        }
        return checkResult;
    }

    public static boolean Check5(int[] winCount) {
        boolean checkResult = false;

        if (winCount[0] >= 1 && winCount[1] >= 1) {
            checkResult = true;
            gameNotOver = false;
            //System.out.println("Impossible");
            finalResult = "Impossible";
        }
        return checkResult;
    }

    public static boolean Check4(int[] winCount) {
        boolean checkResult = false;

        if (winCount[0] == 0 && winCount[1] == 1) {
            checkResult = true;
            gameNotOver = false;
            //System.out.println("O wins");
            finalResult = "O wins";
        }
        return checkResult;
    }

    public static boolean Check3(int[] winCount) {
        boolean checkResult = false;

        if (winCount[0] == 1 && winCount[1] == 0) {
            checkResult = true;
            gameNotOver = false;
            //System.out.println("X wins");
            finalResult = "X wins";
        }
        return checkResult;
    }

    public static boolean Check2(int[] boardCount, int[] winCount) {
        boolean checkResult = false;

        boolean emptyCells = CheckEmptyCells(boardCount);
        if (winCount[0] == 0 && winCount[1] == 0 && !emptyCells) {
            checkResult = true;
            gameNotOver = false;
            //System.out.println("Draw");
            finalResult ="Draw";
        }
        return checkResult;
    }

    public static boolean Check1(int[] boardCount, int[] winCount) {
        boolean checkResult = false;
        int diff = boardCount[0] - boardCount[1];
        boolean emptyCells = CheckEmptyCells(boardCount);
        if (winCount[0] == 0 && winCount[1] == 0 && emptyCells && !(diff >= 2 || diff <= -2)) {
            checkResult = true;
            //System.out.println("Game not finished");
        }
        return checkResult;
    }

    public static int[] winChecks(String str, char[][] board) {
        String r1, r2, r3, c1, c2, c3, df, db;

        // Reference used to find Wins
        String X3 = "XXX", O3 = "OOO";

        // rows
        r1 = rotate(r2 = rotate(r3 = rotate(str, 3), 3),3);
        r1 = r1.substring(0, 3);
        r2 = r2.substring(0, 3);
        r3 = r3.substring(0, 3);

        // Transpose matrix for column checks
        String strTrans =  matrixTranspose(board);

        // columns
        c1 = rotate(c2 = rotate(c3 = rotate(strTrans, 3), 3),3);
        c1 = c1.substring(0, 3);
        c2 = c2.substring(0, 3);
        c3 = c3.substring(0, 3);

        // Find a Win in diagonals
        df = String.valueOf(str.charAt(2)) + str.charAt(4) + str.charAt(6);
        db = String.valueOf(str.charAt(0)) + str.charAt(4) + str.charAt(8);

        // Build winCount Array
        String[] winArray = new String[] {r1, r2, r3, c1, c2, c3, df, db};
        int[] winCountArray = new int[] {0, 0};

        // Find Wins: X or O
        for( int i = 0; i <= 7; i++) {
            if (X3.equals(winArray[i])) {
                winCountArray[0]++;
            } else if (O3.equals(winArray[i]) ) {
                winCountArray[1]++;
            }
        }
        return winCountArray;
    }

    public static String matrixTranspose(char[][] board) {
        // copy board
        char [][] inArray = deepCopyMatrix(board);
        // Transpose inArray
        for (int i = 0; i < 3; i++) {
            for (int j = i + 1; j < 3; j++) {
                char temp = inArray[i][j];
                inArray[i][j] = inArray[j][i];
                inArray[j][i] = temp;
            }
        }
        // Convert transposed inArray to a String
        StringBuilder strAgain = ArrayToStr(inArray);
        return strAgain.toString();
    }

    public static String rotate(String str, int numRotation) {
        StringBuilder strNew = new StringBuilder();
        String[] ary = str.split("");
        String[] newAry = new String[ary.length];
        for(int x = 0; x <= ary.length-1; x++){
            newAry[(x + numRotation) % ary.length ] = ary[x];
        }
        for (String f : newAry) {
            strNew.append(f);
        }
        return strNew.toString();
    }

    public static boolean RunChecks(int testNum, int[] boardCount, int[] winCountArray) {

        switch (testNum) {
            case 1:
                return Check1(boardCount, winCountArray);
            case 2:
                return Check2(boardCount, winCountArray);
            case 3:
                return Check3(winCountArray);
            case 4:
                return Check4(winCountArray);
            case 5:
                return Check5(winCountArray);
            case 6:
                return Check6(boardCount);
            default:
                System.out.println("RunCheck: switch/case default");
                return false;
        }
    }

    public static int[] getCoordinates(String coordinates, char[][] board) {
        Scanner scanner = new Scanner(coordinates);
        int c1 = 0;
        int c2 = 0;
        int count = 0;
        int icount = 0;
        int ccount = 0;
        int[] validCoordinates = new int[] {c1,c2};
        int[] realValidCoordinates = new int[] {-1,-1};

        // find the next int token
        while (scanner.hasNext()) {
            count++;
            if (scanner.hasNextInt()) {
                int c = scanner.nextInt();
                if (count == 1) {
                    c1 = c;
                } else {
                    c2 = c;
                }
                   icount++;
            } else if (scanner.hasNext()) {
                //String s = scanner.next();
                scanner.next();
                ccount++;
            }
        }
        if (ccount > 0 || icount < 2) {
            System.out.println("You should enter numbers!");
        } else if (c1 > 3 || c2 > 3) {
            System.out.println("Coordinates should be from 1 to 3!");
        } else {
            validCoordinates[0] = c1;
            validCoordinates[1] = c2;
            int[][] selectArray = new int[][]{{1, 1}, {1, 2}, {1, 3}, {2, 1}, {2, 2}, {2, 3}, {3, 1}, {3, 2}, {3, 3}};
            int[][] RealArray = new int[][]{{2, 0}, {1, 0}, {0, 0}, {2, 1}, {1, 1}, {0, 1}, {2, 2}, {1, 2}, {0, 2}};
            int indexToReal = 0;
            for (int[] Selected : selectArray) {
                boolean equals = Arrays.equals(Selected, validCoordinates);
                if (equals) break;
                indexToReal++;
            }
            int vC1 = RealArray[indexToReal][0];
            int vC2 = RealArray[indexToReal][1];
            if (board[vC1][vC2] == '_') {
                realValidCoordinates[0] = vC1;
                realValidCoordinates[1] = vC2;
            } else
                System.out.println("This cell is occupied! Choose another one!");
        }
        scanner.close();
        return realValidCoordinates ;
    }

    public static String getChar() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the coordinates: ");
        return scanner.nextLine();
    }

    public static void Session(char player, char[][] board) {
        int[] validCoordinates;
        do {
            validCoordinates = getCoordinates(getChar(), board);
        } while (validCoordinates[0] == -1);
        if (board[validCoordinates[0]] [validCoordinates[1]] == '_')
            board[validCoordinates[0]] [validCoordinates[1]] = player;
        //StatusDisplay(board);
        //StateChecks(board);
    }

    public static void StateChecks(char[][] board) {
        boolean testResult;
        // Convert Array board back to a String
        StringBuilder strAgain = ArrayToStr(board);

        // boardCount - Get the count of each slot for X, O, _
        int[] boardCount = BoardCount(strAgain.toString());

       //StatusDisplay(board); //debug

        // winChecks - check for X or O winner
        int[] winCountArray = winChecks(strAgain.toString(), board);

        //StatusDisplay(board); //debug

        // Go to StateChecks for each desired test on the Board
        for( int i = 1; i <= 6; i++) {
         // System.out.println("StateChecks: " + i );
            testResult = RunChecks( i, boardCount, winCountArray);
            if (testResult) {
                break;
            }
        }
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

    public static char swapPlayer() {
        //static boolean swap;
        swap = !swap;
        return swap?'X':'O';
    }
    public static void main(String[] args) {
        //int userSymbolInputCount;
        char[][] board = new char[][] {{'_','_','_'},{'_','_','_'},{'_','_','_'}};
        char player = 'X'; // Initial player
        StatusDisplay(board);
        do {
            Session(player,board);
            StatusDisplay(board);
            StateChecks(board);
            player = swapPlayer();
        } while (gameNotOver);
        System.out.println(finalResult);
    }
}