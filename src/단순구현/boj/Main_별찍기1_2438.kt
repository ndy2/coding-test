package 단순구현.boj

fun main(){
    val n = System.`in`.bufferedReader().readLine().toInt()

    for(i in 1..n){
        println(buildString(i) {repeat (i) {append('*')}})
    }
}