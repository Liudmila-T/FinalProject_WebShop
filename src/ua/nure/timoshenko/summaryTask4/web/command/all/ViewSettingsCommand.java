package ua.nure.timoshenko.summaryTask4.web.command.all;
import org.apache.log4j.Logger;
import ua.nure.timoshenko.summaryTask4.Path;
import ua.nure.timoshenko.summaryTask4.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * View settings command.
 *
 * @author L.Timoshenko
 *
 */
public class ViewSettingsCommand extends Command {
    private static final long serialVersionUID = 130572827745744414L;

    private static final Logger LOG = Logger
            .getLogger(ViewSettingsCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)  {
        LOG.debug("Command starts");

        LOG.debug("Command finished");
        return Path.PAGE_SETTINGS;
    }

}
