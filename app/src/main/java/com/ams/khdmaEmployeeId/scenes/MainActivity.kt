package com.ams.khdmaEmployeeId.scenes

import android.os.Bundle
import android.view.WindowManager
import com.ams.khdmaEmployeeId.R
import com.ams.khdmaEmployeeId.databinding.ActivityMainBinding
import com.ams.khdmaEmployeeId.extensions.openFragment
import com.ams.khdmaEmployeeId.scenes.baseClasses.AppActivity
import com.ams.khdmaEmployeeId.scenes.fragments.QRCodeFragment
import com.ams.khdmaEmployeeId.scenes.fragments.SplashFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import kotlin.concurrent.timerTask

class MainActivity: AppActivity<ActivityMainBinding>(R.layout.activity_main, ActivityMainBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.disableScreenCapture()
        this.renderSplashScreen()
        this.renderQRCodeScreen()
    }

    private fun disableScreenCapture() {
        // Disable screenshots, screen recording and mirroring.
        this.window.setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE)
    }

    private fun renderSplashScreen() {
        this.openFragment(R.id.fragment_container_view, SplashFragment::class.java)
    }

    private fun renderQRCodeScreen() {
        Timer().schedule(timerTask {
            CoroutineScope(Dispatchers.Main).launch {
                openFragment(R.id.fragment_container_view, QRCodeFragment::class.java)
            }
        }, 2000)
    }
}