insert into quiz (id,name) values (1,"Test Quiz") ;
insert into question (id,type,text,id_quiz) values (1,"multi","Scegliere quali delle seguenti definizioni di Java è corretta.",1) ;
insert into question (id,type,text,id_quiz) values (2,"one","Il Java Runtime Environement (JRE) è indispensabile per l'esecuzione di una applicazione Java in quanto contiene la Virtual Machine. Vero o Falso ?",1) ;
insert into question (id,type,text,id_quiz) values (3,"one","Per sviluppare un'applicazione Java è necessario utilizzare : ",1) ;

insert into answer (text, correct, id_question) values ("Java è un piattaforma di sviluppo specifica per ambiente Windows.",false,1) ;
insert into answer (text, correct, id_question) values ("Java è un linguaggio di programmazione orientato agli oggetti.",true,1) ;
insert into answer (text, correct, id_question) values ("Java ha come scopo principale la scritturare di programma performanti per un determinato ambiente.",false,1) ;
insert into answer (text, correct, id_question) values ("Un'applicazione Java necessita di una Virtual Machine per esere eseguita.",true,1) ;

insert into answer (text, correct, id_question) values ("Vero",true,2) ;
insert into answer (text, correct, id_question) values ("Falso",false,2) ;

insert into answer (text, correct, id_question) values ("Eclipse",false,3) ;
insert into answer (text, correct, id_question) values ("Netbeans",false,3) ;
insert into answer (text, correct, id_question) values ("Notepad",false,3) ;
insert into answer (text, correct, id_question) values ("Un qualunque editor di testi",true,3) ;



insert into quiz (id,name) values (2,"Quiz di valutazione #1") ;
insert into question (id,type,text,id_quiz) values (4,"one","Dummy Question #1",2) ;
insert into question (id,type,text,id_quiz) values (5,"one","Dummy Question #2",2) ;
insert into question (id,type,text,id_quiz) values (6,"one","Dummy Question #3",2) ;
insert into question (id,type,text,id_quiz) values (7,"one","Dummy Question #4",2) ;
insert into question (id,type,text,id_quiz) values (8,"one","Dummy Question #5",2) ;
insert into question (id,type,text,id_quiz) values (9,"one","Dummy Question #6",2) ;
insert into question (id,type,text,id_quiz) values (10,"one","Dummy Question #7",2) ;
insert into question (id,type,text,id_quiz) values (11,"one","Dummy Question #8",2) ;
insert into question (id,type,text,id_quiz) values (12,"one","Dummy Question #9",2) ;
insert into question (id,type,text,id_quiz) values (13,"one","Dummy Question #10",2) ;


insert into answer (text, correct, id_question) values ("Answer #1 to Dummy Question #1",true,4) ;
insert into answer (text, correct, id_question) values ("Answer #2 to Dummy Question #1",false,4) ;
insert into answer (text, correct, id_question) values ("Answer #3 to Dummy Question #1",false,4) ;

insert into answer (text, correct, id_question) values ("Answer #1 to Dummy Question #2",false,5) ;
insert into answer (text, correct, id_question) values ("Answer #2 to Dummy Question #2",true,5) ;
insert into answer (text, correct, id_question) values ("Answer #3 to Dummy Question #2",false,5) ;

insert into answer (text, correct, id_question) values ("Answer #1 to Dummy Question #3",false,6) ;
insert into answer (text, correct, id_question) values ("Answer #2 to Dummy Question #3",false,6) ;
insert into answer (text, correct, id_question) values ("Answer #3 to Dummy Question #3",true,6) ;

insert into answer (text, correct, id_question) values ("Answer #1 to Dummy Question #4",true,7) ;
insert into answer (text, correct, id_question) values ("Answer #2 to Dummy Question #4",false,7) ;
insert into answer (text, correct, id_question) values ("Answer #3 to Dummy Question #4",false,7) ;

insert into answer (text, correct, id_question) values ("Answer #1 to Dummy Question #5",false,8) ;
insert into answer (text, correct, id_question) values ("Answer #2 to Dummy Question #5",true,8) ;
insert into answer (text, correct, id_question) values ("Answer #3 to Dummy Question #5",false,8) ;

insert into answer (text, correct, id_question) values ("Answer #1 to Dummy Question #6",false,9) ;
insert into answer (text, correct, id_question) values ("Answer #2 to Dummy Question #6",false,9) ;
insert into answer (text, correct, id_question) values ("Answer #3 to Dummy Question #6",true,9) ;

insert into answer (text, correct, id_question) values ("Answer #1 to Dummy Question #7",true,10) ;
insert into answer (text, correct, id_question) values ("Answer #2 to Dummy Question #7",false,10) ;
insert into answer (text, correct, id_question) values ("Answer #3 to Dummy Question #7",false,10) ;

insert into answer (text, correct, id_question) values ("Answer #1 to Dummy Question #8",false,11) ;
insert into answer (text, correct, id_question) values ("Answer #2 to Dummy Question #8",true,11) ;
insert into answer (text, correct, id_question) values ("Answer #3 to Dummy Question #8",false,11) ;

insert into answer (text, correct, id_question) values ("Answer #1 to Dummy Question #9",false,12) ;
insert into answer (text, correct, id_question) values ("Answer #2 to Dummy Question #9",false,12) ;
insert into answer (text, correct, id_question) values ("Answer #3 to Dummy Question #9",true,12) ;

insert into answer (text, correct, id_question) values ("Answer #1 to Dummy Question #10",true,13) ;
insert into answer (text, correct, id_question) values ("Answer #2 to Dummy Question #10",false,13) ;
insert into answer (text, correct, id_question) values ("Answer #3 to Dummy Question #10",false,13) ;

