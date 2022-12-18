package com.example.agencycomposeapp.screens.auth

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
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
import androidx.compose.ui.unit.toSize
import com.example.agencycomposeapp.MainActivity.Companion.CLIENT_ID
import com.example.agencycomposeapp.MainActivity.Companion.CLIENT_NAME
import com.example.agencycomposeapp.MainActivity.Companion.CLIENT_PHONE
import com.example.agencycomposeapp.MainActivity.Companion.DIRECTOR_MODE
import com.example.agencycomposeapp.MainViewModel
import com.example.agencycomposeapp.R
import com.example.agencycomposeapp.room.entities.Client
import com.example.agencycomposeapp.ui.theme.Blues
import com.example.agencycomposeapp.ui.theme.whiteBackground
import java.util.*

@Composable
fun ClientSignUpScreen(
    onClick: () -> Unit,
    viewModel: MainViewModel
) {
    DIRECTOR_MODE = false
    val passwordVector = painterResource(id = R.drawable.password_eye)
    var phoneValue by remember { mutableStateOf("") }
    var nameValue by remember { mutableStateOf("") }
    var passwordValue by remember { mutableStateOf("") }
    var confirmPasswordValue by remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }
    var isButtonEnabled by remember { mutableStateOf(false) }
    var selectedAgencyId by remember { mutableStateOf(UUID.randomUUID()) }

    val image = painterResource(id = R.drawable.user_reg)

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
                text = stringResource(id = R.string.enterDataRegistration),
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
                value = nameValue,
                onValueChange = {
                    nameValue = it
                    isButtonEnabled =
                        nameValue.isNotEmpty() && phoneValue.isNotEmpty() && passwordValue.isNotEmpty() && confirmPasswordValue.isNotEmpty()
                },
                label = {
                    Text(
                        text = stringResource(id = R.string.enterName),
                        fontFamily = FontFamily(
                            Font(R.font.gteestiprotext_regular)
                        )
                    )
                },
                textStyle = TextStyle(
                    color = Color.Black,
                    fontSize = 17.sp,
                    fontFamily = FontFamily(
                        Font(R.font.gteestiprotext_regular)
                    )
                ),
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

            selectedAgencyId = myContent(viewModel)

            Spacer(modifier = Modifier.padding(5.dp))

            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                OutlinedTextField(
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Blues,
                        unfocusedBorderColor = Color.Gray,
                        focusedLabelColor = Blues,
                        cursorColor = Blues,
                        textColor = colorResource(id = R.color.black)
                    ),
                    value = phoneValue,
                    onValueChange = {
                        phoneValue = it
                        isButtonEnabled =
                            nameValue.isNotEmpty()
                                    && phoneValue.isNotEmpty()
                                    && passwordValue.isNotEmpty()
                                    && confirmPasswordValue.isNotEmpty()
                    },
                    textStyle = TextStyle(
                        color = Color.Black,
                        fontSize = 17.sp,
                        fontFamily = FontFamily(
                            Font(R.font.gteestiprotext_regular)
                        )
                    ),
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
                    textStyle = TextStyle(
                        color = Color.Black,
                        fontSize = 17.sp,
                        fontFamily = FontFamily(
                            Font(R.font.gteestiprotext_regular)
                        )
                    ),
                    value = passwordValue,
                    onValueChange = {
                        passwordValue = it
                        isButtonEnabled =
                            nameValue.isNotEmpty()
                                    && phoneValue.isNotEmpty()
                                    && passwordValue.isNotEmpty()
                                    && confirmPasswordValue.isNotEmpty()
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
                    textStyle = TextStyle(
                        color = Color.Black,
                        fontSize = 17.sp,
                        fontFamily = FontFamily(
                            Font(R.font.gteestiprotext_regular)
                        )
                    ),
                    value = confirmPasswordValue,
                    onValueChange = {
                        confirmPasswordValue = it
                        isButtonEnabled =
                            nameValue.isNotEmpty()
                                    && phoneValue.isNotEmpty()
                                    && passwordValue.isNotEmpty()
                                    && confirmPasswordValue.isNotEmpty()
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
                            text = stringResource(id = R.string.confirmPassword),
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
                        if (passwordValue == confirmPasswordValue && client == null) {
                            CLIENT_ID = UUID.randomUUID()
                            viewModel.addClient(
                                Client(
                                    clientId = CLIENT_ID,
                                    clientPhoneNumber = phoneValue,
                                    clientName = nameValue,
                                    clientPassword = passwordValue,
                                    agencyId = selectedAgencyId
                                )
                            ) {}
                            CLIENT_NAME = nameValue
                            CLIENT_PHONE = phoneValue
                            onClick()
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

@Composable
fun myContent(viewModel: MainViewModel): UUID {
    val agencies = viewModel.getAllAgencies().observeAsState(initial = listOf()).value
    var agenciesNames: MutableList<String> = mutableListOf()
    var mExpanded by remember { mutableStateOf(false) }

    var mSelectedText by remember { mutableStateOf("") }

    var mTextFieldSize by remember { mutableStateOf(Size.Zero) }

    for (i in agencies.indices) {
        agenciesNames.add(agencies[i].agencyName)
    }

    val icon = if (mExpanded) Icons.Filled.KeyboardArrowUp
    else Icons.Filled.KeyboardArrowDown

    Column {

        OutlinedTextField(
            enabled = false,
            value = mSelectedText,
            onValueChange = { mSelectedText = it },
            textStyle = TextStyle(
                color = Color.Black,
                fontSize = 17.sp,
                fontFamily = FontFamily(
                    Font(R.font.gteestiprotext_regular)
                )
            ),
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .onGloballyPositioned { coordinates ->
                    mTextFieldSize = coordinates.size.toSize()
                },
            label = {
                Text(
                    text = stringResource(id = R.string.chooseAgency),
                    fontFamily = FontFamily(
                        Font(R.font.gteestiprotext_regular)
                    )
                )
            },
            trailingIcon = {
                Icon(icon, "contentDescription",
                    Modifier.clickable { mExpanded = !mExpanded })
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                disabledTextColor = Color.Black,
                backgroundColor = Color.Transparent,
                disabledBorderColor = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.disabled),
                disabledLabelColor = MaterialTheme.colors.onSurface.copy(ContentAlpha.medium),
            )
        )

        DropdownMenu(
            expanded = mExpanded,
            onDismissRequest = { mExpanded = false },
            modifier = Modifier.width(with(LocalDensity.current) { mTextFieldSize.width.toDp() })
        ) {
            agenciesNames.forEach { label ->
                DropdownMenuItem(onClick = {
                    mSelectedText = label
                    mExpanded = false
                }) {
                    Text(
                        text = label,
                        fontFamily = FontFamily(
                            Font(R.font.gteestiprotext_regular)
                        )
                    )
                }
            }
        }
    }
    var selectedAgencyId: UUID = UUID.randomUUID()
    for (i in agencies.indices) {
        if (mSelectedText == agencies[i].agencyName)
            selectedAgencyId = agencies[i].agencyId
    }
    return selectedAgencyId
}
