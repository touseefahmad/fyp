package com.tikrosoft.resturantapp.utility;

public class UtilityClass {


    public final static boolean isValidEmail(String target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target)
                    .matches();
        }
    }
}
