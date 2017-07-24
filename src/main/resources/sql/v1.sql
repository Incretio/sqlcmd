-- Schema: public

-- DROP SCHEMA public;

CREATE SCHEMA public
  AUTHORIZATION postgres;

GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO public;
COMMENT ON SCHEMA public
  IS 'standard public schema';

-- Sequence: public.user_action_seq

-- DROP SEQUENCE public.user_action_seq;

CREATE SEQUENCE public.user_action_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 52
  CACHE 1;
ALTER TABLE public.user_action_seq
  OWNER TO postgres;

-- Database: sqlcmd_log

-- DROP DATABASE sqlcmd_log;

CREATE DATABASE sqlcmd_log
WITH OWNER = postgres
     ENCODING = 'UTF8'
     TABLESPACE = pg_default
     LC_COLLATE = 'Russian_Russia.1251'
     LC_CTYPE = 'Russian_Russia.1251'
     CONNECTION LIMIT = -1;

-- Table: public.useraction

-- DROP TABLE public.useraction;

CREATE TABLE public.useraction
(
  id integer NOT NULL DEFAULT nextval('user_action_seq'::regclass),
  action character varying(255),
  dbname character varying(255),
  username character varying(255),
  CONSTRAINT useraction_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.useraction
  OWNER TO postgres;

