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

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "player")
public class Player
{
	public static final int VF4_VANILLA = 0;
	public static final int VF4_EVO = 1;
	public static final int VF4_FT = 2;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private int id;
	
	@Column(name = "card_id")
	private int cardId;
	
	// see VF4_*
	@Column(name = "game_id")
	private int gameId;
	
	@Column(name = "ring_name")
	private String ringName;
	
	@Column(name = "clan_name")
	private String clanName;
	
	@Column(name = "\"character\"")
	private Integer character;
	
	@Column(name = "created")
	private Timestamp created;
	
	@Column(name = "last_seen")
	private Timestamp lastSeen;
	
	@Column(name = "created_ip")
	private String createdIp;
	
	@Column(name = "last_seen_ip")
	private String lastSeenIp;
	
	@Column(name = "exp_points")
	private int expPoints;
	
	@Column(name = "ranking_points")
	private int rankingPoints;
	
	@Column(name = "my_tenpo_points")
	private int myTenpoPoints;
	
	@Column(name = "level")
	private int level;
	
	@Column(name = "wins")
	private int wins;

	@Column(name = "losses")
	private int losses;
	
	@Column(name = "stage_progress")
	private int stageProgress;
	
	@Column(name = "quest")
	private String quest;
	
	@Column(name = "emblem1")
	private int emblem1;

	@Column(name = "emblem2")
	private int emblem2;
	
	@Column(name = "color")
	private String color;

	@Column(name = "equip")
	private String equip;
	
	@Column(name = "alt_move_1")
	private Boolean altMove1;
	
	@Column(name = "alt_move_2")
	private Boolean altMove2;
	
	@Column(name = "presentation")
	private String presentation;

	@OneToMany(mappedBy="winner")
	private List<Match> wonMatches = new ArrayList<Match>();

	@OneToMany(mappedBy="looser")
	private List<Match> lostMatches = new ArrayList<Match>();

	public int getCardId() {
		return cardId;
	}

	public void setCardId(int cardId) {
		this.cardId = cardId;
	}

	public String getRingName() {
		return ringName;
	}

	public void setRingName(String ringName) {
		this.ringName = ringName;
	}

	public String getClanName() {
		return clanName;
	}

	public void setClanName(String clanName) {
		this.clanName = clanName;
	}

