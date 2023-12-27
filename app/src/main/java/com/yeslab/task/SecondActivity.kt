package com.yeslab.task

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yeslab.task.databinding.ActivityMainBinding
import com.yeslab.task.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}