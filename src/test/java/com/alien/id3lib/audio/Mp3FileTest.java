package com.alien.id3lib.audio;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import org.junit.Ignore;
import org.junit.Test;

public class Mp3FileTest {
    @Test
    public void testCreateMp3File() throws FileNotFoundException, UnsupportedEncodingException {
//        byte[] bytes = {(byte)0xB1, (byte)0xA1, (byte)0xAB, (byte)0x44, (byte)0xB1, (byte)0x6F, (byte)0xA4, (byte)0x77};
//        for(Charset c: Charset.availableCharsets().values()) {
//            for (byte b: "情非得已".getBytes(c.name())) {
//                System.out.printf("%x ", b);
//            }
//            if (c.canEncode()) {
//                System.out.println(" - " + c + " " + new String(bytes, c.name()));
//            }
//            System.out.println("試試");
//        }
        System.out.println("試 " + Charset.defaultCharset().name());

        Mp3File mp3File = new Mp3File("/data/mp3/test.mp3", "Big5");

        System.out.println("Title: " + mp3File.getId3v1Tag().getTitle());
    }

}
