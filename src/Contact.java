package src;

public class Contact implements Comparable<Contact> {
    private String fName;
    private String lName;
    private String phoneNum;
    private String address;
    private int zipCode;
    private String email;

    //  Constructor for Contact object
    public Contact(String address, String email, String fName, String lName, String phoneNum, int zipCode) {
        this.address = address;
        this.email = email;
        this.fName = fName;
        this.lName = lName;
        this.phoneNum = phoneNum;
        this.zipCode = zipCode;
    }

    //  Constuctor for a comparison Contact object for the compareTo method
    public Contact(String fName, String lName, String phoneNumb) {
        this.fName = fName;
        this.lName = lName;
        this.phoneNum = phoneNumb;
    }

    /*  overrides compareTo method from Comparable class
     *  Checks, letter by letter, if two first names in Contact objects are the same 
     *  If they are the same, and both first names still have a letters, try again
     *  If one first name has less characters than the other or the names have a differing character, return a value
     *  If both first names have the same amount of the same letters, call the lNameCompare method
    */
    @Override
    public int compareTo(Contact inputName) {

        String chars = "abcdefghijklmnopqrstuvwxyz";
        boolean fNameEnd = false;
        boolean inputFNameEnd = false;
        int charCounter = 1;
        int fNameIndex;
        int inputFNameIndex;

        //  grabs value of first letter of both first names
        fNameIndex = chars.indexOf(Character.toString(this.fName.toLowerCase().charAt(0)));
        inputFNameIndex = chars.indexOf(Character.toString(inputName.fName.toLowerCase().charAt(0)));

        // checks if first letters in both first names are the same
        if (inputFNameIndex < fNameIndex) {
            return 1;
        } else if (inputFNameIndex > fNameIndex) {
            return -1;
        }

        // runs through every letter in both names until there is a differing character, a name ends, or both names have the same amount of same letters.
        while (true) {
            //  grabs next character, if there are no new characters to grab the first name has ended
            try {
                fNameIndex = chars.indexOf(Character.toString(this.fName.toLowerCase().charAt(charCounter)));
            } catch (StringIndexOutOfBoundsException e) {
                fNameEnd = true;
            }

            //  grabs next character, if there are no new characters to grab the last name has ended
            try {
                inputFNameIndex = chars.indexOf(Character.toString(inputName.fName.toLowerCase().charAt(charCounter)));
            } catch (StringIndexOutOfBoundsException e) {
                inputFNameEnd = true;
            }

            //  checks if either of the names have ended and then checks if the individual characters are the same
            //  if both first names are the same, call lNameCompare method
            if (fNameEnd && inputFNameEnd) {
                return lNameCompare(inputName);
            } else if (inputFNameEnd) {
                return 1;
            } else if (fNameEnd) {
                return -1;
            } else {
                if (inputFNameIndex > fNameIndex) {
                    return 1;
                } else if (inputFNameIndex < fNameIndex) {
                    return -1;
                }
            }
            charCounter++;
        }
    }

    /*  Checks, letter by letter, if two last names in Contact objects are the same 
     *  If they are the same, and both last names still have a letters, try again
     *  If one last name has less characters than the other or the names have a differing character, return a value
     *  If both  lastnames have the same amount of the same letters, return a value of 0
    */
    private int lNameCompare(Contact inputName) {

        String chars = "abcdefghijklmnopqrstuvwxyz";
        boolean lNameEnd = false;
        boolean inputLNameEnd = false;
        int charCounter = 1;
        int lNameIndex;
        int inputLNameIndex;

        //  grabs value of first letter of both first names
        lNameIndex = chars.indexOf(Character.toString(this.lName.toLowerCase().charAt(0)));
        inputLNameIndex = chars.indexOf(Character.toString(inputName.lName.toLowerCase().charAt(0)));

        // checks if first letters in both first names are the same
        if (inputLNameIndex < lNameIndex) {
            return 1;
        } else if (inputLNameIndex > lNameIndex) {
            return -1;
        }

        // runs through every letter in both names until there is a differing character, a name ends, or both names have the same amount of same letters.
        while (true) {
            //  grabs next character, if there are no new characters to grab the first name has ended
            try {
                lNameIndex = chars.indexOf(Character.toString(this.lName.toLowerCase().charAt(charCounter)));
            } catch (StringIndexOutOfBoundsException e) {
                lNameEnd = true;
            }

            //  grabs next character, if there are no new characters to grab the last name has ended
            try {
                inputLNameIndex = chars.indexOf(Character.toString(inputName.lName.toLowerCase().charAt(charCounter)));
            } catch (StringIndexOutOfBoundsException e) {
                inputLNameEnd = true;
            }

            //  checks if either of the names have ended and then checks if the individual characters are the sam
            //  if both last names are the same, return a 0 value
            if (lNameEnd && inputLNameEnd) {
                return 0;
            } else if (inputLNameEnd) {
                return 1;
            } else if (lNameEnd) {
                return -1;
            } else {
                if (inputLNameIndex > lNameIndex) {
                    return 1;
                } else if (inputLNameIndex < lNameIndex) {
                    return -1;
                }
            }
            charCounter++;
        }
    }

    /*  overrides equals method
     *  checks if both objects are the same object and if so, return true
     *  checks if inputted object is a Contact object and if so
     *  checks if both objects contain the same first name, last name, and phonenumber
     */ 
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        //  checks if object is of type Contact and if so
        //  checks if first name, last name, and phone numbers between the two objects are the same
        if (obj instanceof Contact) {
            Contact trueObj = (Contact) obj;
            if (this.fName.equals(trueObj.fName) && this.lName.equals(trueObj.lName) && this.phoneNum.equals(trueObj.phoneNum)) {
                return true;
            }
        }
        return false;
    }

    //  overrides toString method
    //  prints out all data within Contact object
    @Override
    public String toString() {
        return String.format("\"Name: %s %s, Email: %s, Phone: %s, Address: %s, Zip: %d\"", fName, lName, email, phoneNum, address, zipCode);
    }
}
