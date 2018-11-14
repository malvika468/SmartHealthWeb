package smarthealthgui;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

public class Rating {

	private String unamePost;
	private Timestamp timePost;
	private String unameRater;
	private int star;
	
	
	
	public Rating(String unamePost, Timestamp timePost, String unameRater, int star) {
		super();
		this.unamePost = unamePost;
		this.timePost = timePost;
		this.unameRater = unameRater;
		this.star = star;
	}
        
        public static ArrayList<Rating> getRating(String postusername,Timestamp t)throws Exception{
            
            ArrayList<Rating> rating=new ArrayList<Rating>();
            String str="Select * from rating where post_username='"+postusername+"' and post_timecreated='"+t+"';";
            ResultSet res=(new SQLcom()).retrieve(str);
            while(res.next()){
                Rating r=new Rating(postusername,res.getTimestamp(2),res.getString(3),res.getInt(4));
                rating.add(r);
            }
            return rating;
        }
	public String getUnamePost() {
		return unamePost;
	}
	public void setUnamePost(String unamePost) {
		this.unamePost = unamePost;
	}
	public Timestamp getTimePost() {
		return timePost;
	}
	public void setTimePost(Timestamp timePost) {
		this.timePost = timePost;
	}
	public String getUnameRater() {
		return unameRater;
	}
	public void setUnameRater(String unameRater) {
		this.unameRater = unameRater;
	}
	public int getStar() {
		return star;
	}
	public void setStar(int star) {
		this.star = star;
	}

	
	
	
}
