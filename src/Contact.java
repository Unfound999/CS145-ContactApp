package src;

public class Contact implements Comparable<Contact> {
    private String fName;
    private String lName;
    private String phoneNum;
    private String address;
    private int zipCode;
    private String email;

    public Contact(String address, String email, String fName, String lName, String phoneNum, int zipCode) {
        this.address = address;
        this.email = email;
        this.fName = fName;
        this.lName = lName;
        this.phoneNum = phoneNum;
        this.zipCode = zipCode;
    }

    public Contact(String fName, String lName, String phoneNumb) {
        this.fName = fName;
        this.lName = lName;
        this.phoneNum = phoneNumb;
    }

    // assigns an integer value to the first letter of the first name of the
    // inputted object and current object
    // compares these two values and returns a value based on this comparison
    @Override
    public int compareTo(Contact inputName) {

        String chars = "abcdefghijklmnopqrstuvwxyz";
        boolean fNameEnd = false;
        boolean inputFNameEnd = false;
        int charCounter = 1;
        int fNameIndex;
        int inputFNameIndex;

        fNameIndex = chars.indexOf(Character.toString(this.fName.toLowerCase().charAt(0)));
        inputFNameIndex = chars.indexOf(Character.toString(inputName.fName.toLowerCase().charAt(0)));

        // checking if first letters are the same
        if (inputFNameIndex < fNameIndex) {
            return 1;
        } else if (inputFNameIndex > fNameIndex) {
            return -1;
        }

        // runs through all letters in both names one by one, checking if they are the
        // same or not
        while (true) {
            try {
                fNameIndex = chars.indexOf(Character.toString(this.fName.toLowerCase().charAt(charCounter)));
            } catch (StringIndexOutOfBoundsException e) {
                fNameEnd = true;
            }

            try {
                inputFNameIndex = chars.indexOf(Character.toString(inputName.fName.toLowerCase().charAt(charCounter)));
            } catch (StringIndexOutOfBoundsException e) {
                inputFNameEnd = true;
            }

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

    private int lNameCompare(Contact inputName) {

        String chars = "abcdefghijklmnopqrstuvwxyz";
        boolean lNameEnd = false;
        boolean inputLNameEnd = false;
        int charCounter = 1;
        int lNameIndex;
        int inputLNameIndex;

        lNameIndex = chars.indexOf(Character.toString(this.lName.toLowerCase().charAt(0)));
        inputLNameIndex = chars.indexOf(Character.toString(inputName.lName.toLowerCase().charAt(0)));

        // checking if first letters are the same
        if (inputLNameIndex < lNameIndex) {
            return 1;
        } else if (inputLNameIndex > lNameIndex) {
            return -1;
        }

        // runs through all letters in both names one by one, checking if they are the
        // same or not
        while (true) {
            try {
                lNameIndex = chars.indexOf(Character.toString(this.lName.toLowerCase().charAt(charCounter)));
            } catch (StringIndexOutOfBoundsException e) {
                lNameEnd = true;
            }

            try {
                inputLNameIndex = chars.indexOf(Character.toString(inputName.lName.toLowerCase().charAt(charCounter)));
            } catch (StringIndexOutOfBoundsException e) {
                inputLNameEnd = true;
            }

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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Contact) {
            Contact trueObj = (Contact) obj;
            if (this.fName == trueObj.fName && this.lName == trueObj.lName && this.phoneNum == trueObj.phoneNum) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("\"Name: %s %s, Email: %s, Phone: %s, Address: %s, Zip: %d\"", fName, lName, email, phoneNum, address, zipCode);
    }
}
