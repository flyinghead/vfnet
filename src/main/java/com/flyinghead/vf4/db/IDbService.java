package com.flyinghead.vf4.db;

import java.util.List;

public interface IDbService {
	Player getPlayer(int cardId);
	void savePlayer(Player player);
	List<Player> listPlayers();
	void saveMatch(Match match);
	List<Match> listMatches();
}
