CREATE TABLE users (
                       user_id SERIAL PRIMARY KEY,
                       first_name VARCHAR(255) NOT NULL,
                       last_name VARCHAR(255) NOT NULL,
                       email VARCHAR(255) NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       user_role VARCHAR(60) CHECK (user_role IN ('USER', 'ADMIN')) NOT NULL

);


CREATE TABLE cities (
                        city_id SERIAL PRIMARY KEY,
                        name_of_city VARCHAR(255) NOT NULL,
                        cultural_heritage VARCHAR(255) NOT NULL
);


CREATE TABLE gastronomies (
                              gastronome_id SERIAL PRIMARY KEY,
                              name_of_gastronome VARCHAR(255) NOT NULL,
                              schedule VARCHAR(255),
                              city_id BIGINT REFERENCES cities(city_id),
                              type VARCHAR(255) NOT NULL
);

CREATE TABLE reservations (
                              reservation_id SERIAL PRIMARY KEY,
                              gastronome_id BIGINT REFERENCES gastronomies(gastronome_id),
                              reservation_date VARCHAR NOT NULL ,
                              phone_number VARCHAR NOT NULL,
                              number_of_people INT NOT NULL,
                              special_requests TEXT,
                              status VARCHAR(60) CHECK (status IN ('ACTIVE', 'CANCELLED', 'COMPLETED')) NOT NULL
);
CREATE TABLE trips
(
    id                  SERIAL PRIMARY KEY,
    num_of_days         INT,
    city_ids            JSONB,
    types_of_gastronome JSONB
)


CREATE TABLE trip_cities (
                             trip_id BIGINT REFERENCES trips(id),
                             city_id BIGINT,
                             PRIMARY KEY (trip_id, city_id)
);
