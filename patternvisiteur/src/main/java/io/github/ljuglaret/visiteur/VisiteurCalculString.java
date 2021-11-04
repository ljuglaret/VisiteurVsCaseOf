package io.github.ljuglaret.visiteur;

public class VisiteurCalculString  implements Visiteur<String> {


    public String  visitConstante(Constante constante){
        return String.valueOf(constante.getValeur());
    }

    public String visitProd(Prod prod){
        String evalExpr1 =  prod.getExpr1().accept(this);
        String evalExpr2 =  prod.getExpr2().accept(this);
        return "(" +  evalExpr1 + "*" + evalExpr2 +")";
    }
    public String visitMoins(Moins moins){
        String evalExpr1 =  moins.getExpr1().accept(this);
        String evalExpr2 =  moins.getExpr2().accept(this);
        return "(" +  evalExpr1 + "-" + evalExpr2 +")";
    }
    public String visitPlus(Plus plus){
        String evalExpr1 =  plus.getExpr1().accept(this);
        String evalExpr2 =  plus.getExpr2().accept(this);
        return "(" +  evalExpr1 + "+" + evalExpr2 +")";
    }
    public String visitPuissance(Puissance puissance){
        String evalExpr1 =  puissance.getExpr1().accept(this);
        double n =  puissance.getN();
        return "(" +  evalExpr1 + "^" + n +")";
    }
}
