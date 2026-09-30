/*
작성자: 차원우
날짜: 2026-09-30
*/

// ===================================================
//  코틀린 카페 키오스크 (콘솔 버전)
// ===================================================

// ===== 메뉴 데이터 (배열) =====
val menuNames = arrayOf("아메리카노", "카페라떼", "바닐라라떼", "녹차라떼", "치즈케이크")
val menuPrices = intArrayOf(3000, 3500, 4000, 4000, 5500)

// ===== 프로그램 상태 (컬렉션) =====
val cart = mutableMapOf<String, Int>()     // 메뉴 이름 -> 수량
val soldOut = mutableSetOf<String>()       // 품절 메뉴
val orderHistory = mutableListOf<Int>()    // 결제 금액 기록

fun main() {
    println("☕ 코틀린 카페에 오신 것을 환영합니다!")

    while (true) {
        printMainMenu()
        when (readNumber("선택 > ")) {
            1 -> addToCart()
            2 -> showCart()
            3 -> removeFromCart()
            4 -> checkout()
            5 -> manageSoldOut()
            6 -> showSales()
            0 -> {
                println("이용해 주셔서 감사합니다. 👋")
                break
            }
            else -> println("⚠ 0~6 사이 번호를 입력해 주세요.")
        }
    }
}

// ===== 공통 도우미 =====
fun readNumber(prompt: String): Int {
    print(prompt)
    val input = readln().trim()
    return input.toIntOrNull() ?: -1
}

fun formatWon(amount: Int): String = "%,d원".format(amount)

fun priceOf(name: String): Int {
    for (i in menuNames.indices) {
        if (menuNames[i] == name) return menuPrices[i]
    }
    return 0
}

// ===== 화면 출력 =====
fun printMainMenu() {
    println()
    println("=".repeat(30))
    println(" 1. 메뉴 담기")
    println(" 2. 장바구니 보기")
    println(" 3. 장바구니에서 빼기")
    println(" 4. 결제하기")
    println(" 5. 품절 관리 (관리자)")
    println(" 6. 오늘 매출 보기")
    println(" 0. 종료")
    println("=".repeat(30))
}

fun printMenuBoard() {
    println("----- MENU -----")
    menuNames.forEachIndexed { index, name ->
        val status = if (name in soldOut) " (품절)" else ""
        println("${index + 1}. $name - ${formatWon(menuPrices[index])}$status")
    }
}

// ===== 장바구니 =====
fun addToCart() {
    printMenuBoard()
    val no = readNumber("메뉴 번호 > ")
    if (no !in 1..menuNames.size) {
        println("⚠ 없는 메뉴 번호입니다.")
        return
    }

    val name = menuNames[no - 1]
    if (name in soldOut) {
        println("⚠ ${name}은(는) 품절입니다.")
        return
    }

    val qty = readNumber("수량 > ")
    if (qty <= 0) {
        println("⚠ 수량은 1 이상이어야 합니다.")
        return
    }

    cart[name] = cart.getOrDefault(name, 0) + qty
    println("✅ $name ${qty}개를 담았습니다.")
}

fun cartTotal(): Int {
    var total = 0
    cart.forEach { (name, qty) ->
        total += priceOf(name) * qty
    }
    return total
}

fun showCart() {
    if (cart.isEmpty()) {
        println("🛒 장바구니가 비어 있습니다.")
        return
    }
    println("----- 장바구니 -----")
    cart.forEach { (name, qty) ->
        println("$name x$qty = ${formatWon(priceOf(name) * qty)}")
    }
    println("합계: ${formatWon(cartTotal())}")
}

fun removeFromCart() {
    if (cart.isEmpty()) {
        println("🛒 장바구니가 비어 있습니다.")
        return
    }

    val names = cart.keys.toList()
    names.forEachIndexed { i, name ->
        println("${i + 1}. $name x${cart[name]}")
    }

    val no = readNumber("뺄 메뉴 번호 > ")
    if (no !in 1..names.size) {
        println("⚠ 잘못된 번호입니다.")
        return
    }

    val removed = names[no - 1]
    cart.remove(removed)
    println("🗑 ${removed}을(를) 뺐습니다.")
}

