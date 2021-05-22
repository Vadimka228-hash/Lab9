package bsu.rfe.group8.Gulakov.varC3.servlet;

import bsu.rfe.group8.Gulakov.varC3.entity.Ad;
import bsu.rfe.group8.Gulakov.varC3.entity.AdList;
import bsu.rfe.group8.Gulakov.varC3.entity.UserList;
import bsu.rfe.group8.Gulakov.varC3.helper.AdListHelper;
import bsu.rfe.group8.Gulakov.varC3.helper.UserListHelper;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;

public class StartupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		UserList userList =	UserListHelper.readUserList(getServletContext());
		getServletContext().setAttribute("users", userList);
		AdList adList = AdListHelper.readAdList(getServletContext());
		getServletContext().setAttribute("ads", adList);
		for (Ad ad: adList.getAds()) {
			ad.setAuthor(userList.findUser(ad.getAuthorId()));
			ad.setLastModified(ad.getLastModified());
		}
	}
}