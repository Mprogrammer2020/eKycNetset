package com.library.ekycnetset

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.databinding.ViewDataBinding
import com.library.ekycnetset.base.BaseFragment

// by :- Deepak Kumar
// at :- Netset Software
// in :- kotlin

abstract class EKycBaseFragment<T : ViewDataBinding?> : BaseFragment<T>(), FragmentView {

    private lateinit var mContainerActivity: EKycActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContainerActivity = context as EKycActivity
    }

    fun getContainerActivity(): EKycActivity {
        return mContainerActivity
    }

    override fun onStart() {
        super.onStart()
        mContainerActivity.getCurrentFragment(getCurrentFragment())
        mContainerActivity.setTitle(setTitle())
    }

    /* Validate EditText that checks empty. */
    fun validateEditText(et: EditText): Boolean {

        return if (et.text.toString() == "") {

            et.requestFocus()
            et.error = "This field can't be empty"

            false
        } else
            true
    }


    fun validateEditText(et: EditText, msg: String): Boolean {

        return if (et.text.toString() == "") {

            alertBox(msg)

            false
        } else
            true
    }

    fun alertBox(msg : String){
        showToast(msg)
    }

    /* Checks entered email is valid or not. */
    fun isEmailValid(et: EditText): Boolean {
        val isValid = android.util.Patterns.EMAIL_ADDRESS.matcher(et.text.toString()).matches()

        return if (isValid) {
            true
        } else {
            et.requestFocus()
            et.error = "Entered email address is not valid"
            false
        }
    }

    fun isEmailValid(et: EditText, msg: String): Boolean {
        val isValid = android.util.Patterns.EMAIL_ADDRESS.matcher(et.text.toString()).matches()

        return if (isValid) {
            true
        } else {
            alertBox(msg)
            false
        }
    }


    /* Checks both passwords are same or not. */
    fun comparePassword(etOne: EditText, etTwo: EditText): Boolean {

        if (etOne.text.toString() == etTwo.text.toString()) {
            return true
        } else {
//            etTwo.requestFocus()
//            etTwo.error = "Password and Confirm password doesn't match"
            alertBox("Password and Confirm password doesn't match")
            return false
        }
    }

    fun isPasswordValid(et: EditText, msg: String): Boolean {

        return if (et.text.toString().length >= 6) {
            true
        } else {
            alertBox(msg)
            false
        }
    }


    fun notWithSpace(et: EditText) {
        val myWatcher = object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                if (et.text.toString() == " ")
                    et.setText("")
            }
        }
        et.addTextChangedListener(myWatcher)
    }

    fun validatePhoneNumber(et: EditText): Boolean {

        return if (et.text.toString().length < 8) {
            et.requestFocus()
            et.error = "Entered phone number is not valid"
            false
        } else
            true
    }

//    fun showError(e: Throwable, activity: AppCompatActivity) {
//
//        var isNetworkException: Boolean
//
//        try {
//            val obj = JSONObject((e as HttpException).response().errorBody()!!.string())
//            val error = obj.optString("message")
//            val code = e.code()
//
//            isNetworkException = false
//
//            L.e("Error Message", "$error $code")
//
//            if (code == 401) {
//
//                bubblePref.clearPrefs(getContainerActivity())
//
////                displayIt(WelcomeFragment(), WelcomeFragment::class.java.canonicalName, true)
////                fragmentManager?.popBackStackImmediate(
////                    null,
////                    FragmentManager.POP_BACK_STACK_INCLUSIVE
////                )
//
//            } else {
//                BubbleCommon.setResponseDialog(activity, error)
//            }
//
//
//        } catch (e: Exception) {
//            isNetworkException = true
//            e.printStackTrace()
//        }
//
//        L.e("TAG", "onError: " + e.message)
//        if (isNetworkException) {
//
//            if (e.message!!.contains("Failed to connect") || e.message!!.contains("Unable to resolve"))
//                BubbleCommon.setResponseDialog(
//                    activity,
//                    activity.getString(R.string.internet_error)
//                )
//            else
//                BubbleCommon.setResponseDialog(activity, e.message!!)
//
//        }
//    }

    fun getPhoneNumber(number: String): String {
        val input = number
        return input.replace(" ", "")
    }

//    fun commonDOBSelection(tv: EditText, userDOB: OnSelectedDOB, isMaxReq: Boolean) {
//
//        val c = Calendar.getInstance()
//
//        val mYear: Int
//        val mMonth: Int
//        val mDay: Int
//
//        if (tv.text.toString().isNotEmpty()) {
//            L.e("Filled DOB", tv.text.toString())
//
//            c.timeInMillis = BubbleCommon.getDateInMillies(tv.text.toString())
//
//            mYear = c.get(Calendar.YEAR)
//            mMonth = c.get(Calendar.MONTH)
//            mDay = c.get(Calendar.DAY_OF_MONTH)
//
//        } else {
//
//            if (isMaxReq)
//                mYear = c.get(Calendar.YEAR) - 16
//            else
//                mYear = c.get(Calendar.YEAR)
//
//            mMonth = c.get(Calendar.MONTH)
//            mDay = c.get(Calendar.DAY_OF_MONTH)
//
//        }
//
////        val c = Calendar.getInstance()
////
////        val mYear = c.get(Calendar.YEAR) - 16
////        val mMonth = c.get(Calendar.MONTH)
////        val mDay = c.get(Calendar.DAY_OF_MONTH)
//
//
//        val datePickerDialog = DatePickerDialog(getContainerActivity(), R.style.DatePickerDialogTheme, { _, year, monthOfYear, dayOfMonth ->
//
//                val calendar = Calendar.getInstance()
//                calendar.set(year, monthOfYear, dayOfMonth)
//
//                val formatMain = SimpleDateFormat("dd MMMM, yyyy", Locale.ENGLISH)
//                tv.setText(formatMain.format(calendar.time))
//
//                val apiFormat = SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH)
//                userDOB.selectedDate(apiFormat.format(calendar.time))
//
//
//            },
//            mYear,
//            mMonth,
//            mDay
//        )
//
//        if (isMaxReq)
//            datePickerDialog.datePicker.maxDate =
//                (System.currentTimeMillis() - (1000 * 60 * 60 * 24 * 365.25 * 16)).toLong()
//        else
//            datePickerDialog.datePicker.maxDate = (System.currentTimeMillis() - 1000)
//
//        datePickerDialog.show()
//    }

    interface OnSelectedDOB {
        fun selectedDate(date: String)
    }

//    fun validateUneditableET(et: EditText, msg: String): Boolean {
//        when {
//            et.text.toString().isEmpty() -> {
//                BubbleCommon.setResponseDialog(
//                    getContainerActivity(),
//                    msg
//                )
//                return false
//            }
//            else -> return true
//        }
//
//    }

    fun setEditableET(editText: EditText, isEditable: Boolean) {
        editText.isFocusable = isEditable
        editText.isFocusableInTouchMode = isEditable
        editText.isClickable = isEditable
        editText.isCursorVisible = isEditable
        editText.requestFocus()
        editText.setSelection(editText.text.length)

        if (isEditable) {
            val inputMethodManager =
                activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.toggleSoftInputFromWindow(
                editText.applicationWindowToken,
                InputMethodManager.SHOW_FORCED,
                0
            )
        }
    }
}