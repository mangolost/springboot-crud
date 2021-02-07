CREATE SCHEMA `db_test` DEFAULT CHARACTER SET utf8mb4;

USE db_test;

CREATE TABLE `t_employee` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `record_status` tinyint NOT NULL DEFAULT '1',
  `employee_no` varchar(45) NOT NULL,
  `employee_name` varchar(45) NOT NULL,
  `age` tinyint NOT NULL DEFAULT '0',
  `position` varchar(45) NOT NULL,
  `degree` varchar(45) DEFAULT NULL,
  `remark` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `employee_no_UNIQUE` (`employee_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='职员表';
