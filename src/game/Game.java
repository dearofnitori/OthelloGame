package game;
//OthelloGUI
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game extends JPanel implements MouseListener {
    private static final int SIZE = 8;
    private int[][] board = new int[SIZE][SIZE]; // 0=空, 1=黒, 2=白
    private int currentPlayer = 1; // 1=黒, 2=白
    private BoardRenderer renderer = new BoardRenderer(); // ← これを追加
    private GameInfoPanel infoPanel; // 手番・石数表示用パネル

    // GameInfoPanelを受け取るコンストラクタ（盤面初期化も含む）
    public Game(GameInfoPanel infoPanel) {
        this.infoPanel = infoPanel;
        setPreferredSize(new Dimension(400, 400));
        setBackground(Color.GREEN);
        addMouseListener(this);
        initBoard(); // ← 盤面初期化
    }

    private void initBoard() {
        board[3][3] = 2; // 白
        board[3][4] = 1; // 黒
        board[4][3] = 1; // 黒
        board[4][4] = 2; // 白
    }
// 追加項目 描画呼び出し
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // 背景クリア
        
        // BoardRendererに盤面と石の描画を任せる
        renderer.drawBoard(g, board, getWidth(), getHeight());
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int tileSize = getWidth() / SIZE;
        int col = e.getX() / tileSize;
        int row = e.getY() / tileSize;
        
//追加した箇所
        if (board[row][col] == 0) {
//修正箇所、GUI側で合法手のチェックを導入
            ReverseStone3 reverser = new ReverseStone3(board, currentPlayer);
// 追加した箇所、
            if (reverser.isLegalMove(row, col, currentPlayer)) {
                board[row][col] = currentPlayer;
                reverser.reverseStones(row, col, currentPlayer);
                repaint();
                currentPlayer = (currentPlayer == 1) ? 2 : 1; // 手番交代
                infoPanel.updateInfo(currentPlayer, board); // ← 手番と石数の表示を更新
            }
        }
    }

    // 必須の空メソッド
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}

    public static void main(String[] args) {
        JFrame frame = new JFrame("オセロ");
        GameInfoPanel infoPanel = new GameInfoPanel(); // ← 情報表示パネルの生成
        Game gamePanel = new Game(infoPanel); // ← Gameに渡す

        frame.setLayout(new BorderLayout());
        frame.add(infoPanel, BorderLayout.NORTH); // ← 上部に情報表示
        frame.add(gamePanel, BorderLayout.CENTER); // ← 中央に盤面

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}