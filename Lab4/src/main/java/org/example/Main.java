package org.example;

import java.util.*;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {

        var studends = IntStream.rangeClosed(0, 3)
                .mapToObj(i -> new Student("S" + i))
                .toArray(Student[]::new);


        //studends[studends.length-1]=new Student("Teo");
       // studends[studends.length-1]=new Student("Stefan");
        studends[studends.length-1]=new Student("Andrei");

        var projects = IntStream.rangeClosed(0, 6)
                .mapToObj(i -> new Project("Project" + i))
                .toArray(Project[]::new);

        projects[projects.length-1]=new Project("Java");

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

    }
}