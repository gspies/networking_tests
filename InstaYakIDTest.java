package instayak.serialization.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.junit.Test;

import instayak.serialization.InstaYakException;
import instayak.serialization.InstaYakID;
import instayak.serialization.InstaYakMessage;
import instayak.serialization.MessageInput;
import instayak.serialization.MessageOutput;

public class InstaYakIDTest {
	
	private static final byte[] encoding;
	private static String ID;
	
	
	
	/*static {
		try {
			
		    encoding = "ID bob\r\n".getBytes("ISO8859-1");
		} catch (UnsupportedEncodingException e) {
		
		    throw new RuntimeException("Unable to encode", e);
		}
	}*/
	
	@BeforeClass
	public static void setup(){
	   
	}
	
	@Test
	public void testEncode() throws IOException, InstaYakException {
	    encoding = "ID bob\r\n".getBytes("ISO8859-1");
	    ByteArrayOutputStream bout = new ByteArrayOutputStream();
		MessageOutput mout = new MessageOutput(bout);
		new InstaYakID("bob").encode(mout);
		assertArrayEquals(encoding, bout.toByteArray());
	}

	@Test
	public void testDecodeMessageInput() throws InstaYakException, IOException {
	    
	    MessageInput min = new MessageInput(new ByteArrayInputStream(encoding));
		InstaYakID idMsg = (InstaYakID) InstaYakMessage.decode(min);
		assertEquals("bob", idMsg.getID());
	}
	
	@Test (expected = InstaYakException.class)
	public void testSetIDBadInput1() throws InstaYakException{
	    InstaYakID id = new InstaYakID("");
	}
	
	@Test 
    public void testSetIDGoodInput() throws InstaYakException{
        InstaYakID id = new InstaYakID("hello");
        assertEquals("hello", id.getID());
    }
	
	@Test 
    public void testEncodeBadID() throws InstaYakException{
        InstaYakID id = new InstaYakID("hello");
        assertEquals("hello", id.getID());
    }
}