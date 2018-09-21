package org.github.cmonkey.spark;

import sun.misc.Unsafe;

import java.util.Arrays;

public class TestUnsafe{

    public static void main(String[] args) {
        System.err.close();
        int count = 0;
        while (count++ < 120000) {
            test();
        }
    }



    public static void test() {
        byte[] newBuf = serBytes(397);
        if (newBuf.length != 397) {
            System.out.println("ERROR_MSG");
        }
    }

    static final long BYTE_ARRAY_BASE_OFFSET = (long)getUnsafe().arrayBaseOffset(byte[].class);

    public static byte[] serBytes(int bufLen) {
        byte[] buf = new byte[bufLen];
        getUnsafe().putInt(buf, BYTE_ARRAY_BASE_OFFSET + 1L, buf.length);
        System.err.println("ref " + Arrays.toString(buf));
        return buf;
    }

	public static Unsafe getUnsafe() {
		try {
			java.lang.reflect.Field singletonInstanceField = Unsafe.class.getDeclaredField("theUnsafe");
			singletonInstanceField.setAccessible(true);
			return (Unsafe) singletonInstanceField.get(null);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}


}
