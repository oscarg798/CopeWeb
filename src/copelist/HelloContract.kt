package copelist

import base.BaseContract
import entities.Cope

interface HelloContract {

    interface View: BaseContract.BaseView {

        fun showCopes(copes: Array<Cope>)
    }

    interface Presenter :BaseContract.BasePresenter<View>
}