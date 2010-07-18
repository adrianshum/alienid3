package com.alien.id3lib.tag.id3v2;

import java.util.LinkedHashMap;
import java.util.Map;

import com.alien.id3lib.audio.Mp3File;
import com.alien.id3lib.tag.AudioTag;

/**
 * Base class for all ID3 v2.* Tag
 * @author adrianshum
 *
 */
public class Id3v2Tag implements AudioTag {

    protected Map<String, Frame> frameMap = new LinkedHashMap<String, Frame>();
    protected Mp3File parentAudioFile = null;

    protected int majorVersion = 4;
    protected int minorVersion = 0;
    protected boolean isUnsynchronization = false;
    protected boolean isExtendedHeaderExist = false;
    protected int size = 0;
    protected int totalTagSize= 0;  // include header, body, footer etc.

    public Id3v2Tag(byte[] buffer) {
        init(buffer);
    }

    public Id3v2Tag() {

    }

    private void init(byte[] buffer) {
        // TODO Auto-generated method stub

    }



    public String getAlbum() {
        // TODO Auto-generated method stub
        return null;
    }

    public void setAlbum(String album) {
        // TODO Auto-generated method stub

    }

    public String getArtist() {
        // TODO Auto-generated method stub
        return null;
    }

    public void setArtist(String artist) {
        // TODO Auto-generated method stub

    }

    public String getComment() {
        // TODO Auto-generated method stub
        return null;
    }

    public void setComment(String comment) {
        // TODO Auto-generated method stub

    }

    public Integer getYear() {
        // TODO Auto-generated method stub
        return null;
    }

    public void setYear(Integer year) {
        // TODO Auto-generated method stub

    }

    public String getGenre() {
        // TODO Auto-generated method stub
        return null;
    }

    public void setGenre(String genre) {
        // TODO Auto-generated method stub

    }

    public String getTitle() {
        // TODO Auto-generated method stub
        return null;
    }

    public void setTitle(String title) {
        // TODO Auto-generated method stub

    }

    public Integer getTrack() {
        return null;
    }


    public void setTrack(Integer track) {
        // do nothing
    }

    public Mp3File getParentAudioFile() {
        return parentAudioFile;
    }

    public void setParentAudioFile(Mp3File parentAudioFile) {
        this.parentAudioFile = parentAudioFile;
    }

    public Map<String, Frame> getFrameMap() {
        return frameMap;
    }
}
