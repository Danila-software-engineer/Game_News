package orders.appup_kw.gamenews.ui.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import orders.appup_kw.gamenews.R
import orders.appup_kw.gamenews.databinding.ResviewLayoutBinding
import orders.appup_kw.gamenews.model.News

class NewsRecyclerViewAdapter (var context: Context?, private val news: ArrayList<News>) : RecyclerView.Adapter<NewsRecyclerViewAdapter.GoodsViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoodsViewHolder {
        val binding = ResviewLayoutBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        return GoodsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GoodsViewHolder, position: Int) {
        holder.binding.textName.text = news[position].name
        holder.binding.textText.text = news[position].text
        holder.binding.textData.text = news[position].data
        try {
            Glide.with(context!!)
                    .load(news[position].img)
                    .into(holder.binding.imageView)
        } catch (e: Exception) {
            e.printStackTrace()
            holder.binding.imageView.setImageResource(R.drawable.ic_photo)
        }

    }

    override fun getItemCount() = news.size

    class GoodsViewHolder(var binding: ResviewLayoutBinding) : RecyclerView.ViewHolder(binding.root)
}