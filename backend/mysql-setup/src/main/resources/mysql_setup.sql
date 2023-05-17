CREATE TABLE `User`(
    `id` VARCHAR(255) NOT NULL,
    `username` VARCHAR(255) NOT NULL,
    `password` VARCHAR(255) NOT NULL
);
ALTER TABLE
    `User` ADD PRIMARY KEY(`id`);
CREATE TABLE `Place`(
    `id` VARCHAR(255) NOT NULL,
    `course` VARCHAR(255) NOT NULL
);
ALTER TABLE
    `Place` ADD PRIMARY KEY(`id`);
CREATE TABLE `City`(
    `id` VARCHAR(255) NOT NULL,
    `name` VARCHAR(255) NOT NULL
);
ALTER TABLE
    `City` ADD PRIMARY KEY(`id`);
CREATE TABLE `Availability`(
    `id` VARCHAR(255) NOT NULL,
    `distance` VARCHAR(255) NOT NULL,
    `place` VARCHAR(255) NOT NULL
);
ALTER TABLE
    `Availability` ADD PRIMARY KEY(`id`);
CREATE TABLE `Route`(
    `id` VARCHAR(255) NOT NULL,
    `name` VARCHAR(255) NOT NULL
);
ALTER TABLE
    `Route` ADD PRIMARY KEY(`id`);
CREATE TABLE `Distance`(
    `id` VARCHAR(255) NOT NULL,
    `startStop` VARCHAR(255) NOT NULL,
    `endStop` VARCHAR(255) NOT NULL
);
ALTER TABLE
    `Distance` ADD PRIMARY KEY(`id`);
CREATE TABLE `Bus`(
    `id` VARCHAR(255) NOT NULL,
    `type` ENUM('Long', 'Short') NOT NULL DEFAULT 'Long'
);
ALTER TABLE
    `Bus` ADD PRIMARY KEY(`id`);
CREATE TABLE `Ticket`(
    `id` VARCHAR(255) NOT NULL,
    `user` VARCHAR(255) NOT NULL,
    `price` DECIMAL(8, 2) NOT NULL,
    `course` VARCHAR(255) NOT NULL,
    `distance` VARCHAR(255) NOT NULL
);
ALTER TABLE
    `Ticket` ADD PRIMARY KEY(`id`);
CREATE TABLE `Course`(
    `id` VARCHAR(255) NOT NULL,
    `courseDate` DATETIME NOT NULL,
    `bus` VARCHAR(255) NOT NULL,
    `route` VARCHAR(255) NOT NULL
);
ALTER TABLE
    `Course` ADD PRIMARY KEY(`id`);
CREATE TABLE `ReservedPlaces`(
    `id` VARCHAR(255) NOT NULL,
    `ticket` VARCHAR(255) NOT NULL,
    `place` VARCHAR(255) NOT NULL
);
ALTER TABLE
    `ReservedPlaces` ADD PRIMARY KEY(`id`);
CREATE TABLE `Stop`(
    `id` VARCHAR(255) NOT NULL,
    `travellingTimeFromStart` INT NOT NULL,
    `route` VARCHAR(255) NOT NULL,
    `city` VARCHAR(255) NOT NULL
);
ALTER TABLE
    `Stop` ADD PRIMARY KEY(`id`);
ALTER TABLE
    `ReservedPlaces` ADD CONSTRAINT `reservedplaces_place_foreign` FOREIGN KEY(`place`) REFERENCES `Place`(`id`);
ALTER TABLE
    `Stop` ADD CONSTRAINT `stop_route_foreign` FOREIGN KEY(`route`) REFERENCES `Route`(`id`);
ALTER TABLE
    `Distance` ADD CONSTRAINT `distance_startstop_foreign` FOREIGN KEY(`startStop`) REFERENCES `Stop`(`id`);
ALTER TABLE
    `ReservedPlaces` ADD CONSTRAINT `reservedplaces_ticket_foreign` FOREIGN KEY(`ticket`) REFERENCES `Ticket`(`id`);
ALTER TABLE
    `Ticket` ADD CONSTRAINT `ticket_user_foreign` FOREIGN KEY(`user`) REFERENCES `User`(`id`);
ALTER TABLE
    `Course` ADD CONSTRAINT `course_route_foreign` FOREIGN KEY(`route`) REFERENCES `Route`(`id`);
ALTER TABLE
    `Ticket` ADD CONSTRAINT `ticket_course_foreign` FOREIGN KEY(`course`) REFERENCES `Course`(`id`);
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