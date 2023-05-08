package repository;
import entity.ArtistEntity;
import entity.GenresEntity;

public class GenreRepository extends AbstractRepository<GenresEntity>{
    public GenreRepository() {
        super(GenresEntity.class);
    }
}
