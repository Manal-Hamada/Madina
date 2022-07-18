package com.example.madina.weekend

import android.content.DialogInterface
import android.icu.util.Calendar
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import com.example.madina.utils.BaseViewModel
import com.example.madina.utils.Constants
import com.example.madina.utils.Constants.db
import com.google.firebase.auth.FirebaseAuth
import java.text.SimpleDateFormat
import java.util.*


class WeekEndViewModel : BaseViewModel<WeekEndNavigator>() {


    var  startDate  : MutableLiveData<String> = MutableLiveData<String>()
    var  endDate : MutableLiveData<String> = MutableLiveData<String>()
    var  taxesAddedSuccess : MutableLiveData<String> = MutableLiveData<String>()



    @RequiresApi(Build.VERSION_CODES.N)
    fun createVacation() {
        navigator?.controlProgressBar(true)

        //get network time + get day name
          val currentDate = getNetworkDate()

         val dayNameOF_currentDay=getReadableDayName(currentDate)
        Log.e(" current date",dayNameOF_currentDay)

//
//
//
//        //get days name for start and end
       val dayNameOF_start=getReadableDayName(startDate.value.toString())
       Log.e(" current date",dayNameOF_start)
        val dayNameOF_end=getReadableDayName(endDate.value.toString())
          Log.e(" current date",dayNameOF_end)
//    //check if applicaple or not
//
            checkIfApplicapleVacations(dayNameOF_start,dayNameOF_end , currentDate)
//
//    // if applica --> add to firebase
//
//    // if not --> show dialog
//

    }

    private fun checkIfApplicapleVacations(
        daynameofStart: String,
        daynameofEnd: String,
        currentDate: String
    ):Boolean {
         var isApp = false
        //خميس وسبت ولا لا
        if(daynameofStart.contains("Thur")&&daynameofEnd.contains("Satur")){
            //calc needs
                calculateNeeds(currentDate)
            isApp= true

        }else {
         navigator?.hideLodingDialog()
            navigator!!.showDialoge("لا يمكن عمل اجازة الا من يوم الخميس" ,
                postAction = DialogInterface.OnClickListener {
                        dialogInterface, i ->  dialogInterface.dismiss()},null)}

        //  start()- currentt date =2
            return isApp
    }

    private fun calculateNeeds(currentDate: String) {
        //calculate money needed as a taxe
        //get num of start , num of end
        val start = startDate.value?.toString()?.substring(0,2)?.toInt()
        Log.e("get day num start",start.toString())
          val end = endDate.value?.toString()?.substring(0,2)?.toInt()
        Log.e("get day num end",end.toString())
        val cur= currentDate.toString().substring(0,2)?.toInt()
        Log.e("get day num end",end.toString())
         val taxNum=start!!-cur
        if(taxNum<2){
           navigator?.hideLodingDialog()
            navigator?.showDialoge(
                message = "لديك غرامات نتيجة تأخرك في طلب الاجازة وقيمتها = ${taxNum*25}" ,

                postAction = DialogInterface.OnClickListener {
                        dialogInterface, i ->
                    navigator?.showLodingDialog()
                    Log.e("needs ","taxe")
                    addTaxesToFirebaseNeedsCollection(taxNum*25)
                    addVacationToFireBase()
                                          }
                , negAction = DialogInterface.OnClickListener {
                        dialogInterface,i -> dialogInterface.dismiss() })

        }else{
            //
           navigator?.hideLodingDialog()
            navigator?.showDialoge("ليس لديك غرامات ,هل تريد اتمام الاجازة؟",
               postAction = DialogInterface.OnClickListener {
                       dialogInterface, i ->
                    navigator?.showLodingDialog()
                   addVacationToFireBase()}
                ,negAction = DialogInterface.OnClickListener {
                        dialogInterface, i -> dialogInterface.dismiss() })


                }

        }





    private fun addVacationToFireBase() {
        Log.e("add vac fun","")
     // navigator!!.showLodingDialog()
      var user_id = getUserId()
       var vacation : VacationModel = VacationModel(user_id,startDate.value,endDate.value,null)
        Constants.db.collection(Constants.vacationRef).
        add(vacation).addOnSuccessListener {
            Log.e("add vac fun","on success")

            navigator!!.hideLodingDialog()
                navigator!!.showDialoge("تمت اضافة الاجازة بنجاح" , DialogInterface.OnClickListener {
                    dialogInterface,
                    i -> dialogInterface.dismiss()
                    navigator?.dismmissBottomSheetFragment()},null)

        }.addOnFailureListener{
            Log.e("add vac fun","on fail")

            navigator!!.hideLodingDialog()
            navigator!!.showDialoge(
                "هناك مشكلة,الرجاء طلب الاجازة مرة اخري" , DialogInterface.OnClickListener {
                    dialogInterface,
                    i -> dialogInterface.dismiss()
                    Log.e("problem" , it.localizedMessage)
                      },null)


        }



    }

    private fun getUserId(): Any {
        val user_id = FirebaseAuth.getInstance().currentUser?.uid
        Log.e("userid",user_id+"")
        return user_id!!
    }

    private fun addTaxesToFirebaseNeedsCollection(taxNum: Int) {
        Log.e("add taxe fun ",taxNum.toString())
        // collection taxes
          // navigator!!.showLodingDialog()
        var user_id = getUserId()
        var vacation : VacationModel = VacationModel(user_id,startDate.value,endDate.value,"${taxNum}جنيها")
        Constants.db.collection(Constants. taxesRef).
         add(vacation).addOnSuccessListener {
            Log.e(" onsuccess","taxe added")
             addVacationToFireBase()
        }.addOnFailureListener{
            Log.e("on fail","taxe not added")
            navigator!!.hideLodingDialog()
            navigator!!.showDialoge(
                "هناك مشكلة,الرجاء طلب الاجازة مرة اخري" , DialogInterface.OnClickListener {
                        dialogInterface,
                        i -> dialogInterface.dismiss()
                    Log.e("problem" , it.localizedMessage)
                },null)
    }}


    fun getReadableDayName(date:String):String{
        val d = SimpleDateFormat("dd/MM/yyyy").parse(date)
        val format = SimpleDateFormat("EEEE")
        val dayOfTheWeek = format.format(d)
        return dayOfTheWeek
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun getNetworkDate( ) : String {

        val c = Calendar.getInstance().time
        println("Current time => $c")


        val df = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val formattedDate = df.format(c)
        Log.e("current date",formattedDate)
        Log.e("start  date",startDate.value+"")
        Log.e("end date",endDate.value+"")
        return formattedDate
    }

    fun getAllNeedsAndTaxes(){
        navigator?.showLodingDialog()
       val  userId = getUserId()
        db.collection(Constants.taxesRef)
            .whereEqualTo("userId", userId)
            .get()
            .addOnSuccessListener {documents ->
                navigator?.hideLodingDialog()
                Log.e("list of needs",documents.toObjects(VacationModel::class.java).toString())
                navigator?.listAllNeedsFromFirebase(documents.toObjects(VacationModel::class.java))
            }
            .addOnFailureListener { exception ->
                navigator?.showDialoge("حدثت مشكلة , الرجاء المحاولة لاحقا مرة أخري",
                    DialogInterface.OnClickListener { dialogInterface, i -> dialogInterface.dismiss() },null)
            }


    }


}
