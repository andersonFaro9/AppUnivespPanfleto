package br.com.appunivespcurso.interfaces

import br.com.appunivespcurso.activitys.ListClientActivity

/**
 * Created by faro on 12/8/17.
 */
interface ICrudListClientActivity : IListClientActivity {
    fun ListClientActivity.addCliente(): Boolean
    fun ListClientActivity.updateCliente() : Boolean
    fun ListClientActivity.deleteCliente() : Boolean
}