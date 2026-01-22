week1
yksinkertainen Task-sovellus Jetpack Compose -teknologialla
Task-data class ja mock-dataa, sekä Kotlin-funktiot
sovellus ajetaan Android Studiossa emulaattorilla tai android-laitteella

week2
Kun state muuttuu compose päivittää näkymän automaattisesti.
Kun count muuttuu compose piirtää UI:n uudelleen ilman että kehittäjän tarvitsee päivittää näkymää käsin.

remember:
tila häviää, kun näkymä luodaan uudelleen (esim. ruudun kääntö)
logiikka ja UI sekoittuvat samaan tiedostoon
vaikea hallita isompaa sovellusta

viewmodel:
säilyttää tilan koko näytön elinkaaren ajan
erottaa liiketoimintalogiikan UI:sta
tekee koodista selkeämmän ja testattavamman
tukee paremmin suurempia ja monimutkaisempia sovelluksia
