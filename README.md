# Task App

## Week1 – Perustoiminnot (Compose + Kotlin)

### Toteutetut ominaisuudet
- Task-data class ja mock-data
- Kotlin-funktiot:
  - tehtävän lisääminen (add)
  - tilan vaihtaminen (toggle)
  - suodatus (filter)
  - järjestäminen (sort)
- Jetpack Compose -käyttöliittymä
- Tehtävälista ja toimintanapit

### Käyttö
- Add task → lisää uuden tehtävän
- Done / Undo → vaihtaa tehtävän tilan
- Done / Not done / All → suodattaa listan
- Sort by date → järjestää tehtävät

Sovellus ajetaan Android Studiossa emulaattorilla tai Android-laitteella.

---

## Week2 – ViewModel ja tilanhallinta

### Toteutetut ominaisuudet
- TaskViewModel-luokka
- State siirretty Composesta ViewModeliin
- LazyColumn tehtävälistalle
- Uuden tehtävän lisäys TextFieldien avulla
- Checkbox ja Delete-nappi tehtäville
- Suodatus ja järjestäminen ViewModelin kautta
- Scrollattava näkymä

### ViewModel vs remember
- remember säilyttää tilan vain Composable-funktion sisällä
- ViewModel säilyttää tilan näkymän elinkaaren ajan
- logiikka on erillään käyttöliittymästä
- koodi on selkeämpi ja helpommin laajennettavissa
