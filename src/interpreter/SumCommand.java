package interpreter;


import java.util.Map;

/**
 * returns sum of the values of expr1 and expr2
 * 
 * @author Benjamin Hodgson
 * @date 2/26/18
 *
 */
class SumCommand extends Command{

    private Command expr1Command;
    private Command expr2Command;
    private Map<String, Double> myVariables; 

    protected SumCommand(Command expr1, Command expr2 ,Map<String, Double> variables, Turtle turtles) {
    		setActiveTurtles(turtles);
    		expr1Command = expr1;
    		expr2Command = expr2;
    		myVariables = variables;
    }
    @Override
    protected double execute() throws UnidentifiedCommandException {
    	double EXPR1Ret = getCommandValue(expr1Command, myVariables, getActiveTurtles().toSingleTurtle());
    	double EXPR2Ret = getCommandValue(expr2Command, myVariables, getActiveTurtles().toSingleTurtle());
    getActiveTurtles().executeSequentially(myTurtle -> {
    		try {
    		getCommandValue(expr1Command, myVariables, myTurtle);
    		getCommandValue(expr2Command, myVariables, myTurtle);
    		}
    		catch(UnidentifiedCommandException e) {
				throw new UnidentifiedCommandError("Improper # arguments");
			}
    	});
	return EXPR1Ret + EXPR2Ret;
    }
}
