package com.zhewen.kotlinsample.higherorder.generic

import com.zhewen.kotlinsample.advanced.commonclass.inheritance.Animal
import com.zhewen.kotlinsample.advanced.commonclass.inheritance.Base

/**
 * 泛型简单示例
 */
fun main() {

    show(SubClass())
}

/*******java
public class Box<T> {
    public T value;

    public Box(T t) {
        value = t;
    }
}

new Box<String>("123");
new Box<Integer>(1);
***********/
//对应前述的java泛型示例
/***********kotlin
class Box<T>(t: T) {
    var value = t
}
var box: Box<String> = Box("123")
var box2: Box<Int> = Box(123)
***********/

class Box<out T>
fun test(strs:Box<String>) {
    val objects:Box<Any> = strs
}

class Box1<in T>

fun test1(str:Box1<Any>) {
    val objects:Box1<String> = str
}

class Box2<out T> {
}

//泛型函数
fun <T> singletonList(item:T):List<T> {
    return listOf<T>(item)
}
//泛型约束
open class BaseClass
class SubClass : BaseClass()


fun <T:BaseClass> show(item:T):List<T> {
    return listOf<T>(item)
}

class Monster<T:Animal> //单个边界，等价于java中的extends

interface Food

class Monsters<T> where T:Animal, T:Food        //多个边界，即T必须同时是Animal和Food的子类
