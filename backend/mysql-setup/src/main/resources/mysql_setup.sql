CREATE TABLE IF NOT EXISTS `User`(
    `id` VARCHAR(255) NOT NULL PRIMARY KEY,
    `username` VARCHAR(255) NOT NULL,
    `password` VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS `Place`(
    `id` VARCHAR(255) NOT NULL PRIMARY KEY,
    `course` VARCHAR(255) NOT NULL,
    `ticket` VARCHAR(255) NULL
);

CREATE TABLE IF NOT EXISTS `City`(
    `id` VARCHAR(255) NOT NULL PRIMARY KEY,
    `name` VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS `Availability`(
    `id` VARCHAR(255) NOT NULL PRIMARY KEY,
    `distance` VARCHAR(255) NOT NULL,
    `place` VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS `Route`(
    `id` VARCHAR(255) NOT NULL PRIMARY KEY,
    `name` VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS `Distance`(
    `id` VARCHAR(255) NOT NULL PRIMARY KEY,
    `startStop` VARCHAR(255) NOT NULL,
    `endStop` VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS `Bus`(
    `id` VARCHAR(255) NOT NULL PRIMARY KEY,
    `type` ENUM('Long', 'Short') NOT NULL DEFAULT 'Long'
);

CREATE TABLE IF NOT EXISTS `Ticket`(
    `id` VARCHAR(255) NOT NULL PRIMARY KEY,
    `user` VARCHAR(255) NOT NULL,
    `price` DECIMAL(8, 2) NOT NULL,
    `course` VARCHAR(255) NOT NULL,
    `distance` VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS `Course`(
    `id` VARCHAR(255) NOT NULL PRIMARY KEY,
    `courseDate` DATETIME NOT NULL,
    `bus` VARCHAR(255) NOT NULL,
    `route` VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS `Stop`(
    `id` VARCHAR(255) NOT NULL PRIMARY KEY,
    `travellingTimeFromStart` INT NOT NULL,
    `route` VARCHAR(255) NOT NULL,
    `city` VARCHAR(255) NOT NULL
);

ALTER TABLE
    `Stop` ADD CONSTRAINT `stop_route_foreign` FOREIGN KEY(`route`) REFERENCES `Route`(`id`);
ALTER TABLE
    `Distance` ADD CONSTRAINT `distance_startstop_foreign` FOREIGN KEY(`startStop`) REFERENCES `Stop`(`id`);
ALTER TABLE
    `Ticket` ADD CONSTRAINT `ticket_user_foreign` FOREIGN KEY(`user`) REFERENCES `User`(`id`);
ALTER TABLE
    `Course` ADD CONSTRAINT `course_route_foreign` FOREIGN KEY(`route`) REFERENCES `Route`(`id`);
ALTER TABLE
    `Ticket` ADD CONSTRAINT `ticket_course_foreign` FOREIGN KEY(`course`) REFERENCES `Course`(`id`);
ALTER TABLE
    `Place` ADD CONSTRAINT `place_ticket_foreign` FOREIGN KEY(`ticket`) REFERENCES `Ticket`(`id`);
ALTER TABLE
    `Course` ADD CONSTRAINT `course_bus_foreign` FOREIGN KEY(`bus`) REFERENCES `Bus`(`id`);
ALTER TABLE
    `Availability` ADD CONSTRAINT `availability_distance_foreign` FOREIGN KEY(`distance`) REFERENCES `Distance`(`id`);
ALTER TABLE
    `Availability` ADD CONSTRAINT `availability_place_foreign` FOREIGN KEY(`place`) REFERENCES `Place`(`id`);
ALTER TABLE
    `Ticket` ADD CONSTRAINT `ticket_distance_foreign` FOREIGN KEY(`distance`) REFERENCES `Distance`(`id`);
ALTER TABLE
    `Place` ADD CONSTRAINT `place_course_foreign` FOREIGN KEY(`course`) REFERENCES `Course`(`id`);
ALTER TABLE
    `Distance` ADD CONSTRAINT `distance_endstop_foreign` FOREIGN KEY(`endStop`) REFERENCES `Stop`(`id`);
ALTER TABLE
    `Stop` ADD CONSTRAINT `stop_city_foreign` FOREIGN KEY(`city`) REFERENCES `City`(`id`);