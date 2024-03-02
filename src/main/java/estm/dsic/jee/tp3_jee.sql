CREATE SCHEMA tp3_jee;
use tp3_jee;
CREATE TABLE `user` (
  `id` INT AUTO_INCREMENT,
  `name` varchar(20),
  `email` varchar(20),
  `password` varchar(20),
  isadmin boolean,
  isverified boolean,
  PRIMARY KEY (`id`)
);
CREATE TABLE note (
    id INT PRIMARY KEY AUTO_INCREMENT,
    subject VARCHAR(255),
    body TEXT,
    id_user INT,
    date_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_user) REFERENCES user(id)  
);
