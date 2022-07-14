package com.example.madina.profile

import android.annotation.SuppressLint
import android.app.Activity.RESULT_CANCELED
import android.app.Activity.RESULT_OK
import android.app.AlertDialog
import android.content.ContentResolver
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.CalendarContract.Attendees.query
import android.provider.CalendarContract.EventDays.query
import android.provider.MediaStore
import android.provider.MediaStore.Video.query
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.example.madina.HomeActivity
import com.example.madina.R
import com.example.madina.databinding.FragmentWeekendBinding
import com.example.madina.databinding.ProfieFragmentBinding
import com.example.madina.login.LoginActivity
import com.example.madina.sign.SignViewModel

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment : Fragment(),Navigator{
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: ProfieFragmentBinding? = null
    private val binding get() = _binding!!
    lateinit var viewModel:SignViewModel
    lateinit var viewModel2:ProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        viewModel= ViewModelProvider(this).get(SignViewModel::class.java)
        viewModel2= ViewModelProvider(this).get(ProfileViewModel::class.java)
        _binding?.prvm=viewModel
      _binding?.prvm2=viewModel2
      //  viewModel2.navigator=this
        setLogoutListener()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = ProfieFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProfileFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProfileFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    // Function for displaying an AlertDialogue for choosing an image
    private fun selectImage() {
        val choice = arrayOf<CharSequence>("Take Photo", "Choose from Gallery", "Cancel")
        val myAlertDialog: AlertDialog.Builder = AlertDialog.Builder(context)
        myAlertDialog.setTitle("Select Image")
        myAlertDialog.setItems(choice, DialogInterface.OnClickListener { dialog, item ->
            when {
                // Select "Choose from Gallery" to pick image from gallery
                choice[item] == "Choose from Gallery" -> {
                    val pickFromGallery = Intent(
                        Intent.ACTION_GET_CONTENT,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                    )
                    pickFromGallery.type = "/image"
                    startActivityForResult(pickFromGallery, 1)
                }
                // Select "Take Photo" to take a photo
                choice[item] == "Take Photo" -> {
                    val cameraPicture = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    startActivityForResult(cameraPicture, 0)
                }
                // Select "Cancel" to cancel the task
                choice[item] == "Cancel" -> {
                    myAlertDialog.setCancelable(true)
                }
            }
        })
        myAlertDialog.show()
    }

    override fun openLoginScreen(){
        val intent = Intent(activity, LoginActivity::class.java)
        activity?.startActivity(intent)
    }
    fun logOut(){

        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Do you sure you want to logout?")
        builder.setPositiveButton("Yes"){
                dialogeInerface,which->
           // navigator?.openLoginScreen()
            openLoginScreen()
        }
        builder.setNegativeButton("No"){
                dialogeInerface,which->
        }
        builder.setNeutralButton("Cancel"){
                dialogeInerface,which->
        }
        val alertDialog: AlertDialog =builder.create()
        alertDialog.setCancelable(true)
        alertDialog.show()
    }
    fun setLogoutListener(){
        _binding?.logoutBtn?.setOnClickListener{
            logOut()
        }
    }
    }
