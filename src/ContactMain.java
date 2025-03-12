package src;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ContactMain {

    //Main method uses a menu to handle each option for adding, viewing, removing, viewing all, and quitting. This is done through
    //a switch case inside of a do while loop.
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        BinarySearchTree<Contact> Tree = new BinarySearchTree<>();
        boolean running = true;
        do {
            System.out.print("Please enter which option you would like!:");
            System.out.print("Options include: \"AC\" to add a contact\n\"VC\" to view a contact\n\"RC\" to remove a contact\n\"VAC\" to view all contacts\n\"Q\" to quit!\n");
            String answer = input.nextLine();
            switch(answer) {
                case "AC": addContactMain(Tree); break;
                case "VC": viewContact(Tree); break;
                case "RC": removeContact(Tree); break;
                case "VAC": viewAllContacts(Tree); break;
                case "Q": running = false; break;
                default: System.out.println("This is not a valid option!"); break;
            }
        } while (running);
    }

    //addContactMain is a method that asks the user for key information for a contact. Then it creates a contact object with
    //all provided data and then adds that contact object to the tree with the add method.
    public static void addContactMain(BinarySearchTree<Contact> Tree) {
        Scanner input = new Scanner(System.in);

        System.out.print("Please provide the first name of the contact:");
        String fName = input.nextLine();

        System.out.print("Please provide the last name of the contact:");
        String lName = input.nextLine();

        System.out.print("Please provide the email of the contact:");
        String email = input.nextLine();

        int zip;
        while(true)
            try {
                System.out.print("Please provide the zip code of the contact:");
                zip = input.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.print("Sorry this is an invalid zip code!");
        }   

        System.out.print("Please provide the phone number of the contact:");
        String pNum = input.nextLine();

        System.out.print("Please provide the address of the contact:");
        String address = input.nextLine();

        Contact person = new Contact(address, email, fName, lName, pNum, zip);
        
        try {
            Tree.add(person);
            System.out.print("Contact Added!");
        } catch (DuplicateNodeException e) {
            System.out.println("That contact already exists.");
        }
    }

    //ViewContact is a method that asks a person for identifying information about a person. Then it instantiates a contact object
    //with that info and it gets that person using the tree class with the get method. Lastly it shows the person to the user.
    public static void viewContact(BinarySearchTree<Contact> Tree) {
        Scanner input = new Scanner(System.in);

        System.out.print("Please provide the first name of the contact:");
        String fName = input.nextLine();

        System.out.print("Please provide the last name of the contact:");
        String lName = input.nextLine();

        System.out.print("Please provide the phone number of the contact:");
        String pNum = input.nextLine();

        Contact person = new Contact(fName, lName, pNum);
        try {
            person = Tree.get(person);
            System.out.println(person);
        } catch (NodeNotFoundException e) {
            System.err.println("That person doesn't exist");
        } 
    }

    //removeContact is a method that asks the user for identifying information and then uses that information to create a
    //contact object. That contact object is then used to remove the person using the tree class with the remove method.
    public static void removeContact(BinarySearchTree<Contact> Tree) {
        Scanner input = new Scanner(System.in);

        System.out.print("Please provide the first name of the contact:");
        String fName = input.nextLine();

        System.out.print("Please provide the last name of the contact:");
        String lName = input.nextLine();

        System.out.print("Please provide the phone number of the contact:");
        String pNum = input.nextLine();

        Contact person = new Contact(fName, lName, pNum);
        try {
            Tree.remove(person);
            System.out.print("Contact Removed!");
        } catch (NodeNotFoundException e) {
            System.err.println("That person doesn't exist");
        } 
    }

    //viewAllContacts is a method that creates a arraylist through the getAllInOrder method, then it loops through a for loop which
    //prints out the every contacts information in the arraylist.
    public static void viewAllContacts(BinarySearchTree<Contact> Tree) {

        ArrayList<Contact> allPeople = Tree.getAllInOrder();

        for (Contact person : allPeople) {
            System.out.println(person);
        }
    }
}