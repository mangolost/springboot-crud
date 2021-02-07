CREATE DATABASE db_test
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Chinese (Simplified)_China.936'
    LC_CTYPE = 'Chinese (Simplified)_China.936'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

COMMENT ON DATABASE db_test
    IS 'db_test数据库';

CREATE TABLE public.t_employee
(
    id integer NOT NULL DEFAULT nextval('t_employee_id_seq'::regclass),
    create_time timestamp without time zone NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
    update_time timestamp without time zone NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
    record_status boolean NOT NULL DEFAULT true,
    employee_no text COLLATE pg_catalog."default" NOT NULL,
    employee_name text COLLATE pg_catalog."default" NOT NULL,
    age smallint NOT NULL DEFAULT 0,
    "position" text COLLATE pg_catalog."default" NOT NULL,
    degree text COLLATE pg_catalog."default" NOT NULL,
    remark text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT t_employee_pkey PRIMARY KEY (id),
    CONSTRAINT t_employee_employee_no_key UNIQUE (employee_no)
)