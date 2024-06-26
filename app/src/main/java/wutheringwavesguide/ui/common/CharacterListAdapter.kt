package wutheringwavesguide.ui.common

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import wutheringwavesguide.R
import wutheringwavesguide.models.api.character.CharacterResponseItem

class CharacterListAdapter(
    private val contextCharacter: Context,
    private val fruitsList:List<CharacterResponseItem>,
    private val clickListener:(CharacterResponseItem)->Unit
) : RecyclerView.Adapter<MyViewHolder3>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder3 {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem = layoutInflater.inflate(R.layout.layout_character_list,parent,false)
        return MyViewHolder3(listItem)
    }

    override fun onBindViewHolder(holder: MyViewHolder3, position: Int) {
        val fruit = fruitsList[position]
        holder.bind(contextCharacter, fruit,clickListener)
    }

    override fun getItemCount(): Int {
        return fruitsList.size
    }

}

class MyViewHolder3(val view: View):RecyclerView.ViewHolder(view){
    fun bind(contextCharacter: Context, character: CharacterResponseItem, clickListener:(CharacterResponseItem)->Unit) {
        val characterTextView = view.findViewById<TextView>(R.id.characterName)
        characterTextView.text = character.name

        val rarityTextView = view.findViewById<TextView>(R.id.textCharacterWeapon)
        rarityTextView.text = "Weapon: BroadBlade"

        val descriptionTextView = view.findViewById<TextView>(R.id.textTypeCharacter)
        descriptionTextView.text = "Type: " + character.tag

//        val atkTextView = view.findViewById<TextView>(R.id.textWeaponCharacter)
//        atkTextView.text =  "ATK (Lv.1): " + character.id
//
//        val bonusTextView = view.findViewById<TextView>(R.id.textCharacterSonataEffect)
//        bonusTextView.text = character.id

        val echoImageView = view.findViewById<ImageView>(R.id.imageViewCharacter)
        if(character.img != null){
            Glide.with(view)
                .load(character.img)
                .into(echoImageView)
            if(character.bStyle == "linear-gradient(0deg, rgba(119,61,166,1) -79%, rgba(255,255,255,0) 100%)"){
                echoImageView.background = ContextCompat.getDrawable(contextCharacter, R.drawable.four_star_gradient)
            }
            else{
                echoImageView.background = ContextCompat.getDrawable(contextCharacter, R.drawable.five_star_gradient)
            }
        }

        view.setOnClickListener {
            clickListener(character)
        }
    }
}