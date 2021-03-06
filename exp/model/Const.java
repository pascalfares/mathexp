
/**
 * Titre : Expression Arithmétiques<p>
 * Description : Madélisation arborescente d'exspressions arithmétique<p>
 * Copyright : Copyright (c) Pascal Farés<p>
 * Société : ISAE<p>
 * @author Pascal Farès
 * @version 1.0
 */
package exp.model;
import exp.langage.Environnement;
import exp.interprete.NaopTokenizer.Token;
/**
 * Les constantes entières (seulement celle ci dans v1.0)
 */
public class Const extends Exp {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -8209718920751882390L;
	/** Valeur de la constante */    
    private Double valeur;
    /** Constructeur de constante */  
    
    public Const(Environnement e) {
        super(e);
        //laClasse = java.lang.ClassLoader.getSystemClassLoader().loadClass("Double");
        //laClasse.getConstructor(double.class);
    }
    
    /** Constructeur de constante
     * @param n La valeur connue
     */    
    public Const(Environnement e,Double n) {
        super(e);
        setValeur(n);
    }
    
    /** accesseur de l'attribut valeur */    
    public Double getValeur() {
        return valeur;
    }
    /** L'evaluation:
     * Const reste inchangé
     */    
    public Exp eval() {
        return this;
    }
    public Exp getExp() {
        return this;
    }
    
    /** représentation texte */    
    @Override
    public String toString() {
        return "C:"+getValeur();
    }
    public static Exp parse(exp.interprete.NaopTokenizer mt, exp.langage.Environnement ts)
    throws exp.exceptions.ConstException {
        if (mt.getTok() == Token.CONST) {
            Const c = new Const(ts);
            c.setValeur(mt.nval);
            mt.nToken(); //skip la constante
            return c;
        }
        else {
            throw (new exp.exceptions.ConstException("Constante Attendue"));
        }
    }

    public void setValeur(Double valeur) {
        this.valeur = valeur;
    }
}
