CREATE TABLE IF NOT EXISTS `Customer` (
    `id`         INTEGER  PRIMARY KEY AUTO_INCREMENT,
     `name` VARCHAR(50) NOT NULL,
     `email` VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS `Order` (
    `id`         INTEGER  PRIMARY KEY AUTO_INCREMENT,
     `name` VARCHAR(50) NOT NULL,
     `price`        INTEGER  NOT NULL,
     `customer_id`        INTEGER
);