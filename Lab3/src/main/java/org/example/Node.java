package org.example;

import java.util.Comparator;

public interface Node extends Comparable<Node> {
    String getName();

    default double getWeight() {
        return 0.0;
    }

}
