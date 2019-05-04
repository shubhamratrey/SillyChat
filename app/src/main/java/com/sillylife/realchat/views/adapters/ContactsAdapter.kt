package com.sillylife.realchat.views.adapters

import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.sillylife.realchat.R
import com.sillylife.realchat.models.User
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_contact.*
import java.util.*

class ContactsAdapter(private val mContext: Context?, var mContactList: ArrayList<User>, val listener: (Any) -> Unit) : RecyclerView.Adapter<ContactsAdapter.ViewHolder>(), Filterable {

    private var valueFilter: ContactsAdapter.ValueFilter? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ContactsAdapter.ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_contact, parent, false))
    }

    override fun getItemCount(): Int {
        return mContactList.size
    }

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mContactList[holder.adapterPosition]
        holder.contact_name.text = item.userName
        holder.phone_number.text = item.userNumber

        holder.containerView.setOnClickListener {
            listener(item)
        }
    }

    override fun getFilter(): Filter {
        if (valueFilter == null) {
            valueFilter = ValueFilter()
        }
        return valueFilter!!
    }


    inner class ValueFilter : Filter() {
        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            mContactList = results!!.values as ArrayList<User>
            notifyDataSetChanged()
        }

        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filterResults = FilterResults()
            if (TextUtils.isEmpty(constraint)) {
                filterResults.count = mContactList.size
                filterResults.values = mContactList
            } else {
                val filterList: ArrayList<User> = ArrayList<User>()
                for (item in mContactList) {
                    if (item.userName?.contains(constraint!!, ignoreCase = true)!!) {
                        filterList.add(item)
                    }
                }
                filterResults.count = filterList.size
                filterResults.values = filterList
            }
            return filterResults
        }

    }

}