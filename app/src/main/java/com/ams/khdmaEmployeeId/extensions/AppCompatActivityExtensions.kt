package com.ams.khdmaEmployeeId.extensions

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun <F: Fragment>AppCompatActivity.openFragment(fragmentContainerId: Int, fragmentClass: Class<F>, args: Bundle? = null) {
    @Suppress("LocalVariableName") val FRAGMENT_TAG = fragmentClass.simpleName
    val transaction = this.supportFragmentManager.beginTransaction()
    // Replace whatever is in the fragment_container view with this fragment
    transaction.replace(fragmentContainerId, fragmentClass, args, FRAGMENT_TAG)
    // Add the transaction to the back stack so the user can navigate back
    // Add only if the fragment is not already added.
    // if (this.supportFragmentManager.findFragmentByTag(FRAGMENT_TAG) != null) {
    transaction.addToBackStack(FRAGMENT_TAG)
    // }
    // Commit the transaction
    transaction.commit()
}