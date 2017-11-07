INSERT INTO POSITION (id, name)
VALUES
	(1, 'Top Manager'),
	(2, 'Junior Java Developer'),
	(3, 'Middle Java Developer'),
	(4, 'Senior Java Developer'),
	(5, 'Middle Swift Developer'),
	(6, 'Junior Vue.js Developer'),
	(7, 'Oracle DBA'),
	(8, 'PosgreSQL DBA'),
	(9, 'NoSQL Guru');

INSERT INTO DEPARTMENT (id, create_date, name, parent_department_id)
VALUES
	(1, CURRENT_DATE, 'Altarix', NULL),
	(2, CURRENT_DATE, 'Backend Developing', 1),
	(3, CURRENT_DATE, 'Java Developing', 2),
	(4, CURRENT_DATE, 'DBA', 2),
	(5, CURRENT_DATE, 'Frontend Developing', 1),
	(6, CURRENT_DATE, 'Browser Developing', 5),
	(7, CURRENT_DATE, 'Mobile Developing', 5);

INSERT INTO EMPLOYEE (id, birth_day, email, employment_date, gender, last_name, first_name, middle_name, phone, is_chief, salary, department_id, position_id)
VALUES
	(1, '1990-09-29', 'boss@gmail.com', CURRENT_DATE, 'MAN', 'Иванов', 'Иван', 'Владимирович', '89277171120', 't', 1000005.0, 1, 1),
	(2, '1994-11-29', 'some@gmail.com', CURRENT_DATE, 'MAN', 'Короткевич', 'Геннадий', 'Иванович', '89277522533', 't', 100000.0, 2, 4),
	(3, '1994-11-29', 'some@gmail.com', CURRENT_DATE, 'MAN', 'Сергеев', 'Сергей', 'Сергеевич', '89277522777', 'f', 32000.0, 3, 2),
	(4, '1994-11-29', 'some@gmail.com', CURRENT_DATE, 'WOMAN', 'Петрова', 'Снежанна', 'Борисовна', '89276652212', 't', 35000.0, 3, 2),
	(5, '1992-05-29', 'some@gmail.com', CURRENT_DATE, 'MAN', 'Сидоров', 'Олег', 'Сидорович', '89270110011', 't', 30000.0, 4, 7),
	(6, '1992-05-11', 'some@gmail.com', CURRENT_DATE, 'MAN', 'Глебов', 'Глеб', 'Глебович', '88465553535', 't', 50000.0, 5, 5),
	(7, '1992-05-11', 'some@gmail.com', CURRENT_DATE, 'WOMAN', 'Ефимова', 'Наталья', 'Сергеевна', '88005553535', 't', 33000.0, 6, 6),
	(8, '1992-05-11', 'some@gmail.com', CURRENT_DATE, 'MAN', 'Денисов', 'Денис', 'Денисович', '88005553535', 't', 37000.0, 7, 5);