package org.example;

import java.util.*;

public class Network{
    private List<Node> nodes;
    private String name;

    public Network(String name){
        this.nodes = new ArrayList<>();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean existsNode(Node node){
        for(Node element : nodes) {
            if (element.getName().equals(node.getName())) {
                return true;
            }
        }
        return false;
    }

    public void addNode(Node node){
        if(existsNode(node)){
            throw new IllegalArgumentException("Nodul cu numele " + node.getName() + " deja exista in reteaua " + this.name + "!");
        }
        else
            nodes.add(node);
    }

    public int importanceNode(Node node){
        int numberOfConnections = 0;
        if(node instanceof Person){
            //System.out.println("Nodul " + node.getName() + " este o persoana");
            for (Node key : ((Person)node).getRelationship().keySet()){
                numberOfConnections = numberOfConnections+1;
            }
        }

        else if (node instanceof Company) {
            //System.out.println("Nodul " + node.getName() + " este o companie");
            for(Node key : ((Company)node).getRelationship().keySet()){
                numberOfConnections = numberOfConnections+1;
            }
        }

        return numberOfConnections;
    }

    public static int compareByImportance(Node node1, Node node2){
        //ca sa nu face importanceNode statica
        //pot face o noua retea pentru ca importanceNode se uita doar la noduri (nu are nevoie de ceva din Network)
        //informatiile de care am nevoie (relatiile intre noduri) sunt stocate sub forma unui HashMap in fiecare dintre ele.
        Network n = new Network("ceva");
        return Integer.compare(n.importanceNode(node1),n.importanceNode(node2));
    }

    public void printNodes(){
        Collections.sort(this.nodes, (node1, node2) -> Network.compareByImportance(node1,node2)); //Integer.compare(importanceNode(node2), importanceNode(node1))
        Collections.reverse(this.nodes);


        for (Node node : this.nodes){
            System.out.println(node.getName() + "(importanta " + importanceNode(node) + ")");
        }
    }

//    Comparator<Node> compareByImportance = new Comparator<Node>() {
//        @Override
//        public int compare(Node node1, Node node2) {
//            return Integer.compare(Network.importanceNode(node1),Network.importanceNode(node2));
//        }
//    };
//
//    public void printNodes() {
//        Collections.sort(nodes, compareByImportance);
//
//        Collections.reverse(nodes);
//
//        for (Node node : this.nodes) {
//            System.out.println(node.getName() + "(importanta " + importanceNode(node) + ")");
//        }
//    }
}
