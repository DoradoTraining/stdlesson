package qk.el;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.bstek.dorado.sql.iapi.ISqlDao;
import com.bstek.dorado.web.DoradoContext;

public class LoginController extends AbstractController {

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		
		String user = request.getParameter("user");
		String password = request.getParameter("password");
		
		System.out.println("user: "+user+"password: "+password);
		
		//sqlDao
		ISqlDao sqlDao = (ISqlDao) DoradoContext.getCurrent().getServiceBean("sqlDao");
		
		String sql = "select id,password,role from yonghu where user=:user";
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user", user);
		
		
		List<Map<String, Object>> rs = sqlDao.getSqlOperations().queryForList(sql, map);
		
		if(rs.size() == 1){
			Map<String, Object> result = rs.get(0);
			String password2 = (String) result.get("password");
			if(password.equals(password2)){
				//在Session中放置用户信息
				int id = (Integer) result.get("id");
				System.out.println(id);
				String role = (String) result.get("role");
				YongHu yh = new YongHu();
				yh.setId(id);
				yh.setUser(user);
				yh.setRole(role);
				request.getSession().setAttribute(Constants.USER_KEY, yh);
				
				request.getSession().setAttribute("test", "123456");
				
				if("S".equals(role)){
					return new ModelAndView("qk.StudentMain.d");
				}else if("T".equals(role)){
					return new ModelAndView("qk.TeacherMain.d");
				}else if("A".equals(role)){
					return new ModelAndView("qk.el.TestEL.d");
				}
			}
		}
		return new ModelAndView("qk.Login.d");
	}

}
