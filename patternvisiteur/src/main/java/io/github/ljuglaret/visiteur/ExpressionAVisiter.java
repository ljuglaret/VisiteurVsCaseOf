package io.github.ljuglaret.visiteur;


public interface ExpressionAVisiter {
    
    public  <R> R accept(Visiteur<R> e1);
    
}
