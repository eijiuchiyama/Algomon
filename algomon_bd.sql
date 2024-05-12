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
    basespeed integer,
    level integer
);


ALTER TABLE public.commonenemies OWNER TO postgres;

--
-- Name: TABLE commonenemies; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.commonenemies IS 'Inimigos comuns';


--
-- Name: COLUMN commonenemies.level; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.commonenemies.level IS 'Indica o level que o jogador precisa estar para batalhar contra ele';


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
    baseaccuracy integer,
    price integer
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
-- Name: COLUMN movements.price; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.movements.price IS 'Preço para adquirir o movimento';


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
    basespeed integer,
    level integer
);


ALTER TABLE public.specialenemies OWNER TO postgres;

--
-- Name: TABLE specialenemies; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.specialenemies IS 'Inimigos especiais';


--
-- Data for Name: commonenemies; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.commonenemies VALUES (60, 'Pós graduado do IME           ', 363, 410, 128, 118, 0, 135, 10);
INSERT INTO public.commonenemies VALUES (21, 'Competidor fracassado         ', 165, 220, 40, 42, 8, 52, 3);
INSERT INTO public.commonenemies VALUES (61, 'Hacker internacional          ', 393, 470, 140, 140, 0, 140, 11);
INSERT INTO public.commonenemies VALUES (39, 'Professor de segurança        ', 235, 260, 73, 75, 7, 85, 6);
INSERT INTO public.commonenemies VALUES (62, 'Hacker internacional          ', 400, 475, 133, 138, 3, 150, 11);
INSERT INTO public.commonenemies VALUES (7, 'Inimigo meio fraco            ', 120, 110, 27, 30, 10, 27, 1);
INSERT INTO public.commonenemies VALUES (8, 'Inimigo meio fraco            ', 110, 120, 30, 32, 10, 22, 1);
INSERT INTO public.commonenemies VALUES (0, 'Inimigo fraco                 ', 80, 100, 25, 15, 5, 30, 0);
INSERT INTO public.commonenemies VALUES (1, 'Inimigo fraco                 ', 70, 105, 17, 22, 10, 15, 0);
INSERT INTO public.commonenemies VALUES (2, 'Inimigo muito fraco           ', 40, 70, 15, 18, 0, 20, 0);
INSERT INTO public.commonenemies VALUES (3, 'Inimigo muito fraco           ', 50, 60, 10, 25, 0, 10, 0);
INSERT INTO public.commonenemies VALUES (4, 'Inimigo fraco e rápido        ', 80, 100, 22, 15, 5, 50, 0);
INSERT INTO public.commonenemies VALUES (9, 'Inimigo maromba               ', 110, 120, 37, 17, 10, 25, 1);
INSERT INTO public.commonenemies VALUES (6, 'Inimigo paia                  ', 20, 70, 0, 25, 15, 15, 0);
INSERT INTO public.commonenemies VALUES (5, 'Inimigo estranho              ', 140, 150, 8, 7, 0, 17, 0);
INSERT INTO public.commonenemies VALUES (22, 'Competidor fracassado         ', 170, 225, 45, 43, 0, 40, 3);
INSERT INTO public.commonenemies VALUES (10, 'Inimigo blindado              ', 125, 115, 20, 35, 7, 25, 1);
INSERT INTO public.commonenemies VALUES (23, 'Hacker profissional           ', 165, 220, 40, 52, 5, 50, 3);
INSERT INTO public.commonenemies VALUES (24, 'Inimigo ok                    ', 160, 220, 48, 48, 0, 40, 3);
INSERT INTO public.commonenemies VALUES (25, 'Inimigo arrogante             ', 175, 200, 48, 47, 3, 55, 4);
INSERT INTO public.commonenemies VALUES (26, 'Inimigo desequilibrado        ', 230, 210, 35, 50, 5, 60, 4);
INSERT INTO public.commonenemies VALUES (27, 'Hacker iniciante              ', 180, 220, 52, 52, 0, 50, 4);
INSERT INTO public.commonenemies VALUES (11, 'Inimigo iniciante             ', 120, 120, 28, 28, 15, 25, 1);
INSERT INTO public.commonenemies VALUES (40, 'Hacker mediano                ', 235, 290, 75, 65, 7, 75, 6);
INSERT INTO public.commonenemies VALUES (41, 'Professor de desenvolvimento  ', 255, 320, 85, 80, 8, 80, 7);
INSERT INTO public.commonenemies VALUES (12, 'Inimigo desajeitado           ', 140, 150, 25, 25, 0, 45, 1);
INSERT INTO public.commonenemies VALUES (13, 'Inimigo desprotegido          ', 130, 210, 40, 23, 10, 30, 2);
INSERT INTO public.commonenemies VALUES (14, 'Inimigo desprotegido          ', 140, 200, 43, 20, 0, 30, 2);
INSERT INTO public.commonenemies VALUES (28, 'Hacker iniciante              ', 175, 225, 50, 50, 5, 60, 4);
INSERT INTO public.commonenemies VALUES (15, 'Inimigo superprotegido        ', 150, 250, 30, 45, 5, 35, 2);
INSERT INTO public.commonenemies VALUES (16, 'Inimigo superprotegido        ', 145, 200, 30, 38, 0, 40, 2);
INSERT INTO public.commonenemies VALUES (17, 'Inimigo rápido                ', 140, 200, 35, 33, 7, 55, 2);
INSERT INTO public.commonenemies VALUES (18, 'Inimigo equilibrado           ', 150, 200, 35, 35, 5, 35, 2);
INSERT INTO public.commonenemies VALUES (42, 'Hacker promovido              ', 253, 280, 85, 87, 8, 75, 7);
INSERT INTO public.commonenemies VALUES (43, 'Ingressante no IME            ', 245, 288, 90, 83, 0, 75, 7);
INSERT INTO public.commonenemies VALUES (19, 'Aspirante a chefão            ', 160, 160, 35, 30, 0, 40, 2);
INSERT INTO public.commonenemies VALUES (20, 'Inimigo puto                  ', 150, 210, 47, 47, 5, 42, 3);
INSERT INTO public.commonenemies VALUES (29, 'Técnico de segurança iniciante', 170, 230, 53, 48, 0, 40, 4);
INSERT INTO public.commonenemies VALUES (30, 'Inimigo impenetrável          ', 200, 240, 40, 62, 2, 40, 4);
INSERT INTO public.commonenemies VALUES (31, 'Aspirante a chefão            ', 200, 250, 60, 58, 15, 65, 5);
INSERT INTO public.commonenemies VALUES (32, 'Hacker mediano                ', 190, 270, 60, 60, 0, 53, 5);
INSERT INTO public.commonenemies VALUES (44, 'Hacker promovido              ', 255, 300, 83, 80, 5, 90, 7);
INSERT INTO public.commonenemies VALUES (45, 'Inimigo overpowered           ', 300, 295, 80, 87, 0, 87, 7);
INSERT INTO public.commonenemies VALUES (33, 'Inimigo assustador            ', 250, 300, 57, 57, 3, 60, 5);
INSERT INTO public.commonenemies VALUES (34, 'Inimigo invulnerável          ', 150, 225, 38, 43, 50, 35, 5);
INSERT INTO public.commonenemies VALUES (35, 'Hacker mediano                ', 185, 255, 62, 60, 5, 62, 5);
INSERT INTO public.commonenemies VALUES (36, 'Inimigo super forte           ', 240, 270, 75, 73, 10, 70, 6);
INSERT INTO public.commonenemies VALUES (37, 'Inimigo desproporcional       ', 230, 260, 62, 80, 3, 70, 6);
INSERT INTO public.commonenemies VALUES (38, 'Chefão rebaixado              ', 220, 250, 75, 75, 0, 65, 6);
INSERT INTO public.commonenemies VALUES (46, 'Hacker profissional           ', 280, 315, 100, 95, 10, 90, 8);
INSERT INTO public.commonenemies VALUES (47, 'Hacker profissional           ', 290, 320, 103, 103, 0, 100, 8);
INSERT INTO public.commonenemies VALUES (48, 'IMEano médio                  ', 287, 355, 103, 98, 5, 105, 8);
INSERT INTO public.commonenemies VALUES (49, 'IMEano médio                  ', 292, 375, 103, 107, 0, 90, 8);
INSERT INTO public.commonenemies VALUES (50, 'Professor de segurança        ', 287, 345, 90, 103, 3, 98, 8);
INSERT INTO public.commonenemies VALUES (51, 'IMEano último ano             ', 310, 370, 113, 110, 5, 107, 9);
INSERT INTO public.commonenemies VALUES (52, 'IMEano último ano             ', 320, 385, 117, 108, 10, 102, 9);
INSERT INTO public.commonenemies VALUES (53, 'Hacker russo                  ', 325, 400, 110, 110, 8, 115, 9);
INSERT INTO public.commonenemies VALUES (54, 'Hacker russo                  ', 350, 415, 102, 100, 12, 105, 9);
INSERT INTO public.commonenemies VALUES (55, 'Sub-chefe                     ', 340, 375, 108, 115, 0, 123, 9);
INSERT INTO public.commonenemies VALUES (56, 'Professor do IME              ', 357, 415, 122, 122, 9, 115, 10);
INSERT INTO public.commonenemies VALUES (57, 'Professor do IME              ', 360, 440, 125, 120, 7, 115, 10);
INSERT INTO public.commonenemies VALUES (58, 'Hacker internacional          ', 365, 400, 115, 125, 0, 125, 10);
INSERT INTO public.commonenemies VALUES (59, 'Diretor do IMESec             ', 400, 400, 122, 122, 2, 125, 10);
INSERT INTO public.commonenemies VALUES (63, 'Líder dos hackers             ', 430, 470, 128, 133, 8, 130, 11);


