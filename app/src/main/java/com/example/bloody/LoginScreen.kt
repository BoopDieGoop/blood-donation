@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.bloody

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController

@Composable
fun LogScreen(padding: PaddingValues, context: Context, navController: NavHostController) {
    var openS by remember {
        mutableStateOf(false)
    }
    var openL by remember {
        mutableStateOf(false)
    }
    var vis by remember {
        mutableStateOf(false)
    }
    var usrl by remember {
        mutableStateOf("")
    }
    var pasl by remember {
        mutableStateOf("")
    }
    var usrc by remember {
        mutableStateOf("")
    }
    var pasc by remember {
        mutableStateOf("")
    }
    var pacc by remember {
        mutableStateOf("")
    }

    ConstraintLayout(
        Modifier
            .padding(padding)
            .fillMaxSize()
    ) {
        val (title, img, help, log, sign) = createRefs()

        Text(text = "Blood Bank", fontSize = 30.sp, modifier = Modifier
            .constrainAs(title) {
                top.linkTo(parent.top, margin = 55.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })

        Image(painter = painterResource(id = R.drawable.drop_filled), contentDescription = "",
            Modifier
                .size(200.dp)
                .constrainAs(img) {
                    top.linkTo(title.bottom, margin = 35.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })

        Button(onClick = {
            openS = true
        }, shape = RoundedCornerShape(5.dp), modifier = Modifier
            .width(100.dp)
            .constrainAs(sign) {
                top.linkTo(img.bottom, margin = 95.dp)
                start.linkTo(parent.start)
                end.linkTo(log.start, margin = 35.dp)
            }) {
            Text(text = "Sign up")
        }

        Button(onClick = {
            openL = true
        }, shape = RoundedCornerShape(5.dp), modifier = Modifier
            .width(100.dp)
            .constrainAs(log) {
                top.linkTo(img.bottom, margin = 95.dp)
                start.linkTo(sign.end, margin = 35.dp)
                end.linkTo(parent.end)
            }) {
            Text(text = "Login")
        }

        Text(
            text = "Please Login or Sign Up to Continue",
            fontSize = 10.sp,
            modifier = Modifier.constrainAs(help) {
                top.linkTo(log.bottom, margin = 5.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })
    }

    if (openS) {
        AlertDialog(onDismissRequest = {
            openS = false
            vis = false
            pacc = ""
            pasc = ""
            usrc = ""
        }, confirmButton = {
            Button(
                onClick = {
                    if (pacc == pasc && usrc != "" && pacc != "") {
                        openS = false
                        vis = false
                        pacc = ""
                        Toast.makeText(context, "Creating Account", Toast.LENGTH_SHORT).show()
                    }
                    else{
                        openS = true
                        Toast.makeText(context, "Retry Please", Toast.LENGTH_SHORT).show()
                    }
                },
                shape = RoundedCornerShape(5.dp)
            )
            { Text(text = "Continue") }
        },
            dismissButton = {
                TextButton(onClick = {
                    openS = false
                    vis = false
                    pacc = ""
                    pasc = ""
                    usrc = ""
                })
                { Text(text = "Cancel") }
            },
            title = { Text(text = "Create Account") },
            text = {
                ConstraintLayout(
                    modifier = Modifier.wrapContentSize(),
                ) {
                    val (usc, psc, pscr, chkc) = createRefs()

                    OutlinedTextField(
                        value = usrc,
                        onValueChange = { usrc = it },
                        singleLine = true,
                        modifier = Modifier
                            .constrainAs(usc) {
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                                top.linkTo(parent.top, margin = 15.dp)
                            },
                        label = {
                            Text(
                                text = "Username"
                            )
                        })
                    OutlinedTextField(
                        value = pasc,
                        onValueChange = { pasc = it },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        visualTransformation = if (vis) VisualTransformation.None else PasswordVisualTransformation(),
                        modifier = Modifier
                            .constrainAs(psc) {
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                                top.linkTo(usc.bottom, margin = 10.dp)
                            },
                        label = {
                            Text(
                                text = "Password"
                            )
                        })
                    OutlinedTextField(
                        value = pacc,
                        onValueChange = { pacc = it },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        visualTransformation = if (vis) VisualTransformation.None else PasswordVisualTransformation(),
                        modifier = Modifier
                            .constrainAs(pscr) {
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                                top.linkTo(psc.bottom, margin = 10.dp)
                            },
                        label = {
                            Text(
                                text = "Confirm Password"
                            )
                        })
                    Row(modifier = Modifier.constrainAs(chkc) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(pscr.bottom, margin = 5.dp)
                    }) {
                        Checkbox(
                            checked = false,
                            onCheckedChange = { vis = it },
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                        Text(
                            text = "Show Password",
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .padding(start = 3.dp)
                        )
                    }
                }
            }
        )
    }

    if (openL) {
        AlertDialog(onDismissRequest = {
            openL = false
            vis = false
            pasl = ""
            usrl = ""
        }, confirmButton = {
            Button(
                onClick = {
                    if(pasl != "" &&((usrl == usrc && pasl == pasc)||(usrl == "admin" && pasl == "123"))) {
                        openL = false
                        vis = false
                        pasl = ""
                        usrl = ""
                        navController.navigate(route = Screens.Info.name)
                    }
                    else{
                        openL = true
                        Toast.makeText(context, "Retry Please", Toast.LENGTH_SHORT).show()
                    }
                },
                shape = RoundedCornerShape(5.dp)
            )
            { Text(text = "Continue") }
        },
            dismissButton = {
                TextButton(onClick = {
                    openL = false
                    vis = false
                    pasl = ""
                    usrl = ""
                })
                { Text(text = "Cancel") }
            },
            title = { Text(text = "Login") },
            text = {
                ConstraintLayout(
                    modifier = Modifier.wrapContentSize(),
                ) {
                    val (uss, pss, chkl) = createRefs()

                    OutlinedTextField(
                        value = usrl,
                        onValueChange = { usrl = it },
                        singleLine = true,
                        modifier = Modifier
                            .constrainAs(uss) {
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                                top.linkTo(parent.top, margin = 15.dp)
                            },
                        label = {
                            Text(
                                text = "Username"
                            )
                        })
                    OutlinedTextField(
                        value = pasl,
                        onValueChange = { pasl = it },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        visualTransformation = if (vis) VisualTransformation.None else PasswordVisualTransformation(),
                        modifier = Modifier
                            .constrainAs(pss) {
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                                top.linkTo(uss.bottom, margin = 10.dp)
                            },
                        label = {
                            Text(
                                text = "Password"
                            )
                        })
                    Row(modifier = Modifier.constrainAs(chkl) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(pss.bottom, margin = 5.dp)
                    }) {
                        Checkbox(
                            checked = false,
                            onCheckedChange = { vis = it },
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                        Text(
                            text = "Show Password",
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .padding(start = 3.dp)
                        )
                    }
                }
            }
        )
    }
}