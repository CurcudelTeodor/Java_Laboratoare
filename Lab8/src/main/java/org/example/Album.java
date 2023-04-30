package org.example;

import java.util.Arrays;

public class Album {
    private int releaseYear;
    private String title;
    private int artistID;

    private int[] genreIds;

    public int[] getGenreIds() {
        return genreIds;
    }

    public Album(int releaseYear, String title, int artistID, int[] genreIDs) {
        this.releaseYear = releaseYear;
        this.title = title;
        this.artistID = artistID;
        this.genreIds = genreIDs;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public String getTitle() {
        return title;
    }

    public int getArtistID() {
        return artistID;
    }


}