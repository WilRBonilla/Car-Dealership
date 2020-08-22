DROP SEQUENCE uid_maker;
CREATE SEQUENCE uid_maker
    MINVALUE 6
    START WITH 6
    MAXVALUE 10000000
    INCREMENT BY 1;
    


DROP TABLE users;
CREATE TABLE users(
    u_id NUMBER(10) PRIMARY KEY,
    u_name VARCHAR(50)NOT NULL UNIQUE,
    u_password VARCHAR(50)NOT NULL,
    customer NUMBER(1) DEFAULT 1
);

INSERT INTO users VALUES (uid_maker.nextval, 'William', 'password', 0);
INSERT INTO users VALUES (uid_maker.nextval, 'Tom', 'password', 1);
INSERT INTO users VALUES (uid_maker.nextval, 'Leslie', 'password', 1);
INSERT INTO users VALUES (uid_maker.nextval, 'Ron', 'password', 1);
INSERT INTO users VALUES (uid_maker.nextval, 'Ben', 'password', 1);

SELECT * FROM users;
-- =================================================================================================================
-- =================================================================================================================
DROP SEQUENCE cid_maker;
CREATE SEQUENCE cid_maker
    MINVALUE 101
    START WITH 101
    MAXVALUE 200
    INCREMENT BY 1;
    


DROP TABLE cars;
CREATE TABLE cars(
    c_id NUMBER(10)PRIMARY KEY,
    c_year NUMBER(4),
    c_make VARCHAR(10),
    c_model VARCHAR(10),
    c_msrp NUMBER(10)NOT NULL,
    c_miles NUMBER(10)
);
INSERT INTO cars VALUES(cid_maker.nextval, 2017, 'Honda', 'Civic', 15000, 30000);
INSERT INTO cars VALUES(cid_maker.nextval, 2020, 'Kia', 'Soul', 20000, 2000);
INSERT INTO cars VALUES(cid_maker.nextval, 2010, 'BMW', '3', 15000, 100000);

UPDATE cars SET c_model = 'Model 3' WHERE c_make = 'BMW';
SELECT * FROM users WHERE u_name = 'William' AND u_password = 'password';
SELECT * FROM users;
SELECT * FROM cars;

commit;
-- Removing a car from the lot also removes any offers first.
CREATE OR REPLACE PROCEDURE removeCar (uid IN NUMBER, cid IN NUMBER)
IS
BEGIN
DELETE FROM offers WHERE u_id = uid AND c_id = cid;
DELETE FROM cars where c_id = cid;
commit;
END;
-- When the salesman approves a deal, decline all others.
CREATE OR REPLACE PROCEDURE approveCar (uid IN NUMBER, cid IN NUMBER)
IS
BEGIN
UPDATE offers SET o_status = 0 WHERE u_id <> uid AND c_id = cid;
UPDATE offers SET o_status = 2 WHERE u_id = uid AND c_id = cid;
commit;
END;
-- Function to adjust for any downpayment from the customer
CREATE OR REPLACE FUNCTION adjustLoan (ovalue IN NUMBER, downpay IN NUMBER) RETURN NUMBER
IS
BEGIN
RETURN ovalue - downpay;
END;
DELETE FROM bank WHERE c_id = 103; 
-- Successfully selling a car means the bank must now get a new record copied from the offers table
-- and the car values are updated afterwards. loan amount has to be adjusted by a down payment, for which
-- we have a function.
CREATE OR REPLACE PROCEDURE sellCar (uid IN NUMBER, cid IN NUMBER, down IN NUMBER, bterm NUMBER)
IS
BEGIN
INSERT INTO bank (u_id, c_id) SELECT u_id, c_id FROM offers WHERE u_id = uid AND c_id = cid;
UPDATE bank SET bank.b_loan = adjustLoan((SELECT o_value FROM offers WHERE u_id = uid AND c_id = cid ), down)WHERE c_id = cid AND u_id = uid;
UPDATE bank SET b_year = (SELECT c_year FROM cars WHERE bank.c_id = cars.c_id),
                b_make = (SELECT c_make FROM cars WHERE bank.c_id = cars.c_id),
                b_model = (SELECT c_model FROM cars WHERE bank.c_id = cars.c_id),
                b_paid = 0,
                b_term = bterm 
                WHERE c_id = cid AND u_id = uid;
                commit;
END;
INSERT INTO bank (u_id, c_id) SELECT u_id, c_id FROM offers WHERE u_id = 1 AND c_id = 101;
select * FROM bank;
rollback;
UPDATE offers SET o_status = 1 WHERE o_status = 0;
SELECT * FROM bank;
SELECT * FROM cars;
SELECT * FROM offers;
SELECT * FROM offers WHERE c_id = 4;
INSERT INTO offers VALUES (2, 102, 1, 1);
CALL approveCar (2, 102);
CALL sellCar(2, 102, 0, 36);
DELETE FROM offers WHERE u_id = 2 AND c_id = 102;
DELETE FROM bank WHERE u_id = 2 AND c_id = 102;
DELETE FROM bank WHERE u_id = 4 AND c_id = 101;
commit;
UPDATE bank SET B_LOAN = 500, b_paid = 0 WHERE u_id = 4 AND c_id = 404;
UPDATE offers SET o_status= 1 WHERE u_id = 4 AND c_id = 101;
DELETE FROM cars WHERE c_id = 171;
-- =================================================================================================================
-- =================================================================================================================
DROP TABLE offers;
CREATE TABLE offers(
    u_id NUMBER(10),
    c_id NUMBER(10),
    o_value NUMBER(10)NOT NULL,
    o_status NUMBER(1) DEFAULT 1
);

