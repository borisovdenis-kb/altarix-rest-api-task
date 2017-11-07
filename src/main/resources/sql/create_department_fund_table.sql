-- ----------------------------
-- Table structure for department_fund
-- ----------------------------
DROP TABLE IF EXISTS "public"."department_fund";
CREATE TABLE "public"."department_fund" (
  "id" int8 NOT NULL,
  "fund" float8,
  "logging_date" timestamp(6) NOT NULL,
  "department_id" int8 NOT NULL
) WITH (OIDS=FALSE);


-- ----------------------------
-- Primary Key structure for table department_fund
-- ----------------------------
ALTER TABLE "public"."department_fund" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Foreign Key structure for table "public"."department_fund"
-- ----------------------------
ALTER TABLE "public"."department_fund" ADD FOREIGN KEY ("department_id")
REFERENCES "public"."department" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
