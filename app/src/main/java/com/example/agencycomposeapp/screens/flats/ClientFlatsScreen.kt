package com.example.agencycomposeapp.screens.flats

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.agencycomposeapp.MainActivity.Companion.CLIENT_ID
import com.example.agencycomposeapp.MainActivity.Companion.FLAT_ID
import com.example.agencycomposeapp.MainActivity.Companion.FLAT_INDEX
import com.example.agencycomposeapp.MainViewModel
import com.example.agencycomposeapp.R
import com.example.agencycomposeapp.room.entities.Flat
import com.example.agencycomposeapp.ui.theme.Blues
import com.example.agencycomposeapp.utils.CustomDialog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
@OptIn(ExperimentalMaterialApi::class)
fun ClientFlatsScreen(viewModel: MainViewModel) {

    var clientsWithFlats = viewModel.getFlatsOfClient(CLIENT_ID)

    var flats by remember { mutableStateOf(listOf<Flat>()) }

    var deleteClient = remember { mutableStateOf(false) }

    var deleteFlat = remember { mutableStateOf(false) }

    for (i in clientsWithFlats.indices) {
        if (clientsWithFlats[i].client.clientId == CLIENT_ID) {
            flats = clientsWithFlats[i].flats
        }
    }

    val bottomSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val coroutineScope = rememberCoroutineScope()
    var flatAddress by remember { mutableStateOf("") }
    var flatRoomCount by remember { mutableStateOf("") }
    var flatCost by remember { mutableStateOf("") }
    var flatSpace by remember { mutableStateOf("") }

    var needToUpdateData = remember { mutableStateOf(false) }

    val openDialogCustom = remember { mutableStateOf(false) }

    LaunchedEffect(key1 = needToUpdateData.value) {

        if (needToUpdateData.value) {

            for (i in clientsWithFlats.indices) {
                if (clientsWithFlats[i].client.clientId == CLIENT_ID) {
                    flats = clientsWithFlats[i].flats
                }
            }

            if (flats.isNotEmpty()) {
                flatAddress = flats[FLAT_INDEX].flatAddress

                flatRoomCount = flats[FLAT_INDEX].flatRoomCount.toString()

                flatCost = flats[FLAT_INDEX].flatCost.toString()

                flatSpace = flats[FLAT_INDEX].flatSpace.toString()
            }

            needToUpdateData.value = false
        }
    }

    ModalBottomSheetLayout(
        sheetState = bottomSheetState,
        sheetElevation = 5.dp,
        sheetShape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
        sheetContent = {
            Surface {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 20.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.editFlat),
                        fontSize = 18.sp,
                        fontFamily = FontFamily(
                            Font(R.font.gteestiprotext_regular)
                        ),
                        color = colorResource(id = R.color.black),
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.padding(2.dp))

                    OutlinedTextField(
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Blues,
                            unfocusedBorderColor = Color.Gray,
                            focusedLabelColor = Blues,
                            cursorColor = Blues
                        ),
                        value = flatAddress,
                        onValueChange = {
                            flatAddress = it
                        },
                        enabled = true,
                        label = {
                            Text(
                                text = stringResource(id = R.string.flatAddress),
                                fontFamily = FontFamily(
                                    Font(R.font.gteestiprotext_regular)
                                )
                            )
                        },
                        placeholder = {
                            Text(
                                text = stringResource(id = R.string.enterFlatAddress),
                                fontFamily = FontFamily(
                                    Font(R.font.gteestiprotext_regular)
                                )
                            )
                        },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                    )

                    Spacer(modifier = Modifier.padding(2.dp))

                    OutlinedTextField(
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Blues,
                            unfocusedBorderColor = Color.Gray,
                            focusedLabelColor = Blues,
                            cursorColor = Blues
                        ),
                        value = flatSpace,
                        onValueChange = {
                            flatSpace = it
                        },
                        label = {
                            Text(
                                text = stringResource(id = R.string.flatSpace),
                                fontFamily = FontFamily(
                                    Font(R.font.gteestiprotext_regular)
                                )
                            )
                        },
                        placeholder = {
                            Text(
                                text = stringResource(id = R.string.enterFlatSpace),
                                fontFamily = FontFamily(
                                    Font(R.font.gteestiprotext_regular)
                                )
                            )
                        },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
                    )

                    Spacer(modifier = Modifier.padding(2.dp))

                    OutlinedTextField(
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Blues,
                            unfocusedBorderColor = Color.Gray,
                            focusedLabelColor = Blues,
                            cursorColor = Blues
                        ),
                        value = flatRoomCount,
                        onValueChange = {
                            flatRoomCount = it
                        },
                        label = {
                            Text(
                                text = stringResource(id = R.string.roomCount),
                                fontFamily = FontFamily(
                                    Font(R.font.gteestiprotext_regular)
                                )
                            )
                        },
                        placeholder = {
                            Text(
                                text = stringResource(id = R.string.enterRoomCount),
                                fontFamily = FontFamily(
                                    Font(R.font.gteestiprotext_regular)
                                )
                            )
                        },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
                    )

                    Spacer(modifier = Modifier.padding(2.dp))

                    OutlinedTextField(
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Blues,
                            unfocusedBorderColor = Color.Gray,
                            focusedLabelColor = Blues,
                            cursorColor = Blues
                        ),
                        value = flatCost,
                        onValueChange = {
                            flatCost = it
                        },
                        label = {
                            Text(
                                text = stringResource(id = R.string.flatCost),
                                fontFamily = FontFamily(
                                    Font(R.font.gteestiprotext_regular)
                                )
                            )
                        },
                        placeholder = {
                            Text(
                                text = stringResource(id = R.string.enterFlatCost),
                                fontFamily = FontFamily(
                                    Font(R.font.gteestiprotext_regular)
                                )
                            )
                        },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
                    )

                    Spacer(modifier = Modifier.padding(5.dp))

                    Button(
                        modifier = Modifier
                            .padding(bottom = 60.dp)
                            .align(alignment = CenterHorizontally),
                        onClick = {
                            viewModel.updateFlat(
                                flat = Flat(
                                    flatId = flats[FLAT_INDEX].flatId,
                                    flatAddress = flatAddress,
                                    flatSpace = flatSpace.toLong(),
                                    flatRoomCount = flatRoomCount.toLong(),
                                    flatCost = flatCost.toLong(),
                                )
                            ) {
                                coroutineScope.launch {
                                    clientsWithFlats = viewModel.getFlatsOfClient(CLIENT_ID)
                                    for (i in clientsWithFlats.indices) {
                                        if (clientsWithFlats[i].client.clientId == CLIENT_ID) {
                                            flats = clientsWithFlats[i].flats
                                        }
                                    }
                                    bottomSheetState.hide()
                                }
                            }
                        },
                        enabled = flatAddress.isNotEmpty() && flatRoomCount.isNotEmpty() && flatCost.isNotEmpty()
                    ) {
                        Text(
                            text = stringResource(id = R.string.edit),
                            fontFamily = FontFamily(
                                Font(R.font.gteestiprotext_regular)
                            ),
                            fontSize = 20.sp
                        )
                    }
                }
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    backgroundColor = Blues,
                    elevation = 10.dp,
                    content = {
                        Text(
                            text = stringResource(id = R.string.yourFlats),
                            modifier = Modifier.padding(horizontal = 20.dp),
                            fontSize = 20.sp,
                            fontFamily = FontFamily(
                                Font(R.font.gteestiprotext_regular)
                            ),
                            color = Color.White
                        )
                    }
                )
            }

        )
        { paddingValues ->
            Text(
                text = stringResource(id = R.string.hereYouCanSeeAndEditYourFlats),
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 20.dp),
                fontSize = 20.sp,
                fontFamily = FontFamily(
                    Font(R.font.gteestiprotext_regular)
                ),
                textAlign = TextAlign.Center,
                color = Color.Black
            )

            if (flats.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Center
                ) {
                    Text(
                        text = stringResource(id = R.string.youDontHaveFlats), fontSize = 20.sp,
                        fontFamily = FontFamily(
                            Font(R.font.gteestiprotext_regular)
                        ),
                        textAlign = TextAlign.Center,
                        color = Color.Black
                    )
                }
            }

            LazyColumn(
                modifier = Modifier
                    .padding(paddingValues = paddingValues)
                    .padding(vertical = 90.dp)
            ) {
                itemsIndexed(flats) { index, flat ->
                    ClientFlatItem(
                        flat = flat,
                        coroutineScope = coroutineScope,
                        bottomSheetState = bottomSheetState,
                        index = index,
                        openDialogCustom = openDialogCustom,
                        viewModel = viewModel,
                        needToUpdateData = needToUpdateData,
                        deleteClient = deleteClient,
                        deleteFlat = deleteFlat
                    )
                }
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterialApi::class)
fun ClientFlatItem(
    flat: Flat,
    coroutineScope: CoroutineScope,
    bottomSheetState: ModalBottomSheetState,
    index: Int,
    openDialogCustom: MutableState<Boolean>,
    needToUpdateData: MutableState<Boolean>,
    deleteClient: MutableState<Boolean>,
    deleteFlat: MutableState<Boolean>,
    viewModel: MainViewModel
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 24.dp)
            .clickable {
                coroutineScope.launch {
                    needToUpdateData.value = true
                    FLAT_INDEX = index
                    bottomSheetState.show()
                }
            },
        elevation = 6.dp
    ) {
        Row {
            Icon(
                modifier = Modifier
                    .size(80.dp)
                    .align(CenterVertically),
                imageVector = ImageVector.vectorResource(id = R.drawable.flats_icon_foreground),
                contentDescription = "Some icon",
                tint = colorResource(R.color.blue)
            )
            Box(
                modifier = Modifier.fillMaxSize(0.9f),
                contentAlignment = Center
            ){
                Column(
                    modifier = Modifier.padding(vertical = 8.dp),
                    horizontalAlignment = CenterHorizontally
                ) {
                    Text(
                        text = flat.flatCost.toString() + " ₽",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily(
                            Font(R.font.gteestiprotext_regular)
                        ),
                    )
                    Spacer(modifier = Modifier.padding(5.dp))

                    Text(
                        text = stringResource(id = R.string.flatAddressCard, flat.flatAddress),
                        fontSize = 14.sp,
                        fontFamily = FontFamily(
                            Font(R.font.gteestiprotext_regular)
                        ),
                    )

                    Spacer(modifier = Modifier.padding(5.dp))

                    Text(
                        text = stringResource(
                            id = R.string.flatRoomsCard,
                            flat.flatRoomCount.toString()
                        ),
                        fontSize = 14.sp,
                        fontFamily = FontFamily(
                            Font(R.font.gteestiprotext_regular)
                        )
                    )
                }
            }

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = CenterEnd
            ) {
                Icon(
                    modifier = Modifier
                        .clickable(
                            enabled = true,
                            onClickLabel = "Clickable image",
                            onClick = {
                                deleteFlat.value = true
                                deleteClient.value = false
                                FLAT_ID = flat.flatId
                                openDialogCustom.value = true
                            }
                        ),
                    imageVector = ImageVector.vectorResource(id = R.drawable.delete_icon_foreground),
                    contentDescription = "Delete icon",
                    tint = Red
                )
            }

            if (openDialogCustom.value) {
                needToUpdateData.value = true
                CustomDialog(
                    openDialogCustom = openDialogCustom,
                    viewModel = viewModel,
                    deleteFlat = deleteFlat,
                    deleteClient = deleteClient,
                    buyFlat = remember { mutableStateOf(false) }
                )
            }
        }
    }
}

