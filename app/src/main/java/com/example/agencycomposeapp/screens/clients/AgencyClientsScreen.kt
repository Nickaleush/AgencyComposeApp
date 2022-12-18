package com.example.agencycomposeapp.screens.clients

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.agencycomposeapp.MainActivity.Companion.AGENCY_ID
import com.example.agencycomposeapp.MainActivity.Companion.CLIENT_TO_DELETE_ID
import com.example.agencycomposeapp.MainActivity.Companion.DIRECTOR_ID
import com.example.agencycomposeapp.MainViewModel
import com.example.agencycomposeapp.R
import com.example.agencycomposeapp.room.entities.Client
import com.example.agencycomposeapp.ui.theme.Blues
import com.example.agencycomposeapp.utils.CustomDialog

@SuppressLint("MutableCollectionMutableState")
@Composable
fun AgencyClientsScreen(viewModel: MainViewModel) {

    var dClients = viewModel.getClientsOfDirectorsAgency(DIRECTOR_ID)

    var clients  by remember { mutableStateOf(mutableListOf<Client>()) }

    val agency = viewModel.getAgencyById(AGENCY_ID)

    var deleteClient = remember { mutableStateOf(false) }
    var deleteFlat = remember { mutableStateOf(false) }

    var needToUpdateData = remember { mutableStateOf(false) }

    val openDialogCustom = remember { mutableStateOf(false) }

    LaunchedEffect(key1 = needToUpdateData.value) {

        if (needToUpdateData.value) {
            dClients = viewModel.getClientsOfDirectorsAgency(DIRECTOR_ID)
            for (i in dClients.indices) {
               clients.add(dClients[i])
            }
        }

    }
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Blues,
                elevation = 10.dp,
                content = {
                    Text(
                        text = stringResource(
                            id = R.string.agencyName,
                            agency?.agencyName.toString()
                        ),
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
    ) { paddingValues ->

        if (dClients.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(id = R.string.youDontHaveClients), fontSize = 20.sp,
                    fontFamily = FontFamily(
                        Font(R.font.gteestiprotext_regular)
                    ),
                    textAlign = TextAlign.Center,
                    color = Color.Black
                )
            }
        }

        LazyColumn(
            modifier = Modifier.padding(paddingValues = paddingValues)
        ) {
            items(dClients) { client ->
                ClientItem(
                    client = client,
                    openDialogCustom = openDialogCustom,
                    viewModel = viewModel,
                    deleteClient = deleteClient,
                    deleteFlat = deleteFlat
                )
            }
        }
    }
}

@Composable
fun ClientItem(
    client: Client,
    openDialogCustom: MutableState<Boolean>,
    viewModel: MainViewModel,
    deleteClient: MutableState<Boolean>,
    deleteFlat: MutableState<Boolean>,
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 24.dp)
            .clickable {

            },
        elevation = 6.dp
    ) {
        Row {
            Icon(
                modifier = Modifier.size(60.dp),
                imageVector = ImageVector.vectorResource(id = R.drawable.person_list_icon_foreground),
                contentDescription = "Some icon",
                tint = colorResource(R.color.blue)
            )

            Box(
                modifier = Modifier.fillMaxWidth(0.9f),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier.padding(vertical = 8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = client.clientName,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily(
                            Font(R.font.gteestiprotext_regular)
                        ),
                    )
                    Spacer(modifier = Modifier.padding(5.dp))

                    Text(
                        text = stringResource(
                            id = R.string.clientPhoneNumber,
                            client.clientPhoneNumber
                        ),
                        fontSize = 14.sp,
                        fontFamily = FontFamily(
                            Font(R.font.gteestiprotext_regular)
                        ),
                    )

                    Spacer(modifier = Modifier.padding(5.dp))
                }
            }

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.CenterEnd
            ){
                Image(
                    painter = painterResource(id = R.drawable.impossible_icon_foreground),
                    contentDescription = "Localized description",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(40.dp)
                        .clickable(
                            enabled = true,
                            onClickLabel = "Clickable image",
                            onClick = {
                                CLIENT_TO_DELETE_ID = client.clientId
                                deleteFlat.value = false
                                deleteClient.value = true
                                openDialogCustom.value = true
                            }
                        )
                )
            }
            if (openDialogCustom.value) {
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
