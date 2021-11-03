package io.github.ljuglaret.visiteur;


public interface Expr {
    
    public  <R> R accept(Visiteur<R> e1);
    
}
