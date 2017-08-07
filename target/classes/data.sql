DELETE FROM Books;
INSERT INTO Books(id, title) VALUES (1, 'Unit Test Hibernate/JPA with in memory H2 Database');

DELETE FROM People;
INSERT INTO People(id, first_name, second_name) VALUES(1, 'Gregory', 'House');
INSERT INTO People(id, first_name, second_name) VALUES(2, 'Eric', 'Forman');
INSERT INTO People(id, first_name, second_name) VALUES(3, 'Ellison', 'Kemeron');

DELETE FROM Accounts;
INSERT INTO Accounts(id, balance,  user_id) VALUES(1, 100000, 1);
INSERT INTO Accounts(id, balance,  user_id) VALUES(2, 500000, 1);
INSERT INTO Accounts(id, balance,  user_id) VALUES(3, 10000, 2);
INSERT INTO Accounts(id, balance,  user_id) VALUES(4, 200000, 2);
INSERT INTO Accounts(id, balance,  user_id) VALUES(5, 300000, 3);

INSERT INTO Transfers(id, account_from_id, account_to_id, amount, dt) VALUES (1, 1, 2, 500, '2017-01-01 18:00:00.69');