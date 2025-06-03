-- Backup Script
-- Run in terminal: mysqldump -u root -p cjs_db > cjs_db_backup.sql

-- Restore Script
-- Run in terminal: mysql -u root -p cjs_db < cjs_db_backup.sql

-- Manual SQL Backup Example
CREATE DATABASE cjs_db_backup;
USE cjs_db_backup;

-- Copy structure and data from cjs_db
CREATE TABLE users LIKE cjs_db.users;
INSERT INTO users SELECT * FROM cjs_db.users;

CREATE TABLE cases LIKE cjs_db.cases;
INSERT INTO cases SELECT * FROM cjs_db.cases;

CREATE TABLE criminals LIKE cjs_db.criminals;
INSERT INTO criminals SELECT * FROM cjs_db.criminals;

CREATE TABLE victims LIKE cjs_db.victims;
INSERT INTO victims SELECT * FROM cjs_db.victims;

CREATE TABLE witnesses LIKE cjs_db.witnesses;
INSERT INTO witnesses SELECT * FROM cjs_db.witnesses;

CREATE TABLE evidence LIKE cjs_db.evidence;
INSERT INTO evidence SELECT * FROM cjs_db.evidence;

CREATE TABLE court_hearings LIKE cjs_db.court_hearings;
INSERT INTO court_hearings SELECT * FROM cjs_db.court_hearings;

CREATE TABLE judgments LIKE cjs_db.judgments;
INSERT INTO judgments SELECT * FROM cjs_db.judgments;

CREATE TABLE notifications LIKE cjs_db.notifications;
INSERT INTO notifications SELECT * FROM cjs_db.notifications;

CREATE TABLE logs LIKE cjs_db.logs;
INSERT INTO logs SELECT * FROM cjs_db.logs;

-- Manual SQL Restore Example
-- Drop existing database and recreate
DROP DATABASE IF EXISTS cjs_db;
CREATE DATABASE cjs_db;
USE cjs_db;

-- Copy back from backup
CREATE TABLE users LIKE cjs_db_backup.users;
INSERT INTO users SELECT * FROM cjs_db_backup.users;

CREATE TABLE cases LIKE cjs_db_backup.cases;
INSERT INTO cases SELECT * FROM cjs_db_backup.cases;

CREATE TABLE criminals LIKE cjs_db_backup.criminals;
INSERT INTO criminals SELECT * FROM cjs_db_backup.criminals;

CREATE TABLE victims LIKE cjs_db_backup.victims;
INSERT INTO victims SELECT * FROM cjs_db_backup.victims;

CREATE TABLE witnesses LIKE cjs_db_backup.witnesses;
INSERT INTO witnesses SELECT * FROM cjs_db_backup.witnesses;

CREATE TABLE evidence LIKE cjs_db_backup.evidence;
INSERT INTO evidence SELECT * FROM cjs_db_backup.evidence;

CREATE TABLE court_hearings LIKE cjs_db_backup.court_hearings;
INSERT INTO court_hearings SELECT * FROM cjs_db_backup.court_hearings;

CREATE TABLE judgments LIKE cjs_db_backup.judgments;
INSERT INTO judgments SELECT * FROM cjs_db_backup.judgments;

CREATE TABLE notifications LIKE cjs_db_backup.notifications;
INSERT INTO notifications SELECT * FROM cjs_db_backup.notifications;

CREATE TABLE logs LIKE cjs_db_backup.logs;
INSERT INTO logs SELECT * FROM cjs_db_backup.logs;