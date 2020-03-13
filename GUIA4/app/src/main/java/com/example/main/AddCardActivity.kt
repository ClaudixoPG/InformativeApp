package com.example.main

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
    lateinit var btn_add_card: Button

    lateinit var myListView: ListView

    lateinit var cardList: MutableList<Card>
    lateinit var ref: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_user_activity)

        cardList = mutableListOf()
        ref = FirebaseDatabase.getInstance().getReference("cards")

        // get reference to button
        cardName = findViewById(R.id.cardName)
        cardTopic = findViewById(R.id.cardTopic)
        cardTitle = findViewById(R.id.cardTitle)
        cardExplanation = findViewById(R.id.cardExplanation)
        //listView = findViewById(R.id.listViewXML)
        myListView = findViewById(R.id.myListViewXML)



        btn_add_card = findViewById(R.id.newCard) as Button
        // set on-click listener
        btn_add_card.setOnClickListener {

            saveCard()

        }

        ref.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                if(p0!!.exists())
                {
                    cardList.clear()
                    for(h in p0.children)
                    {
                        val card = h.getValue(Card::class.java)
                        cardList.add(card!!)
                    }
                }

                val adapter = CardAdapter(applicationContext,R.layout.cards,cardList)
                myListView.adapter = adapter

            }


        })

    }

     private  fun  saveCard()
     {
         val name = cardName.text.toString().trim()
         val topic = cardTopic.text.toString().trim()
         val title = cardTitle.text.toString().trim()
         val explanation = cardExplanation.text.toString().trim()
         //val image = editTextName.text.toString.trim()
         if(name.isEmpty()){
             cardName.error = "please, enter a name"
             return
         }
         if(topic.isEmpty()){
             cardTopic.error = "please, enter a topic"
             return
         }
         if(title.isEmpty()){
             cardTitle.error = "please, enter a title"
             return
         }
         if(explanation.isEmpty()){
             cardExplanation.error = "please, enter a explanation"
             return
         }


         val cardsId = ref.push().key
         val card = Card(cardsId!!, name!!, topic!!, title!!, explanation!!)

         ref.child(cardsId).setValue(card).addOnCompleteListener{
             Toast.makeText(applicationContext,"card save",Toast.LENGTH_SHORT).show()
         }

     }

}
