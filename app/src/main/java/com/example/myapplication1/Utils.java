package com.example.myapplication1;

import java.util.Scanner;

public class Utils {

    public void console(String  info){System.out.println(info);}
    public void lineBreak(){
        System.out.println("\n---------------");
    }


    public void pressEnterToContinue(){
        try { System.in.read();}
        catch(Exception e) {}

    }

    public int fromConsoleGetInt(String prompt){
        Scanner scnr = new Scanner(System.in);
        console(prompt);
        int retVal = scnr.nextInt();
        scnr = null;
        return retVal;

    }

}
