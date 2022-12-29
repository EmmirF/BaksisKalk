package com.example.bakikalkulator

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

private const val BAKSIS_POSTOTAK = 20
class MainActivity : AppCompatActivity() {
    private lateinit var etIznosRacuna: EditText
    private lateinit var seekBarBaksis: SeekBar
    private lateinit var tvIznosBaksisa: TextView
    private lateinit var tvUkupanIznos: TextView
    private lateinit var tvProcenat: TextView
    private lateinit var etBrojOsoba: EditText

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        etIznosRacuna = findViewById(R.id.etIznosRacuna)
        seekBarBaksis = findViewById(R.id.seekBarBaksis)
        tvIznosBaksisa = findViewById(R.id.tvIznosBaksisa)
        tvUkupanIznos = findViewById(R.id.tvUkupanIznos)
        tvProcenat = findViewById(R.id.tvProcenat)
        etBrojOsoba = findViewById(R.id.etBrojOsoba)

        seekBarBaksis.progress = BAKSIS_POSTOTAK
        tvProcenat.text = "$BAKSIS_POSTOTAK"
        seekBarBaksis.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, progress: Int, p2: Boolean) {
                tvProcenat.text = "$progress"
                IzracunajBaksisIUkupno()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}

            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })
        etIznosRacuna.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {
                IzracunajBaksisIUkupno()
            }
        })
        etBrojOsoba.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {
                IzracunajBaksisIUkupno()
            }
        })

    }

    var BrojOsoba=1.00
    private fun IzracunajBaksisIUkupno() {
        if(etIznosRacuna.text.isEmpty())
        {
            tvIznosBaksisa.text=""
            tvUkupanIznos.text=""
            return
        }

       val IznosRacuna= etIznosRacuna.text.toString().toDouble()
        if(etBrojOsoba.text.isEmpty())
        {
            BrojOsoba=1.00
        }
        else {
            BrojOsoba = etBrojOsoba.text.toString().toDouble()
        }
        val ProcenatBaksisa=seekBarBaksis.progress
        val IznosBaksisa=IznosRacuna*ProcenatBaksisa/BrojOsoba/100
        val UkupanIznos=IznosRacuna+(IznosBaksisa*BrojOsoba)
        tvIznosBaksisa.text="%.2f".format(IznosBaksisa)
        tvUkupanIznos.text="%.2f".format(UkupanIznos)
    }
}