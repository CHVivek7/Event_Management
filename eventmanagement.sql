-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 21, 2025 at 07:37 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `eventmanagement`
--

-- --------------------------------------------------------

--
-- Table structure for table `customers`
--

CREATE TABLE `customers` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) NOT NULL,
  `is_blocked` bit(1) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `customers`
--

INSERT INTO `customers` (`id`, `email`, `is_blocked`, `password`, `role`, `username`) VALUES
(3, 'customer@example.com', b'0', '$2a$10$uHYy1AjUHradiJNvsuacreFSPZbDGDyqkleyOzo8SyBoaTf3UbXki', 'ROLE_CUSTOMER', 'testcustomer'),
(4, 'customer123@example.com', b'0', '$2a$10$.Oa4G8j3X43tbYha7NJy1uLqa37LamfERCbl4HR4m0fAm8Vo.kT7y', 'ROLE_CUSTOMER', 'testcustomer123');

-- --------------------------------------------------------

--
-- Table structure for table `events`
--

CREATE TABLE `events` (
  `id` bigint(20) NOT NULL,
  `budget` double NOT NULL,
  `cancellation_reason` varchar(255) DEFAULT NULL,
  `event_time` datetime(6) DEFAULT NULL,
  `expected_attendance` int(11) NOT NULL,
  `location` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `customer_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `events`
--

INSERT INTO `events` (`id`, `budget`, `cancellation_reason`, `event_time`, `expected_attendance`, `location`, `name`, `type`, `customer_id`) VALUES
(6, 27000, 'Venue unavailable', '2025-12-10 19:00:00.000000', 60, 'Grand Ballroom, Downtown', 'John\'s 30th Birthday', 'Birthday Party', 3),
(7, 25000, NULL, '2025-12-10 19:00:00.000000', 50, 'Grand Ballroom, Downtown', 'John\'s 30th Birthday', 'Birthday Party', 3),
(8, 25000, NULL, '2025-12-10 19:00:00.000000', 50, 'Grand Ballroom, Downtown', 'John\'s 30th Birthday', 'Birthday Party', 3),
(9, 25000, NULL, '2025-12-10 19:00:00.000000', 50, 'Grand Ballroom, Downtown', 'John\'s 30th Birthday', 'Birthday Party', 3),
(10, 25000, NULL, '2025-12-10 19:00:00.000000', 50, 'Grand Ballroom, Downtown', 'John\'s 30th Birthday', 'Birthday Party', 3);

-- --------------------------------------------------------

--
-- Table structure for table `organizers`
--

CREATE TABLE `organizers` (
  `id` bigint(20) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `organizers`
--

INSERT INTO `organizers` (`id`, `password`, `role`, `username`) VALUES
(2, '$2a$10$T8O5tnrcHFesN.YTGqilHOL1fk5yeO/GkZZ3OYWvayUd4nlK/iD42', 'ROLE_ORGANIZER', 'testorganizer');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `customers`
--
ALTER TABLE `customers`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKrfbvkrffamfql7cjmen8v976v` (`email`),
  ADD UNIQUE KEY `UKbepynu3b6l8k2ppuq6b33xfxc` (`username`);

--
-- Indexes for table `events`
--
ALTER TABLE `events`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKii5rb5kwirlh7h032gijcixiy` (`customer_id`);

--
-- Indexes for table `organizers`
--
ALTER TABLE `organizers`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKtrh7r3r00uou47k1uncew5qai` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `customers`
--
ALTER TABLE `customers`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `events`
--
ALTER TABLE `events`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `organizers`
--
ALTER TABLE `organizers`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `events`
--
ALTER TABLE `events`
  ADD CONSTRAINT `FKii5rb5kwirlh7h032gijcixiy` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
