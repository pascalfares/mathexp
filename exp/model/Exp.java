
/**
 * Title :NAOP<p>
 * Description : Natural AOP<p>
 * Copyright : Copyright (c) Pascal Farès<p>
 * Société : ISAE<p>
 * @author Pascal Farès
 * @version 0.1.1
 */
package exp.model;
import exp.interprete.NaopTokenizer.Token;

/**
 * EXP ::= CONST | VAR | EXPB | PREVIOUS | LAMBDA | CONDITION | BIND
 * EXPB ::= (EXP OP EXP)
 * ExpU ::= OPU EXP
 * OP ::= + | - | * | /
 * OPU ::= #
 */
public abstract class Exp implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -7470376100013887074L;
	/**
     * The envirenment of expressions
     */
    protected exp.langage.Environnement env;
    /**
     * Create a new Expression
     * @param e The envirenment of the expression
     */
    public Exp(exp.langage.Environnement e) {
        env=e;
    }
    /** 
     * abstract method for the semantic of the expression
     * Const => Const
     * Var => Eval definition or unchanged
     * exp op exp si exp1 et exp2 Const réduction à un seul Const
     * @return La réduction par évaluation de l'expression
     */    
    public abstract Exp eval();
    /**
     * abstract method for the definition of the expression
     * @return La definition de l'expression
     */
    public abstract Exp getExp();
    
    /** 
     * the parser for recursive descent and attributed grammar
     * the attributess:
     * symbol table : herited
     * the générated expression : synthetised
     * @param mt Tokenizer courant
     * @param ts La table de symbole attribue hérité
     * @throws ExpException Les erreur d'interprétation
     * @return Le modèle de l'expression
     */    
    public static Exp parse(exp.interprete.NaopTokenizer mt, exp.langage.Environnement  ts)
    throws exp.exceptions.ExpException {
        if (mt.getTok()==Token.PO) return ExpB.parse(mt, ts);  
        else if (mt.getTok() ==Token.CONST) return Const.parse(mt, ts);
        else if (mt.getTok() == Token.SYMBOL) return Var.parse(mt,ts);      
        throw (new exp.exceptions.ExpException("N'est pas un debut d'expression"));
    }

    /**
     * Getter for property env.
     * @return Value of property env.
     */
    public exp.langage.Environnement getEnv() {

        return this.env;
    }

    /**
     * Setter de env
     * @param env nouvelle valeur pour env
     */
    public void setEnv(exp.langage.Environnement env) {

        this.env = env;
    }
}
