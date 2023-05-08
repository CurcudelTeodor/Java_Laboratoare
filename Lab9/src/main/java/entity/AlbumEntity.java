package entity;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "albums", schema = "public", catalog = "JavaLab8")
@NamedQuery(name = "AlbumEntity.findByName", query = "SELECT a FROM AlbumEntity a WHERE a.title LIKE: nume")
@NamedQuery(name = "AlbumEntity.findByID", query = "SELECT a FROM AlbumEntity a WHERE a.id =: numar")

public class AlbumEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Basic
    @Column(name = "release_year")
    private int releaseYear;

    @Basic
    @Column(name = "title")
    private String title;

    @Basic
    @Column(name = "artist_id")
    private int artistId;
    @OneToMany(mappedBy = "albumsByAlbumId")
    private Collection<AlbumGenresEntity> albumGenresById;
//    @ManyToOne
//    @JoinColumn(name = "artist_id", referencedColumnName = "id", nullable = false)
//    private ArtistEntity artistsByArtistId;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "album_genres",
            joinColumns = @JoinColumn(name = "album_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Set<GenresEntity> genres = new HashSet<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    public Set<GenresEntity> getGenres() {
        return genres;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AlbumEntity that = (AlbumEntity) o;

        if (id != that.id) return false;
        if (releaseYear != that.releaseYear) return false;
        if (artistId != that.artistId) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + releaseYear;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + artistId;
        return result;
    }

    public Collection<AlbumGenresEntity> getAlbumGenresById() {
        return albumGenresById;
    }

    public void setAlbumGenresById(Collection<AlbumGenresEntity> albumGenresById) {
        this.albumGenresById = albumGenresById;
    }

//    public ArtistEntity getArtistsByArtistId() {
//        return artistsByArtistId;
//    }
//
//    public void setArtistsByArtistId(ArtistEntity artistsByArtistId) {
//        this.artistsByArtistId = artistsByArtistId;
//    }
}
