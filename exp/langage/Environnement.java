/*
 * TableSymboles.java
 *
 * Created on 19 mars 2002, 13:33
 */

package exp.langage;

/**
 *
 * @author  pfares
 * @version 1.0
 */
import exp.model.Exp;
/**
 * The envirement of expression
 * each symbol is defined by a stack of expressions
 * for example:
 * x=1;  then envirement is {(x,1)}
 * x=2;  the envirenment become {(x,[2,1])}
 * The envirement is define by a hierarchie each new block create a sub-envirenment
 * this class is the kernel of the idea of dynamic binding
 */
public class Environnement implements java.io.Serializable {
   
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1562141500365985155L;
	
	protected java.util.Hashtable<String,Exp> table;
    /** Creates new TableSymboles */
    public Environnement() {
        table = new java.util.Hashtable<String,Exp>();
       
    }
    public Environnement(Environnement p) {
        this();
       
    }
    
    public void bind(String n,Exp e) {
        table.put(n,e.getExp());
    }

    public Exp cherche(String n) {
        Exp res=table.get(n);
       
        return res;
    }
    
    @Override
    public String toString() {
       return table.toString();
    }

  
    
}
