package com.alien.id3lib.audio;

import java.util.List;

import com.alien.id3lib.exception.UnsupportedTagException;
import com.alien.id3lib.tag.AudioTag;

public interface AudioFile {
    AudioTag getTag();      // Get current Audio Tag
    
    List<AudioTag> getAllTags();
    
    public void setTag(AudioTag tag) throws UnsupportedTagException;
    
    public void setTags(List<AudioTag> tag);
    
    public void setCharsetName(String charset);
    
    public String getCharsetName();

    public void commit();
    
}
