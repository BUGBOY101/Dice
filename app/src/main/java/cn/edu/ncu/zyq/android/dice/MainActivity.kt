package cn.edu.ncu.zyq.android.dice

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    //设置一个数组存储每次掷骰子的点数和
    val re = IntArray(1000)
    //表示游戏中掷骰子的次数
    var count = 0
    //表示游戏结果，默认为CONTINUE
    var result:String = "CONTINUE"

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageView1:ImageView = findViewById(R.id.imageView)
        val imageView2:ImageView = findViewById(R.id.imageView2)
        val button:Button = findViewById(R.id.button)
        val textView:TextView = findViewById(R.id.textView)
        var a:Int
        var b:Int

        button.setOnClickListener{
//           随机产生两个骰子数
            a = Random.nextInt(1,7)
            b = Random.nextInt(1,7)
            count++
            re[count]=(a+b)
            textView.setText(String.format("两个骰子和为：%d",(a+b)))
            println("骰子数组："+ re[count])
            setDiceImage(imageView1,a)
            setDiceImage(imageView2,b)
            result = judgeGameResult(a,b,count)
            if(result=="WIN"||result=="LOSE"){
//                弹窗
                AlertDialog.Builder(this).apply {
                    setTitle("掷骰子游戏结束")
                    setMessage("游戏结果："+result)
                    setCancelable(false)
                    setPositiveButton("退出"){
                        dialog,which-> System.exit(0)
                    }
                    setNegativeButton("继续"){
                        dialog,which->init()
                    }
                    show()
                }

                Toast.makeText(
                    this@MainActivity,
                    "掷骰子游戏结果：" + result ,
                    Toast.LENGTH_SHORT
                ).show()
            }
            else{
                Toast.makeText(
                    this@MainActivity,
                    "继续掷骰子",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    /**
     * 初始化游戏
     */
    fun init(){
        count=0
//        清空数组
        for(i in 0 until re.size)
            re.set(i,0)
        val imageView1:ImageView = findViewById(R.id.imageView)
        val imageView2:ImageView = findViewById(R.id.imageView2)
        imageView1.setImageResource(R.drawable.dice)
        imageView2.setImageResource(R.drawable.dice)
    }

    /**
     * 根据两个骰子和判断游戏结果
     */
    fun judgeGameResult(a:Int,b:Int,count:Int):String {
        val r = a + b
//    两个骰子点数和r
        println("r0:$r")

        //第一次掷骰子的结果
        if (count == 1) {
            if ((r == 7 || r == 11)) {
                println("WIN")
                return "WIN"
            }
            if ((r == 2 || r == 3 || r == 12)) {
                println("LOSE")
                return "LOSE"
            }
        } else {
            if (r == 7) {
                return "LOSE"
            }
            if (r == re[count-1]) {
                println("游戏结果："+r+"上一次骰子数："+re[count-1])
                return "WIN"
            }
        }
        return "CONTINUE"
    }

//    设置图片显示掷骰子的点数
    fun setDiceImage(imageView:ImageView,a:Int){
        when(a){
            1->imageView.setImageResource(R.drawable.dice1)
            2->imageView.setImageResource(R.drawable.dice2)
            3->imageView.setImageResource(R.drawable.dice3)
            4->imageView.setImageResource(R.drawable.dice4)
            5->imageView.setImageResource(R.drawable.dice5)
            else->imageView.setImageResource(R.drawable.dice6)
        }
    }

}