# Dump of table ProductCatalog
# ------------------------------------------------------------

DROP TABLE IF EXISTS `ProductCatalog`;

CREATE TABLE `ProductCatalog` (
  `id` varchar(200) NOT NULL DEFAULT '',
  `stock_qty` int(50) NOT NULL,
  `price` float(50,2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table ProductDetails
# ------------------------------------------------------------

DROP TABLE IF EXISTS `ProductDetails`;

CREATE TABLE `ProductDetails` (
  `id` varchar(200) NOT NULL DEFAULT '',
  `name` varchar(50) NOT NULL DEFAULT '',
  `description` varchar(200) NOT NULL DEFAULT '',
  `image` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table ProductProjection
# ------------------------------------------------------------

DROP VIEW IF EXISTS `ProductProjection`;

CREATE VIEW `ProductProjection` AS
SELECT pc.*, pd.name, pd.description, pd.image
FROM ProductCatalog as pc
JOIN ProductDetails as pd on pd.id = pc.id
