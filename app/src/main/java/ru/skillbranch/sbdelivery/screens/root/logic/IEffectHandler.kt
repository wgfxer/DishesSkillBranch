package ru.skillbranch.sbdelivery.screens.root.logic

/**
 * @author Valeriy Minnulin
 */
interface IEffectHandler<E, M> {
    suspend fun handle(effect: E, commit: (M) -> Unit)
}