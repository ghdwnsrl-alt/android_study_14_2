package com.alom.androidstudy2

import android.content.Context
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.alom.androidstudy2.databinding.ActivityAddMemoBinding

class AddMemoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddMemoBinding

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
        binding = ActivityAddMemoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val price = binding.etPrice.text.toString()
            val time = binding.etTime.text.toString()


            val newMemo = Memo(
                title = title,
                price = price,
                imageUrl = "",
                time = time,
                id = (0..9999999).random()
            )


            viewModel.addMemo(newMemo)


            finish()
        }
    }
}
