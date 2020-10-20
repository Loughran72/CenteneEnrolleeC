USE centene;

SELECT * FROM enrollees WHERE name = 'Irene';
SELECT dependents FROM enrollees;
SELECT dependents FROM enrollees where name = 'Irene';

SELECT * FROM enrollees;
SELECT * FROM dependents;
SELECT * FROM transactions;

drop procedure if exists `findEnrWithDeps`;
DELIMITER $$
CREATE procedure findEnrWithDeps(IN enrolleeId int(11))
BEGIN 
	SELECT enrollees.id, enrollees.name, enrollees.activation_status, enrollees.dob, enrollees.phone, dependents.id, dependents.name, dependents.dob
	FROM enrollees
    JOIN transactions ON enrollees.id = enrollee_id
    JOIN dependents ON dependents.id = dependent_id
    AND enrollees.id = enrolleeId;

END$$
DELIMITER ;

drop procedure if exists `findDependents`;
DELIMITER $$
CREATE procedure findDependents(IN enrolleeId int(11))
BEGIN 
	SELECT dependents.id, dependents.name, dependents.dob
	FROM enrollees
    JOIN transactions ON enrollees.id = enrollee_id
    JOIN dependents ON dependents.id = dependent_id
    AND enrollees.id = enrolleeId;

END$$
DELIMITER ;

drop procedure if exists `findDependent`;
DELIMITER $$
CREATE procedure findDependent(IN depId int(11))
BEGIN 
	SELECT * FROM dependents WHERE id = depId;

END$$
DELIMITER ;

drop procedure if exists `findEnrollee`;
DELIMITER $$
CREATE procedure findEnrollee(IN enrolleeId int(11))
BEGIN 
	SELECT * FROM enrollees WHERE id = enrolleeId;

END$$
DELIMITER ;

drop procedure if exists `findActiveEnrollees`;
DELIMITER $$
CREATE procedure findActiveEnrollees()
BEGIN 
	SELECT * FROM enrollees WHERE activation_status > 0;

END$$
DELIMITER ;

drop procedure if exists `findInactiveEnrollees`;
DELIMITER $$
CREATE procedure findInactiveEnrollees()
BEGIN 
	SELECT * FROM enrollees WHERE activation_status = 0;

END$$
DELIMITER ;

drop procedure if exists `addEnrollee`;
DELIMITER $$
CREATE procedure addEnrollee(IN addEnrolleeName varChar(45), IN addAS tinyInt(1), IN addDOB date, IN addPhone varChar(25) )
BEGIN 
	INSERT INTO `centene`.`enrollees` (`name`, `activation_status`, `dob`, `phone`) VALUES (addEnrolleeName, addAS, addDOB, addPhone);
   --  SELECT * FROM enrollees WHERE id = LAST_INSERT_ID();

END$$
DELIMITER ;

drop procedure if exists `modifyEnrollee`;
DELIMITER $$
CREATE procedure modifyEnrollee(IN modId int(11), modName varchar(45), modAS int(1), modDOB date, modPhone varchar(25) )
BEGIN 
	-- UPDATE `centene`.`enrollees` SET `name` = modName, `activation_status` = modAS, `dob` = modDOB, `phone` = modPhone WHERE (`id` = modId);
UPDATE centene.enrollees SET enrollees.name = modName, enrollees.activation_status = modAs, enrollees.dob = modDOB, enrollees.phone = modPhone WHERE (enrollees.id = modId);
END$$
DELIMITER ;

drop procedure if exists `removeEnrollee`;
DELIMITER $$
CREATE procedure removeEnrollee(IN removeId int(11) )
BEGIN 
	call findEnrollee(removeId);
	DELETE FROM `centene`.`enrollees` WHERE (`id` = removeId);

END$$
DELIMITER ;

drop procedure if exists `addDependentToEnrollee`;
DELIMITER $$
CREATE procedure addDependentToEnrollee(IN enrolleeId int(11), IN dependentId int(11) )
BEGIN 
	INSERT INTO `centene`.`transactions` (`enrollee_id`, `dependent_id`) VALUES (enrolleeId, dependentId);
	

END$$
DELIMITER ;

drop procedure if exists `removeDependentFromEnrollee`;
DELIMITER $$
CREATE procedure removeDependentFromEnrollee(IN depId int(11), IN enrId int(11) )
BEGIN 
	call findDependent(depId);
	DELETE FROM `centene`.`transactions` 
    WHERE (`dependent_id` = depId AND `enrollee_id` = enrId);

END$$
DELIMITER ;

drop procedure if exists `removeDependentFromAllEnrollees`;
DELIMITER $$
CREATE procedure removeDependentFromAllEnrollees(IN depId int(11) )
BEGIN 
	call findDependent(depId);
	DELETE FROM `centene`.`transactions` WHERE (`dependent_id` = depId);

END$$
DELIMITER ;

drop procedure if exists `removeDependent`;
DELIMITER $$
CREATE procedure removeDependent(IN removeId int(11) )
BEGIN 
	call findDependent(removeId);
	DELETE FROM `centene`.`dependents` WHERE (`id` = removeId);
    DELETE FROM `centene`.`transactions` WHERE (`dependent_id` = removeId);

END$$
DELIMITER ;

drop procedure if exists `modifyDependent`;
DELIMITER $$
CREATE procedure modifyDependent(IN modId int(11), modName varchar(45), modDOB date )
BEGIN 
	UPDATE `centene`.`dependents` SET `name` = modName, `dob` = modDOB WHERE (`id` = modId);
    SELECT * FROM dependents WHERE id = modId;

END$$
DELIMITER ;

drop procedure if exists `addDependent`;
DELIMITER $$
CREATE procedure addDependent(IN addDependentName varChar(45), IN addDOB date )
BEGIN 
	INSERT INTO `centene`.`dependents` (`name`, `dob`) VALUES (addDependentName, addDOB);
	call findDependent(LAST_INSERT_ID());

END$$
DELIMITER ;

SELECT * FROM enrollees WHERE enrollees.id = 15;
DELETE FROM enrollees WHERE enrollees.id = 19;
UPDATE enrollees SET enrollees.name = 'Johnny', enrollees.activation_status = 1, enrollees.dob = '2000-06-07', enrollees.phone = '214-931-5555' WHERE (enrollees.id = 15);
INSERT INTO centene.enrollees (`name`, `activation_status`, `dob`, `phone`) VALUES ("Jack", 0, '1900-09-03', '667-667-6677');


SELECT * FROM enrollees;
SELECT * FROM dependents;
SELECT * FROM transactions;

SELECT * FROM dependents WHERE ('id' = LAST_INSERT_ID());
SELECT LAST_INSERT_ID();
call findEnrWithDeps(13);
call findDependents(13);
call findDependent(1);
call findEnrollee(13);
call findActiveEnrollees();
call findInactiveEnrollees();
call addEnrollee('Jerry', 1, '2020-10-14', '555-555-5555');
call modifyEnrollee(15, 'Johnny', 1, '2000-06-07', '214-930-5555');
call removeEnrollee(14);
call addDependentToEnrollee(14, 3);
call removeDependentFromEnrollee(1, 13);
call removeDependentFromAllEnrollees(1);
call removeDependent(4);
call modifyDependent(1, 'Kyle', '1995-10-16');
call addDependent('Name', '1987-06-02');