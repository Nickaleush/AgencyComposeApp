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
import com.example.agencycomposeapp.MainActivity.Companion.AGENCY_ID
import com.example.agencycomposeapp.MainActivity.Companion.DIRECTOR_ID
import com.example.agencycomposeapp.MainActivity.Companion.DIRECTOR_MODE
import com.example.agencycomposeapp.MainActivity.Companion.DIRECTOR_NAME
import com.example.agencycomposeapp.MainActivity.Companion.DIRECTOR_PHONE
import com.example.agencycomposeapp.MainViewModel
import com.example.agencycomposeapp.R
import com.example.agencycomposeapp.room.entities.Agency
import com.example.agencycomposeapp.room.entities.Director
import com.example.agencycomposeapp.ui.theme.Blues
import com.example.agencycomposeapp.ui.theme.whiteBackground
import java.util.*

@Composable
fun DirectorSignUpScreen(
    viewModel: MainViewModel,
    onClick: () -> Unit
) {
    val passwordVector = painterResource(id = R.drawable.password_eye)
    var phoneValue by remember { mutableStateOf("") }
    var directorNameValue by remember { mutableStateOf("") }
    var directorPasswordValue by remember { mutableStateOf("") }
    var confirmPasswordValue by remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }
    var isButtonEnabled by remember { mutableStateOf(false) }
    var agencyNameValue by remember { mutableStateOf("") }

    val image = painterResource(id = R.drawable.open_agency)

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
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.75f)
                .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
                .background(whiteBackground)
                .padding(10.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = stringResource(id = R.string.оpenAgency),
                fontFamily = FontFamily(
                    Font(R.font.gteestiprotext_regular)
                ),
                style = TextStyle(fontWeight = FontWeight.Medium, letterSpacing = 2.sp),
                fontSize = 25.sp,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.padding(5.dp))

            OutlinedTextField(
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Blues,
                    unfocusedBorderColor = Color.Gray,
                    focusedLabelColor = Blues,
                    cursorColor = Blues
                ),
                value = directorNameValue,
                onValueChange = {
                    directorNameValue = it
                    isButtonEnabled =
                        directorNameValue.isNotEmpty() && phoneValue.isNotEmpty() && agencyNameValue.isNotEmpty() && directorPasswordValue.isNotEmpty() && confirmPasswordValue.isNotEmpty()
                },
                label = {
                    Text(
                        text = stringResource(id = R.string.enterName),
                        fontFamily = FontFamily(
                            Font(R.font.gteestiprotext_regular)
                        )
                    )
                },
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.enterName),
                        fontFamily = FontFamily(
                            Font(R.font.gteestiprotext_regular)
                        )
                    )
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(0.8f),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )

            Spacer(modifier = Modifier.padding(5.dp))

            OutlinedTextField(
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Blues,
                    unfocusedBorderColor = Color.Gray,
                    focusedLabelColor = Blues,
                    cursorColor = Blues
                ),
                value = agencyNameValue,
                onValueChange = {
                    agencyNameValue = it
                    isButtonEnabled =
                        directorNameValue.isNotEmpty() && phoneValue.isNotEmpty() && agencyNameValue.isNotEmpty() && directorPasswordValue.isNotEmpty() && confirmPasswordValue.isNotEmpty()
                },
                label = {
                    Text(
                        text = stringResource(id = R.string.nameYourAgency),
                        fontFamily = FontFamily(
                            Font(R.font.gteestiprotext_regular)
                        )
                    )
                },
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.enterAgencyName),
                        fontFamily = FontFamily(
                            Font(R.font.gteestiprotext_regular)
                        )
                    )
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(0.8f),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )

            Spacer(modifier = Modifier.padding(5.dp))

            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                OutlinedTextField(
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Blues,
                        unfocusedBorderColor = Color.Gray,
                        focusedLabelColor = Blues,
                        cursorColor = Blues
                    ),
                    value = phoneValue,
                    onValueChange = {
                        phoneValue = it
                        isButtonEnabled =
                            directorNameValue.isNotEmpty() && phoneValue.isNotEmpty() && agencyNameValue.isNotEmpty() && directorPasswordValue.isNotEmpty() && confirmPasswordValue.isNotEmpty()
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

                Spacer(modifier = Modifier.padding(5.dp))

                OutlinedTextField(
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Blues,
                        unfocusedBorderColor = Color.Gray,
                        focusedLabelColor = Blues,
                        cursorColor = Blues,
                        textColor = colorResource(id = R.color.black)
                    ),
                    value = directorPasswordValue,
                    onValueChange = {
                        directorPasswordValue = it
                        isButtonEnabled =
                            directorNameValue.isNotEmpty() && phoneValue.isNotEmpty() && agencyNameValue.isNotEmpty() && directorPasswordValue.isNotEmpty() && confirmPasswordValue.isNotEmpty()
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

                Spacer(modifier = Modifier.padding(5.dp))

                OutlinedTextField(
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Blues,
                        unfocusedBorderColor = Color.Gray,
                        focusedLabelColor = Blues,
                        cursorColor = Blues,
                        textColor = colorResource(id = R.color.black)
                    ),
                    value = confirmPasswordValue,
                    onValueChange = {
                        confirmPasswordValue = it
                        isButtonEnabled =
                            directorNameValue.isNotEmpty() && phoneValue.isNotEmpty() && agencyNameValue.isNotEmpty() && directorPasswordValue.isNotEmpty() && confirmPasswordValue.isNotEmpty()
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
                            text = stringResource(id = R.string.confirmPassword),
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
                        val director = viewModel.getDirectorByPhone(phoneValue)
                        if (directorPasswordValue == confirmPasswordValue && director == null) {
                            DIRECTOR_MODE = true
                            DIRECTOR_ID = UUID.randomUUID()
                            AGENCY_ID = UUID.randomUUID()
                            viewModel.addDirectorAndAgency(
                                director = Director(
                                    directorId = DIRECTOR_ID,
                                    directorName = directorNameValue,
                                    directorPhone = phoneValue,
                                    directorPassword = directorPasswordValue,
                                    agencyId = AGENCY_ID
                                ),
                                agency = Agency(
                                    agencyId = AGENCY_ID,
                                    agencyName = agencyNameValue
                                )
                            )
                            DIRECTOR_NAME = directorNameValue
                            DIRECTOR_PHONE = phoneValue
                            Toast.makeText(viewModel.getApplication(), "Вы успешно создали агентство!", Toast.LENGTH_SHORT).show()
                            onClick()
                        } else Toast.makeText(viewModel.getApplication(), "Проверьте ваши данные!", Toast.LENGTH_SHORT).show()
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Blues)
                ) {
                    Text(
                        color = colorResource(id = R.color.white),
                        text = stringResource(id = R.string.createAccount), fontSize = 20.sp,
                        fontFamily = FontFamily(
                            Font(R.font.gteestiprotext_regular),
                        )
                    )
                }
                Spacer(modifier = Modifier.padding(20.dp))
            }
        }
    }
}

