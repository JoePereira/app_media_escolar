package br.com.appmediaescolar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // reconhecer os elementos atraves do findView
        val nota1 = findViewById<EditText>(R.id.editTextNota1)
        val nota2 = findViewById<EditText>(R.id.editTextNota2)
        val nota3 = findViewById<EditText>(R.id.editTextNota3)
        val nota4 = findViewById<EditText>(R.id.editTextNota4)

        val ivAprovado = findViewById<ImageView>(R.id.imageViewAprovado)
        val ivReprovado = findViewById<ImageView>(R.id.imageViewReprovado)

        val calcular = findViewById<Button>(R.id.calcular)
        val resetar = findViewById<Button>(R.id.resetar)

        val resultadoTextView = findViewById<TextView>(R.id.textViewResult)

        // criando uma funcao para obter a media final entre as 4 notas
        calcular.setOnClickListener{
            //pegando os valores informados e dando o trim para remover os espacos em branco desnecessarios
            val valNota1 = nota1.text.toString().trim()
            val valNota2 = nota2.text.toString().trim()
            val valNota3 = nota3.text.toString().trim()
            val valNota4 = nota4.text.toString().trim()

            //valida se os campos estao vazios
            if(valNota1.isEmpty() || valNota2.isEmpty() || valNota3.isEmpty() || valNota4.isEmpty()){
                Toast.makeText(this, "Atenção! preencha todos os campos", Toast.LENGTH_LONG).show()
            } else{
                // desabilita a edicao dos campos
                nota1.isEnabled = false
                nota2.isEnabled = false
                nota3.isEnabled = false
                nota4.isEnabled = false

                // converte o valor para double
                val doubleNota1 = valNota1.toDouble()
                val doubleNota2 = valNota2.toDouble()
                val doubleNota3 = valNota3.toDouble()
                val doubleNota4 = valNota4.toDouble()

                // realiza a conta para obter a media
                val media = (doubleNota1 + doubleNota2 + doubleNota3 + doubleNota4) / 4.0


                resultadoTextView.text = "Media: $media"

                resultadoTextView.visibility = View.VISIBLE

                if(media > 6.0){
                    ivAprovado.visibility = View.VISIBLE
                }else{
                    ivReprovado.visibility = View.VISIBLE
                }
               hideKeyboard()


            }


        }

        resetar.setOnClickListener{

            nota1.isEnabled = true
            nota2.isEnabled = true
            nota3.isEnabled = true
            nota4.isEnabled = true

            nota1.text.clear()
            nota2.text.clear()
            nota3.text.clear()
            nota4.text.clear()

            resultadoTextView.visibility = View.INVISIBLE
            ivAprovado.visibility = View.INVISIBLE
            ivReprovado.visibility = View.INVISIBLE


            Toast.makeText(this, "Os campos foram limpos!", Toast.LENGTH_LONG).show()

            resultadoTextView.visibility = View.GONE
        }
    }
    private fun hideKeyboard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        val currentFocusView = currentFocus
        currentFocusView?.let {
            imm.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }
}