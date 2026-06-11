package edu.ucne.adonis_mercado_ap2_p1.presentation.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import edu.ucne.adonis_mercado_ap2_p1.domain.model.Amonestacion

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListAmonestacionScreen (
    viewModel: ListAmonestacionViewModel = hiltViewModel(),
    onNavigateToEdit: (Int) -> Unit,
    onNew: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(state.navigateToCreate) {
        if (state.navigateToCreate) {
            onNew()
        }
    }

    LaunchedEffect(state.navigateToEditId) {
        state.navigateToEditId?.let { id ->
            onNavigateToEdit(id)
        }
    }

    ListAmonestacionBody(state = state, viewModel::onEvent, onNew)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListAmonestacionBody(
    state: ListAmonestacionUiState,
    onEvent: (ListAmonestacionUiEvent) -> Unit,
    onNew: () -> Unit,
) {
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost =  { SnackbarHost(snackbarHostState) },
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Lista de Amonestaciones") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onNew
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Crear amonestacion"
                )
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(8.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = state.nombresFilter,
                    onValueChange = { onEvent(ListAmonestacionUiEvent.NombreFilterChanged(it)) },
                    label = { Text("Nombres:")},
                    modifier = Modifier.weight(1f),
                    singleLine = true
                )

                OutlinedTextField(
                    value = state.razonFilter,
                    onValueChange = { onEvent(ListAmonestacionUiEvent.RazonFilterChanged(it)) },
                    label = { Text("Razon:") },
                    modifier = Modifier.weight(1f),
                    singleLine = true
                )
            }

            Box(
                modifier = Modifier
                    .padding(8.dp)
            ) {
                if (state.amonestaciones.isEmpty()) {
                    Text(
                        text = "No hay amonestaciones.",
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(12.dp),
                        style = MaterialTheme.typography.bodyLarge
                    )
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxWidth(),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(
                            items = state.amonestaciones,
                            key = { it.amonestacionId }
                        ) { amonestacion ->
                            ListAmonestacionItem(
                                amonestacion = amonestacion,
                                onClick = { onEvent(ListAmonestacionUiEvent.Edit(amonestacion.amonestacionId))}
                            )
                        }
                    }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.End,
            ) {
                ElevatedCard(
                    modifier = Modifier.weight(1f)
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                            .padding(12.dp)
                    ) {
                        Text(
                            text = "${state.amonestaciones.size}",
                            style = MaterialTheme.typography.labelLarge
                        )

                        Text(
                            text = "Amonestaciones",
                            style = MaterialTheme.typography.labelMedium
                        )
                    }
                }

                ElevatedCard(
                    modifier = Modifier.weight(1f)
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                            .padding(12.dp)
                    ) {
                        Text(
                            text = "${state.amonestaciones.sumOf { it.monto.toDoubleOrNull() ?: 0.0 }}",
                            style = MaterialTheme.typography.labelLarge
                        )

                        Text(
                            text = "Total Monto",
                            style = MaterialTheme.typography.labelMedium
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListAmonestacionItem(
    amonestacion: Amonestacion,
    onClick: () -> Unit
) {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Nombres: ${amonestacion.nombres}",
                    style = MaterialTheme.typography.bodyMedium
                )

                Text(
                    text = "Razon: ${amonestacion.razon}",
                    style = MaterialTheme.typography.bodyMedium
                )

                Text(
                    text = "Monto: ${amonestacion.monto}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            IconButton(
                onClick = onClick
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Editar borrame"
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListAmonestacionPreview() {
    ListAmonestacionScreen(
        onNavigateToEdit = {},
        onNew = {}
    )
}