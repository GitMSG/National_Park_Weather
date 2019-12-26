BEGIN TRANSACTION;

DROP TABLE IF EXISTS user_account CASCADE;
DROP TABLE IF EXISTS user_login CASCADE;

DROP SEQUENCE IF EXISTS seq_account_number;
DROP SEQUENCE IF EXISTS seq_account_number;


CREATE SEQUENCE seq_account_number;

CREATE TABLE user_account(
        account_number int NOT NULL DEFAULT nextval('seq_account_number'),
        first_name varchar(20) NOT NULL,
        last_name varchar(20) NOT NULL,
        email_address varchar(20) NOT NULL,
        state_of_residence varchar(20) NOT NULL,
        CONSTRAINT pk_account_number PRIMARY KEY (account_number)
);

CREATE TABLE user_login(
        account_number int NOT NULL,
        username varchar(20) NOT NULL,
        password varchar(40) NOT NULL,
        salt text NOT NULL,
        CONSTRAINT pk_user_login_account_number PRIMARY KEY (account_number),
        CONSTRAINT fk_account_number FOREIGN KEY (account_number) REFERENCES user_account(account_number)
        
);
/*
INSERT INTO user_account
        (first_name, last_name, email_address, state_of_residence)
        VALUES ('Ryan', 'Okey', 'rokey@okey.com', 'OH');
        
*//*INSERT INTO user_account
        (first_name, last_name, email_address, state_of_residence)
        VALUES ('Matt', 'Goshorn', 'matt@goshorn.com', 'OH');*//*
        
INSERT INTO user_login
        (account_number, username, password, salt)
        VALUES ((SELECT account_number FROM user_account ORDER BY account_number DESC LIMIT 1), 'testaccount', 'testpass', 'testsalt'); 
        */

SELECT * FROM user_account;

SELECT * FROM user_login;


             
END TRANSACTION;