package com.flyinghead.vf4;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.zip.DeflaterInputStream;
import java.util.zip.InflaterInputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.flyinghead.vf4.db.IDbService;
import com.flyinghead.vf4.db.Match;
import com.flyinghead.vf4.db.Player;

@WebServlet("/sys/servlet/vf4/VF4NetUpload")
public class VF4NetUpload extends BaseVf4Servlet
{
	private static final long serialVersionUID = 1L;
	
	private static final String VF4_VANILLA_UA = "NAOMI-VF4/1.0";
	private static final String VF4_EVO_UA = "NAOMI-VF4EV/1.0";
	private static final String VF4_FT_UA = "NAOMI-VF4FT/1.0";

	@Autowired
    private IDbService dbService;

	private int getGameTypeFromUA(HttpServletRequest req)
	{
		String userAgent = req.getHeader("User-Agent");
		if (VF4_FT_UA.equals(userAgent))
			return Player.VF4_FT;
		else if (VF4_EVO_UA.equals(userAgent))
			return Player.VF4_EVO;
		else if (VF4_VANILLA_UA.equals(userAgent))
			return Player.VF4_VANILLA;
		else
			return -1;
	}
	
	private String[] getAccessCodes(int gameType, QueryParams params)
	{
		String[] accessCodes = new String[2];

		for (int i = 0; i < 2; i++)
		{
			if (gameType != 0)
				// EVO and FT
				accessCodes[i] = params.playerGet(i, "access_code");
			else
				// VANILLA
				accessCodes[i] = params.playerGet(i, "id");
		}
		return accessCodes;
	}

	private void addEmblems(Map<String, String> params, int playerNum, Player player)
	{
		StringBuilder emblems = new StringBuilder();
		if (player.getEmblem1() != 0)
			emblems.append(player.getEmblem1());
		if (player.getEmblem2() != 0) {
			if (emblems.length() > 0)
				emblems.append(',');
			emblems.append(player.getEmblem2());
		}
		if (emblems.length() > 0)
			params.put(QueryParams.playerQName(playerNum, "emblem"), emblems.toString());
	}
	
	private void addColor(Map<String, String> params, int playerNum, Player player)
	{
		if (player.getColor() != null && !player.getColor().isEmpty())
			params.put(QueryParams.playerQName(playerNum, "color"), player.getColor());
	}
	
	private String getRemoteIP(HttpServletRequest req) {
		String addr = req.getRemoteAddr();
		if ("127.0.0.1".equals(addr) || "::1".equals(addr)) {
			String forwardedIp = req.getHeader("X-Forwarded-For");
			if (forwardedIp != null)
				addr = forwardedIp;
		}
		return addr;
	}

	private Map<String, String> doPowerOn()
	{
		 // cmd=power_on&serial=37821967197&place_id=6666&cnt=0
		Map<String, String> outParams = new HashMap<>();
		outParams.put("db_stat", "1");
		outParams.put("rare", "");
		outParams.put("syuchu", "");
		outParams.put("kumite", "");
		outParams.put("nushi", "0");
		outParams.put("masa", "1");
		outParams.put("ryuko", "1");
		outParams.put("ticket_get", "100");
		outParams.put("struggle_revise", "");
		outParams.put("nickname_revise", "");
		
		return outParams;
	}
	
	private String doMessage()
	{
		return "Flycast VF4 Network!";
	}

