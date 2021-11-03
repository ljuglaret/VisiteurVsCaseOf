
package io.github.ljuglaret.visiteur;

public class App {


    public static void main(String[] args) {
        Expr un     =   new Constante(1);
        Expr cinq   =   new Constante(5);
        Expr quatre =   new Constante(4);
        
        //(4*(2^3 - 1)) + 5 
        Expr e1 = new Plus(new Prod(quatre,new Moins(new Puissance(new Constante(2),new Constante(3)),un)),cinq);
        Eval eval = new Eval(e1); 
        System.out.println(eval.calcul());
    }
}
