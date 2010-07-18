package com.alien.id3lib.tag.id3v2;

import static org.junit.Assert.*;

import org.junit.Test;

public class UtilsTest {

    @Test
    public void testParseSyncSafeIntByteArrayIntInt() {
        byte bytes[] = {0,0,0x11, 0x27};
        assertEquals(2215, Utils.decodeSyncSafeInt(bytes));
    }


    @Test
    public void testParseWriteSyncSafeInt() {
        byte bytes[] = new byte[10];
        Utils.writeSyncSafeInt(2215, bytes, 2);
        assertArrayEquals(new byte[]{0,0,0,0,0x11,0x27,0,0,0,0}, bytes);

    }

}
