package com.sillylife.realchat.views.fragmentsimport android.os.Bundleimport android.view.LayoutInflaterimport android.view.Viewimport android.view.ViewGroupimport com.sillylife.realchat.Rimport com.sillylife.realchat.models.ConvUserimport com.sillylife.realchat.models.Userimport com.sillylife.realchat.util.FragmentHelperimport kotlinx.android.synthetic.main.fragment_home.*class HomeFragment : BaseFragment() {    companion object {        fun newInstance(): HomeFragment {            return HomeFragment()        }        val TAG = HomeFragment::class.java.simpleName    }    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,                              savedInstanceState: Bundle?): View? {        return inflater.inflate(R.layout.fragment_home, container, false)    }    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {        super.onViewCreated(view, savedInstanceState)        val tempId = "jOVG6CK1ptgnn1ud899umRcIPlm2"        tempBtn.setOnClickListener {            showConversation(ConvUser("not_available", "Mahendra Ratrey(Dad)", tempId))        }    }    private fun showConversation(conv: ConvUser) {        addFragment(ConversationFragment.newInstance(User("", conv.userName, conv.userID, conv.userPhoto)), ConversationFragment.TAG)    }}