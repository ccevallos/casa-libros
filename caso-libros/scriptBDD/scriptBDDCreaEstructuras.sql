CREATE SEQUENCE public.libro_id_seq
    INCREMENT 1
    START 0
    MINVALUE 0
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.libro_id_seq
    OWNER TO usr_caso_libros;

CREATE TABLE public.libro
(
    id integer NOT NULL DEFAULT nextval('libro_id_seq'::regclass),
    titulo character varying(100) COLLATE pg_catalog."default" NOT NULL,
    autor character varying(100) COLLATE pg_catalog."default",
    resumen character varying(5000) COLLATE pg_catalog."default",
    estado character varying(8) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT libro_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.libro
    OWNER to usr_caso_libros;
	
CREATE SEQUENCE public.transaccion_id_seq
    INCREMENT 1
    START 0
    MINVALUE 0
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.transaccion_id_seq
    OWNER TO usr_caso_libros;

CREATE TABLE public.transaccion
(
    id integer NOT NULL DEFAULT nextval('transaccion_id_seq'::regclass),
    tipo character varying(10) COLLATE pg_catalog."default" NOT NULL,
	id_libro integer,
    fecha timestamp without time zone NOT NULL,
    CONSTRAINT transaccion_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.transaccion
    OWNER to usr_caso_libros;