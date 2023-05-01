package com.flyinghead.vf4.ui;

import java.sql.Timestamp;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.flyinghead.vf4.db.IDbService;
import com.flyinghead.vf4.db.Player;

@Controller
public class PlayerController {
	@Autowired
    private IDbService dbService;

	@RequestMapping("/player")
	public String editPlayer(@RequestParam("id") int playerId, final ModelMap model) {
		if (playerId == 0)
			throw new IllegalStateException("Null player id");
		Player player = dbService.getPlayer(playerId);
		if (player == null)
			throw new RuntimeException("Player not found");
		model.addAttribute("player", player);
		
	    return "player";
	}
	
	@RequestMapping(value="/player", params={"save"})
	public String savePlayer(final Player player, final BindingResult bindingResult, final ModelMap model, HttpServletRequest req) {
		if (player.getRingName().length() > 20)
			bindingResult.addError(new FieldError("player", "ringName", "Name is too long"));
		if (player.getClanName().length() > 20)
			bindingResult.addError(new FieldError("player", "clanName", "Clan is too long"));
	    //System.out.println("Saving player " + player);
	    Player persistedPlayer = dbService.getPlayer(player.getCardId());
	    if (persistedPlayer == null)
			throw new RuntimeException("Player not found");
	    if (player.getColor() != null && !player.getColor().isEmpty())
	    {
		    if (persistedPlayer.getGameId() == Player.VF4_VANILLA) {
		    	// 0,0,0,0,0,0,0,0
		    	Pattern pattern = Pattern.compile("^\\d?\\d,\\d?\\d,\\d?\\d,\\d?\\d,\\d?\\d,\\d?\\d,\\d?\\d,\\d?\\d$", 0);
		    	Matcher matcher = pattern.matcher(player.getColor());
		    	if (!matcher.find())
		    		bindingResult.addError(new FieldError("player", "color", player.getColor(), false, null, null, "Invalid colors: 8 numbers separated by comma"));
		    }
		    else {
		    	// 0,0,0,0,0,0,0,0,0,0,0,0
		    	Pattern pattern = Pattern.compile("^\\d?\\d,\\d?\\d,\\d?\\d,\\d?\\d,\\d?\\d,\\d?\\d,\\d?\\d,\\d?\\d,\\d?\\d,\\d?\\d,\\d?\\d,\\d?\\d$", 0);
		    	Matcher matcher = pattern.matcher(player.getColor());
		    	if (!matcher.find())
		    		bindingResult.addError(new FieldError("player", "color", player.getColor(), false, null, null, "Invalid colors: 12 numbers separated by comma"));
		    }
	    }
	    if (bindingResult.hasErrors())
	        return "player";
	    persistedPlayer.setRingName(player.getRingName());
	    persistedPlayer.setClanName(player.getClanName());
	    persistedPlayer.setColor(player.getColor());
	    persistedPlayer.setEmblem1(player.getEmblem1());
	    persistedPlayer.setEmblem2(player.getEmblem2());
		String addr = req.getRemoteAddr();
		if ("127.0.0.1".equals(addr) || "::1".equals(addr)) {
			String forwardedIp = req.getHeader("X-Forwarded-For");
			if (forwardedIp != null)
				addr = forwardedIp;
		}
		persistedPlayer.setLastSeenIp(addr);
		persistedPlayer.setLastSeen(new Timestamp(System.currentTimeMillis()));
	    dbService.savePlayer(persistedPlayer);
	    //model.clear();
	    model.addAttribute("message", "Changes saved");
	    return "player";
	}
}
