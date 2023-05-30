import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.EventViewModel
import com.example.finalproject.Person
import com.example.finalproject.ViewPersonFragment

import com.example.finalproject.ViewPersonViewHolder
import com.example.finalproject.databinding.FragmentViewPersonBinding
import com.example.finalproject.databinding.ViewPersonItemLayoutBinding

class ViewPersonAdapter(
    val giftList: List<String>,
    val binding2: FragmentViewPersonBinding,
    val viewModel: EventViewModel,
    val activity: FragmentActivity?,
    val context: Context
) : RecyclerView.Adapter<ViewPersonViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPersonViewHolder {
        val binding =
            ViewPersonItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewPersonViewHolder(binding, binding2, viewModel, this, activity, context)
    }

    override fun onBindViewHolder(holder: ViewPersonViewHolder, position: Int) {
        val currentGift = giftList[position]
        holder.bindGift(currentGift)
    }

    override fun getItemCount(): Int {
        return giftList.size
    }


}