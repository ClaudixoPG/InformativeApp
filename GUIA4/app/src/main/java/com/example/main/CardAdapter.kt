package com.example.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import cl.pdm.felipebesoain.gamecharacterlist.Card

class CardAdapter(val nCtx: Context, val layoutResId: Int, val cardsList: List<Card>)
    : ArrayAdapter<Card>(nCtx, layoutResId,cardsList){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val layoutInflater: LayoutInflater = LayoutInflater.from(nCtx)
        val view: View = layoutInflater.inflate(layoutResId,null)

        val textViewName = view.findViewById<TextView>(R.id.cardGetName)

        val card = cardsList[position]

        textViewName.text = card.name

        return view
    }
}