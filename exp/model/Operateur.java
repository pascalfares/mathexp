
/**
 * Titre : Expression Arithm�tiques<p>
 * Description : Mad�lisation arborescente d'exspressions arithm�tique<p>
 * Copyright : Copyright (c) Pascal Far�s<p>
 * Soci�t� : ISAE<p>
 * @author Pascal Far�s
 * @version 1.0
 */
package exp.model;


import java.io.Serializable;
import exp.interprete.NaopTokenizer;
import exp.interprete.NaopTokenizer.Token;
/**
 * L'op�rateur l'une des op�ration arithm�tique
 * il y en a 4 pour l'instant
 * vous pouver y en ajouter d'autre!
 */

public class Operateur implements Serializable {
  /**
	 * 
	 */
	private static final long serialVersionUID = 2193796257751828430L;
public static final int PLUS =0;
  public static final int MOINS=1;
  public static final int MULT=2;
  public static final int DIV=3;
  public static final int MAX=4;

  int op;
  public Operateur(int o) {
    op = o;
  }
  /**
   * Parser
   */
  public static Operateur parse(NaopTokenizer mt) 
  throws exp.exceptions.OperateurException{
      if (mt.getTok() == Token.PLUS) {mt.nToken(); return new Operateur(PLUS);}
      if (mt.getTok() == Token.MOINS) {mt.nToken(); return new Operateur(MOINS);}
      if (mt.getTok() == Token.MULT) {mt.nToken(); return new Operateur(MULT);}
      if (mt.getTok() == Token.DIV) {mt.nToken(); return new Operateur(DIV);}
      if (mt.getTok() == Token.MAX) {mt.nToken(); return new Operateur(MAX);}
      throw (new exp.exceptions.OperateurException());
     }
       public Object javaObject() {
        return this;
    }
  /**
   * afficher en clair l'op�ration
   */
  public int getOp() { return op;}
  public String toString() {
    switch (op) {
      case PLUS : return "+";
      case MOINS : return "-";
      case MULT : return "*";
      case DIV : return "/";
        case MAX : return "max";
      default : return "Un serieux problème";
    }
  }
}