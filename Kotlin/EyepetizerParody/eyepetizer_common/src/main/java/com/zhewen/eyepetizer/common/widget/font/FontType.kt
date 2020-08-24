package com.zhewen.eyepetizer.common.widget.font
/**
* @author: zhewen
* created: 2020/8/24
* desc: 字体常量
*/
enum class FontType(var index: Int, var fontName: String, val path: String) {

    FZLANTING_NORMAL(0,"FZLanTing_normal","fonts/FZLanTingHeiS-L-GB-Regular.TTF"),//方正兰亭细黑简体
    FZLANTING_BOLD(1, "FZLanTing_bold", "fonts/FZLanTingHeiS-DB1-GB-Regular.TTF"),//方正兰亭中粗黑简体
    FUTURA(2, "Future", "fonts/Futura-CondensedMedium.ttf"),//拉丁
    LOBSTER(3, "Lobster", "fonts/Lobster-1.4.otf")//龙虾字体
}