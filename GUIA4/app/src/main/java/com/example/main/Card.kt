package cl.pdm.felipebesoain.gamecharacterlist

import android.media.Image
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Card(val CardsId: String,val Name:String, val Topic: String,val Title: String,val Explanation: String){


    /*var img:Int?=null
    var name:String?=null
    var des:String?=null
    var life:Int?=null
    var maxLife:Int?=null
*/
    /*constructor(a:Int,b:String, c:String,d:Int,e:Int){

        this.img = a
        this.name = b
        this.des = c
        this.life = d
        this.maxLife = e

    }*/

    /*var id:Int?=null
    var name:String?=null
    var topic:String?=null
    var title:String?=null
    var explanation:String?=null
    var image:Int?=null
    //var database:DatabaseReference
    */
   /* constructor(CardsId: Int, Name:String,Topic: String, Title: String, Explanation: String)//, Image: Int)
    {
        this.id = CardsId
        this.name = Name
        this.topic = Topic
        this.title = Title
        this.explanation = Explanation
        //this.image = Image
        //database = FirebaseDatabase.getInstance().reference
    }*/


    /*private fun writeNewCard(cardId: String, topic: String, title: String, explanation: String, image: Int) {
        val card = Card(topic,title,explanation, image)
        database.child("cards").child(cardId).setValue(card)
    }*/


}