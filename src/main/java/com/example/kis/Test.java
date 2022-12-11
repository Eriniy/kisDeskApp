package com.example.kis;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    public static void main(String[] args){
        String pass = "12JDKFKDKLS%%a d";

        System.out.println(isValidPassword(pass));

    }

    public static boolean
    isValidPassword(String password)
    {
        String regex = "^(?=.*[0-9])(?=.*[ a-z])(?=" +
                ".*[!@#$%^])(?=\\S+$).{8,30}$";
        Pattern p = Pattern.compile(regex);
        if (password == null) {
            return false;
        }
        Matcher m = p.matcher(password);
        return m.matches();
    }
}
