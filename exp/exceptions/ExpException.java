/*
 * ExpException.java
 *
 * Created on 20 mars 2002, 12:55
 */

package exp.exceptions;

/**
 *
 * @author  pfares
 * @version 
 */
public class ExpException extends java.lang.Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = 3475669527393602505L;


	/**
     * Creates new <code>ExpException</code> without detail message.
     */
    public ExpException() {
    }


    /**
     * Constructs an <code>ExpException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public ExpException(String msg) {
        super(msg);
    }
}


