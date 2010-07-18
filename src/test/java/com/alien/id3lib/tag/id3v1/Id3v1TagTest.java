package com.alien.id3lib.tag.id3v1;

import static org.junit.Assert.*;

import org.junit.Test;

public class Id3v1TagTest {


    @Test
    public void testId3v1Tag() {
        byte bytes[] = {'T','A','G',
                        'T','i','t','l','e',0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
                        'A','r','t','i','s','t',0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
                        'A','l','b','u','m',0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
                        '2','0','0','7',
                        'C','o','m','m','e','n','t',0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
                        5,
                        0};
        Id3v11Tag tag = new Id3v11Tag(bytes);

        assertEquals(128, bytes.length);
        assertEquals("Title", "Title", tag.getTitle());
        assertEquals("Artist", "Artist", tag.getArtist());
        assertEquals("Album", "Album", tag.getAlbum());
        assertEquals("Year", Integer.valueOf(2007), tag.getYear());
        assertEquals("Comment", "Comment", tag.getComment());
        assertNull("Track", tag.getTrack());
        assertEquals("Genre", "", tag.getGenre());
    }

    @Test
    public void testConvertBytesToString() throws Throwable {
        byte[] bytes = {'a', 'b', 'c', 0, 0, 0,};
        String str = null;
        str = Id3v1Tag.convertBytesToString(bytes, 0 , 2, null);
        assertEquals("ab", str);

    }

    @Test
    public void testConvertBytesToStringEmptyString() throws Throwable {
        byte[] bytes = {0, 0, 0, 0, 0, 0,};
        String str = null;
        str = Id3v1Tag.convertBytesToString(bytes, 0 , 2, null);
        assertEquals("", str);
    }

    @Test
    public void testConvertBytesToStringExceed() throws Throwable {
        byte[] bytes = {0, 0, 0, 0, 0, 0,};
        String str = null;
        str = Id3v1Tag.convertBytesToString(bytes, 0 , 2, null);
        assertEquals("", str);
    }

}
