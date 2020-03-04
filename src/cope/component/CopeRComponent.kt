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
        div("card") {
            val cope = props.cope
            img(src = cope.icon, classes = "logo") { }
            div("text") {
                a(href = cope.url) {
                    +cope.title
                }
            }
        }
    }
}

fun RBuilder.cope(cope: Cope) = child(CopeRComponent::class) {
    attrs.cope = cope
}