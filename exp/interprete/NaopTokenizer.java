/**
 * Titre : <p>NAOP</p>
 * Description : The NAOP interpreter<p>
 * Copyright : Copyright (c) Pascal Farès<p>
 * <p>ISAE</p>
 * @author Pascal Farès
 * @version $*
 */
package exp.interprete;
import java.io.*;
import static exp.interprete.NaopTokenizer.Token;
/**
 * The special Tokenizer for the NAOP Language
 * @see java.io.StreamTokenizer
 */
public class NaopTokenizer extends StreamTokenizer implements Serializable {
    /** the set of keywords
     */
    public static enum Token {
        /**
         * (
         */
        PO,
        /**
         * )
         */
        PF,
        /**
         * +
         */
        PLUS,
        /**
         * -
         */
        MOINS,
        /**
         * *
         */
        MULT,
        /**
         * /
         */
        DIV,
        /**
         * a valid java symbol
         */
        SYMBOL, //une variable (symbole)
        /**
         * A double
         */
        CONST,
        /**
         * end instruction ;
         */
        FININST, //séparateur ;
        /** Quiter
         */
        QUIT,
        /**
         * =
         */
        AFFECT,
        /**
         * !
         */
        EVAL,
        /**
         * show
         */
        SHOW,
        
        /**
         * nop
         */
        MAX,

        NOP
                
    }
    
    Token  tok=Token.NOP;
    /**
     * Tokenizer from a String
     * @param is The input String
     */
    public NaopTokenizer(String is) {
        super(new BufferedReader(new StringReader(is)));
        this.slashStarComments(true);
        this.ordinaryChar('"');
    }
    /**
     * Tokenizer from an InputStream
     * @param is The input Stream
     */
    public NaopTokenizer(InputStream is) {
        super(new BufferedReader(new InputStreamReader(is)));
        this.slashStarComments(true);
        this.ordinaryChar('"');
    }
    
    /**
     * The nextToken for NAOP language
     * @return the token type. Token is enum
     */
    public Token nToken() {
        tok=Token.NOP;
        
        try {
            switch (super.nextToken()) {
                case TT_WORD :
                    if (sval.equals("quit")) return (tok=Token.QUIT);
                else if(sval.equals("show")) return (tok=Token.SHOW);
                else if(sval.equals("max")) return (tok=Token.MAX);
                else return (tok=Token.SYMBOL);
                case TT_NUMBER : return (tok=Token.CONST) ;
                default :
                    if (ttype == '+') return (tok=Token.PLUS);
                    else if (ttype=='-') return (tok=Token.MOINS);
                    else if (ttype=='*') return (tok=Token.MULT);
                    else if (ttype==':') return (tok=Token.DIV);
                    else if (ttype=='(') return (tok=Token.PO);
                    else if (ttype==')') return (tok=Token.PF);
                 
                    else if (ttype==';') return (tok=Token.FININST);
                    else if (ttype=='=') return (tok=Token.AFFECT);
                    else if (ttype=='!') return (tok=Token.EVAL);
                  
                    else return tok=Token.NOP;
            }
        } catch (IOException e) {System.out.println(e); }
        return (tok);
    }
    /**
     * The last Token
     * @return the token type
     */
    public Token getTok() {return tok;}
    /**
     * Pretty print the current token
     * @return the string pretty print
     */
    @Override
    public String toString() {
        switch (tok) {
            case PO:    return ("PO"); //parenth�se ouvrante
            case PF:    return ("PF"); //parenth�se fermante
            case PLUS:  return("PLUS");
            case MOINS: return ("MOINS");
            case MULT:  return ("MULT");
            case DIV:   return ("DIV");
            case SYMBOL:   return ("VAR:"+sval); //une variable (simbole)
            case CONST: return("CONST:"+nval);
            case FININST:return("SEP"); //s�parateur
            case QUIT:  return("QUIT");
            case NOP:   return("NOP?");
            case AFFECT:return("AFFECT");
            case EVAL:  return("EVAL");
            default: return("Big problème!");
        }
    }
    
    
}
