package io.github.ljuglaret.visiteur;

public class Plus implements ExpressionAVisiter{

    private ExpressionAVisiter expr1;
    private ExpressionAVisiter expr2;
    

    public Plus(ExpressionAVisiter expr1 , ExpressionAVisiter expr2){
        this.expr1 = expr1;
        this.expr2 = expr2;
    }

    public <R> R accept (Visiteur<R> visiteur ){
        return visiteur.visitPlus(this);
    }
    
    public ExpressionAVisiter getExpr1(){
        return expr1;
    }
    
    public ExpressionAVisiter getExpr2(){
        return expr2;
    }
}