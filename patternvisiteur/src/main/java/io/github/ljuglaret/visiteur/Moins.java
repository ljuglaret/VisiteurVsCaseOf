package io.github.ljuglaret.visiteur;

public class Moins implements ExpressionAVisiter{

    private ExpressionAVisiter expr1;
    private ExpressionAVisiter expr2;

    public Moins(ExpressionAVisiter expr1 , ExpressionAVisiter expr2){
        this.expr1 = expr1;
        this.expr2 = expr2;
    }

    public <R> R accept (Visiteur<R> visiteur ){
        return visiteur.visitMoins(this);
    }

    public ExpressionAVisiter getExpr1(){
        return expr1;
    }

    public ExpressionAVisiter getExpr2(){
        return expr2;
    }

}