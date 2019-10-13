package com.appetiser.testapp.presenter.common.extension

import android.os.Bundle
import androidx.navigation.NavController

fun NavController.ensureNavigate(destinationId: Int,bundle: Bundle? = null, currentScreenId: Int, onNavigateValid: (() -> Unit)? = null){
    if (this.currentDestination?.id == currentScreenId){
        onNavigateValid?.invoke()
        if (bundle == null){
            navigate(destinationId)
        }else {
            navigate(destinationId,bundle)
        }
    }
}