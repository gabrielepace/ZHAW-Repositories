-- ************************************** `Mark`

CREATE TABLE `Mark`
(
  `mark_id`   int NOT NULL,
  `mark`      int NOT NULL,
  `weighting` int NOT NULL,

  PRIMARY KEY (`mark_id`)
);

-- ************************************** `Enrollment`

CREATE TABLE `Enrollment`
(
  `semester`  int         NOT NULL,
  `dispensed` bit         NOT NULL,
  `name`      varchar(45) NOT NULL,
  `mark_id`   int         NOT NULL,
  `user_id`   int         NOT NULL,

  PRIMARY KEY (`mark_id`, `name`, `user_id`),
  KEY `fkIdx_57` (`name`),
  CONSTRAINT `FK_57` FOREIGN KEY `fkIdx_57` (`name`) REFERENCES `Module` (`name`),
  KEY `fkIdx_60` (`mark_id`),
  CONSTRAINT `FK_60` FOREIGN KEY `fkIdx_60` (`mark_id`) REFERENCES `Mark` (`mark_id`),
  KEY `fkIdx_63` (`user_id`),
  CONSTRAINT `FK_63` FOREIGN KEY `fkIdx_63` (`user_id`) REFERENCES `User` (`user_id`)
);

-- ************************************** `Module_Group`

CREATE TABLE `Module_Group`
(
  `module_group_id` int         NOT NULL,
  `name`            varchar(45) NOT NULL,

  PRIMARY KEY (`module_group_id`)
);

-- ************************************** `Module`

CREATE TABLE `Module`
(
  `name`            varchar(45)  NOT NULL,
  `ects`            int          NOT NULL,
  `module_group_id` int          NOT NULL,
  `description`     varchar(500) NOT NULL,
  `contraction`     varchar(10)  NOT NULL,

  PRIMARY KEY (`name`),
  KEY `fkIdx_41` (`module_group_id`),
  CONSTRAINT `FK_41` FOREIGN KEY `fkIdx_41` (`module_group_id`) REFERENCES `Module_Group` (`module_group_id`)
);

-- ************************************** `Study`

CREATE TABLE `Study`
(
  `institution_of_education` varchar(45) NOT NULL,
  `study_course`             varchar(45) NOT NULL,

  PRIMARY KEY (`institution_of_education`, `study_course`)
);

-- ************************************** `User`

CREATE TABLE `User`
(
  `user_id`                  int          NOT NULL,
  `first_name`               varchar(45)  NOT NULL,
  `last_name`                varchar(45)  NOT NULL,
  `email`                    varchar(70)  NOT NULL,
  `password`                 varchar(150) NOT NULL,
  `institution_of_education` varchar(45)  NOT NULL,
  `study_course`             varchar(45)  NOT NULL,

  PRIMARY KEY (`user_id`),
  KEY `fkIdx_32` (`institution_of_education`, `study_course`),
  CONSTRAINT `FK_32` FOREIGN KEY `fkIdx_32` (`institution_of_education`, `study_course`) REFERENCES `Study` (`institution_of_education`, `study_course`)
);
