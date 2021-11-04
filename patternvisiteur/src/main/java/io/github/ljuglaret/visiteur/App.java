package io.github.ljuglaret.visiteur;

public class App {
    public static void main(String[] args) {
        ExpressionAVisiter un     =   new Constante(1);
        ExpressionAVisiter cinq   =   new Constante(5);
        ExpressionAVisiter quatre =   new Constante(4);
        
        //(4*(2^3 - 1)) + 5 
        ExpressionAVisiter e1 = new Plus(new Prod(quatre,new Moins(new Puissance(new Constante(2),3),un)),cinq);
    
        System.out.println("l'évaluation de : " 
                            + e1.accept(new VisiteurCalculString()) 
                            + " est " + e1.accept(new VisiteurCalculReel()));

        //ExpressionAVisiter e2 = new Constante([1,2,3])
    }
}
