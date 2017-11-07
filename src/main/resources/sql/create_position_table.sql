-- ----------------------------
-- Table structure for position
-- ----------------------------
DROP TABLE IF EXISTS "public"."position";
CREATE TABLE "public"."position" (
"id" int8 NOT NULL,
"name" varchar(255) COLLATE "default" NOT NULL
) WITH (OIDS=FALSE);

-- ----------------------------
-- Primary Key structure for table position
-- ----------------------------
ALTER TABLE "public"."position" ADD PRIMARY KEY ("id");
