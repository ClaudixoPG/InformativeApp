package com.example.main

import android.app.Activity
import android.content.Context
import android.content.Intent
//import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
//import cl.pdm.felipebesoain.gamecharacterlist.Personaje
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fila.view.*
import androidx.core.view.get
import cl.pdm.felipebesoain.gamecharacterlist.Card
import com.google.firebase.FirebaseError
import com.google.firebase.database.*


class AddCardActivity : AppCompatActivity() {

    lateinit var cardName: EditText
    lateinit var cardTopic: EditText
    lateinit var cardTitle: EditText
    lateinit var cardExplanation: EditText
    lateinit var cardValue: EditText
    lateinit var btn_add_card: Button

    lateinit var myListView: ListView

    lateinit var cardList: ArrayList<Card>
    lateinit var ref: DatabaseReference

    var lastActivity: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_user_activity)

        cardList = arrayListOf()
        ref = FirebaseDatabase.getInstance().getReference("cards")

        // get reference to button
        cardName = findViewById(R.id.cardName)
        cardTopic = findViewById(R.id.cardTopic)
        cardTitle = findViewById(R.id.cardTitle)
        cardExplanation = findViewById(R.id.cardExplanation)
        cardValue = findViewById(R.id.cardValue)
        //listView = findViewById(R.id.listViewXML)
        //myListView = findViewById(R.id.myListViewXML)
        myListView = findViewById(R.id.myListViewXML)



        btn_add_card = findViewById(R.id.newCard) as Button
        // set on-click listener
        btn_add_card.setOnClickListener {

            saveCard()

            if(lastActivity == 1) {
                lastActivity = 0
                val newIntent = Intent(this@AddCardActivity, MainActivity::class.java)
                //newIntent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                newIntent.putExtra("cardsList", cardList)
                setResult(Activity.RESULT_OK,newIntent)
                startActivity(newIntent)
                finish()
            }

        }



    }

     private  fun  saveCard()
     {
         val name = cardName.text.toString().trim()
         val topic = cardTopic.text.toString().trim()
         val title = cardTitle.text.toString().trim()
         val explanation = cardExplanation.text.toString().trim()
         val cardValue = cardValue.text.toString().trim()
         //val image = editTextName.text.toString.trim()
         if(name.isEmpty()){
             this.cardName.error = "please, enter a name"
             return
         }
         if(topic.isEmpty()){
             this.cardTopic.error = "please, enter a topic"
             return
         }
         if(title.isEmpty()){
             this.cardTitle.error = "please, enter a title"
             return
         }
         if(explanation.isEmpty()){
             this.cardExplanation.error = "please, enter a explanation"
             return
         }
         if(cardValue.isEmpty()){
             this.cardValue.error = "please, enter a cardValue"
             return
         }


         val cardsId = ref.push().key
         val card = Card(cardsId!!, name!!, topic!!, title!!, explanation!!,cardValue!!)

         ref.child(cardsId).setValue(card).addOnCompleteListener{
             Toast.makeText(applicationContext,"card save",Toast.LENGTH_SHORT).show()
         }
         lastActivity = 1

     }

}
