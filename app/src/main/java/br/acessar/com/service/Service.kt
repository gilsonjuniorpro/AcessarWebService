package br.acessar.com.service

import br.acessar.com.pojo.Post
import br.acessar.com.pojo.Retorno
import com.google.gson.Gson
import java.net.URL

object Service {

    fun listPosts(): Any? {
        val url = "http://www.projectconnect.com.br/make/api/post/list/list.php"

        val json = URL(url).readText()

        val gson = Gson()

        val retorno = gson.fromJson(json, Retorno::class.java)

        val posts : List<Post> = retorno.list

        return posts
    }
}