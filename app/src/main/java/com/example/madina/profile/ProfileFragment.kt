package com.example.madina.profile


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.madina.database.signIn
import com.example.madina.databinding.ProfieFragmentBinding
import com.example.madina.login.LoginActivity
import com.example.madina.model.AppUser
import com.google.firebase.auth.FirebaseAuth

class ProfileFragment:Fragment(),Navigator {

    var _binding: ProfieFragmentBinding? = null
    lateinit var viewModel: ProfileViewModel
    //lateinit var viewModel2: SignViewModel
    lateinit var proUser:AppUser


    // This property is only valid between onCreateView and
    // onDestroyView.

    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        _binding?.prvm2 = viewModel
        //_binding?.buildingTxt?.setText("oo")
        //logOut()


        _binding = ProfieFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val pUid:String=viewModel.userId.toString()
        setProfileData(pUid)
        logOut()

    }

    fun logOut() {
        _binding?.logoutBtn?.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            activity?.let {
                val intent = Intent(it, LoginActivity::class.java)
                intent.addFlags(
                    Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                            or Intent.FLAG_ACTIVITY_CLEAR_TASK
                )
                it.startActivity(intent)
                it.finish()
                Log.e("error", "hi")
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun openLoginScreen() {
        val intent = Intent(this.requireActivity(), LoginActivity::class.java)
        this.requireActivity().startActivity(intent)
    }

    override fun getContext(): Context {
        return this.requireActivity()
    }

    fun setProfileData(uid:String){

        Log.e("error",viewModel.userId.toString())
        signIn(uid, onSuccessListener =
        {documentSnapshot->
            val user= documentSnapshot.toObject(AppUser::class.java)
            if(user==null){
                Log.e("error","data==null")
                return@signIn}
            else
                _binding?.nameTxt?.setText(user.name)

            Log.e("error","success")
        }
            , onfaliurListener = {
                Log.e("error","fail")
            })
    }


}