package com.voidbit.crud.view_models

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.voidbit.crud.models.UserData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SharedViewModel: ViewModel() {

    var users = mutableStateOf(emptyList<UserData>())

    fun saveData(userData: UserData, context: Context) = CoroutineScope(Dispatchers.IO).launch {
        val fireStoreRef = Firebase.firestore.collection("user").document(userData.userID!!)
        try {
            fireStoreRef.set(userData).addOnSuccessListener {
                Toast.makeText(context, "Success",Toast.LENGTH_SHORT).show()
            }
        }catch (e: Exception){
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    fun getUserById(
        userID: String,
        context: Context,
        data: (UserData) -> Unit
    ) = CoroutineScope(Dispatchers.IO).launch {

        val fireStoreRef = Firebase.firestore
            .collection("user")
            .document(userID)

        try {
            fireStoreRef.get()
                .addOnSuccessListener {docSnap->
                    // for getting single or particular document
                    if (docSnap.exists()) {
                        Log.i("tag","here: ${docSnap.data}")
                        val userData = docSnap.toObject(UserData::class.java)
                        userData?.let { data(it) }
                    } else {
                        Toast.makeText(context, "No User Data Found", Toast.LENGTH_SHORT).show()
                    }
                }
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    fun getAllUsers(
        context: Context,
    ) = CoroutineScope(Dispatchers.IO).launch {

        val fireStoreRef = Firebase.firestore
            .collection("user")

        val data : MutableList<UserData> = emptyList<UserData>().toMutableList()

        try {
            fireStoreRef.get()
                .addOnSuccessListener {docSnap->
                        Log.i("tag","here: ${docSnap.documents.size}")
                    for(doc in docSnap.documents){
                        Log.i("tag","doc: ${doc.data}")
                        val userData = doc.toObject(UserData::class.java)
                        userData?.let { data.add(it) }
                    }
                    users.value = data
                }
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    fun deleteData(
        userID: String,
        context: Context,
        navController: NavController,
    ) = CoroutineScope(Dispatchers.IO).launch {

        val fireStoreRef = Firebase.firestore
            .collection("user")
            .document(userID)

        try {
            fireStoreRef.delete()
                .addOnSuccessListener {
                    Toast.makeText(context, "Successfully deleted data", Toast.LENGTH_SHORT)
                        .show()
                    navController.popBackStack()
                }
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }
}