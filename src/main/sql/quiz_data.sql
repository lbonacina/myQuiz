insert into question (id,type,text,area,level) values (1,"one","Quale dei processi ITIL si occupa di migliorare continuamente la qualità del servizio?","Management",1) ;
insert into answer (text, correct, id_question) values ("Answer #1",false,1) ;
insert into answer (text, correct, id_question) values ("Answer #2",true,1) ;
insert into answer (text, correct, id_question) values ("Answer #3",false,1) ;
insert into answer (text, correct, id_question) values ("Answer #4",false,1) ;
insert into answer (text, correct, id_question) values ("Answer #5",false,1) ;

insert into question (id,type,text,area,level) values (2,"one","Dummy Question #2","Management",1) ;
insert into answer (text, correct, id_question) values ("Answer #2",false,2) ;
insert into answer (text, correct, id_question) values ("Answer #2",true,2) ;
insert into answer (text, correct, id_question) values ("Answer #3",false,2) ;
insert into answer (text, correct, id_question) values ("Answer #4",false,2) ;
insert into answer (text, correct, id_question) values ("Answer #5",false,2) ;

insert into question (id,type,text,area,level) values (3,"one","Dummy Question #3","Management",1) ;
insert into answer (text, correct, id_question) values ("Answer #3",false,3) ;
insert into answer (text, correct, id_question) values ("Answer #2",true,3) ;
insert into answer (text, correct, id_question) values ("Answer #3",false,3) ;
insert into answer (text, correct, id_question) values ("Answer #4",false,3) ;
insert into answer (text, correct, id_question) values ("Answer #5",false,3) ;


insert into question (id,type,text,area,level) values (4,"one","Sei il Team Leader di un gruppo di supporto. Ti chiamano in quanto, a fronte di un guasto grave, il reperibile non è raggiungibile. In quale document, secondo ITIL,  trovi le istruzioni su come comportarti in questo caso?","Management",2) ;
insert into answer (text, correct, id_question) values ("Security Management Plan",false,4) ;
insert into answer (text, correct, id_question) values ("Incident Management Plan",false,4) ;
insert into answer (text, correct, id_question) values ("Business Continuity Plan",false,4) ;
insert into answer (text, correct, id_question) values ("Problem Management Plan",false,4) ;
insert into answer (text, correct, id_question) values ("Escalation Management",true,4) ;

insert into question (id,type,text,area,level) values (5,"one","Secondo la tua esperienza, e nel rispetto della normativa italiana sul lavoro, quanti FTE sono necessari per formare un turno settimanale di supporto in H24?","Management",2) ;
insert into answer (text, correct, id_question) values ("3",true,5) ;
insert into answer (text, correct, id_question) values ("4",false,5) ;
insert into answer (text, correct, id_question) values ("5",false,5) ;
insert into answer (text, correct, id_question) values ("6",false,5) ;
insert into answer (text, correct, id_question) values ("circa 5.5",false,5) ;

insert into question (id,type,text,area,level) values (6,"one","Un tecnico sta svolgendo un'attività critica che necessita di molta attenzione. Purtroppo è continuamente disturbato dalle chiamate del cliente che è molto ansioso in quanto il servizio sul quale si sta intervenendo è assai critico. Tu sei il manager del tecnico. Che fai.","Management",2) ;
insert into answer (text, correct, id_question) values ("Chiami il cliente e gli comunichi che sarai tu il punto di contatto per le comunicazioni di andamento dell'attività. Poi offline, secondo tempistiche concordate, il tecnico ti comunicherà lo stato di avanzamento.",false,6) ;
insert into answer (text, correct, id_question) values ("Chiami il cliente e gli chiedi di limitare le telofonate, altrimenti mette a rischio l'attività.",false,6) ;
insert into answer (text, correct, id_question) values ("Tra le responsabilità del tecnico c'è anche quella di mantenere la comunicazione verso il cliente. Quindi lo inviti a continuare a svolgere l'attività in questo modo.",false,6) ;
insert into answer (text, correct, id_question) values ("Fai escalation al tuo responsabile mostrandogli il disappunto per il comportamento del cliente.",false,6) ;
insert into answer (text, correct, id_question) values ("Dici al tecnico di portare pazienza. Il cliente, dopo tutto ha sempre ragione...",true,6) ;



insert into question (id,type,text,area,level) values (7,"one","Una risorsa che lavora con te, durante un'attività ha sbagliato un'operazione facendola fallire e causando parecchi problemi al ciente. Come ti comporti nei confronti del cliente?","Management",3) ;
insert into answer (text, correct, id_question) values ("Ti inventi una scusa credibile e la sottoponi al cliente.",false,7) ;
insert into answer (text, correct, id_question) values ("Ammetti l'accaduto con il tuo responsabile e mostri i piani che hai messo in atto affinchè ciò non possa piu' accadere. Insieme decidete quale comunicazione passare al cliente.",true,7) ;
insert into answer (text, correct, id_question) values ("Fai capire al cliente che è accaduta una situazione che era impossibile da prevedere.",false,7) ;
insert into answer (text, correct, id_question) values ("Lasci la comunicazione finale in mano  al tuo responsabile dandogli evidenza che chi ha fatto l'attività non aveva competenze adeguate.",false,7) ;
insert into answer (text, correct, id_question) values ("Fai capire al cliente che sta chiedendo troppe attività rispetto a quelle che è possibile fare per contratto",false,7) ;

insert into question (id,type,text,area,level) values (8,"one","Una risorsa che lavora con te, durante un'attività ha sbagliato un'operazione facendola fallire e causando parecchi problemi al ciente. Come ti comporti nei confronti della risorsa?","Management",3) ;
insert into answer (text, correct, id_question) values ("Una risorsa che lavora con te, durante un'attività ha sbagliato un'operazione facendola fallire e causando parecchi problemi al ciente. Come ti comporti nei confronti della risorsa?",false,8) ;
insert into answer (text, correct, id_question) values ("Lo chiami in un incontro dove gli ribadisci la tua insoddisfazione sul suo operato, minacciandolo di metterlo da parte.",true,8) ;
insert into answer (text, correct, id_question) values ("Durante le prossime attività gli affianchi una persona di tua fiducia affinchè possa imparare come si lavora.",false,8) ;
insert into answer (text, correct, id_question) values ("Ti organizzi affinchè la risorsa possa cambiare gruppo",false,8) ;
insert into answer (text, correct, id_question) values ("Cerchi di capire da lui come sono andate realmente le cose efai mettere in atto tutto quello che è possibile fare affinchè certe situazioni non ripetano nuovamente",false,8) ;

insert into question (id,type,text,area,level) values (9,"one","Il cliente continua a chiedere di eseguire attività che pur essendo all'interno dei limiti previsti dal contratto, fanno aumentare i costi ben al di sopra di quello che è possibile permettersi. Tu...","Management",3) ;
insert into answer (text, correct, id_question) values ("Chi ha fatto il contratto ha sbagliato a fare i conti. La situazione deve essere risolta da lui.",false,9) ;
insert into answer (text, correct, id_question) values ("Fai escalation al contract manager (o al sales manager) affinchè si possa identificare insieme una linea da mantenere con il cliente",true,9) ;
insert into answer (text, correct, id_question) values ("Chiedi alle persone di lavorare di più senza segnare straordinari, facendo capire loro che è un momento particolare",false,9) ;


-- insert into session (id, id_quiz, name, status, start_date, end_date) values (1,2,"Dummy Session",1,'2000-1-01','2099-1-01') ;