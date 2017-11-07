-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS "public"."department";
CREATE TABLE "public"."department" (
  "id" int8 NOT NULL,
  "create_date" date NOT NULL,
  "name" varchar(255) COLLATE "default" NOT NULL,
  "department_id" int8
) WITH (OIDS=FALSE);

-- ----------------------------
-- Uniques structure for table department
-- ----------------------------
ALTER TABLE "public"."department" ADD UNIQUE ("name");

-- ----------------------------
-- Primary Key structure for table department
-- ----------------------------
ALTER TABLE "public"."department" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Foreign Key structure for table "public"."department"
-- ----------------------------
ALTER TABLE "public"."department" ADD FOREIGN KEY ("department_id")
REFERENCES "public"."department" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
