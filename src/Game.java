public class Game {

    private final Board board;
    private State state;

    public Game(Board board) {
        this.board = board;
        this.state = State.BLACK; // tryPlayer(BLACK);
    }

    public boolean isFinished() {
        return this.state == State.FINISHED;
    }

    public boolean isSame(State player, Position position) {
        if(player == State.WHITE) {
            return board.isWhite(position);
        }
        else {
            return board.isBlack(position);
        }
    }

    public boolean isOther(State player, Position position) {
        if(player == State.WHITE) {
            return board.isBlack(position);
        }
        else {
            return board.isWhite(position);
        }
    }

    public boolean someSame(State player, Position position, Direction direction) {
        while(board.contains(position) && !board.isEmpty(position)){
            if(isSame(player,position)) {
                return true;
            }
            else {
                position = position.move(direction);
            }
        }
        return false;
    }
    
    public boolean isReverseDirection(State player, Position position, Direction direction) {
        position = position.move(direction);
        if(isOther(player,position)) {
            if(someSame(player,position,direction)) {
                return true;
            }
        }
        return false;
    }

    public boolean[] directionsOfReverse(State player, Position position) {
        boolean[] resultat = new boolean [Direction.ALL.length];
        for(int i = 0; i < Direction.ALL.length; ++i) {
            resultat[i] = isReverseDirection(player, position, Direction.ALL[i]);
        }
        return resultat;
    }

    private static boolean allFalse(boolean[] bools) {
        for(int i = 0; i < bools.length; ++i) {
            if(bools[i] == true) {
                return false;
            }
        }
        return true;
    }

    public boolean canPlayPosition(State player, Position position) {
        if(board.isEmpty(position)) {
            for(int i = 0; i < Direction.ALL.length; ++i) {
                if(isReverseDirection(player, position, Direction.ALL[i])) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean canPlay(State player) {
        for(int i = 0; i < board.size(); ++i) {
            for(int j = 0; j < board.size(); ++j) {
                Position position = new Position(i, j);
                if(canPlayPosition(player, position)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void disk(Position position) {
        if(this.state == State.WHITE) {
            board.setWhite(position);
        }
        else {
            board.setBlack(position);
        }
    }

    private void reverse(Position position, Direction direction) {
        position = position.move(direction);
        while(isOther(this.state, position)){
            board.reverse(position);
            position = position.move(direction);
        }
    }

    private void reverse(Position position, boolean[] directions) {
        for(int i = 0; i < Direction.ALL.length; ++i) {
            if(directions[i] == true) {
                reverse(position, Direction.ALL[i]);
            }
        }
    }

    private void changeTurn() {
        if(this.state == State.BLACK) {
            if(canPlay(State.WHITE)) {
                this.state = State.WHITE;
            }
            else if(canPlay(State.BLACK)) {
                this.state = State.BLACK;
            }
            else {
                this.state = State.FINISHED;
            }
        }
        else if(this.state == State.WHITE) {
            if(canPlay(State.BLACK)) {
                this.state = State.BLACK;
            }
            else if(canPlay(State.WHITE)) {
                this.state = State.WHITE;
            }
            else {
                this.state = State.FINISHED;
            }
        }
    }


    public void move(Position position) {
        if (!this.board.isEmpty(position)) return;
        boolean[] directions = this.directionsOfReverse(this.state, position);
        if (allFalse(directions)) return;
        this.disk(position);
        this.reverse(position, directions);
        this.changeTurn();
    }

    public String getStatus() {
        String move;
        if (this.state == State.FINISHED) {
            move = "FINISHED";
        } else {
            move = (this.state == State.BLACK ? "BLACK" : "WHITE") + " moves";
        }
        return String.format("%s - %s", this.board.getStatus(), move);
    }
}