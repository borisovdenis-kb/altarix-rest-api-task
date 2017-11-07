-- ----------------------------
-- Table structure for department_log
-- ----------------------------
DROP TABLE IF EXISTS "public"."department_log";
CREATE TABLE "public"."department_log" (
  "id" int8 NOT NULL,
  "logging_date" timestamp(6) NOT NULL,
  "operation_name" varchar(255) COLLATE "default" NOT NULL,
  "department_id" int8 NOT NULL
) WITH (OIDS=FALSE);

-- ----------------------------
-- Primary Key structure for table department_log
-- ----------------------------
ALTER TABLE "public"."department_log" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Foreign Key structure for table "public"."department_log"
-- ----------------------------
ALTER TABLE "public"."department_log" ADD FOREIGN KEY ("department_id")
REFERENCES "public"."department" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
