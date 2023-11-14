package com.example.leqcia10

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.leqcia10.databinding.ActivityMainBinding
import java.text.FieldPosition

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val userList = mutableListOf<User>()

        binding.recyclerview.apply {
            setHasFixedSize(true)
            adapter = ListAdapter1(userList,{ position ->
                userList.removeAt(position)
                adapter?.notifyItemRemoved(position)
                Log.d("ItemRecycler","Item removed $position")
            },{position, userToUpdate ->
                updateUserInfo(userToUpdate,position)
            })
        }

        binding.addBtn.setOnClickListener{
            val newUser = User("Empty", "Empty", "Empty@mail.com")
            userList.add(newUser)
            binding.recyclerview.adapter?.notifyItemInserted(userList.size - 1)
        }
    }

    lateinit var userReferance :User
    var positionVal = 0 //default value

    fun updateUserInfo(userToUpdate:User,position: Int){
        val intent = Intent(this, UpdateActivity::class.java)
        userReferance = userToUpdate
        positionVal = position
        activityResultLauncher.launch(intent)
        Log.d("ItemRecycler","A")
    }

    private val activityResultLauncher: ActivityResultLauncher<Intent> =registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data
            val fName = data?.getStringExtra("fName")
            val lName = data?.getStringExtra("lName")
            val email = data?.getStringExtra("mail")

            userReferance.email = email.toString()
            userReferance.fName = fName.toString()
            userReferance.lName = lName.toString()

            binding.recyclerview.adapter?.notifyItemChanged(positionVal)

            Log.d("ItemRecycler","B")

        }else{
            Toast.makeText(this, "Couldn't Update", Toast.LENGTH_SHORT).show()
        }
    }
}