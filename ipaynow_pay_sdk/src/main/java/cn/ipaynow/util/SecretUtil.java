package cn.ipaynow.util;


import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;


public class SecretUtil {


	 
	 public static String ToMd5(String s,String encode,String salt){
		 return MD5.ToMd5(s,encode,salt);
	 }
	 public static byte[] ToMd5(byte[] bytes,String salt){
         return MD5.ToMd5(bytes,salt);
     }
	 public static String getFileMD5String(File file,String salt) throws NoSuchAlgorithmException, IOException{
		 return MD5.getFileMD5String(file,salt);
	 }





}
