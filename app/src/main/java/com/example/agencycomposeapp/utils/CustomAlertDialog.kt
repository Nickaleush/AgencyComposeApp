package com.example.agencycomposeapp.utils

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.agencycomposeapp.MainActivity.Companion.CLIENT_ID
import com.example.agencycomposeapp.MainActivity.Companion.CLIENT_TO_DELETE_ID
import com.example.agencycomposeapp.MainActivity.Companion.FLAT_ID
import com.example.agencycomposeapp.MainViewModel
import com.example.agencycomposeapp.R
import com.example.agencycomposeapp.ui.theme.Blues

@Composable
fun CustomDialog(
    openDialogCustom: MutableState<Boolean>,
    viewModel: MainViewModel,
    buyFlat: MutableState<Boolean>,
    deleteClient: MutableState<Boolean>,
    deleteFlat: MutableState<Boolean>
) {
    Dialog(onDismissRequest = { openDialogCustom.value = false }) {
        CustomDialogUI(
            openDialogCustom = openDialogCustom,
            viewModel = viewModel,
            deleteClient = deleteClient,
            deleteFlat = deleteFlat,
            buyFlat = buyFlat
        )
    }
}


@Composable
fun CustomDialogUI(
    modifier: Modifier = Modifier,
    openDialogCustom: MutableState<Boolean>,
    viewModel: MainViewModel,
    buyFlat: MutableState<Boolean>,
    deleteClient: MutableState<Boolean>,
    deleteFlat: MutableState<Boolean>
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier.padding(10.dp, 5.dp, 10.dp, 10.dp),
        elevation = 8.dp
    ) {
        Column(
            modifier
                .background(Color.White)
        ) {
            if (buyFlat.value) {
                Image(
                    painter = painterResource(id = R.drawable.sell_icon_foreground),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    colorFilter = ColorFilter.tint(
                        color = Blues
                    ),
                    modifier = Modifier
                    .padding(top = 15.dp)
                        .height(150.dp)
                        .fillMaxWidth(),
                )
            } else {
                Image(
                    painter = painterResource(id = R.drawable.impossible_icon_foreground),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    colorFilter = ColorFilter.tint(
                        color = Blues
                    ),
                    modifier = Modifier
//                    .padding(top = 15.dp)
                        .height(150.dp)
                        .fillMaxWidth(),
                )
            }

            Column(modifier = Modifier.padding(horizontal = 16.dp)) {

                if (buyFlat.value) {
                    Text(
                        text = stringResource(id = R.string.confirmDelete),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth(),
                        maxLines = 2,
                        fontFamily = FontFamily(
                            Font(R.font.gteestiprotext_regular)
                        ),
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = stringResource(id = R.string.buyResponsible),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(top = 10.dp, start = 25.dp, end = 25.dp)
                            .fillMaxWidth(),
                        fontFamily = FontFamily(
                            Font(R.font.gteestiprotext_regular)
                        )
                    )
                } else {
                    Text(
                        text = stringResource(id = R.string.confirmDelete),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth(),
                        maxLines = 2,
                        fontFamily = FontFamily(
                            Font(R.font.gteestiprotext_regular)
                        ),
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = stringResource(id = R.string.noRecovery),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(top = 10.dp, start = 25.dp, end = 25.dp)
                            .fillMaxWidth(),
                        fontFamily = FontFamily(
                            Font(R.font.gteestiprotext_regular)
                        )
                    )
                }
            }
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .background(Blues),
                horizontalArrangement = Arrangement.SpaceAround
            ) {

                TextButton(onClick = {
                    openDialogCustom.value = false
                    buyFlat.value = false
                    deleteClient.value = false
                    deleteFlat.value = false
                }) {
                    Text(
                        text = stringResource(id = R.string.no),
                        fontFamily = FontFamily(
                            Font(R.font.gteestiprotext_regular)
                        ),
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)
                    )
                }

                TextButton(onClick = {
                    if (deleteFlat.value) {
                        viewModel.deleteFlatById(FLAT_ID) {
                            openDialogCustom.value = false
                            Toast.makeText(viewModel.getApplication(), "Вы удалили квартиру :(", Toast.LENGTH_SHORT).show()
                        }
                    }

                    if (buyFlat.value) {
                        viewModel.updateFlatCrossRef(FLAT_ID, CLIENT_ID) {
                            openDialogCustom.value = false
                            Toast.makeText(viewModel.getApplication(), "Вы успешно приобрели квартиру!", Toast.LENGTH_SHORT).show()
                        }
                    }

                    if (deleteClient.value) {
                        val clientWithFlats = viewModel.getFlatsOfClient(CLIENT_TO_DELETE_ID)
                        val flats = clientWithFlats.first().flats
                        for (i in flats.indices) {
                            viewModel.deleteFlatById(flats[i].flatId) {
                            }
                        }
                        viewModel.deleteClientById(CLIENT_TO_DELETE_ID) {
                            openDialogCustom.value = false
                            Toast.makeText(viewModel.getApplication(), "Вы удалили клиента :(", Toast.LENGTH_SHORT).show()
                        }
                    }
                }) {
                    Text(
                        stringResource(id = R.string.yes),
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.White,
                        fontFamily = FontFamily(
                            Font(R.font.gteestiprotext_regular)
                        ),
                        modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)
                    )
                }
            }
        }
    }
}