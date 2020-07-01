package org.wit.Marathon.console.models

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import mu.KotlinLogging
import org.wit.Marathon.console.helpers.exists
import org.wit.Marathon.console.helpers.read
import org.wit.Marathon.console.helpers.write

import org.wit.Marathon.console.helpers.*
import java.util.*

private val logger = KotlinLogging.logger {}

val JSON_FILE = "marathon.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<ArrayList<MarathonModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class MarathonJSONStore : MarathonStore {

    var marathons = mutableListOf<MarathonModel>()

    init {
        if (exists(JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<MarathonModel> {
        return marathons
    }

    override fun findOne(id: Long) : MarathonModel? {
        var foundMarathon: MarathonModel? = marathons.find { p -> p.id == id }
        return foundMarathon
    }

    override fun create(marathon: MarathonModel) {
        marathon.id = generateRandomId()
        marathons.add(marathon)
        serialize()
    }

    override fun update(marathon: MarathonModel) {
        var foundPlacemark = findOne(marathon.id!!)
        if (foundPlacemark != null) {
            foundPlacemark.title = marathon.title
            foundPlacemark.description = marathon.description
        }
         serialize()
    }

    internal fun logAll() {
        marathons.forEach { logger.info("${it}") }
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(marathons,
            listType
        )
        write(
            JSON_FILE,
            jsonString
        )
    }

    private fun deserialize() {
        val jsonString =
            read(JSON_FILE)
        marathons = Gson().fromJson(jsonString, listType)
    }
}