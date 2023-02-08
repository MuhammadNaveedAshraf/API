package com.example.profile_v01.Model

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Base64
import java.io.ByteArrayOutputStream

object Person {
        private var image: Bitmap?=null
        private var imageUri:Uri?=null
        var personData = PersonData()

        fun getImage():Bitmap?{return image}
        fun setImage(image:Bitmap?){Person.image = image}
        fun getImageUri():Uri?{return imageUri }
        fun setImageUri(uri:Uri?){ imageUri = uri}
        fun getPerson(personData: PersonData?) {
                if(personData!=null){
                image = personData.image?.let { stringToBitmap(it) }
                imageUri = Uri.parse(personData.imageUri)
                Person.personData = personData
                }
        }
        fun setPerson(): PersonData {
               personData.imageUri = imageUri.toString()
                personData.image = image?.let { bitMapToString(it) }
                return personData

        }

        fun getFullName():String{
                return String.format("%s %s", personData.fname, personData.lname)
        }
        private fun bitMapToString(bitmap: Bitmap): String {
                val baos = ByteArrayOutputStream()
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos)
                val b = baos.toByteArray()
                return Base64.encodeToString(b, Base64.DEFAULT)
        }
        private fun stringToBitmap(string:String):Bitmap?{
                return try {
                        val imageBytes =Base64.decode(string,0)
                        val image= BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
                        image
                } catch(e:Exception ) {
                        e.message
                        null
                }
        }
}