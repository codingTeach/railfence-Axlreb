import javax.swing.JOptionPane

fun railFenceEncrypt(text: String, rails: Int): String {
    val fence = Array(rails) { CharArray(text.length) { '\u0000' } }
    var direction = 1
    var row = 0

    for (i in text.indices) {
        fence[row][i] = text[i]
        row += direction

        if (row == rails - 1 || row == 0) {
            direction *= -1
        }
    }

    val encryptedText = StringBuilder()
    for (i in 0 until rails) {
        for (j in text.indices) {
            if (fence[i][j] != '\u0000') {
                encryptedText.append(fence[i][j])
            }
        }
    }

    return encryptedText.toString()
}

fun railFenceDecrypt(text: String, rails: Int): String {
    val fence = Array(rails) { CharArray(text.length) { '\u0000' } }
    var direction = 1
    var row = 0

    for (i in text.indices) {
        fence[row][i] = '*'
        row += direction

        if (row == rails - 1 || row == 0) {
            direction *= -1
        }
    }

    var index = 0
    for (i in 0 until rails) {
        for (j in text.indices) {
            if (fence[i][j] == '*' && index < text.length) {
                fence[i][j] = text[index++]
            }
        }
    }

    val decryptedText = StringBuilder()
    row = 0
    direction = 1

    for (i in text.indices) {
        decryptedText.append(fence[row][i])
        row += direction

        if (row == rails - 1 || row == 0) {
            direction *= -1
        }
    }

    return decryptedText.toString()
}

fun main() {
    val options = arrayOf("Cifrar", "Descifrar", "Salir")
    while (true) {
        val choice = JOptionPane.showOptionDialog(
            null,
            "Seleccione una opción:",
            "Cifrado Rail Fence",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.INFORMATION_MESSAGE,
            null,
            options,
            options[0]
        )

        when (choice) {
            0 -> {
                val text = JOptionPane.showInputDialog("Ingrese el texto a cifrar:")
                val rails = JOptionPane.showInputDialog("Ingrese el número de rieles:").toInt()
                val encryptedText = railFenceEncrypt(text, rails)
                JOptionPane.showMessageDialog(null, "Texto cifrado: $encryptedText")
            }
            1 -> {
                val text = JOptionPane.showInputDialog("Ingrese el texto a descifrar:")
                val rails = JOptionPane.showInputDialog("Ingrese el número de rieles:").toInt()
                val decryptedText = railFenceDecrypt(text, rails)
                JOptionPane.showMessageDialog(null, "Texto descifrado: $decryptedText")
            }
            2 -> return
            else -> return
        }
    }
}