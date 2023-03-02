package iut.info2.tp6.exo1

import java.awt.Dimension
import java.awt.GridLayout
import javax.swing.*

fun main() {
    val fenetre = JFrame("Essai classe Personne")
    val texteSaisi = JTextField(15)
    val boutonOK = JButton("OK")
    var resultat = JLabel("")
    fenetre.contentPane.layout = GridLayout(3, 1)
    fenetre.contentPane.add(texteSaisi)
    fenetre.contentPane.add(boutonOK)
    fenetre.contentPane.add(resultat)
    boutonOK.addActionListener {
        lateinit var personne: PersonneA
        try {
            personne= PersonneA.donnePersonneSaisie(texteSaisi.text)
            resultat.text=personne.toString()
        }
        catch (e:PersonneException){
            JOptionPane.showMessageDialog(fenetre,"ENTREE INVALIDE","EXCEPTION",JOptionPane.ERROR_MESSAGE)
        }

    }
    fenetre.preferredSize = Dimension(200, 150)
    fenetre.setLocation(200, 200)
    fenetre.defaultCloseOperation = JFrame.DISPOSE_ON_CLOSE
    fenetre.pack()
    fenetre.isVisible = true
}