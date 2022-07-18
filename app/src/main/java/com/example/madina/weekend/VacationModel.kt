package com.example.madina.weekend

import androidx.annotation.Keep
import java.io.Serializable
@Keep
data class VacationModel(var userId: Any? =null, var startDate: String?=null, var endDate: String? =null ,var needsTaxes:Any?=null) :Serializable {

}
