package com.example.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.firebase.ui.auth.AuthUI
import java.util.*
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.my_activity_main.*


class MyMainActivity : AppCompatActivity() {


    //var listPersonaje = ArrayList<Personaje>()
    //var ml: myLista? = null

    //lateint
    private val MY_REQUEST_CODE: Int = 117
    lateinit var providers : List<AuthUI.IdpConfig>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.my_activity_main)

        providers = Arrays.asList<AuthUI.IdpConfig> (
            AuthUI.IdpConfig.EmailBuilder().build(), //Email Login
            AuthUI.IdpConfig.GoogleBuilder().build(), //Google Login
            AuthUI.IdpConfig.PhoneBuilder().build() //Phone Login
            //AuthUI.IdpConfig.FacebookBuilder().build(), //facebook Login (primero hay que encontrar la ruta del jrk)
        )

        ShowSignInOptions()

        btn_sign_out.setOnClickListener(){
            //sign out
            AuthUI.getInstance().signOut(this@MyMainActivity).
                addOnCompleteListener{
                    btn_sign_out.isEnabled = false
                    ShowSignInOptions()
                }.addOnFailureListener{
                e->Toast.makeText(this@MyMainActivity,e.message,Toast.LENGTH_SHORT).show()
            }

        }


    }

    private fun ShowSignInOptions()
    {
        startActivityForResult(AuthUI.getInstance().
            createSignInIntentBuilder().
            setAvailableProviders(providers).
            setTheme(R.style.main_Theme).
            build(),MY_REQUEST_CODE)
    }

    //add
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == MY_REQUEST_CODE) //and user exist in database (need add this sentence)
        {
            val response = IdpResponse.fromResultIntent(data)
            if(resultCode == Activity.RESULT_OK)
            {
                val user = FirebaseAuth.getInstance().currentUser //actual user
                Toast.makeText(this,"" + user!!.email,Toast.LENGTH_LONG).show()

                btn_sign_out.isEnabled = true

                val intent = Intent(this@MyMainActivity, MainActivity::class.java)
                    /*.apply {
                    putExtra("entering data", "work")
                }*/
                //intent.putExtra("EXTRA_SESSION_ID", sessionId)
                startActivity(intent)


            }else
            {
                Toast.makeText(this,""+ response!!.error!!.message,Toast.LENGTH_SHORT).show()
            }

        }

    }



}
