package org.wit.Marathon.console.models

interface MarathonStore {
    fun findAll(): List<MarathonModel>
    fun findOne(id: Long): MarathonModel?
    fun create(marathon: MarathonModel)
    fun update(marathon: MarathonModel)
}