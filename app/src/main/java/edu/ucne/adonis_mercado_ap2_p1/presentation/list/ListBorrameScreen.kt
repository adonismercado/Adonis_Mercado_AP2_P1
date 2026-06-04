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
import androidx.compose.ui.draw.blur
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import edu.ucne.adonis_mercado_ap2_p1.domain.model.Borrame

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListBorrameScreen (
    viewModel: ListBorrameViewModel = hiltViewModel(),
    onAddBorrame: () -> Unit,
    onNavigateToEdit: (Int) -> Unit,
    onNew: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(state.navigateToCrear) {
        if (state.navigateToCrear) {
            onNew()
        }
    }

    ListBorrameBody(state = state, onAddBorrame, onNew)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListBorrameBody(
    state: ListBorrameUiState,
    onAddBorrame: () -> Unit,
    onNew: () -> Unit,
) {
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost =  { SnackbarHost(snackbarHostState) },
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Lista de Borrames") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onNew
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Crear borrame"
                )
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .padding(8.dp)
        ) {
            if (state.borrames.isEmpty()) {
                Text(
                    text = "No hay borrames.",
                    modifier = Modifier.align(Alignment.Center),
                    style = MaterialTheme.typography.bodyLarge
                )
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(
                        items = state.borrames,
                        key = { it.borrameId }
                    ) { borrame ->
                        ListBorrameItem(
                            borrame = borrame,
                            onClick = {}
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListBorrameItem(
    borrame: Borrame,
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
                    text = borrame.borrameId.toString(),
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
fun ListBorramePreview() {
    ListBorrameScreen(
        onAddBorrame = {},
        onNavigateToEdit = {},
        onNew = {}
    )
}