--
-- Data for Name: movements; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.movements VALUES (1, 0, -20, 0, 0, 0, 0, -35, 0, 0, 0, 0, 0, 'Hacking 2.0                   ', 2, 100, 25);
INSERT INTO public.movements VALUES (16, 0, -10, 0, 0, 0, 10, 0, 0, 0, 0, 0, 0, 'Compactação simples           ', 2, 90, 25);
INSERT INTO public.movements VALUES (13, 0, -32, 0, 22, 0, 0, 0, 0, 0, 0, 0, 0, 'Criptografia segura           ', 3, 85, 30);
INSERT INTO public.movements VALUES (20, 0, -27, 0, 0, 0, 0, 0, 0, -18, 0, 0, 0, 'Destruir projeto 2.0          ', 3, 85, 30);
INSERT INTO public.movements VALUES (27, 0, -25, 0, 0, 0, 0, 0, 0, 0, 0, 15, 0, 'Path-finding simples          ', 4, 90, 35);
INSERT INTO public.movements VALUES (7, 90, -70, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 'Deep sleep                    ', 10, 100, 75);
INSERT INTO public.movements VALUES (9, 0, -30, 18, 0, 0, 0, 0, 0, 0, 0, 0, 0, 'Correção eficiente de bugs    ', 4, 90, 35);
INSERT INTO public.movements VALUES (6, 65, -50, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 'Take a long nap               ', 6, 100, 47);
INSERT INTO public.movements VALUES (24, 0, -37, 0, 0, 0, 0, 0, 0, 0, -22, 0, 0, 'Descriptografar 2.0           ', 4, 85, 35);
INSERT INTO public.movements VALUES (30, -20, 15, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 'Small recovery                ', 0, 100, 0);
INSERT INTO public.movements VALUES (31, -35, 25, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 'Recovery                      ', 3, 100, 30);
INSERT INTO public.movements VALUES (2, 0, -35, 0, 0, 0, 0, -55, 0, 0, 0, 0, 0, 'Hacking 3.0                   ', 5, 100, 40);
INSERT INTO public.movements VALUES (21, 0, -42, 0, 0, 0, 0, 0, 0, -26, 0, 0, 0, 'Destruir projeto 3.0          ', 7, 85, 55);
INSERT INTO public.movements VALUES (17, 0, -30, 0, 0, 0, 30, 0, 0, 0, 0, 0, 0, 'Compactação média             ', 8, 80, 62);
INSERT INTO public.movements VALUES (14, 0, -57, 0, 37, 0, 0, 0, 0, 0, 0, 0, 0, 'Criptografia avançada         ', 8, 85, 62);
INSERT INTO public.movements VALUES (25, 0, -52, 0, 0, 0, 0, 0, 0, 0, -32, 0, 0, 'Descriptografar 3.0           ', 8, 85, 62);
INSERT INTO public.movements VALUES (3, 0, -50, 0, 0, 0, 0, -67, 0, 0, 0, 0, 0, 'Hacking 4.0                   ', 8, 100, 62);
INSERT INTO public.movements VALUES (32, -65, 45, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 'Super recovery                ', 6, 100, 47);
INSERT INTO public.movements VALUES (0, 0, -10, 0, 0, 0, 0, -20, 0, 0, 0, 0, 0, 'Hacking 1.0                   ', 0, 100, 0);
INSERT INTO public.movements VALUES (8, 0, -10, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 'Correção simples de bug       ', 0, 90, 0);
INSERT INTO public.movements VALUES (12, 0, -15, 0, 10, 0, 0, 0, 0, 0, 0, 0, 0, 'Criptografia básica           ', 0, 80, 0);
INSERT INTO public.movements VALUES (23, 0, -18, 0, 0, 0, 0, 0, 0, 0, -12, 0, 0, 'Descriptografar 1.0           ', 0, 80, 0);
INSERT INTO public.movements VALUES (19, 0, -12, 0, 0, 0, 0, 0, 0, -7, 0, 0, 0, 'Destruir projeto 1.0          ', 0, 90, 0);
INSERT INTO public.movements VALUES (5, 25, -15, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 'Take a nap                    ', 0, 100, 0);
INSERT INTO public.movements VALUES (28, 0, -50, 0, 0, 0, 0, 0, 0, 0, 0, -30, 0, 'Path-finding avançado         ', 9, 80, 70);
INSERT INTO public.movements VALUES (10, 0, -50, 35, 0, 0, 0, 0, 0, 0, 0, 0, 0, 'Correção extensa de bugs      ', 9, 85, 70);
INSERT INTO public.movements VALUES (22, 0, -70, 0, 0, 0, 0, 0, 0, -45, 0, 0, 0, 'Destruir projeto 4.0          ', 10, 85, 75);
INSERT INTO public.movements VALUES (11, 0, -75, 55, 0, 0, 0, 0, 0, 0, 0, 0, 0, 'Correção completa de bugs     ', 11, 80, 80);
INSERT INTO public.movements VALUES (15, 0, -75, 0, 50, 0, 0, 0, 0, 0, 0, 0, 0, 'Criptografia inquebrável      ', 11, 80, 80);
INSERT INTO public.movements VALUES (18, 0, -50, 0, 0, 0, 50, 0, 0, 0, 0, 0, 0, 'Compactação avançada          ', 12, 70, 87);
INSERT INTO public.movements VALUES (26, 0, -80, 0, 0, 0, 0, 0, 0, 0, -50, 0, 0, 'Descriptografar 4.0           ', 12, 90, 87);
INSERT INTO public.movements VALUES (33, -85, 55, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 'Hyper recovery                ', 9, 100, 70);
INSERT INTO public.movements VALUES (4, 0, -70, 0, 0, 0, 0, -85, 0, 0, 0, 0, 0, 'Hacking 5.0                   ', 10, 100, 75);
INSERT INTO public.movements VALUES (29, 0, -90, 0, 0, 0, 0, -115, 0, 0, 0, 0, 0, 'Super hacking                 ', 12, 95, 87);


