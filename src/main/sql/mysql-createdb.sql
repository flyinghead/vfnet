create database vf4;
use vf4;
create table `match` (id integer not null auto_increment, chain_of_win integer, end_time datetime(6), loose_character integer, win_character integer,
	looser_id integer, winner_id integer, primary key (id));
create table player (id integer not null auto_increment, card_id integer UNIQUE KEY, `character` integer, clan_name varchar(20), color varchar(255), 
	created datetime(6), created_ip varchar(255), emblem1 integer, emblem2 integer, exp_points integer, game_id integer, last_seen datetime(6), 
	last_seen_ip varchar(255), level integer, losses integer, my_tenpo_points integer, quest varchar(255), ranking_points integer, 
	ring_name varchar(20), stage_progress integer, wins integer, equip varchar(16),
	alt_move_1 boolean, alt_move_2 boolean, presentation varchar(255), primary key (id));
alter table `match` add constraint match_looser_constraint foreign key (looser_id) references player (id);
alter table `match` add constraint match_winner_constraint foreign key (winner_id) references player (id);
create index name_index on player (ring_name);

GRANT INSERT, UPDATE, DELETE, SELECT on vf4.* TO 'vf4'@'localhost' WITH GRANT OPTION;
