import javax.swing.*;

public class Game {
    boolean finished = false;
    boolean playing = true;
    String player = "X";
    int moveCnt = 0;
    final int MOVES_FOR_WIN = 5;
    final int MOVES_FOR_TIE = 7;

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public void playerMove(int row, int col) {
        if (!playing) {
            return;
        }
        if (!Board.isValidMove(row, col)) {
            JOptionPane.showMessageDialog(null, "Illegal move!");
            return;
        }

        Board.board[row][col] = player;
        moveCnt++;

        if(moveCnt >= MOVES_FOR_WIN)
        {
            if(Board.isWin(player))
            {
                System.out.println("Player " + player + " wins!");
                playing = false;
                JOptionPane.showMessageDialog(null, "Player " + player + " wins!");
                int answer = JOptionPane.showConfirmDialog(null, "Do you want to play again?", "Play again?", JOptionPane.YES_NO_OPTION);
                if (answer == JOptionPane.YES_OPTION) {
                    Board.clearBoard();
                    playing = true;
                }
            }
        }
        if(moveCnt >= MOVES_FOR_TIE)
        {
            if(Board.isTie())
            {
                System.out.println("It's a Tie!");
                playing = false;
                JOptionPane.showMessageDialog(null, "It's a tie!");
                int answer = JOptionPane.showConfirmDialog(null, "Do you want to play again?", "Play again?", JOptionPane.YES_NO_OPTION);
                if (answer == JOptionPane.YES_OPTION) {
                    Board.clearBoard();
                    playing = true;
                }
            }
        }
        if(player.equals("X"))
        {
            player = "O";
        }
        else
        {
            player = "X";
        }
    }


}
