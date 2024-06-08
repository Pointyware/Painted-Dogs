package org.pointyware.painteddogs.app.di

import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.pointyware.painteddogs.core.navigation.di.NavigationDependencies
import org.pointyware.painteddogs.feature.collections.core.di.FundDependencies
import org.pointyware.painteddogs.feature.payments.di.PaymentDependencies
import org.pointyware.painteddogs.feature.profiles.di.ProfileDependencies

/**
 * Top level dependencies for the app
 */
interface AppDependencies {
    fun provideProfileDependencies(): ProfileDependencies
    fun provideFundDependencies(): FundDependencies
    fun providePaymentDependencies(): PaymentDependencies

    fun getNavigationDependencies(): NavigationDependencies
}

fun getDependencies(): AppDependencies = KoinAppDependencies()

class KoinAppDependencies: AppDependencies, KoinComponent {
    override fun provideProfileDependencies(): ProfileDependencies = get()
    override fun provideFundDependencies(): FundDependencies = get()
    override fun providePaymentDependencies(): PaymentDependencies = get()
    override fun getNavigationDependencies(): NavigationDependencies = get()
}
