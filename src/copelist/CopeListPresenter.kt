package copelist

import base.interactor.Interactor
import entities.Cope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CopeListPresenter(private val getCopesInteractor: Interactor<Array<Cope>, Unit>) : CopeListContract.Presenter {

    override var view: CopeListContract.View? = null

    override fun bind(view: CopeListContract.View) {
        this.view = view
        getCopes()
    }

    override fun unbind() {
        view = null
    }

    private fun getCopes() {

        CoroutineScope(Dispatchers.Default).launch {
            val result = getCopesInteractor(Unit)
            view?.showCopes(result)

        }
    }
}