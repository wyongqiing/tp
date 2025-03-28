package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Set;

/**
 * Represents a Person's nationality in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidNationality(String)}
 */
public class Nationality {
    private static final String nationalities = " Armenian, Australian, Austrian, Azerbaijani, Bahamian, Bahraini,\n "
            + "Bangladeshi, Barbadian, Belarusian, Belgian, Belizean, Beninese, Bhutanese,\n "
            + "Bolivian, Bosnian, Botswanan, Brazilian, British, Bruneian, Bulgarian, Burkinabé,\n "
            + "Burmese, Burundian, Cambodian, Cameroonian, Canadian, Cape Verdean, Central African,\n "
            + "Chadian, Chilean, Chinese, Colombian, Comorian, Congolese, Costa Rican, Croatian,\n "
            + "Cuban, Cypriot, Czech, Danish, Djiboutian, Dominican, Dutch, Ecuadorian, Egyptian,\n "
            + "Emirati, Equatorial Guinean, Eritrean, Estonian, Ethiopian, Fijian, Filipino,\n "
            + "Finnish, French, Gabonese, Gambian, Georgian, German, Ghanaian, Greek, Grenadian,\n "
            + "Guatemalan, Guinean, Guyanese, Haitian, Honduran, Hungarian, Icelandic, Indian,\n "
            + "Indonesian, Iranian, Iraqi, Irish, Israeli, Italian, Ivorian, Jamaican, Japanese,\n "
            + "Jordanian, Kazakh, Kenyan, Kiribati, Kuwaiti, Kyrgyz, Laotian, Latvian, Lebanese,\n "
            + "Liberian, Libyan, Liechtenstein, Lithuanian, Luxembourgish, Malagasy, Malawian,\n "
            + "Malaysian, Maldivian, Malian, Maltese, Marshallese, Mauritanian, Mauritian, Mexican,\n "
            + "Micronesian, Moldovan, Monacan, Mongolian, Montenegrin, Moroccan, Mozambican,\n "
            + "Namibian, Nauruan, Nepalese, New Zealander, Nicaraguan, Nigerien, Nigerian,\n "
            + "North Korean, North Macedonian, Norwegian, Omani, Pakistani, Palauan, Palestinian,\n "
            + "Panamanian, Papua New Guinean, Paraguayan, Peruvian, Polish, Portuguese, Qatari,\n "
            + "Romanian, Russian, Rwandan, Saint Lucian, Salvadoran, Samoan, Saudi Arabian,\n "
            + "Scottish, Senegalese, Serbian, Seychellois, Sierra Leonean, Singaporean, Slovak,\n "
            + "Slovenian, Solomon Islander, Somali, South African, South Korean, South Sudanese,\n "
            + "Spanish, Sri Lankan, Sudanese, Surinamese, Swazi, Swedish, Swiss, Syrian, Tajik,\n "
            + "Tanzanian, Thai, Timorese, Togolese, Tongan, Trinidadian, Tunisian, Turkish, Turkmen,\n "
            + "Tuvaluan, Ugandan, Ukrainian, Uruguayan, Uzbek, Vanuatuan, Venezuelan, Vietnamese,\n "
            + "Welsh, Yemeni, Zambian, Zimbabwean;";

    public static final String MESSAGE_CONSTRAINTS =
            "Nationality must contain only alphabetic characters and may include spaces for multi-word nationalities,"
                    + " but cannot be left blank.\n"
                    + "Here is the list of Nationalities:\n"
                    + nationalities;

    private static final Set<String> VALID_NATIONALITIES = Set.of(
            "Afghan", "Albanian", "Algerian", "American", "Andorran", "Angolan", "Argentine",
            "Armenian", "Australian", "Austrian", "Azerbaijani", "Bahamian", "Bahraini",
            "Bangladeshi", "Barbadian", "Belarusian", "Belgian", "Belizean", "Beninese",
            "Bhutanese", "Bolivian", "Bosnian", "Botswanan", "Brazilian", "British",
            "Bruneian", "Bulgarian", "Burkinabé", "Burmese", "Burundian", "Cambodian",
            "Cameroonian", "Canadian", "Cape Verdean", "Central African", "Chadian",
            "Chilean", "Chinese", "Colombian", "Comorian", "Congolese", "Costa Rican",
            "Croatian", "Cuban", "Cypriot", "Czech", "Danish", "Djiboutian", "Dominican",
            "Dutch", "Ecuadorian", "Egyptian", "Emirati", "Equatorial Guinean",
            "Eritrean", "Estonian", "Ethiopian", "Fijian", "Filipino", "Finnish", "French",
            "Gabonese", "Gambian", "Georgian", "German", "Ghanaian", "Greek", "Grenadian",
            "Guatemalan", "Guinean", "Guyanese", "Haitian", "Honduran", "Hungarian",
            "Icelandic", "Indian", "Indonesian", "Iranian", "Iraqi", "Irish", "Israeli",
            "Italian", "Ivorian", "Jamaican", "Japanese", "Jordanian", "Kazakh", "Kenyan",
            "Kiribati", "Kuwaiti", "Kyrgyz", "Laotian", "Latvian", "Lebanese", "Liberian",
            "Libyan", "Liechtenstein", "Lithuanian", "Luxembourgish", "Malagasy", "Malawian",
            "Malaysian", "Maldivian", "Malian", "Maltese", "Marshallese", "Mauritanian",
            "Mauritian", "Mexican", "Micronesian", "Moldovan", "Monacan", "Mongolian",
            "Montenegrin", "Moroccan", "Mozambican", "Namibian", "Nauruan", "Nepalese",
            "New Zealander", "Nicaraguan", "Nigerien", "Nigerian", "North Korean",
            "North Macedonian", "Norwegian", "Omani", "Pakistani", "Palauan", "Palestinian",
            "Panamanian", "Papua New Guinean", "Paraguayan", "Peruvian", "Polish", "Portuguese",
            "Qatari", "Romanian", "Russian", "Rwandan", "Saint Lucian", "Salvadoran", "Samoan",
            "Saudi Arabian", "Scottish", "Senegalese", "Serbian", "Seychellois", "Sierra Leonean",
            "Singaporean", "Slovak", "Slovenian", "Solomon Islander", "Somali", "South African",
            "South Korean", "South Sudanese", "Spanish", "Sri Lankan", "Sudanese", "Surinamese",
            "Swazi", "Swedish", "Swiss", "Syrian", "Tajik", "Tanzanian", "Thai", "Timorese",
            "Togolese", "Tongan", "Trinidadian", "Tunisian", "Turkish", "Turkmen", "Tuvaluan",
            "Ugandan", "Ukrainian", "Uruguayan", "Uzbek", "Vanuatuan", "Venezuelan", "Vietnamese",
            "Welsh", "Yemeni", "Zambian", "Zimbabwean"
    );


    public final String value;

    /**
     * Constructs a {@code Nationality}.
     *
     * @param nationality A valid Nationality.
     */
    public Nationality(String nationality) {
        requireNonNull(nationality);
        checkArgument(isValidNationality(nationality), MESSAGE_CONSTRAINTS);
        value = nationality;
    }

    public static boolean isValidNationality(String nationality) {
        return VALID_NATIONALITIES.contains(nationality);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Nationality)) {
            return false;
        }

        Nationality otherNationality = (Nationality) other;
        return otherNationality.value.equals(value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
