/*
 Navicat Premium Data Transfer

 Source Server         : online-code
 Source Server Type    : PostgreSQL
 Source Server Version : 130001
 Source Host           : localhost:5432
 Source Catalog        : ecommerce
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 130001
 File Encoding         : 65001

 Date: 12/03/2021 22:38:21
*/


-- ----------------------------
-- Sequence structure for authority_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."authority_id_seq";
CREATE SEQUENCE "public"."authority_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for education_info_sequence
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."education_info_sequence";
CREATE SEQUENCE "public"."education_info_sequence" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for emergency_contact_sequence
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."emergency_contact_sequence";
CREATE SEQUENCE "public"."emergency_contact_sequence" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for family_information_sequence
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."family_information_sequence";
CREATE SEQUENCE "public"."family_information_sequence" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for main_category_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."main_category_id_seq";
CREATE SEQUENCE "public"."main_category_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for personal_information_details_sequence
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."personal_information_details_sequence";
CREATE SEQUENCE "public"."personal_information_details_sequence" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for personal_information_sequence
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."personal_information_sequence";
CREATE SEQUENCE "public"."personal_information_sequence" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for product_description_sequence
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."product_description_sequence";
CREATE SEQUENCE "public"."product_description_sequence" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for product_details_references_sequence
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."product_details_references_sequence";
CREATE SEQUENCE "public"."product_details_references_sequence" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for sequence_category
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."sequence_category";
CREATE SEQUENCE "public"."sequence_category" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for sequence_product
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."sequence_product";
CREATE SEQUENCE "public"."sequence_product" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for sequence_user_
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."sequence_user_";
CREATE SEQUENCE "public"."sequence_user_" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 5
CACHE 1;

-- ----------------------------
-- Sequence structure for sequence_vendor
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."sequence_vendor";
CREATE SEQUENCE "public"."sequence_vendor" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for serial_t
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."serial_t";
CREATE SEQUENCE "public"."serial_t" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 101
CACHE 1;

-- ----------------------------
-- Sequence structure for success_login_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."success_login_id_seq";
CREATE SEQUENCE "public"."success_login_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for t1
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."t1";
CREATE SEQUENCE "public"."t1" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for user__id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."user__id_seq";
CREATE SEQUENCE "public"."user__id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for user_info_details_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."user_info_details_id_seq";
CREATE SEQUENCE "public"."user_info_details_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 32767
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for user_info_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."user_info_id_seq";
CREATE SEQUENCE "public"."user_info_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for user_lock_count_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."user_lock_count_id_seq";
CREATE SEQUENCE "public"."user_lock_count_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS "public"."address";
CREATE TABLE "public"."address" (
  "id" int4 NOT NULL,
  " street" varchar(225) COLLATE "pg_catalog"."default" NOT NULL,
  "sang_kat" varchar(225) COLLATE "pg_catalog"."default" NOT NULL,
  "khan" varchar(225) COLLATE "pg_catalog"."default" NOT NULL,
  "state" varchar(10) COLLATE "pg_catalog"."default" NOT NULL,
  "status" varchar(225) COLLATE "pg_catalog"."default" NOT NULL,
  "create_dete" varchar(8) COLLATE "pg_catalog"."default" NOT NULL,
  "create_by" int4 NOT NULL,
  "modify_date" varchar(8) COLLATE "pg_catalog"."default" NOT NULL,
  "modify_by" int4 NOT NULL
)
;

