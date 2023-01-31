package com.ams.khdmaEmployeeId.scenes.fragments

import android.os.Bundle
import android.view.View
import com.ams.khdmaEmployeeId.R
import com.ams.khdmaEmployeeId.data.apis.CountriesAPIs
import com.ams.khdmaEmployeeId.databinding.FragmentQrcodeBinding
import com.ams.khdmaEmployeeId.scenes.baseClasses.AppFragment
import com.ams.khdmaEmployeeId.utils.QRCode
import com.ams.khdmaEmployeeId.utils.viewBinding.viewBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get

class QRCodeFragment: AppFragment(R.layout.fragment_qrcode) {

    private val binding by viewBinding(FragmentQrcodeBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //
        val bitmap = QRCode.createQRCodeBitmap("123", "456")
        this.binding.qrcodeImageView.setImageBitmap(bitmap)
        //
        CoroutineScope(Dispatchers.Default).launch {
            val apis = get<CountriesAPIs>()
            apis.getCountries().map { it.name }
        }
    }
}