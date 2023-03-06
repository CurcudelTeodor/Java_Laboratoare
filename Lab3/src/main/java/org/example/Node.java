package org.example;

public interface Node
{
    String getName();

    default double getWeight()
    {
        return 0.0;
    }
}
