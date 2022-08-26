package com.example.examplewithcompose.common_ui

import android.os.Parcel
import android.os.Parcelable
import androidx.fragment.app.FragmentActivity
import com.example.examplewithcompose.R
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.MaterialDatePicker
import java.time.ZonedDateTime

fun showDatePicker(
    activity: FragmentActivity,
    startDate: Long = MaterialDatePicker.todayInUtcMilliseconds(),
    endDate: Long = MaterialDatePicker.todayInUtcMilliseconds(),
    onDatePicked: (Long?) -> Unit = {},
    validDate: List<Long> = emptyList(),
) {
    val zonedDateTimeOffSet = ZonedDateTime.now().offset.totalSeconds.times(1000L)

    // define date validator
    val dateValidator = object : CalendarConstraints.DateValidator {
        override fun describeContents(): Int = Parcelable.CONTENTS_FILE_DESCRIPTOR

        override fun writeToParcel(p0: Parcel?, p1: Int) {}

        override fun isValid(utcTime: Long): Boolean {
            return validDate.contains(utcTime - zonedDateTimeOffSet)
        }
    }

    val datePicker = MaterialDatePicker.Builder.datePicker().apply {
        this.setTheme(R.style.ThemeOverlay_App_DatePicker)
        this.setCalendarConstraints(
            CalendarConstraints.Builder().apply {
                setStart(startDate - zonedDateTimeOffSet)
                setEnd(endDate - zonedDateTimeOffSet)
                setValidator(dateValidator)
            }.build()
        ).build()
    }
    datePicker.build().also {
        it.addOnPositiveButtonClickListener { utcDate ->
            onDatePicked(utcDate - zonedDateTimeOffSet)
        }
        it.show(activity.supportFragmentManager, it.toString())
    }
}