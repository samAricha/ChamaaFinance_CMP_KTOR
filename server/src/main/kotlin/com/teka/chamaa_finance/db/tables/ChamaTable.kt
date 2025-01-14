package com.teka.chamaa_finance.db.tables;

import com.teka.chamaa_finance.dtos.ChamaDTO
import com.teka.chamaa_finance.model.Priority
import com.teka.chamaa_finance.model.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

object ChamaTable : IntIdTable("chama") {
    val chamaId = varchar("chama_id", 50).uniqueIndex()
    val chamaName = varchar("chama_name", 100)
    val chamaDescription = varchar("chama_description", 255)
    val dateFormed = varchar("date_formed", 50)
}

class ChamaDAO(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<ChamaDAO>(ChamaTable)

    var chamaId by ChamaTable.chamaId
    var chamaName by ChamaTable.chamaName
    var chamaDescription by ChamaTable.chamaDescription
    var dateFormed by ChamaTable.dateFormed

}


fun daoToChamaDTO(dao: ChamaDAO) = ChamaDTO(
    chamaId = dao.chamaId,
    chamaName = dao.chamaName,
    chamaDescription = dao.chamaDescription,
    dateFormed = dao.dateFormed
)

suspend fun ChamaDTO.toDAO(): ChamaDAO = suspendTransaction {
    ChamaDAO.new {
        chamaId = this@toDAO.chamaId
        chamaName = this@toDAO.chamaName
        chamaDescription = this@toDAO.chamaDescription
        dateFormed = this@toDAO.dateFormed
    }
}
