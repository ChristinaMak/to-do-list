package com.todolist;

/**
 * Class ToDoListGUI. Graphical user interface to interact with to do list.
 * By Christina Mak
 */
import javafx.application.*;
import javafx.scene.control.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.*;
import javafx.event.*;
import javafx.geometry.*;


public class ToDoListGUI extends Application
{
    private static final double WIDTH = 400;
    private static final double HEIGHT = 800;

    private ToDoList list = new ToDoList();

    @Override
    public void start(Stage primaryStage)
    {
        // the pane
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.BOTTOM_CENTER);

        // the text of the list
        VBox listBox = new VBox(10);
        listBox.setAlignment(Pos.CENTER);
        listBox.setPadding(new Insets(50));
//        Text listText = new Text("List text placeholder");
        TextArea listArea = new TextArea("List area placeholder");
        listArea.setEditable(false);
        listBox.getChildren().add(listArea);

        // the buttons
        VBox buttonBox = new VBox(10);
        buttonBox.setAlignment(Pos.BOTTOM_CENTER);
        buttonBox.setPadding(new Insets(50));
        Button addButton = new Button("Add");
        Button deleteButton = new Button("Delete");
        listBox.getChildren().addAll(addButton, deleteButton);

//        // make the add button the default button when ENTER is pressed
//        addButton.setDefaultButton(true);

        addButton.setOnAction(new AddHandler());

        pane.add(listBox, 0, 0);
        //pane.add(buttonBox, 5, 5);

        //pane.add(addButton, 200, 200);
        //pane.add(deleteButton, 200, 600);

        // the scene and the stage
        Scene scene = new Scene(pane, WIDTH, HEIGHT);
        primaryStage.setTitle("To Do List");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /* Inner class to handle "Add" button */
    class AddHandler implements EventHandler<ActionEvent>
    {
        /* Handles adding item when pressing "Add" button */
        @Override
        public void handle(ActionEvent e)
        {
            System.out.println("test");
            list.addItem("First item");
            list.addItem("cat item", "Cat 1");
            System.out.println(list.buildList());
        }
    }

    /* Displays the list contents in GUI */
    private void updateListText(Text listText, String listString)
    {
        listText.setText(listString);
    }

//    public static void main(String[] args)
//    {
//        Application.launch(args);
//    }
}
