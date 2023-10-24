import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TicTacToeFrame extends JFrame {
    JPanel titlePnl;
    JPanel gamePnl;
    JPanel turnPnl;
    JLabel titleLbl;
    JLabel turnLbl;
    JButton quitBtn;

    private static TicTacToeTile[] ticBtns;

    boolean finished = false;
    boolean playing = true;
    String player = "X";
    int moveCnt = 0;
    final int MOVES_FOR_WIN = 5;
    final int MOVES_FOR_TIE = 7;
    public TicTacToeFrame() {
        createTitlePanel();
        createGamePanel();
        createTurnPanel();

        Toolkit tk=Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();

        setTitle("Tic Tac Toe");
        setSize(360, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        Board.clearBoard();
        display();
    }

    private void createTitlePanel() {
        titlePnl = new JPanel();
        titleLbl = new JLabel("TIC TAC TOE");
        titleLbl.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));

        titlePnl.add(titleLbl);
        add(titlePnl, BorderLayout.NORTH);
    }

    private void createGamePanel() {
        gamePnl = new JPanel();
        gamePnl.setLayout(new GridLayout(3,3));
        ticBtns = new TicTacToeTile[]{
                new TicTacToeTile(0,0),
                new TicTacToeTile(0,1),
                new TicTacToeTile(0,2),
                new TicTacToeTile(1,0),
                new TicTacToeTile(1,1),
                new TicTacToeTile(1,2),
                new TicTacToeTile(2,0),
                new TicTacToeTile(2,1),
                new TicTacToeTile(2,2)
        };

        for (TicTacToeTile ticBtn : ticBtns)
        {
            ticBtn.addActionListener((ActionEvent ae) -> playerMove(ticBtn.getRow(), ticBtn.getCol()));
            gamePnl.add(ticBtn);
        }
        add(gamePnl, BorderLayout.CENTER);
    }

    private void createTurnPanel() {
        turnPnl = new JPanel();
        turnLbl = new JLabel("TURN LABEL");
        quitBtn = new JButton("Quit");

        quitBtn.addActionListener((ActionEvent ae) -> System.exit(0));

        turnPnl.add(turnLbl);
        turnPnl.add(quitBtn);
        add(turnPnl, BorderLayout.SOUTH);
    }

    private void playerMove(int row, int col) {
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
                display();
                System.out.println("Player " + player + " wins!");
                playing = false;
                JOptionPane.showMessageDialog(null, "Player " + player + " wins!");
                int answer = JOptionPane.showConfirmDialog(null, "Do you want to play again?", "Play again?", JOptionPane.YES_NO_OPTION);
                if (answer == JOptionPane.YES_OPTION) {
                    Board.clearBoard();
                    display();
                    playing = true;
                }
            }
        }
        if(moveCnt >= MOVES_FOR_TIE)
        {
            if(Board.isTie())
            {
                display();
                System.out.println("It's a Tie!");
                playing = false;
                JOptionPane.showMessageDialog(null, "It's a tie!");
                int answer = JOptionPane.showConfirmDialog(null, "Do you want to play again?", "Play again?", JOptionPane.YES_NO_OPTION);
                if (answer == JOptionPane.YES_OPTION) {
                    Board.clearBoard();
                    display();
                    playing = true;
                }
            }
        }
        if (!playing) {

        }
        if(player.equals("X"))
        {
            player = "O";
        }
        else
        {
            player = "X";
        }

        display();
    }

    private void display()
    {
        // shows the Tic Tac Toe game
        for(int row=0; row < Board.ROW; row++)
        {
            System.out.print("| ");
            for(int col=0; col < Board.COL; col++)
            {
                for (TicTacToeTile ticBtn : ticBtns) {
                    if (ticBtn.getRow() == row && ticBtn.getCol() == col) {
                        ticBtn.setText(Board.board[row][col]);
                    }
                }
                System.out.print(Board.board[row][col] + " | ");
            }
            System.out.println();
        }

        turnLbl.setText("Player " + player + "'s turn.");

    }
}
