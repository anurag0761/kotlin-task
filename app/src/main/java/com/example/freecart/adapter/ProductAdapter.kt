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
import com.example.freecart.bean.ProductsItem
import com.example.freecart.databinding.RowItemViewBinding
import com.example.freecart.databinding.RowItemViewGridBinding


class ProductAdapter(context: Context, private val mList: ArrayList<ProductsItem?>?) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
    private lateinit var viewDataBinding: ViewDataBinding
    private  var type: Int? = 1


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val   layoutInflater = LayoutInflater.from(parent.context)

        if (viewType==1) viewDataBinding =   DataBindingUtil.inflate<RowItemViewBinding>(layoutInflater, R.layout.row_item_view, parent, false)
         else viewDataBinding =   DataBindingUtil.inflate<RowItemViewGridBinding>(layoutInflater, R.layout.row_item_view_grid, parent, false)
        return ViewHolder(viewDataBinding)
        }
    
    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList?.get(position)

        when (holder.viewDataBinding) {
            is RowItemViewBinding -> {
                val context = holder.itemView.context
                val data =ProductsItem()
                data?.title = mList?.get(holder.adapterPosition)?.title
                data?.category = mList?.get(holder.adapterPosition)?.category
                data?.discountPercentage = mList?.get(holder.adapterPosition)?.discountPercentage.toString().plus(context.getString(
                                    R.string.discount))
                data?.price = mList?.get(holder.adapterPosition)?.price
                data?.images = mList?.get(holder.adapterPosition)?.images
                data?.rating = mList?.get(holder.adapterPosition)?.rating
                holder.viewDataBinding.proRating.rating = data.rating!!.toFloat()
                Glide.with(context)
                    .load(data?.images?.get(0).toString())
                    .into(holder.viewDataBinding.productImg);

               holder.viewDataBinding.data = data
                /*  holder.viewDataBinding.rel2.setOnClickListener {
                      holder.itemView.context.startActivity(Intent(holder.itemView.context, SchoolActivity::class.java))
                          //overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
                  }*/

            }
            is RowItemViewGridBinding -> {
                val context = holder.itemView.context
                val data =ProductsItem()
                data?.title = mList?.get(holder.adapterPosition)?.title
                data?.category = mList?.get(holder.adapterPosition)?.category
                data?.discountPercentage = mList?.get(holder.adapterPosition)?.discountPercentage.toString().plus(context.getString(
                                    R.string.discount))
                data?.price = mList?.get(holder.adapterPosition)?.price
                data?.images = mList?.get(holder.adapterPosition)?.images
                data?.rating = mList?.get(holder.adapterPosition)?.rating
                holder.viewDataBinding.proRating.rating = data.rating!!.toFloat()
                Glide.with(context)
                    .load(data?.images?.get(0).toString())
                    .into(holder.viewDataBinding.productImg);

               holder.viewDataBinding.data = data
                /*  holder.viewDataBinding.rel2.setOnClickListener {
                      holder.itemView.context.startActivity(Intent(holder.itemView.context, SchoolActivity::class.java))
                          //overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
                  }*/

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
        return type!!
    }

    fun setdata(type: Int) {
        this.type = type
        notifyDataSetChanged()
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