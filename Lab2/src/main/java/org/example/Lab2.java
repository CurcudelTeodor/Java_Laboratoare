package org.example;

public class Lab2 {
    public static void main(String[] args) {
        /*
        System.out.println("Hello world!");
        Location iasi=new Location("Iasi",LocationType.ORAS,0,0);
        Location galati=new Location("Galati", LocationType.ORAS,7,-50);
        System.out.println(iasi.toString());
        System.out.println("Coordonata X a lui " + iasi.name +" este " + iasi.getX());

        Road roadIasiGalati=new Road("Iasi-Galati",RoadType.NATIONAL,99,iasi,galati);
        System.out.printf("Lungime drum: " + roadIasiGalati.length);
        System.out.println("Limita viteza pe "+ roadIasiGalati.name+ " este "+roadIasiGalati.speedLimit);

        System.out.println(roadIasiGalati.toString());
        System.out.println(roadIasiGalati);
        */

        Location iasi = new City("Iasi", 0, 0);
        Location otopeni = new Airport("OTP", -20, -70);
        Location galati = new City("Galati", 7, -50);
        Location tecuci = new City("Tecuci", 9, -55);

        Road roadIasiGalati = new Road("Iasi-Galati", RoadType.NATIONAL, 99, iasi, galati);
        Road roadGalatiTecuci = new Road("Iasi-Tecuci", RoadType.JUDETEAN, 88, galati, tecuci);
        Road tecuciOtopeni = new Road("Tecuci-Otopeni", RoadType.EXPRES, 32.649, tecuci, otopeni);

        Problem pb = new Problem();

        pb.addLocation(iasi);
        pb.addLocation(galati);
        pb.addLocation(tecuci);
        pb.addLocation(otopeni);

        //pb.addRoad(roadIasiGalati,iasi,galati);
        pb.addRoad(roadGalatiTecuci, iasi, tecuci);
        pb.addRoad(tecuciOtopeni, tecuci, otopeni);

        // System.out.println(iasi.equals(pb.getLocations(1)));

        System.out.println(pb.toStringLocations());
        System.out.println(pb.toStringRoads());

        //Daca ceva nu respecta conditiile de valididate -> throw exception
        //isValidInstance implementata si functionala totusi
        pb.validCoord();
        if (pb.isValid == true)
            System.out.println("Instanta problemei este valida!");

        //isValidInstance
        /*
        Pentru a testa functionalitate isValidInstantce:
        comentare linii 28..33 din Problem.java si metoda validCoord
        comentare linii 36-37 din Road.java if(length<...) throw new...
        Location timisoara=new City("TIMISOARA",2,5);
        Location oradea=new City("ORADEA",4,10);
        Road roadTimisoaraOradea = new Road("Timisoara-Oradea",RoadType.AUTOSTRADA,22,timisoara,oradea);

        pb.addLocation(timisoara);
        pb.addLocation(oradea);
        pb.addRoad(roadTimisoaraOradea,timisoara,oradea);

        if(pb.isValidInstance()==true)
            System.out.println("instanta e valida!");
        else
            System.out.println("date gresite!");
         */

        Graph g = new Graph(pb.locations, pb.roads);
        g.printAdjList(g.adjList);

        System.out.println(g.canReach(pb.locations, pb.roads, "Iasi", "Galati"));
    }
}