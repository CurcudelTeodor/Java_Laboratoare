package org.example;

import com.github.javafaker.Faker;
import org.jgrapht.Graph;
import org.jgrapht.graph.SimpleGraph;
//import org.jgrapht.graph.DefaultEdge;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class Main {
    public static void main(String[] args) {

        Faker faker = new Faker();

        var students = IntStream.rangeClosed(0, 6)
                .mapToObj(i -> new Student(faker.name().firstName()))
                .toArray(Student[]::new);

        var projects = IntStream.rangeClosed(0, 6)
                .mapToObj(i -> new Project(faker.hobbit().location()))
                .toArray(Project[]::new);

        List<String> projects1= new LinkedList<>();
        for(int i=0; i<25; i++){
            projects1.add(faker.hobbit().location());
        }
        System.out.println("ceva" + projects1);


        for(int i = 0 ;i < projects.length;i++){
            System.out.println(projects[i]);
        }

        //projects[projects.length-1] = new Project("Java");

        /*
        List <Student> listOfStudents = new LinkedList<>();
        for (Student s : studends){
            listOfStudents.add(s);
        }
        for(Student s : listOfStudents){
            System.out.println(s.getName());
        }
        System.out.println("-------------");
        Collections.sort(listOfStudents);

        for(Student s : listOfStudents){
            System.out.println(s.getName());
        }

        System.out.println("-------");
        //TreeSet-ul le are deja ordonate
        Set <Project> listofProjects = new TreeSet<>();
        for (Project p : projects){
            listofProjects.add(p);
        }

        for (Project p : listofProjects){
            System.out.println(p.getName());
        }
        */

       //Graph<Integer, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);

        Problem p = new Problem(students, projects);

        p.printPreferinte();
        System.out.print("Student cu putine preferinte: ");
        System.out.println(p.getPickyStudents());

        System.out.println(p.greedyFirstPick());

        int sum = Arrays.asList(1, 2, 3, 4, 5).stream()
                .filter(x -> x >= 3)
                .mapToInt(Integer::intValue)
                .sum();

        System.out.println("sum esre :" +sum);


    }
}