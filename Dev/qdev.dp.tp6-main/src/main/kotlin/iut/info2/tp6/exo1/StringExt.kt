package iut.info2.tp6.exo1

/**
 * méthode indiquant si la chaine de caractère est uniquement composée de
 * lettres en majuscules
 *
 * @return [true] si la chaine est en majuscule
 */
fun String.isFullUpperCase(): Boolean {
    if (!this.all { c: Char -> c.isLetter() }) return false
    if (this.isEmpty()) return false

    var s2=this.uppercase()
    return s2==this
}

/**
 * Méthode indiquant si la chaine de caractères est composée d'un premier
 * caractère en majuscule, suivi des autres caractères en minuscules
 *
 * @return [true] si la chaine respecte le format
 */
fun String.hasAnUppercaseLetterFirst(): Boolean {
    if (!this.all { c: Char -> c.isLetter() }) return false
    if (this.isEmpty()) return false

    var s2=this.uppercase()
    var s3=this.substringAfter(this[0]).lowercase()
    return s2[0]==this[0] && s3==this.substringAfter(this[0])
}

