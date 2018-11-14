package smarthealthgui;



import java.sql.ResultSet;
import java.sql.Timestamp;

public interface Friendship {

	public int sendRequest(String uname,String friendname,Timestamp time);
	public ResultSet seeRequest(String uname);
	public int confirmRequest(String uname,String friendname,Timestamp time);
	public int unfriend(String uname,String friendname,Timestamp time);
	public int rejectRequest(String uname,String friendname, Timestamp time);
	public int seeFriends1(String uname);
	public int cancelRequest(String uname,String friendname, Timestamp time);
	
}
