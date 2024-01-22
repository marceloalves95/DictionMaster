package br.com.dictionmaster.presentation.mock

import br.com.dictionmaster.domain.models.Definitions
import br.com.dictionmaster.domain.models.License
import br.com.dictionmaster.domain.models.Meanings
import br.com.dictionmaster.domain.models.Phonetics
import br.com.dictionmaster.domain.models.Search

val dummyMeanings = Meanings(
    partOfSpeech = "noun",
    definitions = listOf(
        Definitions(
            definition = "The process of imparting knowledge, skill and judgment.",
            synonyms = emptyList(),
            antonyms = emptyList(),
            example = "2016-06-17 AROP JOSEPH \\\"Education is the slight hammer that breaks the yoke of ignorance, and moulds knowledge, skills, ideas, good moral values in a person be it a child, a youth or full grown adult. no matter a persons age learning never stops\\\"."
        ),
        Definitions(
            definition = "Facts, skills and ideas that have been learned, either formally or informally.",
            synonyms = emptyList(),
            antonyms = emptyList(),
            example = "He has had a classical education."
        )
    ),
    synonyms = emptyList(),
    antonyms = emptyList()
)

val dummySearch = Search(
    word = "education",
    phonetic = "/ˌɛdjʊˈkeɪʃn̩/",
    phonetics = listOf(
        Phonetics(
            text = "/ˌɛdjʊˈkeɪʃn̩/",
            audio = "https://api.dictionaryapi.dev/media/pronunciations/en/education-uk.mp3",
            sourceUrl = "https://commons.wikimedia.org/w/index.php?curid=9014400",
            license = License(
                name = "BY 3.0 US",
                url = "https://creativecommons.org/licenses/by/3.0/us"
            )
        ),
        Phonetics(
            text = "/ˌɛdjʊˈkeɪʃn̩/",
            audio = "https://api.dictionaryapi.dev/media/pronunciations/en/education-us.mp3",
            sourceUrl = "https://commons.wikimedia.org/w/index.php?curid=857003",
            license = License(
                name = "CC BY-SA 3.0",
                url = "https://creativecommons.org/licenses/by-sa/3.0"
            )
        )
    ),
    origin = null,
    meanings = listOf(dummyMeanings),
    license = License(
        name = "CC BY-SA 3.0",
        url = "https://creativecommons.org/licenses/by-sa/3.0"
    ),
    sourceUrls = listOf(
        "https://en.wiktionary.org/wiki/education"
    )
)