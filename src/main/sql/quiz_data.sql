insert into quiz (id,name) values (1,"Test Quiz") ;
insert into question (id,type,text,id_quiz) values (1,"multi","Scegliere quali delle seguenti definizioni di Java è corretta.",1) ;
insert into question (id,type,text,id_quiz) values (2,"one","Il Java Runtime Environement (JRE) è indispensabile per l'esecuzione di una applicazione Java in quanto contiene la Virtual Machine. Vero o Falso ?",1) ;
insert into question (id,type,text,id_quiz) values (3,"one","Per sviluppare un'applicazione Java è necessario utilizzare : ",1) ;

insert into possible_answer (text, correct, id_question) values ("Java è un piattaforma di sviluppo specifica per ambiente Windows.",false,1) ;
insert into possible_answer (text, correct, id_question) values ("Java è un linguaggio di programmazione orientato agli oggetti.",true,1) ;
insert into possible_answer (text, correct, id_question) values ("Java ha come scopo principale la scritturare di programma performanti per un determinato ambiente.",false,1) ;
insert into possible_answer (text, correct, id_question) values ("Un'applicazione Java necessita di una Virtual Machine per esere eseguita.",true,1) ;

insert into possible_answer (text, correct, id_question) values ("Vero",true,2) ;
insert into possible_answer (text, correct, id_question) values ("Falso",false,2) ;

insert into possible_answer (text, correct, id_question) values ("Eclipse",false,3) ;
insert into possible_answer (text, correct, id_question) values ("Netbeans",false,3) ;
insert into possible_answer (text, correct, id_question) values ("Notepad",false,3) ;
insert into possible_answer (text, correct, id_question) values ("Un qualunque editor di testi",true,3) ;

