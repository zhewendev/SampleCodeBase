package com.zhewen.kotlinsample.advanced.commonclass.sealed

/**
 * 密封类简单演示
 */
fun main(){

}

sealed class SealedExpr()

sealed class Expr{
    data class Const(val number:Double):Expr()

    data class Sum(val e1:Expr,val e2:Expr):Expr()
}

sealed class SealedClass{

    object SonClass1 : SealedClass() {

    }

    object SonClass2 : SealedClass() {

    }
}
fun check(sealedClass: SealedClass): String =
        when(sealedClass){
            is SealedClass.SonClass1 -> "1"
            is SealedClass.SonClass2 -> "2"
        }