package cn.ipaynow.util;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

public final class BytesUtil {
    
    
    
    /**
     * @param b 
     * @return bit字符串
     */
    public static String byteToBit(byte b) {
        return ""
                + (byte) ((b >> 7) & 0x1) + (byte) ((b >> 6) & 0x1)
                + (byte) ((b >> 5) & 0x1) + (byte) ((b >> 4) & 0x1)
                + (byte) ((b >> 3) & 0x1) + (byte) ((b >> 2) & 0x1)
                + (byte) ((b >> 1) & 0x1) + (byte) ((b >> 0) & 0x1);
    }
    /**
     * 
     * @param bs
     * @return bit字符串
     */
    public static String bytesToBit(byte[] bs) {
        String r = "";
        for(byte b : bs){
            r += byteToBit(b);
        }
        return r;
    }
    /**
     * @param byteStr bit字符串
     * @return
     */
    public static byte bitToByte(String byteStr) {  
        if (byteStr == null || byteStr.length() != 8) {  
            throw new IllegalArgumentException();
        }
        int re;
        if (byteStr.charAt(0) == '0') {  
            re = Integer.parseInt(byteStr, 2);  
        } else {  
            re = Integer.parseInt(byteStr, 2) - 256;  
        }
        return (byte) re;  
    }  
    /**
     * 
     * @param byteStrs bit字符串
     * @return
     */
    public static byte[] bitToBytes(String byteStrs) {  
        if (byteStrs == null || byteStrs.length() % 8 != 0) {  
            throw new IllegalArgumentException();
        }
        byte[] bs = new byte[byteStrs.length()/8];
        for(int i = 0 ;i < bs.length ;i ++){
            bs[i] = bitToByte(byteStrs.substring(i * 8 , i * 8 + 8));
        }
        return bs;
    }
    
    
    
    
    
    /**
     * 
     *  
    private static final char chars[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E','F' };
    public static String byte2hex(byte b) {
        char hex[] = new char[2];
        hex[0] = chars[(new Byte(b).intValue() & 0xf0) >> 4];
        hex[1] = chars[new Byte(b).intValue() & 0xf];
        return new String(hex);
    }
    public static String bytes2hex(byte[] b) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            sb.append(byte2hex(b[i]));
            sb.append(" ");
        }
        return sb.toString();
    }
     */
  	public static String bytes2hex(byte[] bs)
  	{
  		String retStr="";
  		for (int i=0 ;i<bs.length;i++)
  		{
  			if (Integer.toHexString((int)bs[i]).length()>1)
  			{
  			  retStr+= Integer.toHexString((int)bs[i]).substring(Integer.toHexString((int)bs[i]).length()-2);
  			}else
  			{ 
  		      retStr+="0"+Integer.toHexString((int)bs[i]).substring(Integer.toHexString((int)bs[i]).length()-1);;
  			}
  		}
  		return retStr;
  	}
  	public static byte[] hex2bytes(String hexStr)
	{
		if (hexStr.length()%2!=0)
		{
			hexStr="0"+hexStr;
		}
		byte[] retByte=new byte[hexStr.length()/2];
		for (int i=0 ;i<hexStr.length()/2;i++)
		{	
			byte[] b=new byte[1];
			b[0]=intToBytes4(Integer.parseInt(hexStr.substring(2*i, 2*i+2), 16))[3];
			retByte[i]=intToBytes4(Integer.parseInt(hexStr.substring(2*i, 2*i+2), 16))[3];            
		}
		return retByte;
	}
    
    
    
    
    
  	
  	
  	public static long bytes10ToLong(byte mybytes[]) {
        long tmp = (0xff & mybytes[0]) << 72 | (0xff & mybytes[1]) << 64 | (0xff & mybytes[2]) << 56
                | (0xff & mybytes[3]) << 48 | (0xff & mybytes[4]) << 40 | (0xff & mybytes[5]) << 32
                | (0xff & mybytes[6]) << 24 | 0xff & mybytes[7] << 16 | 0xff & mybytes[8] << 8 | 0xff & mybytes[9];
        return tmp;
    }
    
