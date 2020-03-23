public class Board {

    Square[][] board = new Square[8][8];

    public Board() {
        int numOfOccupiedSquares = (int)(Math.random() * 4) + 3;
        int startXPos = (int) Math.random() * 8,startYPos = (int) Math.random() * 8;
        board[startXPos][startYPos].changeSquareStatus();
        int XPos, YPos;
        for (int i = 0; i < numOfOccupiedSquares; i++) {
            do {
                XPos = (int) Math.random() * 8;
                YPos = (int) Math.random() * 8;
            } while (!board[XPos][YPos].getOccupied() && isBesideOccupiedSquare(XPos,YPos));
            board[XPos][YPos].changeSquareStatus();
        }
    }

    private boolean isBesideOccupiedSquare(int xPos, int yPos) {
        if (board[Math.abs(xPos-1)][yPos].getOccupied() || board[xPos+1][yPos].getOccupied()) {
            return true;
        } else if (board[xPos][Math.abs(yPos-1)].getOccupied() || board[xPos][yPos+1].getOccupied()) {
            return true;
        }
        return false;
    }
}
