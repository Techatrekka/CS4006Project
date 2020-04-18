/**
 *
 * CS4006 Intelligent Systems - Project.
 *
 * @author: Michele Cavaliere - 18219365
 * @author: Nicole Berty - 18246702
 * @author: Sean Lynch - 18245137
 * @author: Matt Lucey - 18247083
 *
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class fx extends Application {

    static Square[][] board;
    static ArrayList<Integer> letter = new ArrayList<>();
    static GridPane grid = new GridPane();

    public static void main(String[] args) {
        Board Board = new Board();
        board = Board.getBoard();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        printGrid(primaryStage);
        fx.dialogBox();
    }

    /**
     * Method to fill squares on the grid.
     *
     * @SubScene
     */

    public static SubScene fillSquare() {
        Rectangle rec = new Rectangle(200, 200);
        rec.setFill(Color.RED);

        Group group1 = new Group();
        group1.getChildren().add(rec);
        SubScene scene = new SubScene(group1, 75, 75);
        scene.setFill(Color.WHITE);
        return scene;
    }

    /**
     * Method to draw the path of the A* Algorithm.
     *
     * @return
     */

    public static SubScene path() {
        Circle cir = new Circle(25,25f,20);
        cir.setFill(Color.GREEN);

        Group group1 = new Group();
        group1.getChildren().add(cir);
        SubScene scene = new SubScene(group1, 45, 45);
        scene.setFill(Color.WHITE);
        return scene;
    }

    /**
     * Pops up four dialog box's to the screen to enter the co-ordinates
     * of the start and goal position.
     */

    static void dialogBox() {

        List<String> choices2 = new ArrayList<> ();

        for(int i = 1; i <= 8; i++){
            choices2.add(String.valueOf(i));
        }

        ChoiceDialog<String> dialog = new ChoiceDialog<>("1", choices2);
        dialog.setTitle("Choose Coordinates");
        dialog.setHeaderText("");
        dialog.setContentText("Choose row number for start position: ");

        Optional<String> results = dialog.showAndWait();
        if (results.isPresent()){
            letter.add(Integer.parseInt(results.get()) - 1);
        }

        List<String> choices = new ArrayList<>();

        for(int i = 1; i <= 8; i++){
            char c = (char)(i + 64);
            choices.add(String.valueOf(c));
        }

        ChoiceDialog<String> dialog2 = new ChoiceDialog<>("A", choices);
        dialog2.setTitle("Choose Coordinates");
        dialog2.setHeaderText("");
        dialog2.setContentText("Choose column letter for start position: ");

        Optional<String> result2 = dialog2.showAndWait();
        if (result2.isPresent()){
            letter.add((result2.get().charAt(0) - 64) - 1);
        }

        ChoiceDialog<String> dialog3 = new ChoiceDialog<>("1", choices2);
        dialog3.setContentText("Choose row number for end position: ");
        dialog3.setHeaderText("");
        Optional<String> result3 = dialog3.showAndWait();
        if (result3.isPresent()){
            letter.add(Integer.parseInt(result3.get()) - 1);
        }

        ChoiceDialog<String> dialog4 = new ChoiceDialog<>("A", choices);
        dialog4.setContentText("Choose column letter for end position: ");
        dialog4.setHeaderText("");
        Optional<String> result4 = dialog4.showAndWait();
        if (result4.isPresent()){
            letter.add((result4.get().charAt(0) - 64) - 1);
        }

        Text startPos = new Text("Start");
        startPos.setFont(Font.font("Tahoma", FontWeight.NORMAL, 32));
        startPos.setFill(Color.BLUE);

        Text goalPos = new Text("Goal");
        goalPos.setFont(Font.font("Tahoma", FontWeight.NORMAL, 32));
        goalPos.setFill(Color.ORANGE);

        grid.add(startPos, letter.get(1) ,letter.get(0));
        grid.add(goalPos, letter.get(3), letter.get(2));
    }

    /**
     * Prints out the grid on the sceen.
     * Adds both numbers and letters to the side and top of the grid.
     * @param primaryStage
     */

    void printGrid(Stage primaryStage){

        Text numbers = new Text("1\n2\n3\n4\n5\n6\n7\n8");
        numbers.setFont(Font.font("Tahoma", FontWeight.NORMAL, 63));

        Text letters = new Text(" A  B  C  D  E  F  G  H");
        letters.setFont(Font.font("Tahoma", FontWeight.NORMAL, 63));

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

        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                if (board[i][j].getOccupied()) {
                    grid.add(fillSquare(), i, j);
                }
            }
        }

        StackPane stack = new StackPane();
        stack.getChildren().addAll(grid,numbers,letters);
        StackPane.setAlignment(letters, Pos.TOP_CENTER);
        StackPane.setAlignment(numbers, Pos.CENTER_LEFT);

        grid.setAlignment(Pos.CENTER);
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

