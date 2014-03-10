-- phpMyAdmin SQL Dump
-- version 4.0.6deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Mar 10, 2014 at 02:28 AM
-- Server version: 5.5.35-0ubuntu0.13.10.2
-- PHP Version: 5.5.3-1ubuntu2.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `Flooring`
--

-- --------------------------------------------------------

--
-- Table structure for table `Orders`
--

CREATE TABLE IF NOT EXISTS `Orders` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_name` varchar(20) NOT NULL,
  `state` varchar(2) NOT NULL,
  `tax_rate` decimal(10,2) NOT NULL,
  `product_type` varchar(20) NOT NULL,
  `area` decimal(10,2) NOT NULL,
  `cost_per_sq_ft` decimal(10,2) NOT NULL,
  `labor_cost_per_sq_ft` decimal(10,2) NOT NULL,
  `labor_cost` decimal(10,2) NOT NULL,
  `material_cost` decimal(10,2) NOT NULL,
  `tax` decimal(10,2) NOT NULL,
  `total` decimal(10,2) NOT NULL,
  PRIMARY KEY (`order_id`),
  KEY `state` (`state`),
  KEY `product_type` (`product_type`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=24 ;

--
-- Dumping data for table `Orders`
--

INSERT INTO `Orders` (`order_id`, `customer_name`, `state`, `tax_rate`, `product_type`, `area`, `cost_per_sq_ft`, `labor_cost_per_sq_ft`, `labor_cost`, `material_cost`, `tax`, `total`) VALUES
(7, 'Bert', 'OH', 8.00, 'Carpet', 100.00, 4.00, 5.00, 500.00, 400.00, 72.00, 972.00),
(9, 'Cookie Monster', 'OH', 5.00, 'Carpet', 1200.00, 3.00, 4.00, 4800.00, 3600.00, 420.00, 8820.00),
(11, 'Adam', 'OH', 5.00, 'Carpet', 5000.00, 3.00, 4.00, 20000.00, 15000.00, 1750.00, 36750.00),
(12, 'Charles', 'OH', 5.00, 'Carpet', 2256.00, 3.00, 4.00, 9024.00, 6768.00, 789.60, 16581.60),
(13, 'Ethan', 'OH', 5.00, 'Carpet', 400.00, 3.00, 4.00, 1600.00, 1200.00, 140.00, 2940.00),
(14, 'James', 'OH', 6.25, 'Laminate', 300.00, 1.75, 2.10, 630.00, 525.00, 72.19, 1227.19),
(16, 'Jen', 'OH', 6.25, 'Laminate', 2500.00, 1.75, 2.10, 5250.00, 4375.00, 601.56, 10226.56),
(17, 'Mike', 'PA', 6.75, 'Laminate', 2200.00, 1.75, 2.10, 4620.00, 3850.00, 571.73, 9041.73),
(18, 'Mike', 'PA', 6.75, 'Laminate', 2200.00, 1.75, 2.10, 4620.00, 3850.00, 571.73, 9041.73),
(19, 'Jane', 'IN', 6.00, 'Wood', 3000.00, 5.15, 4.75, 14250.00, 15450.00, 1782.00, 31482.00),
(20, 'Michelle', 'MI', 5.75, 'Carpet', 3333.00, 2.25, 2.10, 6999.30, 7499.25, 833.67, 15332.22),
(21, 'Alex', 'MI', 5.75, 'Wood', 4000.00, 5.15, 4.75, 19000.00, 20600.00, 2277.00, 41877.00),
(22, 'Daniel', 'MI', 5.75, 'Laminate', 100.00, 1.75, 2.10, 210.00, 175.00, 22.14, 407.14),
(23, 'Jennifer', 'OH', 6.25, 'Wood', 200.00, 5.15, 4.75, 950.00, 1030.00, 123.75, 2103.75);

-- --------------------------------------------------------

--
-- Table structure for table `Products`
--

CREATE TABLE IF NOT EXISTS `Products` (
  `product_type` varchar(20) NOT NULL,
  `cost_per_sq_ft` decimal(10,2) NOT NULL,
  `labor_cost_per_sq_ft` decimal(10,2) NOT NULL,
  PRIMARY KEY (`product_type`),
  UNIQUE KEY `product_type` (`product_type`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Products`
--

INSERT INTO `Products` (`product_type`, `cost_per_sq_ft`, `labor_cost_per_sq_ft`) VALUES
('Carpet', 2.25, 2.10),
('Laminate', 1.75, 2.10),
('Tile', 3.50, 4.15),
('Wood', 5.15, 4.75);

-- --------------------------------------------------------

--
-- Table structure for table `Taxes`
--

CREATE TABLE IF NOT EXISTS `Taxes` (
  `state` varchar(2) NOT NULL,
  `tax_rate` decimal(10,2) NOT NULL,
  PRIMARY KEY (`state`),
  UNIQUE KEY `state` (`state`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Taxes`
--

INSERT INTO `Taxes` (`state`, `tax_rate`) VALUES
('IN', 6.00),
('MI', 5.75),
('OH', 6.25),
('PA', 6.75);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `Orders`
--
ALTER TABLE `Orders`
  ADD CONSTRAINT `Orders_ibfk_1` FOREIGN KEY (`product_type`) REFERENCES `Products` (`product_type`) ON DELETE NO ACTION,
  ADD CONSTRAINT `Orders_ibfk_2` FOREIGN KEY (`state`) REFERENCES `Taxes` (`state`) ON DELETE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
