package org.example;
//importing
import view.LoginForm;

import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(LoginForm::new);
    }
}