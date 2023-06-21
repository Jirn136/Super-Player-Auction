package com.jr.superPlayerAuction.dialog

import android.app.Activity
import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.WindowManager
import com.jr.superPlayerAuction.R
import com.jr.superPlayerAuction.databinding.DialogCreateTeamBinding
import com.jr.superPlayerAuction.utils.getScreenHeight
import com.jr.superPlayerAuction.utils.getScreenWidth
import com.jr.superPlayerAuction.utils.toToast

class CreateTeamDialog(private val activity: Activity) {

    fun showDialog(listener: (String) -> Unit) {
        val dialog = AlertDialog.Builder(activity, R.style.CustomDialog)
        val inflater: LayoutInflater = activity.layoutInflater
        val dialogBinding = DialogCreateTeamBinding.inflate(inflater)
        dialog.apply {
            setCancelable(true)
            setView(dialogBinding.root)
        }
        val alertDialog = dialog.create()
        alertDialog.show()
        adjustDialogWidth(activity, alertDialog)
        dialogBinding.apply {
            btnCreateTeam.setOnClickListener {
                if (edtTeamName.text.toString().isNotEmpty()) {
                    listener(edtTeamName.text.toString())
                    alertDialog.dismiss()

                } else
                    "Please Enter a name".toToast(activity)
            }
        }
    }

    private fun adjustDialogWidth(activity: Activity, alertDialog: AlertDialog?) {
        val params = WindowManager.LayoutParams()
        params.apply {
            params.copyFrom(alertDialog?.window!!.attributes)
            width = (getScreenWidth(activity) * 0.8).toInt()
            height = (getScreenHeight(activity) * 0.23).toInt()
        }
        alertDialog?.window!!.attributes = params
    }
}