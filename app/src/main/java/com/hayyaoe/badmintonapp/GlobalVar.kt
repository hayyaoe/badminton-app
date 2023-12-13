package com.hayyaoe.badmintonapp

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.hayyaoe.badmintonapp.model.People

@Composable
fun navController(): NavHostController {
    return rememberNavController()
}

fun getResId(resName: String, c: Class<*> = R.drawable::class.java): Int {
    try {
        val idField = c.getDeclaredField(resName)
        idField.isAccessible = true
        return idField.getInt(null)
    } catch (e: Exception) {
        e.printStackTrace()
        val idField = c.getDeclaredField("rafi")
        idField.isAccessible = true
        return idField.getInt(null)
    }
}

fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}
