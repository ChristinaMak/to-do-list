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
    private static final double CAT_FIELD_W = 90;

    private ToDoList list = new ToDoList();
    private TextArea listArea;
    private TextField addField;
    private TextField catAddField;
    private TextField deleteField;
    private TextField catDeleteField;

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
        listArea = new TextArea("List area placeholder");

        listArea.setEditable(false);
        listBox.getChildren().add(listArea);

        // labels for text fields
        HBox labelBox = new HBox(47);
        Label catLabel = new Label("Category");
        Label itemLabel = new Label("Item");
        labelBox.getChildren().addAll(catLabel, itemLabel);
        listBox.getChildren().add(labelBox);

        // the hbox for add field and button
        Button addButton = new Button("Add");
        addField = new TextField(); // field to let user add item to list
        catAddField = new TextField();
        HBox addBox = new HBox(BTN_BOX_SP);
        initBtnBox(addBox, addField, catAddField, addButton, listBox);

        // the hbox for delete field and button
        Button deleteButton = new Button("Delete");
        deleteField = new TextField();  // field to let user delete item from list
        catDeleteField = new TextField();
        HBox deleteBox = new HBox(BTN_BOX_SP);
        initBtnBox(deleteBox, deleteField, catDeleteField, deleteButton, listBox);

        // handle request to add to list
        addButton.setOnAction(new AddHandler());
        addField.setOnAction(new AddHandler());
        catAddField.setOnAction(new AddHandler());

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
            String category = catAddField.getCharacters().toString();
            String newItem = addField.getCharacters().toString();
            //System.out.println("test");

            // a category and item specified
            if (!(category.equals("")) && !(newItem.equals("")))
            {
                list.addItem(newItem, category);
            }
            // an item but no category specified
            else if (category.equals("") && !(newItem.equals("")))
            {
                System.out.println("here");
                list.addItem(newItem, "UNCATEGORIZED"); //TODO
            }

            //System.out.println(list.buildList());
            String listString = list.buildList();
            updateListArea(listArea, listString);
            System.out.println(listString);
        }
    }

    /* Displays the list contents in GUI */
    private void updateListArea(TextArea listArea, String listString)
    {
        listArea.setText(listString);
    }

    private void initBtnBox(HBox hbox, TextField field, TextField catField, Button button, VBox vbox)
    {
        button.setMinWidth(BTN_WIDTH);
        hbox.setAlignment(Pos.CENTER_RIGHT);
        hbox.getChildren().addAll(catField, field, button);
        //hbox.setMinWidth(vbox.getWidth() - BTN_BOX_SP - BTN_WIDTH);
        catField.setMaxWidth(CAT_FIELD_W);
        //field.setMinWidth(300); //TODO tune width, resizing window
        vbox.getChildren().add(hbox);
    }

//    public static void main(String[] args)
//    {
//        Application.launch(args);
//    }
}
