/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

CREATE DATABASE IF NOT EXISTS `voucher_pool` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `voucher_pool`;

CREATE TABLE IF NOT EXISTS `customer` (
  `cust_id` int NOT NULL AUTO_INCREMENT,
  `cust_name` varchar(255) NOT NULL,
  `cust_email` varchar(255) NOT NULL,
  UNIQUE KEY `cust_email` (`cust_email`),
  KEY `cust_id` (`cust_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` (`cust_id`, `cust_name`, `cust_email`) VALUES
	(2, 'Joe Smith', 'joe.smith@mail.com');
INSERT INTO `customer` (`cust_id`, `cust_name`, `cust_email`) VALUES
	(1, 'John Doe', 'john.doe@mail.com');
INSERT INTO `customer` (`cust_id`, `cust_name`, `cust_email`) VALUES
	(3, 'Sammi Tan', 'sammi.tan@mail.com');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;

CREATE TABLE IF NOT EXISTS `special_offer` (
  `offer_id` int NOT NULL AUTO_INCREMENT,
  `offer_name` varchar(255) DEFAULT NULL,
  `offer_discount` decimal(19,2) unsigned DEFAULT NULL,
  KEY `offer_id` (`offer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*!40000 ALTER TABLE `special_offer` DISABLE KEYS */;
INSERT INTO `special_offer` (`offer_id`, `offer_name`, `offer_discount`) VALUES
	(1, 'Offer1', 10.00);
INSERT INTO `special_offer` (`offer_id`, `offer_name`, `offer_discount`) VALUES
	(2, 'Offer2', 5.50);
INSERT INTO `special_offer` (`offer_id`, `offer_name`, `offer_discount`) VALUES
	(3, 'Offer3', 7.00);
/*!40000 ALTER TABLE `special_offer` ENABLE KEYS */;

CREATE TABLE IF NOT EXISTS `voucher_code` (
  `voucher_id` int NOT NULL AUTO_INCREMENT,
  `voucher_cd` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `cust_id` int NOT NULL,
  `offer_id` int NOT NULL,
  `expiry_date` date NOT NULL,
  `usage_status` int NOT NULL DEFAULT '0',
  `usage_date` timestamp NULL DEFAULT NULL,
  `creation_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  UNIQUE KEY `voucher_cd` (`voucher_cd`),
  KEY `voucher_id` (`voucher_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*!40000 ALTER TABLE `voucher_code` DISABLE KEYS */;
INSERT INTO `voucher_code` (`voucher_id`, `voucher_cd`, `cust_id`, `offer_id`, `expiry_date`, `usage_status`, `usage_date`, `creation_date`) VALUES
	(7, '6V6qABPqbh', 2, 2, '2021-03-31', 0, NULL, '2021-02-27 15:11:48');
INSERT INTO `voucher_code` (`voucher_id`, `voucher_cd`, `cust_id`, `offer_id`, `expiry_date`, `usage_status`, `usage_date`, `creation_date`) VALUES
	(8, 'D6Onm48YYA', 1, 2, '2021-03-31', 0, NULL, '2021-02-27 15:11:48');
INSERT INTO `voucher_code` (`voucher_id`, `voucher_cd`, `cust_id`, `offer_id`, `expiry_date`, `usage_status`, `usage_date`, `creation_date`) VALUES
	(11, 'LAgJgzkex2', 1, 1, '2021-03-31', 0, NULL, '2021-02-27 15:12:15');
INSERT INTO `voucher_code` (`voucher_id`, `voucher_cd`, `cust_id`, `offer_id`, `expiry_date`, `usage_status`, `usage_date`, `creation_date`) VALUES
	(13, 'PBwUWSZgt3', 3, 1, '2021-03-31', 0, NULL, '2021-02-28 14:22:58');
INSERT INTO `voucher_code` (`voucher_id`, `voucher_cd`, `cust_id`, `offer_id`, `expiry_date`, `usage_status`, `usage_date`, `creation_date`) VALUES
	(12, 'qDMoSVgEi9', 2, 1, '2021-03-31', 0, NULL, '2021-02-28 14:22:58');
INSERT INTO `voucher_code` (`voucher_id`, `voucher_cd`, `cust_id`, `offer_id`, `expiry_date`, `usage_status`, `usage_date`, `creation_date`) VALUES
	(10, 'U80r9nmi9i', 3, 2, '2021-03-31', 0, NULL, '2021-02-27 15:12:10');
/*!40000 ALTER TABLE `voucher_code` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