ALTER TABLE offers ADD CONSTRAINT fk_to_users FOREIGN KEY (u_id) REFERENCES users(u_id);
ALTER TABLE offers ADD CONSTRAINT fk_to_cars FOREIGN KEY (c_id) REFERENCES cars(c_id);
ALTER TABLE offers ADD CONSTRAINT ck_to_u_c PRIMARY KEY(u_id, c_id);

INSERT INTO offers VALUES(1, 101, 14000, 1);
INSERT INTO offers VALUES(2, 101, 10000, 1);
INSERT INTO offers VALUES(3, 101, 12000, 1);
INSERT INTO offers VALUES(4, 102, 12000, 1);
SELECT * FROM users;
SELECT * FROM cars;
SELECT * FROM offers;
SELECT * FROM offers WHERE u_id = 4 AND c_id = 101;
DELETE FROM offers WHERE u_id = 4 AND c_id = 103;

commit;
-- For Employees to see all existing offers on a car
SELECT users.u_id, users.u_name, offers.c_id, offers.o_value  FROM users INNER JOIN offers ON offers.u_id = users.u_id 
    WHERE c_id = 101;
-- For Customers wanting to see all offers on their cars    
SELECT users.u_id, users.u_name, offers.c_id, offers.o_value  FROM users INNER JOIN offers ON offers.u_id = users.u_id 
    WHERE c_id = 101;
    
    
-- For all Car offers on a given car, display car details along with username.
SELECT users.u_id, users.u_name, offers.c_id, cars.c_year, cars.c_make, cars.c_model, offers.o_value, cars.c_msrp  FROM users 
    INNER JOIN offers ON offers.u_id = users.u_id 
    INNER JOIN cars ON offers.c_id = cars.c_id
    WHERE offers.c_id = 101;
    
-- For getting all offers on a single CAR
SELECT users.u_id, users.u_name, offers.c_id, cars.c_year, cars.c_make, cars.c_model, offers.o_value, cars.c_msrp  FROM users 
    LEFT JOIN offers ON offers.u_id = users.u_id 
    LEFT JOIN cars ON offers.c_id = cars.c_id
    WHERE offers.c_id = 101;

INSERT INTO offers VALUES(4, 101, 5000, 1);
-- For getting all offers by a single USER
SELECT users.u_id, users.u_name, offers.c_id, cars.c_year, cars.c_make, cars.c_model, offers.o_value, cars.c_msrp  FROM users 
    LEFT JOIN offers ON offers.u_id = users.u_id 
    LEFT JOIN cars ON offers.c_id = cars.c_id
    WHERE offers.u_id = 4;

-- Getting ONE offer for a single USER on a single CAR
SELECT users.u_name, offers.c_id, cars.c_year, cars.c_make, cars.c_model, offers.o_value, cars.c_msrp, cars.c_miles  FROM users 
    LEFT JOIN offers ON offers.u_id = users.u_id 
    LEFT JOIN cars ON offers.c_id = cars.c_id
    WHERE offers.u_id = 4 AND offers.c_id = 101;
    
-- Testing shortcuts for above
SELECT users.u_name, cars.*, offers.o_value FROM users 
    LEFT JOIN offers ON offers.u_id = users.u_id 
    LEFT JOIN cars ON offers.c_id = cars.c_id
    WHERE offers.u_id = 4 AND offers.c_id = 101;  
    
SELECT users.u_id, cars.*, offers.o_value FROM users LEFT JOIN offers ON offers.u_id = users.u_idLEFT JOIN cars ON offers.c_id = cars.c_id  WHERE offers.u_id = 4;
                            
                            commit;


-- Bank
DROP TABLE bank;
CREATE TABLE bank(
    u_id NUMBER(10),
    c_id NUMBER(10),
    b_year NUMBER(4),
    b_make VARCHAR2(50),
    b_model VARCHAR2(50),
    b_paid NUMBER(10),
    b_loan NUMBER(10),
    b_term NUMBER(2)
);
ALTER TABLE bank ADD CONSTRAINT fk_bank_to_users FOREIGN KEY (u_id) REFERENCES users(u_id);

INSERT INTO bank VALUES (4, 404, 1994, 'Toyota', 'Celica', 25000, 50000, 36);
commit;
SELECT * FROM bank;






SELECT * FROM users;
SELECT * FROM cars;
SELECT * FROM offers;
SELECT * FROM bank;
DELETE FROM cars WHERE c_model = 'Encore';
-- SETUP FOR WEDNESDAY MORNING
UPDATE offers SET o_status = 1;
commit;

DELETE FROM bank WHERE u_id = 4 AND c_id = 103;
commit;
DELETE FROM bank WHERE u_id = 2;
DELETE FROM offers WHERE u_id = 4 AND c_id = 103;
commit;