package util;

public class BinaryUtil {
	private static String hexStr = "0123456789ABCDEF";
	private static String[] binaryArray = { "0000", "0001", "0010", "0011",
			"0100", "0101", "0110", "0111", "1000", "1001", "1010", "1011",
			"1100", "1101", "1110", "1111" };

	/**
	 * 
	 * @param str
	 * @return ת��Ϊ�������ַ���
	 */
	public static String bytes2BinaryStr(byte[] bArray) {

		String outStr = "";
		int pos = 0;
		for (byte b : bArray) {
			// ����λ
			pos = (b & 0xF0) >> 4;
			outStr += binaryArray[pos];
			// ����λ
			pos = b & 0x0F;
			outStr += binaryArray[pos];
		}
		return outStr;

	}

	/**
	 * 
	 * @param bytes
	 * @return ��������ת��Ϊʮ�������ַ����
	 */
	public static String BinaryToHexString(byte[] bytes) {

		String result = "";
		String hex = "";
		for (int i = 0; i < bytes.length; i++) {
			// �ֽڸ�4λ
			hex = String.valueOf(hexStr.charAt((bytes[i] & 0xF0) >> 4));
			// �ֽڵ�4λ
			hex += String.valueOf(hexStr.charAt(bytes[i] & 0x0F));
			result += hex + " ";
		}
		return result;
	}

	/**
	 * 
	 * @param hexString
	 * @return ��ʮ������ת��Ϊ�ֽ�����
	 */
	public static byte[] HexStringToBinary(String hexString) {
		// hexString�ĳ��ȶ�2ȡ������Ϊbytes�ĳ���
		int len = hexString.length() / 2;
		byte[] bytes = new byte[len];
		byte high = 0;// �ֽڸ���λ
		byte low = 0;// �ֽڵ���λ

		for (int i = 0; i < len; i++) {
			// ������λ�õ���λ
			high = (byte) ((hexStr.indexOf(hexString.charAt(2 * i))) << 4);
			low = (byte) hexStr.indexOf(hexString.charAt(2 * i + 1));
			bytes[i] = (byte) (high | low);// �ߵ�λ��������
		}
		return bytes;
	}
}
