DROP TABLE IF EXISTS public.artists;

CREATE TABLE artists (
    id SERIAL primary key,
    name VARCHAR(255) unique not null
);


DROP TABLE IF EXISTS public.genres;

CREATE TABLE genres (
    id SERIAL primary key,
    name VARCHAR(255) unique not null
);


DROP TABLE IF EXISTS public.albums;

CREATE TABLE IF NOT EXISTS public.albums
(
	 id SERIAL primary key,
     release_year integer not null,
     title varchar(255) not null,
     artist_id integer not null references artists(id) on delete cascade
)


-- tabelul asociativ (junction)
DROP TABLE IF EXISTS public.album_genres;

CREATE TABLE album_genres (
    album_id integer not null references albums(id) on delete cascade,
    genre_id integer not null references genres(id) on delete cascade,
    primary key (album_id, genre_id)
);