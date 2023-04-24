package org.example;

import java.util.Arrays;

public class Album {
    private int id;
    private int releaseYear;
    private String title;
    private String artist;
    private int[] genreIds;

    public int getId() {
        return id;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public int[] getGenreIds() {
        return genreIds;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setGenreIds(int[] genreIds) {
        this.genreIds = genreIds;
    }
    @Override
    public String toString() {
        return "Album [id=" + id + ", releaseYear=" + releaseYear + ", title=" + title + ", artist=" + artist
                + ", genreIds=" + Arrays.toString(genreIds) + "]";
    }
}