package smarthealthgui;

import java.sql.Timestamp;

public interface forumInterface {

	
	public int createForum(int forumId, String topic , String summary , String url,Timestamp created, Timestamp closed, String modDeleted , String modCreated)	;


public int deleteForum(int forumId, Timestamp closed, String modDeleted);

	
}
