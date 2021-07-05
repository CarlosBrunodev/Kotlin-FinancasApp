package com.bruno.financask.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import com.antoni.financask.R
import com.bruno.financask.dao.TransacaoDAO
import com.bruno.financask.model.Tipo
import com.bruno.financask.model.Transacao
import com.bruno.financask.ui.ResumoView
import com.bruno.financask.ui.adapter.ListaTransacoesAdapter
import com.bruno.financask.ui.dialog.AdicionaTransacaoDialog
import com.bruno.financask.ui.dialog.AlteraTransacaoDialog
import kotlinx.android.synthetic.main.activity_lista_transacoes.*


class ListaTransacoesActivity : AppCompatActivity() {


    private val dao = TransacaoDAO()
    private val transacoes = dao.transacoes


    private val viewDaActivity: View by lazy {
        window.decorView
    }


    private val viewGroupDaActivity: ViewGroup by lazy {
        viewDaActivity as ViewGroup
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

        //viewDaActivity = window.decorView

        configuraResumo()
        configuraLista()
        configuraFab()
    }

    private fun configuraFab() {
        lista_transacoes_adiciona_receita.setOnClickListener {
            chamaDialogTransacao(Tipo.RECEITA)
        }

        lista_transacoes_adiciona_despesa.setOnClickListener {
            chamaDialogTransacao(Tipo.DESPESA)
        }
    }

    private fun chamaDialogTransacao(tipo: Tipo) {
        AdicionaTransacaoDialog(
            viewGroupDaActivity,
            this
        )

            .chama(tipo) {transacaoCriada ->
                adiciona(transacaoCriada)
                lista_transacoes_adiciona_menu.close(true)
            }
    }

    private fun adiciona(transacao: Transacao) {
        dao.adiciona(transacao)
        atualizaTransacoes()
    }

    private fun atualizaTransacoes() {
        configuraLista()
        configuraResumo()
    }

    private fun configuraLista() {
        with(lista_transacoes_listview) {
            adapter = ListaTransacoesAdapter(
                transacoes,
                this@ListaTransacoesActivity
            )
            setOnItemClickListener { _, _, position, _ ->
                val transacao = transacoes[position]
                chamaDialogAlteracao(transacao, position)
            }
            setOnCreateContextMenuListener { menu, _, _ ->
                menu.add(Menu.NONE, 1, Menu.NONE, "remover")
            }
        }
    }

    override fun onContextItemSelected(item: MenuItem?): Boolean {
        val idMenu = item?.itemId

        if(idMenu == 1){
            val adapterMenuInfo = item.menuInfo as AdapterView.AdapterContextMenuInfo
            val posicaoTransacao = adapterMenuInfo.position
            remove(posicaoTransacao)
        }
        return super.onContextItemSelected(item)
    }

    private fun remove(posicao: Int) {
        dao.remove(posicao)
        atualizaTransacoes()
    }


    private fun chamaDialogAlteracao(transacao: Transacao, position: Int) {
        AlteraTransacaoDialog(
            viewGroupDaActivity,
            this
        )
            .chama(transacao) { transacaoAlterada ->
                altera(transacaoAlterada, position)
            }
    }

    private fun altera(transacao: Transacao, position: Int) {
        dao.altera(transacao, position)
        atualizaTransacoes()
    }

    private fun configuraResumo() {
        //retorna a view da activity
        val resumoView =
            ResumoView(this, viewDaActivity, transacoes)
        resumoView.atualiza()
    }
}
