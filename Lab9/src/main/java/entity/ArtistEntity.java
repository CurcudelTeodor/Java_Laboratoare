package entity;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "artists", schema = "public", catalog = "JavaLab8")
@NamedQuery(name = "ArtistEntity.findByName", query = "SELECT a FROM ArtistEntity a WHERE a.name LIKE :nume")
@NamedQuery(name = "ArtistEntity.findByID", query = "SELECT a FROM ArtistEntity a WHERE a.id =: numar")

public class ArtistEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "name")
    private String name;
//    @OneToMany(mappedBy = "artistsByArtistId")
//    private Collection<AlbumEntity> albumsById;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArtistEntity that = (ArtistEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

//    public Collection<AlbumEntity> getAlbumsById() {
//        return albumsById;
//    }
//
//    public void setAlbumsById(Collection<AlbumEntity> albumsById) {
//        this.albumsById = albumsById;
//    }
}
