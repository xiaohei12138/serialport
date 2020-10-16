package tp.xmaihh.serialport.utils;

import android.util.Log;

public class ByteUtil {
    public static int isOdd(int num) {
        return num & 0x1;
    }

    public static int HexToInt(String inHex) {
        return Integer.parseInt(inHex, 16);
    }

    public static byte HexToByte(String inHex) {
        return (byte) Integer.parseInt(inHex, 16);
    }

    public static String Byte2Hex(Byte inByte) {
        return String.format("%02x", new Object[]{inByte}).toUpperCase();
    }

    public static String ByteArrToHex(byte[] inBytArr) {
        StringBuilder strBuilder = new StringBuilder();
        int j = inBytArr.length;
        for (int i = 0; i < j; i++) {
            strBuilder.append(Byte2Hex(Byte.valueOf(inBytArr[i])));
            strBuilder.append("");
        }
        return strBuilder.toString();
    }

    public static String ByteArrToHex(byte[] inBytArr, int offset, int byteCount) {
        StringBuilder strBuilder = new StringBuilder();
        int j = byteCount;
        for (int i = offset; i < j; i++) {
            strBuilder.append(Byte2Hex(Byte.valueOf(inBytArr[i])));
        }
        return strBuilder.toString();
    }

    public static byte[] HexToByteArr(String inHex) {
        int hexlen = inHex.length();
        byte[] result;
        if (isOdd(hexlen) == 1) {
            hexlen++;
            result = new byte[hexlen / 2];
            inHex = "0" + inHex;
        } else {
            result = new byte[hexlen / 2];
        }
        int j = 0;
        for (int i = 0; i < hexlen; i += 2) {
            result[j] = HexToByte(inHex.substring(i, i + 2));
            j++;
        }
        return result;
    }

    /* access modifiers changed from: private */
    public String toHexString(byte[] arg, int length) {
        String result = new String();
        if (arg == null) {
            return "";
        }
        int i = 0;
        while (i < length) {
            result = new StringBuilder(String.valueOf(result)).append(Integer.toHexString(new Integer(arg[i] < (byte) 0 ? arg[i] + 256 : arg[i]).intValue())).append(" ").toString();
            Log.i("result", result);
            i++;
        }
        return result;
    }

    /* access modifiers changed from: private */
    public boolean isAllHex(String arg) {
        if (arg == null) {
            return false;
        }
        char[] array = arg.toCharArray();
        int i = 0;
        while (i < array.length) {
            if (array[i] != ' ' && ((array[i] < '0' || array[i] > '9') && ((array[i] < 'a' || array[i] > 'f') && (array[i] < 'A' || array[i] > 'F')))) {
                return false;
            }
            i++;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static byte[] toByteArray(String arg) {
        if (arg != null) {
            int i;
            char[] NewArray = new char[65536];
            char[] array = arg.toCharArray();
            int length = 0;
            for (i = 0; i < array.length; i++) {
                if (array[i] != ' ') {
                    NewArray[length] = array[i];
                    length++;
                }
            }
            int EvenLength = length % 2 == 0 ? length : length + 1;
            if (EvenLength != 0) {
                int[] data = new int[EvenLength];
                data[EvenLength - 1] = 0;
                i = 0;
                while (i < length) {
                    if (NewArray[i] >= '0' && NewArray[i] <= '9') {
                        data[i] = NewArray[i] - 48;
                    } else if (NewArray[i] >= 'a' && NewArray[i] <= 'f') {
                        data[i] = (NewArray[i] - 97) + 10;
                    } else if (NewArray[i] >= 'A' && NewArray[i] <= 'F') {
                        data[i] = (NewArray[i] - 65) + 10;
                    }
                    i++;
                }
                byte[] byteArray = new byte[(EvenLength / 2)];
                for (i = 0; i < EvenLength / 2; i++) {
                    byteArray[i] = (byte) ((data[i * 2] * 16) + data[(i * 2) + 1]);
                }
                return byteArray;
            }
        }
        return new byte[0];
    }
}