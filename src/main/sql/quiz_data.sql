
delete from submission;
delete from session;
delete from quiz_question;
delete from quiz;
delete from question;

-- Management Assessed

insert into question (id,type,code,text,area,level) values (1,"one","Mana.001","Quale dei processi ITIL si occupa di migliorare continuamente la qualità del servizio?","Management",0) ;
insert into answer (text, correct, id_question) values ("Mana.001.Answer #1",false,1) ;
insert into answer (text, correct, id_question) values ("Mana.001.Answer #2",true,1) ;
insert into answer (text, correct, id_question) values ("Mana.001.Answer #3",false,1) ;
insert into answer (text, correct, id_question) values ("Mana.001.Answer #4",false,1) ;
insert into answer (text, correct, id_question) values ("Mana.001.Answer #5",false,1) ;

insert into question (id,type,code,text,area,level) values (2,"one","Mana.002","Dummy Question #2","Management",0) ;
insert into answer (text, correct, id_question) values ("Mana.002.Answer #2",false,2) ;
insert into answer (text, correct, id_question) values ("Mana.002.Answer #2",true,2) ;
insert into answer (text, correct, id_question) values ("Mana.002.Answer #3",false,2) ;
insert into answer (text, correct, id_question) values ("Mana.002.Answer #4",false,2) ;
insert into answer (text, correct, id_question) values ("Mana.002.Answer #5",false,2) ;

insert into question (id,type,code,text,area,level) values (3,"one","Mana.003","Dummy Question #3","Management",0) ;
insert into answer (text, correct, id_question) values ("Mana.003.Answer #3",false,3) ;
insert into answer (text, correct, id_question) values ("Mana.003.Answer #2",true,3) ;
insert into answer (text, correct, id_question) values ("Mana.003.Answer #3",false,3) ;
insert into answer (text, correct, id_question) values ("Mana.003.Answer #4",false,3) ;
insert into answer (text, correct, id_question) values ("Mana.003.Answer #5",false,3) ;

insert into question (id,type,code,text,area,level) values (4,"one","Mana.004","Dummy Question #4","Management",0) ;
insert into answer (text, correct, id_question) values ("Mana.004.Answer #4",false,4) ;
insert into answer (text, correct, id_question) values ("Mana.004.Answer #2",true,4) ;
insert into answer (text, correct, id_question) values ("Mana.004.Answer #4",false,4) ;
insert into answer (text, correct, id_question) values ("Mana.004.Answer #4",false,4) ;
insert into answer (text, correct, id_question) values ("Mana.004.Answer #5",false,4) ;

-- Management Experienced

insert into question (id,type,code,text,area,level) values (5,"one","Mana.005","Sei il Team Leader di un gruppo di supporto. Ti chiamano in quanto, a fronte di un guasto grave, il reperibile non è raggiungibile. In quale document, secondo ITIL,  trovi le istruzioni su come comportarti in questo caso?","Management",1) ;
insert into answer (text, correct, id_question) values ("Security Management Plan",false,5) ;
insert into answer (text, correct, id_question) values ("Incident Management Plan",false,5) ;
insert into answer (text, correct, id_question) values ("Business Continuity Plan",false,5) ;
insert into answer (text, correct, id_question) values ("Problem Management Plan",false,5) ;
insert into answer (text, correct, id_question) values ("Escalation Management",true,5) ;

insert into question (id,type,code,text,area,level) values (6,"one","Mana.006","Secondo la tua esperienza, e nel rispetto della normativa italiana sul lavoro, quanti FTE sono necessari per formare un turno settimanale di supporto in H24?","Management",1) ;
insert into answer (text, correct, id_question) values ("3",true,6) ;
insert into answer (text, correct, id_question) values ("4",false,6) ;
insert into answer (text, correct, id_question) values ("5",false,6) ;
insert into answer (text, correct, id_question) values ("6",false,6) ;
insert into answer (text, correct, id_question) values ("circa 5.5",false,6) ;

