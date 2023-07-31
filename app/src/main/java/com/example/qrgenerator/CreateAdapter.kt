package com.example.qrgenerator

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat.startActivity
import androidx.core.graphics.drawable.toIcon
import androidx.recyclerview.widget.RecyclerView
import com.example.qrgenerator.R

class CreateAdapter(val c: Context, val List: ArrayList<ItemsViewModel>) : RecyclerView.Adapter<CreateAdapter.ViewHolder>() {

    var onItemclick : ((ItemsViewModel) -> Unit)? = null
    var onclickdeleteItem : ((ItemsViewModel) -> Unit)? = null



    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
        val v = view.inflate(R.layout.card_view_design, parent, false)

        return ViewHolder(v)
    }


    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        var imageView: ImageView = itemView.findViewById(R.id.imageview)
        var textView: TextView = itemView.findViewById(R.id.textView_hint)
        var textView_hint: TextView = itemView.findViewById(R.id.textView_show)
        var btnDelete : ImageView = ItemView.findViewById(R.id.imageview_more)
    }

    fun setOnClickDeleteItem(callback:(ItemsViewModel) -> Unit){
        this.onclickdeleteItem = callback

    }
    // binds the list items to a view
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModelList = List[position]
//        holder.bindView(ItemsViewModelList)
       // sets the image to the imageview from1 our itemHolder class
        val QrImage = ItemsViewModelList.image
        val imageBytes = Base64.decode(QrImage , Base64.DEFAULT)
        val decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)

        holder.imageView.setImageBitmap(decodedImage)
       // sets the text to the textview from our itemHolder class
        holder.textView.text = ItemsViewModelList.text
       // sets the text to the textview from our itemHolder class
        holder.textView_hint.text = ItemsViewModelList.text_hint

        holder.btnDelete.setOnClickListener{
            onclickdeleteItem?.invoke(ItemsViewModelList )
            List.removeAt(position);
            notifyDataSetChanged();
        }
//      set onclicklistner
        holder.itemView.setOnClickListener{

            val intent = Intent( c , ActivityItemDetails::class.java )
            val qrimage = ItemsViewModelList.image
            intent.putExtra("qr_image" , qrimage)

            c.startActivity(intent)
            onItemclick?.invoke(ItemsViewModelList)
        }
    }
    // return the number of the items in the list
    override fun getItemCount(): Int {
        return  List.size
    }
}
