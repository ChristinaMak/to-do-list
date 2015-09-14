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
    private static final double BTN_WIDTH = 61;
    private static final double CAT_FIELD_W = 90;

    private ToDoList list = new ToDoList();
    private TextArea listArea;
    private TextField addField;
    private TextField catAddField;
    private TextField deleteField;
    private TextField oldField;
    private TextField newField;

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
        catAddField.setMaxWidth(CAT_FIELD_W);
        initBtnBox(addBox, addButton, listBox, catAddField, addField);

        // the hbox for delete field and button
        Button deleteButton = new Button("Delete");
        deleteField = new TextField();  // field to let user delete item from list
        HBox deleteBox = new HBox(BTN_BOX_SP);
        deleteField.setMinWidth(250);  //TODO bindings
        //System.out.println(listArea.getWidth());
        initBtnBox(deleteBox, deleteButton, listBox, deleteField);

        // the hbox for the update fields and button
        Button updateButton = new Button("Update");
        oldField = new TextField();
        newField = new TextField();
        HBox updateBox = new HBox(BTN_BOX_SP);
        initBtnBox(updateBox, updateButton, listBox, oldField, newField);

        // handle requests to modify list
        setHandlers(addButton, new AddHandler(), addField, catAddField);
        setHandlers(deleteButton, new DeleteHandler(), deleteField);
        setHandlers(updateButton, new UpdateHandler(), newField, oldField);

        pane.add(listBox, 0, 0);

        // the scene and the stage
        Scene scene = new Scene(pane, WIDTH, HEIGHT);
        primaryStage.setTitle("To Do List");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /** Inner class to handle "Add" button */
    class AddHandler implements EventHandler<ActionEvent>
    {
        /* Handles adding item when pressing "Add" button */
        @Override
        public void handle(ActionEvent e)
        {
            // get user input in fields
            String category = catAddField.getCharacters().toString();
            String newItem = addField.getCharacters().toString();

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

            updateListArea();
        }
    }

    /** Inner class to handle "Delete" button */
    class DeleteHandler implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent e)
        {
            String toDelete = deleteField.getCharacters().toString();

            list.deleteItem(toDelete);

            updateListArea();
        }
    }

    /** Inner class to handle "Update" button */
    class UpdateHandler implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent e)
        {
            // get user inputs in fields
            String oldItem = oldField.getCharacters().toString();
            String newItem = newField.getCharacters().toString();

            // update only if both fields are filled in
            if (!oldItem.equals("") && !newItem.equals(""))
            {
                list.updateItem(oldItem, newItem);
            }

            updateListArea();
        }
    }

    /* Displays the list contents in GUI */
    private void updateListArea()
    {
        // TODO phase out listString and build in this method directly
        String listString = list.buildList();
        listArea.setText(listString);
        System.out.println(listString);
    }

    /* Sets up the hbox with fields and a button. Adds to the vbox. */
    private void initBtnBox(HBox hbox, Button button, VBox vbox, TextField... fields)
    {
        // set alignments
        button.setMinWidth(BTN_WIDTH);
        hbox.setAlignment(Pos.CENTER_RIGHT);

        // add nodes

        for (int i = 0; i < fields.length; i++)
        {
            hbox.getChildren().add(fields[i]);
        }
        hbox.getChildren().add(button);

        //hbox.setMinWidth(vbox.getWidth() - BTN_BOX_SP - BTN_WIDTH);
        //field.setMinWidth(300); //TODO tune width, resizing window
        vbox.getChildren().add(hbox);
    }

    /* Registers the handlers for the buttons and the fields */
    private void setHandlers(Button button, EventHandler<ActionEvent> handler, TextField... fields)
    {
        button.setOnAction(handler);
        for (int i = 0; i < fields.length; i++)
        {
            fields[i].setOnAction(handler);
        }
    }

//    public static void main(String[] args)
//    {
//        Application.launch(args);
//    }
}