insert into question (id,type,code,text,area,level) values (7,"one","Mana.007","Un tecnico sta svolgendo un'attività critica che necessita di molta attenzione. Purtroppo è continuamente disturbato dalle chiamate del cliente che è molto ansioso in quanto il servizio sul quale si sta intervenendo è assai critico. Tu sei il manager del tecnico. Che fai.","Management",1) ;
insert into answer (text, correct, id_question) values ("Chiami il cliente e gli comunichi che sarai tu il punto di contatto per le comunicazioni di andamento dell'attività. Poi offline, secondo tempistiche concordate, il tecnico ti comunicherà lo stato di avanzamento.",false,7) ;
insert into answer (text, correct, id_question) values ("Chiami il cliente e gli chiedi di limitare le telofonate, altrimenti mette a rischio l'attività.",false,7) ;
insert into answer (text, correct, id_question) values ("Tra le responsabilità del tecnico c'è anche quella di mantenere la comunicazione verso il cliente. Quindi lo inviti a continuare a svolgere l'attività in questo modo.",false,7) ;
insert into answer (text, correct, id_question) values ("Fai escalation al tuo responsabile mostrandogli il disappunto per il comportamento del cliente.",false,7) ;
insert into answer (text, correct, id_question) values ("Dici al tecnico di portare pazienza. Il cliente, dopo tutto ha sempre ragione...",true,7) ;

insert into question (id,type,code,text,area,level) values (8,"one","Mana.008","Quale strumento è necessario per tenere sottocontrollo il workflow delle richieste in un ambiente O&M?","Management",1) ;
insert into answer (text, correct, id_question) values ("Orchestration",false,8) ;
insert into answer (text, correct, id_question) values ("Monitoring",false,8) ;
insert into answer (text, correct, id_question) values ("TTM",false,8) ;
insert into answer (text, correct, id_question) values ("CMDB",false,8) ;

-- Management Senior

insert into question (id,type,code,text,area,level) values (9,"one","Mana.009","Una risorsa che lavora con te, durante un'attività ha sbagliato un'operazione facendola fallire e causando parecchi problemi al ciente. Come ti comporti nei confronti del cliente?","Management",2) ;
insert into answer (text, correct, id_question) values ("Ti inventi una scusa credibile e la sottoponi al cliente.",false,9) ;
insert into answer (text, correct, id_question) values ("Ammetti l'accaduto con il tuo responsabile e mostri i piani che hai messo in atto affinchè ciò non possa piu' accadere. Insieme decidete quale comunicazione passare al cliente.",true,9) ;
insert into answer (text, correct, id_question) values ("Fai capire al cliente che è accaduta una situazione che era impossibile da prevedere.",false,9) ;
insert into answer (text, correct, id_question) values ("Lasci la comunicazione finale in mano  al tuo responsabile dandogli evidenza che chi ha fatto l'attività non aveva competenze adeguate.",false,9) ;
insert into answer (text, correct, id_question) values ("Fai capire al cliente che sta chiedendo troppe attività rispetto a quelle che è possibile fare per contratto",false,9) ;

insert into question (id,type,code,text,area,level) values (10,"one","Mana.010","Una risorsa che lavora con te, durante un'attività ha sbagliato un'operazione facendola fallire e causando parecchi problemi al ciente. Come ti comporti nei confronti della risorsa?","Management",2) ;
insert into answer (text, correct, id_question) values ("Una risorsa che lavora con te, durante un'attività ha sbagliato un'operazione facendola fallire e causando parecchi problemi al ciente. Come ti comporti nei confronti della risorsa?",false,10) ;
insert into answer (text, correct, id_question) values ("Lo chiami in un incontro dove gli ribadisci la tua insoddisfazione sul suo operato, minacciandolo di metterlo da parte.",true,10) ;
insert into answer (text, correct, id_question) values ("Durante le prossime attività gli affianchi una persona di tua fiducia affinchè possa imparare come si lavora.",false,10) ;
insert into answer (text, correct, id_question) values ("Ti organizzi affinchè la risorsa possa cambiare gruppo",false,10) ;
insert into answer (text, correct, id_question) values ("Cerchi di capire da lui come sono andate realmente le cose efai mettere in atto tutto quello che è possibile fare affinchè certe situazioni non ripetano nuovamente",false,10) ;

