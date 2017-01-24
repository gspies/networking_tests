/************************************************
*
* Author: Greg Spies
* Assignment: Program 0
* Class: Data Communications
* Testing partner: Daniel Holt
*
************************************************/

package instayak.serialization.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.junit.Test;

import instayak.serialization.InstaYakChallenge;
import instayak.serialization.InstaYakException;
import instayak.serialization.InstaYakID;
import instayak.serialization.InstaYakMessage;
import instayak.serialization.MessageInput;
import instayak.serialization.MessageOutput;

public class InstaYakIDTest {
	
	private static byte[] encoding;
	private static String ID;
	
	
	
	/*static {
		try {
			
		    encoding = "ID bob\r\n".getBytes("ISO8859-1");
		} catch (UnsupportedEncodingException e) {
		
		    throw new RuntimeException("Unable to encode", e);
		}
	}*/
	
	
	
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
	    encoding = "ID bob\r\n".getBytes("ISO8859-1");
  	    MessageInput min = new MessageInput(new ByteArrayInputStream(encoding));
  		InstaYakID idMsg = (InstaYakID) InstaYakMessage.decode(min);
		assertEquals("bob", idMsg.getID());
	}
	
	@Test
    public void testDecodeMessageTripleInput() 
            throws InstaYakException, IOException {
        encoding = "ID bob\r\nID bob\r\nID bob\r\n".getBytes("ISO8859-1");
        MessageInput min = new MessageInput(new ByteArrayInputStream(encoding));
        InstaYakID idMsg = (InstaYakID) InstaYakMessage.decode(min);
        assertEquals("bob", idMsg.getID());
        InstaYakID idMsg2 = (InstaYakID) InstaYakMessage.decode(min);
        assertEquals("bob", idMsg2.getID());
        InstaYakID idMsg3 = (InstaYakID) InstaYakMessage.decode(min);
        assertEquals("bob", idMsg3.getID());
    }
	
	@Test (expected = NullPointerException.class)
    public void testDecodeMessageNullInput() 
            throws InstaYakException, IOException {
        encoding = "".getBytes("ISO8859-1");
        MessageInput min = new MessageInput(new ByteArrayInputStream(encoding));
        InstaYakID idMsg = (InstaYakID) InstaYakMessage.decode(min);
        assertEquals("bob", idMsg.getID());
    }
	
	@Test(expected = InstaYakException.class)
    public void testDecodeMessageBadInput() 
            throws InstaYakException, IOException {
        encoding = "ID 56bob\r65\n46dg\r\n".getBytes("ISO8859-1");
        MessageInput min = new MessageInput(new ByteArrayInputStream(encoding));
        InstaYakID idMsg = (InstaYakID) InstaYakMessage.decode(min);
    }
	
	/*
	 * 
	 * IOEXCEPTION TEST
	 * 
	 */
	
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
	
	@Test
    public void testIDEquals() throws InstaYakException {
       InstaYakID id1 = new InstaYakID("abc97");
       InstaYakID id2 = new InstaYakID("abc97");
       assertTrue(id1.equals(id2));
    }
}
