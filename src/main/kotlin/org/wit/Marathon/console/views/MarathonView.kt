package org.wit.Marathon.console.views

import org.wit.Marathon.console.models.MarathonJSONStore
import org.wit.Marathon.console.models.MarathonMemStore
import org.wit.Marathon.console.models.MarathonModel

class MarathonView {

    fun menu() : Int {

        var option : Int
        var input: String?

        println("MAIN MENU")
        println(" 1. Add Marathon")
        println(" 2. Update Marathon")
        println(" 3. List All Marathons")
        println(" 4. Search Marathons")
        println("-1. Exit")
        println()
        print("Enter Option : ")
        input = readLine()!!
        option = if (input.toIntOrNull() != null && !input.isEmpty())
            input.toInt()
        else
            -9
        return option
    }

    fun listMarathons(marathons : MarathonJSONStore) {
        println("List All Marathon")
        println()
        marathons.logAll()
        println()
    }

    fun showMarathon(marathon : MarathonModel) {
        if(marathon != null)
            println("Marathon Details [ $marathon ]")
        else
            println("Marathon Not Found...")
    }

    fun addMarathonData(marathon : MarathonModel) : Boolean {

        println()
        print("Enter a Title : ")
        marathon.title = readLine()!!
        print("Enter a Description : ")
        marathon.description = readLine()!!

        return marathon.title.isNotEmpty() && marathon.description.isNotEmpty()
    }

    fun updateMarathonData(marathon : MarathonModel) : Boolean {

        var tempTitle: String?
        var tempDescription: String?

        if (marathon != null) {
            print("Enter a new Title for [ " + marathon.title + " ] : ")
            tempTitle = readLine()!!
            print("Enter a new Description for [ " + marathon.description + " ] : ")
            tempDescription = readLine()!!

            if (!tempTitle.isNullOrEmpty() && !tempDescription.isNullOrEmpty()) {
                marathon.title = tempTitle
                marathon.description = tempDescription
                return true
            }
        }
        return false
    }

    fun getId() : Long {
        var strId : String? // String to hold user input
        var searchId : Long // Long to hold converted id
        print("Enter id to Search/Update : ")
        strId = readLine()!!
        searchId = if (strId.toLongOrNull() != null && !strId.isEmpty())
            strId.toLong()
        else
            -9

        return searchId
    }
}