package com.template.memory.data

object Data {

    fun getAllEmojis(count: Int): List<String> {
        val list = listOf("\uD83D\uDC7E", "\uD83E\uDD16", "\uD83D\uDC7D", "\uD83D\uDC80", "\uD83D\uDC38",
            "\uD83E\uDD2F", "\uD83D\uDC7B", "\uD83D\uDC53", "\uD83C\uDF02", "\uD83D\uDC62", "\uD83C\uDF93",
            "\uD83D\uDCAA\uD83C\uDFFB", "\uD83E\uDDDC\uD83C\uDFFC\u200D♀", "\uD83C\uDF32", "\uD83E\uDD93",
            "\uD83C\uDF0D", "\uD83C\uDF49", "\uD83C\uDF46", "\uD83C\uDF70", "\uD83C\uDFAC", "\uD83D\uDE97",
            "\uD83C\uDFE0", "\uD83D\uDD14", "\uD83C\uDF81", "\uD83D\uDE80", "\uD83E\uDDCA", "\uD83C\uDF4C",
            "\uD83C\uDF53", "\uD83E\uDD55", "\uD83D\uDC09", "\uD83D\uDD77", "\uD83E\uDD8A").shuffled()

        return (mutableListOf<String>().apply {
            for (i in 0 until (count * count) / 2) {
                add(list[i])
                add(list[i])
            }
        }).shuffled()
    }

    fun getHiddenItems(count: Int) = mutableListOf<String>().apply {
        for (i in 0 until count*count) this.add("?")
    }
}