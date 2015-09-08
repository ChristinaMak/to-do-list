package com.todolist;

/**
 * Class ToDoListGUI. Graphical user interface to interact with to do list.
 * By Christina Mak
 */
import javafx.application.*;
import javafx.scene.control.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.event.*;
import javafx.geometry.*;


public class ToDoListGUI extends Application
{
    private static final double WIDTH = 400;
    private static final double HEIGHT = 800;

    private ToDoList list;

    @Override
    public void start(Stage primaryStage)
    {
        // the pane and the buttons
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.BOTTOM_CENTER);
        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(50));
        Button addButton = new Button("Add");
        Button deleteButton = new Button("Delete");

//        // make the add button the default button when ENTER is pressed
//        addButton.setDefaultButton(true);

        addButton.setOnAction(new NavHandler());

        vbox.getChildren().add(addButton);
        vbox.getChildren().add(deleteButton);
        pane.add(vbox, 5, 5);
        //pane.add(addButton, 200, 200);
        //pane.add(deleteButton, 200, 600);

        // the scene and the stage
        Scene scene = new Scene(pane, WIDTH, HEIGHT);
        primaryStage.setTitle("To Do List");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /* Inner class to handle navigation buttons */
    class NavHandler implements EventHandler<ActionEvent>
    {
        /* Handles button pressing */
        @Override
        public void handle(ActionEvent e)
        {
            System.out.println("test");
/*
            // "Add" button pressed
            if ()
            {

            }
            // "Delete" button pressed
            else ()
            {

            }
*/
        }
    }

//    public static void main(String[] args)
//    {
//        Application.launch(args);
//    }
}


