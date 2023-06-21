package com.jr.superPlayerAuction.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jr.superPlayerAuction.databinding.ActivityPlayerListBinding

class PlayerListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPlayerListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayerListBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}