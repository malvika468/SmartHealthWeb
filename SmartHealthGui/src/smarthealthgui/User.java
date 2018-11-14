package smarthealthgui;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;



public class User  implements Friendship {

	 private int karma;/*variable to store score of a user.The more the user will contribute quality content 
     to the system, the more will be his/her karma. */
      Date d_of_join;
      private HealthData healthData;  

    public HealthData getHealthData() {
        return healthData;
    }
      SQLcom comm=new SQLcom();
//variable to store date of joining of the user

public User(String uname) throws SQLException   //this returns the stored object in the database(This iis called when we know such user does exist
     {  
                             //System.out.println("Inside constructor top"+uname);     
                ResultSet res=comm.retrieve("Select karma,datecreated from Enduser where username='"+uname+"';");
                            // System.out.println("After query execution : karma "+karma);   
                res.next();
                karma=res.getInt("karma");
                                      //System.out.println("After query execution : now karma "+karma);     
                d_of_join=res.getDate("datecreated");
                                      //System.out.println("After query execution : now doc "+d_of_join);/System.out.println("Inside constructor : "+karma+d_of_join);     
                res=comm.retrieve("Select name,value from datum natural join property where username='"+uname+"';");//111to extract most recent data
                String run = null,cal = null,bp = null;
                while(res.next())
                {
                    if(res.getString(1).equalsIgnoreCase("run"))
                    run=res.getString(2);
                else
                    if(res.getString(1).equalsIgnoreCase("calories-burned"))
                    cal=res.getString(2);
                else
                     if(res.getString(1).equalsIgnoreCase("blood-pressure"))
                     bp=res.getString(2);
                 
                }
         healthData=new HealthData(run,cal,bp);
                     //        System.out.println("Inside constructor:healthdata: "+healthData);     

     }
/*public User(String uname,String primary_emailID,String secondary_emailID,String fname,String lname,String pass,String postaddr,String abtME,String url[])
{
super(uname,primary_emailID,secondary_emailID,fname,lname,pass,postaddr,abtME,url);
this.set_user_type("new");
d_of_join=new java.sql.Date(System.currentTimeMillis());
karma=0;
}
*/
   public  User() {

 }

public int get_karma(){
return this.karma;
}
public void set_karma(int k)
{
karma=k;
}

public int sendRequest (String u,String f,Timestamp time)
{

	 String res="";
     String res2="";
     String sql3="SELECT description from usertype  inner join user on user.usertypeid = usertype.usertypeid  where user.username='"+f+"'";
     ResultSet rs=comm.retrieve(sql3);
     ResultSet rs2=null;
    
int count=0;
int i=-1;
String sql1="Select * from friendship where requester_username='"+u+"' and requested_username='"+f+"'";
try
{
	
	        ResultSet res1=comm.retrieve(sql1);
            while(res1.next())
             count++;
            
            while(rs.next()){
   			 res=rs.getString(1);
   			 }
		    String sql4="SELECT username from enduser";
		    rs2=comm.retrieve(sql4);
		    while(rs2.next()){
		  	res2=rs2.getString(1); 
		  	if(!(f.equalsIgnoreCase(res2)))
		  	{ 
		  	System.out.println("inside invalid user"+i);//// set i for invalid user
		  	break;
		  	}
		  	else if(f.equalsIgnoreCase(res2))
		  	{
		  	i=1;
		  	}
		  	
		  	}
			 
		    
	     if(res.equalsIgnoreCase("mod") || res.equalsIgnoreCase("admin"))
		    {
		      //display error 
			   i=3;
			   System.out.println("inside mod validation");   
		    }   
		   else  if(u.equalsIgnoreCase(f)==true)
		   {
		  	i=4;
		  	System.out.println("inside equals");
		   }	
		   else if(f.length()==0)
		   {
			 i=5;  
			 System.out.println("inside null");
		   }
		   else if(count>0)
		   {
			i=6;   
		   }
     else if(i==2)
     {
    	return i; 
     }
     else	   	 
   {
	String sql = "INSERT INTO friendship " +
    "VALUES ('"+u+"','"+f+"','"+time+"',NULL,NULL,NULL,NULL);";	
   i=comm.update(sql);
   System.out.println("inside sendreuest: "+i);
}


}		   
catch(Exception e){
    System.out.println(e);
    
}
return i;
}


public ResultSet seeRequest(String u)
{
try
{
String sql="select Requester_username from friendship where Requested_username='"+u+"' AND  (WhenConfirmed is NULL OR WhenRejected is NOT NULL) AND (WhenConfirmed is NOT NULL OR WhenRejected is NULL) AND WhenWithdrawn is NULL  ;";
ResultSet rs=comm.retrieve(sql);
/*while(rs.next()){
System.out.println(rs.getString(1));
}*/ return rs;
} 
catch(Exception e)
{
e.printStackTrace();  
}

return null;
}
public int confirmRequest(String uname,String f,Timestamp time)
{

String sql1 = "UPDATE friendship " +
"SET  WhenConfirmed='"+time+"' WHERE Requester_Username='"+f+"' AND  Requested_username='"+uname+"';"; 
String sql2 = "INSERT into friends " +
"VALUES ('"+uname+"','"+f+"')";	

String sql3 = "INSERT into friends " +
"VALUES ('"+f+"','"+uname+"')";	

int i=comm.update(sql1);
System.out.println("inside confirm1 :"+i);
i=comm.update(sql3);
i=comm.update(sql2);
System.out.println("inside confirm1 :"+i);
return i;
}


public int rejectRequest(String uname,String f, Timestamp time)
{
String sql = "UPDATE friendship " +
 "SET  WhenRejected='"+time+"' WHERE Requester_Username='"+f+"' AND  Requested_username='"+uname+"';"; 
int i=comm.update(sql);	
System.out.println("inside reject :"+i);
return i;
}
public int unfriend(String uname,String f,Timestamp time)
{

 int j=-1;
if(f.length()==0)
{
j=0;	
}
	
else{	
String sql1="UPDATE friendship " +
"SET  WhenUnfriended='"+time+"' WHERE Requester_Username='"+f+"' AND  Requested_username='"+uname+"';"; 
String sql2="UPDATE friendship " +
"SET  WhenUnfriended='"+time+"' WHERE Requester_Username='"+uname+"' AND  Requested_username='"+f+"';"; 

String sql3 = "DELETE FROM friends " +
"WHERE username1='"+uname+"' and username2='"+f+"';"; 

String sql4 = "DELETE FROM friends " +
"WHERE username1='"+f+"' and username2='"+uname+"';"; 


 j=comm.update(sql1);
System.out.println("First result: "+j);
 j=comm.update(sql2);
System.out.println("Second:"+j);
 j=comm.update(sql3);
 j=comm.update(sql4);
System.out.println("Third:"+j);

System.out.println("here it is removed");
}

return j;
}



public int seeFriends1(String uname)
{
	try
	   {
	    String  sql2="select username1 from friends where username2='"+uname+"';";  
	    
	    ResultSet rs=comm.retrieve(sql2);
	   
	    System.out.println(rs.getString(1));
	    
	    	    while(rs.next()){
	    System.out.println(rs.getString(1));
	    }
	}
	    catch(Exception e)
	    {
	    System.out.println(e.getMessage());	
	    } 
	   return 1;
 
}

@Override
public int cancelRequest(String uname, String friendname, Timestamp time) {
	// TODO Auto-generated method stub
	
	String sql1 = "UPDATE friendship " +
			"SET  WhenWithdrawn='"+time+"' WHERE Requester_Username='"+uname+"' AND  Requested_username='"+friendname+"';"; 
	 int j=comm.update(sql1);
	 
	return j;
}








	
}

