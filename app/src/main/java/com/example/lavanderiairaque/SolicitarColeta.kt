package com.example.lavanderiairaque

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar

class SolicitarColeta: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_solicitar_coleta)

        // 1. Mapeando os componentes do layout (Ajustado com os IDs corretos do seu XML)
        val etDataHora = findViewById<EditText>(R.id.et_data_hora) // Mudou o ID
        val spinnerQtd = findViewById<Spinner>(R.id.spinner_qtd)
        val spinnerPagamento = findViewById<Spinner>(R.id.spinner_pagamento) // Mudou o ID
        val btnRealizarPedido = findViewById<Button>(R.id.btn_realizar_pedido2) // Mudou o ID
        val etDescricao = findViewById<EditText>(R.id.et_descricao) // Adicionado para validação se quiser

        // Variáveis para guardar o que o usuário escolheu
        var dataSelecionada = ""
        var horaSelecionada = ""

        // Lista de Quantidades
        val listaQtd = listOf("Selecione a quantidade...", "1 a 5 peças", "5 a 10 peças", "Mais de 10 peças")
        spinnerQtd.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, listaQtd)

        // Lista de Pagamentos
        val listaPagamentos = listOf(
            "Selecione o pagamento...",
            "Pix",
            "Cartão de Crédito",
            "Cartão de Débito",
            "Dinheiro"
        )
        spinnerPagamento.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, listaPagamentos)

        // 2. Evento de clique para abrir o Calendário e Relógio
        etDataHora.setOnClickListener {
            val calendario = Calendar.getInstance()
            val ano = calendario.get(Calendar.YEAR)
            val mes = calendario.get(Calendar.MONTH)
            val dia = calendario.get(Calendar.DAY_OF_MONTH)

            val datePicker = DatePickerDialog(this, { _, anoEscolhido, mesEscolhido, diaEscolhido ->
                dataSelecionada = "$diaEscolhido/${mesEscolhido + 1}/$anoEscolhido"

                val hora = calendario.get(Calendar.HOUR_OF_DAY)
                val minuto = calendario.get(Calendar.MINUTE)

                val timePicker = TimePickerDialog(this, { _, horaEscolhida, minutoEscolhido ->
                    val minutoFormatado = String.format("%02d", minutoEscolhido)
                    horaSelecionada = "$horaEscolhida:$minutoFormatado"

                    etDataHora.setText("$dataSelecionada às $horaSelecionada")

                }, hora, minuto, true)

                timePicker.show()

            }, ano, mes, dia)

            datePicker.show()
        }

        // 3. Ação do Botão "Realizar Pedido" com validação
        btnRealizarPedido.setOnClickListener {
            val formaPagamento = spinnerPagamento.selectedItem.toString()
            val quantidadepecas = spinnerQtd.selectedItem.toString()

            // Validações ajustadas incluindo a quantidade e descrição
            if (spinnerQtd.selectedItemPosition == 0) {
                Toast.makeText(this, "Por favor, selecione a quantidade de peças!", Toast.LENGTH_SHORT).show()
            } else if (etDescricao.text.isEmpty()) {
                Toast.makeText(this, "Por favor, dê uma breve descrição das roupas!", Toast.LENGTH_SHORT).show()
            } else if (etDataHora.text.isEmpty()) {
                Toast.makeText(this, "Por favor, escolha a data e o horário!", Toast.LENGTH_SHORT).show()
            } else if (spinnerPagamento.selectedItemPosition == 0) {
                Toast.makeText(this, "Por favor, selecione uma forma de pagamento!", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, FinalPedido::class.java)

                // Passando todos os dados preenchidos para o WhatsApp final
                intent.putExtra("QUANTIDADE", quantidadepecas)
                intent.putExtra("DESCRICAO", etDescricao.text.toString())
                intent.putExtra("AGENDAMENTO", etDataHora.text.toString())
                intent.putExtra("PAGAMENTO", formaPagamento)

                startActivity(intent)
            }
        }
    }
}