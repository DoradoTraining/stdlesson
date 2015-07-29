package qk.chapter03;

import java.math.BigInteger;

import net.sf.ehcache.search.aggregator.Sum;

public class Test {
	public static String change(String str) {
		str = "com";
		return str;
	}
	public static void main(String[] args) {
		String str = "bstek";		
		change(str);	
		//System.out.println(str);
		//System.out.println(change(str));
		fangfa();
	}
	
	public static void fangfa(){
		String str = new String("33322");
		char[] chars = {'a','b',};
		
		//str.charAt(index)
		//System.out.println("2333"+'b');
		//System.out.println(Digui(6));
		//System.out.println(xiangjia(25));
		//System.out.println(jisuan(500));
		js();
	}
	
	public static int Digui(int arg){
		if(arg>1){
			arg*=Digui(arg-1);
		}
		return arg;
	}
	
	public static int xiangjia(int arg){
		if(arg>1){
			arg+=Digui(arg-1);
		}
		return arg;
	}
	
	public  static int jisuan(int count){
		//int count = 1;
		int arg = 2;
		
		int sum = 1;
		int sum2 = 0;
		for(int i=1;i<=count;i++){
			for(int j=1;j<=4;j++){
				sum = sum + arg;
				sum2+=sum;
				if(j == 4){
					arg+=2;
				}
			}
		}
		return sum2;
	}
	
	
	public static void js(){
		BigInteger bi = new BigInteger("2");
		
		for(int i=1;i<=1000;i++){
			bi = bi.multiply(bi);
		}
		System.out.println(bi);
	}
}
