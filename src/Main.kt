/*
작성자: 홍길동
날짜: 2026-09-30
*/
fun main() {
    /*println("Hello, Kotlin!")
    print("안녕하세요.")
    println("저는 코틀린을 배우고 있습니다.")*/

    // println("반갑습니다") // 인사 출력
    // println("이 줄은 출력되면 안 됩니다")

    /*println(1_000_000)
    println(3_000_000_000L)
    println(3.14)
    println(2.5f)
    println('A')
    println("Kotlin")
    println(true)
    println(0xFF)
    println(0b1010)*/

    /*println("그가 말했다. \"안녕\"")
    println("이름:\t홍길동")
    println("첫째 줄\n둘째 줄")

    val raw = """
C:\Users\kotlin
    들여쓰기도 그대로
    """.trim()
    println(raw)*/

    /*println("Int 최댓값: ${Int.MAX_VALUE}")
    println("Long 최댓값: ${Long.MAX_VALUE}")
    println("Double 최댓값: ${Double.MAX_VALUE}")

    val max = Int.MAX_VALUE
    println("Int 최댓값 + 1: ${max + 1}")*/

    /*println("42".toInt() + 8)
    println(3.99.toInt())
    println(7.toDouble())
    println('A'.code)
    println(100.toString() + "점")*/

    /*val name = "김코틀"
    var age = 25
    val height: Double = 175.5

    println("이름: $name, 나이: $age, 키: $height")

    age += 1
    println("생일 후 나이: $age")*/

    /*var a = 10
    var b = 20
    println("바꾸기 전: a=$a, b=$b")

    val temp = a
    a = b
    b = temp
    println("바꾼 후: a=$a, b=$b")*/

    /*printLine()
    println("3 + 5 = ${add(3, 5)}")
    println("4의 제곱 = ${square(4)}")
    printLine()*/

    /*greet("철수")
    greet("영희", "반갑습니다")
    greet(greeting = "좋은 아침", name = "민수")*/

    /*val a = 17
    val b = 5

    println("$a + $b = ${a + b}")
    println("$a - $b = ${a - b}")
    println("$a * $b = ${a * b}")
    println("$a / $b = ${a / b}")
    println("$a % $b = ${a % b}")
    println("$a / 5.0 = ${a / 5.0}")*/

    /*var score = 80

    score += 10
    println(score)

    score++
    println(score)

    println(score >= 90 && score < 100)
    println(score == 100 || score == 91)
    println(!(score > 50))
    println(score in 90..100)*/

    /*checkEvenOdd(4)
    checkEvenOdd(7)*/

    /*println("95점: ${getGrade(95)}")
    println("72점: ${getGrade(72)}")
    println("40점: ${getGrade(40)}")
    println("120점: ${getGrade(120)}")*/

    /*println("1: ${dayName(1)}")
    println("3: ${dayName(3)}")
    println("6: ${dayName(6)}")
    println("9: ${dayName(9)}")*/

    /*println("5세: ${ticketPrice(5)}원")
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
*/

    /*for (i in 1..10) print("$i ")
    println()

    for (i in 0 until 5) print("$i ")
    println()

    for (i in 2..10 step 2) print("$i ")
    println()

    for (i in 10 downTo 1) print("$i ")
    println()*/

    /*// 1. 구구단
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
    } while (count > 0)*/


    /*for (i in 50..100) {
        if (i % 7 == 0) {
            println("첫 번째 7의 배수: $i")
            break
        }
    }

    for (i in 1..20) {
        if (i % 3 == 0) continue
        print("$i ")
    }
    println()*/


    /*val names = listOf("철수", "영희", "민수")
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
    }*/


    /*val scores = intArrayOf(85, 92, 78, 95, 60)

    println("크기: ${scores.size}, 첫 값: ${scores[0]}, 마지막 값: ${scores[scores.size - 1]}")

    scores[2] = 88

    for (i in scores.indices) {
        println("$i: ${scores[i]}")
    }*/


    /*val scores = intArrayOf(85, 92, 88, 95, 60)

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
    println("최저점: $min")*/


    /*val text = "  Hello, Kotlin World  "
    val t = text.trim()

    println(t)
    println(t.length)
    println(t.uppercase())
    println(t.contains("Kotlin"))
    println(t.replace("World", "Android"))
    println(t.substring(0, 5))
    println(t.split(", ")[1])*/


    /*println("Level: ${isPalindrome("Level")}")
    println("Kotlin: ${isPalindrome("Kotlin")}")
    println("토마토: ${isPalindrome("토마토")}")

    var count = 0
    for (c in "banana") {
        if (c == 'a') count++
    }
    println("banana 안의 a 개수: $count")*/


    /*val fruits = listOf("사과", "바나나", "포도")
    println("두 번째 과일: ${fruits[1]}")

    val todoList = mutableListOf<String>()
    todoList.add("공부")
    todoList.add("운동")
    todoList.add("청소")
    todoList.remove("운동")
    todoList.add(0,"기상")

    println(todoList)
    println("할 일 개수: ${todoList.size}")*/


    /*val scores = mutableMapOf("철수" to 90, "영희" to 85)
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
    println("Set 크기: ${tags.size}")*/


    /*val fruits = listOf("사과", "바나나", "포도")

    fruits.forEach { println(it) }
    fruits.forEachIndexed { i, fruit ->
        println("${i + 1}. $fruit")
    }

    val scores = mapOf("철수" to 90, "영희" to 85)
    scores.forEach { (name, score) ->
        println("$name: ${score}점")
    }*/


    val numbers = listOf(3, -1, 7, 0, -5, 10)
    var sum = 0

    numbers.forEach {
        if (it <= 0) return@forEach
        sum += it
        println("$it 더함")
    }

    println("양수 합계: $sum")
}

/*
fun add(a: Int, b: Int): Int {
    return a + b
}

fun square(n: Int): Int = n * n

fun printLine() { println("------------") }

fun greet(name: String, greeting: String = "안녕하세요") {
    println("$greeting, ${name}님!")
}
*/

/*fun checkEvenOdd(n: Int) {
    val result = if (n % 2 == 0) "짝수" else "홀수"
    val josa = if (n % 2 == 0) "는" else "은"
    println("$n$josa ${result}입니다.")
}*/

/*
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
}*/

/*
fun dayName(n: Int): String = when (n) {
    1 -> "월요일"
    2 -> "화요일"
    3 -> "수요일"
    4 -> "목요일"
    5 -> "금요일"
    6, 7 -> "주말"
    else -> "없는 요일"
}*/

/*
fun ticketPrice(age: Int): Int = when (age) {
    in 0..7 -> 0
    in 8..19 -> 5000
    in 20..64 -> 10000
    else -> 3000
}*/

/*
fun findIndex(names: List<String>, target: String): Int {
    for (i in names.indices) {
        if (names[i] == target) return i
    }
    return -1
}*/

fun isPalindrome(word: String): Boolean {
    val lower = word.lowercase()
    return lower == lower.reversed()
}