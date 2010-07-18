package com.alien.id3lib.tag.id3v2;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * ID3 v2.4 Tag
 *
 * @author adrianshum
 *
 */
public class Id3v24Tag extends Id3v2Tag{
    protected static class ExtendedHeader {
        int size = 0;
        byte[] headerLengthBuffer = new byte[4];
        byte[] contentBuffer = null;
        public ExtendedHeader(RandomAccessFile raf) throws IOException {
            raf.read(headerLengthBuffer);
            size = Utils.decodeSyncSafeInt(headerLengthBuffer);
            contentBuffer = new byte[size - 4];
            raf.read(contentBuffer);
        }
    }

    protected boolean isExperimental = false;
    protected boolean isFooterExist = false;
    protected ExtendedHeader extendedHeader = null;
    protected List<Frame> frames = new ArrayList<Frame>();

    public Id3v24Tag(byte[] header, RandomAccessFile raf) throws IOException {
        this.majorVersion=header[3];
        this.minorVersion = header[4];
        this.isUnsynchronization = (header[5] & 0x80) > 0;
        this.isExtendedHeaderExist = (header[5] & 0x40) > 0;
        this.isExperimental = (header[5] & 0x20) > 0;
        this.isFooterExist = (header[5] & 0x10) > 0;
        this.size = Utils.decodeSyncSafeInt(header, 6);

        if (isExtendedHeaderExist) {
            this.extendedHeader = new ExtendedHeader(raf);
        }
        byte[] contentBytes = new byte[this.size];
        raf.read(contentBytes);
        ByteBuffer contentBuffer = ByteBuffer.wrap(contentBytes);

        initFrames(contentBuffer);

        if (isFooterExist) {
            raf.skipBytes(10);
        }

    }

    protected void initFrames(ByteBuffer contentBuffer) {
        byte[] frameIdBytes = new byte[4];
        byte[] frameLengthBytes = new byte[4];
        byte[] frameFlagsBytes = new byte[2];
        while (contentBuffer.hasRemaining()) {
            contentBuffer.get(frameIdBytes);
            if (frameIdBytes[0] == 0x0) {
                break;
            }
            String frameId = new String(frameIdBytes);
            contentBuffer.get(frameLengthBytes);
            int frameLength = Utils.decodeSyncSafeInt(frameLengthBytes);
            contentBuffer.get(frameFlagsBytes);
            byte[] frameContent = new byte[frameLength];
            contentBuffer.get(frameContent);
            frames.add(new Frame(frameId, frameContent));
        }
    }
    public boolean isExperimental() {
        return isExperimental;
    }

    public void setExperimental(boolean isExperimental) {
        this.isExperimental = isExperimental;
    }

    public boolean isFooterExist() {
        return isFooterExist;
    }

    public void setFooterExist(boolean isFooterExist) {
        this.isFooterExist = isFooterExist;
    }

    public ExtendedHeader getExtendedHeader() {
        return extendedHeader;
    }

    public void setExtendedHeader(ExtendedHeader extendedHeader) {
        this.extendedHeader = extendedHeader;
    }

    public List<Frame> getFrames() {
        return frames;
    }

    public void setFrames(List<Frame> frames) {
        this.frames = frames;
    }



}
