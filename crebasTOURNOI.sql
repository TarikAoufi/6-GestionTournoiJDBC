
create table tournoi (
id_tournoi number,
nom_tournoi varchar (50),
nom_jeu varchar (50),
lieu_tournoi varchar (50),
date_tournoi date,
dateinscrl_tournoi date,
taille_tournoi varchar (1),
fin_tournoi varchar (1)
);

insert into tournoi values (1001, 'le tournoi beau', 'dragon ball Z', 'six-fours-les-plages',to_date('12/12/2013','dd/mm/yyyy'),to_date('10/11/2013','dd/mm/yyyy'),1,1);
insert into tournoi values (1002, 'le tournoi sympa', 'street fighter 12', 'tokyo',to_date('13/12/2013','dd/mm/yyyy'),to_date('06/12/2013','dd/mm/yyyy'),2,1);
insert into tournoi values (1003, 'le tournoi cool', 'diablo 3', 'himeji',to_date('15/12/2013','dd/mm/yyyy'),to_date('05/12/2013','dd/mm/yyyy'),2,1);
insert into tournoi values (1004, 'le tournoi joli', 'starcraft 2', 'kyoto',to_date('20/12/2013','dd/mm/yyyy'),to_date('03/12/2013','dd/mm/yyyy'),1,1);
insert into tournoi values (1005, 'le tournoi de qualité', 'candy crush', 'nara',to_date('22/12/2013','dd/mm/yyyy'),to_date('10/05/2013','dd/mm/yyyy'),2,1);
insert into tournoi values (1006, 'le tournoi super', 'flappy bird', 'kamakura',to_date('17/12/2013','dd/mm/yyyy'),to_date('10/08/2013','dd/mm/yyyy'),2,1);
insert into tournoi values (1007, 'le tournoi à jouer', 'tetris', 'nikko',to_date('16/12/2013','dd/mm/yyyy'),to_date('01/12/2013','dd/mm/yyyy'),1,1);
insert into tournoi values (1008, 'le tournoi pas terrible', 'doom', 'nokogiriyama',to_date('12/05/2013','dd/mm/yyyy'),to_date('10/08/2013','dd/mm/yyyy'),1,1);
insert into tournoi values (1009, 'le tournoi difficile', 'myst', 'hakkone',to_date('12/02/2013','dd/mm/yyyy'),to_date('01/12/2013','dd/mm/yyyy'),2,1);
insert into tournoi values (1010, 'le tournoi scandaleux', 'professeur Layton', 'fuji',to_date('12/08/2013','dd/mm/yyyy'),to_date('08/12/2013','dd/mm/yyyy'),1,1);
insert into tournoi values (1011, 'le tournoi enigmatique', 'justin bieber contre les tortues ninja', 'owakudani',to_date('12/06/2013','dd/mm/yyyy'),to_date('10/01/2016','dd/mm/yyyy'),1,1);


insert into tournoi values (1013,'noel','bagarre','la bastide d anjou', to_date('24/12/2015','dd/mm/yyyy'), to_date('12/12/2014','dd/mm/yyyy'),1, 1);
update tournoi set nom_tournoi = ?, nom_jeu = '?', lieu_Tournoi = '?' , date_tournoi = to_date('12/12/2012','dd/mm/yyyy'), dateinscrl_tournoi = to_date('13/11/2014','dd/mm/yyyy'), taille_tournoi = '?'  where id_tournoi = '1020';

select nom_tournoi from TOURNOI where nom_tournoi = 'e';

select count (*) from TOURNOI;

SELECT MAX(id_tournoi) FROM tournoi;

commit;
select nom_tournoi, nom_jeu, lieu_tournoi, date_tournoi, dateinscrl_tournoi, taille_tournoi from TOURNOI;
