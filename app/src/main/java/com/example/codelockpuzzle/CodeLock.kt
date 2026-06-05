package com.example.codelockpuzzle

import ads_mobile_sdk.h4
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.uuid.Uuid.Companion.random
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.fillMaxSize
import androidx.navigation.NavHostController

@Composable
fun CodeLock(navcontroller: NavHostController) {
    Box(modifier = Modifier.fillMaxSize().background(color = Color.DarkGray)) {
    val start by remember {mutableStateOf((1..5).random())}
    val target by remember {  mutableStateOf ((80..120).random())}
    var current by remember { mutableStateOf(start) }
    var MovesLeft by remember { mutableStateOf(10) }
    var showdialogwin by remember { mutableStateOf(false) }
    var showdialogloss by remember { mutableStateOf(false) }
    var restart by remember { mutableStateOf(false) }
    fun reverseInt(input: Int): Int {
        var number = input
        var reversed = 0
        while (number != 0) {
            val digit = number % 10
            reversed = reversed * 10 + digit
            number /= 10
        }
        return reversed
    }
    fun sumdgt(input:Int) :Int {
        var number = input
        var sum = 0
        while (number != 0) {
            val digit = number % 10
            sum += digit
            number /= 10
        }
        return sum
    }

    fun operation( x : Int) {
        if(x==1) current+=1;
        else if(x==2) current+=3;
        else if(x==3) current*=2;
        else if(x==4) current=reverseInt(current);
        else if(x==5) current=sumdgt(current);
        else if(x==6) current=start;
        MovesLeft-=1
        if (current == target) {
            showdialogwin = true
        } else if (MovesLeft == 0) {
            showdialogloss = true
        }
    }

    Column() {
        Row() {
            Text(
                text = "Code Lock",
                modifier = Modifier.padding(20.dp, 30.dp, 20.dp, 10.dp).fillMaxWidth(),
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 54.sp,
                textAlign = TextAlign.Center
            )
        }
        Box(
            modifier = Modifier.size(400.dp).padding(15.dp).clip(RoundedCornerShape(40.dp))
                .background(color = Color(0xFF222222))
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Column() {
                        Row() {
                            Text(
                                text = "Start",
                                fontSize = 24.sp,
                                color = Color(0xFFA4A4A4)
                            )
                        }
                        Row(
                            modifier = Modifier.padding(16.dp),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "$start",
                                fontSize = 30.sp,
                                color = Color.White
                            )
                        }
                    }
                    Column() {
                        Row() {
                            Text(
                                text = "Target",
                                fontSize = 24.sp,
                                color = Color(0xFFA4A4A4)
                            )
                        }
                        Row(
                            modifier = Modifier.padding(16.dp),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "$target",
                                fontSize = 30.sp,
                                color = Color(0xFF16B6A5)
                            )
                        }
                    }
                }
                HorizontalDivider(
                    thickness = 1.dp,
                    color = Color(0xFF3E3E3E)
                )
                Column() {
                    Spacer(modifier = Modifier.padding(8.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Current",
                            fontSize = 24.sp,
                            color = Color(0xFFA4A4A4)
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "$current",
                            fontSize = 80.sp,
                            color = Color.White
                        )
                    }
                    Spacer(modifier = Modifier.padding(8.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Moves Left",
                            fontSize = 24.sp,
                            color = Color(0xFFA4A4A4)
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(8.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "$MovesLeft",
                            fontSize = 24.sp,
                            color = Color(0xFF16B6A5)
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.padding(10.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(
                onClick = { operation(1) }, colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Gray,      // Background color
                    contentColor = Color.White       // Text/Icon color
                ), shape = RoundedCornerShape(10.dp)
            ) {
                Text(text = "+1")
            }
            Button(
                onClick = { operation(2) }, colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Gray,      // Background color
                    contentColor = Color.White       // Text/Icon color
                ), shape = RoundedCornerShape(10.dp)
            ) {
                Text(text = "+3")
            }
            Button(
                onClick = { operation(3) }, colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Gray,      // Background color
                    contentColor = Color.White       // Text/Icon color
                ), shape = RoundedCornerShape(10.dp)
            ) {
                Text(text = "x2")
            }
        }
        Spacer(modifier = Modifier.padding(6.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(
                onClick = { operation(4) }, colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Gray,      // Background color
                    contentColor = Color.White       // Text/Icon color
                ), shape = RoundedCornerShape(10.dp)
            ) {
                Text(text = "reverse")
            }
            Button(
                onClick = { operation(5) }, colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Gray,      // Background color
                    contentColor = Color.White       // Text/Icon color
                ), shape = RoundedCornerShape(10.dp)
            ) {
                Text(text = "sum")
            }
            Button(
                onClick = { operation(6) }, colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Red,      // Background color
                    contentColor = Color.White       // Text/Icon color
                ), shape = RoundedCornerShape(10.dp)
            ) {
                Text(text = "reset")
            }
        }
        Spacer(modifier = Modifier.padding(16.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
            Button(
                onClick = { navcontroller.navigate("firstscreen") },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Red,
                    contentColor = Color.White
                ), shape = RoundedCornerShape(15.dp)) {
                Text("Restart")
            }
        }

    }
    
    if (showdialogwin) {
        AlertDialog(
            onDismissRequest = { restart=true
                               showdialogwin=false},
            confirmButton = {
                TextButton(onClick = { restart=true
                    showdialogwin = false}) {
                    Text("Play Again")
                }
            },
            title = { Text("Winner Winner Chicken Dinner") },
            text = { Text("You Won in ${10-MovesLeft} Moves") }
            
        )
    }
    if (showdialogloss) {
        AlertDialog(
            onDismissRequest = { restart=true
                               showdialogloss=false },
            confirmButton = {
                TextButton(onClick = { restart=true
                    showdialogloss=false}) {
                    Text("Play Again")
                }
            },
            title = { Text("Better Luck next time") },
            text = { Text("You ran out of moves") }
        )
    }
    if(restart) {
        CodeLock(navcontroller)
        showdialogwin=false
        showdialogloss=false
    }

}

}