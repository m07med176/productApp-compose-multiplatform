package data

val regex = "\\{([^{}]*)\\}".toRegex()

inline fun <reified T : Any> String.getUrl(vararg params: T): String {
    val paramMap = mutableMapOf<String, Any>()
    for ((index, param) in params.withIndex()) {
        paramMap["param$index"] = param
    }

    val paramNames = regex.findAll(this).map { it.groupValues[1] }.toList()

    val paramMapWithName = mutableMapOf<String, Any>()
    for (name in paramNames) {
        if (paramMap.containsKey("param${paramNames.indexOf(name)}")) {
            paramMapWithName[name] = paramMap["param${paramNames.indexOf(name)}"] ?: ""
        } else if (!name.contains("{") && !name.contains("}")) {
            paramMapWithName[name] = name
        }
    }

    return this.replaceParams(paramMapWithName.toParamMap<T>())
}

inline fun <reified T : Any> Map<String, Any>.toParamMap(): Map<String, String> {
    val paramMap = mutableMapOf<String, String>()
    for ((key, value) in this) {
        if (value is T) {
            paramMap[key] = value.toString()
        }
    }
    return paramMap
}

fun String.replaceParams(paramMap: Map<String, String>): String {
    val result = StringBuilder()
    var index = 0
    while (index < this.length) {
        val startIndex = this.indexOf('{', index)
        if (startIndex == -1) {
            result.append(this.substring(index))
            break
        }
        val endIndex = this.indexOf('}', startIndex)
        if (endIndex == -1) {
            result.append(this.substring(index))
            break
        }
        result.append(this.substring(index, startIndex))
        val key = this.substring(startIndex + 1, endIndex)
        val value = paramMap[key] ?: "{$key}"
        result.append(value)
        index = endIndex + 1
    }
    return result.toString()
}