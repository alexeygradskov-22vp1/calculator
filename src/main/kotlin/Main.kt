import constants.Constants
import dataAccess.ConsoleIO
import dataAccess.FileIO
import dataAccess.IO
import processing.Determine

fun main(args: Array<String>) {
    var outType = Constants.CONSOLE_IO;
    var io: IO;
    var data:String;
    when (outType) {
        1 -> {
            io = ConsoleIO()
            data = io.read();
            io.write(Determine(data, Constants.HEX).getResult())
        }
        2 ->{
            io = FileIO()
            data = io.read();
            io.write(Determine(data, Constants.HEX).getResult())
        }
    }
}
