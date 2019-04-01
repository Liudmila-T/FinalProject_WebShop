package ua.nure.timoshenko.summaryTask4.tag;

import ua.nure.timoshenko.summaryTask4.db.entity.User;

/**
 * User function/
 *
 * @author L.Timoshenko
 *
 */
public class UserFunction {

	/**
	 * @param user
	 *
	 * @return String
	 * 				name with role of the user
	 */
	public static String nameWithRole(User user) {
		String role = "";
		if(user.getRoleId() == 0){
			role = "admin";
		} else {
			if(user.getRoleId() == 1){
				role = "client";
			} else {
				role = "";
			}
		}
		return user.getName() + " (" + role + ")";
	}
}