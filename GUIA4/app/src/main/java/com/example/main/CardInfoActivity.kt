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


class CardInfoActivity : AppCompatActivity() {

    lateinit var cardName: TextView
    lateinit var cardTopic: TextView
    lateinit var cardTitle: TextView
    lateinit var cardExplanation: TextView
    lateinit var myView: ImageView

    lateinit var button: Button

    var name:String? = ""
    var title:String? = ""
    var topic:String? = ""
    var explanation:String? = ""
    var value:String? = ""

    //lateinit var cardList: ArrayList<Card>
    //lateinit var ref: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.card_info_activity)

        var bundle:Bundle? = intent.extras

        cardName = findViewById(R.id.actualCardName)
        this.name = bundle!!.getString("actualCardName")
        cardName.text = this.name

        cardTitle = findViewById(R.id.actualCardTitle)
        this.title = bundle!!.getString("actualCardTitle");
        cardTitle.text = this.title

        cardTopic = findViewById(R.id.actualCardTopic)
        this.topic = bundle!!.getString("actualCardTopic");
        cardTopic.text = this.topic

        cardExplanation = findViewById(R.id.actualCardExplanation)
        this.explanation = bundle!!.getString("actualCardExplanation");
        cardExplanation.text = this.explanation

        this.value = bundle!!.getString("actualCardImg");

        this.myView = findViewById(R.id.actualImg)
        Toast.makeText(this,R.id.actualImg.toString(),Toast.LENGTH_SHORT).show()
        /*cardValue = findViewById(R.id.actualCardValue)
        this.value = bundle!!.getString("actualCardValue");
        cardValue.text = this.value*/

        //listView = findViewById(R.id.listViewXML)
        //myListView = findViewById(R.id.myListViewXML)
        val resID = resources.getIdentifier(
            this.value,
            "drawable", packageName
        )
        myView.setImageResource(resID)

        button = findViewById(R.id.actualCard) as Button

        button.setOnClickListener {
            val intent = Intent(this@CardInfoActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }


    }

}
