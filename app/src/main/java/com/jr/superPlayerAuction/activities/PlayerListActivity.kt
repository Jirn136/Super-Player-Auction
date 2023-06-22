package com.jr.superPlayerAuction.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.jr.superPlayerAuction.adapter.PlayerListAdapter
import com.jr.superPlayerAuction.databinding.ActivityPlayerListBinding
import com.jr.superPlayerAuction.model.Player
import com.jr.superPlayerAuction.utils.Constants
import com.jr.superPlayerAuction.utils.launchActivity
import com.jr.superPlayerAuction.utils.toToast
import com.jr.superPlayerAuction.viewmodels.PlayerListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlayerListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPlayerListBinding
    private val playerListViewModel: PlayerListViewModel by viewModels()
    private var teamName: String = Constants.EMPTY_STRING
    private lateinit var playerAdapter: PlayerListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayerListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        teamName = intent.getStringExtra(Constants.TEAM_NAME).toString()
        initObservers()
        initRecyclerView()
        binding.fabCreatePlayer.setOnClickListener {
            launchActivity<AddPlayerActivity> {
                putExtra(Constants.TEAM_NAME, teamName)
            }
        }
    }

    private fun initRecyclerView() {
        binding.apply {
            rvPlayerList.apply {
                layoutManager = LinearLayoutManager(this@PlayerListActivity)
                playerAdapter = PlayerListAdapter(this@PlayerListActivity) { player ->
                    launchActivity<AddPlayerActivity> {
                        putExtra("player", player)
                    }
                }
                adapter = playerAdapter
            }
        }
    }

    private fun initObservers() {
        playerListViewModel.retrievePlayerList.observe(this) {
            if (it.isNotEmpty()) playerAdapter.submitList(it)
            else "No Player Found".toToast(this)
        }
    }

    override fun onResume() {
        super.onResume()
        playerListViewModel.retrievePlayerList(teamName = teamName)
    }
}