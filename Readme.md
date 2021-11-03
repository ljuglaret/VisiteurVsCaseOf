# Version case of (avec elm)
L évaluation d une expression arithmetique est recursive, Plus Moins et Fois operent sur des Expressions.   

Par exemple "2 + (3*4)" est une expression composée elle même de deux expressions:
-   2 
-   3*4

## Définition d'une epression
``` elm
type Expr = Plus     Expr Expr
            | Moins  Expr Expr
            | Fois   Expr Expr 
            | Const  Float

```

L'exemple précédent s'écrit : Plus( (Const 2) , Fois ( Const(3) , Const (4))) 


## Fonction d'évaluation
``` elm
eval : Expr ->  Float
eval  o =
    case o of 
        Plus  x y ->  (eval x ) + (eval  y)
        Moins x y ->  (eval x ) - (eval  y)
        Fois x y  -> (eval x ) * (eval  y)
        Const x -> x
```
Déroulement de l'évaluation :   
- eval ( Plus (Const 2)  (Fois (Const 3)(Const 4)))
- Ici X = Const 2, et Y = Fois (Const 3)(Const 4)), Donc :   
- eval ( Plus (Const 2)  (Fois (Const 3)(Const 4))) -> (eval (Const 2)) + (eval(Fois (Const 3)(Const 4)))
- On peut remplacer eval(Const 2) par 2, on obtient :   
- 2 + (eval(Fois (Const 3)(Const 4)))
Ici X = (Const 3) et Y = (Const 4) Donc :   
- 2 + (eval (Const 3)) * (eval(Const 4))
- On peut remplacer eval(Const 3) par 3 et eval(Const 4) par 4 on obtient :   2 + (3*4) = 2 + 12 = 14   

# Version visitor (avec Java)

## Interface Visiteur
``` java
public interface Visiteur<R>{
        public R visitConstante(Constante constante);
        public R visitProd(Prod prod);
        public R visitMoins(Moins moins);
        public R visitPlus(Plus plus);       
        public R visitPuissance(Puissance puissance);
}
```

## Définition des opérateurs
### Plus
``` java
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
 //à définir : getExpr1();getExpr2()
}
``` 

Les autres opérateurs sont construits de la même manière, c'est la méthode accept qui change
## Moins
``` java
public <R> R accept (Visiteur<R> visiteur ){
        return visiteur.visitMoins(this);
    }
``` 

## Prod
``` java
public <R> R accept (Visiteur<R> visiteur ){
        return visiteur.visitProd(this);
    }
``` 

## Puissance
``` java
public <R> R accept (Visiteur<R> visiteur ){
        return visiteur.visitPuissance(this);
    }
``` 

Le cas de Constante est un peu différent puisqu'il son opérande n'est pas une expression mais une valeur numérique

## Constante
```java
public class Constante implements Expr{
    private double valeur;
    
    public Constante (double valeur){
        this.valeur = valeur;
    }
    public <R> R accept (Visiteur<R> visiteur ){
        return visiteur.visitConstante(this);
    }
 //à définir : getValeur();
}
```

## Classe Eval
```java
public class Eval {

    private Expr expr;

    public Eval(Expr expr){
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
                Double evalExpr2 =  puissance.getExpr2().accept(this);
                return Math.pow(evalExpr1 , evalExpr2);
            }
        }; 
        return expr.accept(v1);
    }
}
```
On viste l'expression : new Plus (new Constante(2) , new Produit(new Constante(3) , new Constante(4)))   
On obtient successivement :   
-   (new Constante(2)) + (new Produit(new Constante(3) , new Constante(4)))
-    2 + ((new Constante(3) * new Constante(4)))
-   2  + ( 3 * 4)
-   14

