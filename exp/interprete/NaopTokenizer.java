/**
 * Titre :
 * NAOP
 * Description : The NAOP interpreter
 * Copyright : Copyright (c) Pascal Farès
 * ISAE
 *
 * @author Pascal Farès
 */
package exp.interprete;

import java.io.*;

/**
 * The special Tokenizer for the NAOP Language
 *
 * @see java.io.StreamTokenizer
 */
public class NaopTokenizer extends StreamTokenizer implements Serializable {

    /**
     * As a Serilisable the id of the StreamTokenizer
     */
    private static final long serialVersionUID = -6804357541970450515L;

    /**
     * The set of reserved keyword for the mathexp language
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
         * a valid java like symbol
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
        /**
         * Quiter
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
         * Max operator
         */
        MAX,
        /**
         * nop No opération
         */
        NOP

    }

    Token tok = Token.NOP;

    /**
     * Tokenizer from a String
     *
     * @param is The input String
     */
    public NaopTokenizer(String is) {
        super(new BufferedReader(new StringReader(is)));
        super.slashStarComments(true);
        super.ordinaryChar('"');
    }

    /**
     * Tokenizer from an InputStream
     *
     * @param is The input Stream
     */
    public NaopTokenizer(InputStream is) {
        super(new BufferedReader(new InputStreamReader(is)));
        super.slashStarComments(true);
        super.ordinaryChar('"');
    }

    /**
     * The nextToken for MathExp language
     *
     * @return the token type. Token is enum
     * @see StreamTokenizer
     */
    public Token nToken() {
        tok = Token.NOP;

        try {
            switch (super.nextToken()) {
                case TT_WORD:
                    switch (sval) {
                        case "quit":
                            return (tok = Token.QUIT);
                        case "show":
                            return (tok = Token.SHOW);
                        case "max":
                            return (tok = Token.MAX);
                        default:
                            return (tok = Token.SYMBOL);
                    }
                case TT_NUMBER:
                    return (tok = Token.CONST);
                default:
                    switch (ttype) {
                        case '+':
                            return (tok = Token.PLUS);
                        case '-':
                            return (tok = Token.MOINS);
                        case '*':
                            return (tok = Token.MULT);
                        case ':':
                            return (tok = Token.DIV);
                        case '(':
                            return (tok = Token.PO);
                        case ')':
                            return (tok = Token.PF);
                        case ';':
                            return (tok = Token.FININST);
                        case '=':
                            return (tok = Token.AFFECT);
                        case '!':
                            return (tok = Token.EVAL);
                        default:
                            return tok = Token.NOP;
                    }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return (tok);
    }

    /**
     * The last Token, the current token
     *
     * @return the token type
     */
    public Token getTok() {
        return tok;
    }

    /**
     * Pretty print the current token
     *
     * @return the string pretty print
     */
    @Override
    public String toString() {
        switch (tok) {
            case PO:
                return ("PO"); //parenthèse ouvrante
            case PF:
                return ("PF"); //parenthèse fermante
            case PLUS:
                return ("PLUS");
            case MOINS:
                return ("MOINS");
            case MULT:
                return ("MULT");
            case DIV:
                return ("DIV");
            case SYMBOL:
                return ("VAR:" + sval); //une variable (symbole)
            case CONST:
                return ("CONST:" + nval);
            case FININST:
                return ("SEP");
            case QUIT:
                return ("QUIT");
            case NOP:
                return ("NOP?");
            case AFFECT:
                return ("AFFECT");
            case EVAL:
                return ("EVAL");
            case MAX:
                return ("MAX");
            default:
                return ("Big problème! ;)");
        }
    }

}
