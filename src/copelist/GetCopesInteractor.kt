package copelist

import base.interactor.Interactor
import entities.Cope
import repositories.CopeRepository

class GetCopesInteractor(private val copeRepository: CopeRepository) : Interactor<Array<Cope>, Unit> {

    override suspend fun invoke(input: Unit): Array<Cope> {

        return copeRepository.getCopes()
    }
}