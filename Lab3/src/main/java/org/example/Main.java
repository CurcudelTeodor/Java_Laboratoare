package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args)
    {
        List<Node> nodeList=new ArrayList<>();
        nodeList.add(new Person("Teo"));
        nodeList.add(new Person("Stefan"));
        nodeList.add(new Person("Iustin"));
        nodeList.add(new Person("Hrebe"));
        nodeList.add(new Company("Amazon"));

        for (Node element:nodeList)
        {
            System.out.println(element.getName());
        }

        Person p1=new Person("Teo");
        Person p2=new Person("Teo");

        System.out.println(p1.getName());
        System.out.println(p2.getName());

        if(p1.compareTo(p2)==0)
        {
            System.out.println("Numele sunt egale");
        }
        else
            System.out.println("Nume diferite!");

        System.out.println(new Person("Teo").compareTo(new Person("ztefan")));

    }
}