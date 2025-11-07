# Algomon

![Logo algomon](Logo.png)

## Descrição do Projeto/Project description
Neste projeto será desenvolvido um jogo eletrônico de batalha em turnos. O projeto faz parte do desenvolvimento do curso MAC0350 - Introdução ao Desenvolvimento de Sistemas de Software, oferecido pelo professor Paulo Roberto Miranda Meirelles no Instituto de Matemática e Estatística da Universidade de São Paulo (IME-USP).

This project will develop a turn-based electronic battle game. The project is part of the development of the MAC0350 course - Introduction to Software Systems Development, offered by Professor Paulo Roberto Miranda Meirelles at the Institute of Mathematics and Statistics of the University of São Paulo (IME-USP).

## História do Jogo/Game story
A história do jogo acontece em um campeonato de programadores que ocorre na cidade na qual a personagem principal (jogador) se residencia. Devido ao sonho de se tornar um programador experiente, mesmo sendo um iniciante, o personagem principal se inscreve e torna-se um competidor do campeonato. O campeonato é dividido em várias fases, na qual cada fase o jogador deve enfrentar os outros competidores batalhando com código. Durante o período da competição o personagem principal vai se aprimorando as suas habilidades de programação, adquirir conhecimentos sobre diferentes algoritmos e teorias, e por fim derrota todos os inimigos e torna o vencedor do campeonato.

The game's story takes place at a programmers' championship held in the city where the main character (player) lives. Driven by the dream of becoming an experienced programmer, despite being a beginner, the main character signs up and becomes a competitor in the championship. The championship is divided into several phases, in each of which the player must face other competitors battling with code. During the competition, the main character improves their programming skills, acquires knowledge of different algorithms and theories, and ultimately defeats all enemies to become the champion.

## Características do Jogo/Game characteristics
A mecânica do jogo será semelhante ao mecâncica do famoso jogo Pokemon, porém os ataques do jogo serão baseados em programas.

Entre o intervalo de cada competição, o jogodor terá um tempo livre que é utilizado para aperfeiçoar as habilidades da personagem principal, tais como aprender os algoritmos e teorias através da internet, passear no ambiente da competição conhecendo os outros competidores, ou treinar suas técnicas próprias de programação.

The game mechanics will be similar to the mechanics of the famous Pokémon game, but the attacks in the game will be program-based.

Between each competition, the player will have free time that is used to improve the main character's skills, such as learning algorithms and theories through the internet, exploring the competition environment and meeting other competitors, or practicing their own programming techniques.

## Linguagens de Programação utilizada/Used programming languages
O projeto foi desenvolvido na linguagem Kotlin e baseado no framework libGDX. Como IDE foi utilizado o Android Studio, que é focado em desenvolvimento para Android e já possui recursos para esse sistema.

The project was developed using the Kotlin programming language and based on the libGDX framework. Android Studio, which is focused on Android development and already has features for that system, was used as the IDE.

## SGBD utilizado/Used DBMS
Nosso projeto se utiliza do sistema gerenciador de banco de dados PostgreSQL para organizar os dados a serem utilizados no programa.

Our project uses the PostgreSQL database management system to organize the data to be used in the program.

## Arquitetura/Architecture
A requisição de dados do banco de dados é feita por meio de uma rede cliente-servidor implementada por meio do framework Ktor. Ela é realizada na porta 8080.

The database data request is made through a client-server network implemented using the Ktor framework. It is performed on port 8080.

## Testes/Tests
Os testes automatizados foram realizados com o framework JUnit, que pode ser utilizado em linguagens compatíveis com a JVM, como é o caso de Kotlin.

Automated tests were performed using the JUnit framework, which can be used in languages ​​compatible with the JVM, such as Kotlin.

## Diagramas/Diagrams

Diagrama UML do projeto/UML diagram of the project

![Diagrama UML do projeto](UML.png)

Diagrama ER do banco de dados/ER diagram of the database

![Diagrama entidade-relacionamento do banco de dados](ER.png)

## Execução/Execution

Execução do jogo (gráfico): 
Execute o main do arquivo Lwjgl3Launcher.kt, localizado no diretório lwgl3.

Execução do jogo (linha de comando):
Execute o main do arquivo game.kt localizado no diretório core.

Execução do servidor:
O servidor é iniciado por meio do main do arquivo server.kt, também esse no diretório core.

Execução dos testes:
Vá aos arquivos de testes e execute a partir das indicações nas linhas de código.

---

Game execution (graphics):
Run the main command from the Lwjgl3Launcher.kt file, located in the lwgl3 directory.

Game execution (command line):
Run the main command from the game.kt file located in the core directory.

Server execution:
The server is started using the main command from the server.kt file, also located in the core directory.

Test execution:
Go to the test files and run them according to the instructions in the code lines.

## Autores e Contribuidores/Authors and contributors
Fernando Yang: Programador e Designer do Projeto/Programmer and designer
Lucas Eiji: Programador e Designer do Projeto/Programmer and designer
Paulo Roberto Miranda Meirelles: Orientador do Projeto/Orientator

## Licença/License
A licença (GNU General Public License v3.0) pode ser verificada no arquivo LICENSE.

The license (GNU General Public License v3.0) can be verified in the LICENSE file.

## Creditos/Credits
- [XtremeFreddy - BGM and SFX](https://pixabay.com/pt/users/xtremefreddy-32332307/)
- [魔王魂 - BGM and SFX](https://maou.audio/category/se/se-sounds/)
- [Modern Interiors - Map Assets](https://limezu.itch.io/moderninteriors)
