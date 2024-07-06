//package com.example.pizzaria.utils

package com.example.pizzaria.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.pizzaria.model.PedidoItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class MySharedPreferencesManager(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("my_shared_preferences", Context.MODE_PRIVATE)

    fun savePedidoItemList(key: String, list: List<PedidoItem>) {
        val editor = sharedPreferences.edit()
        val json = Gson().toJson(list)
        editor.putString(key, json)
        editor.apply()
    }

    fun getPedidoItemList(key: String): List<PedidoItem> {
        val json = sharedPreferences.getString(key, null)
        if (json != null) {
            val type = object : TypeToken<List<PedidoItem>>() {}.type
            return Gson().fromJson(json, type)
        }
        return emptyList()
    }
}


//class MySharedPreferencesManager(context: Context) {
//
//    private val prefs: SharedPreferences = context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
//
//    fun saveList(key: String, list: List<String>) {
//        val editor = prefs.edit()
//        editor.putString(key, list.joinToString(","))
//        editor.apply()
//    }
//
//    fun getList(key: String): List<String> {
//        val listString = prefs.getString(key, "") ?: ""
//        return if (listString.isEmpty()) {
//            emptyList()
//        } else {
//            listString.split(",").map { it.trim() }
//        }
//    }
//}


//
//import android.content.Context
//import android.content.SharedPreferences
//import com.google.gson.Gson
//import com.google.gson.reflect.TypeToken
//
//class MySharedPreferencesManager(private val context: Context) {
//    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
//    private val gson = Gson()
//
//    fun saveList(key: String, list: List<String>) {
//        val editor = sharedPreferences.edit()
//        val json = gson.toJson(list)
//        editor.putString(key, json)
//        editor.apply()
//    }
//
//    fun getList(key: String): List<String> {
//        val json = sharedPreferences.getString(key, null)
//        return if (json != null) {
//            val type = object : TypeToken<List<String>>() {}.type
//            gson.fromJson(json, type)
//        } else {
//            emptyList()
//        }
//    }
//}