    public static long bytes8ToLong(byte mybytes[]) {
        long tmp = (0xff & mybytes[0]) << 56 | (0xff & mybytes[1]) << 48 | (0xff & mybytes[2]) << 40
                | (0xff & mybytes[3]) << 32 | (0xff & mybytes[4]) << 24 | (0xff & mybytes[5]) << 16
                | (0xff & mybytes[6]) << 8 | 0xff & mybytes[7];
        return tmp;
    }
    public static long Bytes4ToLong(byte[] mybytes) {
		return ((0xFF & mybytes[0]) << 24 | (0xFF & mybytes[1]) << 16
				| (0xFF & mybytes[2]) << 8 | 0xFF & mybytes[3]);
	}
    public static byte[] longToBytes8(long i) {
        byte mybytes[] = new byte[8];
        mybytes[7] = (byte) (int) ((long)  0xff & i);
        mybytes[6] = (byte) (int) (((long) 0xff00 & i) >> 8);
        mybytes[5] = (byte) (int) (((long) 0xff0000 & i) >> 16);
        mybytes[4] = (byte) (int) (((long) 0xff000000 & i) >> 24);
        int high = (int) (i >> 32);
        mybytes[3] = (byte) (0xff & high);
        mybytes[2] = (byte) ((0xff00 & high) >> 8);
        mybytes[1] = (byte) ((0xff0000 & high) >> 16);
        mybytes[0] = (byte) ((0xff000000 & high) >> 24);
        return mybytes;
    }
    public static byte[] LongToBytes4(long i) {
		byte[] mybytes = new byte[4];
		mybytes[3] = (byte) (int) (0xFF & i);
		mybytes[2] = (byte) (int) ((0xFF00 & i) >> 8);
		mybytes[1] = (byte) (int) ((0xFF0000 & i) >> 16);
		mybytes[0] = (byte) (int) ((0xFF000000 & i) >> 24);
		return mybytes;
	}
    
    
    public static byte[] LongToBytes10(long i) {
    	byte mybytes[] = new byte[10];
        mybytes[9] = (byte) (int) ((long) 0xff & i);
        mybytes[8] = (byte) (int) (((long) 0xffff & i) >> 8);
        mybytes[7] = (byte) (int) (((long) 0xff0000 & i) >> 16);
        mybytes[6] = (byte) (int) (((long) 0xff000000 & i) >> 24);
        long l = i;
        int high = (int) (i >> 512);
        mybytes[5] = (byte) (0xff & high);
        mybytes[4] = (byte) ((0xff00 & high) >> 8);
        mybytes[3] = (byte) ((0xff0000 & high) >> 16);
        mybytes[2] = (byte) ((0xff000000 & high) >> 24);
        high = (int) (l >> 32);
        mybytes[1] = (byte) (0xff & high);
        mybytes[0] = (byte) ((0xff00 & high) >> 8);
        return mybytes;
	}
    
    
    
    
    public static int bytes4ToInt(byte mybytes[],int offset) {
        int tmp = (0xff & mybytes[0+offset]) << 24 | (0xff & mybytes[1+offset]) << 16 | (0xff & mybytes[2+offset]) << 8 | 0xff & mybytes[3+offset];
        return tmp;
    }
    
    
    public static int bytes4ToInt(byte mybytes[]) {
        int tmp = (0xff & mybytes[0]) << 24 | (0xff & mybytes[1]) << 16 | (0xff & mybytes[2]) << 8 | 0xff & mybytes[3];
        return tmp;
    }
    public static byte[] intToBytes4(int i) {
        byte mybytes[] = new byte[4];
        mybytes[3] = (byte) (0xff & i);
        mybytes[2] = (byte) ((0xff00 & i) >> 8);
        mybytes[1] = (byte) ((0xff0000 & i) >> 16);
        mybytes[0] = (byte) ((0xff000000 & i) >> 24);
        return mybytes;
    }
    public static void intToBytes4(int i,byte[] mybytes,int offset) {
        mybytes[3+offset] = (byte) (0xff & i);
        mybytes[2+offset] = (byte) ((0xff00 & i) >> 8);
        mybytes[1+offset] = (byte) ((0xff0000 & i) >> 16);
        mybytes[0+offset] = (byte) ((0xff000000 & i) >> 24);
    }
    
    
    
    public static byte[] IntToBytes2(int i) {
		byte[] mybytes = new byte[2];
		mybytes[1] = (byte) (0xFF & i);
		mybytes[0] = (byte) ((0xFF00 & i) >> 8);
		return mybytes;
	}
    
    
    
    
    
    
    
    public static short bytes2Short(byte mybytes[]) {
        short tmp = (short) ((0xff & mybytes[0]) << 8 | 0xff & mybytes[1]);
        return tmp;
    }
    public static byte[] short2Byte(short i) {
        byte mybytes[] = new byte[2];
        mybytes[1] = (byte) (0xff & i);
        mybytes[0] = (byte) ((0xff00 & i) >> 8);
        return mybytes;
    }
    
    
    
    
    
