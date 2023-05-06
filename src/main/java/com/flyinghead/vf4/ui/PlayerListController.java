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
package com.flyinghead.vf4.ui;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.flyinghead.vf4.db.IDbService;
import com.flyinghead.vf4.db.Player;

//FIXME need to secure the app first @Controller
public class PlayerListController {
	@Autowired
    private IDbService dbService;

	@ModelAttribute("playerList")
	public List<Player> getAllPlayers() {
	    return dbService.listPlayers();
	}
	
	@RequestMapping("/players")
	public String listPlayers() {
	    return "player-list";
	}
}
