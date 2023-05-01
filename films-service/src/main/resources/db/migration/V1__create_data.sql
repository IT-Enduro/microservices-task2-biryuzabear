CREATE TABLE film (
                       id SERIAL PRIMARY KEY,
                       film_uid UUID NOT NULL,
                       name VARCHAR(255) NOT NULL,
                       rating NUMERIC(8, 2) DEFAULT 10 CHECK (rating BETWEEN 0 AND 10) NOT NULL,
                       director VARCHAR(255),
                       producer VARCHAR(255),
                       genre VARCHAR(255) NOT NULL
);

INSERT INTO film (id, film_uid, name, rating, director, producer, genre) VALUES (1, '049161bb-badd-4fa8-9d90-87c9a82b0668', 'Terminator 2 Judgment day', 8.6, 'James Cameron', 'James Cameron', 'Sci-Fi');
