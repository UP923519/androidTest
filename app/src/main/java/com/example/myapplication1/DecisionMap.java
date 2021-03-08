package com.example.myapplication1;

import android.os.Bundle;
import android.util.Log;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;

import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import static java.lang.Integer.*;

public class DecisionMap {
    private Context context;

    InputStream inputStream;
    BufferedReader reader;

    String[] data;
    DecisionNode head;
    DecisionNode tail;



    public DecisionMap(Context current) throws FileNotFoundException {
        this.context = current;
        inputStream = context.getResources().openRawResource(R.raw.data);
        BufferedReader dataSet = new BufferedReader(new InputStreamReader(inputStream));


        buildUnorderedList(dataSet);
        buildOrderedMap();
    }

    private void append(DecisionNode newNode) {

        if (isEmpty()) {
            this.head = newNode;
            this.tail = newNode;
            this.tail.setLinkedNode(null);

            return;
        }

        this.tail.setLinkedNode(newNode);
        this.tail = newNode;
    }


    public Scanner connectDataSet(String pathName) throws FileNotFoundException {
        File prc = new File(pathName);
        return new Scanner(prc);
    }

    public void buildUnorderedList(BufferedReader dataSet) {

        try {
            String csvLine;
            csvLine = dataSet.readLine();
            data = csvLine.split(",");

            DecisionNode node ;
            while ((csvLine = dataSet.readLine()) != null) {
                data = csvLine.split(",");
                try {
                    node = buildNode(csvLine);
                    append(node);
                } catch (Exception e) {
                    Log.e("Problem", e.toString());
                }
            }
        } catch (IOException ex) {
            throw new RuntimeException("Error in reading CSV file: " + ex);
        }

    }

    private void buildOrderedMap() {

        if (head == null) {return;}

        DecisionNode nodeLinker = head;

        while (nodeLinker != null) {

            int yesID = nodeLinker.getYesID();
            int centreID = nodeLinker.getCentreID();
            int noID = nodeLinker.getNoID();

            DecisionNode yesNode = nodeFetch(yesID);
            DecisionNode centreNode = nodeFetch(centreID);
            DecisionNode noNode = nodeFetch(noID);

            nodeLinker.setYesNode(yesNode);
            nodeLinker.setCentreNode(centreNode);
            nodeLinker.setNoNode(noNode);

            nodeLinker = nodeLinker.getLinkedNode();

        }

        cleanup();

    }

    private void cleanup(){
        if (head == null) {return;}

        DecisionNode currentNode = head;
        DecisionNode nextNode = head.getLinkedNode();

        while (nextNode != null) {

            currentNode.setLinkedNode(null);

            currentNode = nextNode;
            nextNode = currentNode.getLinkedNode();
        }
    }

    private DecisionNode buildNode(String line) {
        String[] stringArray = line.split(",");
        DecisionNode n = new DecisionNode();

        n.setNodeID(valueOf(stringArray[0]));
        n.setYesID(valueOf(stringArray[1]));
        n.setCentreID(valueOf(stringArray[2]));
        n.setNoID(valueOf(stringArray[3]));

        n.setDescription(stringArray[4]);
        n.setQuestion(stringArray[5]);

        n.setOption1(stringArray[6]);
        n.setOption2(stringArray[7]);
        n.setOption3(stringArray[8]);

        return n;
    }

    public DecisionNode entryPoint() {
        return head;
    }

    private DecisionNode nodeFetch(int nodeID) {

        DecisionNode nodeLinker = head;

        while (nodeLinker != null) {
            if(nodeLinker.getNodeID() == nodeID){ break ;}
            nodeLinker = nodeLinker.getLinkedNode();
        }

        return nodeLinker;
    }

    private boolean isEmpty() {
        return this.head == null;
    }
}