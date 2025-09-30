package main.java.com.utility;

import main.java.com.utility.exceptions.StringNotMatchException;

import java.util.regex.Pattern;

public class Validator {

    public static String numberValidator(String number) {

        String matchedNumber = "";
        try{

            if(number.matches("/^\\+?[0-10][0-9]{7,14}$/")){
                matchedNumber = number;
            }else throw new StringNotMatchException("number must be like +0627722923");

        }catch (StringNotMatchException e){
            e.printStackTrace();
        }

        return matchedNumber;
    }
}
