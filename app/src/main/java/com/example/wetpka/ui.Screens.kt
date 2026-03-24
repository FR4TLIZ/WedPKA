package com.example.wetpka.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wetpka.data.MockData
import com.example.wetpka.model.Fish
import com.example.wetpka.model.WaterBody

// To są nazwy naszych filtrów (pigułek u góry)
val filterOptions = listOf("Wszystkie", "Drapieżne", "Spokojnego żeru")

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun AtlasScreen(onFishClick: (Int) -> Unit = {}) {
    // Stan: Przechowuje informację, która pigułka filtru jest aktualnie zaznaczona
    var selectedFilter by remember { mutableStateOf("Wszystkie") }

    // Stan: Przechowuje tekst wpisany w wyszukiwarkę (na razie nieaktywny, jak na makiecie)
    var searchQuery by remember { mutableStateOf("") }

    // Logika filtrowania: Ta zmienna przechowuje tylko ryby pasujące do wybranej pigułki
    val filteredFishes = remember(selectedFilter) {
        if (selectedFilter == "Wszystkie") {
            MockData.fishes
        } else {
            MockData.fishes.filter { it.category == selectedFilter }
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            // Pasek górny z napisem "Atlas Ryb"
            TopAppBar(title = { Text("Atlas Ryb", fontWeight = FontWeight.Bold) })
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {

            // 1. Wyszukiwarka (SearchBar) - Placeholder jak na makiecie
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                placeholder = { Text("Wyszukaj gatunek ryby...") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Ikona wyszukiwania") },
                shape = MaterialTheme.shapes.extraLarge, // Zaokrąglone rogi jak na makiecie
                singleLine = true
            )

            // 2. Filtrowanie (Pigułki/Chipsy) - Wiersz z opcjami filtrowania
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                filterOptions.forEach { filter ->
                    FilterChip(
                        selected = selectedFilter == filter,
                        onClick = { selectedFilter = filter },
                        label = { Text(text = filter) }
                    )
                }
            }

            // 3. Siatka (Grid) z rybami - Wypełnia resztę ekranu
            LazyVerticalGrid(
                columns = GridCells.Fixed(2), // 2 kolumny jak na makiecie
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Wyświetlamy tylko pofiltrowane ryby
                items(filteredFishes) { fish ->
                    FishGridItem(fish = fish, onClick = { onFishClick(fish.id) }) // To jest nasz pojedynczy kafelek ryby
                }
            }
        }
    }
}

