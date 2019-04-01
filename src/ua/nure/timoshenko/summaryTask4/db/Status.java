package ua.nure.timoshenko.summaryTask4.db;

import ua.nure.timoshenko.summaryTask4.db.entity.Order;

/**
 * Status order.
 *
 * @author L.Timoshenko
 *
 */
public enum Status {
	REGISTERED, PAID, CANCELED;

	public static Status getStatus(Order order) {
		return Status.values()[order.getStatusID()];
	}
	public static String getStatus(int statusId) {
		return String.valueOf(Status.values()[statusId]).toLowerCase();
	}


	/**
	 * @return String
	 * of the status
	 */
	public String getName() {
		return name().toLowerCase();
	}
}