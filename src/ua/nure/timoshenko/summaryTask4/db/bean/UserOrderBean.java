package ua.nure.timoshenko.summaryTask4.db.bean;

import ua.nure.timoshenko.summaryTask4.db.Status;
import ua.nure.timoshenko.summaryTask4.db.entity.Entity;

import java.util.Date;

/**
 * Provide records for virtual table:
 *
 * <pre>
 * |order.id|user.login|user.name|cart.price|status.name|
 * </pre>
 *
 * @author L.Timoshenko
 *
 */
public class UserOrderBean extends Entity {

	private static final long serialVersionUID = 8069679092818949823L;

	private int orderId;

	private String userLogin;

	private String userName;

	private String statusName;

	private int countOfOrders;

	private int userId;

	private Date date;

	private int amount;


	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getCountOfOrders() {
		return countOfOrders;
	}

	public void setCountOfOrders(int countOfOrders) {
		this.countOfOrders = countOfOrders;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
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
		return "UserOrderBean{" +
				"orderId=" + orderId +
				", userLogin='" + userLogin + '\'' +
				", userName='" + userName + '\'' +
				", statusName='" + statusName + '\'' +
				", countOfOrders=" + countOfOrders +
				", userId=" + userId +
				", date=" + date +
				", amount=" + amount +
				'}';
	}
}
