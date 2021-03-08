package com.example.myapplication1;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.io.InputStreamReader;

import androidx.appcompat.app.AppCompatActivity;

import android.content.*;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import com.opencsv.CSVReader;


public class MainActivity extends AppCompatActivity {
    public static String stringDescription = "no data";
    public static String stringOption1 = "no data";
    public static String stringOption2 = "no data";
    public static String stringOption3 = "no data";
    public DecisionMap perec;
    public Utils u = new Utils();
    public DecisionNode node;
    public int count = 0;
    Button button3;
    Button button2;
    Button buttonReset;

    public static String nextNode = "no data";

    //@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        run3();
    }


    public void onClickHandler(View view){

        //Code - happens when click button
        nextNode = "OptionOne";
        navigateMap(u, perec);
    }

    public void onClickHandler2(View view){

        //Code - happens when click button
        nextNode = "OptionTwo";
        navigateMap(u, perec);
    }

    public void onClickHandler3(View view){

        //Code - happens when click button
        nextNode = "OptionThree";
        navigateMap(u, perec);
    }

    public void onClickHandler4(View view){

        //Code - happens when click button
        nextNode = "no data";
        run3();
        navigateMap(u, perec);

    }

    public void onClickHandlerInstructions(View view){
        Intent intent = new Intent(MainActivity.this, FirstUIScreen.class);
        startActivity(intent);

    }


    public void run3() {
        Utils u = new Utils();
        Scanner choice = new Scanner(System.in);
        DecisionMap perec;
        try {
            perec = new DecisionMap(this);
        } catch (FileNotFoundException fe) {
            u.console("File not found");
            return;
        }

        u.lineBreak();
        node = perec.entryPoint();
        navigateMap(u, perec);

    }



    public void navigateMap(Utils u, DecisionMap perec) {

        button3 = findViewById(R.id.button3);
        button2 = findViewById(R.id.button2);
        buttonReset = findViewById(R.id.button5);
        button3.setEnabled(true);
        button2.setEnabled(true);
        buttonReset.setEnabled(true);

        try {
            stringDescription = node.getDescription();
            TextView tv = (TextView) findViewById(R.id.TextView3);
            tv.setText("The description of the node: " + node.getDescription());

        } catch (NullDataException ce) {
            System.out.println(ce.getMessage());
        }


        if (nextNode == "OptionOne"){
            node = node.getYesNode();
        }
        else if (nextNode == "OptionTwo"){
            node = node.getCentreNode();
        }
        else if (nextNode == "OptionThree"){
            node = node.getNoNode();
        }

        getSupportActionBar().setElevation(0);

        TextView tv = (TextView) findViewById(R.id.TextView3);
        tv.setText(node.getDescription());

        TextView tv3 = (TextView) findViewById(R.id.textView4);
        tv3.setText("Option 1: " + node.getOption1());

        TextView tv2 = (TextView) findViewById(R.id.textView5);
        tv2.setText("Option 2: " + node.getOption2());

        TextView tv1 = (TextView) findViewById(R.id.textView6);
        tv1.setText("Option 3: " + node.getOption3());

        if (node.getOption3().equals("-")){
            button3.setEnabled(false);
            if (node.getOption2().equals("-")){
                button2.setEnabled(false);
            }
            tv1.setText("");
        }
        if (node.getDescription().contains("You're in control")) {
            buttonReset.setEnabled(false);
        }

        if (node.getQuestion().equals("-")) {
            nextNode = "OptionOne";
            stringOption1 = ("Next...");
            stringOption2 = ("Next...");
            stringOption3 = ("Next...");
            tv3.setText("Option 1: " + stringOption1);
            tv2.setText("");
            tv1.setText("");
        }
    }
}
