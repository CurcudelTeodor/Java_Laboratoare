package org.example;

import java.sql.Array;
import java.util.*;
import java.util.stream.Collectors;

public class Problem {

    public Map<Student, List<Project>> preferinte = new HashMap<>();

    public Problem(Student[] students, Project[] projects){
        //consideram cazul din exemplu
        preferinte.put(students[0], Arrays.asList(projects[0],projects[1],projects[2]));
        preferinte.put(students[1], Arrays.asList(projects[0],projects[1]));
        preferinte.put(students[2], Arrays.asList(projects[0]));
    }

    public void printPreferinte() {
        System.out.println("Harta cu studenti si proiecte: ");
        for(Map.Entry<Student, List<Project>> entry :  preferinte.entrySet()){
            System.out.println(entry.getKey().getName() + " : " + entry.getValue());
        }
    }

    public List<Student> getPickyStudents(){
        double averageNumberProjects = preferinte.values().stream()
                .flatMap(List::stream)
                .count() / (double) preferinte.keySet().size();

        //System.out.println(averageNumberProjects);

        List <Student> pickyStudents = preferinte.entrySet().stream()
                .filter(entry -> entry.getValue().size() < averageNumberProjects)
                .map(entry -> entry.getKey())
                .collect(Collectors.toList());
        //Map.Entry::getKey

        return pickyStudents;
    }

    public Map<Student,Project> greedyFirstPick(){
        Map<Student, Project> planificare = new HashMap<>();

        Set<Project> vizitat = new HashSet<>();

        for(Student student : preferinte.keySet()){
            for(Project project : preferinte.get(student)){
                if(!vizitat.contains(project)){
                    //System.out.println("Sunt la studentul " + student + " si am vizitat proiectul " + project);
                    planificare.put(student,project);
                    vizitat.add(project);
                    break;
                }
            }
        }
        System.out.println("Proiectele alese: "+vizitat);
        System.out.println("Planificarea este: ");
        return planificare;
    }
}
