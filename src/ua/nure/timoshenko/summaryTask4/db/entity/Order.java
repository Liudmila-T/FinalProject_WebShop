package ua.nure.timoshenko.summaryTask4.db.entity;

import java.util.Date;

/**
 * Order entity.
 *
 * @author L.Timoshenko
 *
 */
public class Order extends Entity {

	private static final long serialVersionUID = -1319478949818814841L;

	private int userID;

	private int statusID;

	private Date date;

	private int amount;

	public int getStatusID() {
		return statusID;
	}

	public void setStatusID(int statusID) {
		this.statusID = statusID;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Order{" +
				"userID=" + userID +
				", statusID=" + statusID +
				", date=" + date +
				", amount=" + amount +
				'}';
	}
}

