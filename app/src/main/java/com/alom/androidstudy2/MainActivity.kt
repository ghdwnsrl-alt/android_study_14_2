package com.alom.androidstudy2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.alom.androidstudy2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var memoListAdapter: MemoListAdapter


    private val viewModel: MainViewModel by viewModels {
        ViewModelFactory(
            RepositoryImpl(
                getSharedPreferences("MyPrefs", Context.MODE_PRIVATE),
                RetrofitClient.apiService
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        memoListAdapter = MemoListAdapter()
        binding.recyclerView.adapter = memoListAdapter


        lifecycleScope.launchWhenStarted {
            viewModel.currentMemo.collect { memoList ->
                memoListAdapter.submitList(memoList)
            }
        }


        binding.btnAdd.setOnClickListener {
            val intent = Intent(this, AddMemoActivity::class.java)
            startActivity(intent)
        }
    }
}
