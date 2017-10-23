package cn.ipaynow.util;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 不可逆加密(签名)
 *
 */
public class MD5 {
        /**
         * Md5算法加密
         * 
         * @s 明文
         * @return 密文
         */
        static String ToMd5(String s,String encode,String salt){
        	MD5 md5 = new MD5();
        	return md5.md5Str(s, encode,salt);
        }
        String md5Str(String str,String encode,String salt){
        	try {
        		MessageDigest md = MessageDigest.getInstance("MD5");
        		md.update(str.getBytes(encode));
                if(salt != null){
                    md.update(salt.getBytes());
                }
        		byte[] b = md.digest();
        		return BytesUtil.bytes2hex(b);
        	} catch (Exception e) {
        	}
        	return "";
        }
       
     
        
        static String getFileMD5String(File file,String salt) throws IOException, NoSuchAlgorithmException {
        	MessageDigest messagedigest = MessageDigest.getInstance("MD5");
        	FileInputStream in = new FileInputStream(file);
        	FileChannel ch = in.getChannel();
        	MappedByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
            if(salt != null){
                messagedigest.update(salt.getBytes());
            }
        	messagedigest.update(byteBuffer);
        	in.close();
        	return BytesUtil.bytes2hex(messagedigest.digest());
        }
        
        
        
        static byte[] ToMd5(byte[] src,String salt){
            MessageDigest md = null;
            try {
                md = MessageDigest.getInstance("MD5");
                if(salt != null){
                    md.update(salt.getBytes());
                }
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                return null;
            }
            md.update(src);
            return md.digest();
        }
}

