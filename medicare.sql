-- Server version: 8.0.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

create database medicare;
use medicare;
--
-- Database: `medicare`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `userid` varchar(30) NOT NULL,
  `pwd` varchar(30) DEFAULT NULL
);

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`userid`, `pwd`) VALUES
('admin', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `agent`
--

CREATE TABLE `agent` (
  `agentid` varchar(255) NOT NULL,
  `accno` varchar(255) DEFAULT NULL,
  `bank` varchar(255) DEFAULT NULL,
  `dob` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `ifsc` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `pwd` varchar(255) DEFAULT NULL
);

-- --------------------------------------------------------

--
-- Table structure for table `appointment`
--

CREATE TABLE `appointment` (
  `apptid` int NOT NULL,
  `date` varchar(10)  DEFAULT NULL,
  `docid` varchar(10)  DEFAULT NULL,
  `docname` varchar(50)  DEFAULT NULL,
  `remarks` varchar(50)  DEFAULT NULL,
  `servicename` varchar(50)  DEFAULT NULL,
  `status` varchar(10)  DEFAULT 'Booked',
  `time` varchar(10)  DEFAULT NULL,
  `patid` varchar(10)  DEFAULT NULL,
  `patname` varchar(50)  DEFAULT NULL,
  `conditions` varchar(100)  DEFAULT NULL
);

-- --------------------------------------------------------

--
-- Table structure for table `diagnostic_service`
--

CREATE TABLE `diagnostic_service` (
  `dsid` int NOT NULL,
  `addline1` varchar(255) DEFAULT NULL,
  `addline2` varchar(255) DEFAULT NULL,
  `centername` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `facilities` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `servicename` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `website` varchar(255) DEFAULT NULL,
  `zip` varchar(255) DEFAULT NULL
);

-- --------------------------------------------------------

--
-- Table structure for table `doctor`
--

CREATE TABLE `doctor` (
  `doctorid` varchar(255) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `diagservice` varchar(255) DEFAULT NULL,
  `dob` varchar(255) DEFAULT NULL,
  `fname` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `lname` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `pwd` varchar(255) DEFAULT NULL,
  `qualification` varchar(255) DEFAULT NULL,
  `speciality` varchar(255) DEFAULT NULL
);

-- --------------------------------------------------------
CREATE TABLE `frontdesk` ( frontdeskid  varchar(255) NOT NULL,  fname  varchar(255) NOT NULL, lname  varchar(255) NOT NULL, pwd varchar(255) NOT NULL, gender varchar(255) NOT NULL, phone varchar(255) NOT NULL, dob varchar(255) NOT NULL, email varchar(255) NOT NULL, status varchar(255) NOT NULL DEFAULT 'not approved' )
ALTER TABLE frontdesk   ADD PRIMARY KEY (frontdeskid)

--
-- Table structure for table `patient`
--

CREATE TABLE `patient` (
  `patientid` varchar(255) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `dob` varchar(255) DEFAULT NULL,
  `fname` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `lname` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `pwd` varchar(15) DEFAULT NULL
);

-- --------------------------------------------------------

--
-- Table structure for table `prescription`
--

CREATE TABLE `prescription` (
  `id` int NOT NULL,
  `mname` varchar(50) NOT NULL,
  `qty` int NOT NULL,
  `unit` varchar(20) NOT NULL,
  `times` varchar(20) NOT NULL,
  `tid` int NOT NULL
);

-- --------------------------------------------------------

--
-- Table structure for table `test`
--

CREATE TABLE `test` (
  `id` int NOT NULL,
  `testname` varchar(50) NOT NULL,
  `remarks` varchar(50)  DEFAULT NULL,
  `result` varchar(100)  DEFAULT NULL,
  `patid` varchar(20) NOT NULL,
  `status` varchar(10)  NOT NULL DEFAULT 'Requested',
  `tdate` date DEFAULT NULL
);

-- --------------------------------------------------------

--
-- Table structure for table `treatment`
--

CREATE TABLE `treatment` (
  `id` int NOT NULL,
  `patid` varchar(20) NOT NULL,
  `patname` varchar(50) NOT NULL,
  `docid` varchar(20) NOT NULL,
  `docname` varchar(50) NOT NULL,
  `symptoms` varchar(100) NOT NULL,
  `diagnosis` varchar(100) NOT NULL,
  `recommendations` varchar(100) NOT NULL,
  `diet` varchar(100) NOT NULL,
  `referal` varchar(100)  DEFAULT NULL,
  `tests` varchar(100)  DEFAULT NULL,
  `tdate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `status` varchar(20) NOT NULL DEFAULT 'Started'
);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`userid`);

--
-- Indexes for table `agent`
--
ALTER TABLE `agent`
  ADD PRIMARY KEY (`agentid`);

--
-- Indexes for table `appointment`
--
ALTER TABLE `appointment`
  ADD PRIMARY KEY (`apptid`);

--
-- Indexes for table `diagnostic_service`
--
ALTER TABLE `diagnostic_service`
  ADD PRIMARY KEY (`dsid`);

--
-- Indexes for table `doctor`
--
ALTER TABLE `doctor`
  ADD PRIMARY KEY (`doctorid`);

--
-- Indexes for table `patient`
--
ALTER TABLE `patient`
  ADD PRIMARY KEY (`patientid`);

--
-- Indexes for table `prescription`
--
ALTER TABLE `prescription`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `test`
--
ALTER TABLE `test`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `treatment`
--
ALTER TABLE `treatment`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `appointment`
--
ALTER TABLE `appointment`
  MODIFY `apptid` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `diagnostic_service`
--
ALTER TABLE `diagnostic_service`
  MODIFY `dsid` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `prescription`
--
ALTER TABLE `prescription`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `test`
--
ALTER TABLE `test`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `treatment`
--
ALTER TABLE `treatment`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
