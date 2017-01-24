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

import instayak.serialization.InstaYakException;
import instayak.serialization.InstaYakID;
import instayak.serialization.InstaYakMessage;
import instayak.serialization.InstaYakVersion;
import instayak.serialization.MessageInput;
import instayak.serialization.MessageOutput;

public class InstaYakVersionTest {

    private static byte[] encoding;
    
    @Test
    public void testEncode() throws IOException, InstaYakException {
        encoding = "INSTAYAK 1.0\r\n".getBytes("ISO8859-1");
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        MessageOutput mout = new MessageOutput(bout);
        new InstaYakVersion().encode(mout);
        assertArrayEquals(encoding, bout.toByteArray());
    }

}
