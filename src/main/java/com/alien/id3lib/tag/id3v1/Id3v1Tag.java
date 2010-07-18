package com.alien.id3lib.tag.id3v1;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import com.alien.id3lib.tag.AudioTag;

/**
 * ID3 v1 Tag
 *
 * @author adrianshum
 *
 */
public class Id3v1Tag implements AudioTag{
    public static final int ID3V1_TAG_SIZE = 128;
    protected static final String SYSTEM_DEFAULT_CHARSET_NAME = Charset.defaultCharset().name();

    String title = null;
    String artist = null;
    String album = null;
    Integer year = null;
    String comment = null;
    String genre = null;



    public Id3v1Tag(byte[] bytes, String charsetName) {
        init(bytes, charsetName);
    }

    private void init(byte[]bytes, String charsetName) {
        try {
            if (charsetName == null || !Charset.isSupported(charsetName)) {
                charsetName = Charset.defaultCharset().name();
            }
            if (bytes.length >= ID3V1_TAG_SIZE) {   // ignore if not correct size
                this.title = convertBytesToString(bytes, 3, 30, charsetName) ;
                this.artist = convertBytesToString(bytes, 33, 30, charsetName);
                this.album = convertBytesToString(bytes, 63, 30, charsetName);
                this.year = Integer.valueOf(convertBytesToString(bytes, 93, 4, "ISO_8859-1"));
                this.comment = convertBytesToString(bytes, 97, 28, charsetName);
                this.genre = convertGenre(bytes[127]);
            }
        } catch (UnsupportedEncodingException e) {
            // should not exist because charset is checked already;
        }
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getArtist() {
        return artist;
    }
    public void setArtist(String artist) {
        this.artist = artist;
    }
    public String getAlbum() {
        return album;
    }
    public void setAlbum(String album) {
        this.album = album;
    }
    public Integer getYear() {
        return year;
    }
    public void setYear(Integer year) {
        this.year = year;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public Integer getTrack() {
        // intended left empty
        return 0;
    }
    public void setTrack(Integer track) {
        // intended left empty
    }
    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }

    public static String convertBytesToString(byte[] bytes, int offset, int length, String charsetName) throws UnsupportedEncodingException {
        // check bounds
        if (bytes == null) {
            throw new NullPointerException();
        }
        if (bytes.length < offset + length) {
            throw new IndexOutOfBoundsException();
        }
        int i = 0;
        for (i = offset; i < offset + length; ++i) {  // find the real length to convert
            if (bytes[i] == 0) {
                break;
            }
        }
        length = i - offset;

        if (charsetName == null) {
            charsetName = SYSTEM_DEFAULT_CHARSET_NAME;
        }
        return new String(bytes, offset, length, charsetName);

    }

    public static String convertGenre(byte genre) {
        return "";
    }
    public static byte convertGenre(String genre) {
        return 0;
    }
}
