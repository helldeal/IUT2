import iut.info2.tp6.exo1.hasAnUppercaseLetterFirst
import iut.info2.tp6.exo1.isFullUpperCase
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class TestStringExt {

    @Test
    fun `ABXDF is FullUpperCase`() {
        assertTrue("ABXDF".isFullUpperCase())
    }

    @Test
    fun `"" is FullUpperCase`() {
        assertFalse("".isFullUpperCase())
    }

    @Test
    fun `X is FullUpperCase`() {
        assertTrue("X".isFullUpperCase())
    }

    @Test
    fun `ABX12F is not FullUpperCase`() {
        assertFalse("ABX12F".isFullUpperCase())
    }

    @Test
    fun `ABxdF is not FullUpperCase`() {
        assertFalse("ABxdF".isFullUpperCase())
    }

    @Test
    fun `Azerty has only an uppercase letter first `() {
        assertTrue("Azerty".hasAnUppercaseLetterFirst())
    }

    @Test
    fun `A has only an uppercase letter first`() {
        assertTrue("A".hasAnUppercaseLetterFirst())
    }

    @Test
    fun `azerty has not only an uppercase letter first`() {
        assertFalse("azerty".hasAnUppercaseLetterFirst())
    }

    @Test
    fun `AzerTy has not only an uppercase letter first `() {
        assertFalse("AzerTy".hasAnUppercaseLetterFirst())
    }

    @Test
    fun `Azerty has not only an uppercase letter first `() {
        assertTrue("Azerty".hasAnUppercaseLetterFirst())
    }
}