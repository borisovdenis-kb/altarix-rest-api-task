-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS "public"."employee";
CREATE TABLE "public"."employee" (
  "id" int8 NOT NULL,
  "birth_day" date NOT NULL,
  "dismissal_date" date,
  "email" varchar(255) COLLATE "default" NOT NULL,
  "employment_date" date NOT NULL,
  "first_name" varchar(255) COLLATE "default" NOT NULL,
  "gender" varchar(255) COLLATE "default" NOT NULL,
  "is_chief" bool NOT NULL,
  "last_name" varchar(255) COLLATE "default" NOT NULL,
  "middle_name" varchar(255) COLLATE "default",
  "phone" varchar(255) COLLATE "default" NOT NULL,
  "salary" float8 NOT NULL,
  "department_id" int8,
  "position_id" int8 NOT NULL
) WITH (OIDS=FALSE);

-- ----------------------------
-- Primary Key structure for table employee
-- ----------------------------
ALTER TABLE "public"."employee" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Foreign Key structure for table "public"."employee"
-- ----------------------------
ALTER TABLE "public"."employee" ADD FOREIGN KEY ("department_id")
REFERENCES "public"."department" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "public"."employee" ADD FOREIGN KEY ("position_id")
REFERENCES "public"."position" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
