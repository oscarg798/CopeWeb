package cope.component

import entities.Cope
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.a
import react.dom.div
import react.dom.img

interface CopeProps : RProps {
    var cope: Cope
}

class CopeRComponent : RComponent<CopeProps, RState>() {

    override fun RBuilder.render() {
        val cope = props.cope
        div(CARD_STYLE) {
            div(CARD_CONTENT_STYLE){
                div(LOGO_CONTAINER_STYLE) {
                    img(src = cope.icon, classes = LOGO_STYLE) { }
                }
                div(CONTAINER_CONTAINER_STYLE) {
                    div(TEXT_STYLE) {
                        a(href = cope.url, target = LINK_TARGET) {
                            +cope.title
                        }
                    }
                }
            }
        }
    }

    companion object {
        private const val CARD_STYLE = "card"
        private const val CARD_CONTENT_STYLE = "row justify-content-start"
        private const val LOGO_STYLE = "logo"
        private const val TEXT_STYLE = "text"
        private const val LINK_TARGET = "_blank"
        private const val LOGO_CONTAINER_STYLE = "col-md-2 logo_container"
        private const val CONTAINER_CONTAINER_STYLE = "col-md-10"
    }
}

fun RBuilder.cope(cope: Cope) = child(CopeRComponent::class) {
    attrs.cope = cope
}