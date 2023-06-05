package parkingsystem;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ParkingService {
  private ParkingOffice office;
  private Map<String, Command> commands;

  // Parking Service Constructor
  public ParkingService(ParkingOffice office) {
    this.office = office;
    this.commands = new HashMap<String, Command>();
    register(new RegisterCarCommand(this.office));
    register(new RegisterCustomerCommand(this.office));
  }

  // Perform parking commands based on text strings
  public String performCommands(String command, String[] parameters) throws Exception {
    if (commands.containsKey(command)) {
      Command cmd = commands.get(command);

      Properties props = new Properties();
      for (String param : parameters) {
        String[] paramSplit = param.split("=");
        if (paramSplit.length == 2) {
          props.put(paramSplit[0], paramSplit[1]);
        }
      }
      return cmd.execute(props);
    }
    return null;
  }

  // Add a new command to the service
  private void register(Command command) {
    commands.put(command.getCommandName(), command);
  }

  public Map<String, Command> getCommands() {
    return this.commands;
  }
}
