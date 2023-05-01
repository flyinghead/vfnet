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
