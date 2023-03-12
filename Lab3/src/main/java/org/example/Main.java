package org.example;

import jdk.jshell.PersistentSnippet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Node> nodeList=new ArrayList<>();
        Person teo = new Programmer("Teo","10-05-2002","Python");
        Person stefan = new Designer("Stefan","05-11-2004", "Graphic");
        Person bob = new Person("Bob", "12-02-2000");
        Person farin=new Person("Farin","10-05-2002");
        Person alice=new Person("Alice","10-05-2002");

        Company amazon=new Company("Amazon");
        Company dedeman=new Company("Dedeman");
        Company gealan=new Company("Gealan");


        nodeList.add(teo);
        nodeList.add(stefan);
        nodeList.add(bob);
        nodeList.add(farin);
        nodeList.add(alice);

        nodeList.add(amazon);
        nodeList.add(dedeman);
        nodeList.add(gealan);


        bob.addRelationshipPerson(farin,"Best-friend");
        bob.addRelationshipPerson(teo,"Enemy");
        bob.addRelationshipCompany(amazon,"Tech-Lead");

        amazon.addRelationship(teo,"Manager");
        bob.printHashMap();

        System.out.println("Relatiile lui P1 adica a lui teo deci ar trb sa il am pe BOB ca inamic");
        teo.printHashMap();
        System.out.println("--------------------");
        amazon.printHashMap();
        Comparator<Node> lengthComparator = new Comparator<Node>() {
            @Override
            public int compare(Node node1, Node node2) {
                return node1.getName().length() - node2.getName().length();
            }
        };
        Collections.sort(nodeList, lengthComparator);
        for (Node element : nodeList) {
            System.out.println(element.getName());
        }
        System.out.println("----------------------");
        Collections.sort(nodeList);
        for (Node element : nodeList) {
            System.out.println(element.getName());
        }
        System.out.println("----------------------");


        Person p44=new Person("Teo","10-05-2002");
        Person p55=new Person("Teo","10-05-2002");

        System.out.println(p55.getName());
        System.out.println(p44.getName());

        if(p44.compareTo(p55)==0) {
            System.out.println("Numele sunt egale");
        }
        else
            System.out.println("Nume diferite!");

        System.out.println(new Person("Teo","10-23-1233").compareTo(new Person("ztefan","123-312-123")));


        Network facebook = new Network("facebook");
        facebook.addNode(teo);
        //facebook.addNode(teo);
        facebook.addNode(stefan);
        facebook.addNode(bob);
        facebook.addNode(farin);
        facebook.addNode(alice);

        facebook.addNode(amazon);
        facebook.addNode(dedeman);
        facebook.addNode(gealan);

        System.out.println("Bob are importanta: " + facebook.importanceNode(bob));
        System.out.println("Amazon are importanta: " + facebook.importanceNode(amazon));

        facebook.printNodes();

    }
}