package qk.el;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bstek.dorado.core.el.ContextVarsInitializer;
import com.bstek.dorado.core.el.ExpressionUtilsObject;
import com.bstek.dorado.util.SingletonBeanFactory;

public class UserVarsInitializer implements ContextVarsInitializer {
	private static Log Logger = LogFactory.getLog(UserVarsInitializer.class);

	@Override
	public void initializeContext(Map<String, Object> vars) throws Exception {
		Logger.info("执行initializeContext()");
		vars.put("UserEL", new UserSession());
		//vars.put("mysystem", System.getProperties());
		//vars.put("myutil", SingletonBeanFactory.getInstance(ExpressionUtilsObject.class));
	}

}
