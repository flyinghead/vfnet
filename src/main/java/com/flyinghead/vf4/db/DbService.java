package com.flyinghead.vf4.db;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Service
public class DbService implements IDbService
{
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public Player getPlayer(int cardId)
	{
		Session session = sessionFactory.getCurrentSession();
		Query<Player> query = session.createQuery("from Player where cardId = :cardId", Player.class)
				.setParameter("cardId", cardId);
		Player player = null;
		try {
			player = query.getSingleResult();
		} catch (NoResultException e) {
		}
		return player;
	}
	
	@Override
	@Transactional
	public void savePlayer(Player player)
	{
		Session session = sessionFactory.getCurrentSession();
		session.merge(player);
	}
	
	@Override
	@Transactional
	public List<Player> listPlayers()
	{
		Session session = sessionFactory.getCurrentSession();
		List<Player> list = session.createQuery("from Player order by ringName", Player.class).list();
		return list;
	}
	
	@Override
	@Transactional
	public void saveMatch(Match match)
	{
		Session session = sessionFactory.getCurrentSession();
		session.merge(match);
	}
	
	@Override
	@Transactional
	public List<Match> listMatches()
	{
		Session session = sessionFactory.getCurrentSession();
		List<Match> list = session.createQuery("from Match order by endTime desc", Match.class).list();
		return list;
	}
	
	@Override
	@Transactional
	public List<Match> listPlayerMatches(int cardId) {
		Session session = sessionFactory.getCurrentSession();
		List<Match> list = session.createQuery("from Match m where m.winner.cardId=" + cardId + " or m.looser.cardId=" + cardId + " order by endTime desc", Match.class).list();
		return list;
	}

}
