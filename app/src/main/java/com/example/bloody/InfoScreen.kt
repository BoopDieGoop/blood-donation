package com.example.bloody

import android.app.DatePickerDialog
import android.content.Context
import android.widget.DatePicker
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import java.util.Calendar

@Composable
fun DataScreen(padding: PaddingValues, context: Context) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
    ) {
        val (crd, calender) = createRefs()

        val myCal = Calendar.getInstance()
        val yrCurrent = myCal.get(Calendar.YEAR)
        val mnCurrent = myCal.get(Calendar.MONTH)
        val dyCurrent = myCal.get(Calendar.DAY_OF_MONTH)
        val myYear by remember {
            mutableStateOf(yrCurrent)
        }
        val myMonth by remember {
            mutableStateOf(mnCurrent)
        }
        val myDay by remember {
            mutableStateOf(dyCurrent)
        }
        var myDate by remember {
            mutableStateOf("")
        }
        Card(elevation = CardDefaults.elevatedCardElevation(10.dp), modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth()
            .height(500.dp)
            .constrainAs(crd) {

            }) {
            ConstraintLayout(modifier = Modifier.fillMaxSize()) {
                val (ava, num, nme, dob, typ, cen) = createRefs()

                Image(
                    painter = painterResource(id = R.drawable.account_box),
                    contentDescription = "",
                    modifier = Modifier
                        .size(200.dp)
                        .constrainAs(ava) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            top.linkTo(parent.top, margin = 25.dp)
                        })

                Row(modifier = Modifier.constrainAs(num) {
                    start.linkTo(parent.start, margin = 40.dp)
                    top.linkTo(ava.bottom, margin = 20.dp)
                }) {
                    Text(text = "Account Number: ", color = MaterialTheme.colorScheme.secondary)
                    Text(text = "502839")
                }

                Row(modifier = Modifier.constrainAs(nme) {
                    start.linkTo(parent.start, margin = 40.dp)
                    top.linkTo(num.bottom, margin = 25.dp)
                }) {
                    Text(text = "Name: ", color = MaterialTheme.colorScheme.secondary)
                    Text(text = "Adithya S")
                }

                Row(modifier = Modifier.constrainAs(dob) {
                    start.linkTo(parent.start, margin = 40.dp)
                    top.linkTo(nme.bottom, margin = 25.dp)
                }) {
                    Text(text = "Date of Birth: ", color = MaterialTheme.colorScheme.secondary)
                    Text(text = "23/10/2002")
                }

                Row(modifier = Modifier.constrainAs(typ){
                    start.linkTo(parent.start, margin = 40.dp)
                    top.linkTo(dob.bottom, margin = 25.dp)
                }) {
                    Text(text = "Blood Type: ", color = MaterialTheme.colorScheme.secondary)
                    Text(text = "O+")
                }

                Row(modifier = Modifier.constrainAs(cen){
                    start.linkTo(parent.start, margin = 40.dp)
                    top.linkTo(typ.bottom, margin = 25.dp)
                }) {
                    Text(text = "Donation Centre: ", color = MaterialTheme.colorScheme.secondary)
                    Text(text = "Centre 04")
                }
            }
        }
        if (myDate != ""){
            Text(
                text = "Donation Scheduled for $myDate",
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.constrainAs(calender) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(crd.bottom, margin = 25.dp)
                })
        }
        else {
            Button(onClick = {
                DatePickerDialog(
                    context,
                    0,
                    { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
                        myDate = "$mDayOfMonth/${mMonth + 1}/$mYear"
                    },
                    myYear,
                    myMonth,
                    myDay
                ).show()
            }, shape = RoundedCornerShape(5.dp), modifier = Modifier.constrainAs(calender) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(crd.bottom, margin = 25.dp)
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.edit_calendar),
                    contentDescription = ""
                )
                Text(text = "Schedule Donation", modifier = Modifier.padding(start = 5.dp))
            }
        }
    }
}

