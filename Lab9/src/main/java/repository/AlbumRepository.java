package repository;

import entity.AlbumEntity;
import entity.ArtistEntity;

public class AlbumRepository extends AbstractRepository<AlbumEntity>{
    public AlbumRepository() {
        super(AlbumEntity.class);
    }
}
