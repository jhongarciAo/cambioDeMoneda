package com.jgo.cambiodemoneda

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jgo.cambiodemoneda.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        mainBinding.Boton.setOnClickListener {

            val origen = when (mainBinding.RGOrigen.checkedRadioButtonId) {
                mainBinding.RBO2.id -> mainBinding.RBO2.text.toString()
                mainBinding.RBO3.id -> mainBinding.RBO3.text.toString()
                else -> mainBinding.RBO1.text.toString()
            }
            val destino = when (mainBinding.RGDestino.checkedRadioButtonId) {
                mainBinding.RBD2.id -> mainBinding.RBD2.text.toString()
                mainBinding.RBD3.id -> mainBinding.RBD3.text.toString()
                else -> mainBinding.RBD1.text.toString()
            }

            if (mainBinding.etValorAConvertir.text.isNotEmpty()) {

                var numeritos=mainBinding.etValorAConvertir.text.toString().toDouble()

                if (origen == destino) {
                    mainBinding.tvResultado.text = getString(R.string.moneda_igual)
                } else {
                    if(origen==getString(R.string.op1) ){
                        numeritos *= if(destino==getString(R.string.op2)){
                            factorCOL_ARG
                        }else{
                            factorCOL_CHI
                        }
                    }
                    if(origen==getString(R.string.op2) ){
                        numeritos *= if(destino==getString(R.string.op1)){
                            factorARG_COL
                        }else{
                            factorARG_CHI
                        }
                    }
                    if(origen==getString(R.string.op3) ){
                        numeritos *= if(destino==getString(R.string.op1)){
                            factorCHI_COL
                        }else{
                            factorCHI_ARG
                        }
                    }
                    mainBinding.tvResultado.text=String.format("%.2f", numeritos)
                    reset()
                }
            } else {
                mainBinding.tvResultado.text = getString(R.string.campo_vacio)
            }
        }
    }
    private fun reset() {
        with(mainBinding){
            RBO1.isChecked=true
            RBD1.isChecked=true
            etValorAConvertir.setText(vacio)
        }
    }
}