﻿CREATE TABLE `TbPublicWifiInfo` (
	`X_SWIFI_MGR_NO`	TEXT	NOT NULL,
	`X_SWIFI_WRDOFC`	TEXT	NULL,
	`X_SWIFI_MAIN_NM`	TEXT	NULL,
	`X_SWIFI_ADRES1`	TEXT	NULL,
	`X_SWIFI_ADRES2`	TEXT	NULL,
	`X_SWIFI_INSTL_FLOOR`	TEXT	NULL,
	`X_SWIFI_INSTL_TY`	TEXT	NULL,
	`X_SWIFI_INSTL_MBY`	TEXT	NULL,
	`X_SWIFI_SVC_SE`	TEXT	NULL,
	`X_SWIFI_CMCWR`	TEXT	NULL,
	`X_SWIFI_CNSTC_YEAR`	TEXT	NULL,
	`X_SWIFI_INOUT_DOOR`	TEXT	NULL,
	`X_SWIFI_REMARS3`	TEXT	NULL,
	`LAT`	TEXT	NULL,
	`LNT`	TEXT	NULL,
	`WORK_DTTM`	TEXT	NULL,
	`DISTANCE`	TEXT	NULL
);

CREATE TABLE `locHistory` (
	`ID`	INTEGER	NOT NULL,
	`X`	TEXT	NULL,
	`Y`	TEXT	NULL,
	`SEARCH_DATE`	TEXT	NULL
);

CREATE TABLE `Bookmark` (
	`ID`	INTEGER	NOT NULL,
	`NAME`	TEXT	NULL,
	`NUM`	TEXT	NULL,
	`GRP_REGISTER_DATE`	TEXT	NULL,
	`BMK_REGISTER_DATE`	TEXT	NULL,
	`GRP_MODIFY_DATE`	TEXT	NULL,
	`X_SWIFI_MGR_NO`	TEXT	NOT NULL
);

ALTER TABLE `TbPublicWifiInfo` ADD CONSTRAINT `PK_TBPUBLICWIFIINFO` PRIMARY KEY (
	`X_SWIFI_MGR_NO`
);

ALTER TABLE `locHistory` ADD CONSTRAINT `PK_LOCHISTORY` PRIMARY KEY (
	`ID`
);

ALTER TABLE `Bookmark` ADD CONSTRAINT `PK_BOOKMARK` PRIMARY KEY (
	`ID`
);

