fun main() {
    val br = System.`in`.bufferedReader()
    val (aDen, bDen) = br.readLine().split(" ").map { it.toDouble() }
    val (sa, sb, s) = br.readLine().split(" ").map { it.toDouble() }

    var ac = sa * aDen / 1000
    var bc = sb * bDen / 1000
    var aw = sa - ac
    var bw = sb - bc

    // 항아리 a 보다 컵이 그면 한잔도 못 마신다.
    if (s > sa) {
        println("gg")
        return
    }

    var answer = "gg"
    var count = 0
    var minDen = -1.0

    while (den(ac, aw) >= minDen) {
        minDen = den(ac, aw)
        count++

        // a 에서 퍼마신 양
        val amount = s

        val coffeeAmount = amount * den(ac, aw) / 1000
        ac -= coffeeAmount
        aw -= amount - coffeeAmount

        if (sb < s) break
        val bCoffeeAmount = amount * den(bc, bw) / 1000
        // b 에서 빼서
        bc -= bCoffeeAmount
        bw -= amount - bCoffeeAmount

        // a 로 옮긴다.
        ac += bCoffeeAmount
        aw += amount - bCoffeeAmount

        // b 에 물을 추가한다.
        bw += amount

        if (count == 50) {
            count = 0
            break
        }
    }
    if (count != 0) answer = count.toString(16).uppercase()
    println(answer)
}

fun den(c: Double, w: Double) = (c / (c + w)) * 1000