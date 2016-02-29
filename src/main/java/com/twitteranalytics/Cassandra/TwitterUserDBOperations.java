package com.twitteranalytics.Cassandra;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Metadata;
import com.datastax.driver.core.Session;
import com.twitteranalytics.ModelClasses.TwitterUser;

public class TwitterUserDBOperations {

	public Cluster getConnection(Cluster cluster) {
		System.out.println("Connecting to Cassandra Cluster....");
		cluster = Cluster.builder().addContactPoint("127.0.0.1").build();
		Metadata metadata = cluster.getMetadata();
		System.out.println("Connected to cluster: "+metadata.getClusterName());
		return cluster;
		}

	public Session getSession(Cluster cluster, Session session){
		session=cluster.connect();
		return session;
	}
	
	public void removeKeySpace(String KeySpaceName, Session session){
		session.execute("DROP KEYSPACE IF EXISTS "+KeySpaceName+";");
		System.out.println("KeySpace Exist with Name : "+KeySpaceName);
		System.out.println("KeySpace Dropped with Name : "+KeySpaceName);
	}
	
	public void removeTable(Session session){
		session.execute("DROP Table IF EXISTS twitter.User;");
		System.out.println("KeySpace Exist with Name : User");
		System.out.println("KeySpace Dropped with Name : User");
	}
	
	public void close(Cluster cluster,Session session){
		System.out.println("Closing the connection : "+cluster.getClusterName());
		session.close();
		cluster.close();
	}

	public void createSchema(Session session, Cluster cluster){
		
		try{
			session.execute("CREATE KEYSPACE IF NOT EXISTS twitter WITH replication = {'class':'SimpleStrategy', 'replication_factor':1} AND DURABLE_WRITES = true;");
			System.out.println("KeySpace created with Name : twitter");
			session=cluster.connect("twitter");
		}catch(Exception e){
			e.printStackTrace();
		}
		
		try{
			session.execute("CREATE TABLE IF NOT EXISTS twitter.User("+
				"UserID long PRIMARY KEY,"+
				"Name text, "+
				"ScreenName text, "+
				"ProfileImageURL text, "+
				"Follower_Count int, "+
				"Friend_Count int, "+
				"Join_Date date, "+
				"User_Location text, "+
				"Status_Count byte, "+
				"Favourite_Count int, "+
				");");
			System.out.println("Table created with Name : User");
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void addUserDetails(Session session, TwitterUser user){
		try{
			session.execute("INSERT INTO twitter.User (UserID, Name, ScreenName, ProfileImageURL,"
				+" Follower_Count, Friend_Count, Join_date, User_Location, Status_Count, Favourite_Count)"+
				
				" VALUES ("+user.getUserId()+", "+"'"+user.getUsername()+"', "+
				"'"+user.getScreenname()+"', "+"'"+user.getProfileImage()+"', "+
				user.getFollower_Count()+", "+user.getFriend_Count()+", "+
				user.getJoin_Date()+", "+"'"+user.getUser_Location()+"', "+
				user.getStatus_Count()+", "+user.getFavourite_Count()+")"+
				");");
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
}
