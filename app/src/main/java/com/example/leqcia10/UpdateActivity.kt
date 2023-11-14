package com.example.leqcia10

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import com.example.leqcia10.databinding.ActivityUpdateBinding

class UpdateActivity : AppCompatActivity() {

    lateinit var binding:ActivityUpdateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.updtBtn.setOnClickListener{
            validateInput()
        }

        binding.backbtn.setOnClickListener{
            val resultIntent = Intent()
            setResult(Activity.RESULT_CANCELED, resultIntent)
            finish()
        }
    }

    private fun validateInput(){
        if(validateInputIsEmpty()){
            Toast.makeText(this, "Fill all fields!!!", Toast.LENGTH_SHORT).show()
        }else{
            val mail = binding.emlEdtxt.text.toString()

            if(!isEmailValid(mail)){
                Toast.makeText(this, "Mail isn't correct!!!", Toast.LENGTH_SHORT).show()
                return
            }

            val name = binding.nmEdtxt.text.toString()
            val lstName = binding.lNmEdtxt.text.toString()

            if(ifContainsSpace(name,lstName,mail)){
                Toast.makeText(this, "fields shouldn't contain space!!!", Toast.LENGTH_SHORT).show()
            }

            val resultIntent = Intent()
            resultIntent.putExtra("fName",name)
            resultIntent.putExtra("lName",lstName)
            resultIntent.putExtra("mail",mail)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }

    private fun ifContainsSpace(val1:String,val2:String,val3:String):Boolean{
        return val1.contains(" ")||
               val2.contains(" ")||
               val3.contains(" ")
    }

    private fun validateInputIsEmpty():Boolean{
        return (binding.nmEdtxt.text.toString().isEmpty() ||
                binding.lNmEdtxt.text.toString().isEmpty() ||
                binding.emlEdtxt.text.toString().isEmpty())
    }

    private fun isEmailValid(email: String): Boolean {
        val regexPattern = Regex("^[A-Za-z0-9+_.-]+@(.+)\$")
        return regexPattern.matches(email)
    }
}