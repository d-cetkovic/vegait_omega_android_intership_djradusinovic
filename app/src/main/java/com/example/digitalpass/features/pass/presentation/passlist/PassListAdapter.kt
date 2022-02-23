package com.example.digitalpass.features.pass.presentation.passlist

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.digitalpass.R
import com.example.digitalpass.features.pass.data.remote.dto.PassDto
import kotlinx.android.synthetic.main.item_passlist.view.*
import java.net.URL

class PassListAdapter(private val passesList: List<PassDto>): RecyclerView.Adapter<PassListAdapter.PassListViewHolder>() {

    inner class PassListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PassListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_passlist, parent, false)
        return PassListViewHolder(view)
    }

    override fun onBindViewHolder(holder: PassListViewHolder, position: Int) {
        holder.itemView.apply {
            itemPasslistTitle.text = passesList[position].name
            itemPasslistSubtitleTextView.text = passesList[position].description
            //inflate the icon also using glider from the url!
            val url = URL(passesList[position].icon)
            val bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            itemPasslistImageView.setImageBitmap(bmp)
        }
    }

    override fun getItemCount(): Int {
        return passesList.size
    }


}