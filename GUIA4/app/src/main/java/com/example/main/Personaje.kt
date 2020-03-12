package cl.pdm.felipebesoain.gamecharacterlist

class Personaje{


    var img:Int?=null
    var name:String?=null
    var des:String?=null
    var life:Int?=null
    var maxLife:Int?=null

    constructor(a:Int,b:String, c:String,d:Int,e:Int){

        this.img = a
        this.name = b
        this.des = c
        this.life = d
        this.maxLife = e

    }


}