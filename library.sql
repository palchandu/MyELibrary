-- phpMyAdmin SQL Dump
-- version 4.5.4.1deb2ubuntu2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jul 27, 2018 at 11:23 PM
-- Server version: 5.7.22-0ubuntu0.16.04.1
-- PHP Version: 7.0.30-0ubuntu0.16.04.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `library`
--

-- --------------------------------------------------------

--
-- Table structure for table `E_BOOK`
--

CREATE TABLE `E_BOOK` (
  `CALLNO` varchar(400) NOT NULL,
  `NAME` varchar(400) DEFAULT NULL,
  `AUTHOR` varchar(400) DEFAULT NULL,
  `PUBLISHER` varchar(400) DEFAULT NULL,
  `QUANTITY` int(11) DEFAULT NULL,
  `ISSUED` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `E_BOOK`
--

INSERT INTO `E_BOOK` (`CALLNO`, `NAME`, `AUTHOR`, `PUBLISHER`, `QUANTITY`, `ISSUED`) VALUES
('12113', 'HelloAndroid', 'chandu', 'Pearson', 2, 0),
('12114', 'SKGP PROPERTY', 'chandu', 'Pearson', 2, 1),
('12115', 'SKGP', 'chandu', 'Pearson', 2, 0),
('12116', 'SKGP PROPERTY', 'chandu', 'Pearson', 3, 0);

-- --------------------------------------------------------

--
-- Table structure for table `E_ISSUEBOOK`
--

CREATE TABLE `E_ISSUEBOOK` (
  `CALLNO` varchar(400) NOT NULL,
  `STUDENTID` varchar(400) NOT NULL,
  `STUDENTNAME` varchar(400) DEFAULT NULL,
  `STUDENTMOBILE` int(11) DEFAULT NULL,
  `ISSUEDDATE` date DEFAULT NULL,
  `RETURNSTATUS` varchar(400) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `E_ISSUEBOOK`
--

INSERT INTO `E_ISSUEBOOK` (`CALLNO`, `STUDENTID`, `STUDENTNAME`, `STUDENTMOBILE`, `ISSUEDDATE`, `RETURNSTATUS`) VALUES
('12113', '12', 'Hello Chandra', 12343567, '2018-07-27', 'yes'),
('12114', '13', 'Hello Chandra', 1098776, '2018-07-27', 'no');

-- --------------------------------------------------------

--
-- Table structure for table `E_LIBRARIAN`
--

CREATE TABLE `E_LIBRARIAN` (
  `ID` int(11) NOT NULL,
  `NAME` varchar(400) DEFAULT NULL,
  `PASSWORD` varchar(400) DEFAULT NULL,
  `EMAIL` varchar(400) DEFAULT NULL,
  `MOBILE` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `E_LIBRARIAN`
--

INSERT INTO `E_LIBRARIAN` (`ID`, `NAME`, `PASSWORD`, `EMAIL`, `MOBILE`) VALUES
(1, 'chandra', '123456', 'chandu2013pal@gmail.com', 123456),
(2, 'SKGP PROP', '123456', 'chandra.getwebsoftware@gmail.com', 122343),
(3, 'SKGP', '123456', 'librarian2@gmail.com', 1234567),
(4, 'Hello', '123456', 'hello@gmail.com', 12345678),
(8, 'wewew      ', '8888888', 'chandu2013pal@gmail.com', 888888),
(9, 'SKGP PROPERTY', '12345', 'chandu2013pal@gmail.com', 12345);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `E_BOOK`
--
ALTER TABLE `E_BOOK`
  ADD PRIMARY KEY (`CALLNO`);

--
-- Indexes for table `E_LIBRARIAN`
--
ALTER TABLE `E_LIBRARIAN`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `E_LIBRARIAN`
--
ALTER TABLE `E_LIBRARIAN`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