// ===== 결제 =====
fun calcDiscount(total: Int, totalQty: Int): Int = when {
    totalQty >= 10 -> total * 20 / 100
    totalQty >= 5 -> total * 10 / 100
    else -> 0
}

fun checkout() {
    if (cart.isEmpty()) {
        println("🛒 담긴 메뉴가 없습니다.")
        return
    }

    showCart()

    val total = cartTotal()
    val totalQty = cart.values.sum()
    val discount = calcDiscount(total, totalQty)

    print("쿠폰 코드 (없으면 Enter) > ")
    val coupon = readln().trim().uppercase()
    val couponDiscount = if (coupon == "KOTLIN") 1000 else 0
    if (coupon.isNotEmpty() && couponDiscount == 0) {
        println("⚠ 유효하지 않은 쿠폰입니다.")
    }

    var payAmount = total - discount - couponDiscount
    if (payAmount < 0) payAmount = 0

    printReceipt(total, discount, couponDiscount, payAmount)

    orderHistory.add(payAmount)
    cart.clear()
}

fun printReceipt(total: Int, discount: Int, coupon: Int, pay: Int) {
    val line = "-".repeat(30)
    println()
    println("=".repeat(30))
    println("         🧾 영 수 증")
    println("=".repeat(30))

    cart.forEach { (name, qty) ->
        println("$name x$qty".padEnd(18) + formatWon(priceOf(name) * qty).padStart(12))
    }

    println(line)
    println("합계".padEnd(18) + formatWon(total).padStart(12))
    if (discount > 0) println("수량 할인".padEnd(18) + ("-" + formatWon(discount)).padStart(12))
    if (coupon > 0) println("쿠폰 할인".padEnd(18) + ("-" + formatWon(coupon)).padStart(12))
    println(line)
    println("결제 금액".padEnd(18) + formatWon(pay).padStart(12))
    println("오늘 ${orderHistory.size + 1}번째 손님입니다. 감사합니다!")
}

// ===== 관리자 =====
fun manageSoldOut() {
    printMenuBoard()
    val no = readNumber("품절 전환할 메뉴 번호 (0: 취소) > ")
    if (no == 0) return
    if (no !in 1..menuNames.size) {
        println("⚠ 잘못된 번호입니다.")
        return
    }

    val name = menuNames[no - 1]
    if (name in soldOut) {
        soldOut.remove(name)
        println("✅ $name 판매 재개")
    } else {
        soldOut.add(name)
        cart.remove(name)
        println("⛔ $name 품절 처리")
    }
}

fun showSales() {
    if (orderHistory.isEmpty()) {
        println("아직 주문이 없습니다.")
        return
    }

    var sum = 0
    var freeCount = 0
    for ((i, amount) in orderHistory.withIndex()) {
        if (amount == 0) {
            freeCount++
            continue
        }
        println("주문 ${i + 1}: ${formatWon(amount)}")
        sum += amount
    }
    println("총 매출: ${formatWon(sum)} (무료 주문 ${freeCount}건)")
}