	public Timestamp getCreated() {
		return created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public Timestamp getLastSeen() {
		return lastSeen;
	}

	public void setLastSeen(Timestamp lastSeen) {
		this.lastSeen = lastSeen;
	}

	public String getCreatedIp() {
		return createdIp;
	}

	public void setCreatedIp(String createdIp) {
		this.createdIp = createdIp;
	}

	public String getLastSeenIp() {
		return lastSeenIp;
	}

	public void setLastSeenIp(String lastSeenIp) {
		this.lastSeenIp = lastSeenIp;
	}

	public int getExpPoints() {
		return expPoints;
	}

	public void setExpPoints(int expPoints) {
		this.expPoints = expPoints;
	}

	public int getRankingPoints() {
		return rankingPoints;
	}

	public void setRankingPoints(int rankingPoints) {
		this.rankingPoints = rankingPoints;
	}

	public int getMyTenpoPoints() {
		return myTenpoPoints;
	}

	public void setMyTenpoPoints(int myTenpoPoints) {
		this.myTenpoPoints = myTenpoPoints;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getWins() {
		return wins;
	}

	public void setWins(int wins) {
		this.wins = wins;
	}

	public int getLosses() {
		return losses;
	}

	public void setLosses(int losses) {
		this.losses = losses;
	}

	public int getStageProgress() {
		return stageProgress;
	}

	public void setStageProgress(int stageProgress) {
		this.stageProgress = stageProgress;
	}

	public String getQuest() {
		return quest;
	}

	public void setQuest(String quest) {
		this.quest = quest;
	}

	public int getEmblem1() {
		return emblem1;
	}

	public void setEmblem1(int emblem1) {
		this.emblem1 = emblem1;
	}

	public int getEmblem2() {
		return emblem2;
	}

	public void setEmblem2(int emblem2) {
		this.emblem2 = emblem2;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public Integer getCharacter() {
		return character;
	}

	public void setCharacter(Integer character) {
		this.character = character;
	}
	
    public String getEquip() {
		return equip;
	}

	public void setEquip(String equip) {
		this.equip = equip;
	}

	public Boolean getAltMove1() {
		return altMove1;
	}

	public void setAltMove1(Boolean altMove1) {
		this.altMove1 = altMove1;
	}

	public Boolean getAltMove2() {
		return altMove2;
	}

	public void setAltMove2(Boolean altMove2) {
		this.altMove2 = altMove2;
	}

	public String getCharacterName() {
		if (character == null)
			return "(unknown)";
		switch (character) {
		case 0: return "Akira";
		case 1: return "Sarah";
		case 2: return "Lau";
		case 3: return "Shun";
		case 4: return "Jeffry";
		case 5: return "Pai";
		case 6: return "Jacky";
		case 7: return "Kage";
		case 8: return "Lion";
		case 9: return "Wolf";
		case 10: return "Aoi";
		case 11: return "Lei-Fei";
		case 12: return "Vanessa";
		case 13: return "Dural";
		case 14: return "Goh";
		case 15: return "Brad";
		case 16: return "Test";		// ?
		case 17: return "Mot";		// ?
		case 18: return "Dst";		// ?
		default: return "(unknown)";

		}
	}

	@Override
	public String toString() {
		return "Player [id=" + id + ", cardId=" + cardId + ", gameId=" + gameId + ", ringName=" + ringName
				+ ", clanName=" + clanName + ", character=" + character + ", created=" + created + ", lastSeen="
				+ lastSeen + ", createdIp=" + createdIp + ", lastSeenIp=" + lastSeenIp + ", expPoints=" + expPoints
				+ ", rankingPoints=" + rankingPoints + ", myTenpoPoints=" + myTenpoPoints + ", level=" + level
				+ ", wins=" + wins + ", losses=" + losses + ", stageProgress=" + stageProgress + ", quest=" + quest
				+ ", emblem1=" + emblem1 + ", emblem2=" + emblem2 + ", color=" + color + "]";
	}
	
	private int getEquip(int index) {
		if (equip == null || equip.length() < 16)
			return 0;
		return Integer.parseInt(equip.substring(index, index + 2), 16);
	}

	private void setEquip(int index, int value) {
		if (equip == null)
			equip = "0000000000000000";
		else if (equip.length() < 16)
			equip = "0000000000000000".substring(0, 16 - equip.length()) + equip;
		String s = Integer.toHexString(value);
		if (s.length() < 2)
			s = "0" + s;
		equip = equip.substring(0, index) + s + equip.substring(index + 2);
	}

	public int getP1HeadEquip() {
		return getEquip(0);
	}

	public void setP1HeadEquip(int v) {
		setEquip(0, v);
	}

	public int getP1FaceEquip() {
		return getEquip(2);
	}

	public void setP1FaceEquip(int v) {
		setEquip(2, v);
	}

	public int getP1BodyEquip() {
		return getEquip(4);
	}

	public void setP1BodyEquip(int v) {
		setEquip(4, v);
	}

	public int getP1LegsEquip() {
		return getEquip(6);
	}

	public void setP1LegsEquip(int v) {
		setEquip(6, v);
	}

	public int getP2HeadEquip() {
		return getEquip(8);
	}

	public void setP2HeadEquip(int v) {
		setEquip(8, v);
	}

	public int getP2FaceEquip() {
		return getEquip(10);
	}

	public void setP2FaceEquip(int v) {
		setEquip(10, v);
	}

	public int getP2BodyEquip() {
		return getEquip(12);
	}

	public void setP2BodyEquip(int v) {
		setEquip(12, v);
	}

	public int getP2LegsEquip() {
		return getEquip(14);
	}

	public void setP2LegsEquip(int v) {
		setEquip(14, v);
	}

	public String getPresentation() {
		return presentation;
	}

	public void setPresentation(String presentation) {
		this.presentation = presentation;
	}
	
	private int getColor(int playerNum, int index) {
		if (color == null)
			return 0;
		String[] colors = color.split(",");
		if (index < 4)
			// body parts
			index = playerNum * 4 + index;
		else
			// hair, eyes
			index = 4 + playerNum * 2 + index;
		if (index >= colors.length)
			return 0;
		else
			return Integer.parseInt(colors[index]);
	}
	
	private void setColor(int playerNum, int index, int value) {
		if (color == null || color.isBlank())
			color = "0,0,0,0,0,0,0,0,0,0,0,0";
		String[] colors = color.split(",");
		if (index < 4)
			// body parts
			colors[playerNum * 4 + index] = Integer.toString(value);
		else
			// hair, eyes
			colors[4 + playerNum * 2 + index] = Integer.toString(value);
		color = String.join(",", colors);
	}

	public int getP1Color0() {
		return getColor(0, 0);
	}

	public void setP1Color0(int v) {
		setColor(0, 0, v);
	}

	public int getP1Color1() {
		return getColor(0, 1);
	}

	public void setP1Color1(int v) {
		setColor(0, 1, v);
	}

	public int getP1Color2() {
		return getColor(0, 2);
	}

	public void setP1Color2(int v) {
		setColor(0, 2, v);
	}

	public int getP1Color3() {
		return getColor(0, 3);
	}

	public void setP1Color3(int v) {
		setColor(0, 3, v);
	}

	public int getP1Color4() {
		return getColor(0, 4);
	}

	public void setP1Color4(int v) {
		setColor(0, 4, v);
	}

	public int getP1Color5() {
		return getColor(0, 5);
	}

	public void setP1Color5(int v) {
		setColor(0, 5, v);
	}

	public int getP2Color0() {
		return getColor(1, 0);
	}

	public void setP2Color0(int v) {
		setColor(1, 0, v);
	}

	public int getP2Color1() {
		return getColor(1, 1);
	}

	public void setP2Color1(int v) {
		setColor(1, 1, v);
	}

	public int getP2Color2() {
		return getColor(1, 2);
	}

	public void setP2Color2(int v) {
		setColor(1, 2, v);
	}

	public int getP2Color3() {
		return getColor(1, 3);
	}

	public void setP2Color3(int v) {
		setColor(1, 3, v);
	}

	public int getP2Color4() {
		return getColor(1, 4);
	}

	public void setP2Color4(int v) {
		setColor(1, 4, v);
	}

	public int getP2Color5() {
		return getColor(1, 5);
	}

	public void setP2Color5(int v) {
		setColor(1, 5, v);
	}
}
