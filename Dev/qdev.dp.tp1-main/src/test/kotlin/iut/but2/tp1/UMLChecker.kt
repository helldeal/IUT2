package iut.but2.tp1

import org.junit.jupiter.api.Assertions.*
import kotlin.reflect.KClass
import kotlin.reflect.KVisibility
import kotlin.reflect.full.allSuperclasses
import kotlin.reflect.full.declaredMemberFunctions
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.primaryConstructor

/**
 * @author : Arnaud Lanoix
 * version du 30/03/2022
 */

class UMLChecker(kClass: KClass<*>) {

    private val kClass: KClass<*>

    init {
        this.kClass = kClass
    }

    fun isOpen(ok: Boolean = true) {
        if (ok)
            assertTrue(
                kClass.isOpen,
                "${kClass.simpleName} doit etre open"
            )
        else
            assertFalse(
                kClass.isOpen,
                "${kClass.simpleName} ne doit pas etre open"
            )
    }

    fun isAbstract(ok: Boolean = true) {
        if (ok)
            assertTrue(
                kClass.isAbstract,
                "${kClass.simpleName} doit etre abstraite"
            )
        else
            assertFalse(
                kClass.isAbstract,
                "${kClass.simpleName} ne doit pas etre abstraite"
            )
    }

    fun extendNothing() {
        assertTrue(
            1 == kClass.allSuperclasses.size,
            "${kClass.simpleName} ne doit rien etendre"
        )
    }

    fun extend(vararg extends: String) {
        for (extend in extends) {
            val extension = kClass.allSuperclasses.find { it.simpleName == extend }
            if (extension == null)
                fail<Any>("${kClass.simpleName} doit étendre ou réaliser $extend")
        }
    }

    fun constructorCheck(
        constructVisibility: KVisibility,
        vararg constructParamAndType: String
    ) {
        val construct = kClass.primaryConstructor
        if (construct == null)
            fail<Any>("${kClass.simpleName} doit avoir un constructeur primaire")
        else {
            assertEquals(
                constructVisibility, construct.visibility,
                "La visibilité du constructeur doit etre $constructVisibility"
            )
            var i = 0
            while (i < constructParamAndType.size) {
                val param = construct.parameters.find { it.name == constructParamAndType[i] }
                if (param == null)
                    fail<Any>("Lr constructeur de ${kClass.simpleName} doit avoir un parametre ${constructParamAndType[i]}")
                if (constructParamAndType[i + 1] != param?.type.toString())
                    fail<Any>("Le parametre ${constructParamAndType[i]} doit etre typé ${constructParamAndType[i + 1]}")
                i += 2
            }
        }
    }

    fun attributeCheck(
        attName: String,
        attType: String,
        attVisibility: KVisibility = KVisibility.PRIVATE
    ) {
        val member = kClass.declaredMemberProperties.find { it.name == attName }
        if (member == null)
            fail<Any>("${kClass.simpleName} doit avoir un attribut $attName")
        else
            assertAll(
                {
                    assertEquals(
                        attVisibility, member.visibility,
                        "La visibilité de $attName doit etre $attVisibility"
                    )
                },
                {
                    assertEquals(
                        attType, member.returnType.toString(),
                        "Le type de $attName oit être $attType"
                    )
                },
            )
    }

    fun noAttribute() {
        val member = kClass.declaredMemberProperties
        assertEquals(0, member.size, "${kClass.simpleName} ne doit declarer aucun attribut")
    }

    fun methodCheck(
        methName: String,
        methVisibility: KVisibility = KVisibility.PUBLIC,
        isOpenOrOverride: Boolean = false,
        isAbstract: Boolean = false
    ) {
        val method = kClass.declaredMemberFunctions.find { it.name == methName }
        if (method == null)
            fail<Any>("${kClass.simpleName} doit declarer une methode $methName")
        else
            assertAll(
                {
                    assertEquals(
                        methVisibility, method.visibility,
                        "La visibilité de $methName doit etre $methVisibility"
                    )
                },
                { assertEquals(isOpenOrOverride, method.isOpen, "$methName doit etre open/override") },
                { assertEquals(isAbstract, method.isAbstract, "$methName doit etre abstraite") },
            )
    }

    fun methodCheckParams(
        methName: String,
        vararg methParamAndType: String
    ) {
        val method = kClass.declaredMemberFunctions.find { it.name == methName }
        if (method == null)
            fail<Any>("${kClass.simpleName} doit declarer une methode $methName")
        else {
            var i = 0
            while (i < methParamAndType.size) {
                val param = method.parameters.find { it.name == methParamAndType[i] }
                if (param == null)
                    fail<Any>("La methode $methName doit avoir un parametre ${methParamAndType[i]}")
                if (methParamAndType[i + 1] != param?.type.toString())
                    fail<Any>("Le parametre ${methParamAndType[i]} doit etre typé ${methParamAndType[i + 1]}")
                i += 2
            }
        }
    }

    fun noMethod() {
        val member = kClass.declaredMemberFunctions
        assertEquals(0, member.size, "${kClass.simpleName} ne doit declarer aucune methode")
    }

}