// Składnik UI dla pojedynczego kafelka w siatce (Zdjęcie + Nazwa + Nazwa łacińska)
@Composable
fun FishGridItem(fish: Fish, onClick: () -> Unit = {}) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f) // Kafelki będą kwadratowe
            .clickable(onClick = onClick),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {

            // Zdjęcie Ryby
            Image(
                painter = painterResource(id = fish.imageResId),
                contentDescription = "Zdjęcie: ${fish.name}",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp) // Zdjęcie zajmuje górną część kafelka
                    .padding(4.dp), // Delikatny margines wewnątrz karty
                contentScale = ContentScale.Crop // Przytnij zdjęcie, żeby ładnie wypełniało przestrzeń
            )

            // Tekst na dole kafelka
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 8.dp, vertical = 4.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally // Centrujemy napisy jak na makiecie
            ) {
                // Nazwa Polska
                Text(
                    text = fish.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    maxLines = 1 // Jeśli nazwa jest za długa, utnij ją, nie przenoś
                )

                // Nazwa Łacińska
                Text(
                    text = fish.latinName,
                    fontWeight = FontWeight.Light,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.secondary, // Używamy koloru pomocniczego dla łaciny
                    maxLines = 1 // Jeśli nazwa jest za długa, utnij ją, nie przenoś
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FishDetailScreen(fishId: Int, onBackClick: () -> Unit) {
    val fish = MockData.fishes.find { it.id == fishId } ?: return

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Szczegóły: ${fish.name}",
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Wróć"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {

            // Zdjęcie ryby na pełną szerokość
            Image(
                painter = painterResource(id = fish.imageResId),
                contentDescription = "Zdjęcie: ${fish.name}",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Karta z nazwami
            ElevatedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                elevation = CardDefaults.elevatedCardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = fish.name,
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp
                    )
                    Text(
                        text = fish.latinName,
                        fontWeight = FontWeight.Light,
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.secondary
                    )
                    Text(
                        text = fish.englishName,
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.outline
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Karta "Regulacje Wędkarskie"
            ElevatedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                elevation = CardDefaults.elevatedCardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Regulacje Wędkarskie",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(bottom = 12.dp)
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        RegulationItem(
                            modifier = Modifier.weight(1f),
                            title = "Wymiar Ochronny",
                            value = fish.protectionSize
                        )
                        RegulationItem(
                            modifier = Modifier.weight(1f),
                            title = "Limit Dobowy",
                            value = fish.dailyLimit
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        RegulationItem(
                            modifier = Modifier.weight(1f),
                            title = "Okres Ochronny",
                            value = fish.protectionPeriod
                        )
                        RegulationItem(
                            modifier = Modifier.weight(1f),
                            title = "Czas Tarła",
                            value = fish.spawningTime
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Karta "Informacje i Siedlisko"
            ElevatedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                elevation = CardDefaults.elevatedCardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Informacje i Siedlisko",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(bottom = 12.dp)
                    )
                    InfoItem(label = "Regiony Występowania", value = fish.regions)
                    Spacer(modifier = Modifier.height(8.dp))
                    InfoItem(label = "Preferowane Akweny", value = fish.habitat)
                    Spacer(modifier = Modifier.height(8.dp))
                    InfoItem(label = "Dobre Brania", value = fish.goodBites)
                    Spacer(modifier = Modifier.height(8.dp))
                    InfoItem(label = "Słabe Brania", value = fish.badBites)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun RegulationItem(modifier: Modifier = Modifier, title: String, value: String) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                fontSize = 11.sp,
                color = MaterialTheme.colorScheme.secondary,
                fontWeight = FontWeight.Medium,
                maxLines = 2
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = value,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun InfoItem(label: String, value: String) {
    Column {
        Text(
            text = label,
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.secondary,
            fontWeight = FontWeight.Medium
        )
        Text(
            text = value,
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapScreen() {
    val context = androidx.compose.ui.platform.LocalContext.current
    var userLocation by remember { mutableStateOf<android.location.Location?>(null) }
    var locationPermissionGranted by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf("") }

    val filteredWaterBodies = remember(searchQuery) {
        if (searchQuery.isBlank()) {
            com.example.wetpka.data.MockData.waterBodies
        } else {
            com.example.wetpka.data.MockData.waterBodies.filter {
                it.name.contains(searchQuery, ignoreCase = true) ||
                it.region.contains(searchQuery, ignoreCase = true) ||
                it.district.contains(searchQuery, ignoreCase = true)
            }
        }
    }

    // Pomocnicza funkcja do pobierania lokalizacji z fallbackiem
    fun fetchLocation() {
        val fusedLocationClient = com.google.android.gms.location.LocationServices.getFusedLocationProviderClient(context)
        try {
            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                if (location != null) {
                    userLocation = location
                } else {
                    // lastLocation może być null – żądamy aktualnej lokalizacji
                    val cts = com.google.android.gms.tasks.CancellationTokenSource()
                    fusedLocationClient.getCurrentLocation(
                        com.google.android.gms.location.Priority.PRIORITY_BALANCED_POWER_ACCURACY,
                        cts.token
                    ).addOnSuccessListener { freshLocation ->
                        userLocation = freshLocation
                    }
                }
            }
        } catch (e: SecurityException) { e.printStackTrace() }
    }

    // Rejestrator pozwolenia na lokalizację
    val locationPermissionLauncher = androidx.activity.compose.rememberLauncherForActivityResult(
        contract = androidx.activity.result.contract.ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        locationPermissionGranted = isGranted
        if (isGranted) {
            fetchLocation()
        }
    }

    // Prosimy o uprawnienia przy starcie ekranu
    LaunchedEffect(Unit) {
        val permission = android.Manifest.permission.ACCESS_FINE_LOCATION
        val isGranted = androidx.core.content.ContextCompat.checkSelfPermission(
            context, permission
        ) == android.content.pm.PackageManager.PERMISSION_GRANTED
        if (isGranted) {
            locationPermissionGranted = true
            fetchLocation()
        } else {
            locationPermissionLauncher.launch(permission)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Zbiorniki PZW", fontWeight = FontWeight.Bold) }
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding).fillMaxSize()) {

            // Pasek wyszukiwania
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                placeholder = { Text("Szukaj zbiornika...") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Szukaj") },
                shape = MaterialTheme.shapes.extraLarge,
                singleLine = true
            )

            // Lista łowisk
            androidx.compose.foundation.lazy.LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(filteredWaterBodies) { waterBody ->
                    WaterBodyCard(waterBody, userLocation, locationPermissionGranted)
                }
            }
        }
    }
}

@Composable
fun WaterBodyCard(waterBody: com.example.wetpka.model.WaterBody, userLocation: android.location.Location?, permissionGranted: Boolean) {
    // Liczenie odległości
    var distanceText = "Lokalizacja niedostępna"
    if (permissionGranted && userLocation != null) {
        val targetLocation = android.location.Location("").apply {
            latitude = waterBody.latitude
            longitude = waterBody.longitude
        }
        val distanceInMeters = userLocation.distanceTo(targetLocation)
        val distanceInKm = distanceInMeters / 1000
        distanceText = String.format(java.util.Locale.getDefault(), "%.1f km od Ciebie", distanceInKm)
    }

    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 2.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth().padding(8.dp), verticalAlignment = Alignment.CenterVertically) {

            // Obrazek (Miniatura)
            Image(
                painter = painterResource(id = waterBody.imageResId),
                contentDescription = waterBody.name,
                modifier = Modifier.size(80.dp).padding(4.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(8.dp))

            // Teksty
            Column(modifier = Modifier.weight(1f)) {
                Text(text = waterBody.name, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(text = waterBody.region, fontSize = 14.sp)
                Text(text = waterBody.sizeInfo, fontSize = 12.sp, color = MaterialTheme.colorScheme.secondary)
                Text(text = waterBody.district, fontSize = 12.sp, color = MaterialTheme.colorScheme.secondary)
            }

            // Odległość na samym dole po prawej
            Column(horizontalAlignment = Alignment.End) {
                Spacer(modifier = Modifier.height(40.dp))
                Text(text = distanceText, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
            }
        }
    }
}

@Composable
fun LogbookScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Tu będzie Rejestr Połowów")
    }
}

@Composable
fun ProfileScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Tu będzie Legitymacja")
    }
}
