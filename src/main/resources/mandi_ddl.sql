CREATE TABLE `grain_type` (
  `id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `license` (
  `id` int(11) NOT NULL,
  `key` varchar(45) NOT NULL,
  `member_id` int(11) NOT NULL,
  `expiry_date` datetime NOT NULL,
  `payment_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `key_UNIQUE` (`key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `member` (
  `id` int(11) NOT NULL,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `gstin` varchar(45) DEFAULT NULL,
  `pan_id` varchar(45) DEFAULT NULL,
  `created_by` varchar(45) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_by` varchar(45) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`last_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Information about member								';

CREATE TABLE `payment` (
  `id` int(11) NOT NULL,
  `amount` float NOT NULL,
  `bank` varchar(45) DEFAULT NULL,
  `info` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `sell_entries` (
  `id` int(11) NOT NULL,
  `entry_date` datetime NOT NULL,
  `grain_type_id` int(11) NOT NULL,
  `buyer_name` varchar(45) NOT NULL,
  `weight` float NOT NULL,
  `town` varchar(45) DEFAULT NULL,
  `rate_per_kg` float NOT NULL,
  `labour_charge` float DEFAULT NULL,
  `transaction_reference` varchar(45) NOT NULL,
  `gst_amount` float NOT NULL,
  `total_amount` float NOT NULL,
  `delete_flag` tinyint(4) DEFAULT '0',
  `created_by` varchar(45) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_by` varchar(45) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `buy_entries` (
  `id` int(11) NOT NULL,
  `entry_date` datetime NOT NULL,
  `grain_type_id` int(11) NOT NULL,
  `seller_name` varchar(45) NOT NULL,
  `town` varchar(45) DEFAULT NULL,
  `weight` float NOT NULL,
  `rate_per_kg` float NOT NULL,
  `labour_charge` float DEFAULT NULL,
  `total_amount` float DEFAULT NULL,
  `transaction_reference` varchar(45) NOT NULL,
  `delete_flag` tinyint(4) DEFAULT '0',
  `created_by` varchar(45) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_by` varchar(45) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE app_role (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  description varchar(255) DEFAULT NULL,
  role_name varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE app_user (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  first_name varchar(255) NOT NULL,
  last_name varchar(255) NOT NULL,
  password varchar(255) NOT NULL,
  username varchar(255) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE user_role (
  user_id bigint(20) NOT NULL,
  role_id bigint(20) NOT NULL,
  CONSTRAINT FK859n2jvi8ivhui0rl0esws6o FOREIGN KEY (user_id) REFERENCES app_user (id),
  CONSTRAINT FKa68196081fvovjhkek5m97n3y FOREIGN KEY (role_id) REFERENCES app_role (id)
);

/*INSERT INTO app_role (id, role_name, description) VALUES (1, 'STANDARD_USER', 'Standard User - Has no admin rights');
INSERT INTO app_role (id, role_name, description) VALUES (2, 'ADMIN_USER', 'Admin User - Has permission to perform admin tasks');

INSERT INTO app_user (id, first_name, last_name, password, username) VALUES (1, 'John', 'Doe', '821f498d827d4edad2ed0960408a98edceb661d9f34287ceda2962417881231a', 'john.doe');
INSERT INTO app_user (id, first_name, last_name, password, username) VALUES (2, 'Admin', 'Admin', '821f498d827d4edad2ed0960408a98edceb661d9f34287ceda2962417881231a', 'admin.admin');

INSERT INTO user_role(user_id, role_id) VALUES(1,1);
INSERT INTO user_role(user_id, role_id) VALUES(2,1);*/
