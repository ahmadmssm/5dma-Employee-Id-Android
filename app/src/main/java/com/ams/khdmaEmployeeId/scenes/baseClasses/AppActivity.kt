package com.ams.khdmaEmployeeId.scenes.baseClasses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.WindowManager
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

open class AppActivity<VB: ViewBinding>(@LayoutRes contentLayoutId: Int, val bindingFactory: (LayoutInflater) -> VB): AppCompatActivity(contentLayoutId) {

    protected val binding by lazy { bindingFactory(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        // Set layout direction corresponding to the App locale.
        super.onCreate(savedInstanceState)
        setContentView(this.binding.root)
        // Prevent bottom bar from going on top of the soft keyboard.
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
    }
}