package com.judy.self.regulation.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.judy.self.regulation.databinding.DialogStatusBinding

class StatusDialog(context: Context, val messege: String) : Dialog(context) {
    private lateinit var binding: DialogStatusBinding

    init {
        window!!.attributes.apply {
            this.height = 500
            this.width = 300
            this.dimAmount = 0.8F
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogStatusBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setContent(messege)
    }

    /** 設定 dialog 文字 */
    private fun setContent(messege: String) {
        binding.MessegeTextview.text = messege
    }


}