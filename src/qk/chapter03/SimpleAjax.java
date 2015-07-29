package qk.chapter03;

import java.util.Properties;

import org.springframework.stereotype.Component;

import com.bstek.dorado.annotation.Expose;
import com.bstek.dorado.core.DoradoAbout;

@Component
public class SimpleAjax {
	
	@Expose
	public String toUpperCase(String str){
		System.out.println("toUpperCase()");
		return str.toUpperCase();
	}
	@Expose
	public int multiply(int num1, int num2){
		System.out.println(num1+" "+num2);
		return num1*num2;
	}
	@Expose
	public Properties getSystemInfo(){
		Properties info = new Properties();
		System.out.println(DoradoAbout.getProductTitle());
		System.out.println(DoradoAbout.getVendor());
		System.out.println(DoradoAbout.getVersion());
		
		info.setProperty("product", DoradoAbout.getProductTitle());
		info.setProperty("vendor", DoradoAbout.getVendor());
		info.setProperty("version", DoradoAbout.getVersion());
		
		return info;
	}

}