/*
===== Day1 챕터 실습 코드 (주석 보관) =====

fun main() {
    println("Hello, Kotlin!")
    print("안녕하세요.")
    println("저는 코틀린을 배우고 있습니다.")

    // println("반갑습니다") // 인사 출력
    // println("이 줄은 출력되면 안 됩니다")

    println(1_000_000)
    println(3_000_000_000L)
    println(3.14)
    println(2.5f)
    println('A')
    println("Kotlin")
    println(true)
    println(0xFF)
    println(0b1010)

    println("그가 말했다. \"안녕\"")
    println("이름:\t홍길동")
    println("첫째 줄\n둘째 줄")

    val raw = """
C:\Users\kotlin
    들여쓰기도 그대로
    """.trim()
    println(raw)

    println("Int 최댓값: ${Int.MAX_VALUE}")
    println("Long 최댓값: ${Long.MAX_VALUE}")
    println("Double 최댓값: ${Double.MAX_VALUE}")

    val max = Int.MAX_VALUE
    println("Int 최댓값 + 1: ${max + 1}")

    println("42".toInt() + 8)
    println(3.99.toInt())
    println(7.toDouble())
    println('A'.code)
    println(100.toString() + "점")

    val name = "김코틀"
    var age = 25
    val height: Double = 175.5

    println("이름: $name, 나이: $age, 키: $height")

    age += 1
    println("생일 후 나이: $age")

    var a = 10
    var b = 20
    println("바꾸기 전: a=$a, b=$b")

    val temp = a
    a = b
    b = temp
    println("바꾼 후: a=$a, b=$b")

    printLine()
    println("3 + 5 = ${add(3, 5)}")
    println("4의 제곱 = ${square(4)}")
    printLine()

    greet("철수")
    greet("영희", "반갑습니다")
    greet(greeting = "좋은 아침", name = "민수")

    val a = 17
    val b = 5

    println("$a + $b = ${a + b}")
    println("$a - $b = ${a - b}")
    println("$a * $b = ${a * b}")
    println("$a / $b = ${a / b}")
    println("$a % $b = ${a % b}")
    println("$a / 5.0 = ${a / 5.0}")

    var score = 80

    score += 10
    println(score)

    score++
    println(score)

    println(score >= 90 && score < 100)
    println(score == 100 || score == 91)
    println(!(score > 50))
    println(score in 90..100)

    checkEvenOdd(4)
    checkEvenOdd(7)

    println("95점: ${getGrade(95)}")
    println("72점: ${getGrade(72)}")
    println("40점: ${getGrade(40)}")
    println("120점: ${getGrade(120)}")

    println("1: ${dayName(1)}")
    println("3: ${dayName(3)}")
    println("6: ${dayName(6)}")
    println("9: ${dayName(9)}")

    println("5세: ${ticketPrice(5)}원")
    println("15세: ${ticketPrice(15)}원")
    println("30세: ${ticketPrice(30)}원")
    println("70세: ${ticketPrice(70)}원")

    val temp = 15
    val clothes = when {
        temp >= 28 -> "반팔"
        temp >= 20 -> "긴팔"
        temp >= 10 -> "자켓"
        else -> "패딩"
    }
    println("오늘 ${temp}도 → $clothes")

    for (i in 1..10) print("$i ")
    println()

    for (i in 0 until 5) print("$i ")
    println()

    for (i in 2..10 step 2) print("$i ")
    println()

    for (i in 10 downTo 1) print("$i ")
    println()

    // 1. 구구단
    for (dan in 2..3) {
        for (i in 1..9) {
            println("$dan x $i = ${dan*i}")
        }
    }

    // 2. while
    var n = 0
    var sum = 0
    while (sum <= 100) {
        n++
        sum += n
    }
    println("${n}까지 더하면 합이 $sum")

    // 3. do-while
    val count = 0
    do {
        println("실행됨 (count=$count)")
    } while (count > 0)


    for (i in 50..100) {
        if (i % 7 == 0) {
            println("첫 번째 7의 배수: $i")
            break
        }
    }

    for (i in 1..20) {
        if (i % 3 == 0) continue
        print("$i ")
    }
    println()


    val names = listOf("철수", "영희", "민수")
    println("\"영희\"의 위치: ${findIndex(names, "영희")}")
    println("\"길동\"의 위치: ${findIndex(names, "길동")}")

    outer@ for (i in 1..3) {
        for (j in 1..3) {
            println("i=$i j=$j -> ${i * j}")

            if (i * j == 6) {
                println("6 발견! 종료")
                break@outer
            }
        }
    }


    val scores = intArrayOf(85, 92, 78, 95, 60)

    println("크기: ${scores.size}, 첫 값: ${scores[0]}, 마지막 값: ${scores[scores.size - 1]}")

    scores[2] = 88

    for (i in scores.indices) {
        println("$i: ${scores[i]}")
    }


    val scores = intArrayOf(85, 92, 88, 95, 60)

    var total = 0
    var max = scores[0]
    var min = scores[0]

    for (s in scores) {
        total += s
        if (s > max) max = s
        if (s < min) min = s
    }

    val avg = total.toDouble() / scores.size

    println("합계: $total")
    println("평균: $avg")
    println("최고점: $max")
    println("최저점: $min")


    val text = "  Hello, Kotlin World  "
    val t = text.trim()

    println(t)
    println(t.length)
    println(t.uppercase())
    println(t.contains("Kotlin"))
    println(t.replace("World", "Android"))
    println(t.substring(0, 5))
    println(t.split(", ")[1])


    println("Level: ${isPalindrome("Level")}")
    println("Kotlin: ${isPalindrome("Kotlin")}")
    println("토마토: ${isPalindrome("토마토")}")

    var count = 0
    for (c in "banana") {
        if (c == 'a') count++
    }
    println("banana 안의 a 개수: $count")


    val fruits = listOf("사과", "바나나", "포도")
    println("두 번째 과일: ${fruits[1]}")

    val todoList = mutableListOf<String>()
    todoList.add("공부")
    todoList.add("운동")
    todoList.add("청소")
    todoList.remove("운동")
    todoList.add(0,"기상")

    println(todoList)
    println("할 일 개수: ${todoList.size}")


    val scores = mutableMapOf("철수" to 90, "영희" to 85)
    scores["민수"] = 77
    scores["철수"] = 95
    println(scores)

    println("영희: ${scores["영희"]}")
    println("길동: ${scores.getOrDefault("길동", 0)}")

    val unique = listOf(1, 2, 2, 3, 3, 3).toSet()
    println(unique)

    val tags = mutableSetOf<String>()
    tags.add("코틀린")
    tags.add("코틀린")
    println("Set 크기: ${tags.size}")


    val fruits = listOf("사과", "바나나", "포도")

    fruits.forEach { println(it) }
    fruits.forEachIndexed { i, fruit ->
        println("${i + 1}. $fruit")
    }

    val scores = mapOf("철수" to 90, "영희" to 85)
    scores.forEach { (name, score) ->
        println("$name: ${score}점")
    }


    val numbers = listOf(3, -1, 7, 0, -5, 10)
    var sum = 0

    numbers.forEach {
        if (it <= 0) return@forEach
        sum += it
        println("$it 더함")
    }

    println("양수 합계: $sum")
}

fun add(a: Int, b: Int): Int {
    return a + b
}

fun square(n: Int): Int = n * n

fun printLine() { println("------------") }

fun greet(name: String, greeting: String = "안녕하세요") {
    println("$greeting, ${name}님!")
}

fun checkEvenOdd(n: Int) {
    val result = if (n % 2 == 0) "짝수" else "홀수"
    val josa = if (n % 2 == 0) "는" else "은"
    println("$n$josa ${result}입니다.")
}

fun getGrade(score: Int): String {
    return if (score < 0 || score > 100) {
        "잘못된 점수"
    } else if (score >= 90) {
        "A"
    } else if (score >= 80) {
        "B"
    } else if (score >= 70) {
        "C"
    } else if (score >= 60) {
        "D"
    } else {
        "F"
    }
}

fun dayName(n: Int): String = when (n) {
    1 -> "월요일"
    2 -> "화요일"
    3 -> "수요일"
    4 -> "목요일"
    5 -> "금요일"
    6, 7 -> "주말"
    else -> "없는 요일"
}

fun ticketPrice(age: Int): Int = when (age) {
    in 0..7 -> 0
    in 8..19 -> 5000
    in 20..64 -> 10000
    else -> 3000
}

fun findIndex(names: List<String>, target: String): Int {
    for (i in names.indices) {
        if (names[i] == target) return i
    }
    return -1
}

fun isPalindrome(word: String): Boolean {
    val lower = word.lowercase()
    return lower == lower.reversed()
}
*/
