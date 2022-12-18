package com.example.agencycomposeapp.screens.profile

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.agencycomposeapp.MainActivity
import com.example.agencycomposeapp.MainActivity.Companion.CLIENT_PHONE
import com.example.agencycomposeapp.MainActivity.Companion.DIRECTOR_NAME
import com.example.agencycomposeapp.MainActivity.Companion.DIRECTOR_PHONE
import com.example.agencycomposeapp.MainViewModel
import com.example.agencycomposeapp.R
import com.example.agencycomposeapp.ui.theme.Blues

@Composable
fun DirectorProfileScreen(
    viewModel: MainViewModel,
    onClick: () -> Unit
) {

    val image = painterResource(id = R.drawable.director)

    val agency = viewModel.getAgencyById(MainActivity.AGENCY_ID)

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            contentAlignment = Alignment.TopCenter
        ) {
            Image(
                modifier = Modifier.fillMaxHeight(0.30f),
                painter = image,
                contentDescription = "Login main image",
                contentScale = ContentScale.Crop,
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.65f)
                .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
                .background(colorResource(id = R.color.white))
                .padding(10.dp)
                .verticalScroll(rememberScrollState())
        ) {

            Text(
                text = stringResource(id = R.string.helloUser, DIRECTOR_NAME),
                fontFamily = FontFamily(
                    Font(R.font.gteestiprotext_regular)
                ),
                style = TextStyle(fontWeight = FontWeight.Medium, letterSpacing = 2.sp),
                fontSize = 25.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.padding(10.dp))

            Text(
                text = stringResource(id = R.string.yourCredentials),
                fontFamily = FontFamily(
                    Font(R.font.gteestiprotext_regular)
                ),
                style = TextStyle(
                    fontWeight = FontWeight.Medium, letterSpacing = 2.sp, color = colorResource(
                        id = R.color.black
                    )
                ),
                fontSize = 15.sp,
                textAlign = TextAlign.Left
            )

            Spacer(modifier = Modifier.padding(10.dp))

            OutlinedTextField(
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    disabledTextColor = Color.Black,
                    focusedBorderColor = Blues,
                    unfocusedBorderColor = Blues,
                    focusedLabelColor = Blues,
                    cursorColor = Blues,
                    textColor = colorResource(id = R.color.black),
                ),
                textStyle = TextStyle(
                    color = Color.Black,
                    fontSize = 17.sp,
                    fontFamily = FontFamily(
                        Font(R.font.gteestiprotext_regular)
                    )
                ),
                value = DIRECTOR_NAME,
                onValueChange = {
                    DIRECTOR_NAME = it
                },
                enabled = false,
                label = {
                    Text(
                        text = stringResource(id = R.string.name),
                        fontFamily = FontFamily(
                            Font(R.font.gteestiprotext_regular)
                        ),
                        color = Color.Black
                    )
                },
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.name),
                        fontFamily = FontFamily(
                            Font(R.font.gteestiprotext_regular)
                        ), color = Color.Black
                    )
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(0.8f),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )

            Spacer(modifier = Modifier.padding(5.dp))

            OutlinedTextField(
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    disabledTextColor = Color.Black,
                    focusedBorderColor = Blues,
                    unfocusedBorderColor = Blues,
                    focusedLabelColor = Blues,
                    cursorColor = Blues,
                    textColor = colorResource(id = R.color.black)
                ),
                value = DIRECTOR_PHONE,
                onValueChange = {
                    CLIENT_PHONE = it
                },
                textStyle = TextStyle(
                    color = Color.Black,
                    fontSize = 17.sp,
                    fontFamily = FontFamily(
                        Font(R.font.gteestiprotext_regular)
                    )
                ),
                enabled = false,
                label = {
                    Text(
                        text = stringResource(id = R.string.phone),
                        fontFamily = FontFamily(
                            Font(R.font.gteestiprotext_regular)
                        ),
                        color = Color.Black
                    )
                },
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.phone),
                        fontFamily = FontFamily(
                            Font(R.font.gteestiprotext_regular)
                        ),
                        color = Color.Black
                    )
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(0.8f),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )

            Spacer(modifier = Modifier.padding(5.dp))

            OutlinedTextField(
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    disabledTextColor = Color.Black,
                    focusedBorderColor = Blues,
                    unfocusedBorderColor = Blues,
                    focusedLabelColor = Blues,
                    cursorColor = Blues,
                    textColor = colorResource(id = R.color.black),
                ),
                textStyle = TextStyle(
                    color = Color.Black,
                    fontSize = 17.sp,
                    fontFamily = FontFamily(
                        Font(R.font.gteestiprotext_regular)
                    )
                ),
                value = agency?.agencyName.toString(),
                onValueChange = {
                },
                enabled = false,
                label = {
                    Text(
                        text = stringResource(id = R.string.agency),
                        fontFamily = FontFamily(
                            Font(R.font.gteestiprotext_regular)
                        ),
                        color = Color.Black
                    )
                },
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.agencyName),
                        fontFamily = FontFamily(
                            Font(R.font.gteestiprotext_regular)
                        ), color = Color.Black
                    )
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(0.8f),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )

            Spacer(modifier = Modifier.padding(10.dp))

            CardExit(
                onClick = {
                    onClick()
                }
            )
        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CardExit(onClick: () -> Unit) {
    val paddingModifier = Modifier
        .padding(10.dp)
        .fillMaxSize()
    Card(
        elevation = 10.dp,
        modifier = paddingModifier,
        onClick = { onClick() },
        backgroundColor = Blues,
        contentColor = Color.White,
        shape = RoundedCornerShape(20.dp)
    ) {
        Text(
            text = stringResource(id = R.string.logout),
            modifier = paddingModifier,
            fontFamily = FontFamily(
                Font(R.font.gteestiprotext_regular),
            ),
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )
    }
}