	private Map<String, String> doSelect(HttpServletRequest req, QueryParams params)
	{
		// cmd=select&access_code_1p=4240965643711192&card_id_1p=0
		//sb.append("color_1p=1,1,1,0,0,0,0,0,0,0,0,0&equip_1p=0000000000020100&sv_item_1p=2,4,5&emblem_1p=1&tec_1p=1,0&ring_name_1p=Flyinghead");
		//sb.append("&color_2p=3,2,1,0,0,0,0,0,0,0,0,0&equip_2p=0000000000020100&sv_item_2p=2,4,5&emblem_2p=1&tec_2p=1,0&ring_name_2p=Flyinghead2&db_stat=1");
		Map<String, String> outParams = new HashMap<>();
		int gameType = getGameTypeFromUA(req);
		String[] accessCodes = getAccessCodes(gameType, params);
		for (int i = 0; i < 2; i++)
		{
			if (accessCodes[i] != null && !accessCodes[i].isEmpty())
			{
				if (gameType == Player.VF4_VANILLA)
					outParams.put(QueryParams.playerQName(i, "id"), accessCodes[i]);
				else
					outParams.put(QueryParams.playerQName(i, "access_code"), accessCodes[i]);
				int cardId;
				if (gameType == Player.VF4_VANILLA)
					cardId = Integer.parseInt(accessCodes[i].substring(9));
				else
					cardId = params.playerGetInt(i, "card_id");
				if (cardId != 0)
				{
					Player player = dbService.getPlayer(cardId);
					if (player != null)
					{
						addEmblems(outParams, i, player);
						addColor(outParams, i, player);
						if (gameType == Player.VF4_FT && player.getGameId() == Player.VF4_EVO)
						{
							// EVO cards are upgradable by FT
							player.setGameId(gameType);
							dbService.savePlayer(player);
						}
					}
					else if (gameType == Player.VF4_VANILLA)
					{
						// Vanilla card ids are assigned by the game so store the card now
						player = new Player();
						player.setCardId(cardId);
						player.setGameId(gameType);
						player.setCharacter(params.playerGetInt(i, "char", -1) % 100);
						player.setCreated(new Timestamp(System.currentTimeMillis()));
						player.setCreatedIp(getRemoteIP(req));
						player.setLastSeen(player.getCreated());
						player.setLastSeenIp(player.getCreatedIp());
						dbService.savePlayer(player);
					}
				}
			}
		}
		return outParams;
	}

	private Map<String, String> doStart(HttpServletRequest req, QueryParams params)
	{
		// cmd=start & cpu=1 & id=2304231100150000 & serial=37821967197 & place_id=0001 & access_code_1p=4240965643711192 & card_id_1p=0 & char_1p=12 & quest_1p=0,0,0 & cnt=3
		Map<String, String> outParams = new HashMap<>();
		outParams.put("id", params.get("id"));
		outParams.put("db_stat", "1");
		int gameType = getGameTypeFromUA(req);
		String[] accessCodes = getAccessCodes(gameType, params);
		for (int i = 0; i < 2; i++)
		{
			if (accessCodes[i] != null && !accessCodes[i].isEmpty())
			{
				if (gameType == Player.VF4_VANILLA) {
					if ("4240965643711192".equals(accessCodes[i]))
						accessCodes[i] = "4403393343571863";
					outParams.put(QueryParams.playerQName(i, "id"), accessCodes[i]);
				}
				else
					outParams.put(QueryParams.playerQName(i, "access_code"), accessCodes[i]);

				int character = params.playerGetInt(i, "char", -1) % 100;
				int cardId;
				if (gameType == Player.VF4_VANILLA)
					cardId = Integer.parseInt(accessCodes[i].substring(9));
				else
					cardId = params.playerGetInt(i, "card_id");
				if (cardId == 0)
				{
					// Only for EVO and FT
					do {
						cardId = ThreadLocalRandom.current().nextInt(1, 0x1000000);
					} while (dbService.getPlayer(cardId) != null);
					outParams.put(QueryParams.playerQName(i, "card_id"), String.valueOf(cardId));
					Player player = new Player();
					player.setCardId(cardId);
					player.setGameId(gameType);
					player.setCharacter(character);
					player.setCreated(new Timestamp(System.currentTimeMillis()));
					player.setCreatedIp(getRemoteIP(req));
					player.setLastSeen(player.getCreated());
					player.setLastSeenIp(player.getCreatedIp());
					dbService.savePlayer(player);
				}
				else
				{
					Player player = dbService.getPlayer(cardId);
					if (player != null)
					{
						// FIXME
						if (player.getCharacter() == null || player.getCharacter() == -1) {
							player.setCharacter(character);
							dbService.savePlayer(player);
						}
						if (player.getRingName() != null && !player.getRingName().isBlank())
							outParams.put(QueryParams.playerQName(i, "ring_name"), player.getRingName());
						if (player.getClanName() != null && !player.getClanName().isBlank())
							outParams.put(QueryParams.playerQName(i, "clan_name"), player.getClanName());
						addEmblems(outParams, i, player);
						addColor(outParams, i, player);
					}
					else
					{
						// Persist player if not found
						player = new Player();
						player.setCardId(cardId);
						player.setGameId(gameType);
						player.setCharacter(character);
						player.setCreated(new Timestamp(System.currentTimeMillis()));
						player.setCreatedIp(getRemoteIP(req));
						player.setLastSeen(player.getCreated());
						player.setLastSeenIp(player.getCreatedIp());
						dbService.savePlayer(player);
					}
				}
			}
		}

		return outParams;
	}

