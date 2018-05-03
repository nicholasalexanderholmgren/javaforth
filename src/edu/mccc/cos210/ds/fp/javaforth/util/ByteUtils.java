package edu.mccc.cos210.ds.fp.javaforth.util;
/**
 * Utility class for holding various useful byte related operations.
 * @author Nicholas Holmgren
 *
 */
public interface ByteUtils {
	public static Integer bytesToInt(byte b, byte a) {
		int leadingDigits = 0;
		if(a<0) {
			leadingDigits = (int) (-Math.pow(2, 16) + (a%128)*256);
		}else {
			leadingDigits = a*256;
		}
		int trailingDigits = unsignByte(b);

		return leadingDigits + trailingDigits;
	}
	public static Byte[] intToBytes(Integer n) {
		Byte[] ans = new Byte[2];
		if(n > Math.pow(2, 16)) {
			n = n%((int)Math.pow(2, 16));
		}
		Integer nA = n/256;
		Integer nB = n%256;
		ans[0] = nA.byteValue();
		ans[1] = nB.byteValue();
		return ans;
	}
	/**
	 * @param byte addra is the leading half of the address
	 * @param byte addrb is the trailing half of the address
	 */
	public static int bytesToAddr(byte addra,byte addrb) {
		int leadingDigits = unsignByte(addra)*256;
		int trailingDigits = unsignByte(addrb);
		return leadingDigits + trailingDigits;
	}
	public static Byte[] addrToBytes(int addr) {
		addr = (int) (addr%Math.pow(2, 16));
		Byte[] ans = new Byte[2];
		ans[0] = (byte) (addr%256);
		ans[1] = (byte) (addr/256);
		return ans;
	}
	public static int unsignByte(byte b) {
		if (b>=0) {
			return b;
		}else {
			return 256 +b;
		}
	}

}
