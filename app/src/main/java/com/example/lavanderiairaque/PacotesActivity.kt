package com.example.lavanderiairaque

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2

data class Pacote(
    val nome: String,
    val subtitulo: String,
    val descricao: String,
    val preco: String,
    val corFundo: Int
)

class PacotesActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var dotsLayout: LinearLayout

    private val pacotes = listOf(
        Pacote(
            nome = "Básico",
            subtitulo = "Essencial (Dia a Dia)",
            descricao = "• Foco em quem quer praticidade para as roupas comuns da semana.\n\n" +
                    "• O que inclui: Até 20kg de roupas dobradas (camisetas, bermudas, roupas íntimas, toalhas e lençóis).\n\n" +
                    "• Serviço: Lavar, secar e dobrar com fragrância exclusiva.\n\n" +
                    "• Logística: 2 coletas mensais (10kg por vez).\n\n" +
                    "• Investimento: R\$ 239,90 / mês\n\n" +
                    "• Ideal para: Solteiros ou casais com rotina agitada.",
            preco = "R\$ 249,90 / mês",
            corFundo = 0xFF2B6CDA.toInt()
        ),
        Pacote(
            nome = "Executivo",
            subtitulo = "Impecável",
            descricao = "• Foco em imagem profissional e cuidado com peças que precisam de ferro.\n\n" +
                    "• O que inclui: 20 peças de camisaria (camisas sociais, calças de sarja ou vestidos simples).\n\n" +
                    "• Serviço: Lavagem especial + Passadoria artesanal (Entrega no cabide).\n\n" +
                    "• Logística: Coletas semanais (5 peças por semana).\n\n" +
                    "• Investimento: R\$ 299,00 / mês\n\n" +
                    "• Ideal para: Profissionais que não abrem mão de uma roupa impecável.",
            preco = "R\$ 299,00 / mês",
            corFundo = 0xFF1F4B93.toInt()
        ),
        Pacote(
            nome = "Premium",
            subtitulo = "Cuidado Total",
            descricao = "• A experiência completa da Iraque: roupas do corpo, da casa e renovação.\n\n" +
                    "• O que inclui: 30kg de roupas (lavar/dobrar) + 10 peças de passadoria + 1 Tingimento Profissional por mês.\n\n" +
                    "• Serviço: Tratamento completo + Higienização de 1 Edredom ou Manta incluso.\n\n" +
                    "• Logística: 4 coletas mensais (uma por semana).\n\n" +
                    "• Investimento: R\$ 489,00 / mês\n\n" +
                    "• Ideal para: Famílias que buscam conveniência total e renovação constante do guarda-roupa.",
            preco = "R\$ 299,00 / mês",
            corFundo = 0xFF112E5E.toInt()
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pacotes)

        val isAdmin = intent.getBooleanExtra("IS_ADMIN", false)

        findViewById<ImageView>(R.id.btnMenu).setOnClickListener {
            val i = Intent(this, MenuActivity::class.java)
            i.putExtra("IS_ADMIN", isAdmin)
            startActivity(i)
        }

        viewPager = findViewById(R.id.viewPagerPacotes)
        dotsLayout = findViewById(R.id.dotsLayout)

        val adapter = PacoteAdapter(pacotes) {
            val i = Intent(this, ColetaPacote::class.java)
            i.putExtra("IS_ADMIN", isAdmin)
            startActivity(i)
        }
        viewPager.adapter = adapter
        viewPager.offscreenPageLimit = 1
        viewPager.setPageTransformer { page, position ->
            page.scaleY = if (position == 0f) 1f else 0.92f
            page.alpha = if (position == 0f) 1f else 0.75f
        }

        setupDots(pacotes.size)

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                updateDots(position)
            }
        })
    }

    private fun setupDots(count: Int) {
        dotsLayout.removeAllViews()
        repeat(count) { i ->
            val dot = ImageView(this)
            dot.setImageResource(if (i == 0) R.drawable.dot_active else R.drawable.dot_inactive)
            val params = LinearLayout.LayoutParams(12.dpToPx(), 12.dpToPx())
            params.setMargins(6.dpToPx(), 0, 6.dpToPx(), 0)
            dot.layoutParams = params
            dotsLayout.addView(dot)
        }
    }

    private fun updateDots(selected: Int) {
        for (i in 0 until dotsLayout.childCount) {
            val dot = dotsLayout.getChildAt(i) as ImageView
            dot.setImageResource(if (i == selected) R.drawable.dot_active else R.drawable.dot_inactive)
        }
    }

    private fun Int.dpToPx(): Int = (this * resources.displayMetrics.density).toInt()
}

class PacoteAdapter(
    private val pacotes: List<Pacote>,
    private val onClick: () -> Unit
) : RecyclerView.Adapter<PacoteAdapter.PacoteVH>() {

    inner class PacoteVH(view: View) : RecyclerView.ViewHolder(view) {
        val card: CardView = view.findViewById(R.id.cardPacote)
        val nome: TextView = view.findViewById(R.id.tvNomePacote)
        val subtitulo: TextView = view.findViewById(R.id.tvSubtitulo)
        val descricao: TextView = view.findViewById(R.id.tvDescricao)
        val preco: TextView = view.findViewById(R.id.tvPreco)
        val btn: Button = view.findViewById(R.id.btnMarca)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PacoteVH {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pacote_card, parent, false)
        return PacoteVH(view)
    }

    override fun onBindViewHolder(holder: PacoteVH, position: Int) {
        val pacote = pacotes[position]
        holder.card.setCardBackgroundColor(pacote.corFundo)
        holder.nome.text = pacote.nome
        holder.subtitulo.text = pacote.subtitulo
        holder.descricao.text = pacote.descricao
        holder.preco.text = pacote.preco
        holder.card.setOnClickListener { onClick() }
        holder.btn.setOnClickListener { onClick() }
    }

    override fun getItemCount() = pacotes.size
}