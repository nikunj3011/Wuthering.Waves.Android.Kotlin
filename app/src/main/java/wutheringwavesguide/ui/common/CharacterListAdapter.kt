package wutheringwavesguide.ui.common

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.View.OnLongClickListener
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import wutheringwavesguide.R
import wutheringwavesguide.models.api.character.CharacterResponseItem
import java.util.Timer
import kotlin.concurrent.schedule


class CharacterListAdapter(
    private val contextCharacter: Context,
    private var fruitsList:List<CharacterResponseItem>,
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

    fun setFilteredList(mList: List<CharacterResponseItem>){
        this.fruitsList = mList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return fruitsList.size
    }

}
class MyViewHolder3(val view: View):RecyclerView.ViewHolder(view){
    private var touchRelease : Boolean = false
    @SuppressLint("ClickableViewAccessibility")
    fun bind(contextCharacter: Context, character: CharacterResponseItem, clickListener:(CharacterResponseItem)->Unit) {
        val characterTextView = view.findViewById<TextView>(R.id.characterName)
        characterTextView.text = character.name
        val characterView = view.findViewById<RecyclerView>(R.id.characterRecyclerView)
//        characterView?.background = ContextCompat.getDrawable(contextCharacter, R.drawable.four_star_gradient)
//        val rarityTextView = view.findViewById<TextView>(R.id.textCharacterWeapon)
//        rarityTextView.text = "Weapon: BroadBlade"
//
//        val descriptionTextView = view.findViewById<TextView>(R.id.textTypeCharacter)
//        descriptionTextView.text = "Type: " + character.tag

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

        val characterElementImageView = view.findViewById<ImageView>(R.id.imageViewCharacterElement)
        if(character.tag != null){

            when (character.tag) {
                "Aero" -> {
                    Glide.with(view)
                        .load(R.drawable.element_aero)
                        .into(characterElementImageView)
                }
                "Glacio" -> {
                    Glide.with(view)
                        .load(R.drawable.element_glacio)
                        .into(characterElementImageView)
                }
                "Electro" -> {
                    Glide.with(view)
                        .load(R.drawable.element_electro)
                        .into(characterElementImageView)
                }
                "Fusion" -> {
                    Glide.with(view)
                        .load(R.drawable.element_fusion)
                        .into(characterElementImageView)
                }
                "Havoc" -> {
                    Glide.with(view)
                        .load(R.drawable.element_havoc)
                        .into(characterElementImageView)
                }
                "Spectro" -> {
                    Glide.with(view)
                        .load(R.drawable.element_spectro)
                        .into(characterElementImageView)
                }
                else -> {
                    Glide.with(view)
                        .load(R.drawable.element_spectro)
                        .into(characterElementImageView)
                }
            }
        }

//        view.setOnClickListener {
//            clickListener(character)
//            Timer().schedule(100) {
//                view.background = ContextCompat.getDrawable(contextCharacter, R.drawable.layout_bg)
//            }
//        }
//
//        view.setOnLongClickListener(OnLongClickListener {
//            view.background =  ContextCompat.getDrawable(contextCharacter, R.drawable.translucent_black)
//            clickListener(character)
//            Timer().schedule(100) {
//            }
//            true
//        })
//        view.setOnTouchListener(object : View.OnTouchListener {
//            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
//                when (event?.action) {
//                    MotionEvent.ACTION_DOWN -> {
//                        view.background =  ContextCompat.getDrawable(contextCharacter, R.drawable.translucent_black)
//                    }
//                    MotionEvent.AXIS_X -> {
//                        view.performClick()
//                        view.background = ContextCompat.getDrawable(contextCharacter, R.drawable.layout_bg)
//                        clickListener(character)
//                    }
//                }
//                return v?.onTouchEvent(event) ?: true
//            }
//        })
        view.setOnTouchListener { view, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                view.background =  ContextCompat.getDrawable(contextCharacter, R.drawable.translucent_black)
            } else if (event.action == MotionEvent.ACTION_UP) {
                view.background = ContextCompat.getDrawable(contextCharacter, R.drawable.layout_bg)
                clickListener(character)
            }
            true
        }
    }
}