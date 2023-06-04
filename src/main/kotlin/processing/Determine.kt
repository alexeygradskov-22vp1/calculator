package processing

import exceptions.InvalidFormat

class Determine(data: String, type: Int) {
    private var result: String = "";
    private var _data = data;
    private var _type = type;

    init {
        determine();
    }

    private fun determine() {
        var list: List<String> = _data.split("\n");
        list = list.dropLast(1);
        list.forEach {
            val listOfString: List<String> = processAString(it).split(" ");
            result += try {
                if (listOfString[0].matches(Regex("stop")) ||
                    listOfString[0].matches(Regex("stop")) ||
                    listOfString[0].matches(
                        Regex("stop")
                    )
                ) {
                    result += "stop";
                    return;
                }
                validData(listOfString);
                calculate(listOfString) + "\n";
            } catch (e: InvalidFormat) {
                e.what() + "\n";
            }

        }
    }

    private fun calculate(data: List<String>): String {
        var resultOfCalculation = 0;
        var resultString = "";
        when (data[1]) {
            "*" -> resultOfCalculation = (data[0].toInt() * data[2].toInt())
            "/" -> resultOfCalculation = (data[0].toInt() / data[2].toInt())
            "+" -> resultOfCalculation = (data[0].toInt() + data[2].toInt())
            "-" -> resultOfCalculation = (data[0].toInt() - data[2].toInt())
        }
        when (_type) {
            1 -> resultString = Integer.toBinaryString(resultOfCalculation);
            2 -> resultString = Integer.toHexString(resultOfCalculation);
            3 -> resultString = Integer.toOctalString(resultOfCalculation);
            4 -> resultString = resultOfCalculation.toString();
        }
        return resultString;
    }

    private fun validData(list: List<String>): Boolean {
        if (list[0] == null || list[2] == null || !list[0].matches(Regex("^\\d+\$")) || !list[2].matches(Regex("^\\d+\$"))) {
            throw InvalidFormat("Неверный формат строки");
        }
        return true;
    }

    fun getResult(): String {
        return result;
    }

    private fun processAString(string: String): String {
        var replacedString: String = string.replace("\\s+".toRegex(), " ");
        replacedString = replacedString.replace("*", " * ");
        replacedString = replacedString.replace("/", " / ");
        replacedString = replacedString.replace("+", " + ");
        replacedString = replacedString.replace("-", " - ");
        replacedString = replacedString.replace("\n$".toRegex(), "")
        return replacedString;
    }
}