package main.java.com.utility;
import java.math.BigInteger;
import java.util.UUID;

public class Helper {

    public static String generateCode(String pattern){

        String uniqueId = UUID.randomUUID().toString().replace("-", "");
        String uniqueCode = (pattern + new BigInteger(uniqueId,16)).substring(0,9);

        return uniqueCode;
    }

}
