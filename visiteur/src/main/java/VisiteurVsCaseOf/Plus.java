package io.github.ljuglaret.visiteur;

public class Plus implements Expr{

    private Expr expr1;
    private Expr expr2;
    

    public Plus(Expr expr1 , Expr expr2){
        this.expr1 = expr1;
        this.expr2 = expr2;
    }

    public <R> R accept (Visiteur<R> visiteur ){
        return visiteur.visitPlus(this);
    }
    
    public Expr getExpr1(){
        return expr1;
    }
    
    public Expr getExpr2(){
        return expr2;
    }
}