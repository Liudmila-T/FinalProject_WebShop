package ua.nure.timoshenko.summaryTask4.web.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * Main interface for the Command pattern implementation.
 *
 * @author L.Timoshenko
 *
 */
public abstract class Command implements Serializable {

	private static final long serialVersionUID = 5767263539806311780L;

	/**
	 * Execution method for command.
	 *
	 * @return Address to go once the command is executed.
	 */
	public abstract String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException;

	@Override
	public final String toString() {
		return getClass().getSimpleName();
	}
}
