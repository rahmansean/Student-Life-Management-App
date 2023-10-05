package com.example.project22;


public class Exception {


    public boolean SignUpException(String Name, String Email, String Password, String Confirm_password,String semester,String section,String department,String id) {
        boolean empty = false;
        if (Name.isEmpty() || Email.isEmpty() || Password.isEmpty() || Confirm_password.isEmpty()|| semester.isEmpty()||section.isEmpty()||department.isEmpty()|| id.isEmpty()) {
            empty = true;
        }
        return empty;
    }



    public int signup_SpecharacterException(String Name, String Email, String Password, String Confirm_password) {

        boolean name_invalid = false;
        boolean email_invalid = false;
        int invalid = 4;
        for (int i = 0; i < Name.length(); ++i) {
            if (!((Name.charAt(i) >= 'A' && Name.charAt(i) <= 'Z') || (Name.charAt(i) >= 'a' && Name.charAt(i) <= 'z') || (Name.charAt(i) == ' '))) {

                name_invalid = true;

            }

        }


        {
            for (int i = 0; i < Email.length(); ++i) {
                if (!((Email.charAt(i) >= 'A' && Email.charAt(i) <= 'Z') || (Email.charAt(i) >= 'a' && Email.charAt(i) <= 'z') || (Email.charAt(i) >= '0' && Email.charAt(i) <= '9') || Email.charAt(i) == '@' || Email.charAt(i) == '.')) {

                    email_invalid = true;
                }
            }

        }

        if (name_invalid == true && email_invalid==true)

        {
            invalid = 1;
        }

        else if(name_invalid == true)

        {
            invalid=2;
        }
        else if (email_invalid==true)
        {
            invalid=3;
        }



        return invalid;
    }




}
