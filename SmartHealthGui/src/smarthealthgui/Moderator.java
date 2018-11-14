package smarthealthgui;


import java.sql.*;
import java.util.Date;

public class Moderator implements forumInterface
{
   
	long contact;   //variable to store emergency contact no of the Moderator
    String qualification;
       
    
    
    
    public Moderator(long contact, String qualification, SQLcom comm) {
		super();
		this.contact = contact;
		this.qualification = qualification;
		this.comm = comm;
	}
	SQLcom comm=new SQLcom();
    
    
    public long getContact() {
		return contact;
	}
	public void setContact(long contact) {
		this.contact = contact;
	}
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public SQLcom getComm() {
		return comm;
	}
	public void setComm(SQLcom comm) {
		this.comm = comm;
	}
	public Moderator() {
		
	}
    public int createForum(int forumId, String topic , String summary , String url,Timestamp created, Timestamp closed, String modDeleted , String modCreated)	
    {
    	
    	
    	System.out.println("inside mod"+url+" "+modDeleted);
    	String sql5="Insert into Forum values('"+forumId+"','"+topic+"','"+url+"','"+summary+"','"+created+"', NULL, '"+modCreated+"',"+modDeleted+")";
        
    	 int j=comm.update(sql5);
    	 return j;
    }
    public int deleteForum(int forumId, Timestamp closed, String modDeleted)
    {
    	 String res11=null;
    	 int i=-1;
    	 String sq="Select whenClosed From Forum Where ForumID='"+forumId+"';";
 	    
    	 try
    	 {
    	 ResultSet rs2=comm.retrieve(sq);
 	     while(rs2.next()){
         res11=rs2.getString(1);
 	     }
         if(res11!=null)
         System.out.println("Already Closed");
         else
         {
        
         String sql5="UPDATE Forum " +
                 "SET  whenClosed='"+closed+"'  WHERE  ForumID='"+forumId+"';";
         String sql6="UPDATE Forum " +
                 "SET  DeletedByModerator_Username='"+modDeleted+"'  WHERE  ForumID='"+forumId+"';";
         i= comm.update(sql5);
         i= comm.update(sql6); 	 
         }
        
    	 }
    	 
    	 catch(Exception e)
    	 {
          e.printStackTrace();		 
    	 }
    	 return i;
    }
  
    /*public Moderator(String uname,String primary_emailID,String secondary_emailID,String fname,String lname,String pass,String postaddr,String abtME,String url[])
    {
     super(uname,primary_emailID,secondary_emailID,fname,lname,pass,postaddr,abtME,url);
     Scanner sc=new Scanner(System.in);
     System.out.print("\nEnter emeregency contact : ");
     e_contact=sc.nextLong();
     this.set_user_type("mod");
     qual=new ArrayList<String>();
     System.out.print("\nEnter no. of qualifications : ");
     int n=sc.nextInt(),i=0;
     while(i<n)
     {
         System.out.print("\nEnter "+(i+1)+" qualifications : ");
         qual.add(sc.next());
         i++;
     }
     */
    
   
  /*static public void changeQual(String uname){
        boolean ans;String a,b;int id;
        Scanner sc=new Scanner(System.in);
        
        //fetching all the qualifications of moderator
        
        String str="Select QualificationId,description from user natural join Qualificaton where username=? ;";
            try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/smarthealthdb", "root", "root");
        
            PreparedStatement pstmt= conn.prepareStatement(str);
            pstmt.setString(1,uname);
            ResultSet res=pstmt.executeQuery();
            System.out.println("\nQualID   Description");
            if(res.next())
            {   
                Long qid=res.getLong(1);
                String desc=res.getString(2);
                System.out.println(qid+" "+desc);
            }   
            
            while(true)                                             //this allows Moderator  to remove as many existing qualifications
            {     if(qual==null)break;
            System.out.print("\nDo you want to remove existing qualification??Y/N");
            a=sc.next();
            if((ans=a.equals("Y")?true:false))
            {
            System.out.print("\nEnter qualification Id to be removed :");
            id=sc.nextInt();
            if(qual.contains(b))
               qual.remove(b);
            else
               System.out.print("No such qualification exist .. ");     //if no such qualification already exist
            }
         else
           break;
        }    
       
      while(true)                                               //this allows Moderator  to add as many new qualifications
        {     
            System.out.print("\nDo you want to add new qualification??Y/N");
            a=sc.next();
            if((ans=a.equals("Y")?true:false))
            {
            System.out.print("\nEnter qualification to be added :");
            b=sc.next();
            if(qual.contains(b))                        //if already that qualificaton exists
                System.out.print("This qualification already exists .. ");
            else
               qual.add(b);
              
            }
            else
            break;
           }  
        
}
     
     catch(Exception e)
     {
         System.out.println(e);
     }
}*/
    
    
  
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}