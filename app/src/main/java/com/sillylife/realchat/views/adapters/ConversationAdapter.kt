package com.sillylife.realchat.views.adaptersimport android.content.Contextimport android.graphics.Rectimport android.view.LayoutInflaterimport android.view.Viewimport android.view.ViewGroupimport androidx.recyclerview.widget.RecyclerViewimport com.google.firebase.auth.FirebaseAuthimport com.sillylife.realchat.Rimport com.sillylife.realchat.managers.imagemanager.ImageManagerimport com.sillylife.realchat.models.Conversationimport kotlinx.android.extensions.LayoutContainerimport kotlinx.android.synthetic.main.item_conv_other.*import kotlinx.android.synthetic.main.item_conv_self.*class ConversationAdapter(val context: Context, val listener: (Any) -> Unit) : RecyclerView.Adapter<ConversationAdapter.ViewHolder>() {    private val TAG = ConversationAdapter::class.java.simpleName    var pageNo = 1    val commonItemLists = ArrayList<Any>()    var hasMore: Boolean = false    companion object {        const val SELF = 0        const val OTHER = 1        const val SELF_IMAGE = 2        const val OTHER_IMAGE = 3        const val TIMESTAMP = 4        const val PROGRESS_VIEW = 5    }    fun addData(data: ArrayList<Conversation>?, hasMore: Boolean) {        data?.reverse()        commonItemLists.addAll(data!!)        if (hasMore) {            this.hasMore = hasMore            pageNo++            commonItemLists.add(PROGRESS_VIEW)        }    }    override fun getItemViewType(position: Int): Int {        val selfUid = FirebaseAuth.getInstance().currentUser?.uid        return if (commonItemLists[position] is Conversation) {            val item = commonItemLists[position] as Conversation            val uid = item.convUser?.userID            if (uid.equals(selfUid, true)) {                if (item.convFile?.fileType.equals("Image", true)) {                    SELF_IMAGE                } else {                    SELF                }            } else {                if (item.convFile?.fileType.equals("Image", true)) {                    OTHER_IMAGE                } else {                    OTHER                }            }        } else {            PROGRESS_VIEW        }    }    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {        val view = when (viewType) {            SELF -> LayoutInflater.from(parent.context).inflate(R.layout.item_conv_self, parent, false)            OTHER -> LayoutInflater.from(parent.context).inflate(R.layout.item_conv_other, parent, false)            else -> LayoutInflater.from(parent.context).inflate(R.layout.item_conv_else, parent, false)        }        return ViewHolder(view)    }    override fun getItemCount(): Int {        return commonItemLists.size    }    override fun onBindViewHolder(holder: ViewHolder, position: Int) {        if (holder.itemViewType != PROGRESS_VIEW) {            val item = commonItemLists[holder.adapterPosition] as Conversation            when {                holder.itemViewType == SELF -> {                    holder.message.text = item.convMessage                }                holder.itemViewType == SELF_IMAGE -> {                }                holder.itemViewType == OTHER -> {                    ImageManager.loadImage(holder.otherPhoto, item.convUser?.userPhoto, R.drawable.googleg_disabled_color_18)                    holder.otherMessageTv.text = item.convMessage                }                holder.itemViewType == OTHER_IMAGE -> {                }            }        }        if (holder.adapterPosition == itemCount - 1 && hasMore) {            listener(pageNo)        }    }    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer    fun addMoreData(data: ArrayList<Conversation>, hasMore: Boolean) {        val oldSize = itemCount        commonItemLists.remove(PROGRESS_VIEW)        this.hasMore = hasMore        data.reverse()        this.commonItemLists.addAll(data)        if (this.hasMore) {            pageNo++            commonItemLists.add(PROGRESS_VIEW)        }        if (oldSize > itemCount) {            this.hasMore = false            notifyItemRangeRemoved(itemCount, oldSize)        } else {            notifyItemRangeInserted(oldSize, itemCount)        }    }    class ProfileEpisodeItemDecoration(private val leftMargin: Int, private val firstItemSpace: Int, private val lastItemSpace: Int, private val rightMargin: Int, private val verticalSpaceHeight: Int) : RecyclerView.ItemDecoration() {        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {            super.getItemOffsets(outRect, view, parent, state)            if (parent.getChildAdapterPosition(view) == 0) {                outRect.top = firstItemSpace            }            outRect.left = leftMargin            outRect.right = rightMargin            if (lastItemSpace != 0 && parent.getChildAdapterPosition(view) == parent.adapter!!.itemCount - 1) {                outRect.bottom = lastItemSpace            } else {                outRect.bottom = verticalSpaceHeight            }        }    }}