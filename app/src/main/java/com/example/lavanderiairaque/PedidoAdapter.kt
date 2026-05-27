package com.example.lavanderiairaque

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PedidoAdapter(
    private val dataSet: MutableList<Pedido>,
    private val api: ApiService
) : RecyclerView.Adapter<PedidoAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val descricao: TextView = view.findViewById(R.id.tvDescricaoPedido)
        val status: TextView    = view.findViewById(R.id.tvStatusPedido)
        val data: TextView      = view.findViewById(R.id.tvDataPedido)
        val btnEditar: Button   = view.findViewById(R.id.btnEditarPedido)
        val btnDeletar: Button  = view.findViewById(R.id.btnDeletarPedido)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_pedido, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val p = dataSet[position]
        holder.descricao.text = p.PEDIDO_DESCRICAO
        holder.status.text    = "Status: ${p.PEDIDO_STATUS}"
        holder.data.text      = "Data: ${p.PEDIDO_DATA}"

        holder.btnEditar.setOnClickListener {
            it.context.startActivity(Intent(it.context, EditarPedidoActivity::class.java).apply {
                putExtra("PEDIDO_ID",        p.PEDIDO_ID)
                putExtra("PEDIDO_DESCRICAO", p.PEDIDO_DESCRICAO)
                putExtra("PEDIDO_STATUS",    p.PEDIDO_STATUS)
            })
        }

        holder.btnDeletar.setOnClickListener {
            api.deletarPedido(p.PEDIDO_ID).enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    val pos = holder.adapterPosition
                    if (pos != RecyclerView.NO_POSITION) {
                        dataSet.removeAt(pos)
                        notifyItemRemoved(pos)
                    }
                    Toast.makeText(holder.itemView.context, "Pedido deletado!", Toast.LENGTH_SHORT).show()
                }
                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Toast.makeText(holder.itemView.context, "Erro ao deletar", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    override fun getItemCount() = dataSet.size
}
