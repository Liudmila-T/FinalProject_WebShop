package ua.nure.timoshenko.summaryTask4.db.entity;

import java.io.Serializable;

/**
 * Root of all entities which have identifier field.
 *
 * @author L.Timoshenko
 */
public abstract class Entity implements Serializable {

	private static final long serialVersionUID = 3921041613524098343L;

	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
