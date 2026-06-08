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

class ColetaPacote: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coleta_pacote) // Use o nome do seu XML

        // 1. Mapeando os componentes do layout
        val etDataHora = findViewById<EditText>(R.id.et_data_hora)
        val spinnerPagamento = findViewById<Spinner>(R.id.spinner_pagamento)
        val btnRealizarPedido = findViewById<Button>(R.id.btn_realizar_pedido1)

        // Variáveis para guardar o que o usuário escolheu
        var dataSelecionada = ""
        var horaSelecionada = ""

        // 2. Evento de clique para abrir o Calendário e Relógio
        etDataHora.setOnClickListener {
            val calendario = Calendar.getInstance()
            val ano = calendario.get(Calendar.YEAR)
            val mes = calendario.get(Calendar.MONTH)
            val dia = calendario.get(Calendar.DAY_OF_MONTH)

            // Abre o seletor de DATA
            val datePicker = DatePickerDialog(this, { _, anoEscolhido, mesEscolhido, diaEscolhido ->
                // O mês no Android começa em 0, então somamos 1
                dataSelecionada = "$diaEscolhido/${mesEscolhido + 1}/$anoEscolhido"

                // Assim que escolhe a data, já abre o seletor de HORA
                val hora = calendario.get(Calendar.HOUR_OF_DAY)
                val minuto = calendario.get(Calendar.MINUTE)

                val timePicker = TimePickerDialog(this, { _, horaEscolhida, minutoEscolhido ->
                    // Formata os minutos para sempre terem 2 dígitos (ex: 05 em vez de 5)
                    val minutoFormatado = String.format("%02d", minutoEscolhido)
                    horaSelecionada = "$horaEscolhida:$minutoFormatado"

                    // Preenche o campo de texto na tela para o usuário ver
                    etDataHora.setText("$dataSelecionada às $horaSelecionada")

                }, hora, minuto, true) // true para formato 24h

                timePicker.show()

            }, ano, mes, dia)

            datePicker.show()
        }

        // Substitua o bloco antigo do Spinner por este trecho único e limpo:
        val listaPagamentos = listOf(
            "Selecione o pagamento...",
            "Pix",
            "Cartão de Crédito",
            "Cartão de Débito",
            "Dinheiro"
        )

// Usamos um layout nativo um pouco mais espaçado e elegante para caixas customizadas
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, listaPagamentos)

        spinnerPagamento.adapter = adapter

        // 3. Ação do Botão "Realizar Pedido" com validação
        btnRealizarPedido.setOnClickListener {
            val formaPagamento = spinnerPagamento.selectedItem.toString()

            // Validações simples para o usuário não deixar vazio
            if (etDataHora.text.isEmpty()) {
                Toast.makeText(this, "Por favor, escolha a data e o horário!", Toast.LENGTH_SHORT).show()
            } else if (spinnerPagamento.selectedItemPosition == 0) {
                Toast.makeText(this, "Por favor, selecione uma forma de pagamento!", Toast.LENGTH_SHORT).show()
            } else {
                // Se tudo estiver preenchido, vai para a tela final do WhatsApp
                val intent = Intent(this, FinalPedido::class.java)

                // (Opcional) Passando os dados salvos para a próxima tela se precisar
                intent.putExtra("AGENDAMENTO", etDataHora.text.toString())
                intent.putExtra("PAGAMENTO", formaPagamento)

                startActivity(intent)
            }
        }
    }
}