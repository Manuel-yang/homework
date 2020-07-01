package org.wit.Marathon.console.controllers

import mu.KotlinLogging
import org.wit.Marathon.console.models.MarathonJSONStore
import org.wit.Marathon.console.models.MarathonMemStore
import org.wit.Marathon.console.models.MarathonModel
import org.wit.Marathon.console.views.MarathonView

class MarathonController {

    // val marathons = MarathonMemStore()
     val marathons = MarathonJSONStore()
    val marathonView = MarathonView()
    val logger = KotlinLogging.logger {}

    init {
        logger.info { "Launching Marathon Console App" }
        println("Marathon Kotlin App Version 4.0")
    }

    fun start() {
        var input: Int

        do {
            input = menu()
            when (input) {
                1 -> add()
                2 -> update()
                3 -> list()
                4 -> search()
                -99 -> dummyData()
                -1 -> println("Exiting App")
                else -> println("Invalid Option")
            }
            println()
        } while (input != -1)
        logger.info { "Shutting Down Marathon Console App" }
    }

    fun menu() :Int { return marathonView.menu() }

    fun add(){
        var aMarathon = MarathonModel()

        if (marathonView.addMarathonData(aMarathon))
            marathons.create(aMarathon)
        else
            logger.info("Marathon Not Added")
    }

    fun list() {
        marathonView.listMarathons(marathons)
    }

    fun update() {

        marathonView.listMarathons(marathons)
        var searchId = marathonView.getId()
        val aMarathon = search(searchId)

        if(aMarathon != null) {
            if(marathonView.updateMarathonData(aMarathon)) {
                marathons.update(aMarathon)
                marathonView.showMarathon(aMarathon)
                logger.info("Marathon Updated : [ $aMarathon ]")
            }
            else
                logger.info("Marathon Not Updated")
        }
        else
            println("Marathon Not Updated...")
    }

    fun search() {
        val aMarathon = search(marathonView.getId())!!
        marathonView.showMarathon(aMarathon)
    }


    fun search(id: Long) : MarathonModel? {
        var foundMarathon = marathons.findOne(id)
        return foundMarathon
    }

    fun dummyData() {
        marathons.create(
            MarathonModel(
                title = "New York New York",
                description = "So Good They Named It Twice"
            )
        )
        marathons.create(
            MarathonModel(
                title = "Ring of Kerry",
                description = "Some place in the Kingdom"
            )
        )
        marathons.create(
            MarathonModel(
                title = "Waterford City",
                description = "You get great Blaas Here!!"
            )
        )
    }
}