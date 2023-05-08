package entity;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "genres", schema = "public", catalog = "JavaLab8")
@NamedQuery(name = "GenresEntity.findByName", query = "SELECT a FROM GenresEntity a WHERE a.name LIKE: nume")
@NamedQuery(name = "GenresEntity.findByID", query = "SELECT a FROM GenresEntity a WHERE a.id =: numar")

public class GenresEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "name")
    private String name;


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

        GenresEntity that = (GenresEntity) o;

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

//    public Collection<AlbumEntity> getAlbumGenresById() {
//        return albumGenresById;
//    }
//
//    public void setAlbumGenresById(Collection<AlbumEntity> albumGenresById) {
//        this.albumGenresById = albumGenresById;
//    }
}
