package smarthealthgui;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Post implements Comparable{
	
private String username;
private Timestamp timeCreated;
private int forumID;
private String text;
private String photo;
private String link;
private String video;
private ArrayList<Comment> commList;
private ArrayList<Rating> ratList;
public Post(String username, Timestamp timeCreated, int forumID, String text, String photo, String link, String video,
		ArrayList<Comment> commList, ArrayList<Rating> ratList) {
	super();
	this.username = username;
	this.timeCreated = timeCreated;
	this.forumID = forumID;
	this.text = text;
	this.photo = photo;
	this.link = link;
	this.video = video;
	this.commList = commList;
	this.ratList = ratList;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public Timestamp getTimeCreated() {
	return timeCreated;
}
public void setTimeCreated(Timestamp timeCreated) {
	this.timeCreated = timeCreated;
}
public int getForumID() {
	return forumID;
}
public void setForumID(int forumID) {
	this.forumID = forumID;
}
public String getText() {
	return text;
}
public void setText(String text) {
	this.text = text;
}
public String getPhoto() {
	return photo;
}
public void setPhoto(String photo) {
	this.photo = photo;
}
public String getLink() {
	return link;
}
public void setLink(String link) {
	this.link = link;
}
public String getVideo() {
	return video;
}
public void setVideo(String video) {
	this.video = video;
}
public ArrayList<Comment> getCommList() {
	return commList;
}
public void setCommList(ArrayList<Comment> commList) {
	this.commList = commList;
}
public ArrayList<Rating> getRatList() {
	return ratList;
}
public void setRatList(ArrayList<Rating> ratList) {
	this.ratList = ratList;
}



public void giveComment()
{
	
}

public void giveRating()
{
	
}

    public int compareTo(Object o) {
       return ((Post)o).getTimeCreated().compareTo(this.getTimeCreated());
    }

}
