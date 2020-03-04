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
        div(CARD_STYLE_NAME) {
            val cope = props.cope
            img(src = cope.icon, classes = LOGO_STYLE_NAME) { }
            div(TEXT_STYLE_NAME) {
                a(href = cope.url, target = LINK_TARGET) {
                    +cope.title
                }
            }
        }
    }

    companion object {
        private const val CARD_STYLE_NAME = "card"
        private const val LOGO_STYLE_NAME = "logo"
        private const val TEXT_STYLE_NAME = "text"
        private const val LINK_TARGET = "_blank"
    }
}

fun RBuilder.cope(cope: Cope) = child(CopeRComponent::class) {
    attrs.cope = cope
}