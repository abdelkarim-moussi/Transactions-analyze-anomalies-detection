package main.java.com.util;

import main.java.com.util.exceptions.StringNotMatchException;

public class Validator {

    public static String numberValidator(String number) {

        String matchedNumber = "";
        String pattern = "\\+212[0-9]{9}";
        try{

            if(number.matches(pattern)){
                matchedNumber = number;
            }else throw new StringNotMatchException("number must be like +212627722923");

        }catch (StringNotMatchException e){
            e.printStackTrace();
        }

        return matchedNumber;
    }

    public static String emailValidator(String email){

        String validatedEmail = "";
        String pattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

        try{
            if(email.matches(pattern)){
                validatedEmail = email;
            }else throw new StringNotMatchException("the provided email doesn't match the provided format ex:example@gmail.com");

        }catch (StringNotMatchException e){
            e.printStackTrace();
        }

        return validatedEmail;

    }
}
