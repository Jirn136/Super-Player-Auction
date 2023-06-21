package com.jr.superPlayerAuction.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.jr.superPlayerAuction.adapter.TeamAdapter
import com.jr.superPlayerAuction.databinding.ActivityCreateTeamBinding
import com.jr.superPlayerAuction.dialog.CreateTeamDialog
import com.jr.superPlayerAuction.model.Team
import com.jr.superPlayerAuction.utils.Constants
import com.jr.superPlayerAuction.utils.TAG
import com.jr.superPlayerAuction.utils.launchActivity
import com.jr.superPlayerAuction.utils.toToast
import com.jr.superPlayerAuction.viewmodels.TeamListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateTeamActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateTeamBinding
    private lateinit var teamAdapter: TeamAdapter

    private val viewModel: TeamListViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateTeamBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.retrieveTeamList()
        initObserver()
        binding.apply {
            fabCreateTeam.setOnClickListener {
                Log.i(TAG, "onCreate: clicked")
                val dialog = CreateTeamDialog(this@CreateTeamActivity)
                dialog.showDialog { teamName ->
                    Log.i(TAG, "onCreate: edt:: $teamName")
                }
            }
        }
    }

    private fun initObserver() {
        viewModel.teamList.observe(this) {
            if (it.isNotEmpty())
                initRecyclerView(it)
            else
                "No Teams found".toToast(this)
        }
    }

    private fun initRecyclerView(teams: ArrayList<Team>) {
        binding.apply {
            rvTeamList.apply {
                layoutManager = LinearLayoutManager(this@CreateTeamActivity)
                teamAdapter = TeamAdapter(teams) { teamName ->
                    launchActivity<PlayerListActivity> {
                        putExtra(Constants.TEAM_NAME, teamName)
                    }
                }
                adapter = teamAdapter
            }
        }
    }
}