//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package practice.common.utils;

public final class ArrayUtil {
    public static final int MAX_ARRAY_LENGTH = 0;

    private ArrayUtil() {
    }



    public static void swapTwoArrayEle(int[] arr,int i,int j){
        int tmp;
        tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }



























    public static int parseInt(char[] chars, int offset, int len) throws NumberFormatException {
        return parseInt(chars, offset, len, 10);
    }

    public static int parseInt(char[] chars, int offset, int len, int radix) throws NumberFormatException {
        if (chars != null && radix >= 2 && radix <= 36) {
            int i = 0;
            if (len == 0) {
                throw new NumberFormatException("chars length is 0");
            } else {
                boolean negative = chars[offset + i] == '-';
                if (negative) {
                     i = i + 1;
                    if (i == len) {
                        throw new NumberFormatException("can't convert to an int");
                    }
                }

                if (negative) {
                    ++offset;
                    --len;
                }

                return parse(chars, offset, len, radix, negative);
            }
        } else {
            throw new NumberFormatException();
        }
    }

    private static int parse(char[] chars, int offset, int len, int radix, boolean negative) throws NumberFormatException {
        int max = -2147483648 / radix;
        int result = 0;

        for(int i = 0; i < len; ++i) {
            int digit = Character.digit(chars[i + offset], radix);
            if (digit == -1) {
                throw new NumberFormatException("Unable to parse");
            }

            if (max > result) {
                throw new NumberFormatException("Unable to parse");
            }

            int next = result * radix - digit;
            if (next > result) {
                throw new NumberFormatException("Unable to parse");
            }

            result = next;
        }

        if (!negative) {
            result = -result;
            if (result < 0) {
                throw new NumberFormatException("Unable to parse");
            }
        }

        return result;
    }


    public static short[] growExact(short[] array, int newLength) {
        short[] copy = new short[newLength];
        System.arraycopy(array, 0, copy, 0, array.length);
        return copy;
    }




    public static float[] growExact(float[] array, int newLength) {
        float[] copy = new float[newLength];
        System.arraycopy(array, 0, copy, 0, array.length);
        return copy;
    }





    public static double[] growExact(double[] array, int newLength) {
        double[] copy = new double[newLength];
        System.arraycopy(array, 0, copy, 0, array.length);
        return copy;
    }




    public static int[] growExact(int[] array, int newLength) {
        int[] copy = new int[newLength];
        System.arraycopy(array, 0, copy, 0, array.length);
        return copy;
    }




    public static long[] growExact(long[] array, int newLength) {
        long[] copy = new long[newLength];
        System.arraycopy(array, 0, copy, 0, array.length);
        return copy;
    }




    public static byte[] growExact(byte[] array, int newLength) {
        byte[] copy = new byte[newLength];
        System.arraycopy(array, 0, copy, 0, array.length);
        return copy;
    }



    public static char[] growExact(char[] array, int newLength) {
        char[] copy = new char[newLength];
        System.arraycopy(array, 0, copy, 0, array.length);
        return copy;
    }




    public static int hashCode(char[] array, int start, int end) {
        int code = 0;

        for(int i = end - 1; i >= start; --i) {
            code = code * 31 + array[i];
        }

        return code;
    }

    public static <T> void swap(T[] arr, int i, int j) {
        T tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }




    public static byte[] copyOfSubArray(byte[] array, int from, int to) {
        byte[] copy = new byte[to - from];
        System.arraycopy(array, from, copy, 0, to - from);
        return copy;
    }

    public static char[] copyOfSubArray(char[] array, int from, int to) {
        char[] copy = new char[to - from];
        System.arraycopy(array, from, copy, 0, to - from);
        return copy;
    }

    public static short[] copyOfSubArray(short[] array, int from, int to) {
        short[] copy = new short[to - from];
        System.arraycopy(array, from, copy, 0, to - from);
        return copy;
    }

    public static int[] copyOfSubArray(int[] array, int from, int to) {
        int[] copy = new int[to - from];
        System.arraycopy(array, from, copy, 0, to - from);
        return copy;
    }

    public static long[] copyOfSubArray(long[] array, int from, int to) {
        long[] copy = new long[to - from];
        System.arraycopy(array, from, copy, 0, to - from);
        return copy;
    }

    public static float[] copyOfSubArray(float[] array, int from, int to) {
        float[] copy = new float[to - from];
        System.arraycopy(array, from, copy, 0, to - from);
        return copy;
    }

    public static double[] copyOfSubArray(double[] array, int from, int to) {
        double[] copy = new double[to - from];
        System.arraycopy(array, from, copy, 0, to - from);
        return copy;
    }


}
