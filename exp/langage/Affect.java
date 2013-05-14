/*
 * Affect.java
 *
 * Created on 24 janvier 2004, 13:16
 */

package exp.langage;

import exp.exceptions.LangageMathException;
import exp.exceptions.ExpException;
import exp.model.Exp;
import static exp.interprete.NaopTokenizer.Token;
/**
 * 
 * @author  pfares
 *
 */
public class Affect extends Instruction {
    
    /** Creates a new instance of Affect */
    public Affect() {
    }
    public static Exp parse(exp.interprete.NaopTokenizer mt,Environnement ts)
    throws LangageMathException {
        try {
            if (mt.getTok() == Token.SYMBOL) {
                //Var v = (Var) Exp.parse(mt, ts);
                String n=new String(mt.sval);
                //System.out.println("AFF1"+ts.cherche(n));
                mt.nToken();
                
                if (mt.getTok() == Token.AFFECT) {
                    mt.nToken(); //skip =
                    Exp e=Exp.parse(mt, ts);
                    // inserer la variable dans la table des symbol
                    //System.out.println("exp de l'affect est "+e+"getExp"+e.getExp());
                    ts.bind(n, e);
                    //System.out.println("AFF1"+ts.cherche(n));
                    //System.out.println(v);
                    return e;
                } else {
                    throw new LangageMathException("pas de =") ;
                }
            } else return null;
        } catch (ExpException e) {
            System.out.println("Dans une affectation "+e);
        }
        return null;
        
    }
    
}
