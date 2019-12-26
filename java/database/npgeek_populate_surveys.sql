BEGIN TRANSACTION;
INSERT INTO survey_result (parkcode,emailaddress,state,activitylevel) VALUES ('YNP','test@test.com','OH', 'Active');
INSERT INTO survey_result (parkcode,emailaddress,state,activitylevel) VALUES ('YNP','test@test.com','OH', 'Active');
INSERT INTO survey_result (parkcode,emailaddress,state,activitylevel) VALUES ('YNP','test@test.com','OH', 'Active');
INSERT INTO survey_result (parkcode,emailaddress,state,activitylevel) VALUES ('YNP','test@test.com','OH', 'Active');
INSERT INTO survey_result (parkcode,emailaddress,state,activitylevel) VALUES ('YNP','test@test.com','OH', 'Active');
INSERT INTO survey_result (parkcode,emailaddress,state,activitylevel) VALUES ('GNP','test@test.com','OH', 'Active');
INSERT INTO survey_result (parkcode,emailaddress,state,activitylevel) VALUES ('GNP','test@test.com','OH', 'Active');
INSERT INTO survey_result (parkcode,emailaddress,state,activitylevel) VALUES ('YNP2','test@test.com','OH', 'Active');
INSERT INTO survey_result (parkcode,emailaddress,state,activitylevel) VALUES ('YNP2','test@test.com','OH', 'Active');
INSERT INTO survey_result (parkcode,emailaddress,state,activitylevel) VALUES ('YNP2','test@test.com','OH', 'Active');
INSERT INTO survey_result (parkcode,emailaddress,state,activitylevel) VALUES ('YNP2','test@test.com','OH', 'Active');
INSERT INTO survey_result (parkcode,emailaddress,state,activitylevel) VALUES ('YNP2','test@test.com','OH', 'Active');

SELECT parkname, park.parkcode, count(*) FROM survey_result
        JOIN park
        ON park.parkcode = survey_result.parkcode
        GROUP BY park.parkcode, parkname ORDER BY count DESC , park.parkcode ASC;



END TRANSACTION;