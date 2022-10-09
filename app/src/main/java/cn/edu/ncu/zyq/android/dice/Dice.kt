package cn.edu.ncu.zyq.android.dice

import kotlin.random.Random

fun main(){

//    两个骰子
    var a:Int = Random.nextInt(1,7)
    var b:Int = Random.nextInt(1,7)
//    两个骰子点数和r
    var r = a+b
    println("r0:$r")
    val k:Int = r
    if(r==7||r==11){
        println("WIN")
    }
    else if(r==2||r==3||r==12){
        println("LOSE")
    }
    else{
        while(true){
            a = Random.nextInt(1,7)
            b = Random.nextInt(1,7)
            r = a+b
            println("r:$r")
            if(r==7){
                println("LOSE")
                break
            }
            if(r==k){
                println("k:$k")
                println("WIN")
                break
            }
        }
    }
}