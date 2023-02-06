import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import javafx.stage.FileChooser;

public class Gui extends Application
{
    private Brett b = new Brett(3,3);
    private int fontSize = 49;
    private SudokuBeholder container = new SudokuBeholder();
    private static boolean hideFont = true;

    // Gui
    private final VBox layout = new VBox(5);
    private final Scene scene = new Scene(layout, 500, 550);
    private final BorderPane topBar = new BorderPane();
    private final HBox leftAlign = new HBox(5);
    private final HBox rightAlign = new HBox(5);
    private GridPane grid;

    private final Button chooseFileBtn = new Button("Load sudoku file");
    private final TextField fontField = new TextField(""+fontSize);
    private final Button changeSizeBtn = new Button("font size");

    private final Label solutionsLabel = new Label("solutions:");
    private final ComboBox<String> dropDown = new ComboBox<String>();
    private final Button solveBtn = new Button("Solve!");



    public static void main(String[] args)
    {
        if (args.length > 0 && args[0].equals("show-font-size"))
        {
            hideFont = false;
        }

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {

        primaryStage.setTitle("Sudoku solver");

        grid = generateGrid();

        leftAlign.setAlignment(Pos.CENTER_LEFT);
        rightAlign.setAlignment(Pos.CENTER_RIGHT);

        leftAlign.getChildren().addAll(chooseFileBtn,fontField,changeSizeBtn);
        rightAlign.getChildren().addAll(solutionsLabel,dropDown,solveBtn);

        if (hideFont)
        {
            leftAlign.getChildren().remove(fontField);
            leftAlign.getChildren().remove(changeSizeBtn);
        }

        topBar.setPrefHeight(30);
        topBar.setLeft(leftAlign);
        topBar.setRight(rightAlign);

        dropDown.getItems().add(""+0);
        dropDown.setValue(""+0);
        dropDown.setDisable(true);
        dropDown.setEditable(true);
        dropDown.setMaxWidth(75);

        fontField.setPrefColumnCount(2);

        layout.setPadding(new Insets(5,5,5,5));

        layout.getChildren().add(topBar);
        layout.getChildren().add(grid);

        primaryStage.setScene(scene);

        primaryStage.show();


        chooseFileBtn.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent arg0)
            {
                FileChooser fileChooser = new FileChooser();
                File file = fileChooser.showOpenDialog(primaryStage);
                String s = "";
                boolean fail = false;
                try
                {
                    b = new BoardReader().lesFil(file);
                    container = new SudokuBeholder(); // reset container
                    solveBtn.setDisable(false);
                }
                catch (FileNotFoundException e)
                {
                    s = "File not found.";
                    fail = true;
                }
                catch (InValidCharException e)
                {
                    s = "Found invalid character on sudoku board.";
                    fail = true;
                }
                catch (NumberFormatException e)
                {
                    s = "Board size must be defined by integers.";
                    fail = true;
                }
                catch (FileExceedMaxSudokuSizeException e)
                {
                    s = "Maximum sudoku size is 64x64";
                    fail = true;
                }
                catch (FileNotAdhereDefinitionException e)
                {
                    s = "Board is not the correct size.";
                    fail = true;
                }
                catch (ValueOutsideLegalRangeException e)
                {
                    s = "Character value is outside legal range";
                    fail = true;
                }
                catch (NoSuchElementException e)
                {
                    s = "Invalid file";
                    fail = true;

                }
                catch (NullPointerException e)
                {
                    // the user probably pressed cancel
                }
                catch (Exception e)
                {
                    // unexpected behaviour
                    s = "Something went wrong! :(\n";
                    s += e;
                    fail = true;
                }

                if (fail)
                {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error dialog");
                    alert.setHeaderText("File error!");
                    alert.setContentText(s);
                    alert.show();
                }
                double size = b.length()*b.width();
                fontSize = (int)Math.round(570.0*Math.pow(size, -1.114));
                if (fontSize>49)
                {
                    fontSize = 49;
                }
                fontField.setText(""+fontSize);

                updateGrid();
            }
        });

        fontField.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent key)
            {
                if (key.getCode().equals(KeyCode.ENTER))
                {
                    try
                    {
                        fontSize = Integer.parseInt(fontField.getText());
                    }
                    catch (Exception e)
                    {
                        // this field was left empty
                    }
                    updateGrid();
                }
            }
        });

        changeSizeBtn.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent arg0)
            {
                try
                {
                    fontSize = Integer.parseInt(fontField.getText());
                }
                catch (Exception e)
                {
                    // we carry on
                }
                updateGrid();
            }
        });

        dropDown.getEditor().textProperty().addListener(new ChangeListener<String>()
        {
            @Override
            public void changed(ObservableValue v, String s1, String s2)
            {
                try
                {
                    if (container.hentAntallLosninger()>0 && !s2.equals(""))
                    {
                        b = container.taUt()[(Integer.parseInt(s2) - 1)];
                    }
                }
                catch (Exception e)
                {
                    // this should never happen
                    System.out.println("Something went wrong! :(");
                    System.out.println("ComboBox dropDown chaged()");
                    System.out.println(e);
                    System.exit(1);
                }
                layout.getChildren().remove(grid);
                grid = generateGrid();
                layout.getChildren().add(grid);
            }
        });

        solveBtn.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent arg0)
            {
                solveBtn.setDisable(true);
                container = new SudokuBeholder(); // reset container
                new Brett(b, container, true).start();
                new Brett(b, container, false).start();
                try
                {
                    container.waitHere();
                }
                catch (Exception e)
                {
                    System.out.println("Something went wrong! :(");
                    System.out.println("Button solveBtn handle()");
                    System.out.println(e);
                    System.exit(1);
                }
                if (container.hentAntallLosninger() > 0)
                {
                    b = container.taUt()[0];
                    updateGrid();
                }
            }
        });
    }

    /**
     * Generates the grid panel where we see the current sudoku board.
     * The board is essentially a GridPane inside a GridPane with BorderPane
     * to show the induvidual values.
     * 
     * @return the new grid
     */
    private GridPane generateGrid()
    {
        int width = b.width();
        int length = b.length();
        int size = b.width()*b.length();
        double cellSize = 500.0/(double)size;
        GridPane grid = new GridPane();

        for (int i=0; i<length; i++)
        {
            for (int n=0; n<width; n++)
            {
                GridPane tmpGrid = new GridPane();
                tmpGrid.setStyle("-fx-border-color: black;"+
                                 "-fx-border-width: 1;");
                for (int y=0; y<width; y++)
                {
                    for (int x=0; x<length; x++)
                    {
                        int p = x+(size*width)*i+size*y+length*n;
                        char c = b.verdiTilTegn(b.place(p).value());
                        Label tmpLabel = new Label(Character.toString(c));
                        tmpLabel.setStyle("-fx-font:"+fontSize+" monospace;");
                        BorderPane tmpBox = new BorderPane();
                        tmpBox.setPrefSize(cellSize,cellSize);
                        tmpBox.setStyle("-fx-border-color: black;"+
                                        "-fx-border-width: .5;");
                        tmpBox.setCenter(tmpLabel);
                        tmpGrid.add(tmpBox, x, y);
                    }
                }
                grid.add(tmpGrid, n, i);
            }
        }
        return grid;
    }

    /**
     * This method replaces the old grid with the current.
     * Updating the dropdown menu(ComboBox) was added because we're changing
     * the number of solutions when we change Sudoku board.
     */
    private void updateGrid()
    {
        dropDown.getItems().clear();
        if (container.hentAntallLosninger() == 0)
        {
            dropDown.setValue(""+0);
            dropDown.setDisable(true);
        }
        else
        {
            for (int i=0;i<container.hentAntallLosninger(); i++)
            {
                dropDown.getItems().add(""+(i+1));
            }
            dropDown.setValue(""+1);
            if (container.hentAntallLosninger() == 1)
            {
                dropDown.setDisable(true);
            }
            else
            {
                dropDown.setDisable(false);
            }
        }
        layout.getChildren().remove(grid);
        grid = generateGrid();
        layout.getChildren().add(grid);
    }
}