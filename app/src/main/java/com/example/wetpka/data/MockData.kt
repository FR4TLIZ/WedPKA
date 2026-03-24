package com.example.wetpka.data

import com.example.wetpka.R
import com.example.wetpka.model.Fish
import com.example.wetpka.model.WaterBody

object MockData {
    // Tymczasowo używamy domyślnej ikony Androida jako obrazka ryby (R.drawable.ic_launcher_foreground)
    // Kiedy wrzucisz zdjęcia, po prostu zmienisz to np. na R.drawable.szczupak
    val fishes = listOf(
        Fish(
            id = 1,
            name = "Szczupak Pospolity",
            latinName = "Esox lucius",
            englishName = "Northern Pike",
            category = "Drapieżne",
            protectionSize = "50 cm",
            dailyLimit = "2 szt.",
            protectionPeriod = "1 Stycznia - 30 Kwietnia",
            spawningTime = "Wczesna Wiosna (Marzec-Kwiecień)",
            regions = "Cała Polska (powszechny), Ameryka Północna, Europa, Azja",
            habitat = "Czyste, wolno płynące rzeki, jeziora nizinne, zbiorniki zaporowe z roślinnością",
            goodBites = "Wiosna, Jesień, Rano, Wieczorem",
            badBites = "Upalne dni letnie, Południe",
            imageResId = R.drawable.szczupak
        ),
        Fish(
            id = 2,
            name = "Okoń Pospolity",
            latinName = "Perca fluviatilis",
            englishName = "European Perch",
            category = "Drapieżne",
            protectionSize = "15 cm",
            dailyLimit = "5 kg",
            protectionPeriod = "Brak",
            spawningTime = "Wiosna (Kwiecień-Maj)",
            regions = "Cała Polska",
            habitat = "Jeziora, stawy, rzeki, zbiorniki zaporowe",
            goodBites = "Późne lato, Jesień, Wczesny ranek",
            badBites = "Środek upalnego dnia",
            imageResId = R.drawable.okon
        ),
        Fish(
            id = 3,
            name = "Karp",
            latinName = "Cyprinus carpio",
            englishName = "Common Carp",
            category = "Spokojnego żeru",
            protectionSize = "30 cm",
            dailyLimit = "3 szt.",
            protectionPeriod = "Brak",
            spawningTime = "Późna wiosna, Lato (Maj-Czerwiec)",
            regions = "Cała Polska",
            habitat = "Ciepłe, płytkie wody, stawy hodowlane, jeziora z mulistym dnem",
            goodBites = "Ciepłe noce letnie, Wczesny ranek",
            badBites = "Nagłe ochłodzenia, Zima",
            imageResId = R.drawable.karp
        ),
        Fish(
            id = 4,
            name = "Leszcz",
            latinName = "Abramis brama",
            englishName = "Common Bream",
            category = "Spokojnego żeru",
            protectionSize = "25 cm",
            dailyLimit = "5 kg",
            protectionPeriod = "Brak",
            spawningTime = "Wiosna (Maj-Czerwiec)",
            regions = "Cała Polska",
            habitat = "Głębokie jeziora, duże rzeki o wolnym nurcie",
            goodBites = "Letnie noce, Wczesny świt",
            badBites = "Bardzo zimna woda",
            imageResId = R.drawable.leszcz
        ),
        Fish(
            id = 5,
            name = "Sandacz",
            latinName = "Sander lucioperca",
            englishName = "Zander",
            category = "Drapieżne",
            protectionSize = "50 cm",
            dailyLimit = "2 szt.",
            protectionPeriod = "1 Stycznia - 31 Maja",
            spawningTime = "Wiosna (Kwiecień-Maj)",
            regions = "Cała Polska",
            habitat = "Głębsze rzeki, zbiorniki zaporowe z twardym, żwirowym dnem",
            goodBites = "Ciemne noce, Świt, Zmierzch",
            badBites = "Słoneczne dni w płytkiej wodzie",
            imageResId = R.drawable.sandacz
        ),
        Fish(
            id = 6,
            name = "Płoć",
            latinName = "Rutilus rutilus",
            englishName = "Common Roach",
            category = "Spokojnego żeru",
            protectionSize = "15 cm",
            dailyLimit = "5 kg",
            protectionPeriod = "Brak",
            spawningTime = "Wiosna (Kwiecień-Maj)",
            regions = "Cała Polska",
            habitat = "Prawie wszystkie wody słodkie, od rzek po małe stawy",
            goodBites = "Cały rok (najlepiej Wiosna i Jesień)",
            badBites = "Skrajne upały",
            imageResId = R.drawable.ploc
        )
    )
    val waterBodies = listOf(
        WaterBody(1, "Rzeka Odra - Odcinek Wrocławski", "Dolnośląskie", "Długość: 850 km", "Okręg: PZW Wrocław", 51.1079, 17.0385, R.drawable.odra),
        WaterBody(2, "Zalew Mietkowski", "Dolnośląskie", "Wielkość: 9 km²", "Okręg: PZW Wrocław", 50.9667, 16.6167, R.drawable.mietkowski),
        WaterBody(3, "Staw Pilczycki", "Wrocław", "Wielkość: 8 ha", "Okręg: PZW Wrocław", 51.1411, 16.9422, R.drawable.pilczyce),
        WaterBody(4, "Jezioro Bajkał (Kamieniec)", "Dolnośląskie", "Wielkość: 60 ha", "Okręg: PZW Wrocław", 51.0768, 17.1824, R.drawable.bajkal),
        WaterBody(5, "Rzeka Bystrzyca", "Dolnośląskie", "Długość: 95 km", "Okręg: PZW Wrocław", 51.1444, 16.9328, R.drawable.bystrzyca),
        WaterBody(6, "Rzeka Oława", "Dolnośląskie", "Długość: 99 km", "Okręg: PZW Wrocław", 51.1033, 17.0549, R.drawable.olawa),
        WaterBody(7, "Kąpielisko Kopalnia", "Paniowice", "Wielkość: 18 ha", "Łowisko Komercyjne", 51.1897, 16.9381, R.drawable.kopalnia),
        WaterBody(8, "Staw Leśnicki", "Wrocław", "Wielkość: 3 ha", "Okręg: PZW Wrocław", 51.1481, 16.8617, R.drawable.lesnicki),
        WaterBody(9, "Rzeka Widawa", "Dolnośląskie", "Długość: 109 km", "Okręg: PZW Wrocław", 51.1683, 17.0425, R.drawable.widawa),
        WaterBody(10, "Zbiornik Sulistrowice", "Dolnośląskie", "Wielkość: 5 ha", "Okręg: PZW Wrocław", 50.8522, 16.6978, R.drawable.sulistrowice)
    )
}