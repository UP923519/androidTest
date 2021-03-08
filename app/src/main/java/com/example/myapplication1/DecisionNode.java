package com.example.myapplication1;

public class DecisionNode {

    int nodeID;
    int yesID;
    int centreID;
    int noID;
    String description;
    String question;
    String option1;
    String option2;
    String option3;

    DecisionNode yesNode;
    DecisionNode centreNode;
    DecisionNode noNode;

    DecisionNode linkedNode;

    public DecisionNode() {}

    public DecisionNode getLinkedNode() {return linkedNode;}
    public void setLinkedNode(DecisionNode linkedNode) {this.linkedNode = linkedNode;}

    public int getNodeID() {return nodeID;}
    public void setNodeID(int nodeID) {this.nodeID = nodeID;}

    public int getYesID() {return yesID;}
    public void setYesID(int yesID) {this.yesID = yesID; }

    public int getCentreID() {return centreID;}
    public void setCentreID(int centreID) {this.centreID = centreID; }

    public int getNoID() {return noID;}
    public void setNoID(int noID) {this.noID = noID;}

    public String getDescription() {return description;}
    public void setDescription(String description) {
        if(description == null){
            throw new NullDataException("description is not found: " + nodeID);
        }
        else{
            this.description = description;
        }
    }

    public String getQuestion() {return question;}
    public void setQuestion(String question) {this.question = question;}

    public String getOption1() {
        if(option1 == null){
            throw new NullDataException("option1 is not found: " + option1);
        }
        else {
            return option1;
        }
    }
    public void setOption1(String option1) {this.option1 = option1; }

    public String getOption2() {
        if (option2 == null){
            throw new NullDataException("option2 is not found: + option2");
        }
        else{
            return option2;
        }
    }
    public void setOption2(String option2) {this.option2 = option2;}

    public String getOption3() {
        if (option3 == null){
            throw new NullDataException("option3 is not found: + option3");
        }
        else{
            return option3;
        }
    }
    public void setOption3(String option3) {this.option3 = option3;}



    public DecisionNode getYesNode() {return yesNode;}
    public void setYesNode(DecisionNode yesNode) {this.yesNode = yesNode;}

    public DecisionNode getCentreNode() {return centreNode;}
    public void setCentreNode(DecisionNode centreNode) {this.centreNode = centreNode;}

    public DecisionNode getNoNode() {return noNode;}
    public void setNoNode(DecisionNode noNode) {this.noNode = noNode;}
}

