/************************************************
*
* Author: Greg Spies
* Assignment: Program 0
* Class: Data Communications
* Testing partner: Daniel Holt
*
************************************************/
package instayak.serialization.test;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.junit.Test;

import instayak.serialization.InstaYakChallenge;
import instayak.serialization.InstaYakException;
import instayak.serialization.InstaYakID;
import instayak.serialization.InstaYakMessage;
import instayak.serialization.MessageInput;
import instayak.serialization.MessageOutput;

public class InstaYakChallengeTest {

    private static String nonce;
    private static byte[] encoding; 
    
    @Test(expected = InstaYakException.class)
    public void testChallengeConstructorBadInput() throws InstaYakException {
       InstaYakChallenge test = new InstaYakChallenge("asdflja34 343qa rfer");
    }

    @Test
    public void testChallengeConstructorValidInput() throws InstaYakException {
       InstaYakChallenge test = new InstaYakChallenge("797897");
       assertEquals("797897", test.getNonce());
    }
    
    @Test
    public void testChallengeEquals() throws InstaYakException {
       InstaYakChallenge clng1 = new InstaYakChallenge("97");
       InstaYakChallenge clng2 = new InstaYakChallenge("97");
       assertTrue(clng1.equals(clng2));
    }
    
    @Test(expected = InstaYakException.class)
    public void testEncodeBadInput() throws IOException, InstaYakException {
        encoding = "CLNG bob\r\n".getBytes("ISO8859-1");
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        MessageOutput mout = new MessageOutput(bout);
        new InstaYakChallenge("bob").encode(mout);
    }
    
    @Test
    public void testEncode() throws IOException, InstaYakException {
        encoding = "CLNG 75783\r\n".getBytes("ISO8859-1");
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        MessageOutput mout = new MessageOutput(bout);
        new InstaYakChallenge("75783").encode(mout);
        assertArrayEquals(encoding, bout.toByteArray());
    }
    
    @Test
    public void testDecodeMessageInput() throws InstaYakException, IOException {
        encoding = "ID bob\r\n".getBytes("ISO8859-1");
        MessageInput min = new MessageInput(new ByteArrayInputStream(encoding));
        InstaYakID idMsg = (InstaYakID) InstaYakMessage.decode(min);
        assertEquals("bob", idMsg.getID());
    }
}

