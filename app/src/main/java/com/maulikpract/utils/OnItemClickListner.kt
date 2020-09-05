package com.maulikpract.utils

import android.view.View

interface OnItemClickListner {
    fun onItemClick(view : View,data : Any,position: Int)
}