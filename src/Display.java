import acm.graphics.GCanvas;
import acm.graphics.GLine;
import acm.graphics.GOval;

import java.awt.*;

public class Display {

    private final int BOARD_HEIGHT;
    private final int BOARD_WIDTH;

    private final double CELL_HEIGHT;
    private final double CELL_WIDTH;

    private final GCanvas gcanvas;
    private final int size; 

    public Display(int order, GCanvas gcanvas) {
        this.BOARD_HEIGHT = gcanvas.getHeight();
        this.BOARD_WIDTH = gcanvas.getWidth();
        this.size = 2 * order;
        this.CELL_HEIGHT = BOARD_HEIGHT / size;
        this.CELL_WIDTH = BOARD_WIDTH / size;
        this.gcanvas = gcanvas;
        showInitial();
    }

    public void showInitial() {
        gcanvas.setBackground(Color.GREEN);
        showLines();
    }

    private void showLines() {
        for (int i = 1; i < size; i++) {
            gcanvas.add(new GLine(i * CELL_WIDTH, 0.0, i * CELL_WIDTH, BOARD_HEIGHT));
            gcanvas.add(new GLine(0.0, i * CELL_HEIGHT, BOARD_WIDTH, i * CELL_HEIGHT));
        }
    }

    private void setDisk(Position p, Color color) {
        GOval disk = new GOval(
                p.getColumn() * CELL_WIDTH,
                p.getRow() * CELL_HEIGHT,
                CELL_WIDTH,
                CELL_HEIGHT);
        disk.setFilled(true);
        disk.setFillColor(color);
        gcanvas.add(disk);
    }

    public void setWhite(Position p) {
        setDisk(p, Color.WHITE);
    }

    public void setBlack(Position p) {
        setDisk(p, Color.BLACK);
    }

    public void reverse(Position p) {
        GOval disk = (GOval) gcanvas.getElementAt(
                p.getColumn() * CELL_WIDTH + CELL_WIDTH / 2,
                p.getRow() * CELL_HEIGHT + CELL_HEIGHT / 2);
        if (disk.getFillColor() == Color.BLACK)
            disk.setFillColor(Color.WHITE);
        else
            disk.setFillColor(Color.BLACK);
    }

    public Position toPosition(double x, double y) {
        int row    = (int) (y / CELL_HEIGHT);
        int column = (int) (x / CELL_WIDTH);
        return new Position(row, column);
    }
}