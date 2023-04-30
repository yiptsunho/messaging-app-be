CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `avatar` varbinary(255) DEFAULT NULL,
  `email_address` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(256) NOT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `verified` bit(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `message` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `content` varchar(1000) NOT NULL,
  `date_time` datetime(6) NOT NULL,
  `group_id` bigint DEFAULT NULL,
  `is_group` bit(1) NOT NULL,
  `receiver_id` bigint DEFAULT NULL,
  `sender_id` bigint NOT NULL,
  `type` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKgvr0ylqjhbi2cvnlbr1lskyya` (`group_id`),
  KEY `FK86f0kc2mt26ifwupnivu6v8oa` (`receiver_id`),
  KEY `FKcnj2qaf5yc36v2f90jw2ipl9b` (`sender_id`),
  CONSTRAINT `FK86f0kc2mt26ifwupnivu6v8oa` FOREIGN KEY (`receiver_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKcnj2qaf5yc36v2f90jw2ipl9b` FOREIGN KEY (`sender_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKgvr0ylqjhbi2cvnlbr1lskyya` FOREIGN KEY (`group_id`) REFERENCES `chat_group` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `chat_group` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `avatar` varbinary(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `register_verification_token` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `expiry_date` datetime(6) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `user_id` bigint NOT NULL,
  `active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK829rv15sh5fkgxceh6sl85prq` (`user_id`),
  CONSTRAINT `FK829rv15sh5fkgxceh6sl85prq` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `user_to_group` (
  `user_id` bigint NOT NULL,
  `group_id` bigint NOT NULL,
  KEY `FKglyqmlqmme5vg9n25ekra7kgh` (`group_id`),
  KEY `FKmc3ldwvpe48shqdy7kn7yk31k` (`user_id`),
  CONSTRAINT `FKglyqmlqmme5vg9n25ekra7kgh` FOREIGN KEY (`group_id`) REFERENCES `chat_group` (`id`),
  CONSTRAINT `FKmc3ldwvpe48shqdy7kn7yk31k` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `contacts` (
  `id` bigint NOT NULL,
  `contact_id` bigint NOT NULL,
  KEY `FKrs3dlygg4whsg3wi9eiuh58m7` (`contact_id`),
  KEY `FKgxtg0dbxf0h4doiexsg6yfqhx` (`id`),
  CONSTRAINT `FKgxtg0dbxf0h4doiexsg6yfqhx` FOREIGN KEY (`id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKrs3dlygg4whsg3wi9eiuh58m7` FOREIGN KEY (`contact_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



