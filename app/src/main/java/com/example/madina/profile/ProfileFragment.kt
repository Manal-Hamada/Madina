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
    var arr=ArrayList<String>()


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
        //_binding?.buildingTxt?.setText(fuser)



        _binding = ProfieFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding?.progressPar?.visibility=View.VISIBLE
        setProfile(viewModel.getUserId())
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

    fun setProfile(uid:String){
        Log.e("error",uid)
        signIn(uid,AppUser.COLLECTION_NAME, onSuccessListener =
        {documentSnapshot->
            val user= documentSnapshot.toObject(AppUser::class.java)
            if(user==null){
                Log.e("error","data==null")
                return@signIn}
            else
                _binding?.progressPar?.visibility= View.GONE
             setProfileData(user)

            Log.e("error","success")
        }
            , onfaliurListener = {
                Log.e("error","fail")
            })
    }

    fun setProfileData (user:AppUser){
      //set data in array to be fitched not to load for fitching from database

        _binding?.nameTxt?.setText(user.name.toString())
        _binding?.buildingTxt?.setText(user.buildingNum.toString())
        _binding?.phoneTxt?.setText(user.phoneNum.toString())
        _binding?.roomTxt?.setText(user.roomNum.toString())
        _binding?.emailTxt?.setText(user.email.toString())
        _binding?.colageTxt?.setText(user.colleage.toString())

    }
    fun setArray(){

    }

}