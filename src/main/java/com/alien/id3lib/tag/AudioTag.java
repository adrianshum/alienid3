package com.alien.id3lib.tag;



public interface AudioTag {
    String getTitle();
    void setTitle(String title);
    
    String getArtist();
    void setArtist(String artist);
    
    String getAlbum();
    void setAlbum(String album);
    
    Integer getYear();
    void setYear(Integer year);
    
    String getComment();
    void setComment(String comment);
    
    String getGenre();
    void setGenre(String genre);
    
    Integer getTrack();
    void setTrack(Integer track);
    
}
