
/**
 * Titre : Expression Arithm�tiques<p>
 * Description : Mad�lisation arborescente d'exspressions arithm�tique<p>
 * Copyright : Copyright (c) Pascal Far�s<p>
 * Soci�t� : ISAE<p>
 * @author Pascal Far�s
 * @version 1.0
 */
package exp.model;

import exp.model.Operateur;
import exp.langage.Environnement;
import exp.interprete.NaopTokenizer.Token;
import exp.exceptions.ExpBException;
import exp.exceptions.ExpException;

public class ExpB extends Exp {
    int op;
    Exp expg;
    Exp expd;
    
    public ExpB(Environnement e){
        super(e);
    }
    public ExpB(Environnement e,int o, Exp eg, Exp ed) {
        super(e);
        op=o;expg=eg;expd=ed;
    }
    public Exp getExp() {
        return this;
    }
    public Exp previousExp() {
        return this;
    }
    public Exp plus(Exp e1, Exp e2) {
        if ((e1 instanceof Const) && (e2 instanceof Const)) {
            return new Const(getEnv(),((Const)e1).getValeur()+((Const)e2).getValeur());
        }
        else return new ExpB(getEnv(),Operateur.PLUS, e1,e2);
    }
    public Exp moins(Exp e1, Exp e2) {
        if ((e1 instanceof Const) && (e2 instanceof Const)) {
            return new Const(getEnv(),((Const)e1).getValeur()-((Const)e2).getValeur());
        }
        else return new ExpB(getEnv(),Operateur.MOINS, e1,e2);
    }
    public Exp mult(Exp e1, Exp e2) {
        if ((e1 instanceof Const) && (e2 instanceof Const)) {
            return new Const(getEnv(),((Const)e1).getValeur()*((Const)e2).getValeur());
        }
        else return new ExpB(getEnv(),Operateur.MULT, e1,e2);
    }
    public Exp div(Exp e1, Exp e2) {
        if ((e1 instanceof Const) && (e2 instanceof Const)) {
            return new Const(getEnv(),((Const)e1).getValeur()/((Const)e2).getValeur());
        }
        else return new ExpB(getEnv(),Operateur.DIV, e1,e2);
    }
    public Exp max(Exp e1, Exp e2) {
        if ((e1 instanceof Const) && (e2 instanceof Const)) {
            return new Const(getEnv(),java.lang.Math.max(((Const)e1).getValeur(),((Const)e2).getValeur()));
        } else if (e1 instanceof Const) return e1;
        else if (e2 instanceof Const) return e2;
        else return new ExpB(getEnv(),Operateur.MAX, e1,e2);
    }
    /**
     * applique aux expression la s�mantique des op�rateurs
     */
    public Exp eval() {
        //java.lang.reflect.Method m = Exp.class.getClass().getMethod("eval");
        
        //A FAIRE : impl�mentez cette m�thode expression.Exp
        switch (op) {
            
            case Operateur.PLUS : return (plus(expg.eval() , expd.eval()));
            case Operateur.MOINS: return (moins(expg.eval() , expd.eval()));
            case Operateur.MULT:return (mult(expg.eval() ,expd.eval()));
            case Operateur.DIV:return div(expg.eval() , expd.eval());
            case Operateur.MAX:return max(expg.eval() , expd.eval());
            default : return null;
        }
    }
    
    /** représentation texte */    
    @Override
    public String toString() {
        Operateur opl=new Operateur(op);
        return "ExpB:("+expg.toString()+opl.toString()+expd.toString()+")";
    }
    
    private void parseOperateur(exp.interprete.NaopTokenizer mt)
    throws ExpBException {
       
        
        if (mt.getTok()==Token.PLUS) op=Operateur.PLUS;
        else if (mt.getTok()==Token.MULT) op= Operateur.MULT;
        else if (mt.getTok()==Token.MOINS) op= Operateur.MOINS;
        else if (mt.getTok()==Token.DIV) op=Operateur.DIV;
        else if (mt.getTok()==Token.MAX) op=Operateur.MAX;
        else throw new ExpBException("N'est pas un operateur");
        mt.nToken(); //skip l'op�rateur
    }
    /** Le parser par descente récursive et grammaire denotationelle
     * Les attributs:
     * Table symbole : hérité
     * L'objet généré Exp synthétisé
     * @param mt Tokenizer courant
     * @param ts La table de symbole attribue hérité
     * @throws ExpException Les erreur d'interprétation
     * @return Le modèle de l'expression
     */    
    public static Exp parse(exp.interprete.NaopTokenizer mt, Environnement ts)
    throws ExpException {
        Token res;
        //System.out.println(mt);
        if (mt.getTok()==Token.PO) { //c'est bien une expression binaire
            ExpB eb = new ExpB(ts); //Cr�ation d'une expression
            res = mt.nToken(); // skip (
            //Expression gauche qui est soit COnst soit Var soir EXPB
            eb.expg=Exp.parse(mt,ts);
            //il faut maintenant un op�rateur
            //res=mt.nextToken();
            eb.parseOperateur(mt);
            //si on continue => il n'y a pas eu d'exptions
            
            //et une expression droite
            eb.expd=Exp.parse(mt, ts);
            
            //et le tous se termine par une )
            //res = mt.nextToken();
            if (mt.getTok() == Token.PF) {
                mt.nToken(); //skip ) et fin
                //System.out.println("L'expression reconue "+ eb);
                return eb;
            }
            else {
                throw new ExpBException("Pas de parenth�se fermante");
            }
        }
        else {
            return null;
        }
    }
   
   
    
}
