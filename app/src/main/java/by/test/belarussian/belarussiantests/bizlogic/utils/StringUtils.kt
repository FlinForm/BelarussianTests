@file:JvmName("StringUtils")

package by.test.belarussian.belarussiantests.bizlogic.utils

/**
 * Provides string utility methods.
 *
 * @author Pavel Davydzenka
 */
const val EMPTY = ""
const val SPACE = " "

/**
 * Checks if given [value] is null or empty.
 */
fun isNullOrEmpty(value: String?) : Boolean = value == EMPTY || value == null