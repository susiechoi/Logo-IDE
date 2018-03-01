package interpreter;

import java.util.Map;

/**
 * returns 1 if the value of expr1 is strictly less than the value of expr2, otherwise 0
 * 
 * @author Benjamin Hodgson
 * @author Susie Choi 
 * @date 2/26/18
 *
 */
class LessThanCommand extends Command{

    private Command expr1Command;
    private Command expr2Command;
    private Map<String, Double> myVariables; 

    protected LessThanCommand(Command expr1, Command expr2, Map<String, Double> variables) {
	expr1Command = expr1;
	expr2Command = expr2;
	myVariables = variables; 
    }

    @Override
    protected double execute() throws UnidentifiedCommandException{
	double arg1Val= getCommandValue(expr1Command, myVariables); 
	double arg2Val= getCommandValue(expr2Command, myVariables); 
	return (arg1Val < arg2Val) ? 1.0 : 0.0; 
    }

}
