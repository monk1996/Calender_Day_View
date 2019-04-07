package com.alamkanak.weekview

import android.content.Context
import java.util.*

/**
 * Created by Raquib on 1/6/2015.
 */
interface DateTimeInterpreter {
    fun interpretDate(date: Calendar): String
    fun interpretTime(hour: Int): String
}

internal class DefaultDateTimeInterpreter(
        context: Context,
        numberOfDays: Int
) : DateTimeInterpreter {

    private var sdfDate = getDefaultDateFormat(numberOfDays)
    private val sdfTime = getDefaultTimeFormat(context)

    // This calendar is only used for interpreting the time. To avoid issues with time changes,
    // we always use the first day of the year
    private val calendar = firstDayOfYear()

    fun setNumberOfDays(numberOfDays: Int) {
        sdfDate = getDefaultDateFormat(numberOfDays)
    }

    override fun interpretDate(date: Calendar): String {
        return sdfDate.format(date.time).toUpperCase()
    }

    override fun interpretTime(hour: Int): String {
        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, 0)
        return sdfTime.format(calendar.time)
    }

}
