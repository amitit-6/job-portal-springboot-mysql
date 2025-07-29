-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 29, 2025 at 03:59 PM
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
-- Database: `jobportal`
--

-- --------------------------------------------------------

--
-- Table structure for table `application`
--

CREATE TABLE `application` (
  `id` bigint(20) NOT NULL,
  `applicant_id` bigint(20) DEFAULT NULL,
  `job_id` bigint(20) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `applied_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `application`
--

INSERT INTO `application` (`id`, `applicant_id`, `job_id`, `status`, `applied_at`, `updated_at`) VALUES
(1, 1, 6, 'APPLIED', NULL, NULL),
(2, 1, 7, 'APPLIED', NULL, NULL),
(3, 1, 10, 'APPLIED', NULL, NULL),
(4, 1, 13, 'APPLIED', NULL, NULL),
(5, 1, 6, 'APPLIED', NULL, NULL),
(6, 1, 15, 'APPLIED', NULL, NULL),
(7, 1, 14, 'APPLIED', NULL, NULL),
(8, 1, 9, 'APPLIED', NULL, NULL),
(9, 1, 8, 'APPLIED', NULL, NULL),
(10, 1, 11, 'APPLIED', NULL, NULL),
(11, 1, 12, 'APPLIED', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `job`
--

CREATE TABLE `job` (
  `id` bigint(20) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `company` varchar(255) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `experience` varchar(255) DEFAULT NULL,
  `employer_id` bigint(20) DEFAULT NULL,
  `job_type` varchar(255) DEFAULT NULL,
  `last_date` date DEFAULT NULL,
  `posted_date` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `job`
--

INSERT INTO `job` (`id`, `title`, `description`, `company`, `location`, `experience`, `employer_id`, `job_type`, `last_date`, `posted_date`) VALUES
(6, 'Java Developer', 'Develop backend services using Spring Boot and Java.', 'TechNova Solutions', 'Bangalore', '2+ years', 1, NULL, NULL, NULL),
(7, 'Frontend Developer', 'Work on modern UI using ReactJS and Tailwind CSS.', 'BrightCoders Inc.', 'Pune', '1+ years', 2, NULL, NULL, NULL),
(8, 'Android Developer', 'Build Android apps with Jetpack Compose.', 'Mobitech Pvt. Ltd.', 'Delhi', '1-2 years', 1, NULL, NULL, NULL),
(9, 'Android', 'This is a Android Development Opportunity.', 'Google ', 'India', 'Fresher', 2, NULL, NULL, NULL),
(10, 'Java', 'This is a dummy Text.', 'Google ', 'Jetpur, Gujarat', '1+ years', 2, 'Internship', '2025-07-17', NULL),
(11, 'aaa', 'sfdfd', 'aaa', 'aaa', 'Fresher', 2, 'Full-Time', '2026-02-04', NULL),
(12, 'aa', 'dfdfd', 'aa', 'aa', 'Fresher', 2, 'Full-Time', '0323-03-31', NULL),
(13, 'aa', 'dgdddg', 'aa', 'aa', '3+ years', 2, 'Part-Time', '2030-09-04', NULL),
(14, 'demo', 'vddf', 'ggg', 'gdgd', '5+ years', 2, 'Internship', '2025-07-18', NULL),
(15, 'Flutter', 'Flutter', 'Amit', 'Ahmedabad', 'Fresher', 2, 'Internship', '2025-07-18', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `profile_picture` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `username`, `password`, `role`, `name`, `profile_picture`) VALUES
(1, 'amit', '$2a$10$SkCCU9qTxi059NK168G4RupSsK1mPXIGbsawMvUH6bdzf7zMOgJBa', 'APPLICANT', 'amit', NULL),
(2, 'admin', '$2a$10$KVWBGk9LEsVzVsLqqHlfr.xmAc1hNBmv0LXQK0CjToxZ3WUyEOmbW', 'EMPLOYER', NULL, NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `application`
--
ALTER TABLE `application`
  ADD PRIMARY KEY (`id`),
  ADD KEY `applicant_id` (`applicant_id`),
  ADD KEY `job_id` (`job_id`);

--
-- Indexes for table `job`
--
ALTER TABLE `job`
  ADD PRIMARY KEY (`id`),
  ADD KEY `employer_id` (`employer_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `application`
--
ALTER TABLE `application`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `job`
--
ALTER TABLE `job`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `application`
--
ALTER TABLE `application`
  ADD CONSTRAINT `application_ibfk_1` FOREIGN KEY (`applicant_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `application_ibfk_2` FOREIGN KEY (`job_id`) REFERENCES `job` (`id`);

--
-- Constraints for table `job`
--
ALTER TABLE `job`
  ADD CONSTRAINT `job_ibfk_1` FOREIGN KEY (`employer_id`) REFERENCES `user` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
