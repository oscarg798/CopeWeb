package copelist

import BASE_URL
import entities.Cope
import kotlinx.html.ThScope
import networking.CopeWebConsumer
import networking.endpoint.GetCopesEndpoint
import react.*
import react.dom.a
import react.dom.div
import react.dom.h2
import react.dom.img
import repositories.CopeRepositoryImpl

interface CopeListProps : RProps {
}

interface CopeListState : RState {
    var copes: Array<Cope>?
}

class CopeList : RComponent<CopeListProps, CopeListState>(), HelloContract.View {

    private val presenter: HelloContract.Presenter

    init {
        val consumer = CopeWebConsumer(BASE_URL)
        val repository = CopeRepositoryImpl(consumer, GetCopesEndpoint)
        presenter = HelloPresenter(GetCopesInteractor(repository))
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

                div("card") {
                    img(src = it.icon, classes = "logo") { }
                    div("text") {
                        a(href = it.url) {
                            +it.title
                        }
                    }
                }

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