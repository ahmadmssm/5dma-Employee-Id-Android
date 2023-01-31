package com.ams.khdmaEmployeeId.utils

import android.graphics.Bitmap
import android.graphics.Color
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.qrcode.QRCodeWriter

// https://stackoverflow.com/a/69219318/6927433
object QRCode {

    fun createQRCodeBitmap(ssid: String, password: String): Bitmap {
        val size = 512 //pixels
        // https://github.com/zxing/zxing/wiki/Barcode-Contents
        val qrCodeContent = "WIFI:S:$ssid;T:WPA;P:$password;;"
        val hints = hashMapOf<EncodeHintType, Int>().also { it[EncodeHintType.MARGIN] = 1 } // Make the QR code buffer border narrower
        val bits = QRCodeWriter().encode(qrCodeContent, BarcodeFormat.QR_CODE, size, size, hints)
        return Bitmap.createBitmap(size, size, Bitmap.Config.RGB_565).also {
            for (x in 0 until size) {
                for (y in 0 until size) {
                    it.setPixel(x, y, if (bits[x, y]) Color.BLACK else Color.WHITE)
                }
            }
        }
    }
}