--
-- Data for Name: players; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.players VALUES (0, 100, 120, 20, 20, 7, 20);


--
-- Data for Name: specialenemies; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.specialenemies VALUES (0, 'Barrera                       ', 100, 120, 16, 16, 10, 25, 0);
INSERT INTO public.specialenemies VALUES (1, 'A                             ', 125, 155, 27, 25, 5, 25, 1);
INSERT INTO public.specialenemies VALUES (2, 'B                             ', 145, 200, 35, 37, 0, 32, 2);
INSERT INTO public.specialenemies VALUES (3, 'C                             ', 170, 255, 47, 42, 8, 42, 3);
INSERT INTO public.specialenemies VALUES (4, 'D                             ', 187, 250, 50, 50, 5, 50, 4);
INSERT INTO public.specialenemies VALUES (5, 'E                             ', 215, 270, 57, 55, 10, 55, 5);
INSERT INTO public.specialenemies VALUES (6, 'F                             ', 235, 310, 75, 70, 10, 77, 6);
INSERT INTO public.specialenemies VALUES (7, 'G                             ', 265, 350, 85, 85, 7, 92, 7);
INSERT INTO public.specialenemies VALUES (8, 'H                             ', 292, 360, 100, 100, 7, 95, 8);
INSERT INTO public.specialenemies VALUES (9, 'I                             ', 335, 415, 113, 112, 10, 107, 9);
INSERT INTO public.specialenemies VALUES (10, 'J                             ', 360, 440, 127, 125, 12, 110, 10);
INSERT INTO public.specialenemies VALUES (11, 'K                             ', 390, 505, 142, 137, 7, 142, 11);
INSERT INTO public.specialenemies VALUES (12, 'Daniel                        ', 400, 800, 150, 160, 15, 160, 12);


--
-- Name: movements_baseaccuracy_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.movements_baseaccuracy_seq', 6, true);


--
-- PostgreSQL database dump complete
--

