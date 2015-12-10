/*
 * Var.java
 *
 * Created on 19 mars 2002, 11:51
 */

package exp.model;
import exp.langage.Environnement;
import exp.interprete.NaopTokenizer.Token;
/**
 *
 * @author  pfares
 * @version 2 15 Fevrier 2005
 */

/**
 * Le non terminal var: un symbole
 */
public class Var extends exp.model.Exp {
    /**
	 * 
	 */
	private static final long serialVersionUID = -7425419212327156134L;
	//static List<Var> traverse=new java.util.LinkedList<Var>();;
    /**
     * Le nom du symbole
     */
    String nom;
    /**
     * L'expression definissant la variable
     * @return retourne la définition de la variable (ie L'expression en tête de la pile)
     */
    public Exp getExp() {
        Exp e = env.cherche(nom);
        if (e == null) return this;
        return (e);
    }
   
    /**
     * Constructeur
     * @param e Un environnement
     * @param n Le nom de la variable
     */
    public Var(Environnement e,String n) {
        super(e);
        nom=n;
    }
    
    /** Evalution de l'expression
     * si la variable est défine alors eval definition sinon inchangé
     * @return L'expression résultat de l'évalution
     */
    public Exp eval() {
        Exp e=getExp();
        if (e==this) return this;
        return e.eval();
        
    }
    /**
     * suprimer la dernière definition
     * @return L'expression suprimée
     */
   
    /**
     * ajout en tête de pile d'une definition
     * @param e L'expression de liaison
     */
    public void bind(Exp e) {
        env.bind(nom,e);
    }
    /**
     * Permet de gérer les advices
     * @return La définition suivante dans la pile
     */
   
    /**
     *
     * @return
     */
    
    Exp _eval(Exp e) {
        if (e != null)
            return e.eval();
        else return this;
    }
    /** Affectation (liaison au sens lambda calcul) attribution d'une expression à une varialble
     * Pour AOP et l'opérateur # la liason se rajoute aux liasons précédentes
     * @param e definition de la variable (une liaison dans le contexte)
     */
    public void affect(Exp e) {
        bind(e);
    }
    /**
     * accesseur
     * @return Le nom de la variable
     */
    public String getNom() {
        return nom;
    }
    
    /**
     * Le parser une methode statique qui (si l'expression est bien un Var cr�e
     * l'objet VAR
     * @param mt Le tokenizer
     * @param ts L'environnement de parsing
     * @throws exp.exceptions.VarException Erreur de parsing
     * @return Une nouvelle instance de Var
     */
    public static Exp parse(exp.interprete.NaopTokenizer mt,
            exp.langage.Environnement ts)
            throws exp.exceptions.VarException {
        
        if (mt.getTok() == Token.SYMBOL) {
            String n=new String(mt.sval);
            Var v =  new Var(ts,n);
            mt.nToken();
            return v;
        } else {
            return null;
        }
        
    }
    
    /**
     * représentation texte
     * @return La String
     */
    @Override
    public String toString() {
            Exp e = env.cherche(nom);
            if (e == null) return "Var:"+nom;
            
            String res= "Var:"+nom+"="+e;
            return res;
        
    }
    
    
}
