import java.util.StringTokenizer;

public class Board {
    private final Cell[][] cells;
    private final Display display;
    private final int order;

    private int black;
    private int white;

    public Board(int order, Display display) {
        this.order = order;
        this.cells = new Cell[2 * order][2 * order];
        this.black = 0;
        this.white = 0;
        this.display = display;
        initBoard();
    }

    public Board(int order) {
        this(order, null);
    }

    private void initBoard() {
        
        for(int i = 0; i < size(); ++i) {
            for(int j = 0; j < size(); ++j) {
                this.cells[i][j] = Cell.empty();
            }
        }

        Position pos = new Position(order,order);
        setWhite(pos);
        pos = new Position(order-1, order-1);
        setWhite(pos);
        pos = new Position(order, order-1);
        setBlack(pos);
        pos = new Position(order-1, order);
        setBlack(pos);
        
    }

    public int size() {
        return 2 * order;
    }

    public boolean contains(Position position) {
        return position.getRow() < size() && position.getColumn() < size() && position.getRow() >= 0 && position.getColumn() >= 0;
    }

    public boolean isEmpty(Position position) {
        return contains(position) && this.cells[position.getRow()][position.getColumn()].isEmpty();
    }

    public boolean isWhite(Position position) {
        return contains(position) && this.cells[position.getRow()][position.getColumn()].isWhite();
    }

    public boolean isBlack(Position position) {
        return contains(position) && this.cells[position.getRow()][position.getColumn()].isBlack();
    }

    public void setWhite(Position position) {
        if(contains(position) && isEmpty(position)) {
            this.cells[position.getRow()][position.getColumn()].setWhite();
            ++this.white;
        }
        if(display != null​) display​.setWhite(position);
    }

    public void setBlack(Position position) {
        if(contains(position) && isEmpty(position)) {
            this.cells[position.getRow()][position.getColumn()].setBlack();
            ++this.black;
        }
        if(display != null​) display​.setBlack(position);
    }

    public void reverse(Position position) {
        if(contains(position) && !isEmpty(position)) {
            this.cells[position.getRow()][position.getColumn()].reverse();
            if(isWhite(position)){
                ++this.white;
                --this.black;
            }
            else {
                ++this.black;
                --this.white;
            }
        } 
        if(display != null​) display​.reverse(position);
    }

    public void loadBoard(String str) {
        StringTokenizer st = new StringTokenizer(str, "\n");
        int row = 0;
        this.white = 0;
        this.black = 0;
        while (st.hasMoreTokens()) {
            String rowChars = st.nextToken();
            for (int column = 0; column < this.cells[row].length; column++) {
                Cell cell = Cell.cellFromChar(rowChars.charAt(column));
                this.cells[row][column] = cell;
                if (cell.isWhite()) white += 1;
                else if (cell.isBlack()) black += 1;
            }
            row += 1;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(64);
        for (int i = 0; i < this.cells.length; i++) {
            for (int j = 0; j < this.cells[i].length; j++) {
                sb.append(cells[i][j].toString());
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public String getStatus() {
        return String.format("B:%3d W:%3d", this.black, this.white);
    }
}