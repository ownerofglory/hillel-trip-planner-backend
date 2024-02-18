CREATE TABLE t_user
(
    name       VARCHAR(256) NOT NULL,
    email      VARCHAR(256) NOT NULL,
    id         INT AUTO_INCREMENT NOT NULL,
    birth_date DATE NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (email)
);

CREATE TABLE t_hotel
(
    id           INT AUTO_INCREMENT NOT NULL,
    name         VARCHAR(256) NOT NULL,
    description  VARCHAR(256),
    phone_number VARCHAR(32),
    PRIMARY KEY (id)
);

CREATE TABLE t_hotel_room
(
    id       INT AUTO_INCREMENT NOT NULL,
    capacity INT NOT NULL,
    price    DOUBLE NOT NULL,
    hotel_id INT,
    PRIMARY KEY (id),
    FOREIGN KEY (hotel_id) REFERENCES t_hotel(id)
);

CREATE TABLE t_hotel_booking
(
    id            INT AUTO_INCREMENT NOT NULL,
    checkin_date  DATE NOT NULL,
    checkout_date DATE NOT NULL,
    user_id       INT,
    room_id       INT,
    PRIMARY KEY (id),
    FOREIGN KEY (room_id) REFERENCES t_hotel_room(id),
    FOREIGN KEY (user_id) REFERENCES t_user(id)
);

CREATE TABLE t_address
(
    id        INT AUTO_INCREMENT NOT NULL,
    country   VARCHAR(256) NOT NULL DEFAULT 'Ukraine',
    state     VARCHAR(256),
    place     VARCHAR(128) NOT NULL,
    street    VARCHAR(128) NOT NULL,
    house_num VARCHAR(32) NOT NULL,
    apartment VARCHAR(128),
    user_id   INT,
    PRIMARY KEY (id),
    UNIQUE (user_id),
    FOREIGN KEY (user_id) REFERENCES t_user(id)
);

CREATE TABLE t_guest_hotel_booking
(
    id               INT AUTO_INCREMENT NOT NULL,
    user_id          INT,
    hotel_booking_id INT,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES t_user(id),
    FOREIGN KEY (hotel_booking_id) REFERENCES t_hotel_booking(id)
);