insert into question (id,type,code,text,area,level) values (11,"one","Mana.011","Il cliente continua a chiedere di eseguire attività che pur essendo all'interno dei limiti previsti dal contratto, fanno aumentare i costi ben al di sopra di quello che è possibile permettersi. Tu...","Management",2) ;
insert into answer (text, correct, id_question) values ("Chi ha fatto il contratto ha sbagliato a fare i conti. La situazione deve essere risolta da lui.",false,11) ;
insert into answer (text, correct, id_question) values ("Fai escalation al contract manager (o al sales manager) affinchè si possa identificare insieme una linea da mantenere con il cliente",true,11) ;
insert into answer (text, correct, id_question) values ("Chiedi alle persone di lavorare di più senza segnare straordinari, facendo capire loro che è un momento particolare",false,11) ;

insert into question (id,type,code,text,area,level) values (12,"multi","Mana.012","Quali step ritieni obbligatori per la stesura del piano operativo di un'attività critica?","Management",2) ;
insert into answer (text, correct, id_question) values ("Mana.012.Answer #1",false,12) ;
insert into answer (text, correct, id_question) values ("Mana.012.Answer #2",true,12) ;
insert into answer (text, correct, id_question) values ("Mana.012.Answer #3",false,12) ;
insert into answer (text, correct, id_question) values ("Mana.012.Answer #4",true,12) ;

insert into question (id,type,code,text,area,level) values (13,"one","Db.001","Dummy Database Question #","Database",1) ;
insert into answer (text, correct, id_question) values ("Db.001.Answer #1",false,13) ;
insert into answer (text, correct, id_question) values ("Db.001.Answer #2",true,13) ;
insert into answer (text, correct, id_question) values ("Db.001.Answer #3",false,13) ;
insert into answer (text, correct, id_question) values ("Db.001.Answer #4",false,13) ;
insert into answer (text, correct, id_question) values ("Db.001.Answer #5",false,13) ;

insert into question (id,type,code,text,area,level) values (14,"one","Db.002","Dummy Database Question #","Database",1) ;
insert into answer (text, correct, id_question) values ("Db.002.Answer #1",false,14) ;
insert into answer (text, correct, id_question) values ("Db.002.Answer #2",true,14) ;
insert into answer (text, correct, id_question) values ("Db.002.Answer #3",false,14) ;
insert into answer (text, correct, id_question) values ("Db.002.Answer #4",false,14) ;
insert into answer (text, correct, id_question) values ("Db.002.Answer #5",false,14) ;

insert into question (id,type,code,text,area,level) values (15,"one","Db.003","Dummy Database Question #","Database",1) ;
insert into answer (text, correct, id_question) values ("Db.003.Answer #1",false,15) ;
insert into answer (text, correct, id_question) values ("Db.003.Answer #2",true,15) ;
insert into answer (text, correct, id_question) values ("Db.003.Answer #3",false,15) ;
insert into answer (text, correct, id_question) values ("Db.003.Answer #4",false,15) ;
insert into answer (text, correct, id_question) values ("Db.003.Answer #5",false,15) ;


insert into question (id,type,code,text,area,level) values (16,"one","Db.004","Dummy Database Question #","Database",1) ;
insert into answer (text, correct, id_question) values ("Db.004.Answer #1",false,16) ;
insert into answer (text, correct, id_question) values ("Db.004.Answer #2",true,16) ;
insert into answer (text, correct, id_question) values ("Db.004.Answer #3",false,16) ;
insert into answer (text, correct, id_question) values ("Db.004.Answer #4",false,16) ;
insert into answer (text, correct, id_question) values ("Db.004.Answer #5",false,16) ;

insert into question (id,type,code,text,area,html_formatted,level) values (17,"one","Jv.001","<html><p>Dato il seguente codice:</p><div class="code">public interface Drivable {...}public class Tractor implements Drivable {...}public Class Veichle {...}public class Car extends Veichle implements Drivable {...}public class Truck extends Veichle implements  Drivable {...}<div><p>la sequenza di istruzioni sottostante genererà errori di compilazione ?</p><div class="code">Car car  = new Car() ;Vehicle vehicle = car ;<div></html>","Java",1,1) ;
insert into answer (text, correct, id_question) values ("Nessun Errore",true,17) ;
insert into answer (text, correct, id_question) values ("Verrà sollevato un errore di compilazione.",false,17) ;




-- insert into session (id, id_quiz, name, status, start_date, end_date) values (1,2,"Dummy Session",1,'2000-1-01','2099-1-01') ;