/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  gc
 * Created: 02.07.2017
 */

CREATE TABLE `Folders` (
	`id` int NOT NULL,
	`parent_id` int NOT NULL,
	`name` TEXT(32000) NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `Files` (
	`id` int NOT NULL,
	`folder_id` int NOT NULL,
	`name` TEXT(32000) NOT NULL,
	PRIMARY KEY (`id`)
);

ALTER TABLE `Folders` ADD CONSTRAINT `Folders_fk0` FOREIGN KEY (`parent_id`) REFERENCES `Folders`(`id`)
    ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE `Files` ADD CONSTRAINT `Files_fk0` FOREIGN KEY (`folder_id`) REFERENCES `Folders`(`id`)
    ON UPDATE CASCADE ON DELETE CASCADE;
