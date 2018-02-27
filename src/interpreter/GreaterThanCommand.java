package interpreter;

/**
 * returns 1 if the value of expr1 is strictly greater than the value of expr2, otherwise 0
 * 
 * @author Benjamin Hodgson
 * @date 2/26/18
 *
 */
public class GreaterThanCommand implements Command{
    	private final double TRUE = 1;
    	private final double FALSE = 0;
    	double EXPR1;
    	double EXPR2;
	protected GreaterThanCommand(Command expr1, Command expr2) {
		EXPR1 = expr1.execute();
		EXPR2 = expr2.execute();
	}
	@Override
	public double execute() {
		if (EXPR1 > EXPR2) {
		    return TRUE;
		}
		else {
		    return FALSE;
		}
	}
	public int getNumArgs() {
		return 2;
	}
}
