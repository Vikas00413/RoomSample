package com.demo.test.noteapp.model

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.android.databinding.library.baseAdapters.BR



class NoteData:BaseObservable() {
    var date:String=""
        @Bindable get() = field
        set(date) {
            field = date
            notifyPropertyChanged(BR.date)
        }
    var title:String?=null
        @Bindable get() = field
        set(title) {
            field = title
            notifyPropertyChanged(BR.title)
        }
    var description:String?=null
        @Bindable get() = field
        set(description) {
            field = description
            notifyPropertyChanged(BR.description)
        }
    var noteid:String?=null


}