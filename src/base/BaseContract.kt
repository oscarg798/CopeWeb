package base

interface BaseContract {

    interface BaseView

    interface BasePresenter<View : BaseView> {
        var view: View?

        fun bind(view: View) {
            this.view = view
        }

        fun unbind() {
            view = null
        }
    }
}