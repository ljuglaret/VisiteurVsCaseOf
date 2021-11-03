package io.github.ljuglaret.visiteur;

public class Constante implements Expr{
    private double valeur;
    
    public Constante (double valeur){
        this.valeur = valeur;
    }
    public <R> R accept (Visiteur<R> visiteur ){
        return visiteur.visitConstante(this);
    }
    public double getValeur(){
        return valeur;
    }
}