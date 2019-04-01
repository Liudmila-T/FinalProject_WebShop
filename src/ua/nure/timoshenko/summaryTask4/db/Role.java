package ua.nure.timoshenko.summaryTask4.db;

import ua.nure.timoshenko.summaryTask4.db.entity.User;

/**
 * Role entity.
 *
 * @author L.Timoshenko
 */

public enum Role {
	ADMIN, CLIENT,BLOCKED_USER;

	public static Role getRole(User user) {
		int roleId = user.getRoleId();
		return Role.values()[roleId];
	}


	public String getName() {
		return name().toLowerCase();
	}

}
