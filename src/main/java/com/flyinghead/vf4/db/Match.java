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
