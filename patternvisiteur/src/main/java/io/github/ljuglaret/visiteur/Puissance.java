package io.github.ljuglaret.visiteur;

public class Puissance implements Expr{

    private Expr expr1;
    private double n;
    

    public Puissance(Expr expr1 , double n){
        this.expr1 = expr1;
        this.n = n;
    }

    public <R> R accept (Visiteur<R> visiteur ){
        return visiteur.visitPuissance(this);
    }
    
    public Expr getExpr1(){
        return expr1;
    }
    
    public double getN(){
        return n;
    }
 
   
}