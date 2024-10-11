import kotlin.math.sqrt
data class Point(val x: Double, val y: Double) {
    fun distanceTo(other: Point): Double {
        return sqrt((other.x - x) * (other.x - x) + (other.y - y) * (other.y - y))
    }
}
class Triangle(val p1: Point, val p2: Point, val p3: Point) {
    private fun area(): Double {
        return 0.5 * kotlin.math.abs(p1.x * (p2.y - p3.y) + p2.x * (p3.y - p1.y) + p3.x * (p1.y - p2.y))
    }

    fun exists(): Boolean {
        return area() > 0
    }

    private fun sideLength(a: Point, b: Point): Double {
        return a.distanceTo(b)
    }

    fun circumradius(): Double {
        val a = sideLength(p1, p2)
        val b = sideLength(p2, p3)
        val c = sideLength(p3, p1)
        val s = area()

        return (a * b * c) / (4 * s)
    }

    fun circumcenter(): Point {
        val d = 2 * (p1.x * (p2.y - p3.y) + p2.x * (p3.y - p1.y) +
                p3.x * (p1.y - p2.y))

        val ox = ((p1.x * p1.x + p1.y * p1.y) * (p2.y - p3.y) +
                (p2.x * p2.x + p2.y * p2.y) * (p3.y - p1.y) +
                (p3.x * p3.x + p3.y * p3.y) * (p1.y - p2.y)) / d

        val oy = ((p1.x * p1.x + p1.y * p1.y) * (p3.x - p2.x) +
                (p2.x * p2.x + p2.y * p2.y) * (p1.x - p3.x) +
                (p3.x * p3.x + p3.y * p3.y) * (p2.x - p1.x)) / d

        return Point(ox, oy)
    }
}

fun main() {
    println("Программа для нахождения центра и радиуса описанной окружности вокруг треугольника.")

        fun readCoordinate(prompt: String): Double {
            while (true) {
                print(prompt)
                val input = readLine()
                if (input.isNullOrBlank()) {
                    println("Ошибка ввода: Ввод не может быть пустым.")
                    continue
                }

                val number = input.toDoubleOrNull()
                if (number != null) return number

                println("Ошибка ввода: Пожалуйста, введите корректное число.")
            }
        }

    println("Введите координаты вершин треугольника:")
    val p1 = Point(readCoordinate("X1: "), readCoordinate("Y1: "))
    val p2 = Point(readCoordinate("X2: "), readCoordinate("Y2: "))
    val p3 = Point(readCoordinate("X3: "), readCoordinate("Y3: "))

    val triangle = Triangle(p1, p2, p3)

    if (!triangle.exists()) {
        println("Ошибка: Треугольник с заданными вершинами не существует. Точки лежат на одной прямой.")
            return
    }

    val circumcenter = triangle.circumcenter()
    val radius = triangle.circumradius()

    println("Координаты центра описанной окружности: (${circumcenter.x}, ${circumcenter.y})")
    println("Радиус описанной окружности: $radius")
}