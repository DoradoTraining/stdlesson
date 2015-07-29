package qk.el;

import com.bstek.dorado.web.DoradoContext;

public class UserSession {
	public String getUserId(){
		System.out.println("执行UserSession");
		DoradoContext context = DoradoContext.getCurrent();
		YongHu yh = (YongHu) context.getAttribute(DoradoContext.SESSION, Constants.USER_KEY);
		int userId = yh.getId();
		System.out.println("userId: "+userId);
		return String.valueOf(userId);
//		String str = (String) context.getAttribute(DoradoContext.SESSION, "test");
//		System.out.println("str: "+str);
		//return "12345678";
		//return str;
	}

}
