package com.zhewen.kotlinsample.advanced.commonclass.`interface`

/**
 * Enum class demo
 */
fun main() {
    print(State.NORMAL.name)
    println("\t${State.NORMAL.ordinal}")
    println(Color.GREEN.rgb)

    ConsoleColor.BLACK.print()
    ConsoleColor.WHITE.print()
    ConsoleColor1.BLACK.print()

    println(enumValues<ConsoleColor1>().joinToString { it.name })
    println(enumValueOf<ConsoleColor1>("BLACK"))
    println(ConsoleColor1.valueOf("WHITE"))
    println(ConsoleColor1.values()[0])
}

enum class State{
    NORMAL,ERROR,NO_NETWORK,OTHER
}

enum class Color(val rgb: Int) {
    RED(0xFF0000),
    GREEN(0x00FF00),
    BLUE(0x0000FF)
}
enum class ConsoleColor{
    BLACK,WHITE;

    fun print() {
        println("我是枚举类")
    }
}

enum class ConsoleColor1{

    BLACK{
        override fun print() {
            println("show BLACK")
        }
    },
    WHITE{
        override fun print() {
            println("show WHITE")
        }
    };

    abstract fun print()
}