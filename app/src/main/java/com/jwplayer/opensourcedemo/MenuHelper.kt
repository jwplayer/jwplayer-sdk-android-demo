package com.jwplayer.opensourcedemo

import android.content.Context
import android.content.Intent
import android.view.Menu

object MenuHelper {

    fun fillInMenu(menu: Menu, clazz: Any, context: Context){
        if (clazz::class == JWPlayerViewExample::class){
            menu.add("JWKotlinPlayerViewExample").setOnMenuItemClickListener {
                context.startActivity(Intent(context, JWKotlinPlayerViewExample::class.java))
                true
            }
            menu.add("JWPlayerFragmentExample").setOnMenuItemClickListener {
                context.startActivity(Intent(context, JWPlayerFragmentExample::class.java))
                true
            }
        }else if (clazz::class == JWKotlinPlayerViewExample::class){
            menu.add("JWPlayerViewExample").setOnMenuItemClickListener {
                context.startActivity(Intent(context, JWPlayerViewExample::class.java))
                true
            }
            menu.add("JWPlayerFragmentExample").setOnMenuItemClickListener {
                context.startActivity(Intent(context, JWPlayerFragmentExample::class.java))
                true
            }
        }else if(clazz::class == JWPlayerFragmentExample::class){
            menu.add("JWPlayerViewExample").setOnMenuItemClickListener {
                context.startActivity(Intent(context, JWPlayerViewExample::class.java))
                true
            }
            menu.add("JWKotlinPlayerViewExample").setOnMenuItemClickListener {
                context.startActivity(Intent(context, JWKotlinPlayerViewExample::class.java))
                true
            }
        }
    }
}