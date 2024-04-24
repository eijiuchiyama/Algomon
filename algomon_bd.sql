--
-- PostgreSQL database cluster dump
--

SET default_transaction_read_only = off;

SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;

--
-- Databases
--

--
-- Database "template1" dump
--

\connect template1

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
-- PostgreSQL database dump complete
--

--
-- Database "postgres" dump
--

\connect postgres

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
-- Data for Name: commonenemies; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.commonenemies (id, name, basehp, basestamina, baseatk, basedef, basedodge, basespeed, level) FROM stdin;
4	Inimigo fraco e rápido        	80	57	22	15	5	50	0
5	Inimigo estranho              	140	120	8	7	0	17	0
0	Inimigo fraco                 	80	65	25	15	5	30	0
1	Inimigo fraco                 	70	75	17	22	10	15	0
2	Inimigo muito fraco           	40	30	15	18	0	20	0
3	Inimigo muito fraco           	50	45	10	25	0	10	0
7	Inimigo meio fraco            	120	80	26	28	10	25	1
8	Inimigo meio fraco            	110	100	28	30	10	20	1
6	Inimigo paia                  	20	25	0	25	15	15	0
9	Inimigo maromba               	110	90	35	15	10	5	1
10	Inimigo blindado              	125	100	15	32	7	20	1
11	Inimigo iniciante             	120	24	25	25	15	20	1
12	Inimigo desajeitado           	140	70	20	20	0	40	1
\.


--
-- Data for Name: movements; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.movements (id, hpown, staminaown, atkown, defown, dodgeown, speedown, hpenemy, staminaenemy, atkenemy, defenemy, dodgeenemy, speedenemy, name, minlevel, baseaccuracy, preco) FROM stdin;
1	0	-20	0	0	0	0	-35	0	0	0	0	0	Hacking 2.0                   	2	100	25
16	0	-10	0	0	0	10	0	0	0	0	0	0	Compactação simples           	2	90	25
13	0	-32	0	22	0	0	0	0	0	0	0	0	Criptografia segura           	3	85	30
20	0	-27	0	0	0	0	0	0	-18	0	0	0	Destruir projeto 2.0          	3	85	30
27	0	-25	0	0	0	0	0	0	0	0	15	0	Path-finding simples          	4	90	35
9	0	-30	18	0	0	0	0	0	0	0	0	0	Correção eficiente de bugs    	4	90	35
24	0	-37	0	0	0	0	0	0	0	-22	0	0	Descriptografar 2.0           	4	85	35
2	0	-35	0	0	0	0	-55	0	0	0	0	0	Hacking 3.0                   	5	100	40
6	65	-50	0	0	0	0	0	0	0	0	0	0	Take a long nap               	7	100	55
7	-60	40	0	0	0	0	0	0	0	0	0	0	Recovery                      	7	100	55
21	0	-42	0	0	0	0	0	0	-26	0	0	0	Destruir projeto 3.0          	7	85	55
17	0	-30	0	0	0	30	0	0	0	0	0	0	Compactação média             	8	80	62
14	0	-57	0	37	0	0	0	0	0	0	0	0	Criptografia avançada         	8	85	62
25	0	-52	0	0	0	0	0	0	0	-32	0	0	Descriptografar 3.0           	8	85	62
3	0	-50	0	0	0	0	-67	0	0	0	0	0	Hacking 4.0                   	8	100	62
0	0	-10	0	0	0	0	-20	0	0	0	0	0	Hacking 1.0                   	0	100	0
8	0	-10	5	0	0	0	0	0	0	0	0	0	Correção simples de bug       	0	90	0
12	0	-15	0	10	0	0	0	0	0	0	0	0	Criptografia básica           	0	80	0
23	0	-18	0	0	0	0	0	0	0	-12	0	0	Descriptografar 1.0           	0	80	0
19	0	-12	0	0	0	0	0	0	-7	0	0	0	Destruir projeto 1.0          	0	90	0
5	25	-15	0	0	0	0	0	0	0	0	0	0	Take a nap                    	0	100	0
28	0	-50	0	0	0	0	0	0	0	0	-30	0	Path-finding avançado         	9	80	70
10	0	-50	35	0	0	0	0	0	0	0	0	0	Correção extensa de bugs      	9	85	70
22	0	-70	0	0	0	0	0	0	-45	0	0	0	Destruir projeto 4.0          	10	85	75
30	-90	65	0	0	0	0	0	0	0	0	0	0	Super recovery                	10	95	75
11	0	-75	55	0	0	0	0	0	0	0	0	0	Correção completa de bugs     	11	80	80
15	0	-75	0	50	0	0	0	0	0	0	0	0	Criptografia inquebrável      	11	80	80
4	0	-70	0	0	0	0	-85	0	0	0	0	0	Hacking 5.0                   	11	100	80
18	0	-50	0	0	0	50	0	0	0	0	0	0	Compactação avançada          	12	70	87
26	0	-80	0	0	0	0	0	0	0	-50	0	0	Descriptografar 4.0           	12	90	87
29	0	-90	0	0	0	0	-115	0	0	0	0	0	Super hacking                 	13	95	95
\.


--
-- Data for Name: players; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.players (id, basehp, basestamina, baseatk, basedef, basedodge, basespeed) FROM stdin;
0	100	70	20	20	7	20
\.


--
-- Data for Name: specialenemies; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.specialenemies (id, name, basehp, basestamina, baseatk, basedef, basedodge, basespeed, level) FROM stdin;
0	Barrera                       	100	80	16	16	10	25	0
\.


--
-- Name: movements_baseaccuracy_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.movements_baseaccuracy_seq', 6, true);


--
-- PostgreSQL database dump complete
--

--
-- PostgreSQL database cluster dump complete
--

