/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.12-log : Database - web_book
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`web_book` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `web_book`;

/*Table structure for table `company` */

DROP TABLE IF EXISTS `company`;

CREATE TABLE `company` (
  `id` varchar(40) NOT NULL,
  `companyname` varchar(100) NOT NULL,
  `linkman` varchar(20) NOT NULL,
  `linkphone` varchar(20) NOT NULL,
  `address` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`,`companyname`),
  UNIQUE KEY `companyname` (`companyname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `company` */

insert  into `company`(`id`,`companyname`,`linkman`,`linkphone`,`address`) values ('44f49b5a-eb8e-44ee-a486-0f82b5fa1740','中国邮政速递物流股份有限公司广东省电商物流分公司','杨俊豪','13821907906','广州市白云区槎头西洲北路93号\r'),('44f49b5a-eb8e-44ee-a486-0f82b5fa1742','广州市道路扩建工程办公室','林志明','020-87560912','广州越秀区仓边路133号\r'),('44f49b5a-eb8e-44ee-a486-0f82b5fa1744','中国移动通信集团公司广东分公司','王明辉','13843256709','广州天河区天河北路610号金海大厦\r'),('44f49b5a-eb8e-44ee-a486-0f82b5fa1744','汕头市潮阳区城市综合管理局','张静丽','13256789078','广东省汕头市峡山街道峡山广汕公路南1009号\r'),('44f49b5a-eb8e-44ee-a486-0f82b5fa1745','中国移动通信集团广东有限公司河源分公司','廖文琴','13532167567','河源市新市区建设大道移动综合大楼\r'),('44f49b5a-eb8e-44ee-a486-0f82b5fa1746','中国移动通信集团广东有限公司东莞分公司','章伟','13577890899','东莞市东城街道东城大道升威楼二楼\r'),('44f49b5a-eb8e-44ee-a486-0f82b5fa1747','中移铁通有限公司广东分公司','余冰','020-86546890','广州市越秀区中山一路163号\r'),('44f49b5a-eb8e-44ee-a486-0f82b5fa1748','中国移动通信集团广东有限公司中山分公司','黎晓君','13521131678','广东省中山市东区起湾南道139号\r'),('44f49b5a-eb8e-44ee-a486-0f82b5fa1749','中国移动通信集团广东有限公司深圳分公司','张铭华','13867112345','深圳市福田区莲花街道深南大道2010号中国移动深圳信息大厦\r'),('44f49b5a-eb8e-44ee-a486-0f82b5fa1751','广东省卫生和计划生育委员会','李玲玲','13571223890','广州市越秀区先烈南路17号大院省\r'),('44f49b5a-eb8e-44ee-a486-0f82b5fa1752','惠州白鹭湖旅游实业开发有限公司','吴天华','13844513890','广东省惠州市汝湖镇东亚角洞水库\r');

/*Table structure for table `customers` */

DROP TABLE IF EXISTS `customers`;

CREATE TABLE `customers` (
  `id` varchar(40) NOT NULL,
  `cname` varchar(30) NOT NULL,
  `cpassword` varchar(40) NOT NULL,
  `cgender` varchar(5) DEFAULT NULL,
  `cbirth` varchar(15) DEFAULT NULL,
  `cmajority` varchar(30) DEFAULT NULL,
  `cinterest` varchar(50) DEFAULT NULL,
  `cemail` varchar(25) DEFAULT NULL,
  `cphone` varchar(30) NOT NULL,
  PRIMARY KEY (`cname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `customers` */

insert  into `customers`(`id`,`cname`,`cpassword`,`cgender`,`cbirth`,`cmajority`,`cinterest`,`cemail`,`cphone`) values ('dbed40b8-1394-46e4-9785-bb39a779e7d1','丽萨','123456','男','1992-01-02','软件工程','足球;台球;其他;','laoliweibo@sina.com','17405570239'),('e0335332-9191-410a-b200-f66f1b90ca6b','光头强','asdfcv','男','1050-09-25','建筑学','羽毛球;篮球;足球;','guantouqiang@flash.com','17568945541'),('a3645348-4627-461b-85ac-a667ec1d4a7b','周杰伦','111111','男','1996-08-01','其他','篮球;足球;拳击;其他;','123456@qq.com','18872626574'),('3c6617bb-b7d7-4749-b3fd-a06bf43d4978','思科','ghj123','女','2001-10-01','建筑学','篮球;足球;游泳;羽毛球;排球;台球;拳击;其他;','9654781235@outlook.con','15250216456'),('8ede264b-562c-4f04-bbe8-5bf2a5308131','李大大','gtygty56','女','1997-12-02','小学教育','足球;台球;其他;','lidadasina@sina.com','13406570209'),('cb8495ee-f797-4d18-8596-0fb766603e07','李忠','123456','女','1965-05-01','文学院','羽毛球;足球;','480725864@qq.com','13406689975'),('8f2e09ef-07d1-40cb-9052-ebffd8ad0ec3','测试','123456','男','','其他','足球;其他;','5129478512@163.com','13405570209'),('39e06647-bda0-4303-aff4-53e9225b0b4f','熊二','520520','男','1001-02-02','其他','羽毛球;篮球;足球;游泳;','456123789@outlook.com','13564867591'),('7474d737-d72b-4d9f-a732-eea28712d686','熊大','250250','男','1000-01-01','其他','羽毛球;篮球;足球;','25025025025@outlook.com','15250250250'),('9596b928-8a3d-44c8-913c-778e2b1f87ea','王','123456','女','1992-02-01','计算机与科学','篮球;台球;其他;','148562397@qq.com','13264867596'),('e671b46e-a5e3-4433-a8ea-9b17fc5607d7','王五','qwerdf','女','1985-02-09','计算机与科学','羽毛球;篮球;足球;游泳;','11283919231@163.com','15864567588'),('d6b24e6c-aef6-412a-98c3-8a62b6f4786e','王大','123456','男','1992-10-15','软件工程','篮球;足球;台球;拳击;其他;','wangda@outlook.com','17405510219'),('15a5d82b-8e0a-4058-bd5b-0bcbb97ba957','王老','148159','男','1992-01-01','软件工程','篮球;足球;游泳;羽毛球;排球;台球;拳击;其他;','5129478512@163.com','13405570209'),('7ec06a97-26ae-4a3e-a2dc-90d31805d320','王老二','123789','男','1200-01-02','电子信息','篮球;游泳;台球;其他;','147895623@qq.com','13422312611'),('d33f1e2f-ecb8-4d19-b4ff-a55cefbe3dfd','王老板','asdfgh','男','1992-05-03','计算机与科学','羽毛球;足球;游泳;','5598949525@163.com','13456899535'),('e81d9a2f-c903-420a-a1ed-f556f82fd16d','王霞可','cf1cf2','女','1992-0102','电子信息','篮球;足球;排球;台球;其他;','148262397@163.com','18405510119'),('0bdb1fb7-4208-466a-8d00-df33f7840cbe','蔡依林','hjkiop1','女','1992-02-06','其他','足球;游泳;台球;','caiyilin@outlook.com','13264237596'),('101d202c-8023-4172-a60a-2eede35d3b7c','蔡雯','123qwe','男','1992-01-02','网络工程','篮球;排球;其他;','148562397@163.com','17405570219'),('8acf52a3-4cd7-4e11-ae43-c0b313154f58','袁媛','sjoiu1','男','1991-07-02','计算机与科学','排球;其他;','578856297@163.com','17405021931'),('a6a57e58-8103-427e-bdc7-af777f443412','阿三','asan520','男','1996-01-02','软件工程','足球;台球;拳击;其他;','1485623297@163.com','13557020956'),('89b6ff1d-b1ee-43d4-8397-a95a67878195','阿斯','qwer1230','男','2007-01-01','计算机与科学','足球;台球;其他;','sda1asdad@163.com','15403570247'),('84ffa587-a1ff-4c69-b90a-d68b0f5f543f','高顿','qwer520qw','男','2005-12-21','历史师范','足球;台球;其他;','987564123@sina.com','15250255621');

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` varchar(40) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(30) DEFAULT NULL,
  `email` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`,`username`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `users` */

insert  into `users`(`id`,`username`,`password`,`email`) values ('34f39b5a-eb8e-43ee-a486-0f82b5fa1732','sjppop','124','455@163.com'),('933098ad-3737-423d-9cd7-733cfc6668e8','xfzhang','123456',''),('d8f71490-7029-46a8-9da8-9a937010585e','admin','123456',NULL),('d8f71490-7029-46a8-9da8-9a937060585e','Jack','123456','');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
