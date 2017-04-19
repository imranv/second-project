package com.niit.collaboration.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.collaboration.dao.FriendDAO;
import com.niit.collaboration.dao.UserDAO;
import com.niit.collaboration.model.Friend;
import com.niit.collaboration.model.User;

@SuppressWarnings("deprecation")
@Repository("friendDAO")
public class FriendDAOImpl implements FriendDAO{
	
	@Autowired (required=true)
	 private SessionFactory sessionFactory;
	@Autowired 
	private UserDAO userDAO;
	
	
	public void FriendDaoimpl(SessionFactory sessionFactory)
	{
		try {
			this.sessionFactory=sessionFactory;
		} catch (Exception e) {
			//log.error("Unable to connect to db");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    @Transactional
	@Override
	public boolean save(Friend friend) {
		try {
			sessionFactory.getCurrentSession().save(friend);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return false;
	}
    @Transactional
	@Override
	public boolean update(Friend friend) {
		try {
			sessionFactory.getCurrentSession().update(friend);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return false;
	}
    @Transactional
	@Override
	public void delete(int id) {
		Friend friend =new Friend();
		friend.setId(id);
		sessionFactory.getCurrentSession().delete(friend);
		// TODO Auto-generated method stub
		
	}
    @Transactional
	@Override
	public Friend get(String uid,String fid) {
		
    		String s="from Friend where userID='"+uid+"' and friendID='"+fid+"'";
			List<Friend> friends=sessionFactory.getCurrentSession().createQuery(s).list();
    			 return friends.get(0);
	}
    @Transactional
	@Override
	public List<Friend> list() {
		String q="from Friend";
		  List<Friend> li=sessionFactory.getCurrentSession().createQuery(q). list();
		  if(li.isEmpty()||li==null)
			return null;
		  else
			  return li;
		// TODO Auto-generated method stub
		
	}
    @Transactional
	@Override
	public List<Friend> ListNewFriendRequest(String Userid) {
		Session session=sessionFactory.getCurrentSession();
		User user=userDAO.get(Userid);
		List<Friend> friends=session.createQuery("from Friend where friendID='"+Userid+"' and status='N'").getResultList();
		// TODO Auto-generated method stub
		return friends;
	}
    
    @Transactional
	@Override
	public List<Friend> ListMyFriends(String id) {
		Session session=sessionFactory.getCurrentSession();
		User user=userDAO.get(id);
		List<Friend> friends=session.createQuery("from Friend where userID='"+id+"' and status='A'").getResultList();
		// TODO Auto-generated method stub
		return friends;
		// TODO Auto-generated method stub
	
	}
	

}