-- ----------------------------
-- Table structure for authority
-- ----------------------------
DROP TABLE IF EXISTS "public"."authority";
CREATE TABLE "public"."authority" (
  "id" int8 NOT NULL DEFAULT nextval('authority_id_seq'::regclass),
  "name" varchar(255) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Table structure for card_identify
-- ----------------------------
DROP TABLE IF EXISTS "public"."card_identify";
CREATE TABLE "public"."card_identify" (
  "id" varchar COLLATE "pg_catalog"."default" NOT NULL,
  "card_id" varchar COLLATE "pg_catalog"."default",
  "card_img_front" varchar(255) COLLATE "pg_catalog"."default",
  "card_img_rear" varchar(255) COLLATE "pg_catalog"."default",
  "create_by" int4,
  "create_date" varchar COLLATE "pg_catalog"."default",
  "modify_by" int4,
  "modify_date" varchar COLLATE "pg_catalog"."default",
  "status" varchar(255) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS "public"."category";
CREATE TABLE "public"."category" (
  "id" int4 NOT NULL,
  "name" varchar(225) COLLATE "pg_catalog"."default" NOT NULL,
  "description" varchar(225) COLLATE "pg_catalog"."default" NOT NULL,
  "create_date" varchar(8) COLLATE "pg_catalog"."default",
  "create_by" int4,
  "modify_date" varchar(8) COLLATE "pg_catalog"."default",
  "modify_by" int4,
  "status" varchar(10) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS "public"."customer";
CREATE TABLE "public"."customer" (
  "id" int4 NOT NULL,
  "first_name" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "last_name" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "birth_date" varchar(8) COLLATE "pg_catalog"."default" NOT NULL,
  "contact" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "email" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "status" varchar(8) COLLATE "pg_catalog"."default" NOT NULL,
  "create_date" varchar(8) COLLATE "pg_catalog"."default" NOT NULL,
  "create_by" int4 NOT NULL,
  "modify_date" varchar(8) COLLATE "pg_catalog"."default" NOT NULL,
  "modify_by" int4 NOT NULL
)
;

-- ----------------------------
-- Table structure for education_info
-- ----------------------------
DROP TABLE IF EXISTS "public"."education_info";
CREATE TABLE "public"."education_info" (
  "id" int4 NOT NULL,
  "institution" varchar(255) COLLATE "pg_catalog"."default",
  "subject" varchar(255) COLLATE "pg_catalog"."default",
  "starting_date" varchar(10) COLLATE "pg_catalog"."default",
  "complete_date" varchar(10) COLLATE "pg_catalog"."default",
  "degree" varchar(255) COLLATE "pg_catalog"."default",
  "grade" varchar(255) COLLATE "pg_catalog"."default",
  "status" varchar(255) COLLATE "pg_catalog"."default",
  "create_by" int4,
  "create_date" varchar(10) COLLATE "pg_catalog"."default",
  "modify_by" int4,
  "modify_date" varchar(10) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Table structure for emergency_contact
-- ----------------------------
DROP TABLE IF EXISTS "public"."emergency_contact";
CREATE TABLE "public"."emergency_contact" (
  "id" int4 NOT NULL,
  "name" varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
  "relationship" varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
  "phone" varchar(10) COLLATE "pg_catalog"."default" NOT NULL,
  "phone2" varchar(10) COLLATE "pg_catalog"."default",
  "create_date" varchar(10) COLLATE "pg_catalog"."default",
  "create_by" int4,
  "modify_date" varchar(10) COLLATE "pg_catalog"."default",
  "modify_by" int4
)
;

-- ----------------------------
-- Table structure for family_information
-- ----------------------------
DROP TABLE IF EXISTS "public"."family_information";
CREATE TABLE "public"."family_information" (
  "id" int4 NOT NULL,
  "name" varchar(100) COLLATE "pg_catalog"."default",
  "relationship" varchar(50) COLLATE "pg_catalog"."default",
  "phone" varchar(10) COLLATE "pg_catalog"."default",
  "description" varchar(150) COLLATE "pg_catalog"."default",
  "create_date" varchar(10) COLLATE "pg_catalog"."default",
  "create_by" int4,
  "modify_date" varchar(10) COLLATE "pg_catalog"."default",
  "modify_by" int4
)
;

-- ----------------------------
-- Table structure for import_product
-- ----------------------------
DROP TABLE IF EXISTS "public"."import_product";
CREATE TABLE "public"."import_product" (
  "id" int4 NOT NULL,
  "product_id" int4 NOT NULL,
  "vendor_id" int4 NOT NULL,
  "qty" int4 NOT NULL,
  "unit_price" int4 NOT NULL,
  "discount" int4 NOT NULL,
  "currency_code" varchar(8) COLLATE "pg_catalog"."default" NOT NULL,
  "status" varchar(8) COLLATE "pg_catalog"."default" NOT NULL,
  "create_date" varchar(8) COLLATE "pg_catalog"."default" NOT NULL,
  "create_by" int4 NOT NULL,
  "modify_date" varchar(8) COLLATE "pg_catalog"."default" NOT NULL,
  "modify_by" int4 NOT NULL
)
;

-- ----------------------------
-- Table structure for link_product_to_details
-- ----------------------------
DROP TABLE IF EXISTS "public"."link_product_to_details";
CREATE TABLE "public"."link_product_to_details" (
  "id" int4 NOT NULL,
  "product_id" int4 NOT NULL,
  "resource_id" int4 NOT NULL,
  "product_details_id" int4 NOT NULL,
  "create_date" varchar(8) COLLATE "pg_catalog"."default" NOT NULL,
  "create_by" int4 NOT NULL,
  "modify_date" varchar(8) COLLATE "pg_catalog"."default" NOT NULL,
  "modify_by" int4 NOT NULL
)
;

-- ----------------------------
-- Table structure for main_category
-- ----------------------------
DROP TABLE IF EXISTS "public"."main_category";
CREATE TABLE "public"."main_category" (
  "name" varchar(225) COLLATE "pg_catalog"."default" NOT NULL,
  "status" varchar(225) COLLATE "pg_catalog"."default" NOT NULL,
  "create_by" int4,
  "create_date" varchar(8) COLLATE "pg_catalog"."default",
  "modify_by" int4,
  "modify_date" varchar(8) COLLATE "pg_catalog"."default",
  "description" varchar(225) COLLATE "pg_catalog"."default",
  "id" int4 NOT NULL DEFAULT nextval('main_category_id_seq'::regclass)
)
;

-- ----------------------------
-- Table structure for oauth_access_token
-- ----------------------------
DROP TABLE IF EXISTS "public"."oauth_access_token";
CREATE TABLE "public"."oauth_access_token" (
  "token_id" varchar(255) COLLATE "pg_catalog"."default",
  "token" bytea,
  "authentication_id" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "user_name" varchar(255) COLLATE "pg_catalog"."default",
  "client_id" varchar(255) COLLATE "pg_catalog"."default",
  "authentication" bytea,
  "refresh_token" varchar(255) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Table structure for oauth_approvals
-- ----------------------------
DROP TABLE IF EXISTS "public"."oauth_approvals";
CREATE TABLE "public"."oauth_approvals" (
  "userid" varchar(255) COLLATE "pg_catalog"."default",
  "clientid" varchar(255) COLLATE "pg_catalog"."default",
  "scope" varchar(255) COLLATE "pg_catalog"."default",
  "status" varchar(10) COLLATE "pg_catalog"."default",
  "expiresat" timestamp(6),
  "lastmodifiedat" timestamp(6)
)
;

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS "public"."oauth_client_details";
CREATE TABLE "public"."oauth_client_details" (
  "client_id" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "resource_ids" varchar(255) COLLATE "pg_catalog"."default",
  "client_secret" varchar(255) COLLATE "pg_catalog"."default",
  "scope" varchar(255) COLLATE "pg_catalog"."default",
  "authorized_grant_types" varchar(255) COLLATE "pg_catalog"."default",
  "web_server_redirect_uri" varchar(255) COLLATE "pg_catalog"."default",
  "authorities" varchar(255) COLLATE "pg_catalog"."default",
  "access_token_validity" int4,
  "refresh_token_validity" int4,
  "additional_information" varchar(4096) COLLATE "pg_catalog"."default",
  "autoapprove" varchar(255) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Table structure for oauth_client_token
-- ----------------------------
DROP TABLE IF EXISTS "public"."oauth_client_token";
CREATE TABLE "public"."oauth_client_token" (
  "token_id" varchar(255) COLLATE "pg_catalog"."default",
  "token" bytea,
  "authentication_id" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "user_name" varchar(255) COLLATE "pg_catalog"."default",
  "client_id" varchar(255) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Table structure for oauth_code
-- ----------------------------
DROP TABLE IF EXISTS "public"."oauth_code";
CREATE TABLE "public"."oauth_code" (
  "code" varchar(255) COLLATE "pg_catalog"."default",
  "authentication" bytea
)
;

-- ----------------------------
-- Table structure for oauth_refresh_token
-- ----------------------------
DROP TABLE IF EXISTS "public"."oauth_refresh_token";
CREATE TABLE "public"."oauth_refresh_token" (
  "token_id" varchar(255) COLLATE "pg_catalog"."default",
  "token" bytea,
  "authentication" bytea
)
;

-- ----------------------------
-- Table structure for payment
-- ----------------------------
DROP TABLE IF EXISTS "public"."payment";
CREATE TABLE "public"."payment" (
  "id" int4 NOT NULL,
  "purchase_id" int4 NOT NULL,
  "customer_id" int4 NOT NULL,
  "user_id" int4 NOT NULL,
  "payment_id" varchar(225) COLLATE "pg_catalog"."default" NOT NULL,
  "approval_status" varchar(10) COLLATE "pg_catalog"."default" NOT NULL,
  "create_date" varchar(8) COLLATE "pg_catalog"."default" NOT NULL,
  "create_by" int4 NOT NULL,
  "modify_date" varchar(8) COLLATE "pg_catalog"."default" NOT NULL,
  "modify_by" int4 NOT NULL
)
;

-- ----------------------------
-- Table structure for personal_information
-- ----------------------------
DROP TABLE IF EXISTS "public"."personal_information";
CREATE TABLE "public"."personal_information" (
  "id" int4 NOT NULL,
  "first_name" varchar(100) COLLATE "pg_catalog"."default",
  "last_name" varchar(100) COLLATE "pg_catalog"."default",
  "phone" varchar(10) COLLATE "pg_catalog"."default",
  "email" varchar(100) COLLATE "pg_catalog"."default",
  "birthday" varchar(10) COLLATE "pg_catalog"."default",
  "gender" varchar(6) COLLATE "pg_catalog"."default",
  "address" varchar(150) COLLATE "pg_catalog"."default",
  "reports_to" int4,
  "national_id" int4,
  "nationality" varchar(255) COLLATE "pg_catalog"."default",
  "religion" varchar(255) COLLATE "pg_catalog"."default",
  "marital_status" varchar(255) COLLATE "pg_catalog"."default",
  "description" varchar(255) COLLATE "pg_catalog"."default",
  "resource_image_id" int4,
  "create_date" varchar(10) COLLATE "pg_catalog"."default",
  "create_by" int4,
  "modify_date" varchar(10) COLLATE "pg_catalog"."default",
  "modify_by" int4
)
;

-- ----------------------------
-- Table structure for personal_information_details
-- ----------------------------
DROP TABLE IF EXISTS "public"."personal_information_details";
CREATE TABLE "public"."personal_information_details" (
  "family_information_id" int4 NOT NULL,
  "personal_information_id" int4 NOT NULL,
  "emergency_contact_id" int4 NOT NULL
)
;

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS "public"."product";
CREATE TABLE "public"."product" (
  "id" int4 NOT NULL,
  "category_id" int4 NOT NULL,
  "name" varchar(225) COLLATE "pg_catalog"."default" NOT NULL,
  "status" varchar(10) COLLATE "pg_catalog"."default" NOT NULL,
  "create_date" varchar(8) COLLATE "pg_catalog"."default",
  "create_by" int4,
  "modify_date" varchar(8) COLLATE "pg_catalog"."default",
  "modify_by" int4,
  "resource_img_id" varchar(100) COLLATE "pg_catalog"."default",
  "description" varchar(255) COLLATE "pg_catalog"."default",
  "web_show" bool,
  "mobile_show" bool
)
;

-- ----------------------------
-- Table structure for product_description
-- ----------------------------
DROP TABLE IF EXISTS "public"."product_description";
CREATE TABLE "public"."product_description" (
  "id" varchar COLLATE "pg_catalog"."default" NOT NULL,
  "khmer_value" text COLLATE "pg_catalog"."default",
  "chinese_value" text COLLATE "pg_catalog"."default",
  "english_value" text COLLATE "pg_catalog"."default",
  "create_date" varchar COLLATE "pg_catalog"."default",
  "create_by" varchar(255) COLLATE "pg_catalog"."default",
  "modify_date" varchar COLLATE "pg_catalog"."default",
  "modify_by" varchar(255) COLLATE "pg_catalog"."default",
  "status" varchar(255) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Table structure for product_details_references
-- ----------------------------
DROP TABLE IF EXISTS "public"."product_details_references";
CREATE TABLE "public"."product_details_references" (
  "id" varchar(32) COLLATE "pg_catalog"."default" NOT NULL,
  "product_id" int4,
  "resource_id" varchar COLLATE "pg_catalog"."default",
  "product_details_id" varchar COLLATE "pg_catalog"."default",
  "product_detials_id" int4
)
;

-- ----------------------------
-- Table structure for product_detials
-- ----------------------------
DROP TABLE IF EXISTS "public"."product_detials";
CREATE TABLE "public"."product_detials" (
  "id" varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
  "product_id" int4 NOT NULL,
  "context_en" text COLLATE "pg_catalog"."default" NOT NULL,
  "context_kh" text COLLATE "pg_catalog"."default" NOT NULL,
  "context_ch" text COLLATE "pg_catalog"."default" NOT NULL,
  "status" varchar(225) COLLATE "pg_catalog"."default" NOT NULL,
  "create_date" varchar(8) COLLATE "pg_catalog"."default" NOT NULL,
  "create_by" int4 NOT NULL,
  "modify_date" varchar(8) COLLATE "pg_catalog"."default" NOT NULL,
  "modify_by" int4 NOT NULL
)
;

-- ----------------------------
-- Table structure for purchase
-- ----------------------------
DROP TABLE IF EXISTS "public"."purchase";
CREATE TABLE "public"."purchase" (
  "id" int4 NOT NULL,
  "transaction_details_id" int4 NOT NULL,
  "customer_id" int4 NOT NULL,
  "shipping_id" int4 NOT NULL,
  "amount" numeric(42) NOT NULL,
  "free" numeric(22) NOT NULL,
  "status" varchar(8) COLLATE "pg_catalog"."default" NOT NULL,
  "create_date" varchar(8) COLLATE "pg_catalog"."default" NOT NULL,
  "error_code" varchar(8) COLLATE "pg_catalog"."default" NOT NULL,
  "error_message" varchar(225) COLLATE "pg_catalog"."default" NOT NULL,
  "approval_code" varchar(10) COLLATE "pg_catalog"."default" NOT NULL,
  "approval_status" varchar(10) COLLATE "pg_catalog"."default" NOT NULL,
  "pg_code" varchar(10) COLLATE "pg_catalog"."default" NOT NULL,
  "pg_error_message" varchar(225) COLLATE "pg_catalog"."default" NOT NULL,
  "pg_approval_code" varchar(225) COLLATE "pg_catalog"."default" NOT NULL
)
;

-- ----------------------------
-- Table structure for resource_img
-- ----------------------------
DROP TABLE IF EXISTS "public"."resource_img";
CREATE TABLE "public"."resource_img" (
  "id" varchar(200) COLLATE "pg_catalog"."default" NOT NULL,
  "status" varchar(8) COLLATE "pg_catalog"."default" NOT NULL,
  "create_date" varchar(8) COLLATE "pg_catalog"."default",
  "create_by" int4,
  "modify_date" varchar(8) COLLATE "pg_catalog"."default",
  "modify_by" int4,
  "file_source" text COLLATE "pg_catalog"."default",
  "file_name" varchar(255) COLLATE "pg_catalog"."default",
  "file_size" int4,
  "file_extension" varchar(10) COLLATE "pg_catalog"."default",
  "file_type" varchar(255) COLLATE "pg_catalog"."default",
  "original_name" varchar(255) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Table structure for shipping
-- ----------------------------
DROP TABLE IF EXISTS "public"."shipping";
CREATE TABLE "public"."shipping" (
  "id" int4 NOT NULL,
  "first_name" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "last_name" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "address_line1" varchar(225) COLLATE "pg_catalog"."default" NOT NULL,
  "address_line2" varchar(225) COLLATE "pg_catalog"."default" NOT NULL,
  "country" varchar(80) COLLATE "pg_catalog"."default" NOT NULL,
  "state" varchar(80) COLLATE "pg_catalog"."default" NOT NULL,
  "city" varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
  "postal_code" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "phone_number" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "status" varchar(10) COLLATE "pg_catalog"."default" NOT NULL,
  "create_date" varchar(8) COLLATE "pg_catalog"."default" NOT NULL,
  "modify_date" varchar(8) COLLATE "pg_catalog"."default" NOT NULL
)
;

-- ----------------------------
-- Table structure for stock_details
-- ----------------------------
DROP TABLE IF EXISTS "public"."stock_details";
CREATE TABLE "public"."stock_details" (
  "id" int4 NOT NULL,
  "import_product_id" int4 NOT NULL,
  "product_id" int4 NOT NULL,
  "stock_in_id" int4 NOT NULL,
  "unit_qty" int4 NOT NULL,
  "unit_price" int4 NOT NULL,
  "unit_discount" int4 NOT NULL,
  "to_stock_in_qty" int4 NOT NULL,
  "qty" int4 NOT NULL,
  "profit_rate" int4 NOT NULL,
  "price" int4 NOT NULL,
  "status" varchar(10) COLLATE "pg_catalog"."default" NOT NULL,
  "create_date" varchar(8) COLLATE "pg_catalog"."default" NOT NULL,
  "create_by" int4 NOT NULL,
  "modify_date" varchar(8) COLLATE "pg_catalog"."default" NOT NULL,
  "modify_by" int4 NOT NULL
)
;

-- ----------------------------
-- Table structure for stock_in
-- ----------------------------
DROP TABLE IF EXISTS "public"."stock_in";
CREATE TABLE "public"."stock_in" (
  "id" int4 NOT NULL,
  "product_id" int4 NOT NULL,
  "qty" int4 NOT NULL,
  "price" numeric(102) NOT NULL,
  "discount" numeric(22) NOT NULL,
  "profit_rate" numeric(22) NOT NULL,
  "status" varchar(8) COLLATE "pg_catalog"."default" NOT NULL,
  "create_date" varchar(8) COLLATE "pg_catalog"."default" NOT NULL,
  "create_by" int4 NOT NULL,
  "modify_date" varchar(8) COLLATE "pg_catalog"."default" NOT NULL,
  "modify_by" int4 NOT NULL
)
;

-- ----------------------------
-- Table structure for success_login
-- ----------------------------
DROP TABLE IF EXISTS "public"."success_login";
CREATE TABLE "public"."success_login" (
  "id" int4 NOT NULL DEFAULT nextval('success_login_id_seq'::regclass),
  "user_agent" varchar(255) COLLATE "pg_catalog"."default",
  "os" varchar(255) COLLATE "pg_catalog"."default",
  "browser" varchar(255) COLLATE "pg_catalog"."default",
  "device" varchar(255) COLLATE "pg_catalog"."default",
  "os_version" varchar(255) COLLATE "pg_catalog"."default",
  "browser_version" varchar(255) COLLATE "pg_catalog"."default",
  "device_type" varchar(255) COLLATE "pg_catalog"."default",
  "orientation" varchar(255) COLLATE "pg_catalog"."default",
  "network_ip" varchar(255) COLLATE "pg_catalog"."default",
  "user_name" varchar(255) COLLATE "pg_catalog"."default",
  "date" varchar(255) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Table structure for t
-- ----------------------------
DROP TABLE IF EXISTS "public"."t";
CREATE TABLE "public"."t" (
  "id" int4,
  "name" text COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Table structure for test_decrypt
-- ----------------------------
DROP TABLE IF EXISTS "public"."test_decrypt";
CREATE TABLE "public"."test_decrypt" (
  "id" varchar(225) COLLATE "pg_catalog"."default" NOT NULL,
  "name" varchar(255) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Table structure for transaction
-- ----------------------------
DROP TABLE IF EXISTS "public"."transaction";
CREATE TABLE "public"."transaction" (
  "id" int4 NOT NULL,
  "product_id" int4 NOT NULL,
  "qty" int4 NOT NULL,
  "price" numeric(4,2) NOT NULL,
  "discount" numeric(22) NOT NULL,
  "total" numeric(4,2) NOT NULL,
  "status" varchar(8) COLLATE "pg_catalog"."default" NOT NULL,
  "create_date" varchar(8) COLLATE "pg_catalog"."default" NOT NULL,
  "create_by" int4 NOT NULL,
  "modify_date" varchar(8) COLLATE "pg_catalog"."default" NOT NULL,
  "modify_by" int4 NOT NULL
)
;

-- ----------------------------
-- Table structure for transaction_details
-- ----------------------------
DROP TABLE IF EXISTS "public"."transaction_details";
CREATE TABLE "public"."transaction_details" (
  "id" int4 NOT NULL,
  "transaction_id" int4 NOT NULL,
  "stock_in_id" int4 NOT NULL,
  "create_date" varchar(8) COLLATE "pg_catalog"."default" NOT NULL
)
;

-- ----------------------------
-- Table structure for user_
-- ----------------------------
DROP TABLE IF EXISTS "public"."user_";
CREATE TABLE "public"."user_" (
  "id" int8 NOT NULL DEFAULT nextval('user__id_seq'::regclass),
  "account_expired" bool,
  "account_locked" bool,
  "credentials_expired" bool,
  "enabled" bool,
  "password" varchar(255) COLLATE "pg_catalog"."default",
  "user_name" varchar(255) COLLATE "pg_catalog"."default",
  "status" varchar COLLATE "pg_catalog"."default",
  "create_date" varchar(8) COLLATE "pg_catalog"."default",
  "create_by" int4,
  "modify_date" varchar(8) COLLATE "pg_catalog"."default",
  "modify_by" int4,
  "last_login_date" varchar(8) COLLATE "pg_catalog"."default",
  "last_login_time" varchar(8) COLLATE "pg_catalog"."default",
  "is_first_login" bool
)
;
COMMENT ON COLUMN "public"."user_"."account_expired" IS 'Account Expired';
COMMENT ON COLUMN "public"."user_"."account_locked" IS 'Account Locked';
COMMENT ON COLUMN "public"."user_"."credentials_expired" IS 'Credentials Expired';
COMMENT ON COLUMN "public"."user_"."enabled" IS 'Enable';
COMMENT ON COLUMN "public"."user_"."password" IS 'Password';
COMMENT ON COLUMN "public"."user_"."user_name" IS 'User Name';
COMMENT ON COLUMN "public"."user_"."status" IS 'Status';
COMMENT ON COLUMN "public"."user_"."create_date" IS 'Create Date';
COMMENT ON COLUMN "public"."user_"."create_by" IS 'Create By';
COMMENT ON COLUMN "public"."user_"."modify_date" IS 'Modify Date';
COMMENT ON COLUMN "public"."user_"."modify_by" IS 'Modify By';

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS "public"."user_info";
CREATE TABLE "public"."user_info" (
  "id" varchar(150) COLLATE "pg_catalog"."default" NOT NULL DEFAULT nextval('user_info_id_seq'::regclass),
  "first_name" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "last_name" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "gender" varchar(10) COLLATE "pg_catalog"."default" NOT NULL,
  "date_birth" varchar(8) COLLATE "pg_catalog"."default" NOT NULL,
  "email" varchar(150) COLLATE "pg_catalog"."default" NOT NULL,
  "contact" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "profile_resource_img_id" varchar(150) COLLATE "pg_catalog"."default",
  "status" varchar(10) COLLATE "pg_catalog"."default" NOT NULL,
  "create_date" varchar(8) COLLATE "pg_catalog"."default",
  "create_by" int4,
  "modify_date" varchar(8) COLLATE "pg_catalog"."default",
  "modify_by" int4,
  "description" varchar(150) COLLATE "pg_catalog"."default",
  "address" varchar(150) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Table structure for user_info_details
-- ----------------------------
DROP TABLE IF EXISTS "public"."user_info_details";
CREATE TABLE "public"."user_info_details" (
  "id" int2 NOT NULL DEFAULT nextval('user_info_details_id_seq'::regclass),
  "card_identify_id" varchar(255) COLLATE "pg_catalog"."default",
  "user_info_id" varchar(255) COLLATE "pg_catalog"."default",
  "user_id" int4
)
;

-- ----------------------------
-- Table structure for user_lock_count
-- ----------------------------
DROP TABLE IF EXISTS "public"."user_lock_count";
CREATE TABLE "public"."user_lock_count" (
  "id" int4 NOT NULL DEFAULT nextval('user_lock_count_id_seq'::regclass),
  "user_name" varchar COLLATE "pg_catalog"."default" NOT NULL,
  "count" int4 NOT NULL,
  "date" varchar COLLATE "pg_catalog"."default" NOT NULL,
  "status" varchar COLLATE "pg_catalog"."default" NOT NULL,
  "message" varchar COLLATE "pg_catalog"."default" NOT NULL,
  "islocked" bool NOT NULL DEFAULT false,
  "time" varchar COLLATE "pg_catalog"."default"
)
;
COMMENT ON COLUMN "public"."user_lock_count"."id" IS 'ID';
COMMENT ON COLUMN "public"."user_lock_count"."user_name" IS 'User Name';
COMMENT ON COLUMN "public"."user_lock_count"."count" IS 'count user lock';
COMMENT ON COLUMN "public"."user_lock_count"."date" IS 'Date Time';
COMMENT ON COLUMN "public"."user_lock_count"."status" IS 'Status';
COMMENT ON COLUMN "public"."user_lock_count"."message" IS 'Message';
COMMENT ON COLUMN "public"."user_lock_count"."islocked" IS 'Is Lock User';
COMMENT ON TABLE "public"."user_lock_count" IS 'Lock User login wrong user or password';

-- ----------------------------
-- Table structure for users_authorities
-- ----------------------------
DROP TABLE IF EXISTS "public"."users_authorities";
CREATE TABLE "public"."users_authorities" (
  "user_id" int8 NOT NULL,
  "authority_id" int8 NOT NULL
)
;

-- ----------------------------
-- Table structure for vendor
-- ----------------------------
DROP TABLE IF EXISTS "public"."vendor";
CREATE TABLE "public"."vendor" (
  "id" int4 NOT NULL,
  "name" varchar(225) COLLATE "pg_catalog"."default" NOT NULL,
  "address" varchar(225) COLLATE "pg_catalog"."default",
  "contact" varchar(225) COLLATE "pg_catalog"."default" NOT NULL,
  "email" varchar(225) COLLATE "pg_catalog"."default",
  "status" varchar(8) COLLATE "pg_catalog"."default",
  "create_date" varchar(8) COLLATE "pg_catalog"."default",
  "create_by" int4,
  "modify_date" varchar(8) COLLATE "pg_catalog"."default",
  "modify_by" int4,
  "description" varchar(255) COLLATE "pg_catalog"."default",
  "other_contact" varchar(255) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."authority_id_seq"
OWNED BY "public"."authority"."id";
SELECT setval('"public"."authority_id_seq"', 6, true);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
SELECT setval('"public"."education_info_sequence"', 2, false);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
SELECT setval('"public"."emergency_contact_sequence"', 2, false);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
SELECT setval('"public"."family_information_sequence"', 2, false);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."main_category_id_seq"
OWNED BY "public"."main_category"."id";
SELECT setval('"public"."main_category_id_seq"', 14, true);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
SELECT setval('"public"."personal_information_details_sequence"', 2, false);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
SELECT setval('"public"."personal_information_sequence"', 2, false);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."product_description_sequence"
OWNED BY "public"."product_description"."id";
SELECT setval('"public"."product_description_sequence"', 4, false);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."product_details_references_sequence"
OWNED BY "public"."product_details_references"."id";
SELECT setval('"public"."product_details_references_sequence"', 4, false);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
SELECT setval('"public"."sequence_category"', 94, true);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
SELECT setval('"public"."sequence_product"', 35, true);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
SELECT setval('"public"."sequence_user_"', 23, true);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
SELECT setval('"public"."sequence_vendor"', 11, true);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
SELECT setval('"public"."serial_t"', 105, true);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."success_login_id_seq"
OWNED BY "public"."success_login"."id";
SELECT setval('"public"."success_login_id_seq"', 135, true);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."t1"
OWNED BY "public"."address"."id";
SELECT setval('"public"."t1"', 4, false);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."user__id_seq"
OWNED BY "public"."user_"."id";
SELECT setval('"public"."user__id_seq"', 5, false);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."user_info_details_id_seq"
OWNED BY "public"."user_info_details"."id";
SELECT setval('"public"."user_info_details_id_seq"', 10, true);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."user_info_id_seq"
OWNED BY "public"."user_info"."id";
SELECT setval('"public"."user_info_id_seq"', 5, false);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."user_lock_count_id_seq"
OWNED BY "public"."user_lock_count"."id";
SELECT setval('"public"."user_lock_count_id_seq"', 22, true);

-- ----------------------------
-- Uniques structure for table authority
-- ----------------------------
ALTER TABLE "public"."authority" ADD CONSTRAINT "authority_name" UNIQUE ("name");

-- ----------------------------
-- Primary Key structure for table authority
-- ----------------------------
ALTER TABLE "public"."authority" ADD CONSTRAINT "authority_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table card_identify
-- ----------------------------
ALTER TABLE "public"."card_identify" ADD CONSTRAINT "card_identify_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table customer
-- ----------------------------
ALTER TABLE "public"."customer" ADD CONSTRAINT "pk_customer" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table education_info
-- ----------------------------
ALTER TABLE "public"."education_info" ADD CONSTRAINT "education_info_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table emergency_contact
-- ----------------------------
ALTER TABLE "public"."emergency_contact" ADD CONSTRAINT "emergency_contact_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table family_information
-- ----------------------------
ALTER TABLE "public"."family_information" ADD CONSTRAINT "family_information_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table oauth_access_token
-- ----------------------------
ALTER TABLE "public"."oauth_access_token" ADD CONSTRAINT "oauth_access_token_pkey" PRIMARY KEY ("authentication_id");

-- ----------------------------
-- Primary Key structure for table oauth_client_details
-- ----------------------------
ALTER TABLE "public"."oauth_client_details" ADD CONSTRAINT "oauth_client_details_pkey" PRIMARY KEY ("client_id");

-- ----------------------------
-- Primary Key structure for table oauth_client_token
-- ----------------------------
ALTER TABLE "public"."oauth_client_token" ADD CONSTRAINT "oauth_client_token_pkey" PRIMARY KEY ("authentication_id");

-- ----------------------------
-- Primary Key structure for table payment
-- ----------------------------
ALTER TABLE "public"."payment" ADD CONSTRAINT "pk_payment" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table personal_information
-- ----------------------------
ALTER TABLE "public"."personal_information" ADD CONSTRAINT "personal_information_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table product
-- ----------------------------
ALTER TABLE "public"."product" ADD CONSTRAINT "pk_product" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table product_description
-- ----------------------------
ALTER TABLE "public"."product_description" ADD CONSTRAINT "product_description_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table product_details_references
-- ----------------------------
ALTER TABLE "public"."product_details_references" ADD CONSTRAINT "product_details_references_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table product_detials
-- ----------------------------
ALTER TABLE "public"."product_detials" ADD CONSTRAINT "pk_product_detials" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table purchase
-- ----------------------------
ALTER TABLE "public"."purchase" ADD CONSTRAINT "pk_purchase" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table shipping
-- ----------------------------
ALTER TABLE "public"."shipping" ADD CONSTRAINT "pk_shipping" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table stock_details
-- ----------------------------
ALTER TABLE "public"."stock_details" ADD CONSTRAINT "pk_stock_details" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table stock_in
-- ----------------------------
ALTER TABLE "public"."stock_in" ADD CONSTRAINT "pk_stock_in" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table success_login
-- ----------------------------
ALTER TABLE "public"."success_login" ADD CONSTRAINT "success_login_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table test_decrypt
-- ----------------------------
ALTER TABLE "public"."test_decrypt" ADD CONSTRAINT "test_decrypt_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table transaction
-- ----------------------------
ALTER TABLE "public"."transaction" ADD CONSTRAINT "pk_transaction" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table transaction_details
-- ----------------------------
ALTER TABLE "public"."transaction_details" ADD CONSTRAINT "pk_transaction_details" PRIMARY KEY ("id");

-- ----------------------------
-- Uniques structure for table user_
-- ----------------------------
ALTER TABLE "public"."user_" ADD CONSTRAINT "user_user_name" UNIQUE ("user_name");

-- ----------------------------
-- Primary Key structure for table user_
-- ----------------------------
ALTER TABLE "public"."user_" ADD CONSTRAINT "user__pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table user_info
-- ----------------------------
ALTER TABLE "public"."user_info" ADD CONSTRAINT "user_info_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table user_info_details
-- ----------------------------
ALTER TABLE "public"."user_info_details" ADD CONSTRAINT "user_info_details_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table users_authorities
-- ----------------------------
ALTER TABLE "public"."users_authorities" ADD CONSTRAINT "users_authorities_pkey" PRIMARY KEY ("user_id", "authority_id");

-- ----------------------------
-- Foreign Keys structure for table users_authorities
-- ----------------------------
ALTER TABLE "public"."users_authorities" ADD CONSTRAINT "users_authorities_authority" FOREIGN KEY ("authority_id") REFERENCES "public"."authority" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."users_authorities" ADD CONSTRAINT "users_authorities_user_" FOREIGN KEY ("user_id") REFERENCES "public"."user_" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
