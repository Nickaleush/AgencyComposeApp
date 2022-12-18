package com.example.agencycomposeapp.screens.flats

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.agencycomposeapp.MainActivity.Companion.CLIENT_ID
import com.example.agencycomposeapp.MainActivity.Companion.FLAT_ID
import com.example.agencycomposeapp.MainViewModel
import com.example.agencycomposeapp.R
import com.example.agencycomposeapp.room.entities.Flat
import com.example.agencycomposeapp.room.relations.ClientsFlatCrossRef
import com.example.agencycomposeapp.ui.theme.Blues
import com.example.agencycomposeapp.utils.CustomDialog
import kotlinx.coroutines.launch
import java.util.*

@Composable
@OptIn(ExperimentalMaterialApi::class)
fun AllFlatsScreen(viewModel: MainViewModel) {

    var flats = viewModel.getAllFlats().observeAsState(initial = listOf()).value

    val bottomSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val coroutineScope = rememberCoroutineScope()
    var flatAddress by remember { mutableStateOf("") }
    var flatRoomCount by remember { mutableStateOf("") }
    var flatCost by remember { mutableStateOf("") }
    var flatSpace by remember { mutableStateOf("") }

    var buyFlat = remember { mutableStateOf(false) }

    val openDialogCustom = remember { mutableStateOf(false) }

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
                        text = stringResource(id = R.string.addFlat),
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
                        textStyle = TextStyle(
                            color = Color.Black,
                            fontSize = 17.sp,
                            fontFamily = FontFamily(
                                Font(R.font.gteestiprotext_regular)
                            )
                        ),
                        value = flatAddress,
                        onValueChange = {
                            flatAddress = it
                        },
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
                        textStyle = TextStyle(
                            color = Color.Black,
                            fontSize = 17.sp,
                            fontFamily = FontFamily(
                                Font(R.font.gteestiprotext_regular)
                            )
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
                        textStyle = TextStyle(
                            color = Color.Black,
                            fontSize = 17.sp,
                            fontFamily = FontFamily(
                                Font(R.font.gteestiprotext_regular)
                            )
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
                        textStyle = TextStyle(
                            color = Color.Black,
                            fontSize = 17.sp,
                            fontFamily = FontFamily(
                                Font(R.font.gteestiprotext_regular)
                            )
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
                            val flatID = UUID.randomUUID()
                            viewModel.addFlatAndCrossRef(
                                flat = Flat(
                                    flatId = flatID,
                                    flatAddress = flatAddress,
                                    flatSpace = flatSpace.toLong(),
                                    flatRoomCount = flatRoomCount.toLong(),
                                    flatCost = flatCost.toLong(),
                                ),
                                crossRef = ClientsFlatCrossRef(
                                    clientId = CLIENT_ID,
                                    flatId = flatID
                                )
                            ) {}
                            coroutineScope.launch {
                                bottomSheetState.hide()
                                flatRoomCount = ""
                                flatAddress = ""
                                flatCost = ""
                                flatSpace = ""
                            }
                        },
                        enabled = flatAddress.isNotEmpty() && flatRoomCount.isNotEmpty() && flatCost.isNotEmpty()
                    ) {
                        Text(
                            text = stringResource(id = R.string.add),
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
                            text = stringResource(id = R.string.allAvailableFlats),
                            modifier = Modifier.padding(horizontal = 20.dp),
                            fontSize = 20.sp,
                            fontFamily = FontFamily(
                                Font(R.font.gteestiprotext_regular)
                            ),
                            color = Color.White
                        )
                    }
                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    modifier = Modifier.padding(vertical = 56.dp),
                    backgroundColor = Blues,
                    onClick = {
                        coroutineScope.launch {
                            bottomSheetState.show()
                        }
                    }) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "Add Icons",
                        tint = Color.White,
                    )
                }
            }
        ) { paddingValues ->
            if (flats.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize().padding(paddingValues = paddingValues),
                    contentAlignment = Alignment.Center
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
                modifier = Modifier.padding(bottom = 60.dp)
            ) {
                if (flats.isNotEmpty()) {
                    items(flats) { flat ->
                        FlatItem(
                            viewModel = viewModel,
                            flat = flat,
                            openDialogCustom = openDialogCustom,
                            buyFlat = buyFlat
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun FlatItem(
    viewModel: MainViewModel,
    flat: Flat,
    openDialogCustom: MutableState<Boolean>,
    buyFlat: MutableState<Boolean>
) {
    val clientsOfFlat = viewModel.getClientsOfFlat(flat.flatId)
    val owner = clientsOfFlat.first().clients
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 24.dp)
            .clickable {
                FLAT_ID = flat.flatId
                buyFlat.value = true
                openDialogCustom.value = true
            },
        elevation = 6.dp
    ) {
        Row {
            Icon(
                modifier = Modifier
                    .size(100.dp)
                    .align(CenterVertically),
                imageVector = ImageVector.vectorResource(id = R.drawable.flats_icon_foreground),
                contentDescription = "Some icon",
                tint = colorResource(R.color.blue)
            )

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = CenterStart
            ) {
                Column(
                    modifier = Modifier.padding(vertical = 8.dp),
                ) {
                    Text(
                        text = flat.flatCost.toString() + " ₽",
                        fontSize = 20.sp,
                        modifier = Modifier.padding(horizontal = 30.dp),
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

                    Spacer(modifier = Modifier.padding(5.dp))
                    Text(
                        text = stringResource(
                            id = R.string.flatOwner,
                            owner.first().clientPhoneNumber
                        ),
                        fontSize = 14.sp,
                        fontFamily = FontFamily(
                            Font(R.font.gteestiprotext_regular)
                        )
                    )
                }
            }
        }

        if (openDialogCustom.value) {
            CustomDialog(
                openDialogCustom = openDialogCustom,
                viewModel = viewModel,
                deleteFlat = remember { mutableStateOf(false) },
                deleteClient = remember { mutableStateOf(false) },
                buyFlat = buyFlat
            )
        }
    }
}
