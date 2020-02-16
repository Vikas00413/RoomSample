package com.demo.test.noteapp.util

import android.content.Context
import android.view.View
import android.widget.Toast

/**
 * This class is use for defining Extension function
 * @author vikas kesharvani
 */
/**
 * This method is use for showing toast message
 */
fun Context.toast(message: String){
    Toast.makeText(this, message, Toast.LENGTH_LONG ).show()
}

/**
 * This method is use for show a view
 */
fun View.show(){
    visibility = View.VISIBLE
}

/**
 * This method is use for hide a view
 */
fun View.hide(){
    visibility = View.GONE
}