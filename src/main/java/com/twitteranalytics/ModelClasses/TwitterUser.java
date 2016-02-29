package com.twitteranalytics.ModelClasses;

import java.io.Serializable;
import java.util.Date;

import twitter4j.User;

public class TwitterUser implements Serializable{
	
	/**
	 * Variable Declaration
	 */
	private long UserId;
	private String Screenname;
	private String Username;
	private String ProfileImageURL;
	private int Follower_Count;
	private int Friend_Count;
	private Date Join_Date;
	private String User_Location;
	private byte Status_Count;
	private int Favourite_Count;

	
	public TwitterUser() {
		super();
	}

	
	public TwitterUser(long userId, String screenname, String username,String Profileimage,
			int follower_Count, int friend_Count, Date join_Date,
			String user_Location, byte status_Count, int favourite_Count) {
		super();
		UserId = userId;
		Screenname = screenname;
		Username = username;
		ProfileImageURL=Profileimage;
		Follower_Count = follower_Count;
		Friend_Count = friend_Count;
		Join_Date = join_Date;
		User_Location = user_Location;
		Status_Count = status_Count;
		Favourite_Count = favourite_Count;
	}
	

	public TwitterUser parseUserDetails(User user){
		this.UserId=user.getId();
		this.Screenname = user.getScreenName();
		this.Username = user.getName();
		this.ProfileImageURL= user.getMiniProfileImageURLHttps();
		this.Follower_Count = user.getFollowersCount();
		this.Friend_Count = user.getFriendsCount();
		this.Join_Date = user.getCreatedAt();
		this.User_Location = user.getLocation();
		this.Status_Count = (byte)user.getStatusesCount();
		this.Favourite_Count = user.getFavouritesCount();
		
		return this;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TwitterUser [UserId=" + UserId + ", Screenname=" + Screenname
				+ ", Username=" + Username + ", ProfileImage=" + ProfileImageURL
				+ ", Follower_Count=" + Follower_Count + ", Friend_Count="
				+ Friend_Count + ", Join_Date=" + Join_Date
				+ ", User_Location=" + User_Location + ", Status_Count="
				+ Status_Count + ", Favourite_Count=" + Favourite_Count + "]";
	}


	/**
	 * @return the profileImage
	 */
	public String getProfileImage() {
		return ProfileImageURL;
	}


	/**
	 * @param profileImage the profileImage to set
	 */
	public void setProfileImage(String profileImage) {
		ProfileImageURL = profileImage;
	}


	/**
	 * @return the userId
	 */
	public long getUserId() {
		return UserId;
	}


	/**
	 * @param userId the userId to set
	 */
	public void setUserId(long userId) {
		UserId = userId;
	}


	/**
	 * @return the screenname
	 */
	public String getScreenname() {
		return Screenname;
	}


	/**
	 * @param screenname the screenname to set
	 */
	public void setScreenname(String screenname) {
		Screenname = screenname;
	}


	/**
	 * @return the username
	 */
	public String getUsername() {
		return Username;
	}


	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		Username = username;
	}


	/**
	 * @return the follower_Count
	 */
	public int getFollower_Count() {
		return Follower_Count;
	}


	/**
	 * @param follower_Count the follower_Count to set
	 */
	public void setFollower_Count(int follower_Count) {
		Follower_Count = follower_Count;
	}


	/**
	 * @return the friend_Count
	 */
	public int getFriend_Count() {
		return Friend_Count;
	}


	/**
	 * @param friend_Count the friend_Count to set
	 */
	public void setFriend_Count(int friend_Count) {
		Friend_Count = friend_Count;
	}


	/**
	 * @return the join_Date
	 */
	public Date getJoin_Date() {
		return Join_Date;
	}


	/**
	 * @param join_Date the join_Date to set
	 */
	public void setJoin_Date(Date join_Date) {
		Join_Date = join_Date;
	}


	/**
	 * @return the user_Location
	 */
	public String getUser_Location() {
		return User_Location;
	}


	/**
	 * @param user_Location the user_Location to set
	 */
	public void setUser_Location(String user_Location) {
		User_Location = user_Location;
	}


	/**
	 * @return the status_Count
	 */
	public byte getStatus_Count() {
		return Status_Count;
	}


	/**
	 * @param status_Count the status_Count to set
	 */
	public void setStatus_Count(byte status_Count) {
		Status_Count = status_Count;
	}


	/**
	 * @return the favourite_Count
	 */
	public int getFavourite_Count() {
		return Favourite_Count;
	}


	/**
	 * @param favourite_Count the favourite_Count to set
	 */
	public void setFavourite_Count(int favourite_Count) {
		Favourite_Count = favourite_Count;
	}
	
}