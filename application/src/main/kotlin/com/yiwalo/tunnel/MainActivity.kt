package com.yiwalo.tunnel

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : Activity() {

    private var connected = false
    private lateinit var status: TextView
    private lateinit var connectButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.statusBarColor = Color.rgb(5, 17, 38)
        window.navigationBarColor = Color.rgb(5, 17, 38)

        val background = Color.rgb(5, 17, 38)
        val cyan = Color.rgb(0, 174, 239)
        val green = Color.rgb(0, 230, 118)

        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(28, 24, 28, 24)
            setBackgroundColor(background)
        }

        val brand = TextView(this).apply {
            text = "YIWALO TUNNEL"
            textSize = 30f
            setTextColor(Color.WHITE)
            typeface = Typeface.DEFAULT_BOLD
            gravity = Gravity.CENTER
        }

        val subtitle = TextView(this).apply {
            text = "Sécurisé • Rapide • Privé"
            textSize = 18f
            setTextColor(cyan)
            gravity = Gravity.CENTER
        }

        connectButton = Button(this).apply {
            text = "⏻"
            textSize = 48f
            setTextColor(green)
            setBackgroundColor(background)
            contentDescription = "Connecter ou déconnecter"
            setOnClickListener {
                connected = !connected
                updateStatus()
            }
        }

        status = TextView(this).apply {
            textSize = 24f
            setTextColor(Color.WHITE)
            typeface = Typeface.DEFAULT_BOLD
            gravity = Gravity.CENTER
        }

        val information = TextView(this).apply {
            text = "IP du VPN : --\n\nLatence : --\n\nDurée : 00:00:00\n\nDébit : --"
            textSize = 18f
            setTextColor(Color.LTGRAY)
            gravity = Gravity.CENTER
        }

        val servers = Button(this).apply {
            text = "🌐  Serveurs"
            setOnClickListener {
                status.text = "Importation des configurations à intégrer"
            }
        }

        val settings = Button(this).apply {
            text = "⚙  Réglages"
            setOnClickListener {
                status.text = "Réglages YIWALO TUNNEL"
            }
        }

        root.addView(brand)
        root.addView(subtitle)
        root.addView(
            connectButton,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 180
            )
        )
        root.addView(status)
        root.addView(information)

        val navigation = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER
        }

        navigation.addView(servers)
        navigation.addView(settings)
        root.addView(navigation)

        setContentView(root)
        updateStatus()
    }

    private fun updateStatus() {
        if (connected) {
            status.text = "Connexion non configurée"
            connectButton.text = "⏻"
        } else {
            status.text = "Déconnecté"
            connectButton.text = "⏻"
        }
    }
}
