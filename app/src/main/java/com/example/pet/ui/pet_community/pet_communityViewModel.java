package com.example.pet.ui.pet_community;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class pet_communityViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public pet_communityViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}