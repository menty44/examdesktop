-- phpMyAdmin SQL Dump
-- version 4.1.12
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Oct 27, 2014 at 11:32 PM
-- Server version: 5.6.16
-- PHP Version: 5.5.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `exam`
--

-- --------------------------------------------------------

--
-- Table structure for table `exams`
--

CREATE TABLE IF NOT EXISTS `exams` (
  `indexno` int(20) NOT NULL DEFAULT '0',
  `subject` varchar(20) NOT NULL,
  `cat1` float NOT NULL,
  `cat2` float NOT NULL,
  `endterm` float NOT NULL,
  `total` float NOT NULL,
  `average` float NOT NULL,
  `grade` text NOT NULL,
  PRIMARY KEY (`indexno`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `exams`
--

INSERT INTO `exams` (`indexno`, `subject`, `cat1`, `cat2`, `endterm`, `total`, `average`, `grade`) VALUES
(0, 'maths', 20, 27, 68, 115, 91.5, 'A'),
(1, 'maths', 20, 27, 89, 0, 0, '');

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE IF NOT EXISTS `student` (
  `indexno` int(11) NOT NULL,
  `firstname` text NOT NULL,
  `lastname` text NOT NULL,
  `stream` int(20) NOT NULL,
  `form` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`indexno`, `firstname`, `lastname`, `stream`, `form`) VALUES
(1, 'fred', 'oluoch', 1, 1),
(2, 'tom', 'weida', 2, 1),
(3, 'mary', 'gathoni', 1, 2),
(4, 'wanjohi', 'ken', 2, 2),
(5, 'tony', 'gudda', 1, 3),
(6, 'alex', 'alela', 2, 3),
(7, 'archie', 'phoebe', 1, 4),
(8, 'mya', 'tayana', 2, 4),
(9, 'curtis', 'dwayne', 2, 1),
(10, 'brian', 'okoyo', 1, 2);

-- --------------------------------------------------------

--
-- Table structure for table `subjects`
--

CREATE TABLE IF NOT EXISTS `subjects` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `firstname` text NOT NULL,
  `lastname` text NOT NULL,
  `maths` int(11) NOT NULL,
  `english` int(4) NOT NULL,
  `biology` int(4) NOT NULL,
  `chem` int(4) NOT NULL,
  `hist` int(4) NOT NULL,
  `bizstudies` int(4) NOT NULL,
  `agri` int(4) NOT NULL,
  `cre` int(4) NOT NULL,
  `comp` int(4) NOT NULL,
  `art` int(4) NOT NULL,
  `total` varchar(4) NOT NULL,
  `avg` float NOT NULL,
  `grade` varchar(2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8091 ;

--
-- Dumping data for table `subjects`
--

INSERT INTO `subjects` (`id`, `firstname`, `lastname`, `maths`, `english`, `biology`, `chem`, `hist`, `bizstudies`, `agri`, `cre`, `comp`, `art`, `total`, `avg`, `grade`) VALUES
(1, 'y', 'u', 78, 76, 76, 88, 99, 99, 78, 89, 99, 88, '948', 86, 'A'),
(2, 'y', 'u', 78, 76, 76, 88, 99, 99, 78, 89, 99, 100, '960', 86, 'A'),
(4, 'FREDDY', 'OLUOCH', 90, 88, 99, 89, 87, 67, 89, 98, 88, 99, '983', 88, '65'),
(5, 'COOL', 'MAN', 99, 89, 89, 87, 97, 67, 87, 98, 99, 98, '997', 89, 'A'),
(6, 'yr', 'kj', 88, 99, 77, 88, 99, 88, 77, 77, 99, 99, '968', 86, 'E'),
(8, 'hack', 'hacked it', 88, 99, 77, 88, 99, 100, 77, 99, 99, 99, '1002', 90, 'A'),
(10, 'BAD', 'GAL', 9, 89, 8, 87, 9, 67, 8, 98, 9, 98, '490', 39, 'E'),
(11, 'tom', 'ngolo', 90, 89, 50, 60, 9, 67, 70, 98, 92, 39, '734', 69, 'B'),
(12, 'tab', 'asus', 89, 77, 88, 99, 88, 67, 88, 99, 85, 89, '957', 86, 'A'),
(13, 'java', 'code', 90, 87, 56, 77, 66, 77, 67, 98, 87, 93, '865', 77, 'A'),
(14, 'alela', 'mwiki', 90, 87, 77, 62, 66, 23, 67, 48, 87, 87, '761', 67, 'B'),
(15, 'hen', 'boss', 76, 56, 98, 88, 77, 88, 99, 66, 82, 97, '926', 82, 'A'),
(16, 'gret', 'matha', 88, 99, 76, 87, 56, 66, 79, 97, 77, 88, '892', 80, 'A'),
(17, 'def', 'last', 88, 66, 55, 66, 77, 88, 98, 43, 69, 80, '828', 74, 'A'),
(18, 'mike', 'xrx', 90, 67, 54, 66, 77, 44, 52, 69, 67, 54, '692', 69, 'B'),
(89, 'jhg', 'jhg', 66, 77, 55, 44, 33, 22, 44, 99, 99, 99, '682', 68, 'B'),
(655, 'uy', 'ygjuh', 67, 88, 77, 55, 66, 77, 87, 88, 76, 76, '844', 84, 'A'),
(909, 'meme', 'wewe', 12, 33, 22, 44, 32, 14, 56, 75, 34, 89, '467', 46, 'D'),
(6565, 'juh', 'yuyu', 13, 66, 55, 44, 77, 66, 88, 99, 11, 33, '640', 64, 'B'),
(8080, 'FRED', 'OLUOCH', 90, 78, 66, 77, 55, 44, 78, 98, 87, 76, '827', 82, 'A'),
(8089, 'FRED', 'OLUOCH', 13, 78, 62, 77, 55, 44, 33, 67, 87, 76, '625', 62, 'B'),
(8090, 'FRED', 'OLUOCH', 13, 78, 62, 77, 55, 44, 33, 67, 87, 76, '625', 62, 'B');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
