package com.alien.id3lib.tag.id3v2;

public class Utils {
    public static final int SYNC_SAFE_INT_SIZE = 4;
    public static final int SYNC_SAFE_INT_MAX_VALUE = 0xFFFFFFF;

    public static int decodeSyncSafeInt(byte[] bytes, int offset) {
        if (bytes == null || bytes.length < offset + SYNC_SAFE_INT_SIZE) {
            return -1;
        }

        int result = 0;
        for (int i = 0; i < SYNC_SAFE_INT_SIZE; ++i) {
            result = (result << 7) | bytes[offset + i];
        }

        return result;
    }
    public static int decodeSyncSafeInt(byte[] bytes) {
        return decodeSyncSafeInt(bytes, 0);
    }
    
    public static byte[] encodeSyncSafeInt(int value) {
        byte[] bytes = null;
        if (value >= 0 && value <= SYNC_SAFE_INT_MAX_VALUE) {
            bytes = new byte[SYNC_SAFE_INT_SIZE];
            writeSyncSafeInt(value, bytes);
        }
        return bytes;
    }

    public static void writeSyncSafeInt(int value, byte[] bytes, int offset) {
        if (value < 0 || value > SYNC_SAFE_INT_MAX_VALUE || bytes == null || bytes.length < offset + SYNC_SAFE_INT_SIZE) {
            return;
        }
        for (int i = 0; i < SYNC_SAFE_INT_SIZE; ++i) {
            bytes[offset + SYNC_SAFE_INT_SIZE - i - 1] = (byte) ((value >> (i * 7)) & 0x7F);
        }
    }

    public static void writeSyncSafeInt(int value, byte[] bytes) {
        writeSyncSafeInt(value, bytes, 0);

    }

}