class Node {
    int x,y;
    double gValue = 100, hValue = 100, fValue = 100;
    Node parent;

    Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Graph {

    Square[][] Board;
    Node start;
    Node end;

    Graph(Square[][] Board, int startX, int startY, int endX, int endY) {
        this.Board = Board;
        start = new Node(startX, startY);
        end = new Node(endX, endY);
        Astar();
    }

    ArrayList<Node> Astar() {
        ArrayList<Node> open = new ArrayList<>();
        ArrayList<Node> closed = new ArrayList<>();
        open.add(start);
        //g(n) is the total computed cost
        Node current = open.get(0);
        while(!(current == end)) {
            current.hValue = ManhattanDistance(current);
            current.gValue = g(current);
            current.fValue = current.hValue + current.gValue;
            for (int i = 0; i < open.size(); i++) {
                if (open.get(i).hValue < current.hValue) {
                    current = open.get(i);
                }
            }
            closed.add(current);
            if (current.hValue == 0) {
                break;
            } else {
                ArrayList<Node> neighboursOfCurrent = new ArrayList<>();
                if (!(current.x + 1 > 7)) {
                    Node rightNeighbour = new Node(current.x + 1,current.y);
                    rightNeighbour.hValue = ManhattanDistance(rightNeighbour);
                    rightNeighbour.gValue = g(rightNeighbour);
                    rightNeighbour.fValue = rightNeighbour.gValue + rightNeighbour.hValue;
                    neighboursOfCurrent.add(rightNeighbour);
                }
                if (!(current.x - 1 < 0)) {
                    Node leftNeighbour = new Node(current.x - 1,current.y);
                    leftNeighbour.hValue = ManhattanDistance(leftNeighbour);
                    leftNeighbour.gValue = g(leftNeighbour);
                    leftNeighbour.fValue = leftNeighbour.gValue + leftNeighbour.hValue;
                    neighboursOfCurrent.add(leftNeighbour);
                }
                if (!(current.y + 1 > 7)) {
                    Node upNeighbour = new Node(current.x,current.y + 1);
                    upNeighbour.hValue = ManhattanDistance(upNeighbour);
                    upNeighbour.gValue = g(upNeighbour);
                    upNeighbour.fValue = upNeighbour.gValue + upNeighbour.hValue;
                    neighboursOfCurrent.add(upNeighbour);
                }
                if (!(current.y - 1 < 0)) {
                    Node downNeighbour = new Node(current.x,current.y - 1);
                    downNeighbour.hValue = ManhattanDistance(downNeighbour);
                    downNeighbour.gValue = g(downNeighbour);
                    downNeighbour.fValue = downNeighbour.gValue + downNeighbour.hValue;
                    neighboursOfCurrent.add(downNeighbour);
                }
                for (Node x: neighboursOfCurrent) {
                    if (x.gValue < current.gValue && closed.contains(x)) {
                        closed.remove(current);
                    } else if (x.gValue < current.gValue && open.contains(x)) {
                        open.remove(current);
                    } else if (!open.contains(x) && !closed.contains(x)) {
                        x.hValue = ManhattanDistance(x);
                        x.parent = current;
                        open.add(x);
                    }
                }
            }
        }
        return closed;
    }

    //h(n)
    double ManhattanDistance(Node current) {
        int x = end.x - current.x;
        int y  = end.y - current.y;
        double distance = Math.sqrt(Math.pow(x,2) + Math.pow(y,2));
        return distance;
    }

    //g(n)
    double g(Node current) {
        int x = current.x - start.x;
        int y = current.y - start.y;
        double distance = Math.sqrt(x^2 + y^2);
        return distance;
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
        int startXPos = (int) (Math.random() * 6);
        int startYPos = (int) (Math.random() * 6);
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
                if(startXPos > 5 && startYPos > 5) {
                    startXPos = (int) (Math.random() * 4 + 1);
                    startYPos = (int) (Math.random() * 4 + 1);
                }
                if (orientation == 0) {
                    for(int i = 1; i < 3; i++) {
                        board[startXPos + i][startYPos].changeSquareStatus();
                    }
                    for(int i = 1; i < 3; i++) {
                        board[startXPos + 1][startYPos + i].changeSquareStatus();
                    }
                } else {
                    for(int i = 1; i < 3; i++) {
                        board[startXPos][startYPos + i].changeSquareStatus();
                    }
                    for(int i = 1; i < 3; i++) {
                        board[startXPos + i][startYPos + 1].changeSquareStatus();
                    }
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
}
