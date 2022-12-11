package com.example.kis;

import javafx.scene.control.TextField;

public class ControllerFunc {

    public static boolean checkFieldForNumber(String tf_text){
        return tf_text.matches("-?\\d+(\\.\\d+)?");
    }
}
