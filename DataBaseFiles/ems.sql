-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 05, 2024 at 01:35 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ems`
--

-- --------------------------------------------------------

--
-- Table structure for table `00_admins`
--

CREATE TABLE `00_admins` (
  `Username` varchar(255) NOT NULL,
  `Password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `00_admins`
--

INSERT INTO `00_admins` (`Username`, `Password`) VALUES
('Hasanka', '$2a$09$OHolzl3mVm3ZiJ9GFX3Kzu7HsNUe5MXaOerUuZdYpn/dlYikNNpgK');

-- --------------------------------------------------------

--
-- Table structure for table `00_allowances`
--

CREATE TABLE `00_allowances` (
  `ID` int(11) NOT NULL,
  `Description` varchar(100) NOT NULL,
  `Amount` decimal(12,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `00_deductions`
--

CREATE TABLE `00_deductions` (
  `ID` int(11) NOT NULL,
  `Description` varchar(100) NOT NULL,
  `Amount` decimal(12,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `00_designation`
--

CREATE TABLE `00_designation` (
  `ID` int(11) NOT NULL,
  `Name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `00_designation`
--

INSERT INTO `00_designation` (`ID`, `Name`) VALUES
(1, 'Typesetter'),
(2, 'Graphic Designer'),
(3, 'Print Operator'),
(4, 'Proof Reader'),
(5, 'Computer Operator'),
(6, 'Cashier');

-- --------------------------------------------------------

--
-- Table structure for table `00_holidays`
--

CREATE TABLE `00_holidays` (
  `ID` int(11) NOT NULL,
  `Date` date NOT NULL,
  `Description` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `00_leaves`
--

CREATE TABLE `00_leaves` (
  `ID` int(11) NOT NULL,
  `Description` varchar(255) NOT NULL,
  `SalaryMulti` decimal(5,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `00_leaves`
--

INSERT INTO `00_leaves` (`ID`, `Description`, `SalaryMulti`) VALUES
(1, 'Full Day', 0.00),
(2, 'Half Day', 0.50);

-- --------------------------------------------------------

--
-- Table structure for table `00_schedules`
--

CREATE TABLE `00_schedules` (
  `ID` int(11) NOT NULL,
  `Time_in` time NOT NULL,
  `Time_out` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `00_schedules`
--

INSERT INTO `00_schedules` (`ID`, `Time_in`, `Time_out`) VALUES
(1, '06:00:00', '16:00:00'),
(2, '07:00:00', '17:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `00_shopdetails`
--

CREATE TABLE `00_shopdetails` (
  `ID` int(2) NOT NULL,
  `name` varchar(255) NOT NULL,
  `address` text NOT NULL,
  `start_date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `00_shopdetails`
--

INSERT INTO `00_shopdetails` (`ID`, `name`, `address`, `start_date`) VALUES
(1, 'Danuji Book Shop', 'School Rd, Hambantota', '2015-07-27');

-- --------------------------------------------------------

--
-- Table structure for table `01_00_employee`
--

CREATE TABLE `01_00_employee` (
  `EmpNumber` varchar(255) NOT NULL,
  `FirstName` varchar(255) NOT NULL,
  `LastName` varchar(255) NOT NULL,
  `Name` varchar(255) NOT NULL,
  `NICNumber` varchar(12) NOT NULL,
  `Gender` varchar(10) NOT NULL,
  `DOB` date NOT NULL,
  `PhoneNumber` varchar(12) NOT NULL,
  `Address` varchar(255) NOT NULL,
  `Status` varchar(20) NOT NULL DEFAULT 'Active'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `01_00_employee`
--

INSERT INTO `01_00_employee` (`EmpNumber`, `FirstName`, `LastName`, `Name`, `NICNumber`, `Gender`, `DOB`, `PhoneNumber`, `Address`, `Status`) VALUES
('DBSEMP_1', 'Charitha', 'Gamage', 'M G Charitha', '200111103767', 'MALE', '2023-06-21', '0761806162', 'manajjawa', 'Active'),
('DBSEMP_2', 'Charitha', 'Gamage', 'M G C P KUMARA', '200111103767', 'MALE', '2023-06-21', '0761806162', 'manajjawa', 'Active');

-- --------------------------------------------------------

--
-- Table structure for table `01_01_employee_education`
--

CREATE TABLE `01_01_employee_education` (
  `EmpNumber` varchar(255) NOT NULL,
  `Education` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `01_01_employee_education`
--

INSERT INTO `01_01_employee_education` (`EmpNumber`, `Education`) VALUES
('DBSEMP_1', 'GCE A/L,GCE A/L'),
('DBSEMP_2', 'GCE O/L,GCE A/L');

-- --------------------------------------------------------

--
-- Table structure for table `01_02_employee_designation`
--

CREATE TABLE `01_02_employee_designation` (
  `EmpNumber` varchar(255) NOT NULL,
  `DesignationID` int(11) NOT NULL,
  `ScheduleID` int(11) NOT NULL,
  `AppointedDate` date NOT NULL,
  `BasicSalary` decimal(12,2) NOT NULL,
  `DesignatedStep` int(3) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `01_02_employee_designation`
--

INSERT INTO `01_02_employee_designation` (`EmpNumber`, `DesignationID`, `ScheduleID`, `AppointedDate`, `BasicSalary`, `DesignatedStep`) VALUES
('DBSEMP_1', 2, 1, '2023-06-21', 20000.00, 1),
('DBSEMP_1', 3, 2, '2023-06-22', 15000.00, 2),
('DBSEMP_2', 1, 2, '2023-06-21', 200000.00, 1),
('DBSEMP_2', 5, 2, '2023-06-25', 25000.00, 2),
('DBSEMP_1', 1, 2, '2023-06-25', 20000.00, 3),
('DBSEMP_1', 4, 1, '2023-06-25', 30000.00, 4),
('DBSEMP_2', 2, 1, '2023-07-20', 35000.00, 3);

-- --------------------------------------------------------

--
-- Table structure for table `01_03_employee_salary`
--

CREATE TABLE `01_03_employee_salary` (
  `EmpNumber` varchar(255) NOT NULL,
  `OTRate` decimal(12,2) NOT NULL,
  `SalaryDate` int(11) NOT NULL,
  `MonthlyLeaves` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `01_03_employee_salary`
--

INSERT INTO `01_03_employee_salary` (`EmpNumber`, `OTRate`, `SalaryDate`, `MonthlyLeaves`) VALUES
('DBSEMP_1', 100.00, 28, 3),
('DBSEMP_2', 100.00, 28, 5);

-- --------------------------------------------------------

--
-- Table structure for table `01_04_employee_attendence`
--

CREATE TABLE `01_04_employee_attendence` (
  `EmpNumber` varchar(255) NOT NULL,
  `Date` date NOT NULL,
  `InTime` time NOT NULL DEFAULT '00:00:00',
  `OutTime` time NOT NULL DEFAULT '00:00:00',
  `Status` int(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `01_04_employee_attendence`
--

INSERT INTO `01_04_employee_attendence` (`EmpNumber`, `Date`, `InTime`, `OutTime`, `Status`) VALUES
('DBSEMP_1', '2023-06-01', '00:00:00', '00:00:00', 0),
('DBSEMP_1', '2023-06-02', '08:00:00', '00:00:00', 0),
('DBSEMP_1', '2023-06-03', '08:00:00', '00:00:00', 0),
('DBSEMP_1', '2023-06-04', '07:00:00', '19:54:00', 1),
('DBSEMP_1', '2023-06-05', '06:00:00', '00:00:00', 0),
('DBSEMP_1', '2023-06-24', '08:00:00', '19:00:00', 1),
('DBSEMP_1', '2023-06-27', '09:00:00', '18:00:00', 1),
('DBSEMP_1', '2023-07-20', '00:00:00', '00:00:00', 0),
('DBSEMP_2', '2023-06-24', '08:00:00', '19:23:00', 1),
('DBSEMP_2', '2023-06-27', '08:00:00', '19:00:00', 1);

-- --------------------------------------------------------

--
-- Table structure for table `01_05_employee_leaves`
--

CREATE TABLE `01_05_employee_leaves` (
  `EmpNumber` varchar(255) NOT NULL,
  `Date` date NOT NULL,
  `TypeID` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `01_06_employee_salaryslip`
--

CREATE TABLE `01_06_employee_salaryslip` (
  `SalarySlipID` varchar(255) NOT NULL,
  `EmpNumber` varchar(255) NOT NULL,
  `SalaryCycleStartDate` date NOT NULL,
  `SalaryCycleEndDate` date NOT NULL,
  `SalaryCycleDateCount` int(11) NOT NULL,
  `Holidays` int(2) NOT NULL,
  `LeavesOwned` int(2) NOT NULL,
  `LeavesTaken` int(2) NOT NULL,
  `TotalPresntDays` int(2) NOT NULL,
  `TotalAbsentDays` int(2) NOT NULL,
  `TotalWorkedDays` int(2) NOT NULL,
  `BasicSalary` decimal(12,2) NOT NULL,
  `Allowences` varchar(255) NOT NULL,
  `OverTimePay` decimal(12,2) NOT NULL,
  `GrossSalary` decimal(12,2) NOT NULL,
  `SalaryAdvance` varchar(255) NOT NULL,
  `Deductions` varchar(255) NOT NULL,
  `TotalDeductions` decimal(12,2) NOT NULL,
  `NetSalary` decimal(12,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `01_07_employee_accounts`
--

CREATE TABLE `01_07_employee_accounts` (
  `EmpNumber` varchar(255) NOT NULL,
  `Username` varchar(255) NOT NULL,
  `Password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `01_07_employee_accounts`
--

INSERT INTO `01_07_employee_accounts` (`EmpNumber`, `Username`, `Password`) VALUES
('DBSEMP_1', 'DBSEMP_1', '$2a$09$skML0p3LtRQMANVp0UM/kuC7f.KXFDKZqOc4SBb1znj7loMcME32e'),
('DBSEMP_2', 'DBSEMP_2', '$2a$09$58ZJQSTBqaVcNNUIehvLfu8.otNfck3O34a9LJJAJcNRKZAyfHfkC');

-- --------------------------------------------------------

--
-- Table structure for table `01_08_cashadvance`
--

CREATE TABLE `01_08_cashadvance` (
  `ID` int(11) NOT NULL,
  `Date` date NOT NULL,
  `EmpNumber` varchar(255) NOT NULL,
  `Amount` decimal(12,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Stand-in structure for view `02_02_employee_accounts`
-- (See below for the actual view)
--
CREATE TABLE `02_02_employee_accounts` (
`EmpNumber` varchar(255)
,`Name` varchar(255)
,`Status` varchar(20)
,`Username` varchar(255)
,`Password` varchar(255)
);

-- --------------------------------------------------------

--
-- Structure for view `02_02_employee_accounts`
--
DROP TABLE IF EXISTS `02_02_employee_accounts`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `02_02_employee_accounts`  AS SELECT `01_00_employee`.`EmpNumber` AS `EmpNumber`, `01_00_employee`.`Name` AS `Name`, `01_00_employee`.`Status` AS `Status`, `01_07_employee_accounts`.`Username` AS `Username`, `01_07_employee_accounts`.`Password` AS `Password` FROM (`01_00_employee` join `01_07_employee_accounts` on(`01_00_employee`.`EmpNumber` = `01_07_employee_accounts`.`EmpNumber`)) ;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
