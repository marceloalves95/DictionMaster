package br.com.dictionmaster.data.remote.mapper

import br.com.dictionmaster.data.remote.models.DefinitionsResponse
import br.com.dictionmaster.data.remote.models.LicenseResponse
import br.com.dictionmaster.data.remote.models.MeaningsResponse
import br.com.dictionmaster.data.remote.models.PhoneticsResponse
import br.com.dictionmaster.data.remote.models.SearchResponse
import br.com.dictionmaster.domain.models.Definitions
import br.com.dictionmaster.domain.models.License
import br.com.dictionmaster.domain.models.Meanings
import br.com.dictionmaster.domain.models.Phonetics
import br.com.dictionmaster.domain.models.Search

internal fun SearchResponse.toSearch() = Search(
    word = word,
    phonetic = phonetic,
    phonetics = phonetics?.toListPhonetics() ?: emptyList(),
    origin = origin,
    meanings = meanings?.toListMeanings() ?: emptyList(),
    license = license?.toLicense(),
    sourceUrls = sourceUrls
)

internal fun List<PhoneticsResponse>.toListPhonetics() = this.map { phonetics ->
    with(phonetics) {
        Phonetics(
            text = text,
            audio = audio,
            sourceUrl = sourceUrl,
            license = license?.toLicense()
        )
    }
}

internal fun LicenseResponse.toLicense() = License(
    name = name, url = url
)

internal fun List<MeaningsResponse>.toListMeanings() = this.map { meanings ->
    with(meanings) {
        Meanings(
            partOfSpeech = partOfSpeech,
            definitions = definitions?.toListDefinitions() ?: emptyList(),
            synonyms = synonyms,
            antonyms = antonyms
        )
    }
}

internal fun List<DefinitionsResponse>.toListDefinitions() = this.map { definitions ->
    with(definitions) {
        Definitions(
            definition = definition,
            synonyms = synonyms,
            antonyms = antonyms,
            example = example
        )
    }
}
