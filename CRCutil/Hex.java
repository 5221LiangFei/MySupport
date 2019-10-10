package com.fro.util;

public class Hex {

	public static final char[] DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
	
	public static char[] encodeHexString(byte[] data) {
        int l = data.length;
        char[] out = new char[l << 1];
        int i = 0;
        for (int j = 0; i < l; i++) {
            out[(j++)] = DIGITS[((0xF0 & data[i]) >>> 4)];
            out[(j++)] = DIGITS[(0xF & data[i])];
        }
        return out;
    }
    public static byte[] decodeHex(char[] data) throws Exception {
        int len = data.length;
        if ((len & 0x1) != 0) throw new Exception("Odd number of characters.");
        byte[] out = new byte[len >> 1];
        int i = 0;
        for (int j = 0; j < len; i++) {
            int f = toDigit(data[j], j) << 4;
            j++;
            f |= toDigit(data[j], j);
            j++;
            out[i] = ((byte) (f & 0xFF));
        }
        return out;
    }
    
    public static int toDigit(char ch, int index) throws Exception {
        int digit = Character.digit(ch, 16);
        if (digit == -1) throw new Exception("Illegal hexadecimal charcter " + ch + " at index " + index);
        return digit;
    }

}
