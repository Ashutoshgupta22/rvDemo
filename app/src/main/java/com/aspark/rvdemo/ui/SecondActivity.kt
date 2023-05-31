package com.aspark.rvdemo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.aspark.rvdemo.ImagesAdapter
import com.aspark.rvdemo.SecondActivityViewModel
import com.aspark.rvdemo.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding
    private val viewModel: SecondActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.progressBar.visibility = View.VISIBLE

        binding.rvImages.apply {

            layoutManager = LinearLayoutManager(this@SecondActivity,
                LinearLayoutManager.VERTICAL, false)
        }

        viewModel.getImages()

        viewModel.imageList.observe(this) {

            it?.let {
                binding.progressBar.visibility = View.GONE
                binding.tvError.visibility = View.GONE
                binding.rvImages.adapter = ImagesAdapter(it)

            }
        }

        viewModel.errorMessage.observe(this) {

            binding.tvError.apply {
                visibility = View.VISIBLE
                text = it
            }

            binding.progressBar.visibility = View.GONE

        }




    }
}