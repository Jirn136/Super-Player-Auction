package com.jr.superPlayerAuction.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.jr.superPlayerAuction.R
import com.jr.superPlayerAuction.databinding.ActivityAddPlayerBinding
import com.jr.superPlayerAuction.utils.gone
import com.jr.superPlayerAuction.utils.show
import com.jr.superPlayerAuction.utils.showViews

class AddPlayerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddPlayerBinding
    private var checkedItem = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            radioGroupType.setOnCheckedChangeListener { _, checkedId ->
                checkedItem = when (checkedId) {
                    R.id.radio_button_all_rounder -> {
                        showViews(radioGroupBatType, radioGroupBowlType)
                        2
                    }

                    R.id.radio_button_bowler -> {
                        radioGroupBowlType.show()
                        radioGroupBatType.gone()
                        1
                    }

                    else -> {
                        radioGroupBowlType.gone()
                        radioGroupBatType.show()
                        0
                    }
                }
            }
            btnSave.setOnClickListener {

                Log.i("kanaku", "onCreate: $checkedItem")
            }
        }
    }
}