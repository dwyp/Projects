SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

-- Database: `Jobs`
-- --------------------------------------------------------
-- Table structure for table `authors`

CREATE TABLE IF NOT EXISTS `authors` (
  `authorId` int(11) NOT NULL AUTO_INCREMENT,
  `authorName` varchar(50) NOT NULL,
  PRIMARY KEY (`authorId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;
-- --------------------------------------------------------
--
-- Table structure for table `jobs`
--
CREATE TABLE IF NOT EXISTS `jobs` (
  `number` int(11) NOT NULL AUTO_INCREMENT,
  `published` date NOT NULL,
  `updated` date NOT NULL,
  `title` varchar(50) NOT NULL,
  `content` varchar(5000) NOT NULL,
  `authorId` int(11) NOT NULL,
  PRIMARY KEY (`number`),
  KEY `authorId` (`authorId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;
--
-- Constraints for table `jobs`
--
ALTER TABLE `jobs`
  ADD CONSTRAINT `jobs_ibfk_1` FOREIGN KEY (`authorId`) REFERENCES `authors` (`authorId`) ON DELETE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
