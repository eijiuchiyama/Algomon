--
-- PostgreSQL database dump
--

-- Dumped from database version 14.11 (Ubuntu 14.11-0ubuntu0.22.04.1)
-- Dumped by pg_dump version 14.11 (Ubuntu 14.11-0ubuntu0.22.04.1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: adminpack; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS adminpack WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION adminpack; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION adminpack IS 'administrative functions for PostgreSQL';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: commonenemies; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.commonenemies (
    id integer,
    name character(30),
    basehp integer,
    basestamina integer,
    baseatk integer,
    basedef integer,
    basedodge integer,
    basespeed integer
);


ALTER TABLE public.commonenemies OWNER TO postgres;

--
-- Name: TABLE commonenemies; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.commonenemies IS 'Inimigos comuns';


--
-- Name: movements; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.movements (
    id integer,
    hpown integer,
    staminaown integer,
    atkown integer,
    defown integer,
    dodgeown integer,
    speedown integer,
    hpenemy integer,
    staminaenemy integer,
    atkenemy integer,
    defenemy integer,
    dodgeenemy integer,
    speedenemy integer,
    name character(30),
    minlevel integer,
    baseaccuracy integer
);


ALTER TABLE public.movements OWNER TO postgres;

--
-- Name: TABLE movements; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.movements IS 'Movimentos ';


--
-- Name: COLUMN movements.minlevel; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.movements.minlevel IS 'Level mínimo para utilizar o movimento';


--
-- Name: movements_baseaccuracy_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.movements_baseaccuracy_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.movements_baseaccuracy_seq OWNER TO postgres;

--
-- Name: movements_baseaccuracy_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.movements_baseaccuracy_seq OWNED BY public.movements.baseaccuracy;


--
-- Name: players; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.players (
    id integer,
    basehp integer,
    basestamina integer,
    baseatk integer,
    basedef integer,
    basedodge integer,
    basespeed integer
);


ALTER TABLE public.players OWNER TO postgres;

--
-- Name: TABLE players; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.players IS 'Jogador';


--
-- Name: specialenemies; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.specialenemies (
    id integer,
    name character(30),
    basehp integer,
    basestamina integer,
    baseatk integer,
    basedef integer,
    basedodge integer,
    basespeed integer
);


ALTER TABLE public.specialenemies OWNER TO postgres;

--
-- Name: TABLE specialenemies; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.specialenemies IS 'Inimigos especiais';


--
-- Data for Name: commonenemies; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.commonenemies VALUES (0, 'Inimigo fraco 1               ', 80, 65, 25, 15, 5, 30);
INSERT INTO public.commonenemies VALUES (1, 'Inimigo fraco 2               ', 70, 75, 17, 22, 10, 15);
INSERT INTO public.commonenemies VALUES (2, 'Inimigo muito fraco 1         ', 40, 30, 15, 18, 0, 20);
INSERT INTO public.commonenemies VALUES (3, 'Inimigo muito fraco 2         ', 50, 45, 10, 25, 0, 10);
INSERT INTO public.commonenemies VALUES (7, 'Inimigo meio fraco 1          ', 120, 80, 26, 28, 10, 5);
INSERT INTO public.commonenemies VALUES (4, 'Inimigo fraco e rápido        ', 80, 57, 22, 15, 5, 50);
INSERT INTO public.commonenemies VALUES (5, 'Inimigo estranho              ', 140, 120, 8, 7, 0, 17);
INSERT INTO public.commonenemies VALUES (6, 'Inimigo paia                  ', 20, 25, 0, 25, 22, 15);


--
-- Data for Name: movements; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.movements VALUES (0, 0, -10, 0, 0, 0, 0, -20, 0, 0, 0, 0, 0, 'Hacking 1.0                   ', 0, 100);
INSERT INTO public.movements VALUES (1, 0, -20, 0, 0, 0, 0, -35, 0, 0, 0, 0, 0, 'Hacking 2.0                   ', 2, 100);
INSERT INTO public.movements VALUES (2, 0, -35, 0, 0, 0, 0, -55, 0, 0, 0, 0, 0, 'Hacking 3.0                   ', 5, 100);
INSERT INTO public.movements VALUES (6, 65, -50, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 'Take a long nap               ', 7, 100);
INSERT INTO public.movements VALUES (7, -60, 40, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 'Recovery                      ', 7, 100);
INSERT INTO public.movements VALUES (8, 0, -10, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 'Correção simples de bug       ', 0, 90);
INSERT INTO public.movements VALUES (12, 0, -15, 0, 10, 0, 0, 0, 0, 0, 0, 0, 0, 'Criptografia básica           ', 0, 80);
INSERT INTO public.movements VALUES (16, 0, -10, 0, 0, 0, 10, 0, 0, 0, 0, 0, 0, 'Compactação simples           ', 2, 90);
INSERT INTO public.movements VALUES (18, 0, -50, 0, 0, 0, 50, 0, 0, 0, 0, 0, 0, 'Compactação avançada          ', 12, 70);
INSERT INTO public.movements VALUES (17, 0, -30, 0, 0, 0, 30, 0, 0, 0, 0, 0, 0, 'Compactação média             ', 8, 80);
INSERT INTO public.movements VALUES (23, 0, -18, 0, 0, 0, 0, 0, 0, 0, -12, 0, 0, 'Descriptografar 1.0           ', 0, 80);
INSERT INTO public.movements VALUES (19, 0, -12, 0, 0, 0, 0, 0, 0, -7, 0, 0, 0, 'Destruir projeto 1.0          ', 0, 90);
INSERT INTO public.movements VALUES (28, 0, -50, 0, 0, 0, 0, 0, 0, 0, 0, -30, 0, 'Path-finding avançado         ', 9, 80);
INSERT INTO public.movements VALUES (27, 0, -25, 0, 0, 0, 0, 0, 0, 0, 0, 15, 0, 'Path-finding simples          ', 4, 90);
INSERT INTO public.movements VALUES (9, 0, -30, 18, 0, 0, 0, 0, 0, 0, 0, 0, 0, 'Correção eficiente de bugs    ', 4, 90);
INSERT INTO public.movements VALUES (10, 0, -50, 35, 0, 0, 0, 0, 0, 0, 0, 0, 0, 'Correção extensa de bugs      ', 9, 85);
INSERT INTO public.movements VALUES (11, 0, -75, 55, 0, 0, 0, 0, 0, 0, 0, 0, 0, 'Correção completa de bugs     ', 11, 80);
INSERT INTO public.movements VALUES (13, 0, -32, 0, 22, 0, 0, 0, 0, 0, 0, 0, 0, 'Criptografia segura           ', 3, 85);
INSERT INTO public.movements VALUES (15, 0, -75, 0, 50, 0, 0, 0, 0, 0, 0, 0, 0, 'Criptografia inquebrável      ', 11, 80);
INSERT INTO public.movements VALUES (20, 0, -27, 0, 0, 0, 0, 0, 0, -18, 0, 0, 0, 'Destruir projeto 2.0          ', 3, 85);
INSERT INTO public.movements VALUES (21, 0, -42, 0, 0, 0, 0, 0, 0, -26, 0, 0, 0, 'Destruir projeto 3.0          ', 7, 85);
INSERT INTO public.movements VALUES (22, 0, -70, 0, 0, 0, 0, 0, 0, -45, 0, 0, 0, 'Destruir projeto 4.0          ', 10, 85);
INSERT INTO public.movements VALUES (14, 0, -57, 0, 37, 0, 0, 0, 0, 0, 0, 0, 0, 'Criptografia avançada         ', 8, 85);
INSERT INTO public.movements VALUES (26, 0, -80, 0, 0, 0, 0, 0, 0, 0, -50, 0, 0, 'Descriptografar 4.0           ', 12, 90);
INSERT INTO public.movements VALUES (24, 0, -37, 0, 0, 0, 0, 0, 0, 0, -22, 0, 0, 'Descriptografar 2.0           ', 4, 85);
INSERT INTO public.movements VALUES (25, 0, -52, 0, 0, 0, 0, 0, 0, 0, -32, 0, 0, 'Descriptografar 3.0           ', 8, 85);
INSERT INTO public.movements VALUES (3, 0, -50, 0, 0, 0, 0, -67, 0, 0, 0, 0, 0, 'Hacking 4.0                   ', 8, 100);
INSERT INTO public.movements VALUES (5, 25, -15, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 'Take a nap                    ', 0, 100);
INSERT INTO public.movements VALUES (29, 0, -90, 0, 0, 0, 0, -115, 0, 0, 0, 0, 0, 'Super hacking                 ', 13, 95);
INSERT INTO public.movements VALUES (4, 0, -70, 0, 0, 0, 0, -85, 0, 0, 0, 0, 0, 'Hacking 5.0                   ', 11, 100);
INSERT INTO public.movements VALUES (30, -90, 65, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 'Super recovery                ', 10, 95);


--
-- Data for Name: players; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.players VALUES (0, 100, 70, 20, 20, 7, 20);


--
-- Data for Name: specialenemies; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.specialenemies VALUES (0, 'Barrera                       ', 100, 80, 16, 16, 10, 25);


--
-- Name: movements_baseaccuracy_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.movements_baseaccuracy_seq', 6, true);


--
-- PostgreSQL database dump complete
--

