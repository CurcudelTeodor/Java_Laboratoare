package repository;

import entity.ArtistEntity;

public class ArtistRepository extends AbstractRepository<ArtistEntity>{
    public ArtistRepository() {
        super(ArtistEntity.class);
    }
}
