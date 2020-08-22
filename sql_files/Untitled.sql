SELECT * FROM users;
SELECT * FROM cars;
SELECT * FROM offers;
SELECT * FROM bank;

-- SETUP FOR WEDNESDAY MORNING
UPDATE offers SET o_status = 1;
commit;

DELETE FROM bank WHERE u_id = 4 AND c_id = 101;
commit;
DELETE FROM offers WHERE u_id = 4 AND c_id = 103;
commit;