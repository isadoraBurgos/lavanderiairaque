package com.example.lavanderiairaque

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.net.URLEncoder

class FinalPedido : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_final_pedido)

        // 1. Mapeia o botão do WhatsApp do seu XML
        val btnWhatsapp = findViewById<Button>(R.id.btn_ir_whatsapp)

        // 2. (Opcional) Recupera os dados enviados pelas telas anteriores
        val agendamento = intent.getStringExtra("AGENDAMENTO") ?: ""
        val pagamento = intent.getStringExtra("PAGAMENTO") ?: ""
        val quantidade = intent.getStringExtra("QUANTIDADE") ?: ""
        val descricao = intent.getStringExtra("DESCRICAO") ?: ""
        val nomeEmpresa = intent.getStringExtra("EMPRESA_NOME") ?: ""

        // 3. Configura o clique do botão
        btnWhatsapp.setOnClickListener {

            // INSIRA AQUI O NÚMERO DA SUA LAVANDERIA (com DDD e código do país 55)
            // Exemplo para o número (11) 99999-9999 o código fica: "5511999999999"
            val numeroTelefone = "5511999999999"

            // 4. Monta o texto da mensagem dependendo de qual tela veio
            val textoMensagem = when {
                nomeEmpresa.isNotEmpty() -> {
                    "Olá! Gostaria de confirmar o pedido de Parceria Empresa.\n\n" +
                            "🏢 *Empresa:* $nomeEmpresa\n" +
                            "📦 *Quantidade:* $quantidade\n" +
                            "📝 *Descrição:* $descricao"
                }
                quantidade.isNotEmpty() -> {
                    "Olá! Gostaria de confirmar meu pedido de Solicitar Coleta.\n\n" +
                            "📦 *Quantidade:* $quantidade\n" +
                            "📝 *Descrição:* $descricao\n" +
                            "📅 *Agendamento:* $agendamento\n" +
                            "💳 *Pagamento:* $pagamento"
                }
                else -> {
                    "Olá! Gostaria de confirmar meu pedido de Coleta de Pacote.\n\n" +
                            "📅 *Agendamento:* $agendamento\n" +
                            "💳 *Pagamento:* $pagamento"
                }
            }

            try {
                // O URLEncoder serve para formatar os espaços e quebras de linha para o formato de link da web
                val mensagemFormatada = URLEncoder.encode(textoMensagem, "UTF-8")

                // Cria a URL que o Android usa para chamar o WhatsApp
                val urlCompleta = "https://api.whatsapp.com/send?phone=$numeroTelefone&text=$mensagemFormatada"

                // Cria a Intent para abrir o navegador ou o aplicativo do WhatsApp instalado
                val intentWhatsapp = Intent(Intent.ACTION_VIEW)
                intentWhatsapp.data = Uri.parse(urlCompleta)

                startActivity(intentWhatsapp)

            } catch (e: Exception) {
                // Caso aconteça algum erro inesperado
                Toast.makeText(this, "Não foi possível abrir o WhatsApp.", Toast.LENGTH_SHORT).show()
                e.printStackTrace()
            }
        }
    }
}