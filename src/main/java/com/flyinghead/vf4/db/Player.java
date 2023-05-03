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
	String ringName;
	
	@Column(name = "clan_name")
	String clanName;
	
	@Column(name = "\"character\"")
	Integer character;
	
	@Column(name = "created")
	Timestamp created;
	
	@Column(name = "last_seen")
	Timestamp lastSeen;
	
	@Column(name = "created_ip")
	String createdIp;
	
	@Column(name = "last_seen_ip")
	String lastSeenIp;
	
	@Column(name = "exp_points")
	int expPoints;
	
	@Column(name = "ranking_points")
	int rankingPoints;
	
	@Column(name = "my_tenpo_points")
	int myTenpoPoints;
	
	@Column(name = "level")
	int level;
	
	@Column(name = "wins")
	int wins;

	@Column(name = "losses")
	int losses;
	
	@Column(name = "stage_progress")
	int stageProgress;
	
	@Column(name = "quest")
	String quest;
	
	@Column(name = "emblem1")
	int emblem1;

	@Column(name = "emblem2")
	int emblem2;
	
	@Column(name = "color")
	String color;

	@Column(name = "equip")
	String equip;

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
}
