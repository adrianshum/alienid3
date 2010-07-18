package com.alien.id3lib.audio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import com.alien.id3lib.exception.CharsetNotSupportedException;
import com.alien.id3lib.exception.UnsupportedTagException;
import com.alien.id3lib.tag.AudioTag;
import com.alien.id3lib.tag.id3v1.Id3v11Tag;
import com.alien.id3lib.tag.id3v1.Id3v1Tag;
import com.alien.id3lib.tag.id3v2.Id3v22Tag;
import com.alien.id3lib.tag.id3v2.Id3v23Tag;
import com.alien.id3lib.tag.id3v2.Id3v24Tag;
import com.alien.id3lib.tag.id3v2.Id3v2Tag;

public class Mp3File implements AudioFile {

    protected Id3v1Tag id3v1Tag;
    protected Id3v2Tag id3v2Tag;
    protected List<AudioTag> audioTags;

    protected String charsetName = Charset.defaultCharset().name();
    protected RandomAccessFile randomAccessFile;
    protected File file;

    // Raw data for ID3v1 Tag and ID3v2 Tag in MP3 file
    private long id3v1TagOffset = -1;
    private long id3v1TagLength = 128;
    private long id3v2TagOffset = -1;
    private long id3v2TagLength = -1;

    private long audioDataOffset = 0;       // start position of audio data
    private long audioDataLength = 0;       // length of audio data

    public Mp3File(File file, String charsetName) throws FileNotFoundException {
        if (charsetName != null) {
            if (Charset.isSupported(charsetName)) {
                this.charsetName = charsetName;
            } else {
                throw new CharsetNotSupportedException(charsetName);
            }
        }

        if (file == null || !file.exists() || !file.isFile()) {
            throw new FileNotFoundException();
        }
        this.file = file;
        randomAccessFile = new RandomAccessFile(file, "r");
        refreshTags();
    }

    public Mp3File(File file) throws FileNotFoundException {
        this(file, null);
    }

    public Mp3File(String filename) throws FileNotFoundException {
        this(new File(filename), null);
    }

    public Mp3File(String filename, String charsetName) throws FileNotFoundException {
        this(new File(filename), charsetName);
    }


    public List<AudioTag> getAllTags() {
        if (audioTags == null) {
            audioTags = new ArrayList<AudioTag>();
            if (id3v1Tag != null) {
                audioTags.add(id3v1Tag);
            }
            if (id3v2Tag != null) {
                audioTags.add(id3v2Tag);
            }
        }

        return audioTags;
    }

    public AudioTag getTag() {
        // TODO Auto-generated method stub
        return null;
    }

    public void setTag(AudioTag tag) throws UnsupportedTagException {
        // TODO Auto-generated method stub

    }

    public void setTags(List<AudioTag> tag) {
        // TODO Auto-generated method stub

    }

    public void setCharsetName(String charset) {
        // TODO Auto-generated method stub

    }

    public String getCharsetName() {
        // TODO Auto-generated method stub
        return this.charsetName;

    }

    public void commit() {
        // TODO Auto-generated method stub

    }

    public Id3v1Tag getId3v1Tag() {
        return id3v1Tag;
    }

    public void setId3v1Tag(Id3v1Tag id3v1Tag) {
        this.id3v1Tag = id3v1Tag;
    }

    public Id3v2Tag getId3v2Tag() {
        return id3v2Tag;
    }

    public void setId3v2Tag(Id3v2Tag id3v2Tag) {
        this.id3v2Tag = id3v2Tag;
    }


    private void refreshTags() {
        this.id3v1Tag = readId3v1Tag();
        this.id3v2Tag = readId3v2Tag();
    }

    // Internal factory method for ID3 v1 tag
    private Id3v1Tag readId3v1Tag() {
        // check if ID3 tag exists
        Id3v1Tag tag = null;
        final int ID3_TAG_SIZE = 128;
        try {
            long fileLength = randomAccessFile.length();
            if (fileLength > ID3_TAG_SIZE) {
                randomAccessFile.seek(fileLength - ID3_TAG_SIZE);
                byte[] bytes = new byte[ID3_TAG_SIZE];
                randomAccessFile.read(bytes);
                if (bytes[0] == 'T' && bytes[1] == 'A' && bytes[2] == 'G') {  // Contains ID3 v1 Tag
                    this.id3v1TagOffset = fileLength - ID3_TAG_SIZE;
                    if (bytes[125] == 0 && bytes[126] > 0) {  // ID3 v1.1
                        tag = new Id3v11Tag(bytes, charsetName);
                    } else {
                        tag = new Id3v1Tag(bytes, charsetName);
                    }

                }
            }
        } catch (IOException e){

        }

        return tag;

    }

    // Internal factory method for ID3 v2.x tag
    private Id3v2Tag readId3v2Tag() {
        Id3v2Tag tag = null;
        final int ID3V2_HEADER_SIZE = 10;
        try {
            if (randomAccessFile.length() < ID3V2_HEADER_SIZE ) {
                return null;
            }

            // Check if ID3v2 exists

            randomAccessFile.seek(0);
            byte[] header= new byte[ID3V2_HEADER_SIZE];
            randomAccessFile.read(header);
            if (matchId3v2HeaderPattern(header)) {
                switch (header[3]) {
                    case 2:
                        tag = new Id3v22Tag();
                        break;
                    case 3:
                        tag = new Id3v23Tag();
                        break;
                    case 4:
                        tag = new Id3v24Tag(header, randomAccessFile);
                        break;
                    default:
                        break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return tag;
    }

    protected static boolean matchId3v2HeaderPattern(byte[] header) {
        return (header != null && header.length == 10
                && header[0]=='I' && header[1] == 'D' && header[2] == '3'
                && header[3]< 0xFF && header[4]< 0xFF
                && header[6]< 0x80 && header[7]< 0x80 && header[8]< 0x80 && header[9]< 0x80);
    }


}
