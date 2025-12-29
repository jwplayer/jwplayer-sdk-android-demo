package com.jwplayer.opensourcedemo

import android.content.Context
import android.content.Intent
import android.view.Menu

object MenuHelper {

    fun fillInMenu(menu: Menu, clazz: Any, context: Context){
        if (clazz::class == JWPlayerViewExample::class){
            menu.add("JWKotlinPlayerViewExample").setOnMenuItemClickListener {
                val intent = Intent(context, JWKotlinPlayerViewExample::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                context.startActivity(intent)
                true
            }
            menu.add("JWPlayerFragmentExample").setOnMenuItemClickListener {
                val intent = Intent(context, JWPlayerFragmentExample::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                context.startActivity(intent)
                true
            }
        }else if (clazz::class == JWKotlinPlayerViewExample::class){
            menu.add("JWPlayerViewExample").setOnMenuItemClickListener {
                val intent = Intent(context, JWPlayerViewExample::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                context.startActivity(intent)
                true
            }
            menu.add("JWPlayerFragmentExample").setOnMenuItemClickListener {
                val intent = Intent(context, JWPlayerFragmentExample::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                context.startActivity(intent)
                true
            }
        }else if(clazz::class == JWPlayerFragmentExample::class){
            menu.add("JWPlayerViewExample").setOnMenuItemClickListener {
                val intent = Intent(context, JWPlayerViewExample::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                context.startActivity(intent)
                true
            }
            menu.add("JWKotlinPlayerViewExample").setOnMenuItemClickListener {
                val intent = Intent(context, JWKotlinPlayerViewExample::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                context.startActivity(intent)
                true
            }
        }
    }
}