package io.github.ljuglaret.visiteur;

public class VisiteurOperation {

    private Expr expr;

    public VisiteurOperation(Expr expr){
        this.expr = expr;
    }

    public double calcul(){
        Visiteur<Double> v1 = new Visiteur<Double>(){
            public Double  visitConstante(Constante constante){
                return constante.getValeur();
            }
            public Double visitProd(Prod prod){
                Double evalExpr1 =  prod.getExpr1().accept(this);
                Double evalExpr2 =  prod.getExpr2().accept(this);
                return evalExpr1 * evalExpr2;
            }
            public Double visitMoins(Moins moins){
                Double evalExpr1 =  moins.getExpr1().accept(this);
                Double evalExpr2 =  moins.getExpr2().accept(this);
                return evalExpr1 - evalExpr2;
            }
            public Double visitPlus(Plus plus){
                Double evalExpr1 =  plus.getExpr1().accept(this);
                Double evalExpr2 =  plus.getExpr2().accept(this);
                return evalExpr1 + evalExpr2;
            }
            public Double visitPuissance(Puissance puissance){
                Double evalExpr1 =  puissance.getExpr1().accept(this);
                double n =  puissance.getN();
                return Math.pow(evalExpr1 , n);
            }
        }; 
        return expr.accept(v1);
    }
}
