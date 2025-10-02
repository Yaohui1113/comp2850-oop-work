// Task 3.1: command line arguments
import kotlin.math.PI
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    if (args.size != 1) {
        println("Error: filename required on command line!")
        exitProcess(1)
    }

    val r = args[0].toDouble()
    when{
        r > 0.0 -> println("Area = ${"%.3f".format(PI * r*r)}")
        else    ->println("Invalid radius!")
    }

    // required argument available as args[0]
}