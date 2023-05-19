/*
	vf.net web server revival
	Copyright (C) 2023 flyinghead

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.flyinghead.vf4.db;

import java.util.List;

public interface IDbService {
	Player getPlayer(int cardId);
	void savePlayer(Player player);
	List<Player> listPlayers();
	void saveMatch(Match match);
	List<Match> listMatches();
	List<Match> listPlayerMatches(int cardId);
	void updateCardId(int oldId, int newId);
}
