package com.example.main

import android.content.Context
import android.content.Intent
//import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
//import cl.pdm.felipebesoain.gamecharacterlist.Personaje
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fila.view.*
import cl.pdm.felipebesoain.gamecharacterlist.Card
import android.app.Activity
import android.net.Uri
import android.widget.ImageButton
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.add_user_activity.view.*


class MainActivity : AppCompatActivity() {


    var cardList = ArrayList<Card>()
    var ml: myLista? = null

    lateinit var ref: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ref = FirebaseDatabase.getInstance().getReference("cards")


        //ml = myLista(this,cardList)
        //listViewXML.adapter = ml



        //val adapter = CardAdapter(applicationContext,R.layout.cards,cardList)
        //listViewXML.adapter = adapter

        // get reference to button
        val btn_add_PJ = findViewById(R.id.newPJ) as Button
        val btn_wsp = findViewById(R.id.wsp_icon) as ImageButton
        // set on-click listener
        btn_add_PJ.setOnClickListener {

            val intent = Intent(this@MainActivity, AddCardActivity::class.java)
            startActivity(intent)
            finish()

        }

        btn_wsp.setOnClickListener{

            val url = "https://api.whatsapp.com/send?phone=$+56 990910719"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }



        /*listViewXML.setOnItemClickListener{ parent, view, position, id ->
            val element = listViewXML.adapter.getItem(position)// The item that was clicked
            listViewXML.remove()
            //val intent = Intent(this, BookDetailActivity::class.java)
            //startActivity(intent)
        }*/

        //listViewXML.setOnItemLongClickListener(AdapterView.OnItemLongClickListener { parent, view, position, id ->
        // TODO Auto-generated method stub

        //listViewXML.adapter.remove(position)

        /*for (x in 0..listViewXML.count)
            {
                if(listViewXML.get(x) == listViewXML[position])
                    listPersonaje.remove(listViewXML.get(x))
            }

            //listViewXML.adapter. .notifyDataSetChanged()

            Toast.makeText(this@MainActivity, "Item Deleted", Toast.LENGTH_LONG).show()

            true
        })
             */

        /*listViewXML.setOnItemLongClickListener { parent, view, position, id ->
            listPersonaje.removeAt(position)
            ml!!.notifyDataSetChanged()
        }*/

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
                //ml = myLista(this,cardList)
                //listViewXML.adapter = ml
                ml = myLista(applicationContext,cardList)
                //val adapter = CardAdapter(applicationContext,R.layout.fila ,cardList)
                listViewXML.adapter = ml
            }
        })

        //

        listViewXML.setOnItemLongClickListener { adapter, view, pos, id ->
            // TODO Auto-generated method stub
            var myCard = cardList[pos]
            ref.child(myCard.cardsId!!).removeValue()
            cardList.removeAt(pos)
            ml!!.notifyDataSetChanged();
            Toast.makeText(this, "Personaje removido.", Toast.LENGTH_SHORT).show()

            true
        }

        listViewXML.setOnItemClickListener { parent, view, position, id ->

            val myIntent = Intent(this@MainActivity, CardInfoActivity::class.java)
            myIntent.putExtra("actualCardName",cardList[position].name)
            myIntent.putExtra("actualCardTitle",cardList[position].title)
            myIntent.putExtra("actualCardTopic",cardList[position].topic)
            myIntent.putExtra("actualCardExplanation",cardList[position].explanation)
            myIntent.putExtra("actualCardImg",cardList[position].img)
            startActivity(myIntent)
            //finish()

            //Toast.makeText(this, listPersonaje[position].des, Toast.LENGTH_SHORT).show()
            //view.descripcion.text = listCards[position].des
            //ml!!.getView(position,view,parent).descripcion.text = listPersonaje[position].des
            //Toast.makeText(this,view.cardName.text, Toast.LENGTH_SHORT).show()
            //ml!!.getView(position,view,parent).invalidate();
            //ml!!.notifyDataSetChanged();
        }

}

/*public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
super.onActivityResult(requestCode, resultCode, data)
when (requestCode) {
    Activity.RESULT_OK -> {
        if (resultCode == Activity.RESULT_OK) {
            Toast.makeText(this,"entro",Toast.LENGTH_SHORT).show()
            // TODO Extract the data returned from the child Activity.
            //val returnValue = data!!.getStringExtra("some_key")
            this.cardList = data!!.getParcelableArrayListExtra<Card>("cardsList")
        }
    }
}




}*/

  inner class myLista:BaseAdapter{

        var listCard = ArrayList<Card>()
        var context:Context?=null

        constructor(context:Context, listCard:ArrayList<Card>):super(){

            this.listCard = listCard
            this.context = context

        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

            var myView: View? = null
            val Card = this.listCard[position]
            var inflater = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

            myView = inflater.inflate(R.layout.fila_okey, null)
            myView.nombre.text = Card.name
            myView.title.text = Card.title
            myView.topic.text = Card.topic
            //val explanation: String?
            val resID = resources.getIdentifier(
                Card.img,
                "drawable", packageName
            )
            myView.imageView.setImageResource(resID)
            //val cardImageID = resources.getIdentifier(Card.img!!, "drawable", packageName)
            //myView.imageView.setImageResource(cardImageID)

            //myView.imageView.setImageResource(Card.img!!.toInt()!!)
            //myView.topic.text = ""

            return myView
        }

            override fun getItem(position: Int): Any {
            return this.listCard[position]
            }

            override fun getItemId(position: Int): Long {

            return position.toLong()
            }

            override fun getCount(): Int {
            return this.listCard.size
            }


        }
}

