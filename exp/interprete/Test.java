/*
 * Test.java
 *
 * Created on 24 janvier 2004, 01:02
 */
package exp.interprete;

import java.io.*;

/**
 * Programme de test utilisant Le parser du langage mathématique. Ce processus
 * Lit le clavier et envoie la chaine au Tokenizer (Utilisation d'un tube entre
 * 2 threads)
 *
 * @author Pascal Far�s ISAE
 * @version $@
 *
 * @see PipedInputStream PipedOutputStream
 */
public class Test extends Thread {

    /**
     * Flux d'ent� pour l'interpr�teur
     */
    BufferedReader in;
    /**
     * Envoi de l'entr�e vers le flux du toenizer
     */
    PrintWriter out;

    /**
     * Construteur Test
     *
     * @param in Stream d'entr�e de l'interpr�teur
     * @param out Stream de sortie vers le tokenizer, en g�n�ral un tube
     */
    public Test(InputStream in, OutputStream out) {
        this.in = new java.io.BufferedReader(new InputStreamReader(in));
        this.out = new java.io.PrintWriter(out);

    }

    /**
     * Cr�ation du tube pipeout-->pipein Cr�ation du tokenizer sur pipein
     * Lancement des 2 threads (Interpr�teur/Parseur du langage math�matique) Le
     * dialogue entre les deux thread est r�alis� au moyen du tube
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {

        PipedInputStream pi = new PipedInputStream();
        PipedOutputStream po = new PipedOutputStream();

        try {
            po.connect(pi);
        } catch (java.io.IOException e) {
            System.out.println(e);
        }
        Test t = new Test(System.in, po);

        //Lancement du thread
        t.start();


        exp.langage.Langage l = exp.langage.Langage.parse(pi);

        /**/
        /*
         * 
         Environnement ts= new Environnement();
         NaopTokenizer tokenizer = new NaopTokenizer("p1 = (x +5)");
         tokenizer.nToken();
         Instruction.parse(tokenizer,ts);

         tokenizer = new NaopTokenizer("x=2");
         tokenizer.nToken();
         Instruction.parse(tokenizer,ts);

         tokenizer = new NaopTokenizer("!p1");
         tokenizer.nToken();
         Exp r=Instruction.parse(tokenizer, ts);

         System.out.println(r);

         tokenizer = new NaopTokenizer("x=5");
         tokenizer.nToken();
         Instruction.parse(tokenizer,ts);

         tokenizer = new NaopTokenizer("!p1");
         tokenizer.nToken();
         r=Instruction.parse(tokenizer, ts);

         System.out.println(r);
         */
    }

    /**
     * La boucle de l'interpr�teur
     *
     * <PRE>while (true) {
     *   Afficher le prompt
     *   Lire le clavier
     *   Envoyer les entr�e vers le Tokenizer
     * }
     * </PRE>
     */
    public void run() {
        String s;
        System.out.print("$> ");
        System.out.flush();
        try {
            while ((s = this.in.readLine()) != null) {

                this.out.println(s);
                this.out.flush();

                System.out.print("$> ");
                System.out.flush();
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
