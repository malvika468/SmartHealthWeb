package smarthealthgui;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

public class Forum {

	private int forumId;
	private String topic;
	private String summary;
	private String url;
	private Timestamp created;
	private Timestamp closed;
	private String modCreated;
	private String modDeleted;
	private ArrayList<Post> postList;

  
	public Forum()
	{
		
	}
	
	public Forum(int forumId, String topic, String summary, String url, Timestamp created, Timestamp closed,
			String modCreated, String modDeleted, ArrayList<Post> postList) {
		super();
		this.forumId = forumId;
		this.topic = topic;
		this.summary = summary;
		this.url = url;
		this.created = created;
		this.closed = closed;
		this.modCreated = modCreated;
		this.modDeleted = modDeleted;
		this.postList = postList;
	}

        public static Forum getForum(int id) throws Exception{
            SQLcom comm=new SQLcom();
            String q="Select * from forum where forumid="+id+";";
            ResultSet res=comm.retrieve(q);
            Forum f=new Forum();f.setForumId(id);
            f.postList=new ArrayList<Post>();
            while(res.next()){
                f.setTopic(res.getString(2));f.setSummary(res.getString(4));
                f.setUrl(res.getString(3));
                f.setModCreated(res.getString(7));
                f.setModDeleted(res.getString(8));
                f.setCreated(res.getTimestamp(5));f.setClosed(res.getTimestamp(6));
            }
            String q1="Select * from post where forumid="+id+";";
            
            res=comm.retrieve(q1);
            while(res.next()){
                System.out.println("in hereee: "+res.getString(1));
                Post p=new Post(res.getString(1),res.getTimestamp(2),res.getInt(3),res.getString(4),res.getString(5),
                res.getString(6),res.getString(7),new ArrayList<Comment>(),new ArrayList<Rating>());
                ArrayList<Comment> commnt=Comment.getComment(res.getString(1),res.getTimestamp(2));
                ArrayList<Rating> rating=Rating.getRating(res.getString(1),res.getTimestamp(2));
                p.setCommList(commnt);p.setRatList(rating);
                f.postList.add(p);
            }
            return f;
        }

	public int getForumId() {
		return forumId;
	}



	public void setForumId(int forumId) {
		this.forumId = forumId;
	}



	public String getTopic() {
		return topic;
	}



	public void setTopic(String topic) {
		this.topic = topic;
	}



	public String getSummary() {
		return summary;
	}



	public void setSummary(String summary) {
		this.summary = summary;
	}



	public String getUrl() {
		return url;
	}



	public void setUrl(String url) {
		this.url = url;
	}



	public Timestamp getCreated() {
		return created;
	}



	public void setCreated(Timestamp created) {
		this.created = created;
	}



	public Timestamp getClosed() {
		return closed;
	}

        public static boolean checkRating(Post p,String uname,Timestamp time) throws Exception{
            String str="Select count(*) from rating where post_username='"+p.getUsername()+"' and post_timecreated='"+time+
                    "' and rater_username='"+uname+"';";
            ResultSet res=(new SQLcom()).retrieve(str);
            int c=0;
            while(res.next()){
                c=res.getInt(1);
            }
            if(c==0)
                return true;
            else
                return false;
        }
        
	public void setClosed(Timestamp closed) {
		this.closed = closed;
	}



	public String getModCreated() {
		return modCreated;
	}



	public void setModCreated(String modCreated) {
		this.modCreated = modCreated;
	}



	public String getModDeleted() {
		return modDeleted;
	}



	public void setModDeleted(String modDeleted) {
		this.modDeleted = modDeleted;
	}



	public ArrayList<Post> getPostList() {
		return postList;
	}



	public void setPostList(ArrayList<Post> postList) {
		this.postList = postList;
	}
 // business logic

	public int createPost(String unamelogin,Timestamp time, int fid, String text, String pic,String web,String video,Forum f)
	{
		 SQLcom comm=new SQLcom();
		 System.out.println("inside create post"+f.getTopic());
		 //adding the post object in forum objects arraylist corresponding to fid
		 
		 f.getPostList().add(new Post(unamelogin,time,fid,text,pic,web,video,new ArrayList<Comment>(),new ArrayList<Rating>()));
		 System.out.println(f.getPostList().get(0).getText());
		 String sq2="Insert into post values ('"+unamelogin+"','"+time+"',"+fid+",'"+text+"','"+pic+"','"+web+"','"+video+"');";
                int i= comm.update(sq2);	
                System.out.println("after update in create post i : "+i);
                return i;
	}
	
}
