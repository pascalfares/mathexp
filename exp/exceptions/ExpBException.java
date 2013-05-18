/*
 * VarException.java
 *
 * Created on 20 mars 2002, 12:56
 */

package exp.exceptions;

/**
 *
 * @author  pfares
 * $Log
 */
public class ExpBException extends ExpException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1739050500143556533L;
	/** Creates new ExpBException */
    public ExpBException() {
    }
    /**concructeur
     */
    public ExpBException(String n) {
        super(n);
    }

}
