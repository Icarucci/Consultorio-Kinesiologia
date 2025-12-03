package utils;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JTextField;

public class Placeholder {
    public static void setPlaceholder(JTextField textField, String placeholder){
        textField.setText(placeholder);
        textField.setForeground(Color.GRAY);
        
        textField.addFocusListener(new FocusListener(){
            @Override
            public void focusGained(FocusEvent e){
                if(textField.getText().equals(placeholder)){
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e){
                if(textField.getText().isEmpty()){
                    textField.setText(placeholder);
                    textField.setForeground(Color.GRAY);
                }
            }
        });
    }
}
