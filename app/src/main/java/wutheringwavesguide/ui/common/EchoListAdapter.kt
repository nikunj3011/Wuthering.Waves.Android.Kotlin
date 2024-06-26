package wutheringwavesguide.ui.common

import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import wutheringwavesguide.R
import wutheringwavesguide.models.api.echo.EchoesResponseItem


class EchoListAdapter(
    private val fruitsList:List<EchoesResponseItem>,
    private val clickListener:(EchoesResponseItem)->Unit
) : RecyclerView.Adapter<MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem = layoutInflater.inflate(R.layout.layout_echo_list,parent,false)
        return MyViewHolder(listItem)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val fruit = fruitsList[position]
        holder.bind(fruit,clickListener)
    }

    override fun getItemCount(): Int {
        return fruitsList.size
    }

}

class MyViewHolder(val view: View):RecyclerView.ViewHolder(view){
    fun bind(echo: EchoesResponseItem, clickListener:(EchoesResponseItem)->Unit) {
        val echoTextView = view.findViewById<TextView>(R.id.echoName)
        echoTextView.text = echo.name

        val costTextView = view.findViewById<TextView>(R.id.textCost)
        costTextView.text = "Cost: " + echo.cost

        val cooldownTextView = view.findViewById<TextView>(R.id.textCooldown)
        cooldownTextView.text = "CD: " + echo.cooldown

        val calamityTextView = view.findViewById<TextView>(R.id.textCalamity)
        calamityTextView.text = "CD: " + echo.classEcho

        val descriptionTextView = view.findViewById<TextView>(R.id.textDescription)
        descriptionTextView.movementMethod = ScrollingMovementMethod()
        descriptionTextView.text = echo.description

        val setsTextView = view.findViewById<TextView>(R.id.textSets)
        setsTextView.text =  "Sets: " + echo.cost

        val echoImageView = view.findViewById<ImageView>(R.id.imageView)
        Glide.with(view)
            .load(echo.img)
            .into(echoImageView)

//        view.setOnClickListener {
//            clickListener(echo)
//        }
    }
}