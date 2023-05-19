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

import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.sql.Timestamp;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ServletContextAware;

import com.flyinghead.vf4.db.IDbService;
import com.flyinghead.vf4.db.Player;

@Controller
public class PlayerController implements ServletContextAware {
	private static final Pattern HEXADECIMAL_PATTERN = Pattern.compile("\\p{XDigit}+");
	private static final Pattern COLOR_PATTERN = Pattern.compile("^\\d?\\d,\\d?\\d,\\d?\\d,\\d?\\d,\\d?\\d,\\d?\\d,\\d?\\d,\\d?\\d,\\d?\\d,\\d?\\d,\\d?\\d,\\d?\\d$", 0);

	public static class ItemImageResolver {
		private ServletContext context;
		
		public ItemImageResolver(ServletContext context) {
			this.context = context;
		}

		public String getImage(int character, boolean p2, int item) {
			return Items.getItemImage(context, character, p2, item);
		}
	}

	@Autowired
    private IDbService dbService;

    private ServletContext servletContext;
	
	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	@ModelAttribute
	public Items getItems() {
		return new Items();
	}

	@ModelAttribute
	public Colors getColors() {
		return new Colors();
	}
	
	@ModelAttribute
	public ItemImageResolver getImgResolver() {
		return new ItemImageResolver(servletContext);
	}

	@RequestMapping("/player")
	public String editPlayer(@RequestParam("id") int playerId, final ModelMap model, @RequestParam(value="advanced", defaultValue="false") boolean advanced) {
		if (playerId == 0)
			throw new IllegalStateException("Null player id");
		Player player = dbService.getPlayer(playerId);
		if (player == null)
			throw new RuntimeException("Player not found");
		model.addAttribute("player", player);
		model.addAttribute("matchList", dbService.listPlayerMatches(playerId));
		model.addAttribute("advanced", advanced);
		
	    return "player";
	}
	
	@RequestMapping(value="/player", params={"save"})
	public String savePlayer(final Player player, final BindingResult bindingResult, final ModelMap model, HttpServletRequest req) {
		if (player.getRingName().length() > 20)
			bindingResult.addError(new FieldError("player", "ringName", player.getRingName(), false, null, null, 
					"Name is too long"));
		CharsetEncoder encoder = Charset.forName("EUC-JP").newEncoder();
		if (!encoder.canEncode(player.getRingName()))
			bindingResult.addError(new FieldError("player", "ringName", player.getRingName(), false, null, null,
					"Invalid character in ring name"));
		encoder.reset();
		if (player.getClanName() != null)
		{
			if (player.getClanName().length() > 20)
				bindingResult.addError(new FieldError("player", "clanName", player.getClanName(), false, null, null,
						"Clan is too long"));
			if (!encoder.canEncode(player.getClanName()))
				bindingResult.addError(new FieldError("player", "clanName", player.getClanName(), false, null, null,
						"Invalid character in clan name"));
			encoder.reset();
		}
		if (player.getPresentation() != null) {
			String[] lines = player.getPresentation().split("\r\n");
			if (lines.length > 3)
				bindingResult.addError(new FieldError("player", "presentation", player.getPresentation(), false, null, null,
						"Presentation max 3 lines"));
			else {
				for (String line : lines)
					if (line.length() > 30) {
						bindingResult.addError(new FieldError("player", "presentation", player.getPresentation(), false, null, null,
								"Presentation max 30 characters per line"));
						break;
					}
			}
			if (!encoder.canEncode(player.getPresentation()))
				bindingResult.addError(new FieldError("player", "presentation", player.getPresentation(), false, null, null,
						"Invalid character in presentation"));
		}
	    //System.out.println("Saving player " + player);
	    Player persistedPlayer = dbService.getPlayer(player.getCardId());
	    if (persistedPlayer == null)
			throw new RuntimeException("Player not found");
	    if (player.getColor() != null && !player.getColor().isEmpty())
	    {
	    	// We force 12 colors but vanilla only uses the first 8
	    	// 0,0,0,0,0,0,0,0,0,0,0,0
	    	Matcher matcher = COLOR_PATTERN.matcher(player.getColor());
	    	if (!matcher.find())
	    		bindingResult.addError(new FieldError("player", "color", player.getColor(), false, null, null, 
	    				"Invalid colors: 12 numbers separated by comma"));
	    }
	    if (player.getEquip() != null && !player.getEquip().isEmpty()) {
	    	// TODO vanilla format is different
	    	if (player.getEquip().length() > 16
	    			|| !HEXADECIMAL_PATTERN.matcher(player.getEquip()).matches())
	    		bindingResult.addError(new FieldError("player", "equip", player.getEquip(), false, null, null, "Invalid equipment: max 16 hex digits"));
	    	else if (player.getEquip().length() < 16)
	    		player.setEquip("0000000000000000".substring(0, 16 - player.getEquip().length())
	    				+ player.getEquip());
	    }
	    persistedPlayer.setRingName(player.getRingName());
	    persistedPlayer.setClanName(player.getClanName());
	    persistedPlayer.setColor(player.getColor());
	    persistedPlayer.setEmblem1(player.getEmblem1());
	    persistedPlayer.setEmblem2(player.getEmblem2());
	    persistedPlayer.setEquip(player.getEquip());
	    persistedPlayer.setAltMove1(player.getAltMove1());
	    persistedPlayer.setAltMove2(player.getAltMove2());
	    persistedPlayer.setPresentation(player.getPresentation());
	    if (bindingResult.hasErrors()) {
	    	player.setGameId(persistedPlayer.getGameId());
	    	player.setCharacter(persistedPlayer.getCharacter());
		    model.addAttribute("player", player);
			model.addAttribute("matchList", dbService.listPlayerMatches(player.getCardId()));
	        return "player";
	    }
		String addr = req.getRemoteAddr();
		if ("127.0.0.1".equals(addr) || "::1".equals(addr)) {
			String forwardedIp = req.getHeader("X-Forwarded-For");
			if (forwardedIp != null)
				addr = forwardedIp;
		}
		persistedPlayer.setLastSeenIp(addr);
		persistedPlayer.setLastSeen(new Timestamp(System.currentTimeMillis()));
	    dbService.savePlayer(persistedPlayer);
	    model.clear();
	    model.addAttribute("message", "Changes saved");
	    return "redirect:player?id=" + String.valueOf(player.getCardId());
	}
}
