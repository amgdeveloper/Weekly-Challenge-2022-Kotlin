package com.mouredev.weeklychallenge2022

/*
 * Reto #1
 * ¿ES UN ANAGRAMA?
 * Fecha publicación enunciado: 03/01/22
 * Fecha publicación resolución: 10/01/22
 * Dificultad: MEDIA
 *
 * Enunciado: Escribe una función que reciba dos palabras (String) y retorne verdadero o falso (Boolean) según sean o no anagramas.
 * Un Anagrama consiste en formar una palabra reordenando TODAS las letras de otra palabra inicial.
 * NO hace falta comprobar que ambas palabras existan.
 * Dos palabras exactamente iguales no son anagrama.
 *
 * Información adicional:
 * - Usa el canal de nuestro discord (https://mouredev.com/discord) "🗓reto-semanal" para preguntas, dudas o prestar ayuda la acomunidad.
 * - Puedes hacer un Fork del repo y una Pull Request al repo original para que veamos tu solución aportada.
 * - Revisaré el ejercicio en directo desde Twitch el lunes siguiente al de su publicación.
 * - Subiré una posible solución al ejercicio el lunes siguiente al de su publicación.
 *
 */

fun main() {
    val word1 = "night"
    val word2 = "thing";

    val isAnagram = word1.isAnagramOf(word2)

    print("$word1 is ")
    if (!isAnagram) print("not ")
    println("an anagram of $word2")
}

fun String.isAnagramOf(word: String): Boolean {
    if (this.length != word.length || this == word)
        return false
    val currentWordMap = createMapFromWord(this)
    val comparedWordMap = createMapFromWord(word)

    for (entry in currentWordMap.entries) {
        val letter = entry.key
        val currentWordOccurrences = entry.value
        val comparedWordOccurrences = comparedWordMap[letter]
        val sameLetterCountIsDifferent =
            comparedWordOccurrences == null || currentWordOccurrences.size != comparedWordOccurrences.size
        if (sameLetterCountIsDifferent) {
            return false
        } else if (currentWordOccurrences.containsAll(comparedWordOccurrences!!)) {
            return false
        }
    }
    return true
}

fun createMapFromWord(word: String): Map<Char, List<Int>> {
    val map = mutableMapOf<Char, MutableList<Int>>()
    for (i in word.indices) {
        val letter = word[i]
        if (map.containsKey(letter)) {
            val list = map[letter]
            if (list != null) {
                list.add(i)
                map[letter] = list
            }
        } else {
            map[letter] = mutableListOf(i)
        }
    }
    return map
}
