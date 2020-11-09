package com.zhewen.kotlinsample.advanced.commonclass.abstract


/**
 * 嵌套类简单演示
 */
fun main() {
    Other.Nested().print()
    Other().InnerClass().init()

    //测试匿名内部类
    val other = Other()
    other.setOnClickListener(object :OnClickListener{
        override fun onItemClick(str: String) {
            println(str)
        }
    })
    other.testListener()

    other.partMethod()
}

/**
 * 嵌套类
 */
class Other{
    val numOther = 1
    private lateinit var listener:OnClickListener

    fun setOnClickListener(listener: OnClickListener){
        this.listener = listener
    }

    fun testListener(){
        if (this::listener.isInitialized){
            listener.onItemClick("我是匿名内部类的测试方法")
        }
    }

    /**
     * 嵌套类，默认静态
     */
    class Nested{
        fun print(){
            println("执行了Nested.print方法")
        }
    }

    /**
     * 嵌套内部类，带有外部类的引用
     */
    inner class InnerClass{
        val name = "InnerClass"
        fun init(){
            println("我是InnerClass类的init方法，外部类的属性numOther = $numOther")
        }
    }

    fun partMethod(){
        var name:String = "partMethod"

        /**
         * 局部类
         */
        class Part{
            var numPart = 2
            fun test(){
                name = "test"
                numPart = 5
                println("我是局部类中的方法")

            }
        }

        val part = Part()
        println("name = $name \t numPart = " + part.numPart + "\t numOther = numOther")
        part.test()
        println("name = $name \t numPart = " + part.numPart + "\t numOther = numOther")

    }


}

interface OnClickListener{
    fun onItemClick(str:String)
}