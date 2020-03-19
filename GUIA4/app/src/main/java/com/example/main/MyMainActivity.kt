package com.example.main

import android.app.Activity
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
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

        var calendar:Calendar = Calendar.getInstance()

        calendar.set(Calendar.HOUR_OF_DAY,23)
        calendar.set(Calendar.MINUTE,52)
        calendar.set(Calendar.SECOND,0)

        var intent = Intent(applicationContext,Notification_receiver::class.java)

        var pendingIntent: PendingIntent = PendingIntent.getBroadcast(applicationContext,100,intent,PendingIntent.FLAG_UPDATE_CURRENT)

        var alarmManager:AlarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.timeInMillis,AlarmManager.INTERVAL_DAY,pendingIntent)

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
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
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
