# Version case of (avec elm)
L évaluation d une expression arithmetique est recursive,
donc Plus Moins et Fois operent non pas sur des Float mais sur des Expressions

## Défintion du type expression
``` elm
type Expr = Plus     Expr Expr
            | Moins  Expr Expr
            | Fois   Expr Expr 
            | Const  Float

```


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


## Exemple
(8 + 3) - 5
``` elm
z : Float
z = eval (Moins (Plus (Const 8) (Const 3)) (Const 5))
```

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

##
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

## Un exemple
(8 + 3) - 4

```java
Expr e1 = new Moins(new Plus(new Constante(8),new Constante(3)),new Constante(4)));
Eval eval = new Eval(e1); 
System.out.println(eval.calcul());
```