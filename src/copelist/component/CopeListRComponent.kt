package copelist.component

import BASE_URL
import cope.component.cope
import copelist.CopeListContract
import copelist.CopeListPresenter
import copelist.GetCopesInteractor
import entities.Cope
import networking.CopeWebConsumer
import networking.endpoint.GetCopesEndpoint
import react.*
import react.dom.h2
import repositories.CopeRepositoryImpl

interface CopeListProps : RProps {
}

interface CopeListState : RState {
    var copes: Array<Cope>?
}

class CopeList : RComponent<CopeListProps, CopeListState>(), CopeListContract.View {

    private val presenter: CopeListContract.Presenter

    init {
        val consumer = CopeWebConsumer(BASE_URL)
        val repository = CopeRepositoryImpl(consumer, GetCopesEndpoint)
        presenter = CopeListPresenter(GetCopesInteractor(repository))
    }

    override fun componentDidMount() {
        presenter.bind(this)
    }

    override fun componentWillMount() {
        presenter.unbind()
    }

    override fun RBuilder.render() {
        if (state.copes != null) {
            state.copes!!.forEach {
                cope(it)
            }
        } else {
            h2 {
                +"Loading"
            }
        }
    }

    override fun showCopes(copes: Array<Cope>) {
        setState {
            this.copes = copes
        }
    }
}


fun RBuilder.copeList() = child(CopeList::class) {}