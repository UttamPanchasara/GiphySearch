package com.uttampanchasara.baseprojectkotlin.ui.dashboard.home

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.uttampanchasara.baseprojectkotlin.R
import com.uttampanchasara.baseprojectkotlin.data.repository.videos.Data

class VideosAdapter(val context: Context, private val clickListener: VideoClickListener) : RecyclerView.Adapter<ViewHolder>() {

    private var videoList: List<Data> = emptyList()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.list_videos, p0, false))
    }

    override fun getItemCount(): Int {
        return videoList.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val video = videoList[position]
        Glide.with(context).load(video.images.w_still.url).into(viewHolder.mImageView)

        viewHolder.txtName.text = video.title
        viewHolder.txtDate.text = video.import_datetime

        viewHolder.itemView.setOnClickListener {
            clickListener.onVideoSelected(video)
        }
    }

    fun setVideoList(data: List<Data>) {
        this.videoList = data
        notifyDataSetChanged()
    }

    interface VideoClickListener {
        fun onVideoSelected(video: Data)
    }
}

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val mImageView: ImageView = itemView.findViewById(R.id.imageView)
    val txtName: TextView = itemView.findViewById(R.id.txtName)
    val txtDate: TextView = itemView.findViewById(R.id.txtDate)
}