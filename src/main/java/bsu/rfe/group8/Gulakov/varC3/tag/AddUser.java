package bsu.rfe.group8.Gulakov.varC3.tag;

import java.io.IOException;

import bsu.rfe.group8.Gulakov.varC3.entity.User;
import bsu.rfe.group8.Gulakov.varC3.entity.UserList;
import bsu.rfe.group8.Gulakov.varC3.entity.UserList.UserExistsException;
import bsu.rfe.group8.Gulakov.varC3.helper.UserListHelper;
import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.PageContext;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;

public class AddUser extends SimpleTagSupport {
	private User user;

	public void setUser(User user) {
		this.user = user;
	}

	public void doTag() throws JspException, IOException {
		String errorMessage = null;
		UserList userList = (UserList)getJspContext().getAttribute("users", PageContext.APPLICATION_SCOPE);
		if (user.getLogin()==null || user.getLogin().equals("")) {
			errorMessage = "Логин не может быть пустым!";
		} else {
			if (user.getName()==null || user.getName().equals("")) {
				errorMessage = "Имя пользователя не может‚ быть пустым!";
			} else {
				if (user.getName()!=null && !user.getCaptcha().equals(
						getJspContext().getAttribute("captcha",PageContext.SESSION_SCOPE))) {
					errorMessage = "Не верно введена captcha";
				}
			}
		}
		if (errorMessage==null) {
			try {
				userList.addUser(user);
				UserListHelper.saveUserList(userList);
			} catch (UserExistsException e) {
				errorMessage = "Пользователь с таким логином уже существует!";
			}
		}
		getJspContext().setAttribute("errorMessage", errorMessage, PageContext.SESSION_SCOPE);
	}
}