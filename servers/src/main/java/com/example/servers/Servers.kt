package com.example.servers

import android.content.Context
import top.oneconnectapi.app.api.OneConnect
import java.io.IOException

object Servers {


    private var freeServer = ""
    private var paidServer = ""

    fun initOneConnect(context: Context,key:String) {

        Thread {
            try {
                val oneConnect = OneConnect()
                oneConnect.initialize(
                    context, key
                )
                try {
                    freeServer = oneConnect.fetch(true)
                    paidServer = oneConnect.fetch(false)

                } catch (e: IOException) {
                    e.printStackTrace()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }.start()
    }

    fun getServers(isPaid: Boolean = false) = if (isPaid) paidServer else freeServer

}