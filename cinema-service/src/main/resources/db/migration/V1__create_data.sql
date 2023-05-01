CREATE TABLE cinema
(
    id         SERIAL PRIMARY KEY,
    cinema_uid UUID         NOT NULL,
    name       VARCHAR(255) NOT NULL,
    address    VARCHAR(255) NOT NULL
);

CREATE UNIQUE INDEX udx_cinema_cinema_uid ON cinema (cinema_uid);

INSERT INTO cinema (id, cinema_uid, name, address)
VALUES (1, '06cc4ba3-ee97-4d29-a814-c40588290d17', 'Кинотеатр Москва', 'Ереван, улица Хачатура Абовяна, 18');

CREATE TABLE film_session
(
    id           SERIAL PRIMARY KEY,
    session_uid  UUID      NOT NULL,
    film_uid     UUID      NOT NULL,
    total_seats  INT       NOT NULL,
    booked_seats INT       NOT NULL DEFAULT 0 CHECK (booked_seats < total_seats),
    date         TIMESTAMP NOT NULL,
    cinema_id    INT       NOT NULL,
    CONSTRAINT fk_film_session_cinema FOREIGN KEY (cinema_id) REFERENCES cinema (id)
);

CREATE UNIQUE INDEX udx_film_session_session_uid ON film_session (session_uid);

INSERT INTO film_session (id, session_uid, cinema_id, film_uid, date, total_seats, booked_seats)
VALUES (1, '5a8f19d4-aafa-4d2c-a6f1-12c74556b652', 1, '049161bb-badd-4fa8-9d90-87c9a82b0668', '2024-01-01 08:00:00', 5000, 0);
