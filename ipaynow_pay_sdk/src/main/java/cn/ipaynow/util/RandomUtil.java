package cn.ipaynow.util;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomUtil {
    private static String CHARS = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static String NUMBERS = "0123456789";
    private static Random random = new Random();
    private static int DEFAULT_LENGTH = 32;
	/**
     * 生成指定长度的随机字符串
     *
     * @len 要生成的字符串的长度
     * @return 随机字符串
     */
    public static String getRandomStr(int length) {

        if (length <= 0) {
            length = DEFAULT_LENGTH;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(CHARS.length());
            sb.append(CHARS.charAt(index));
        }
        return sb.toString();
    }

    public static String getRandomNumber(int length) {

        if (length <= 0) {
            length = DEFAULT_LENGTH;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(NUMBERS.length());
            sb.append(NUMBERS.charAt(index));
        }
        return sb.toString();
    }

	/**
     * 生成指定范围内的随即int值
     *
     * @from 范围起始值
     * @to 范围结束值
     * @return 范围内的随即数
     */
    public static int randInt(int from,int to)
    {
	return (Math.abs(new Random().nextInt()))%(to - from + 1)+from;	
    }
    
    
    
    
    /**
     * 生成指定范围内的随即int值,出去excludes中的值
     *
     * @from 范围起始值
     * @to 范围结束值
     * @excludes 除去的int值
     * @return 范围内的随即数
     * @throws Exception 
     */
    public static int randInt(int from,int to,int [] excludes) throws Exception
    {
    	if(to - from +1 <= excludes.length){
    		throw new Exception("params err");
    	}
    	List<Integer> randomAble = new ArrayList<Integer>();
    	for(int i = from;i<= to ;i++){
    		
    		boolean f = false;
    		for(int exclude:excludes){
    			if(exclude == i){
    				f = true;
    				break;
    			}
    		}
    		if(!f){
    			randomAble.add(i);
    		}
    	}
    	return randomAble.get(RandomUtil.randInt(0, randomAble.size()-1)).intValue();
    }
}
