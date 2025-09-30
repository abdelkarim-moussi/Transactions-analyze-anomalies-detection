package main.java.com.utility;

import main.java.com.utility.exceptions.StringNotMatchException;

import java.util.regex.Pattern;

public class Validator {

    public static String numberValidator(String number) {

        String matchedNumber = "";
        String pattern = "/\\+212[0-9]{9}/";
        try{

            if(number.matches(pattern)){
                matchedNumber = number;
            }else throw new StringNotMatchException("number must be like +212 627722923 or 0613425226");

        }catch (StringNotMatchException e){
            e.printStackTrace();
        }

        return matchedNumber;
    }

    public static String emailValidator(String email){

        String validatedEmail = "";
        String pattern = "/[a-zA-Z0-9]{4}\\@[a-zA-Z]\\.[a-zA-Z]/g";

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
