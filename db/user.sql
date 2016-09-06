CREATE TABLE `user` (
  `id` bigint(20) NOT NULL auto_increment,
  `name` varchar(20) default NULL,
  `remark` blob,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
