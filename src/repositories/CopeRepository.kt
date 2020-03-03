package repositories

import entities.Cope

interface CopeRepository {

    suspend fun getCopes(): Array<Cope>
}