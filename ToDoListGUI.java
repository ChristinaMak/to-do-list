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
    private static final double HEIGHT = 400;
    private static final double INSET_SIZE = 50;
    private static final double VERT_SP = 10;
    private static final double BTN_BOX_SP = 5;
    private static final double BTN_WIDTH = 57;

    private ToDoList list = new ToDoList();

    @Override
    public void start(Stage primaryStage)
    {
        // the pane
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        
        // the text of the list
        VBox listBox = new VBox(VERT_SP);
        listBox.setAlignment(Pos.CENTER);
        listBox.setPadding(new Insets(INSET_SIZE));
        TextArea listArea = new TextArea("List area placeholder");
        listArea.setEditable(false);
        listBox.getChildren().add(listArea);

        // the hbox for add field and button
        Button addButton = new Button("Add");
        TextField addField = new TextField(); // field to let user add item to list
        HBox addBox = new HBox(BTN_BOX_SP);
        initBtnBox(addBox, addField, addButton, listBox);

        // the hbox for delete button
        Button deleteButton = new Button("Delete");
        TextField deleteField = new TextField();  // field to let user delete item from list
        HBox deleteBox = new HBox(BTN_BOX_SP);
        initBtnBox(deleteBox, deleteField, deleteButton, listBox);

//        // make the add button the default button when ENTER is pressed
//        addButton.setDefaultButton(true);

        addButton.setOnAction(new AddHandler());

        pane.add(listBox, 0, 0);

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

    private void initBtnBox(HBox hbox, TextField field, Button button, VBox vbox)
    {
        button.setMinWidth(BTN_WIDTH);
        hbox.setAlignment(Pos.CENTER_RIGHT);
        hbox.getChildren().addAll(field, button);
        //hbox.setMinWidth(vbox.getWidth() - BTN_BOX_SP - BTN_WIDTH);
        field.setMinWidth(300); //TODO tune width
        vbox.getChildren().add(hbox);
    }

//    public static void main(String[] args)
//    {
//        Application.launch(args);
//    }
}
