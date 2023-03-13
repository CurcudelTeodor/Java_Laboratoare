package org.example;

public interface Node extends Comparable<Node>{

    String getName();

    default int compareTo(Node other){
        return this.getName().compareTo(other.getName());
    }
}
