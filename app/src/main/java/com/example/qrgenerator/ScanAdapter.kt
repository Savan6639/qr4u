package com.example.qrgenerator

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.qrgenerator.R

class ScanAdapter(val c: Context, val List: ArrayList<ScanViewModel>) : RecyclerView.Adapter<ScanAdapter.ViewHolder>() {

    var onItemclick : ((ScanViewModel) -> Unit)? = null
    var onclickdeleteItem : ((ScanViewModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
        val v = view.inflate(R.layout.card_view_design_scan, parent, false)

        return ViewHolder(v)
    }


    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        var imageView: ImageView = itemView.findViewById(R.id.imageview_history)
        var textView: TextView = itemView.findViewById(R.id.textView_history)
        var btndelete : ImageView = ItemView.findViewById(R.id.imageview_more_delete)
    }

    fun setOnClickDeleteItem(callback:(ScanViewModel) -> Unit){
        this.onclickdeleteItem = callback

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ScanViewModelList = List[position]

        holder.imageView.setImageLevel(ScanViewModelList.Image)

        holder.textView.text = ScanViewModelList.text
        // sets the text to the textview from our itemHolder class
        holder.btndelete.setOnClickListener{
            onclickdeleteItem?.invoke(ScanViewModelList)
            List.removeAt(position)
            notifyDataSetChanged()

        }
//      set onclicklistner
        holder.itemView.setOnClickListener{

            val intent = Intent( c , Itemdetails_qrscan::class.java)
            val text = ScanViewModelList.text
            val text_hint = ScanViewModelList.text_hint
            intent.putExtra("text" , text)
            intent.putExtra("text_hint" , text_hint)
            c.startActivity(intent)
            onItemclick?.invoke(ScanViewModelList)
        }
    }

    override fun getItemCount(): Int {
        return  List.size
    }

}