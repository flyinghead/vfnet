package com.flyinghead.vf4.db;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "\"match\"")
public class Match {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private int id;

	@ManyToOne
	@JoinColumn(name="winner_id")
	Player winner;

	@ManyToOne
	@JoinColumn(name="looser_id")
	Player looser;

    @Column(name = "end_time")
	private Timestamp endTime;
    
    @Column(name = "win_character")
    int winCharacter;
    
    @Column(name = "loose_character")
    int looseCharacter;
    
    @Column(name = "chain_of_win")
    int chainOfWin;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Player getWinner() {
		return winner;
	}

	public void setWinner(Player winner) {
		this.winner = winner;
	}

	public Player getLooser() {
		return looser;
	}

	public void setLooser(Player looser) {
		this.looser = looser;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public int getWinCharacter() {
		return winCharacter;
	}

	public void setWinCharacter(int winCharacter) {
		this.winCharacter = winCharacter;
	}

	public int getLooseCharacter() {
		return looseCharacter;
	}

	public void setLooseCharacter(int looseCharacter) {
		this.looseCharacter = looseCharacter;
	}

	public int getChainOfWin() {
		return chainOfWin;
	}

	public void setChainOfWin(int chainOfWin) {
		this.chainOfWin = chainOfWin;
	}
}
