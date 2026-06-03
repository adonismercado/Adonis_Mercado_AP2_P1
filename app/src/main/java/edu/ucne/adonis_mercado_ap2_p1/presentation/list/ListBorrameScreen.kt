package edu.ucne.adonis_mercado_ap2_p1.presentation.list

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListBorrameScreen (
    viewModel: ListBorrameViewModel = hiltViewModel(),
    onAddBorrame: () -> Unit,
    onNavigateToEdit: (Int) -> Unit
) { }

@Preview(showBackground = true)
@Composable
fun ListBorramePreview() {
    ListBorrameScreen(
        onAddBorrame = {},
        onNavigateToEdit = {}
    )
}