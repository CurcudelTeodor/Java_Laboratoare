package org.example;

public class Lab2 {
    public static void main(String[] args)
    {
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
        Location iasi=new Location("Iasi",LocationType.ORAS,0,0);
        Location galati=new Location("Galati", LocationType.ORAS,7,-50);
        Location tecuci=new Location("Tecuci",LocationType.ORAS,9,-55);

        Road roadIasiGalati=new Road("Iasi-Galati",RoadType.NATIONAL,99,iasi,galati);
        Road roadGalatiTecuci=new Road("Iasi-Tecuci",RoadType.JUDETEAN,88,galati,tecuci);

        Problem pb = new Problem();



        pb.addLocation(iasi);
        pb.addLocation(galati);
        pb.addLocation(tecuci);

        pb.addRoad(roadIasiGalati,iasi,galati);
        pb.addRoad(roadGalatiTecuci,iasi,tecuci);

        System.out.println(iasi.equals(pb.getLocations(1)));


        System.out.println(pb.toStringLocations());
        System.out.println(pb.toStringRoads());

    }
}