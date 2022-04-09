package com.example.attendifyclient


import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.common.BitMatrix
import com.google.zxing.qrcode.QRCodeWriter
import com.google.zxing.qrcode.encoder.QRCode


private lateinit var ivQRCode: ImageView
private lateinit var Btn1 : Button
var bitmap: Bitmap? = null
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ivQRCode=findViewById(R.id.idIVQrcode)
        Btn1=findViewById(R.id.Btn1)


        val mId = Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)

       Btn1.setOnClickListener {



            if (mId.isEmpty()){


            Toast.makeText(this,"Error Reading Android ID",Toast.LENGTH_SHORT).show()

       }
           else{

            val writer = QRCodeWriter()
                try{

val bitMatrix=writer.encode(mId, BarcodeFormat.QR_CODE,512,512)
val width=bitMatrix.width
                    val height=bitMatrix.height
                    val bmp=Bitmap.createBitmap(width,height,Bitmap.Config.RGB_565)
                    for (x in 0 until width){

                        for (y in 0 until height){


                            bmp.setPixel(x,y,if(bitMatrix[x,y]) Color.BLACK else Color.WHITE)
                        }
                    }
                    ivQRCode.setImageBitmap(bmp)

                }
                catch (e : WriterException){

                    e.printStackTrace()
                }


            }




        }




    }
}