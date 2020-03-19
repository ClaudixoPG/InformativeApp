package cl.pdm.felipebesoain.gamecharacterlist

import android.media.Image
import android.os.Parcel
import android.os.Parcelable
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.io.Serializable

class Card(val cardsId: String?,val name:String?, val topic: String?,val title: String?,val explanation: String?, val img: String?) :Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    constructor() : this("","","","","","")
    {

    }

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
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(cardsId)
        parcel.writeString(name)
        parcel.writeString(topic)
        parcel.writeString(title)
        parcel.writeString(explanation)
        parcel.writeString(img)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Card> {
        override fun createFromParcel(parcel: Parcel): Card {
            return Card(parcel)
        }

        override fun newArray(size: Int): Array<Card?> {
            return arrayOfNulls(size)
        }
    }


}