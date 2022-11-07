package com.example.flowfunctions

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.flowfunctions.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    private val mViewModel:MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mViewModel.setCountDownValue(20)

        binding.clickBtn.setOnClickListener {
           collectTimer()
        }

        callCountDownTimer={value->
            binding.nextBtn.text = value.toString()
        }

        binding.nextBtn.setOnClickListener {
            callCountDownTimer?.invoke(10)
          //  startActivity(Intent(this,MainActivity2::class.java))
        }

    }

    private fun collectTimer() {
        CoroutineScope(Dispatchers.Main).launch {
            mViewModel.countDownTimer.collect { time ->
                Toast.makeText(this@MainActivity, "" + time, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
