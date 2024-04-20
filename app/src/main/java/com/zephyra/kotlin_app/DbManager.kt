package com.zephyra.kotlin_app

class DbManager(private var num:Int): Thread() {
    override fun run() {
        while (num < 10){
            println(num)
            num++
        }
    }
}