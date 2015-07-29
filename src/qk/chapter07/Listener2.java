package qk.chapter07;

import org.springframework.stereotype.Component;

import qk.Constants;
import qk.YongHu;

import com.bstek.dorado.view.widget.base.Button;
import com.bstek.dorado.web.DoradoContext;

@Component
public class Listener2 {
	//spring:listener2#onInit
	public void onInit(Button button){
		System.out.println("进入Listener2 的 onInit()");
		YongHu yh = (YongHu) DoradoContext.getCurrent().getAttribute(DoradoContext.SESSION,Constants.KEY_YONGHU);
		if(null == yh){
			button.setIgnored(true);
		}
	}
}
