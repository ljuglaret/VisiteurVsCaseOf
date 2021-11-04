package io.github.ljuglaret.visiteur;

public class Puissance implements ExpressionAVisiter{

    private ExpressionAVisiter expr1;
    private double n;
    

    public Puissance(ExpressionAVisiter expr1 , double n){
        this.expr1 = expr1;
        this.n = n;
    }

    public <R> R accept (Visiteur<R> visiteur ){
        return visiteur.visitPuissance(this);
    }
    
    public ExpressionAVisiter getExpr1(){
        return expr1;
    }
    
    public double getN(){
        return n;
    }
 
   
}