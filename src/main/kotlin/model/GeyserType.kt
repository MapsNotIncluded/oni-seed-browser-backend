/*
 * ONI Seed Browser Backend
 * Copyright (C) 2024 Stefan Oltmann
 * https://stefan-oltmann.de/oni-seed-browser
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package model

import kotlinx.serialization.Serializable

@Suppress("UNUSED")
@Serializable
enum class GeyserType(
    val type: String,
    val displayName: String
) {

    /*
     * Sorted by order to be displayed
     */

    COOL_STEAM(
        type = "steam",
        displayName = "Cool Steam Vent"
    ),
    HYDROGEN(
        type = "hot_hydrogen",
        displayName = "Hydrogen Vent"
    ),
    NATURAL_GAS(
        type = "methane",
        displayName = "Natural Gas Geyser"
    ),
    CHLORINE(
        type = "chlorine_gas",
        displayName = "Chlorine Gas Vent"
    ),
    HOT_STEAM(
        type = "hot_steam",
        displayName = "Steam Vent"
    ),
    HOT_CO2(
        type = "hot_co2",
        displayName = "Carbon Dioxide Vent"
    ),
    HOT_POLLUTED_O2(
        type = "hot_po2",
        displayName = "Hot Polluted Oxygen Vent"
    ),
    INFECTIOUS_POLLUTED_O2(
        type = "slimy_po2",
        displayName = "Infectious Polluted Oxygen Vent"
    ),
    WATER(
        type = "hot_water",
        displayName = "Water Geyser"
    ),
    COOL_SLUSH_WATER(
        type = "slush_water",
        displayName = "Cool Slush Geyser"
    ),
    POLLUTED_WATER(
        type = "filthy_water",
        displayName = "Polluted Water Vent"
    ),
    COOL_SALT_WATER(
        type = "slush_salt_water",
        displayName = "Cool Salt Slush Geyser"
    ),
    HOT_SALT_WATER(
        type = "salt_water",
        displayName = "Salt Water Geyser"
    ),
    LIQUID_CO2(
        type = "liquid_co2",
        displayName = "Carbon Dioxide Geyser"
    ),
    LEAKY_OIL_FISSURE(
        type = "oil_drip",
        displayName = "Leaky Oil Fissure"
    ),
    LIQUID_SULFUR_GEYSER(
        type = "liquid_sulfur",
        displayName = "Liquid Sulfur Geyser"
    ),
    IRON_VOLCANO(
        type = "molten_iron",
        displayName = "Iron Volcano"
    ),
    COPPER_VOLCANO(
        type = "molten_copper",
        displayName = "Copper Volcano"
    ),
    GOLD_VOLCANO(
        type = "molten_gold",
        displayName = "Gold Volcano"
    ),
    ALUMINIUM_VOLCANO(
        type = "molten_aluminum",
        displayName = "Aluminium Volcano"
    ),
    COBALT_VOLCANO(
        type = "molten_cobalt",
        displayName = "Cobalt Volcano"
    ),
    TUNGSTEN_VOLCANO(
        type = "molten_tungsten",
        displayName = "Tungsten Volcano"
    ),
    NIOBIUM_VOLCANO(
        type = "molten_niobium",
        displayName = "Niobium Volcano"
    ),
    VOLCANO(
        type = "big_volcano",
        displayName = "Valcano"
    ),
    MINOR_VOLCANO(
        type = "small_volcano",
        displayName = "Minor Volcano"
    ),
    OIL_RESERVOIR(
        type = "OilWell",
        displayName = "Oil Reservoir"
    )
}
