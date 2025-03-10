package src;
import java.util.Scanner;
public class ContactMain {

    public static void main(String[] args) {

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

        Tree.getNode(Guy);

        Tree.getClass().pri;
    }

    public static void removeContact(BinarySearchTree<Contact> Tree) {

    }

    public static void viewAllContacts(BinarySearchTree<Contact> Tree) {

    }
}