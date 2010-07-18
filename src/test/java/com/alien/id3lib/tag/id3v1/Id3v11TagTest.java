package com.alien.id3lib.tag.id3v1;

import static org.junit.Assert.*;

import org.junit.Test;


public class Id3v11TagTest {

    @Test
    public void testId3v11Tag() {
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
        assertEquals("Track", Integer.valueOf(5), tag.getTrack());
        assertEquals("Genre", "", tag.getGenre());
    }
}
