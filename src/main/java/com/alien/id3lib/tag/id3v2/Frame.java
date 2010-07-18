package com.alien.id3lib.tag.id3v2;

/**
 * Base ID3 v2 Frame
 * @author adrianshum
 *
 */
public class Frame {
    private String id;
    private byte[] content;
    // some other flags

    public Frame(String id, byte[] content) {
        this.id = id;
        this.content = content.clone();
    }

    public Frame(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }



}
