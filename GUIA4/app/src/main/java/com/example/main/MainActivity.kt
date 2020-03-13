package com.example.main

import android.content.Context
import android.content.Intent
//import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
//import cl.pdm.felipebesoain.gamecharacterlist.Personaje
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fila.view.*
import android.widget.AdapterView
import androidx.core.view.get
import cl.pdm.felipebesoain.gamecharacterlist.Card
import com.google.firebase.FirebaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.add_user_activity.*


class MainActivity : AppCompatActivity() {


    var listCards = ArrayList<Card>()
    var ml:myLista?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*listCards.add(Pr(R.drawable.cloud,"Nombre: Cloud","Descripcion: Estes es un personaje de FFVII 100 HP",100,1000))
        listCards.add(Personaje(R.drawable.ryu,"Nombre: Ryo","Descripcion: Estes es un personaje de FFVII 200 HP",200,1000))
        listCards.add(Personaje(R.drawable.sonic,"Nombre: Sonic","Descripcion: Estes es un personaje de FFVII 300 HP",300,1000))
        listCards.add(Personaje(R.drawable.link,"Nombre: Link","Descripcion: Estes es un personaje de FFVII 400 HP",400,1000))

        listCards.add(Personaje(R.drawable.cloud,"Nombre: Cloud","Descripcion: Estes es un personaje de FFVII 500 HP",500,1000))
        listCards.add(Personaje(R.drawable.ryu,"F","Descripcion: Estes es un personaje de FFVII 600 HP",600,1000))
        listCards.add(Personaje(R.drawable.sonic,"Nombre: Sonic","Descripcion: Estes es un personaje de FFVII 850 HP",850,1000))
        listCards.add(Personaje(R.drawable.link,"Nombre: Link","Descripcion: Estes es un personaje de FFVII 1000 HP",1000,1000))
        */

        //listCards.add(Card(R.string))


        ml = myLista(this,listCards)
        listViewXML.adapter = ml

        // get reference to button
        val btn_add_PJ = findViewById(R.id.newPJ) as Button
        // set on-click listener
        btn_add_PJ.setOnClickListener {

            val intent = Intent(this@MainActivity, AddCardActivity::class.java)
            startActivity(intent)
            //saveCard()
            // your code to perform when the user clicks on the button
            //this.listCards.add(Personaje(R.drawable.link,"Nombre: Link","Descripcion: Estes es un personaje de FFVII 1000 HP",1000,1000))
            //ml!!.notifyDataSetChanged();
            //Toast.makeText(this, "Personaje añadido.", Toast.LENGTH_SHORT).show()
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
        listViewXML.setOnItemLongClickListener { adapter, view, pos, id ->
            // TODO Auto-generated method stub

            listCards.removeAt(pos)
            ml!!.notifyDataSetChanged();
            Toast.makeText(this, "Personaje removido.", Toast.LENGTH_SHORT).show()

            true
        }

        listViewXML.setOnItemClickListener { parent, view, position, id ->
            //Toast.makeText(this, listPersonaje[position].des, Toast.LENGTH_SHORT).show()
            //view.descripcion.text = listCards[position].des
            //ml!!.getView(position,view,parent).descripcion.text = listPersonaje[position].des
            Toast.makeText(this,view.descripcion.text, Toast.LENGTH_SHORT).show()
            //ml!!.getView(position,view,parent).invalidate();
            ml!!.notifyDataSetChanged();
        }


    }


inner class myLista:BaseAdapter{

    var listPersonaje = ArrayList<Card>()
    var context:Context?=null

    constructor(context:Context, listPersonaje:ArrayList<Card>):super(){

        this.listPersonaje = listPersonaje
        this.context = context

    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
       // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

        var myView:View?=null
        val Personaje = this.listPersonaje[position]
        var inflater = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        myView = inflater.inflate(R.layout.fila_okey,null)
        //myView.nombre.text = Personaje.name
        //myView.imageView.setImageResource(Personaje.img!!)
        myView.descripcion.text = ""

        /*if(Personaje.life!!.toFloat()/Personaje.maxLife!!.toFloat() == 1.0f) {
            myView.container.setBackgroundColor(context!!.resources.getColor(R.color.green))
        }else if (Personaje.life!!.toFloat()/Personaje.maxLife!!.toFloat() < 1.0f &&
            Personaje.life!!.toFloat()/Personaje.maxLife!!.toFloat() >= .85f) {
            myView.container.setBackgroundColor(context!!.resources.getColor(R.color.orange))
        }else if (Personaje.life!!.toFloat()/Personaje.maxLife!!.toFloat() < .85f &&
            Personaje.life!!.toFloat()/Personaje.maxLife!!.toFloat() >= .6f) {
            myView.container.setBackgroundColor(context!!.resources.getColor(R.color.yellow))
        }else if (Personaje.life!!.toFloat()/Personaje.maxLife!!.toFloat() < .6f &&
            Personaje.life!!.toFloat()/Personaje.maxLife!!.toFloat() >= .5f) {
            myView.container.setBackgroundColor(context!!.resources.getColor(R.color.fuchsia))
        }else if (Personaje.life!!.toFloat()/Personaje.maxLife!!.toFloat() < .4f &&
            Personaje.life!!.toFloat()/Personaje.maxLife!!.toFloat() >= .3f) {
            myView.container.setBackgroundColor(context!!.resources.getColor(R.color.silver))
        }else if (Personaje.life!!.toFloat()/Personaje.maxLife!!.toFloat() < .3f &&
            Personaje.life!!.toFloat()/Personaje.maxLife!!.toFloat() >= .0f) {
            myView.container.setBackgroundColor(context!!.resources.getColor(R.color.red))
        }*/



/*}else {
}else if (Personaje.life!!.toFloat()/Personaje.maxLife!!.toFloat() < .50f &&
    Personaje.life!!.toFloat()/Personaje.maxLife!!.toFloat() >= .4f) {
    myView.contje.name
    myView.imageView.setImageResource(Personaje.img!!)
    myView.descripcion.text = Personaje.des

}*/
return myView



}

override fun getItem(position: Int): Any {
// TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
return this.listPersonaje[position]
}

override fun getItemId(position: Int): Long {
// TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

return position.toLong()
}

override fun getCount(): Int {
// TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
return this.listPersonaje.size
}


}
    /* private  fun  saveCard()
     {
         val name = cardName.text.toString.trim()
         val topic = editTextTopic.text.toString.trim()
         val title = editTextTitle.text.toString.trim()
         val explanation = editTextExplanation.text.toString.trim()
         //val image = editTextName.text.toString.trim()
         if(name.isEmpty()){
             editTextName.error = "please, enter a name"
             return
         }
         if(topic.isEmpty()){
             editTextTopic.error = "please, enter a topic"
             return
         }
         if(title.isEmpty()){
             editTextTitle.error = "please, enter a title"
             return
         }
         if(explanation.isEmpty()){
             editTextExplanation.error = "please, enter a explanation"
             return
         }

         val ref = FirebaseDatabase.getInstance().getReference("cards")

         val cardsId = ref.push().key
         val card = Card(cardsId!!, name!!, topic!!, title!!, explanation!!)

         ref.child(cardsId).setValue(card).addOnCompleteListener{
             Toast.makeText(applicationContext,"card save",Toast.LENGTH_SHORT).show()
         }

     }

     */

}
