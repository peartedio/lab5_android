package com.peartedio.lab5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private val button: Button by lazy {
        findViewById(R.id.request_button)
    }

    private val progressBar: ProgressBar by lazy {
        findViewById(R.id.loading_indicator)
    }

    private val list: RecyclerView by lazy {
        findViewById(R.id.list)
    }

    private val api: APIInterface by lazy {
        APIClient.client.create(APIInterface::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener {
            button.visibility = View.GONE
            progressBar.visibility = View.VISIBLE
            lifecycleScope.launch(Dispatchers.IO) {
                try {
                    val items = api.getItems()
                    showItems(items)
                } catch (e: Exception) {
                    showError(e)
                }
            }
        }
    }

    private fun showItems(items: List<Item>) {
        lifecycleScope.launch(Dispatchers.Main) {
            list.visibility = View.VISIBLE
            progressBar.visibility = View.GONE
            button.visibility = View.GONE
            list.adapter = ItemAdapter(items)
        }
    }

    private fun showError(e: Exception) {
        lifecycleScope.launch(Dispatchers.Main) {
            button.visibility = View.VISIBLE
            progressBar.visibility = View.GONE
            list.visibility = View.GONE
            Toast.makeText(this@MainActivity, e.localizedMessage, Toast.LENGTH_LONG).show()
        }
    }
}