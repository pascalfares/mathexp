/*
 * VarException.java
 *
 * Created on 20 mars 2002, 12:56
 */

package exp.exceptions;

/**
 *
 * @author pfares
 * @version $Log: ConstException.java,v $
 * @version Revision 1.1  2006/01/27 09:19:58  pascalfares
 * @version First modification
 * @version
 * @version Revision 1.1.1.1  2005/01/31 09:32:32  pfares
 * @version Init
 * @version
 * @version Revision 1.1.1.1  2005/01/29 23:44:38  pfares
 * @version NAOP
 * @version
 * @version Revision 1.1.1.1  2004/01/19 07:32:56  pfares
 * @version
 * @version
 * @version Revision 1.2  2002/05/20 11:10:13  pfares
 * @version no message
 * @version
 * Revision 1.1.1.2  2002/04/11 09:03:06  pfares
 * 
 */
public class ConstException extends ExpException{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1172236414260564716L;
	/** 
     * Creates new VarException 
     */
    public ConstException() {
    }
    /**
     *
     **/
    public ConstException(String n) {
        super(n);
    }
    
}
