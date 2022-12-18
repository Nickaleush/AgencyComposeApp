package com.example.agencycomposeapp.screens.auth

import android.widget.Toast
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.agencycomposeapp.MainActivity.Companion.AGENCY_ID
import com.example.agencycomposeapp.MainActivity.Companion.CLIENT_ID
import com.example.agencycomposeapp.MainActivity.Companion.CLIENT_NAME
import com.example.agencycomposeapp.MainActivity.Companion.CLIENT_PHONE
import com.example.agencycomposeapp.MainActivity.Companion.DIRECTOR_ID
import com.example.agencycomposeapp.MainActivity.Companion.DIRECTOR_MODE
import com.example.agencycomposeapp.MainActivity.Companion.DIRECTOR_NAME
import com.example.agencycomposeapp.MainActivity.Companion.DIRECTOR_PHONE
import com.example.agencycomposeapp.MainViewModel
import com.example.agencycomposeapp.R
import com.example.agencycomposeapp.navigation.graphs.Graph
import com.example.agencycomposeapp.ui.theme.Blues
import com.example.agencycomposeapp.ui.theme.whiteBackground

@Composable
fun SignInScreen(
    navController: NavController,
    onSignUpClick: () -> Unit,
    onOpenAgencyClick: () -> Unit,
    viewModel: MainViewModel
) {
    val passwordVector = painterResource(id = R.drawable.password_eye)
    var phoneValue by remember { mutableStateOf("") }
    var passwordValue by remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }
    var isButtonEnabled by remember { mutableStateOf(false) }

    val image = painterResource(id = R.drawable.login_image)

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            contentAlignment = Alignment.TopCenter
        ) {
            Image(
                modifier = Modifier.fillMaxHeight(0.33f),
                painter = image,
                contentDescription = "Login main image",
                contentScale = ContentScale.Crop,
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.60f)
                .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
                .background(whiteBackground)
                .padding(10.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = stringResource(id = R.string.enterCredentials),
                fontFamily = FontFamily(
                    Font(R.font.gteestiprotext_regular)
                ),
                style = TextStyle(fontWeight = FontWeight.Medium, letterSpacing = 2.sp),
                fontSize = 25.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.padding(10.dp))
            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                OutlinedTextField(
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Blues,
                        unfocusedBorderColor = Color.Gray,
                        focusedLabelColor = Blues,
                        cursorColor = Blues
                    ),
                    textStyle = TextStyle(
                        color = Color.Black,
                        fontSize = 17.sp,
                        fontFamily = FontFamily(
                            Font(R.font.gteestiprotext_regular)
                        )),
                    value = phoneValue,
                    onValueChange = {
                        phoneValue = it
                        isButtonEnabled = phoneValue.isNotEmpty() && passwordValue.isNotEmpty()
                    },
                    label = {
                        Text(
                            text = stringResource(id = R.string.phone),
                            fontFamily = FontFamily(
                                Font(R.font.gteestiprotext_regular)
                            )
                        )
                    },
                    placeholder = {
                        Text(
                            text = stringResource(id = R.string.enterPhoneNumber),
                            fontFamily = FontFamily(
                                Font(R.font.gteestiprotext_regular)
                            )
                        )
                    },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(0.8f),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
                )

                OutlinedTextField(
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Blues,
                        unfocusedBorderColor = Color.Gray,
                        focusedLabelColor = Blues,
                        cursorColor = Blues
                    ),
                    textStyle = TextStyle(
                        color = Color.Black,
                        fontSize = 17.sp,
                        fontFamily = FontFamily(
                        Font(R.font.gteestiprotext_regular)
                    )),
                    value = passwordValue,
                    onValueChange = {
                        passwordValue = it
                        isButtonEnabled = phoneValue.isNotEmpty() && passwordValue.isNotEmpty()
                    },
                    trailingIcon = {
                        IconButton(onClick = {
                            passwordVisibility = !passwordVisibility
                        }) {
                            Icon(
                                painter = passwordVector, contentDescription = "Password icon",
                                tint = if (passwordVisibility) Blues else Color.Gray
                            )
                        }
                    },
                    label = {
                        Text(
                            text = stringResource(id = R.string.password),
                            fontFamily = FontFamily(
                                Font(R.font.gteestiprotext_regular)
                            )
                        )
                    },
                    placeholder = {
                        Text(
                            text = stringResource(id = R.string.enterPassword),
                            fontFamily = FontFamily(
                                Font(R.font.gteestiprotext_regular)
                            )
                        )
                    },
                    singleLine = true,
                    visualTransformation = if (passwordVisibility) VisualTransformation.None
                    else PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth(0.8f)
                )

                Spacer(modifier = Modifier.padding(10.dp))
                Button(
                    enabled = isButtonEnabled,
                    onClick = {

                        val client = viewModel.getClientByPhone(phoneValue)
                        val director = viewModel.getDirectorByPhone(phoneValue)

                        if (director != null && director.directorPassword == passwordValue) {
                            DIRECTOR_MODE = true
                            Toast.makeText(
                                viewModel.getApplication(),
                                "Вы успешно авторизовались!",
                                Toast.LENGTH_SHORT
                            ).show()
                            DIRECTOR_ID = director.directorId
                            DIRECTOR_NAME = director.directorName
                            DIRECTOR_PHONE = director.directorPhone
                            AGENCY_ID = director.agencyId
                            navController.navigate(Graph.MAIN)
                        } else Toast.makeText(
                            viewModel.getApplication(),
                            "Проверьте ваши данные!",
                            Toast.LENGTH_SHORT
                        ).show()

                        if (client != null && client.clientPassword == passwordValue) {
                            DIRECTOR_MODE = false
                            Toast.makeText(
                                viewModel.getApplication(),
                                "Вы успешно авторизовались!",
                                Toast.LENGTH_SHORT
                            ).show()
                            CLIENT_ID = client.clientId
                            CLIENT_NAME = client.clientName
                            CLIENT_PHONE = client.clientPhoneNumber
                            navController.navigate(Graph.MAIN)
                        } else Toast.makeText(
                            viewModel.getApplication(),
                            "Проверьте ваши данные!",
                            Toast.LENGTH_SHORT
                        ).show()
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Blues)
                ) {
                    Text(
                        color = colorResource(id = R.color.white),
                        text = stringResource(id = R.string.signIn), fontSize = 20.sp,
                        fontFamily = FontFamily(
                            Font(R.font.gteestiprotext_regular),
                        )
                    )
                }
                Spacer(modifier = Modifier.padding(4.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .wrapContentHeight(), contentAlignment = Alignment.CenterEnd
                ) {
                    Text(
                        text = stringResource(id = R.string.forgotPassword),
                        fontFamily = FontFamily(
                            Font(R.font.gteestiprotext_regular)
                        ),
                        modifier = Modifier.clickable(onClick = {
                        }), color = colorResource(id = R.color.blue), fontSize = 14.sp
                    )
                }

                Spacer(modifier = Modifier.padding(15.dp))

                Text(
                    text = stringResource(id = R.string.createAccount),
                    fontFamily = FontFamily(
                        Font(R.font.gteestiprotext_regular)
                    ),
                    color = Color.Black,
                    modifier = Modifier.clickable(onClick = {
                        onSignUpClick()
                    })
                )
                Spacer(modifier = Modifier.padding(20.dp))

                Text(
                    text = stringResource(id = R.string.orOpenAgency),
                    fontFamily = FontFamily(
                        Font(R.font.gteestiprotext_regular)
                    ),
                    color = Color.Gray,
                    modifier = Modifier.clickable(onClick = {
                        onOpenAgencyClick()
                    })
                )
                Spacer(modifier = Modifier.padding(20.dp))
            }
        }
    }
}
