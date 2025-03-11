package src;
import java.util.ArrayList;
import java.util.Scanner;
public class ContactMain {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        BinarySearchTree<Contact> Tree = new BinarySearchTree<Contact>();
        System.out.print("Please enter which option you would like!:");
        System.out.print("Options include: \"AC\" to add a contact, \"VC\" to view a contact \"RC\" to remove a contact, \"VAC\" to view all contacts, or \"Q\" to quit!.");
        String answer = input.nextLine();
        boolean running = true;
        do {
            switch(answer) {
                case "AC": addContactMain(Tree); break;
                case "VS": viewContact(Tree); break;
                case "RC": removeContact(Tree); break;
                case "VAC": viewAllContacts(Tree); break;
                case "Q": running = false; break;
                default: System.out.println("This is not a valid option!"); break;
            }
        } while (running);
        
    }

    public static void addContactMain(BinarySearchTree<Contact> Tree) {
        Scanner input = new Scanner(System.in);

        System.out.print("Please provide the first name of the contact:");
        String fName = input.nextLine();

        System.out.print("Please provide the last name of the contact:");
        String lName = input.nextLine();

        System.out.print("Please provide the email of the contact:");
        String email = input.nextLine();

        System.out.print("Please provide the zip code of the contact:");
        int zip = input.nextInt();

        System.out.print("Please provide the phone number of the contact:");
        String pNum = input.nextLine();

        System.out.print("Please provide the address of the contact:");
        String address = input.nextLine();

        Contact Guy = new Contact(address, email, fName, lName, pNum, zip);
        Tree.add(Guy);

        System.out.print("Contact Added!");
    }

    public static void viewContact(BinarySearchTree<Contact> Tree) {
        Scanner input = new Scanner(System.in);

        System.out.print("Please provide the first name of the contact:");
        String fName = input.nextLine();

        System.out.print("Please provide the last name of the contact:");
        String lName = input.nextLine();

        System.out.print("Please provide the phone number of the contact:");
        String pNum = input.nextLine();

        Contact Guy = new Contact(fName, lName, pNum);
        Guy = Tree.get(Guy);
        System.out.println(Guy);
    }

    public static void removeContact(BinarySearchTree<Contact> Tree) {
        Scanner input = new Scanner(System.in);

        System.out.print("Please provide the first name of the contact:");
        String fName = input.nextLine();

        System.out.print("Please provide the last name of the contact:");
        String lName = input.nextLine();

        System.out.print("Please provide the phone number of the contact:");
        String pNum = input.nextLine();

        Contact Guy = new Contact(fName, lName, pNum);
        Tree.remove(Guy);
        System.out.print("Contact Removed!");
    }

    public static void viewAllContacts(BinarySearchTree<Contact> Tree) {

        ArrayList<Contact> allPeople = Tree.getAllInOrder();

        for (Contact person : allPeople) {
            System.out.println(person);
        }
    }
}