    /**
     * 打印int的二进制字符串,如3(十进制) 标示为0011(二进制)
     * @l 十进制int值
     * @len 表示出来的长度
     * 
     * @return 二进制字符串
     */
    public static String IntegerToBinary(int l, int len) {
        String bin = Integer.toBinaryString(l);
        while (len - bin.length() > 0) {
            bin = "0" + bin;
        }
        return bin;
    }
    
    
    
    /**
     * 将字符串转化成特定长度的byte[],如果value的长度小于length,则右补零。如果value的长度大于length,则截取掉�?��分�?
     */
    public static byte[] string2bytes(int length, String value) {
        byte[] b1 = new byte[length];
        int i = 0;
        if (value != null && !value.equals("")) {
            byte[] b2 = value.getBytes();
            while (i < b2.length && i < length) {
                b1[i] = b2[i];
                i++;
            }
        }
        while (i < b1.length) {
            b1[i] = 0;
            i++;
        }
        return b1;
    }
    public static String bytes2string(byte[] bRefArr,String encoding) throws UnsupportedEncodingException{
		 int length=0;
		 for (length=0;length<bRefArr.length;length++)
		 {
			 if (bRefArr[length]==0)
				 break;
		 }
		 byte[] temp = new byte[length];
		 for (int i=0;i<length;i++)
		 {
			 temp[i]=bRefArr[i];
		 }
		 return new String(temp,0,temp.length,encoding);
	 }
    
    
    //字节数组转换为char[] , �?
    public static char[] bytes2chars(byte[] paramArrayOfByte)
    {
    	int i = 0;
    	for (; (i < paramArrayOfByte.length) && (paramArrayOfByte[i] != 0); i++);
		   
    	char[] arrayOfChar = new char[i];
    	for (i = 0; i < arrayOfChar.length; i++){
    		arrayOfChar[i] = (char)paramArrayOfByte[i];
    	}
    	return arrayOfChar;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /**
	 * 去掉右边的二进制0
	 */
	public static byte[] trimRightZero(byte[] bytes) {
		for (int i = bytes.length - 1; i >= 0; i--) {
			if (bytes[i] != 0) {
				if (i == bytes.length - 1)
					return bytes;
				else {
					byte[] temp = new byte[i + 1];
					System.arraycopy(bytes, 0, temp, 0, temp.length);
					return temp;
				}
			}
		}
		return null;
	}
	
	/**
	 * 数组拷贝
	 * @param source 源数�?
	 * @param dest 目标数组�?
	 * @param sourceBegin�?���?��位置
	 * @param sourceEnd    源结束位置�?
	 * @param destBegin�??目标数组�?��位置
	 */
	public static void BytesCopy(byte[] source, byte[] dest, int sourceBegin,
			int sourceEnd, int destBegin) {
		int index = 0;
		for (int i = sourceBegin; i <= sourceEnd; ++i) {
			dest[(destBegin + index)] = source[i];
			++ index;
		}
	}
	
	
	
	
	public static boolean compare(byte[] byte1, byte[] byte2) {
		if (byte1 == null || byte2 == null)
			return false;
		if (byte1.length != byte2.length)
			return false;
		for (int i = 0; i < byte1.length; i++)
			if (byte1[i] != byte2[i])
				return false;

		return true;
	}
	
	
	
	
	
	
	
	public static void writeByLength(DataOutputStream out,byte[] b, int length)throws IOException
	{
		out.write(b);
		for (int i = 0; i < length - b.length; i++){
			out.write(0);
		}
	}
	
	public static byte[] readByLength(InputStream in ,int length) throws IOException{
		byte[] bodybytes = new byte[length];
		int index = 0;
		while(true){
			byte b = (byte)in.read();
			bodybytes[index] = b;
			index ++;
			if(index == length){
				return bodybytes;
			}
		}
	}
	public static boolean validBytes(byte[] bytes){
		for(byte b:bytes){
			if(b == -1){
				return false;
			}
		}
		return true;
	}
	
	
	
	
	
	public static String getFirstStr(byte[] buf, int sPos)
	  {
	    byte[] tmpBuf = new byte[21];
	    int pos = sPos;
	    while ((buf[pos] != 0) && (pos < buf.length)) {
	      tmpBuf[(pos - sPos)] = buf[pos];
	      pos += 1;
	    }
	    if (pos == sPos) {
	      return "";
	    }

	    String tmpStr = new String(tmpBuf);
	    return tmpStr.substring(0, pos - sPos);
	  }
}