	private Map<String, String> doEnd(HttpServletRequest req, QueryParams params)
	{
		// cmd=end&cpu=1&id=2304221505510001&serial=37821967197&place_id=0001&free_play=1&ng_play=0&cnt=3
		// with card:
		// cmd=end&cpu=1&id=2304231103330001&serial=37821967197&place_id=0001&free_play=1&ng_play=0&access_code_1p=4240965643711192&card_id_1p=0&exp_1p=3
		//     &char_1p=12&level_1p=0&win_1p=0&lose_1p=0&level_other_1p=0,0,3&item_equip_1p=0000000000000000&item_have_1p=&item_curse_1p=0
		//     &color_1p=0,0,0,0,0,0,0,0,0,0,0,0&ranking_point_1p=0&my_tenpo_point_1p=0&quest_1p=0,0,0&stage_progress_1p=2&cnt=6
		// cmd=end&cpu=1&id=2304242008460001&serial=37821967197&place_id=0001&free_play=1&ng_play=0
		//     &ack=color_1p,equip_1p&access_code_1p=4240965643711192&card_id_1p=0&exp_1p=2&char_1p=12&level_1p=0&win_1p=0&lose_1p=0
		//     &level_other_1p=0,0,2&item_equip_1p=0000000000000000&item_have_1p=2,4,5&item_curse_1p=0&color_1p=0,0,0,0,0,0,0,0,0,0,0,0
		//     &ranking_point_1p=0&my_tenpo_point_1p=0&quest_1p=0,0,0&stage_progress_1p=1&cnt=5
		// ranking match:
		// cmd=end&id=2304242329410012&serial=37821967197&place_id=0001&free_play=1&ng_play=0&winner=1&chain_of_win=0&ack=color_1p,equip_1p,color_2p,equip_2p
		//	&access_code_1p=4240965643711192&card_id_1p=0&exp_1p=102&char_1p=12&level_1p=1&win_1p=4&lose_1p=2&level_other_1p=0,0,2&item_equip_1p=0000000000000200
		//  &item_have_1p=2,4,5&item_curse_1p=0&color_1p=1,1,1,0,0,0,0,0,0,0,0,0&ranking_point_1p=0&my_tenpo_point_1p=0&quest_1p=0,0,0&vs_char_1p=1
		//  &access_code_2p=4240965643711192&card_id_2p=0&exp_2p=18&char_2p=1&level_2p=0&win_2p=1&lose_2p=0&level_other_2p=0,1,2&item_equip_2p=0000000000000000
		//  &item_have_2p=2,5&item_curse_2p=0&color_2p=0,0,0,0,0,0,0,0,0,0,0,0&ranking_point_2p=0&my_tenpo_point_2p=0&quest_2p=0,0,0&vs_char_2p=12&cnt=5
		// vanilla:
		// cmd=end&id=870320-15-53-20001&serial=37821967197&place_id=0001&win=0&chain_of_win=0&id_1p=4240965643711192&exp_1p=13&advise_1p=
		// 2,14,402,0,0,2,1,25,1,1,
		// 0,0,0,0,0,0,0,0,0,0,
		// 0,0,0,0,0,0,0,0,0,0,
		// 0,0,0,0,0,1,0,2,0,0,
		// 0,0,402,1800,0,2,0,0,0,0,
		// 0,2,0,0,0,0,0,0,0,2,
		// 0,0,0,0,0,0,0,0,0,0,
		// 0,0,0,0,0,0,0,0,0,0,
		// 0,0,0,0,0,0,0,0,2,0,
		// 0,25,0,0,0,0,0,0,0,0,
		// 0,0&advise_ex_1p=0,395,1,25,0,0&item_1p=0000000000000000,000000002000000000000000,00,00&color_1p=0,0,0,0,0,0,0,0&cnt=6
		// from code:
		// cmd=end&cpu=1&id=...&serial=...&place_id=...&free_play=1&ng_play=1&winner=0&chain_of_win=2
		// &access_code=...&card_id=...&...
		Map<String, String> outParams = new HashMap<>();
		outParams.put("id", params.get("id"));
		outParams.put("db_stat", "1");

		int gameType = getGameTypeFromUA(req);
		String[] accessCodes = getAccessCodes(gameType, params);
		Player[] players = new Player[2];
		for (int i = 0; i < 2; i++)
		{
			int cardId;
			if (gameType == Player.VF4_VANILLA) {
				if (accessCodes[i] == null || accessCodes[i].isEmpty())
					continue;
				cardId = Integer.parseInt(accessCodes[i].substring(9));
			}
			else {
				cardId = params.playerGetInt(i, "card_id");
			}
			if (cardId == 0)
				continue;
			players[i] = dbService.getPlayer(cardId);
			if (players[i] == null)
				continue;
			players[i].setExpPoints(params.playerGetInt(i, "exp", players[i].getExpPoints()));
			players[i].setLevel(params.playerGetInt(i, "level", players[i].getLevel()));
			players[i].setWins(params.playerGetInt(i, "win", players[i].getWins()));
			players[i].setLosses(params.playerGetInt(i, "lose", players[i].getLosses()));
			players[i].setRankingPoints(params.playerGetInt(i, "ranking_point", players[i].getRankingPoints()));
			players[i].setMyTenpoPoints(params.playerGetInt(i, "my_tenpo_point", players[i].getMyTenpoPoints()));
			players[i].setQuest(params.playerGet(i, "quest", players[i].getQuest()));
			players[i].setLastSeen(new Timestamp(System.currentTimeMillis()));
			players[i].setLastSeenIp(getRemoteIP(req));
			dbService.savePlayer(players[i]);
		}
		int winner = gameType == Player.VF4_VANILLA ? params.getInt("win", -1) : params.getInt("winner", -1);
		if (winner != -1)
		{
			Match match = new Match();
			match.setEndTime(new Timestamp(System.currentTimeMillis()));
			match.setWinner(winner == 0 ? players[0] : players[1]);
			match.setLooser(winner == 0 ? players[1] : players[0]);
			match.setChainOfWin(params.getInt("chain_of_win"));
			match.setWinCharacter(params.playerGetInt(winner, "char", -1) % 100);
			match.setLooseCharacter(params.playerGetInt(1 - winner, "char", -1) % 100);
			dbService.saveMatch(match);
		}
		//if (!accessCode1.isEmpty())
		//	sb.append("&access_code_1p=").append(accessCode1);
		//if (!accessCode2.isEmpty())
		//	sb.append("&access_code_2p=").append(accessCode2);

		return outParams;
	}

