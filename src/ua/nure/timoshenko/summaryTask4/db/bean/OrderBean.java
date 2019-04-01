package ua.nure.timoshenko.summaryTask4.db.bean;

import ua.nure.timoshenko.summaryTask4.db.entity.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Provide records for virtual table:
 *
 * <pre>
 * |status.name|
 * </pre>
 *
 * @author L.Timoshenko
 *
 */
public class OrderBean extends Entity {

	private static final long serialVersionUID = -3481961856143858589L;

	private String statusOrder;

	private Date date;

	private int amount;


	public String getStatusOrder() {
		return statusOrder;
	}

	public void setStatusOrder(String statusOrder) {
		this.statusOrder = statusOrder;
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
		return "OrderBean{" +
				"statusOrder='" + statusOrder + '\'' +
				", date=" + date +
				", amount=" + amount +
				'}';
	}
}
