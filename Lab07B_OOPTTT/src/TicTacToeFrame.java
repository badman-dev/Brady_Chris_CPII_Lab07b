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

    Game game = new Game();
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
            ticBtn.addActionListener((ActionEvent ae) -> handleTurn(ticBtn.getRow(), ticBtn.getCol()));
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

    private void handleTurn(int row, int col)
    {
        game.playerMove(row, col);
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
        turnLbl.setText("Player " + game.getPlayer() + "'s turn.");
    }

}
