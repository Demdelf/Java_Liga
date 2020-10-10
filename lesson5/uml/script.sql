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
                                         INDEX `fk_Student_School_idx` (`school_id` ASC) VISIBLE,
                                         CONSTRAINT `fk_Student_School`
                                             FOREIGN KEY (`school_id`)
                                                 REFERENCES `School` (`school_id`)
                                                 ON DELETE NO ACTION
                                                 ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS `Teacher` (
                                         `teacher_id` INT NOT NULL,
                                         `teacher_FIO` VARCHAR(45) NOT NULL,
                                         `teacher_age` INT,
                                         `teacher_gender` VARCHAR(45),
                                         `school_id` INT NOT NULL,
                                         PRIMARY KEY (`teacher_id`),
                                         INDEX `fk_Teacher_School1_idx` (`school_id` ASC) VISIBLE,
                                         CONSTRAINT `fk_Teacher_School1`
                                             FOREIGN KEY (`school_id`)
                                                 REFERENCES `School` (`school_id`)
                                                 ON DELETE NO ACTION
                                                 ON UPDATE NO ACTION
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
                                                     INDEX `fk_Teacher_has_Subject_Subject1_idx` (`subject_id` ASC) VISIBLE,
                                                     INDEX `fk_Teacher_has_Subject_Teacher1_idx` (`teacher_id` ASC) VISIBLE,
                                                     CONSTRAINT `fk_Teacher_has_Subject_Teacher1`
                                                         FOREIGN KEY (`teacher_id`)
                                                             REFERENCES `Teacher` (`teacher_id`)
                                                             ON DELETE NO ACTION
                                                             ON UPDATE NO ACTION,
                                                     CONSTRAINT `fk_Teacher_has_Subject_Subject1`
                                                         FOREIGN KEY (`subject_id`)
                                                             REFERENCES `Subject` (`subject_id`)
                                                             ON DELETE NO ACTION
                                                             ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS `Student_has_Subject` (
                                                     `student_id` INT NOT NULL,
                                                     `subject_id` INT NOT NULL,
                                                     PRIMARY KEY (`student_id`, `subject_id`),
                                                     INDEX `fk_Student_has_Subject_Subject1_idx` (`subject_id` ASC) VISIBLE,
                                                     INDEX `fk_Student_has_Subject_Student1_idx` (`student_id` ASC) VISIBLE,
                                                     CONSTRAINT `fk_Student_has_Subject_Student1`
                                                         FOREIGN KEY (`student_id`)
                                                             REFERENCES `Student` (`student_id`)
                                                             ON DELETE NO ACTION
                                                             ON UPDATE NO ACTION,
                                                     CONSTRAINT `fk_Student_has_Subject_Subject1`
                                                         FOREIGN KEY (`subject_id`)
                                                             REFERENCES `Subject` (`subject_id`)
                                                             ON DELETE NO ACTION
                                                             ON UPDATE NO ACTION
);