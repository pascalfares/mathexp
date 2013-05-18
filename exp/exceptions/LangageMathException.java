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
public class LangageMathException extends java.lang.Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = 3635921363648340535L;


	/**
     * Creates new <code>ExpException</code> without detail message.
     */
    public LangageMathException() {
    }


    /**
     * Constructs an <code>ExpException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public LangageMathException(String msg) {
        super(msg);
    }
}


