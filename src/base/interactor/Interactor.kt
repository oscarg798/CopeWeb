package base.interactor

interface Interactor<Output, Input> {

    suspend operator fun invoke(input: Input): Output
}