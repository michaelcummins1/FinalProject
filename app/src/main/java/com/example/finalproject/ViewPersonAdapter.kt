import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.EventViewModel
import com.example.finalproject.Person

import com.example.finalproject.ViewPersonViewHolder
import com.example.finalproject.databinding.ViewPersonItemLayoutBinding

class ViewPersonAdapter(val giftList: List<String>, val viewModel: EventViewModel) : RecyclerView.Adapter<ViewPersonViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPersonViewHolder {
        val binding = ViewPersonItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewPersonViewHolder(binding, viewModel)
    }

    override fun onBindViewHolder(holder: ViewPersonViewHolder, position: Int) {
        val currentGift = giftList[position]
        holder.bindGift(currentGift)
    }

    override fun getItemCount(): Int {
        return giftList.size
    }


}