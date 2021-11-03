package io.github.ljuglaret.visiteur;

public interface Visiteur<R>{
        public R visitConstante(Constante constante);
        public R visitProd(Prod prod);
        public R visitMoins(Moins moins);
        public R visitPlus(Plus plus);       
        public R visitPuissance(Puissance puissance);
}
