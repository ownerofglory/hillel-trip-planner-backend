INSERT INTO t_user (name,email,birth_date) VALUES
                                                             ('John Doe','jd@mail.com','1980-01-01'),
                                                             ('Jane Doe','jane@mail.com','1982-01-01'),
                                                             ('Oleksandr Kovalenko','oleksandrkovalenko@example.com','1990-01-01'),
                                                             ('Yulia Shevchenko','yuliashevchenko@example.com','1992-05-15'),
                                                             ('Dmytro Ivanov','dmytroivanov@example.com','1988-08-20'),
                                                             ('Kateryna Petrenko','katerynapetrenko@example.com','1995-12-10'),
                                                             ('Ivan Melnyk','ivanmelnyk@example.com','1985-04-03'),
                                                             ('Sofia Pavlova','sofiapavlova@example.com','1993-07-22'),
                                                             ('Artem Zhukov','artemzhukov@example.com','1987-09-30'),
                                                             ('Olena Kryvyi','olenakryvyi@example.com','1991-11-05');
INSERT INTO t_user (name,email,birth_date) VALUES
                                                             ('Vladyslav Bondar','vladyslavbondar@example.com','1986-02-17'),
                                                             ('Natalia Vasylenko','nataliavasylenko@example.com','1994-03-28'),
                                                             ('Test1','test@tes.com','1990-01-01'),
                                                             ('Test2','test1@tes.com','1990-01-01'),
                                                             ('Test User','someMail@sdfds.com','2024-02-02'),
                                                             ('Very new user','new@sdfds.com','2024-02-02'),
                                                             ('test1111','etst@sdfs.com','1970-01-01'),
                                                             ('New2 User2','new2@sdfds.com','2024-02-06');


INSERT INTO t_address (country,state,place,street,house_num,apartment,user_id) VALUES
                                                                                                 ('Ukraine',NULL,'Kyiv','Some str.','1A','24',1),
                                                                                                 ('Ukraine',NULL,'Dnipro','Other str.','2','34',2),
                                                                                                 ('Ukraine',NULL,'Kyiv','Sample str.','12A','23322',NULL);

INSERT INTO t_hotel (name,description,phone_number) VALUES
                                                                      ('Hotel 1','Hotel 1 descr','+134356645'),
                                                                      ('Hotel 2','Hotel 2 descr','+35454345'),
                                                                      ('Hotel 3','Hotel 3 descr','+134356645'),
                                                                      ('Sunset Inn','A relaxing stay with stunning sunset views','7773941937'),
                                                                      ('Mountain View Resort','Luxury resort nestled in the mountains','6771984389'),
                                                                      ('Oceanfront Escape','Enjoy the best of beachside living','0089646573'),
                                                                      ('Urban Retreat Hotel','A perfect urban getaway with modern amenities','6768050174'),
                                                                      ('Tranquil Gardens B&B','Tranquil setting with beautiful garden views','7844852316'),
                                                                      ('Historic Manor House','Experience the charm of a historic manor','2395768402'),
                                                                      ('Seaside Haven','Your perfect seaside retreat','5520376489');
INSERT INTO t_hotel (name,description,phone_number) VALUES
                                                                      ('City Center Suites','Conveniently located in the heart of the city','1135679028'),
                                                                      ('Lakeside Lodge','Enjoy the serenity of lakeside views','6679083451'),
                                                                      ('Country Charm Inn','A quaint inn with a country feel','9988776655'),
                                                                      ('Riverside Villa','Luxurious villa overlooking the river','1234567890'),
                                                                      ('Grand Plaza Hotel','Elegance and luxury in the city center','2345678901'),
                                                                      ('Forest Retreat','Escape to the tranquility of the forest','3456789012'),
                                                                      ('Skyline Tower Suites','Stunning city views from our modern suites','4567890123'),
                                                                      ('Beachfront Oasis','Beachfront location with direct beach access','5678901234'),
                                                                      ('Royal Heritage Inn','Stay in a hotel with royal heritage','6789012345'),
                                                                      ('Island Paradise Hotel','Experience island luxury and comfort','222-abc-222');
INSERT INTO t_hotel (name,description,phone_number) VALUES
                                                                      ('Contemporary City Loft','Modern loft in a vibrant city setting','8901234567'),
                                                                      ('Meadow View B&B','Cozy bed and breakfast with meadow views','9012345678'),
                                                                      ('Cozy Corner Inn','A cozy corner for a relaxing stay','0123456789'),
                                                                      ('Some hotel','Some hotel description','+1111111'),
                                                                      ('Some hotel','Some hotel description','+1111111'),
                                                                      ('Some hotel','Some hotel description','+1111111'),
                                                                      ('Some hotel','Some hotel description','+1111111'),
                                                                      ('Some hotel','Some hotel description','+1111111'),
                                                                      ('Some hotel','Some hotel description','+1111111');


INSERT INTO t_hotel_room (capacity,price,hotel_id) VALUES
                                                                     (1,100.0,1),
                                                                     (2,150.0,1),
                                                                     (5,300.0,1),
                                                                     (2,400.0,2),
                                                                     (4,100.0,NULL),
                                                                     (4,100.0,NULL),
                                                                     (4,100.0,NULL),
                                                                     (4,100.0,NULL),
                                                                     (4,100.0,29);

INSERT INTO t_hotel_booking (checkin_date,checkout_date,user_id,room_id) VALUES
                                                                                           ('2023-12-12','2023-12-15',1,2),
                                                                                           ('2023-12-12','2023-12-15',3,3),
                                                                                           ('2023-12-13','2023-12-16',5,2),
                                                                                           ('2023-12-12','2023-12-15',1,2),
                                                                                           ('2023-12-12','2023-12-15',3,3),
                                                                                           ('2024-02-09','2024-02-23',5,1);

INSERT INTO t_guest_hotel_booking (user_id,hotel_booking_id) VALUES
                                                                               (2,1),
                                                                               (4,2),
                                                                               (5,2),
                                                                               (6,2);