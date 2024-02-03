package arch.sm213.machine.student;


import machine.AbstractMainMemory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MainMemoryTest {

    @Test
    public void testIsAccessAligned() {
        MainMemory m = new MainMemory(0);
        assertTrue(m.isAccessAligned(0, 4));
        assertTrue((m.isAccessAligned(4,4)));
        assertTrue(m.isAccessAligned(8,4));
        assertFalse(m.isAccessAligned(1,4));
        assertFalse(m.isAccessAligned(1,2));
        assertTrue(m.isAccessAligned(2,2));
    }

    @Test
    public void testBytesToInteger() {
        MainMemory m = new MainMemory(0);
        assertEquals(0, m.bytesToInteger((byte)0, (byte)0, (byte)0, (byte)0));
        assertEquals(1, m.bytesToInteger((byte)0, (byte)0, (byte)0, (byte)1));
        assertEquals(255, m.bytesToInteger((byte)0, (byte)0, (byte)0, (byte)0xff));
        assertEquals(256, m.bytesToInteger((byte)0, (byte)0, (byte)0x01, (byte)0x00));
        assertEquals(-1, m.bytesToInteger((byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff));
        assertEquals(Integer.MAX_VALUE, m.bytesToInteger((byte)0x7f, (byte)0xff, (byte)0xff, (byte)0xff));
        assertEquals(Integer.MIN_VALUE, m.bytesToInteger((byte)0x80, (byte)0x00, (byte)0x00, (byte)0x00));

    }

    @Test
    public void testIntegerToBytes() {
        MainMemory m = new MainMemory(0);
        byte[] a = new byte[4];

        bytesEqual(0,0,0,0, m.integerToBytes(0));
        bytesEqual(0,0,0,1, m.integerToBytes(1));
        bytesEqual(0,0,0,0xff, m.integerToBytes(255));
        bytesEqual(0x80, 0,0,0, m.integerToBytes(Integer.MIN_VALUE));
        bytesEqual(0xff,0xff,0xff,0xff, m.integerToBytes(-1));
        bytesEqual(0x7f,0xff,0xff,0xff, m.integerToBytes(Integer.MAX_VALUE));

    }

    private void bytesEqual(int a, int b, int c, int d, byte[] bytes) {
        assertEquals((byte)a,bytes[0]);
        assertEquals((byte)b,bytes[1]);
        assertEquals((byte)c,bytes[2]);
        assertEquals((byte)d,bytes[3]);
    }

    @Test
    public void testGet() throws AbstractMainMemory.InvalidAddressException {
        MainMemory m = new MainMemory(128);
        byte[] a = {1,2,3,4};
        m.set(8,a);
        byte[] b = m.get(8,4);

        assertEquals(a.length, b.length);
        for(int i=0; i < a.length; i++) {
            assertEquals(a[i],b[i]);
        }
        //assertEquals(b, a);

        try{
            m.get(127, 1);
            for(int i=0; i < a.length; i++) {
                assertEquals(i+1,b[i]);
            }
        } catch(Exception e ) {
            fail();
        }

        try{
            m.get(-1, 1);
            fail("address lesser than 0");
        } catch(Exception e ) {}

        try{
            m.get(130, 1);
            fail("address larger than memory length");
        } catch(Exception e ) {}

    }

    @Test
    public void testSet() {
        MainMemory m = new MainMemory(128);
        byte[] a = {1,2,3,4};

        try{
            m.set(4,a);
        } catch (Exception e) {
            fail();
        }

        try{
            m.set(124, a);
        } catch (Exception e) {
            fail();
        }

        try{
            m.set(-1, a);
            fail("address lesser than 0");
        } catch (Exception e) {}

        try{
            m.set(133, a);
            fail("address+value.length larger than memory length");
        } catch (Exception e) {}
    }

    @Test
    public void testLength() {
        MainMemory m = new MainMemory(128);
        assertEquals(128, m.length());
    }

}
