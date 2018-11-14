package smarthealthgui;


import java.sql.*;
public abstract class Person{
    String fname,lname;
    private String uname,primary_emailID,secondary_emailID,pass,abtME,postaddr;
    private String url[];        //urls of profile photos
    private String utype;       //user type ["mod","admin","new","middle","old"]
    private boolean quit;       //if quit=true user is active else not
    
    public Person(String uname,String primary_emailID,String secondary_emailID,String fname,String lname,String pass,String postaddr,String abtME,String url[])
    {//constructor
     //it will initialize instance variables of Person class
       this.uname=uname;
       this.primary_emailID=primary_emailID;
       this.secondary_emailID=secondary_emailID;
       this.fname=fname;
       this.lname=lname;
       this.pass=pass;
       this.abtME=abtME;
       this.postaddr=postaddr;
       this.url=url;
        quit=true; //url,utype
        
    }
    
    static public void remove1(String uname)
    {
    	SQLcom comm=new SQLcom(); 	
        String str="Update user set status=0 where username='"+uname+"';";
        comm.update(str);
    }
    
        //getter and setter methods are given below
    public String get_utype()
    {
        return this.utype;
    }
    public void set_user_type(String u)
    {
        this.utype=u;
    }
    public boolean getQuit()
    {
        return this.quit;
    }
    public void set_uname(String u)
    {
        uname=u;
    }
    public void set_primary_emailID(String u)
    {
        this.primary_emailID=u;
    }
    public void set_secondary_emailID(String u)
    {
        this.secondary_emailID=u;
    }
    public String get_pass()
    {
        return this.pass;
    }
    public String get_uname()
    {
        return this.uname;
    }
    public void setPass(String pass)
    {
        this.pass=pass;
    }
    public void set_postaddr(String addr)
    {
        postaddr=addr;
    }
    public void set_abtME(String p)
    {
        abtME=p;
    }
    public void set_urls(String p[])
    {
        url=p;
    }
}
