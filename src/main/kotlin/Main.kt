import constants.InputTypeEnum
import dataAccess.ConsoleIO
import dataAccess.FileIO
import processing.Determine

fun main() {
    val outType = InputTypeEnum.FILE_IO
    val data:String
    val io = when (outType) {
        InputTypeEnum.CONSOLE_IO -> {
            ConsoleIO()
        }
        InputTypeEnum.FILE_IO ->{
            FileIO()
        }
    }
    data = io.read()
    io.write(Determine(data).getResult())
}
