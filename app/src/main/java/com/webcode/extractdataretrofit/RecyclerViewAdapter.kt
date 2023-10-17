package com.webcode.extractdataretrofit

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class RecyclerViewAdapter(private val courseDataArrayList: ArrayList<RecyclerData>, private val mcontext: Context) : RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        // Inflate Layout
        val view = LayoutInflater.from(parent.context).inflate(R.layout.xmr, parent, false)
        return RecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        // Set the data to textview from our modal class.
        val modal = courseDataArrayList[position]
        holder.courseNameTV.text = modal.courseName
        holder.courseTracksTV.text = modal.courseTracks
        holder.courseModeTV.text = modal.courseMode
        Picasso.get().load(modal.courseimg).into(holder.courseIV)
    }

    override fun getItemCount(): Int {
        // this method returns the size of recyclerview
        return courseDataArrayList.size
    }

    // View Holder Class to handle Recycler View.
    inner class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // creating variables for our views.
        var courseNameTV: TextView = itemView.findViewById(R.id.idTVCourseName)
        var courseModeTV: TextView = itemView.findViewById(R.id.idTVBatch)
        var courseTracksTV: TextView = itemView.findViewById(R.id.idTVTracks)
        var courseIV: ImageView = itemView.findViewById(R.id.idIVCourse)
    }
}