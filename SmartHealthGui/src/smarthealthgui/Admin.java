package smarthealthgui;


public class Admin
{
    long  e_contact; //variable to store emergency contact no of the Admin

    
    
	public Admin(long e_contact) {
		super();
		this.e_contact = e_contact;
	}

	public long getE_contact() {
		return e_contact;
	}

	public void setE_contact(long e_contact) {
		this.e_contact = e_contact;
	}
    
}
