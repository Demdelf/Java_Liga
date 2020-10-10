CREATE SCHEMA IF NOT EXISTS `edu`;
USE `edu` ;

CREATE TABLE IF NOT EXISTS `School` (
    `school_id` INT NOT NULL,
    `school_title` VARCHAR(45) NOT NULL,
    `school_address` VARCHAR(45),
    PRIMARY KEY (`school_id`)
);

CREATE TABLE IF NOT EXISTS `Student` (
    `student_id` INT NOT NULL,
    `student_FIO` VARCHAR(45) NOT NULL,
    `student_age` INT,
    `student_gender` VARCHAR(45),
    `school_id` INT NOT NULL,
    PRIMARY KEY (`student_id`),
        FOREIGN KEY (`school_id`)
            REFERENCES `School` (`school_id`)
);

CREATE TABLE IF NOT EXISTS `Teacher` (
    `teacher_id` INT NOT NULL,
    `teacher_FIO` VARCHAR(45) NOT NULL,
    `teacher_age` INT,
    `teacher_gender` VARCHAR(45),
    `school_id` INT NOT NULL,
    PRIMARY KEY (`teacher_id`),
        FOREIGN KEY (`school_id`)
            REFERENCES `School` (`school_id`)
);

CREATE TABLE IF NOT EXISTS `Subject` (
    `subject_id` INT NOT NULL,
    `subject_title` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`subject_id`)
);

CREATE TABLE IF NOT EXISTS `Teacher_has_Subject` (
    `teacher_id` INT NOT NULL,
    `subject_id` INT NOT NULL,
    PRIMARY KEY (`teacher_id`, `subject_id`),
        FOREIGN KEY (`teacher_id`)
            REFERENCES `Teacher` (`teacher_id`),
        FOREIGN KEY (`subject_id`)
            REFERENCES `Subject` (`subject_id`)
);

CREATE TABLE IF NOT EXISTS `Student_has_Subject` (
    `student_id` INT NOT NULL,
    `subject_id` INT NOT NULL,
    PRIMARY KEY (`student_id`, `subject_id`),
        FOREIGN KEY (`student_id`)
            REFERENCES `Student` (`student_id`),
        FOREIGN KEY (`subject_id`)
            REFERENCES `Subject` (`subject_id`)
);