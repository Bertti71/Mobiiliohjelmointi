Task App – README
Week1 – Perus Task-sovellus (Jetpack Compose)

Week1-tehtävässä toteutettiin yksinkertainen Task-sovellus Jetpack Compose -teknologialla.
Sovelluksessa luotiin Task-data class, mock-dataa sekä Kotlin-funktiot tehtävien käsittelyyn.

Sovellus näyttää tehtävälistan ja mahdollistaa tehtävien lisäämisen, tilan vaihtamisen, suodattamisen ja järjestämisen.
Käyttöliittymä on toteutettu Composella ilman XML:ää.

Sovellus ajetaan Android Studiossa emulaattorilla tai Android-laitteella.

Week2 – Tilanhallinta ja ViewModel

Week2-tehtävässä sovellusta laajennettiin käyttämään ViewModelia.
Compose päivittää käyttöliittymän automaattisesti, kun state muuttuu.

Pelkkä remember sopii vain yksinkertaiseen paikalliseen tilaan, mutta tila voi hävitä esimerkiksi ruudun käännössä ja logiikka sekoittuu helposti UI:hin.

ViewModel säilyttää tilan näkymän elinkaaren ajan ja erottaa logiikan käyttöliittymästä.
Tämä tekee koodista selkeämmän, helpommin laajennettavan ja paremmin testattavan.
