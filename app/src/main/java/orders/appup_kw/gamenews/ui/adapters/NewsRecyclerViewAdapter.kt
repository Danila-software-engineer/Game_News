package orders.appup_kw.gamenews.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import orders.appup_kw.gamenews.R
import orders.appup_kw.gamenews.databinding.ResviewLayoutBinding
import orders.appup_kw.gamenews.databinding.ResviewloadLayoutBinding
import orders.appup_kw.gamenews.network.POJONews

class NewsRecyclerViewAdapter(var context: Context?, private val news: List<POJONews?>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val VIEW_TYPE_ITEM = 0
    private val VIEW_TYPE_LOADING = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if(viewType == VIEW_TYPE_ITEM){
            val binding = ResviewLayoutBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)
            GoodsViewHolder(binding)
        }else{
            val binding = ResviewloadLayoutBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)
            LoadViewHolder(binding)
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is GoodsViewHolder) {
            try {
                holder.binding.textName.text = news[position]?.name
                holder.binding.textText.text = news[position]?.text
                holder.binding.textData.text = news[position]?.data
                try {
                    Glide.with(context!!)
                            .load(news[position]?.img)
                            .into(holder.binding.imageView)
                } catch (e: Exception) {
                    e.printStackTrace()
                    holder.binding.imageView.setImageResource(R.drawable.ic_photo)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }else{

        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (news[position] == null) VIEW_TYPE_LOADING else VIEW_TYPE_ITEM
    }

    override fun getItemCount() = news.size

    class GoodsViewHolder(var binding: ResviewLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    class LoadViewHolder(var binding: ResviewloadLayoutBinding) : RecyclerView.ViewHolder(binding.root)
}