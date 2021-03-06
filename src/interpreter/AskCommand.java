package interpreter;

import java.util.ArrayList;
import java.util.Map;

/**
 * Executes "ask" command, having turtles with specific ids complete a task
 * Assumes that user will only activate turtles that have previously been made
 * @author Sarahbland
 *
 */
public class AskCommand extends Command {
	Command myIdCommand;
	Command myActionCommand;
	Turtle myAllTurtles;
	CommandTreeBuilder myBuilder;
	Map<String, Double> myVariables;
	protected AskCommand(Command turtleIds, Command actions, Turtle allTurtles,
			Map<String, Double> variables, Map<String, String> userDefCommands, Map<String, Integer> userDefCommandNumArgs) {
		myIdCommand = turtleIds;
		myActionCommand = actions;
		myAllTurtles = allTurtles;
		myBuilder = new CommandTreeBuilder(variables, userDefCommands, userDefCommandNumArgs);
		myVariables = variables;
	}
	@Override
	protected double execute() throws UnidentifiedCommandException{
		String IDString = ((StringCommand) myIdCommand).getString();
		String[] IDs = IDString.split(" ");
		String actionString = ((StringCommand) myActionCommand).getString();
		String[] actions = actionString.split(" ");
		ArrayList<SingleTurtle> tempActiveTurtles = new ArrayList<>();
		for(int k = 0; k<IDs.length; k+=1) {
			if(myVariables.containsKey(IDs[k])){
				IDs[k] = myVariables.get(IDs[k]).toString();
			}
			if(!myAllTurtles.containsTurtleWithID(IDs[k])) {
				IDs[k] = Double.toString(0);
				System.out.println(IDs[k]);
			}
			tempActiveTurtles.add(myAllTurtles.getTurtleWithID(IDs[k]));
		}
		MultipleTurtles tempActive = new MultipleTurtles(tempActiveTurtles);
		double returnVal = -1;
		try {
			returnVal = myBuilder.buildAndExecute(myAllTurtles, tempActive, actions, true);
		}
		catch(Exception e) {
			throw new UnidentifiedCommandException(e.getMessage());
		}
	    return returnVal;
		
	}

}
