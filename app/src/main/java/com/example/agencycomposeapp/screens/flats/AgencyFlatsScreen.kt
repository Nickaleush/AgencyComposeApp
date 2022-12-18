package com.example.agencycomposeapp.screens.flats

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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.agencycomposeapp.MainActivity
import com.example.agencycomposeapp.MainActivity.Companion.DIRECTOR_ID
import com.example.agencycomposeapp.MainViewModel
import com.example.agencycomposeapp.R
import com.example.agencycomposeapp.room.entities.Flat
import com.example.agencycomposeapp.ui.theme.Blues

@Composable
fun AgencyFlatsScreen(viewModel: MainViewModel) {

    val dFlats = viewModel.getFlatsOfDirectorsAgency(DIRECTOR_ID)

    val agency = viewModel.getAgencyById(MainActivity.AGENCY_ID)

    Scaffold(
        topBar = {
        TopAppBar(
            backgroundColor = Blues,
            elevation = 10.dp,
            content = {
                Text(
                    text = stringResource(id = R.string.agencyName, agency?.agencyName.toString() ),
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
    ) {
        paddingValues ->

        if (dFlats.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
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
            modifier = Modifier.padding(paddingValues = paddingValues)
        ) {
            items(dFlats) { flat ->
                DirectorAgencyFlatItem(flat = flat)
            }
        }
    }
}

@Composable
fun DirectorAgencyFlatItem(
    flat: Flat
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 24.dp)
            .clickable {},
        elevation = 6.dp
    ) {
        Row {
            Icon(
                modifier = Modifier
                    .size(100.dp)
                    .align(Alignment.CenterVertically),
                imageVector = ImageVector.vectorResource(id = R.drawable.flats_icon_foreground),
                contentDescription = "Some icon",
                tint = colorResource(R.color.blue)
            )

            Box(
                modifier = Modifier.fillMaxSize(),
            ){
                Column(
                    modifier = Modifier.padding(vertical = 8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
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
                }
            }
        }
    }
}


