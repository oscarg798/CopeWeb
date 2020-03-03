package copelist

import base.interactor.Interactor
import entities.Cope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HelloPresenter(private val getCopesInteractor: Interactor<Array<Cope>, Unit>) : HelloContract.Presenter {

    override var view: HelloContract.View? = null

    override fun bind(view: HelloContract.View) {
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