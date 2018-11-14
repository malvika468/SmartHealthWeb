package smarthealthgui;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

public class Comment implements Comparable{

	private String unamePost;
	private Timestamp timePost;
	private String unameComment;
	private Timestamp timeComment;
	private String text;
	private String photo;
	private String link;
	private String video;
	public Comment(String unamePost, Timestamp timePost, String unameComment, Timestamp timeComment, String text,
			String photo, String link, String video) {
		super();
		this.unamePost = unamePost;
		this.timePost = timePost;
		this.unameComment = unameComment;
		this.timeComment = timeComment;
		this.text = text;
		this.photo = photo;
		this.link = link;
		this.video = video;
	}
        public static ArrayList<Comment> getComment(String postusername,Timestamp t) throws Exception{
            ArrayList<Comment> comment=new ArrayList<Comment>();
            String str="Select * from comment where post_username='"+postusername+"' and post_timecreated='"+t+"';";
            ResultSet res=(new SQLcom()).retrieve(str);
            while(res.next()){
                Comment r=new Comment(postusername,res.getTimestamp(2),res.getString(3),res.getTimestamp(4),res.getString(5),res.getString(6),res.getString(7),res.getString(8));
                comment.add(r);
            }
            return comment;
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
	public String getUnameComment() {
		return unameComment;
	}
	public void setUnameComment(String unameComment) {
		this.unameComment = unameComment;
	}
	public Timestamp getTimeComment() {
		return timeComment;
	}
	public void setTimeComment(Timestamp timeComment) {
		this.timeComment = timeComment;
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
	public int compareTo(Object o) {
       return ((Comment)o).getTimeComment().compareTo(this.getTimeComment());
    }
	
	
}
