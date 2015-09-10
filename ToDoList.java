package com.todolist;

/**
 * Class ToDoList. Stores string items with categories.
 * By Christina Mak
 */
import java.util.*;

/** Class for a to-do list */
public class ToDoList
{
    //private ArrayList<String> list;  //default list, uncategorized
    private ArrayList<String> categoryNames;
    private ArrayList<ArrayList<String>> categoryList;

    private String listFull;
    private String listCurrCategory;

    /** Constructor for to-do list */
    public ToDoList()
    {
        //list = new ArrayList<String>();
        categoryNames = new ArrayList<String>();
        categoryList = new ArrayList<ArrayList<String>>();

        // TODO initialize default uncategorized list
        //categoryNames.add("UNCATEGORIZED");
        //categoryList.add(list);
    }

    /** Adds an item to the list
     *  @param item the item to add to the list
     *  @return whether the item was added successfully
     */
    public boolean addItem(String item)
    {
        //return list.add(item);
        return addItem(item, "UNCATEGORIZED");
    }

    /** Add method to add item to categories
     *  @param item the item to add to the list
     *  @param categories the categories to add the item to
     *  @return whether the item was added successfully
     */
    public boolean addItem(String item, String... categories)
    {
        for(int i = 0; i < categories.length; i++)
        {
            //convert category name to uppercase and delete whitespace
            categories[i] = categories[i].toUpperCase().trim();
            item = item.trim();

            //category already exists
            if(categoryNames.contains(categories[i]))
            {
                //find index of category
                int index = categoryNames.indexOf(categories[i]);

                //add item to category at index
                categoryList.get(index).add(item);
            }
            //category is new
            else
            {
                categoryNames.add(categories[i].toUpperCase());
                ArrayList<String> newCategory = new ArrayList<String>();
                newCategory.add(item);
                categoryList.add(newCategory);
            }

        }

        return false;
    }

    /** Deletes an item from the list if present
     *  @param item the item to delete from the list
     *  @return whether the item was deleted from the list
     */
    public boolean deleteItem(String item)
    {
        //delete trailing and leading whitespace
        item = item.trim();

        //value to keep track of whether deletion successful
        boolean deleted = false;

        for(int i = 0; i < categoryList.size(); i++)
        {
            for(int j = 0; j < categoryList.get(i).size(); j++)
            {
                if(categoryList.get(i).get(j).equalsIgnoreCase(item))
                {
                    deleted = categoryList.get(i).remove(categoryList.get(i).get(j));
                }
            }
        }

        return deleted;
    }

    /** Displays the list */
    /*
    public void viewList()
    {
        //don't display heading if no categories created
        if(categoryNames.size() == 1)
        {
            for(String string: list)
            {
                System.out.println(string);
            }
        }
        //otherwise display all categories
        else
        {
            for(String string : categoryNames)
            {
                viewList(string);
            }
        }
    }
    */
    /** Builds the list as a string
     */
    public String buildList()
    {
        StringBuilder outputString = new StringBuilder();

        //don't display heading if no categories created
        /*
        if(categoryNames.size() == 1)
        {
            for(String string: list)
            {
                //System.out.println(string);
                outputString.append(string + "\n");
            }
        }
        //otherwise display all categories
        else
        {*/
            for(String string : categoryNames)
            {
                //viewList(string);
                outputString.append(buildList(string));
            }
        //}

        // save the list
        listFull = outputString.toString();

        return listFull;

        //return listFull = outputString.toString();  TODO allowed?
    }

    /** Builds a specified categories of the list
     *  @param categories the categories to include in list
     *  @return the list as a string
     */
    public String buildList(String... categories)
    {
        StringBuilder outputString = new StringBuilder();

        for(int i = 0; i < categories.length; i++)
        {
            //convert category to uppercase and trim
            categories[i] = categories[i].toUpperCase().trim();

            //check that category exists
            if(categoryNames.contains(categories[i]))
            {
                // append heading
                //System.out.println("----" + categories[i] + "----");
                outputString.append("----" + categories[i] + "----\n");

                //find index of category
                int index = categoryNames.indexOf(categories[i]);

                //print each item in category
                ArrayList<String> currList = categoryList.get(index);
                for(String item: currList)
                {
                    //System.out.println("- " + item);
                    outputString.append("- " + item + "\n");
                }

                // line break between categories
                outputString.append("\n");
            }
            else
            {
                System.out.println("Category '" + categories[i] + "' does not exist.");
            }

        }

        // save the list
        listCurrCategory = outputString.toString();

        return listCurrCategory;
    }

    /** Returns the full list */
    public String getListFull()
    {
        return listFull;
    }

    /** Returns the list of the last requested categories */
    public String getListCurrCategory()
    {
        return listCurrCategory;
    }

    /** Display specified categories of lists
     *  @param categories the categories to display
     */
    public void viewList(String... categories)
    {
        for(int i = 0; i < categories.length; i++)
        {
            //convert category to uppercase and trim
            categories[i] = categories[i].toUpperCase().trim();

            //check that category exists
            if(categoryNames.contains(categories[i]))
            {
                //display heading
                System.out.println("----" + categories[i] + "----");

                //find index of category
                int index = categoryNames.indexOf(categories[i]);

                //print each item in category
                ArrayList<String> currList = categoryList.get(index);
                for(String item: currList)
                {
                    System.out.println("- " + item);
                }
            }
            else
            {
                System.out.println("Category '" + categories[i] + "' does not exist.");
            }
        }
    }

    /** Updates the item if present in the list
     *  @param oldItem the item to change
     *  @param newItem the item to replace with
     */
    public void updateItem(String oldItem, String newItem)
    {
        for(int i = 0; i < categoryList.size(); i++)
        {
            if(categoryList.get(i).contains(oldItem))
            {
                int index = categoryList.get(i).indexOf(oldItem);
                categoryList.get(i).set(index, newItem);
            }
        }
    }
}