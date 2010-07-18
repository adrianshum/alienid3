package com.alien.id3lib.tag.id3v2;

public class UnknownFrame extends Frame {

    byte[] data;

    public UnknownFrame(String id, byte[] data) {
        super(id);
        if (data != null) {
            this.data = new byte[data.length];
            System.arraycopy(data, 0, this.data, 0, data.length);
        }
    }

    public void setData(byte[] data) {
        if (data != null) {
            this.data = new byte[data.length];
            System.arraycopy(data, 0, this.data, 0, data.length);
        }
    }

    public byte[] getData() {
        return this.data;
    }

}
