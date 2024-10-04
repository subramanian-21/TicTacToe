import java.util.Arrays;
import java.util.Scanner;

public class TicTacToe {
    private final int rowsCols;
    char[][] board;
    TicTacToe(int rowsCols){
        board = new char[rowsCols][rowsCols];
        this.rowsCols = rowsCols;
        for (int i = 0; i < board.length; i++) {
            Arrays.fill(board[i], '-');
        }
    }
    public void start() {
        char chance = 'X';
        boolean isWon = false;
        while (!isComplete()) {
            int[] rc = getCoordinates(chance);
            int r = rc[0];
            int c = rc[1];
            place(r,c, chance);
            printBoard();
            if(isWon(r, c)){
                isWon = true;
                break;
            }
            if(chance == 'X'){
                chance = 'O';
            }else {
                chance = 'X';
            }
        }
        if(isWon){
            System.out.println(chance + " won ");
        }else {
        System.out.println("Match draw...");
        }
    }
    void place(int r, int c, char ch){
        board[r][c] = ch;
    }
    boolean isWon(int r, int c) {
        char player = board[r][c];
        boolean horizontal = true;
        boolean vertical = true;
        boolean lDiagonal = true;
        boolean rDiagonal = true;
        for(int i = 0;i<board.length;i++) {
            if(board[r][i] != player) {
                horizontal = false;
            }
            if(board[i][c] != player) {
                vertical = false;
            }
            if(r == c){
                if(board[i][i] != player) {
                    lDiagonal = false;
                }
                if(board[i][board.length-1-i] != player) {
                    rDiagonal = false;
                }
            }
        }
        if(r == c){
            return horizontal || vertical || lDiagonal || rDiagonal;
        }else {
            return horizontal || vertical ;
        }
    }
    boolean isComplete(){
        for(int i = 0;i<board.length;i++) {
            for (int j = 0; j < board.length; j++) {
                if(board[i][j] == '-') return false;
            }
        }

        return true;
    }

    public void printBoard(){
        for(int i = 0;i<board.length;i++) {
            for (int j = 0; j < board.length; j++) {
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
    }
    public boolean validCoordinates(int r, int c) throws  Exception{
        if(r >= board.length || c >= board.length || r < 0 || c < 0) {
            throw  new Exception("Invalid index");
        }
        if(board[r][c] != '-'){
            throw new Exception("Already "+board[r][c]+ " is placed");
        }
        return true;
    }
    public int[] getCoordinates(char chance){
        try {
            System.out.print(chance +" turn :");
            Scanner scanner = new Scanner(System.in);
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            if(validCoordinates(x, y)){
                return new int[]{x, y};
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return getCoordinates(chance);
    }
}
