insert into teacher(cpf,name,telephone,salary)
values(23322332133, 'Kelly Wiggers', 46999121292, 10.000);

insert into teacher(cpf,name,telephone,salary)
values(23342372933, 'Rubia Eliza Ascari', 46999151782, 10000);

insert into teacher(cpf,name,telephone,salary)
values(93352352932, 'Viviane Dalmolin', 46984171722, 10000);

INSERT INTO subject(name,totalhours,cpfteacher)
VALUES('Programação Orientada a Objetos', 100,23322332133);

INSERT INTO subject(name,totalhours,cpfteacher)
VALUES('Banco de Dados',80,93352352932);

INSERT INTO subject(name,totalhours,cpfteacher)
VALUES('Estrutura de Dados II',100,23342372933);


INSERT INTO student(ra,name,cpf,course)
VALUES(2315954,'Matheus de Souza Guedes',10395655439,'Análise e Desenvolvimento de Sistema');

INSERT INTO student(ra,name,cpf,course)
VALUES(2315234,'Ana Flávia Perin',10285652439,'Análise e Desenvolvimento de Sistema');

INSERT INTO student(ra,name,cpf,course)
VALUES(2211954,'Welinton Kuhnen',10144655436,'Engenharia da Computação');

INSERT INTO enrollment(idclass,ra)
values(4,2315954);

INSERT INTO enrollment(idclass,ra)
values(5,2315234);

INSERT INTO enrollment(idclass,ra)
values(6,2211954)