package com.example.madina.weekend

import android.content.DialogInterface
import android.icu.util.Calendar
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import com.example.madina.utils.BaseViewModel
import com.thecode.aestheticdialogs.AestheticDialog
import com.thecode.aestheticdialogs.OnDialogClickListener
import kotlinx.coroutines.newFixedThreadPoolContext
import java.text.SimpleDateFormat
import java.util.*


class WeekEndViewModel: BaseViewModel<WeekEndNavigator>() {


    var  startDate  : MutableLiveData<String> = MutableLiveData<String>()
    var  endDate : MutableLiveData<String> = MutableLiveData<String>()


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
            checkIfApplicapleVacations(dayNameOF_start,dayNameOF_end)
//
//    // if applica --> add to firebase
//
//    // if not --> show dialog
//

    }

    private fun checkIfApplicapleVacations(daynameofStart: String, daynameofEnd: String):Boolean {
         var isApp = false
        //خميس وسبت ولا لا
        if(daynameofStart.contains("Thur")&&daynameofEnd.contains("Satur")){
            //calc needs
                calculateNeeds()
            isApp= true

        }else {
            navigator?.controlProgressBar(false)
            navigator!!.showDialoge("لا يمكن عمل اجازة الا من يوم الخميس" ,
                postAction = DialogInterface.OnClickListener {
                        dialogInterface, i ->  dialogInterface.dismiss()},null)}

        //  start()- currentt date =2
            return isApp
    }

    private fun calculateNeeds() {
        //calculate money needed as a taxe
        //get num of start , num of end
        val start = startDate.value?.toString()?.substring(0,2)?.toInt()
        Log.e("get day num start",start.toString())

        val end = endDate.value?.toString()?.substring(0,2)?.toInt()
        Log.e("get day num end",end.toString())

        val taxNum=end!!-start!!
        if(taxNum<2){
            navigator?.controlProgressBar(false)
            navigator?.showDialoge(
                message = "لديك غرامات نتيجة تأخرك في طلب الاجازة وقيمتها = ${taxNum}" ,

                postAction = DialogInterface.OnClickListener {
                        dialogInterface, i ->
                    navigator?.controlProgressBar(true)
                    addTaxesToFirebaseNeedsCollection(taxNum)
                    addVacationToFireBase()
                                          }
                , negAction = DialogInterface.OnClickListener {
                        dialogInterface,i -> dialogInterface.dismiss() })

        }else{
            //
            navigator?.controlProgressBar(false)
            navigator?.showDialoge("ليس لديك غرامات ,هل تريد اتمام الاجازة؟",
               postAction = DialogInterface.OnClickListener {
                       dialogInterface, i ->
                   navigator?.controlProgressBar(true)
                   addTaxesToFirebaseNeedsCollection(taxNum)
                   addVacationToFireBase()},negAction = DialogInterface.OnClickListener {
                        dialogInterface, i -> dialogInterface.dismiss() })


                }

        }



    private fun addVacationToFireBase() {
    }

    private fun addTaxesToFirebaseNeedsCollection(taxNum: Int) {
        // collection taxes
    }


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
        Log.e(" current date",formattedDate)
        Log.e("start  date",startDate.value+"")
        Log.e("end date",endDate.value+"")
        return formattedDate
    }


}
