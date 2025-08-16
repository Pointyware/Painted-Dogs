package org.pointyware.painteddogs.shared.di

import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.pointyware.painteddogs.core.navigation.di.NavigationDependencies
import org.pointyware.painteddogs.feature.funds.di.FundDependencies
import org.pointyware.painteddogs.feature.payments.di.PaymentDependencies
import org.pointyware.painteddogs.feature.profiles.di.ProfileDependencies

/**
 * Top level dependencies for the app
 */
interface AppDependencies {
    fun getProfileDependencies(): ProfileDependencies
    fun getFundDependencies(): FundDependencies
    fun getPaymentDependencies(): PaymentDependencies
    fun getNavigationDependencies(): NavigationDependencies
}

fun getDependencies(): AppDependencies = KoinAppDependencies()

class KoinAppDependencies: AppDependencies, KoinComponent {
    override fun getProfileDependencies(): ProfileDependencies = get()
    override fun getFundDependencies(): FundDependencies = get()
    override fun getPaymentDependencies(): PaymentDependencies = get()
    override fun getNavigationDependencies(): NavigationDependencies = get()
}
