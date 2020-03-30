package com.company;

import java.util.Scanner;

public class Main {
    static boolean usingRetainedMode;

    public static void main(String[] args) {
        System.out.println("Utilizati modul cu retinere a imaginilor? Introduceti 1 daca da, 0 daca nu");
        Scanner s = new Scanner(System.in);
        boolean corect = false;
        while (!corect) {
            switch (s.nextLine()) {
                case "0":
                    usingRetainedMode = false;
                    corect = true;
                    break;
                case "1":
                    usingRetainedMode = true;
                    corect = true;
                    break;
                default:
            }
        }
        new MainFrame().setVisible(true);
    }
}
