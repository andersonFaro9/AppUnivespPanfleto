package br.com.appunivespcurso.model

/**
 * Created by faro on 10/19/17.
 */

data class ClientModel(var uid: String = "", var nome:String = "", var email: String = "") {

    override fun toString() = nome

}

