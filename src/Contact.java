package src;

public class Contact implements Comparable<Contact> {
    private String fName;
    private String lName;
    private String phoneNum;
    private String address;
    private int zipCode;
    private String email;

    @Override
    public int compareTo(Contact inputName) {

        String chars = "abcdefghijklmnopqrstuvwxyz";
        String InputNameChar =  Character.toString(inputName.fName.toLowerCase().charAt(0));

        int nameIndex = chars.indexOf(Character.toString(this.fName.toLowerCase().charAt(0)));
        int inputNameIndex = chars.indexOf(Character.toString(inputName.fName.toLowerCase().charAt(0)));

        if (inputNameIndex > nameIndex) {
            return 1;
        } else if (inputNameIndex < nameIndex) {
            return -1;
        } else {
            return 0;
        }
    }
}
