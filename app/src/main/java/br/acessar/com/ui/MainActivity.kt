package br.acessar.com.ui

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import br.acessar.com.R
import br.acessar.com.pojo.Post
import br.acessar.com.service.Service
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Thread(Runnable {
            var postList = Service.listPosts()

            this@MainActivity.runOnUiThread(java.lang.Runnable {
                var postsAdapter = PostsAdapter(this, (postList as ArrayList<Post>?)!!)
                listPosts.adapter = postsAdapter
            })
        }).start()
    }


    inner class PostsAdapter : BaseAdapter {

        private var postList = ArrayList<Post>()
        private var context: Context? = null

        constructor(context: Context, postList: ArrayList<Post>) : super() {
            this.postList = postList
            this.context = context
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {

            val view: View?
            val viewHolder: ViewHolder

            if (convertView == null) {
                view = layoutInflater.inflate(R.layout.post, parent, false)
                viewHolder = ViewHolder(view)
                view.tag = viewHolder
            } else {
                view = convertView
                viewHolder = view.tag as ViewHolder
            }

            viewHolder.tvTitle.text = postList[position].title
            viewHolder.tvContent.text = postList[position].address

            return view
        }

        override fun getItem(position: Int): Any {
            return postList[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return postList.size
        }
    }

    private class ViewHolder(view: View?) {
        val tvTitle: TextView
        val tvContent: TextView

        init {
            this.tvTitle = view?.findViewById(R.id.tvTitle) as TextView
            this.tvContent = view?.findViewById(R.id.tvContent) as TextView
        }
    }


}
