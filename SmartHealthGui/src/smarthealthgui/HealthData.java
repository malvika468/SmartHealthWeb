package smarthealthgui;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HealthData {
    private String run,calories_burned,bloodPressure;

    public String getRun() {
        return run;
    }

    public void setRun(String run) {
        this.run = run;
    }

    public String getCalories_burned() {
        return calories_burned;
    }

    public void setCalories_burned(String calories_burned) {
        this.calories_burned = calories_burned;
    }

    public String getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure;
    }
    static SQLcom comm=new SQLcom();
    public Map getHealthData()
    {
        Map map=new HashMap();
        map.put("run",run);
        map.put("calories_burned",calories_burned);
        map.put("bloodPressure",bloodPressure);
        return map;
    }
    public void setHealthData(String run,String calories_burned,String bloodPressure){
        this.run=run;
        this.calories_burned=calories_burned;
        this.bloodPressure=bloodPressure;
        
    }
    
    public HealthData(String run,String calories_burned,String bloodPressure)
    {
        setHealthData(run,calories_burned,bloodPressure);
    }
    
    public int saveHealthData(String uname) throws SQLException     //saves the data in db
   {    
       ResultSet rs=comm.retrieve("select propertyid,name from property where name='run' or name='blood-pressure' or name= 'calories-burned';");
       int pr1=-1,pr2=-1,pr3=-1;
       while(rs.next())
       {
           if(rs.getString(2).equalsIgnoreCase("run"))
               pr1=rs.getInt(1);
           else
               if(rs.getString(2).equalsIgnoreCase("calories-burned"))
               pr2=rs.getInt(1);
           else
                   if(rs.getString(2).equalsIgnoreCase("blood-pressure"))
               pr3=rs.getInt(1);
       }
       
       if(pr1==-1)
           comm.update("Insert into property (name,description)values('run','user run distance measure');");
       if(pr2==-1)
         comm.update("Insert into property (name,description)values('calories-burned','user calories burned measure');");
       if(pr3==-1)
        comm.update("Insert into property (name,description)values('blood-pressure','user blood pressure measure');");
       
       rs=comm.retrieve("select propertyid,name from property where name ='run' or name='blood-pressure' or name= 'calories-burned';");
       while(rs.next())
       {
           if(rs.getString(2).equalsIgnoreCase("run"))
               pr1=rs.getInt(1);
           else
               if(rs.getString(2).equalsIgnoreCase("calories-burned"))
               pr2=rs.getInt(1);
           else
                   if(rs.getString(2).equalsIgnoreCase("blood-pressure"))
               pr3=rs.getInt(1);
       }
       
       java.util.Date date = new java.util.Date();
                        long time = date.getTime();
                        String dateof=new java.sql.Timestamp(time).toString();
       int r=comm.update("Insert into datum (username,propertyid,value,whensaved) values('"+
               uname+"',"+pr1+",'"+run+"','"+dateof+"');");
       if(r<=0)return r;
       r=comm.update("Insert into datum (username,propertyid,value,whensaved) values('"+
               uname+"',"+pr2+",'"+calories_burned+"','"+dateof+"');");
       if(r<=0)return r;
       r=comm.update("Insert into datum (username,propertyid,value,whensaved) values('"+
               uname+"',"+pr3+",'"+bloodPressure+"','"+dateof+"');");
       if(r<=0)return r;
       return 1;
   }
    
    public ArrayList<HashMap> getFriendsHealthData(String uname) throws SQLException{
        ArrayList<HashMap> r=new ArrayList<HashMap>();
        String fr="Select username1 from friends where username2='"+uname+"';",fq;//to find friends
        ResultSet res=comm.retrieve(fr),res1;
        HashMap map;
        
        while(res.next())   //for each friends of his returning that friend's health data
        {
         map=new HashMap();
         String funame=res.getString(1);
         map.put("Name:",funame);
        
         fq="select name , value from property natural join datum where username='"+funame+"';";
         res1=comm.retrieve(fq);String run="not-specified",cb="not-specified",bp="not-specified";
         while(res1.next()) //over one  particular friend
         {
             if(res1.getString(1).equalsIgnoreCase("run"))
                 run=res1.getString(2);
             else
               if(res1.getString(1).equalsIgnoreCase("calories-burned"))
                 cb=res1.getString(2);
             else
                   if(res1.getString(1).equalsIgnoreCase("blood-pressure"))
                 bp=res1.getString(2);
         }
         map.put("run", run);
         map.put("calories-burned", cb);
         map.put("blood-pressure", bp);
         r.add(map);    //adding friends record in arraylist
        }
        return r;
    }
}
