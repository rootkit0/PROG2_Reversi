import acm.program.GraphicsProgram;
import java.awt.event.MouseEvent;

public class Reversi extends GraphicsProgram {

    public static final int APPLICATION_HEIGHT = 600;
    public static final int APPLICATION_WIDTH  = 600;

    private static final int ORDER = 4;

    private Game game;
    private Display display;

    public void run() {
        addMouseListeners();
        initialize();
    }

    private void initialize() {
        display = new Display(ORDER, getGCanvas());
        Board board = new Board(ORDER, display);
        game = new Game(board);
        display.showInitial();
        setTitle(game.getStatus());
    }

    public void mouseClicked(MouseEvent evt) {
        if (game.isFinished()) return;
        Position p = display.toPosition(evt.getX(), evt.getY());
        game.move(p);
        setTitle(game.getStatus());
    }

    public static void main(String[] args) {
        new Reversi().start(args);
    }
}