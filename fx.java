import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class fx extends Application {

    static Square[][] board;

    public static void main(String[] args) {
        Board Board = new Board();
        board = Board.getBoard();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        printGrid(primaryStage);

    }

    public static SubScene fillSquare() {
        Circle circle = new Circle(20, 20f, 7);
        circle.setFill(Color.RED);

        Group group1 = new Group();
        group1.getChildren().add(circle);
        SubScene scene = new SubScene(group1, 40, 40);
        scene.setFill(Color.WHITE);
        return scene;
    }

    /*
    Point dialogBox() {
       JOptionPane popUpJOptionPane = new JOptionPane();
       //JOptionPane.
    }
    */

    void printGrid(Stage primaryStage){

        Text numbers = new Text("\n    1\n    2\n    3\n    4\n    5\n    6\n    7\n    8\n");
        numbers.setFont(Font.font("Tahoma", FontWeight.NORMAL, 63));

        Text letters = new Text("  A  B  C  D  E  F  G  H");
        letters.setFont(Font.font("Tahoma", FontWeight.NORMAL, 63));

        GridPane grid = new GridPane();
        grid.setGridLinesVisible(true);
        grid.setAlignment(Pos.CENTER_RIGHT);
        

        int rowSize = 8;
        int colSize = 8;
        for(int row = 0; row < rowSize; row++){
            RowConstraints rows = new RowConstraints(75);
            grid.getRowConstraints().add(rows);
        }

        for(int col = 0; col < colSize; col++){
            ColumnConstraints column = new ColumnConstraints(75);
            grid.getColumnConstraints().add(column);
        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j].getOccupied()) {
                    grid.add(fillSquare(), i, j);
                }
            }
        }
        
        StackPane stack = new StackPane();
        stack.getChildren().addAll(grid,numbers,letters);
        StackPane.setAlignment(letters, Pos.TOP_RIGHT);
        StackPane.setAlignment(numbers, Pos.CENTER_LEFT);

        grid.setStyle("-fx-background-color: WHITE; -fx-grid-lines-visible: true");
        Scene scene = new Scene(stack, (colSize * 89),(rowSize * 92), Color.WHITE);
        primaryStage.setTitle("Grid");
        primaryStage.setScene(scene);
        primaryStage.show();

        primaryStage.setMinHeight(rowSize * 95);
        primaryStage.setMinWidth(colSize * 89);
        primaryStage.setMaxHeight(rowSize * 95);
        primaryStage.setMaxWidth(colSize * 89);
    }
}

class Point {
    int x,y;
    Point() { 

    }

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Graph {

    Square[][] Board;
    Point start;
    Point end;

    Graph(Square[][] Board, int startX, int startY, int endX, int endY) {
        this.Board = Board;
        start = new Point(startX, startY);
        end = new Point(endX, endY);
    }

    int ManhattanDistance() {
        int distance = 0;
        return distance;
    }
}

class Path extends Board {

    Path() {

    }
}

class Square {

    boolean occupied = false;

    public Square() {

    }

    public Square(boolean occupied) {
        this.occupied = occupied;
    }

    public void changeSquareStatus() {
        if (occupied) {
            occupied = false;
        } else {
            occupied = true;
        }
    }

    public boolean getOccupied() {
        return occupied;
    }
}

class Board {

    Square[][] board;

    public Board() {
        board = new Square[8][8];
        for (int i =0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = new Square();
            }
        }
        int obstacle = (int)(Math.random() * 3);
        int startXPos = (int) (Math.random() * 8);
        int startYPos = (int) (Math.random() * 8);
        board[startXPos][startYPos].changeSquareStatus();
        int orientation = (int) (Math.random() * 2);
        System.out.print(orientation);
        System.out.println();
        System.out.print(obstacle);
        System.out.println();
        switch (obstacle) {
            case 0:
                //I shape
                if (orientation == 0) {
                    for (int i = 1; Math.abs(i) < 3; Math.abs(i++)) {
                        if (startYPos+i > 7){
                            i *= -1;
                            if (i == -2) {
                                i += 1;
                                board[startXPos][startYPos+i].changeSquareStatus();
                                break;
                            }
                        }
                        board[startXPos][startYPos+i].changeSquareStatus();
                        if (i < 0) {
                            i*=-1;
                        }
                    }
                } else {
                    for (int i = 1; Math.abs(i) < 3; Math.abs(i++)) {
                        if (startXPos+i > 7) {
                            i*= -1;
                            if (i == -2) {
                                i += 1;
                                board[startXPos+i][startYPos].changeSquareStatus();
                                break;
                            }
                        }
                        board[startXPos+i][startYPos].changeSquareStatus();
                        if (i < 0) {
                            i*=-1;
                        }
                    }
                }
                break;
            case 1:
                // L shape
                if (orientation == 0) {
                    for (int i = 1; Math.abs(i) < 3; Math.abs(i++)) {
                        if (startYPos+i > 7){
                            i *= -1;
                        }
                        board[startXPos][startYPos+i].changeSquareStatus();
                        if (i < 0) {
                            i*=-1;
                        }
                    }
                    if (startXPos + 1 > 7) {
                        board[startXPos-1][startYPos].changeSquareStatus();
                    } else {
                        board[startXPos+1][startYPos].changeSquareStatus();
                    }
                } else {
                    for (int i = 1; Math.abs(i) < 3; Math.abs(i++)) {
                        if (startXPos+i > 7){
                            i *= -1;
                        }
                        board[startXPos+i][startYPos].changeSquareStatus();
                        if (i < 0) {
                            i*=-1;
                        }
                    }
                    if (startYPos + 1 > 7) {
                        board[startXPos][startYPos-1].changeSquareStatus();
                    }else {
                        board[startXPos][startYPos+1].changeSquareStatus();
                    }
                }
                break;
            case 2:
                //T shape
                if (orientation == 0) {

                } else {

                }
                break;
        }
        /*int XPos, YPos;
        for (int i = 1; i < numOfOccupiedSquares; i++) {
            do {
                XPos = (int) (Math.random() * 8);
                YPos = (int) (Math.random() * 8);
            } while (!board[XPos][YPos].getOccupied() && !isBesideOccupiedSquare(XPos,YPos));
            board[XPos][YPos].changeSquareStatus();
        }*/
    }

    private boolean isBesideOccupiedSquare(int xPos, int yPos) {
        if (board[Math.abs(xPos-1)][yPos].getOccupied()) {
            return true;
        } else if (board[xPos][Math.abs(yPos-1)].getOccupied()) {
            return true;
        } else if (xPos !=7) {
            if (board[xPos+1][yPos].getOccupied()) {
                return true;
            }
        } else if (yPos != 7) {
            if (board[xPos][yPos+1].getOccupied()) {
                return true;
            }
        }
        return false;
    }

    public Square[][] getBoard() {
        return board;
    }

    void displayBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(board[i][j].getOccupied());
            }
            System.out.println();
        }
    }
}