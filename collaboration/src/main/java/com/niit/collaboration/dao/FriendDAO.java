package com.niit.collaboration.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.niit.collaboration.model.Friend;
@Repository("friendDAO")
public interface FriendDAO {
	public boolean save(Friend friend  );
	public boolean update(Friend friend );
	public void delete(int id);
	public Friend get(String uid,String fid);
	public List<Friend> list();
	public List<Friend> ListMyFriends(String id);
	public List<Friend> ListNewFriendRequest(String Userid);

}

