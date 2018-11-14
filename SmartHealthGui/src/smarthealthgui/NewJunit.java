package smarthealthgui;

import static org.junit.Assert.*;

import org.junit.Test;

public class NewJunit {

	@Test
    public void  validation() {
          
		//ServletClass t=new ServletClass();
            // assert statements
            assertEquals("", false ,ServletClass.check("aaa","aaa"));
            
    }

}
