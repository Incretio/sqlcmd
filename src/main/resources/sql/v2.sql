-- Sequence: public.database_connection_seq

-- DROP SEQUENCE public.database_connection_seq;

CREATE SEQUENCE public.database_connection_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE public.database_connection_seq
  OWNER TO postgres;

-- Table: public.database_connection

-- DROP TABLE public.database_connection;

CREATE TABLE public.database_connection
(
  id integer NOT NULL DEFAULT nextval('database_connection_seq'::regclass),
  db_name character varying(255),
  user_name character varying(255),
  CONSTRAINT database_connection_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.database_connection
  OWNER TO postgres;

-- Column: database_connection_id

-- ALTER TABLE public.useraction DROP COLUMN database_connection_id;

ALTER TABLE public.useraction ADD COLUMN database_connection_id integer;

-- Foreign Key: public.user_name_database_connection_fk

-- ALTER TABLE public.useraction DROP CONSTRAINT user_name_database_connection_fk;

ALTER TABLE public.useraction
  ADD CONSTRAINT user_name_database_connection_fk FOREIGN KEY (database_connection_id)
      REFERENCES public.database_connection (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION;

-- Index: public.fki_user_name_database_connection_fk

-- DROP INDEX public.fki_user_name_database_connection_fk;

CREATE INDEX fki_user_name_database_connection_fk
  ON public.useraction
  USING btree
  (database_connection_id);

-- Data migration

INSERT INTO database_connection (db_name, user_name)
SELECT DISTINCT dbname, username FROM useraction

UPDATE useraction
SET database_connection_id = subquery.id
FROM
(SELECT * FROM database_connection) AS subquery
WHERE useraction.dbname = subquery.db_name AND
    useraction.username = subquery.user_name

-- Drop unused columns
ALTER TABLE public.useraction DROP COLUMN dbname;
ALTER TABLE public.useraction DROP COLUMN username;


