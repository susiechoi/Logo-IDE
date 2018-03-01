package interpreter;

import java.util.Map;

/**
 * returns product of the values of expr1 and expr2
 * 
 * @author Benjamin Hodgson
 * @date 2/26/18
 *
 */
class ProductCommand extends Command{

    private Command expr1Command;
    private Command expr2Command;
    private Map<String, Double> myVariables; 

    protected ProductCommand(Command expr1, Command expr2 ,Map<String, Double> variables) {
	expr1Command = expr1;
	expr2Command = expr2;
	myVariables = variables;
    }
    @Override
    protected double execute() throws UnidentifiedCommandException{
	double EXPR1 = getCommandValue(expr1Command, myVariables);
	double EXPR2 = getCommandValue(expr2Command, myVariables);
	return EXPR1*EXPR2;
    }
}