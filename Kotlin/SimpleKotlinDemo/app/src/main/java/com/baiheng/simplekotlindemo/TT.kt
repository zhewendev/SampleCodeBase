package com.baiheng.simplekotlindemo

class TT(val name: String, val age: Int) {

    val height: Int = 175

    operator fun component1(): String {
        return name
    }

    operator fun component2(): Int{
        return age
    }

    operator fun component3(): Int{
        return height
    }
}