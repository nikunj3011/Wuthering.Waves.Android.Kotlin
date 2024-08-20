package wutheringwavesguide.ui.common

import android.content.Context
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.Transformation
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import wutheringwavesguide.R
import wutheringwavesguide.databinding.LayoutCategoryItemBinding
import wutheringwavesguide.databinding.LayoutEchoListBinding
import wutheringwavesguide.models.api.character.CharacterResponseItem
import wutheringwavesguide.models.api.echo.EchoesResponseItem


class EchoListAdapter(
    private val contextEcho: Context,
    private var fruitsList:List<EchoesResponseItem>,
    private val clickListener:(EchoesResponseItem)->Unit
) : RecyclerView.Adapter<MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem = layoutInflater.inflate(R.layout.layout_echo_list,parent,false)
        return MyViewHolder(listItem)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val fruit = fruitsList[position]
        holder.bind(contextEcho, fruit, clickListener)
    }

    fun setFilteredList(mList: List<EchoesResponseItem>){
        this.fruitsList = mList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return fruitsList.size
    }

}

class MyViewHolder(val view: View):RecyclerView.ViewHolder(view){
    fun bind(contextEcho: Context, echo: EchoesResponseItem, clickListener:(EchoesResponseItem)->Unit) {
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
        var sets = "Best Sets: "
        for (values in echo.echoSets){
            sets = " " + sets + values.name + ", "
        }
        setsTextView.text = sets

        var count = 1
//        val echoImageView1 = view.findViewById<ImageView>(R.id.imageViewEchoSet1)
//        val echoImageView2 = view.findViewById<ImageView>(R.id.imageViewEchoSet1)
//        val echoImageView3 = view.findViewById<ImageView>(R.id.imageViewEchoSet1)
//        val echoImageView4 = view.findViewById<ImageView>(R.id.imageViewEchoSet1)

//        while(count != echo.echoSets.count()){
//            when (count) {
//                1 -> {
////                    echoImageView1.background = ContextCompat.getDrawable(contextEcho, R.drawable.find)
//                }
//                2 -> {
//                    Glide.with(view)
//                        .load(echo.img)
//                        .into(echoImageView2)
//                }
//                3 -> {
//                    Glide.with(view)
//                        .load(echo.img)
//                        .into(echoImageView3)
//                }
//                4 -> {
//                    Glide.with(view)
//                        .load(echo.img)
//                        .into(echoImageView4)
//                }
//                else -> {
//                    Glide.with(view)
//                        .load(echo.img)
//                        .into(echoImageView4)
//                }
//            }
//            count++
//        }

        val echoImageView = view.findViewById<ImageView>(R.id.imageView)
        if(echo.img != null)
        {
            Glide.with(view)
                .load(echo.img)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .into(echoImageView)

            when (echo.classEcho) {
                "Calamity" -> {
                    echoImageView.background = ContextCompat.getDrawable(contextEcho, R.drawable.five_star_gradient)
                }
                "Overlord" -> {
                    echoImageView.background = ContextCompat.getDrawable(contextEcho, R.drawable.four_star_gradient)
                }
                "Elite" -> {
                    echoImageView.background = ContextCompat.getDrawable(contextEcho, R.drawable.three_star_gradient)
                }
                "Common" -> {
                    echoImageView.background = ContextCompat.getDrawable(contextEcho, R.drawable.one_star_gradient)
                }
                else -> {
                    echoImageView.background = ContextCompat.getDrawable(contextEcho, R.drawable.one_star_gradient)
                }
            }
        }

//        view.setOnClickListener {
//            clickListener(echo)
//        }
    }

//
//
//    fun View.expand(duration: Long) {
//        val initialHeight = this.measuredHeight
//        val matchParentMeasureSpec = View.MeasureSpec.makeMeasureSpec((this.parent as View).width, View.MeasureSpec.EXACTLY)
//        val wrapContentMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
//        this.measure(matchParentMeasureSpec, wrapContentMeasureSpec)
//        val targetHeight = this.measuredHeight
//
//        this.layoutParams.height = initialHeight
//        this.visibility = View.VISIBLE
//        val a: Animation = object : Animation() {
//            override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
//                this@expand.layoutParams.height = if (interpolatedTime == 1.0f) ViewGroup.LayoutParams.WRAP_CONTENT else (initialHeight + ((targetHeight - initialHeight) * interpolatedTime)).toInt()
//                this@expand.requestLayout()
//            }
//
//            override fun willChangeBounds(): Boolean {
//                return true
//            }
//        }
//
//
//        a.duration = duration
//        this.startAnimation(a)
//    }
//
//    fun View.collapse(duration: Long) {
//        val initialHeight = this.measuredHeight
//        val a: Animation = object : Animation() {
//            override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
//                if (interpolatedTime == 1.0f) {
//                    this@collapse.visibility = View.GONE
//                } else {
//                    this@collapse.layoutParams.height = initialHeight - (initialHeight * interpolatedTime).toInt()
//                    this@collapse.requestLayout()
//                }
//            }
//
//            override fun willChangeBounds(): Boolean {
//                return true
//            }
//        }
//
//        a.duration = duration
//        this@collapse.startAnimation(a)
//    }
}