import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.SwitchCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.freecart.R
import com.example.freecart.bean.ImageItem
import com.example.freecart.bean.ProductsItem
import com.example.freecart.databinding.ImgViewItemBinding
import com.example.freecart.databinding.RowItemViewBinding



class ImageAdapters(context: Context, private val mList: ArrayList<ImageItem?>?) : RecyclerView.Adapter<ImageAdapters.ViewHolder>() {
    private lateinit var viewDataBinding: ViewDataBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item

        val   layoutInflater = LayoutInflater.from(parent.context)
        viewDataBinding =   DataBindingUtil.inflate<ImgViewItemBinding>(layoutInflater, R.layout.img_view_item, parent, false)
        return ViewHolder(viewDataBinding)
        }
    
    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList?.get(position)

        when (holder.viewDataBinding) {
            is ImgViewItemBinding -> {
                val context = holder.itemView.context

                Glide.with(context)
                    .load(ItemsViewModel?.image.toString())
                    .into(holder.viewDataBinding.proImg);

               //holder.viewDataBinding.data = data


            }

        }
        // sets the image to the imageview from our itemHolder class

        // sets the text to the textview from our itemHolder class


    }
    
    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList!!.size
        }

    // Holds the views for adding it to image and text
    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    class ViewHolder(val viewDataBinding: ViewDataBinding) : RecyclerView.ViewHolder(viewDataBinding.root) {
       /* var name: TextView
        var swiched: SwitchCompat
        var checkbox100: CheckBox
        var checkbox250: CheckBox
        var imgs: ImageView*/

        init {

        }
    }
}