package com.alien.id3lib.tag.id3v1;

/**
 * ID3 v1.1 Tag
 * 
 * @author adrianshum
 *
 */
public class Id3v11Tag extends Id3v1Tag {
    private Integer track = 0;
    
    public Id3v11Tag(byte[] bytes, String charsetName) {
        super(bytes, charsetName);
        this.track = (int)bytes[126];
    }

    public Id3v11Tag(byte[] bytes) {
        this(bytes, null);
    }

    @Override
    public Integer getTrack() {
        return this.track;
    }

    @Override
    public void setTrack(Integer track) {
        this.track = track;
    }
}
