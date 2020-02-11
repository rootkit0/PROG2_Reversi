

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LoadBoardTest {

    private static final String someBoard =
            "··w·····\n" +
            "··w··b··\n" +
            "··w·b···\n" +
            "··wwbb··\n" +
            "···bw···\n" +
            "···b·w··\n" +
            "···w····\n" +
            "··bbb···\n";

    @Test
    public void test_loadBoard() {
        Board board = new Board(4);
        board.loadBoard(someBoard);
        assertEquals(someBoard, board.toString());
        assertEquals("B:  9 W:  8", board.getStatus());
    }
}
