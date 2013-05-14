/*
 * Langage.java
 *
 * Created on 23 janvier 2004, 22:01
 */

package exp.langage;
import exp.interprete.NaopTokenizer;
import exp.interprete.NaopTokenizer.Token;
/**
 * The interpreter: Implement the loop for parsing instructions
 * it is a Thread that loop until the quit instruction
 * I choose to make it a thread to separate the input method from the
 * parsing
 * @see exp.interprete.Test
 * @author  pfares
 */
public class Langage extends Thread{
    //L est composé d'un ensemble de LE
    //des affectations
    /**
     *ts : est l'environnement dynamique
     *     attribut hérité de L vers LE
     */
    private Environnement ts;
    NaopTokenizer mt;
    
    /** Creates a new instance of Langage */
    public Langage(java.io.InputStream in) {
        mt=new NaopTokenizer(in);
        ts=new Environnement();
    }
    public void run() {
        mt.nToken();
        
        while ((mt.getTok()) != NaopTokenizer.Token.QUIT) {
            try {
                System.out.println(Instruction.parse(mt,ts));
                if (mt.getTok() != Token.FININST) {
                    throw new exp.exceptions.LangageMathException("erreur!! fin inst") ;
                }
                else {    
                    mt.nToken();
                }
            }
            catch (exp.exceptions.LangageMathException e) {
                System.out.println(e);
                mt.nToken();
            }
            catch (exp.exceptions.ExpException ex) {
                System.out.println(ex);
                mt.nToken();
            }
           
        }
        System.exit(0);
       
    }
    public static Langage parse(java.io.InputStream in) {
        Langage l=new Langage(in);
        l.start();
        return l;
    }
    
}
