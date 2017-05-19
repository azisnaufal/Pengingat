/*
SQLyog Ultimate v11.33 (64 bit)
MySQL - 10.1.13-MariaDB : Database - db_pengingat
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`db_pengingat` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `db_pengingat`;

/*Table structure for table `tb_alarm` */

DROP TABLE IF EXISTS `tb_alarm`;

CREATE TABLE `tb_alarm` (
  `id_alarm` int(4) NOT NULL AUTO_INCREMENT,
  `enabled` enum('1','0') DEFAULT NULL,
  `alarm_name` varchar(25) DEFAULT NULL,
  `time` time DEFAULT NULL,
  `repeat` enum('1','0') DEFAULT NULL,
  `days` varchar(69) DEFAULT NULL,
  `id_music` int(4) DEFAULT NULL,
  PRIMARY KEY (`id_alarm`),
  KEY `id_music` (`id_music`),
  CONSTRAINT `tb_alarm_ibfk_1` FOREIGN KEY (`id_music`) REFERENCES `tb_music` (`id_music`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=latin1;

/*Data for the table `tb_alarm` */

/*Table structure for table `tb_music` */

DROP TABLE IF EXISTS `tb_music`;

CREATE TABLE `tb_music` (
  `id_music` int(4) NOT NULL AUTO_INCREMENT,
  `filedir` varchar(900) NOT NULL,
  `filename` varchar(900) NOT NULL,
  `fileextension` varchar(6) NOT NULL,
  PRIMARY KEY (`id_music`),
  KEY `id_music` (`id_music`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

/*Data for the table `tb_music` */

insert  into `tb_music`(`id_music`,`filedir`,`filename`,`fileextension`) values (1,'C:\\Windows\\Media','Alarm02.wav','.wav');

/*Table structure for table `tb_pomo_run` */

DROP TABLE IF EXISTS `tb_pomo_run`;

CREATE TABLE `tb_pomo_run` (
  `run` int(1) NOT NULL,
  PRIMARY KEY (`run`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tb_pomo_run` */

insert  into `tb_pomo_run`(`run`) values (0);

/*Table structure for table `tb_setting` */

DROP TABLE IF EXISTS `tb_setting`;

CREATE TABLE `tb_setting` (
  `id_setting` int(11) NOT NULL AUTO_INCREMENT,
  `silence_after` int(4) NOT NULL,
  `snooze_length` int(4) NOT NULL,
  `start_week_on` varchar(9) NOT NULL,
  `pomo_length` int(4) NOT NULL,
  `pomo_break` int(4) NOT NULL,
  `lock_filedirname` text,
  PRIMARY KEY (`id_setting`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `tb_setting` */

insert  into `tb_setting`(`id_setting`,`silence_after`,`snooze_length`,`start_week_on`,`pomo_length`,`pomo_break`,`lock_filedirname`) values (1,4,3,'Monday',5,4,'');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