	private Map<String, String> doTest(HttpServletRequest req, QueryParams params)
	{
		// cmd=test&serial=37821967197&cnt=0
		Map<String, String> outParams = new HashMap<>();
		outParams.put("stat", "1");
		String value = params.get("serial");
		if (value != null)
			outParams.put("serial", value);
		
		return outParams;
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		// Request headers:
		// x-rd2-crc: crc32 of the payload
		// x-rd2-wait: 0000,0000,0000,0000
		// x-rd2-size: 0000,0000,0000,0000
		//
		// Response headers:
		// x-rd2-cmd: start, end, power_on, message, test, advise, select, mission
		//
		// cmd=power_on&serial=37821967197&place_id=6666&cnt=0
		// response params:
		// db_stat				1 (required)
		// rare					max 4 ints separated with , (only first 3 used?)
		// syuchu				max 3 ints separated with , (only first 2 used? if [0]==0 then use 800, if [1] == 0 then use 400)
		// kumite				max 75 ints separated with , (values are looked up sequentially and matched against players' card id) 
		// nushi				0 or 1, if 1 card ids sent by the server are ignored by the game
		// masa					0 or 1
		// ryuko				0 or 1
		// struggle_revise		max 16 ints sep ,
		// nickname_revise		max 8 shorts sep , (7 used, defaults to 100 if 0)
		// ticket_get			int, 0 to 100?
		//
		// cmd=message&serial=%s&cnt=%d
		// resp: payload is message
		//
		// cmd=test&serial=37821967197&cnt=0
		// expects stat=1 in response
		// 
		// no card:
		// cmd=start&cpu=1&id=2304211804300000&serial=37821967197&place_id=6666&cnt=2
		// with card:
		// cmd=start&cpu=1&id=2304231100150000&serial=37821967197&place_id=0001&access_code_1p=4240965643711192&card_id_1p=0&char_1p=12&quest_1p=0,0,0&cnt=3
		// vanilla:
		// cmd=start&cpu=1&id=870321-7-2-270004&serial=37821967197&id_2p=4240965643711192&char_2p=111&place_id=0001&cnt=4
		// id_1p (vanilla) is the same as access_code_1p (ft)
		// cpu=1 => match vs cpu
		// response params:
		// id					yymmddhhmmss???? last four digits looks like a counter
		// db_stat 				1 (required)
		// (the following ones only if using a card)
		// ring_name_1p/2p		max 20 chars
		// clan_name_1p/2p		max 20 chars
		// equip_1p/...			(mv? only parse 1st one) max 16 hex digits (8 items)
		// sv_item_1p			mv, max 16 byte values, min 1 value 
		// emblem_1p			mv, max 2 byte values, 1: A with red wave, tested up to 5
		// color_1p				exactly 12 mv byte values [0-20] (8 for vanilla)
		// leader_1p
		// money_1p
		// chat[0-3]_1p
		// card_id_1p 			int, [0-0xffffff] (evo and ft)
		// tenpo_info_1p		2 values: short, [0-10] (2nd used if nushi=1)
		// disp_1p				0-7
		// user_1p 				1 or 2
		// foul_1p 				set or not
		// revise_1p
		// pr_1p
		// nickname_lv_1p		mv 9 bytes strict?
		// nickname_pt_1p		mv 3*7 shorts strict?
		// nickname_para_1p		mv 3 bytes strict?
		// ranking_point_1p
		// my_tenpo_point_1p
		// tec_1p= 				multiple 0/1 values (only first 2 used)
		//
		// cmd=select&access_code_1p=4240965643711192&card_id_1p=0&cnt=2
		// response params:
		// access_code_1p		(ft, evo) req'd
		// id_1p				(vanilla) req'd, same as access_code_1p
		// color_1p				same as start, doesn't work on first select, need a 2nd match?
		// sv_item_1p			same as start
		// equip_1p				same as start
		// emblem_1p			same as start, seems to work
		// tec_1p				same as start
		//
		// cmd=end&id=2304242329410012&serial=37821967197&place_id=0001&free_play=1&ng_play=0&winner=1&chain_of_win=0&ack=color_1p,equip_1p,color_2p,equip_2p
		//	&access_code_1p=4240965643711192&card_id_1p=0&exp_1p=102&char_1p=12&level_1p=1&win_1p=4&lose_1p=2&level_other_1p=0,0,2&item_equip_1p=0000000000000200
		//  &item_have_1p=2,4,5&item_curse_1p=0&color_1p=1,1,1,0,0,0,0,0,0,0,0,0&ranking_point_1p=0&my_tenpo_point_1p=0&quest_1p=0,0,0&vs_char_1p=1
		//  &access_code_2p=4240965643711192&card_id_2p=0&exp_2p=18&char_2p=1&level_2p=0&win_2p=1&lose_2p=0&level_other_2p=0,1,2&item_equip_2p=0000000000000000
		//  &item_have_2p=2,5&item_curse_2p=0&color_2p=0,0,0,0,0,0,0,0,0,0,0,0&ranking_point_2p=0&my_tenpo_point_2p=0&quest_2p=0,0,0&vs_char_2p=12&cnt=5
		// vanilla:
		// cmd=end&cpu=1&id=870321-7-2-270004&serial=37821967197&place_id=0001&id_2p=4240965643711192&exp_2p=2&item_2p=0000000000000000,000000000000000000000000,00,00
		// &color_2p=0,0,0,0,0,0,0,0&mileage_2p=0&cpu_game_cnt_2p=1&cnt=7
		// response:
		// id			required, must match
		// db_stat=1	required
		//
		StringBuilder sb = new StringBuilder();
		while (true) {
			String in = req.getReader().readLine();
			if (in == null)
				break;
			sb.append(in);
		}
		// base64 decode
		byte[] bytes = Base64.getDecoder().decode(sb.toString());
		// deflate
		ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
		InflaterInputStream iis = new InflaterInputStream(bais);
		bytes = iis.readAllBytes();
		// parse params
		String paramString = new String(bytes);
		log(paramString);
		QueryParams params = new QueryParams(paramString);

		sb = new StringBuilder();
		Map<String, String> outParams = null;
		String cmd = params.get("cmd");
		if ("power_on".equals(cmd))
			outParams = doPowerOn();
		else if ("select".equals(cmd))
			outParams = doSelect(req, params);
		else if ("start".equals(cmd))
			outParams = doStart(req, params);
		else if ("end".equals(cmd))
			outParams = doEnd(req, params);
		else if ("test".equals(cmd))
			outParams = doTest(req, params);
		else if ("message".equals(cmd))
			sb.append(doMessage());
		// vanilla:
		// cmd=update_card&old_id_1p=%s&id_1p=%s&cnt=%d
		// advise
		else {
			resp.sendError(500, "Command not implemented: " + cmd);
			return;
		}
		
		if (outParams != null)
		{
			for (String name : outParams.keySet())
			{
				if (sb.length() > 0)
					sb.append('&');
				sb.append(name).append('=').append(URLEncoder.encode(outParams.get(name), "UTF-8"));
			}
		}
		log(" -> " + sb.toString());
		resp.setHeader("Connection", "close");	// ignored, need nginx config
		resp.setHeader("x-rd2-cmd", cmd);
		
		// deflate (doesn't seem to be required)
		DeflaterInputStream dos = new DeflaterInputStream(new ByteArrayInputStream(sb.toString().getBytes()));
		bytes = dos.readAllBytes();
		// base64 encode
		String out = Base64.getEncoder().encodeToString(bytes) + "\r\n";
		
		resp.setHeader("x-rd2-cmd", cmd);

		resp.setHeader("Pragma", "DFI");		// indicates that payload is deflated and base64 encoded
		resp.getWriter().write(out);
//		resp.getWriter().write(sb.toString());
	}
}
