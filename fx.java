import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.Arrays;

public class fx extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        letterChoice();
        printGrid(primaryStage);

    }

    void letterChoice() {
        int xVal = (int) (Math.random() * 7 + 1);
        int yVal = (int) (Math.random() * 7 + 1);

        int[] startPoint = {xVal, yVal};
        for (int i = 0; i < startPoint.length; i++) {
            System.out.println(Arrays.toString(startPoint));
        }

        String letter = " ";
        // T
        if(xVal - 1 >= 0 && xVal + 1 <= 7 && yVal + 2 <= 7){
            letter += "T, ";
        }

        // L
        if(yVal + 2 >= 0 && xVal + 2 <=7){
            letter += "L, ";
        }

        // I
        // I is always true, can be done at any point
        if(yVal + 2 <= 7 || yVal - 2 >= 0){
            letter += "I";
        }
        System.out.println(letter);
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

    void printGrid(Stage primaryStage){
        GridPane grid = new GridPane();
        grid.setGridLinesVisible(true);

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

        grid.setOnMouseReleased(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                grid.add(fillSquare(), (int)((me.getSceneX() - (me.getSceneX() % 75)) / 75), (int)((me.getSceneY() - (me.getSceneY() % 75)) / 75));
            }
        });
        
        grid.setStyle("-fx-background-color: WHITE; -fx-grid-lines-visible: true");
        Scene scene = new Scene(grid, (colSize * 75),(rowSize * 75), Color.WHITE);
        primaryStage.setTitle("Grid");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
